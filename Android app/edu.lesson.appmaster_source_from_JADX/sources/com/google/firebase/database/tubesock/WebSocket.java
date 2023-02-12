package com.google.firebase.database.tubesock;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import com.google.firebase.database.connection.ConnectionContext;
import com.google.firebase.database.logging.LogWrapper;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import p004io.grpc.internal.GrpcUtil;

public class WebSocket {
    static final byte OPCODE_BINARY = 2;
    static final byte OPCODE_CLOSE = 8;
    static final byte OPCODE_NONE = 0;
    static final byte OPCODE_PING = 9;
    static final byte OPCODE_PONG = 10;
    static final byte OPCODE_TEXT = 1;
    private static final int SSL_HANDSHAKE_TIMEOUT_MS = 60000;
    private static final String THREAD_BASE_NAME = "TubeSock";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final AtomicInteger clientCount = new AtomicInteger(0);
    private static ThreadInitializer intializer = new ThreadInitializer() {
        public void setName(Thread t, String name) {
            t.setName(name);
        }
    };
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private final int clientId;
    private WebSocketEventHandler eventHandler;
    private final WebSocketHandshake handshake;
    private final Thread innerThread;
    private final LogWrapper logger;
    private final WebSocketReceiver receiver;
    private volatile Socket socket;
    private final String sslCacheDirectory;
    private volatile State state;
    private final URI url;
    private final WebSocketWriter writer;

    private enum State {
        NONE,
        CONNECTING,
        CONNECTED,
        DISCONNECTING,
        DISCONNECTED
    }

    static ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    static ThreadInitializer getIntializer() {
        return intializer;
    }

    public static void setThreadFactory(ThreadFactory threadFactory2, ThreadInitializer intializer2) {
        threadFactory = threadFactory2;
        intializer = intializer2;
    }

    public WebSocket(ConnectionContext context, URI url2) {
        this(context, url2, (String) null);
    }

    public WebSocket(ConnectionContext context, URI url2, String protocol) {
        this(context, url2, protocol, (Map<String, String>) null);
    }

    public WebSocket(ConnectionContext context, URI url2, String protocol, Map<String, String> extraHeaders) {
        this.state = State.NONE;
        this.socket = null;
        this.eventHandler = null;
        int incrementAndGet = clientCount.incrementAndGet();
        this.clientId = incrementAndGet;
        this.innerThread = getThreadFactory().newThread(new Runnable() {
            public void run() {
                WebSocket.this.runReader();
            }
        });
        this.url = url2;
        this.sslCacheDirectory = context.getSslCacheDirectory();
        this.logger = new LogWrapper(context.getLogger(), "WebSocket", "sk_" + incrementAndGet);
        this.handshake = new WebSocketHandshake(url2, protocol, extraHeaders);
        this.receiver = new WebSocketReceiver(this);
        this.writer = new WebSocketWriter(this, THREAD_BASE_NAME, incrementAndGet);
    }

    public void setEventHandler(WebSocketEventHandler eventHandler2) {
        this.eventHandler = eventHandler2;
    }

    /* access modifiers changed from: package-private */
    public WebSocketEventHandler getEventHandler() {
        return this.eventHandler;
    }

    public synchronized void connect() {
        if (this.state != State.NONE) {
            this.eventHandler.onError(new WebSocketException("connect() already called"));
            close();
            return;
        }
        getIntializer().setName(getInnerThread(), "TubeSockReader-" + this.clientId);
        this.state = State.CONNECTING;
        getInnerThread().start();
    }

    public synchronized void send(String data) {
        send((byte) 1, data.getBytes(UTF8));
    }

    public synchronized void send(byte[] data) {
        send((byte) 2, data);
    }

    /* access modifiers changed from: package-private */
    public synchronized void pong(byte[] data) {
        send((byte) 10, data);
    }

    private synchronized void send(byte opcode, byte[] data) {
        if (this.state != State.CONNECTED) {
            this.eventHandler.onError(new WebSocketException("error while sending data: not connected"));
        } else {
            try {
                this.writer.send(opcode, true, data);
            } catch (IOException e) {
                this.eventHandler.onError(new WebSocketException("Failed to send frame", e));
                close();
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public void handleReceiverError(WebSocketException e) {
        this.eventHandler.onError(e);
        if (this.state == State.CONNECTED) {
            close();
        }
        closeSocket();
    }

    /* renamed from: com.google.firebase.database.tubesock.WebSocket$3 */
    static /* synthetic */ class C07513 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State = iArr;
            try {
                iArr[State.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State[State.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State[State.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State[State.DISCONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$firebase$database$tubesock$WebSocket$State[State.DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public synchronized void close() {
        switch (C07513.$SwitchMap$com$google$firebase$database$tubesock$WebSocket$State[this.state.ordinal()]) {
            case 1:
                this.state = State.DISCONNECTED;
                return;
            case 2:
                closeSocket();
                return;
            case 3:
                sendCloseHandshake();
                return;
            case 4:
                return;
            case 5:
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void onCloseOpReceived() {
        closeSocket();
    }

    private synchronized void closeSocket() {
        if (this.state != State.DISCONNECTED) {
            this.receiver.stopit();
            this.writer.stopIt();
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (Exception e) {
                    this.eventHandler.onError(new WebSocketException("Failed to close", e));
                }
            }
            this.state = State.DISCONNECTED;
            this.eventHandler.onClose();
        }
    }

    private void sendCloseHandshake() {
        try {
            this.state = State.DISCONNECTING;
            this.writer.stopIt();
            this.writer.send((byte) 8, true, new byte[0]);
        } catch (IOException e) {
            this.eventHandler.onError(new WebSocketException("Failed to send close frame", e));
        }
    }

    private Socket createSocket() {
        String scheme = this.url.getScheme();
        String host = this.url.getHost();
        int port = this.url.getPort();
        if (scheme != null && scheme.equals("ws")) {
            if (port == -1) {
                port = 80;
            }
            try {
                return new Socket(host, port);
            } catch (UnknownHostException uhe) {
                throw new WebSocketException("unknown host: " + host, uhe);
            } catch (IOException ioe) {
                throw new WebSocketException("error while creating socket to " + this.url, ioe);
            }
        } else if (scheme == null || !scheme.equals("wss")) {
            throw new WebSocketException("unsupported protocol: " + scheme);
        } else {
            if (port == -1) {
                port = GrpcUtil.DEFAULT_PORT_SSL;
            }
            SSLSessionCache sessionCache = null;
            try {
                if (this.sslCacheDirectory != null) {
                    sessionCache = new SSLSessionCache(new File(this.sslCacheDirectory));
                }
            } catch (IOException e) {
                this.logger.debug("Failed to initialize SSL session cache", e, new Object[0]);
            }
            try {
                SSLSocket sslSocket = (SSLSocket) SSLCertificateSocketFactory.getDefault(SSL_HANDSHAKE_TIMEOUT_MS, sessionCache).createSocket(host, port);
                if (HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sslSocket.getSession())) {
                    return sslSocket;
                }
                throw new WebSocketException("Error while verifying secure socket to " + this.url);
            } catch (UnknownHostException uhe2) {
                throw new WebSocketException("unknown host: " + host, uhe2);
            } catch (IOException ioe2) {
                throw new WebSocketException("error while creating secure socket to " + this.url, ioe2);
            }
        }
    }

    public void blockClose() throws InterruptedException {
        if (this.writer.getInnerThread().getState() != Thread.State.NEW) {
            this.writer.getInnerThread().join();
        }
        getInnerThread().join();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = new java.io.DataInputStream(r2.getInputStream());
        r3 = r2.getOutputStream();
        r3.write(r1.handshake.getHandshake());
        r4 = false;
        r6 = new byte[1000];
        r7 = 0;
        r8 = new java.util.ArrayList<>();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r4 != false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
        r9 = r0.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        if (r9 == -1) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0050, code lost:
        r6[r7] = (byte) r9;
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        if (r6[r7 - 1] != 10) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0063, code lost:
        if (r6[r7 - 2] != 13) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
        r10 = new java.lang.String(r6, UTF8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0076, code lost:
        if (r10.trim().equals("") == false) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0078, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        r8.add(r10.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        r6 = new byte[1000];
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if (r7 == 1000) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        throw new com.google.firebase.database.tubesock.WebSocketException("Unexpected long line in handshake: " + new java.lang.String(r6, UTF8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b3, code lost:
        throw new com.google.firebase.database.tubesock.WebSocketException("Connection closed before handshake was complete");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b4, code lost:
        r10 = 0;
        r1.handshake.verifyServerStatusLine(r8.get(0));
        r8.remove(0);
        r9 = new java.util.HashMap<>();
        r11 = r8.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d0, code lost:
        if (r11.hasNext() == false) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d2, code lost:
        r13 = r11.next().split(": ", 2);
        r9.put(r13[r10].toLowerCase(java.util.Locale.US), r13[1].toLowerCase(java.util.Locale.US));
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f5, code lost:
        r1.handshake.verifyServerHandshakeHeaders(r9);
        r1.writer.setOutput(r3);
        r1.receiver.setInput(r0);
        r1.state = com.google.firebase.database.tubesock.WebSocket.State.CONNECTED;
        r1.writer.getInnerThread().start();
        r1.eventHandler.onOpen();
        r1.receiver.run();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void runReader() {
        /*
            r16 = this;
            r1 = r16
            java.net.Socket r0 = r16.createSocket()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r2 = r0
            monitor-enter(r16)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r1.socket = r2     // Catch:{ all -> 0x011c }
            com.google.firebase.database.tubesock.WebSocket$State r0 = r1.state     // Catch:{ all -> 0x011c }
            com.google.firebase.database.tubesock.WebSocket$State r3 = com.google.firebase.database.tubesock.WebSocket.State.DISCONNECTED     // Catch:{ all -> 0x011c }
            if (r0 != r3) goto L_0x0025
            java.net.Socket r0 = r1.socket     // Catch:{ IOException -> 0x001e }
            r0.close()     // Catch:{ IOException -> 0x001e }
            r0 = 0
            r1.socket = r0     // Catch:{ all -> 0x011c }
            monitor-exit(r16)     // Catch:{ all -> 0x011c }
            r16.close()
            return
        L_0x001e:
            r0 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x011c }
            r3.<init>(r0)     // Catch:{ all -> 0x011c }
            throw r3     // Catch:{ all -> 0x011c }
        L_0x0025:
            monitor-exit(r16)     // Catch:{ all -> 0x011c }
            java.io.DataInputStream r0 = new java.io.DataInputStream     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r0.<init>(r3)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.io.OutputStream r3 = r2.getOutputStream()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketHandshake r4 = r1.handshake     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            byte[] r4 = r4.getHandshake()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r3.write(r4)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r4 = 0
            r5 = 1000(0x3e8, float:1.401E-42)
            byte[] r6 = new byte[r5]     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r7 = 0
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r8.<init>()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
        L_0x0047:
            if (r4 != 0) goto L_0x00b4
            int r9 = r0.read()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10 = -1
            if (r9 == r10) goto L_0x00ac
            byte r10 = (byte) r9     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r6[r7] = r10     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            int r7 = r7 + 1
            int r10 = r7 + -1
            byte r10 = r6[r10]     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r11 = 10
            if (r10 != r11) goto L_0x0086
            int r10 = r7 + -2
            byte r10 = r6[r10]     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r11 = 13
            if (r10 != r11) goto L_0x0086
            java.lang.String r10 = new java.lang.String     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.nio.charset.Charset r11 = UTF8     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.<init>(r6, r11)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r11 = r10.trim()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r12 = ""
            boolean r11 = r11.equals(r12)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            if (r11 == 0) goto L_0x007a
            r4 = 1
            goto L_0x0081
        L_0x007a:
            java.lang.String r11 = r10.trim()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r8.add(r11)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
        L_0x0081:
            byte[] r11 = new byte[r5]     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r6 = r11
            r7 = 0
            goto L_0x008a
        L_0x0086:
            r10 = 1000(0x3e8, float:1.401E-42)
            if (r7 == r10) goto L_0x008c
        L_0x008a:
            goto L_0x0047
        L_0x008c:
            java.lang.String r10 = new java.lang.String     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.nio.charset.Charset r11 = UTF8     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.<init>(r6, r11)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketException r11 = new com.google.firebase.database.tubesock.WebSocketException     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r12.<init>()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r13 = "Unexpected long line in handshake: "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.StringBuilder r12 = r12.append(r10)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r12 = r12.toString()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r11.<init>(r12)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            throw r11     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
        L_0x00ac:
            com.google.firebase.database.tubesock.WebSocketException r10 = new com.google.firebase.database.tubesock.WebSocketException     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r11 = "Connection closed before handshake was complete"
            r10.<init>(r11)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            throw r10     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
        L_0x00b4:
            com.google.firebase.database.tubesock.WebSocketHandshake r9 = r1.handshake     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10 = 0
            java.lang.Object r11 = r8.get(r10)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r9.verifyServerStatusLine(r11)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r8.remove(r10)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r9.<init>()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.util.Iterator r11 = r8.iterator()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
        L_0x00cc:
            boolean r12 = r11.hasNext()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            if (r12 == 0) goto L_0x00f5
            java.lang.Object r12 = r11.next()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r13 = ": "
            r14 = 2
            java.lang.String[] r13 = r12.split(r13, r14)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r14 = r13[r10]     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.util.Locale r15 = java.util.Locale.US     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r14 = r14.toLowerCase(r15)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r15 = 1
            r15 = r13[r15]     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.util.Locale r10 = java.util.Locale.US     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.String r10 = r15.toLowerCase(r10)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r9.put(r14, r10)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10 = 0
            goto L_0x00cc
        L_0x00f5:
            com.google.firebase.database.tubesock.WebSocketHandshake r10 = r1.handshake     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.verifyServerHandshakeHeaders(r9)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketWriter r10 = r1.writer     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.setOutput(r3)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketReceiver r10 = r1.receiver     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.setInput(r0)     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocket$State r10 = com.google.firebase.database.tubesock.WebSocket.State.CONNECTED     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r1.state = r10     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketWriter r10 = r1.writer     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            java.lang.Thread r10 = r10.getInnerThread()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.start()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketEventHandler r10 = r1.eventHandler     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.onOpen()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            com.google.firebase.database.tubesock.WebSocketReceiver r10 = r1.receiver     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            r10.run()     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
            goto L_0x0148
        L_0x011c:
            r0 = move-exception
            monitor-exit(r16)     // Catch:{ all -> 0x011c }
            throw r0     // Catch:{ WebSocketException -> 0x0142, all -> 0x011f }
        L_0x011f:
            r0 = move-exception
            com.google.firebase.database.tubesock.WebSocketEventHandler r2 = r1.eventHandler     // Catch:{ all -> 0x014d }
            com.google.firebase.database.tubesock.WebSocketException r3 = new com.google.firebase.database.tubesock.WebSocketException     // Catch:{ all -> 0x014d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x014d }
            r4.<init>()     // Catch:{ all -> 0x014d }
            java.lang.String r5 = "error while connecting: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x014d }
            java.lang.String r5 = r0.getMessage()     // Catch:{ all -> 0x014d }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x014d }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x014d }
            r3.<init>(r4, r0)     // Catch:{ all -> 0x014d }
            r2.onError(r3)     // Catch:{ all -> 0x014d }
            goto L_0x0148
        L_0x0142:
            r0 = move-exception
            com.google.firebase.database.tubesock.WebSocketEventHandler r2 = r1.eventHandler     // Catch:{ all -> 0x014d }
            r2.onError(r0)     // Catch:{ all -> 0x014d }
        L_0x0148:
            r16.close()
            return
        L_0x014d:
            r0 = move-exception
            r16.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.tubesock.WebSocket.runReader():void");
    }

    /* access modifiers changed from: package-private */
    public Thread getInnerThread() {
        return this.innerThread;
    }
}
