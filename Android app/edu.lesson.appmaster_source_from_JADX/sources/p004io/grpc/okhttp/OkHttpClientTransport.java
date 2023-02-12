package p004io.grpc.okhttp;

import android.support.p005v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.http.StatusLine;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import p004io.grpc.Attributes;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Grpc;
import p004io.grpc.HttpConnectProxiedSocketAddress;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalLogId;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.SecurityLevel;
import p004io.grpc.Status;
import p004io.grpc.StatusException;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.ConnectionClientTransport;
import p004io.grpc.internal.GrpcAttributes;
import p004io.grpc.internal.GrpcUtil;
import p004io.grpc.internal.Http2Ping;
import p004io.grpc.internal.InUseStateAggregator;
import p004io.grpc.internal.KeepAliveManager;
import p004io.grpc.internal.ManagedClientTransport;
import p004io.grpc.internal.SerializingExecutor;
import p004io.grpc.internal.SharedResourceHolder;
import p004io.grpc.internal.StatsTraceContext;
import p004io.grpc.internal.TransportTracer;
import p004io.grpc.okhttp.ExceptionHandlingFrameWriter;
import p004io.grpc.okhttp.OkHttpFrameLogger;
import p004io.grpc.okhttp.internal.ConnectionSpec;
import p004io.grpc.okhttp.internal.framed.ErrorCode;
import p004io.grpc.okhttp.internal.framed.FrameReader;
import p004io.grpc.okhttp.internal.framed.FrameWriter;
import p004io.grpc.okhttp.internal.framed.Header;
import p004io.grpc.okhttp.internal.framed.HeadersMode;
import p004io.grpc.okhttp.internal.framed.Http2;
import p004io.grpc.okhttp.internal.framed.Settings;
import p004io.grpc.okhttp.internal.framed.Variant;
import p004io.perfmark.PerfMark;

/* renamed from: io.grpc.okhttp.OkHttpClientTransport */
class OkHttpClientTransport implements ConnectionClientTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler {
    private static final OkHttpClientStream[] EMPTY_STREAM_ARRAY = new OkHttpClientStream[0];
    private static final Map<ErrorCode, Status> ERROR_CODE_TO_STATUS = buildErrorCodeToStatusMap();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    /* access modifiers changed from: private */
    public final InetSocketAddress address;
    /* access modifiers changed from: private */
    public Attributes attributes;
    /* access modifiers changed from: private */
    public ClientFrameHandler clientFrameHandler;
    SettableFuture<Void> connectedFuture;
    Runnable connectingCallback;
    /* access modifiers changed from: private */
    public final ConnectionSpec connectionSpec;
    /* access modifiers changed from: private */
    public int connectionUnacknowledgedBytesRead;
    private final String defaultAuthority;
    private boolean enableKeepAlive;
    /* access modifiers changed from: private */
    public final Executor executor;
    /* access modifiers changed from: private */
    public ExceptionHandlingFrameWriter frameWriter;
    private boolean goAwaySent;
    /* access modifiers changed from: private */
    public Status goAwayStatus;
    private boolean hasStream;
    /* access modifiers changed from: private */
    public HostnameVerifier hostnameVerifier;
    private final InUseStateAggregator<OkHttpClientStream> inUseState;
    /* access modifiers changed from: private */
    public final int initialWindowSize;
    /* access modifiers changed from: private */
    public KeepAliveManager keepAliveManager;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    /* access modifiers changed from: private */
    public ManagedClientTransport.Listener listener;
    /* access modifiers changed from: private */
    public final Object lock;
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public int maxConcurrentStreams;
    /* access modifiers changed from: private */
    public final int maxInboundMetadataSize;
    private final int maxMessageSize;
    private int nextStreamId;
    /* access modifiers changed from: private */
    public OutboundFlowController outboundFlow;
    private final Deque<OkHttpClientStream> pendingStreams;
    /* access modifiers changed from: private */
    public Http2Ping ping;
    @Nullable
    final HttpConnectProxiedSocketAddress proxiedAddr;
    private final Random random;
    private ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public InternalChannelz.Security securityInfo;
    private final SerializingExecutor serializingExecutor;
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public final SocketFactory socketFactory;
    /* access modifiers changed from: private */
    public SSLSocketFactory sslSocketFactory;
    private boolean stopped;
    private final Supplier<Stopwatch> stopwatchFactory;
    /* access modifiers changed from: private */
    public final Map<Integer, OkHttpClientStream> streams;
    /* access modifiers changed from: private */
    public OkHttpFrameLogger testFrameLogger;
    /* access modifiers changed from: private */
    public FrameReader testFrameReader;
    private FrameWriter testFrameWriter;
    /* access modifiers changed from: private */
    public final Runnable tooManyPingsRunnable;
    private final TransportTracer transportTracer;
    private final boolean useGetForSafeMethods;
    private final String userAgent;

    static /* synthetic */ int access$2412(OkHttpClientTransport x0, int x1) {
        int i = x0.connectionUnacknowledgedBytesRead + x1;
        x0.connectionUnacknowledgedBytesRead = i;
        return i;
    }

    private static Map<ErrorCode, Status> buildErrorCodeToStatusMap() {
        Map<ErrorCode, Status> errorToStatus = new EnumMap<>(ErrorCode.class);
        errorToStatus.put(ErrorCode.NO_ERROR, Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        errorToStatus.put(ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("Protocol error"));
        errorToStatus.put(ErrorCode.INTERNAL_ERROR, Status.INTERNAL.withDescription("Internal error"));
        errorToStatus.put(ErrorCode.FLOW_CONTROL_ERROR, Status.INTERNAL.withDescription("Flow control error"));
        errorToStatus.put(ErrorCode.STREAM_CLOSED, Status.INTERNAL.withDescription("Stream closed"));
        errorToStatus.put(ErrorCode.FRAME_TOO_LARGE, Status.INTERNAL.withDescription("Frame too large"));
        errorToStatus.put(ErrorCode.REFUSED_STREAM, Status.UNAVAILABLE.withDescription("Refused stream"));
        errorToStatus.put(ErrorCode.CANCEL, Status.CANCELLED.withDescription("Cancelled"));
        errorToStatus.put(ErrorCode.COMPRESSION_ERROR, Status.INTERNAL.withDescription("Compression error"));
        errorToStatus.put(ErrorCode.CONNECT_ERROR, Status.INTERNAL.withDescription("Connect error"));
        errorToStatus.put(ErrorCode.ENHANCE_YOUR_CALM, Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        errorToStatus.put(ErrorCode.INADEQUATE_SECURITY, Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        return Collections.unmodifiableMap(errorToStatus);
    }

    OkHttpClientTransport(InetSocketAddress address2, String authority, @Nullable String userAgent2, Attributes eagAttrs, Executor executor2, @Nullable SocketFactory socketFactory2, @Nullable SSLSocketFactory sslSocketFactory2, @Nullable HostnameVerifier hostnameVerifier2, ConnectionSpec connectionSpec2, int maxMessageSize2, int initialWindowSize2, @Nullable HttpConnectProxiedSocketAddress proxiedAddr2, Runnable tooManyPingsRunnable2, int maxInboundMetadataSize2, TransportTracer transportTracer2, boolean useGetForSafeMethods2) {
        Executor executor3 = executor2;
        this.random = new Random();
        this.lock = new Object();
        this.streams = new HashMap();
        this.maxConcurrentStreams = 0;
        this.pendingStreams = new LinkedList();
        this.inUseState = new InUseStateAggregator<OkHttpClientStream>() {
            /* access modifiers changed from: protected */
            public void handleInUse() {
                OkHttpClientTransport.this.listener.transportInUse(true);
            }

            /* access modifiers changed from: protected */
            public void handleNotInUse() {
                OkHttpClientTransport.this.listener.transportInUse(false);
            }
        };
        this.address = (InetSocketAddress) Preconditions.checkNotNull(address2, "address");
        this.defaultAuthority = authority;
        this.maxMessageSize = maxMessageSize2;
        this.initialWindowSize = initialWindowSize2;
        this.executor = (Executor) Preconditions.checkNotNull(executor3, "executor");
        this.serializingExecutor = new SerializingExecutor(executor3);
        this.nextStreamId = 3;
        this.socketFactory = socketFactory2 == null ? SocketFactory.getDefault() : socketFactory2;
        this.sslSocketFactory = sslSocketFactory2;
        this.hostnameVerifier = hostnameVerifier2;
        this.connectionSpec = (ConnectionSpec) Preconditions.checkNotNull(connectionSpec2, "connectionSpec");
        this.stopwatchFactory = GrpcUtil.STOPWATCH_SUPPLIER;
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", userAgent2);
        this.proxiedAddr = proxiedAddr2;
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(tooManyPingsRunnable2, "tooManyPingsRunnable");
        this.maxInboundMetadataSize = maxInboundMetadataSize2;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2);
        this.logId = InternalLogId.allocate(getClass(), address2.toString());
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, eagAttrs).build();
        this.useGetForSafeMethods = useGetForSafeMethods2;
        initTransportTracer();
    }

    OkHttpClientTransport(String userAgent2, Executor executor2, FrameReader frameReader, FrameWriter testFrameWriter2, OkHttpFrameLogger testFrameLogger2, int nextStreamId2, Socket socket2, Supplier<Stopwatch> stopwatchFactory2, @Nullable Runnable connectingCallback2, SettableFuture<Void> connectedFuture2, int maxMessageSize2, int initialWindowSize2, Runnable tooManyPingsRunnable2, TransportTracer transportTracer2) {
        Executor executor3 = executor2;
        this.random = new Random();
        this.lock = new Object();
        this.streams = new HashMap();
        this.maxConcurrentStreams = 0;
        this.pendingStreams = new LinkedList();
        this.inUseState = new InUseStateAggregator<OkHttpClientStream>() {
            /* access modifiers changed from: protected */
            public void handleInUse() {
                OkHttpClientTransport.this.listener.transportInUse(true);
            }

            /* access modifiers changed from: protected */
            public void handleNotInUse() {
                OkHttpClientTransport.this.listener.transportInUse(false);
            }
        };
        this.useGetForSafeMethods = false;
        this.address = null;
        this.maxMessageSize = maxMessageSize2;
        this.initialWindowSize = initialWindowSize2;
        this.defaultAuthority = "notarealauthority:80";
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", userAgent2);
        this.executor = (Executor) Preconditions.checkNotNull(executor3, "executor");
        this.serializingExecutor = new SerializingExecutor(executor3);
        this.socketFactory = SocketFactory.getDefault();
        this.testFrameReader = (FrameReader) Preconditions.checkNotNull(frameReader, "frameReader");
        this.testFrameWriter = (FrameWriter) Preconditions.checkNotNull(testFrameWriter2, "testFrameWriter");
        this.testFrameLogger = (OkHttpFrameLogger) Preconditions.checkNotNull(testFrameLogger2, "testFrameLogger");
        this.socket = (Socket) Preconditions.checkNotNull(socket2, "socket");
        this.nextStreamId = nextStreamId2;
        this.stopwatchFactory = stopwatchFactory2;
        this.connectionSpec = null;
        this.connectingCallback = connectingCallback2;
        this.connectedFuture = (SettableFuture) Preconditions.checkNotNull(connectedFuture2, "connectedFuture");
        this.proxiedAddr = null;
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(tooManyPingsRunnable2, "tooManyPingsRunnable");
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
        this.logId = InternalLogId.allocate(getClass(), String.valueOf(socket2.getInetAddress()));
        initTransportTracer();
    }

    /* access modifiers changed from: package-private */
    public boolean isUsingPlaintext() {
        return this.sslSocketFactory == null;
    }

    private void initTransportTracer() {
        synchronized (this.lock) {
            this.transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader() {
                public TransportTracer.FlowControlWindows read() {
                    TransportTracer.FlowControlWindows flowControlWindows;
                    synchronized (OkHttpClientTransport.this.lock) {
                        flowControlWindows = new TransportTracer.FlowControlWindows(-1, OkHttpClientTransport.this.outboundFlow == null ? -1 : (long) OkHttpClientTransport.this.outboundFlow.windowUpdate((OkHttpClientStream) null, 0));
                    }
                    return flowControlWindows;
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void enableKeepAlive(boolean enable, long keepAliveTimeNanos2, long keepAliveTimeoutNanos2, boolean keepAliveWithoutCalls2) {
        this.enableKeepAlive = enable;
        this.keepAliveTimeNanos = keepAliveTimeNanos2;
        this.keepAliveTimeoutNanos = keepAliveTimeoutNanos2;
        this.keepAliveWithoutCalls = keepAliveWithoutCalls2;
    }

    private boolean isForTest() {
        return this.address == null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3.addCallback(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ping(p004io.grpc.internal.ClientTransport.PingCallback r10, java.util.concurrent.Executor r11) {
        /*
            r9 = this;
            r0 = 0
            java.lang.Object r2 = r9.lock
            monitor-enter(r2)
            io.grpc.okhttp.ExceptionHandlingFrameWriter r3 = r9.frameWriter     // Catch:{ all -> 0x0057 }
            r4 = 0
            if (r3 == 0) goto L_0x000c
            r3 = 1
            goto L_0x000d
        L_0x000c:
            r3 = r4
        L_0x000d:
            com.google.common.base.Preconditions.checkState(r3)     // Catch:{ all -> 0x0057 }
            boolean r3 = r9.stopped     // Catch:{ all -> 0x0057 }
            if (r3 == 0) goto L_0x001d
            java.lang.Throwable r3 = r9.getPingFailure()     // Catch:{ all -> 0x0057 }
            p004io.grpc.internal.Http2Ping.notifyFailed(r10, r11, r3)     // Catch:{ all -> 0x0057 }
            monitor-exit(r2)     // Catch:{ all -> 0x0057 }
            return
        L_0x001d:
            io.grpc.internal.Http2Ping r3 = r9.ping     // Catch:{ all -> 0x0057 }
            if (r3 == 0) goto L_0x0024
            r5 = 0
            goto L_0x0045
        L_0x0024:
            java.util.Random r3 = r9.random     // Catch:{ all -> 0x0057 }
            long r5 = r3.nextLong()     // Catch:{ all -> 0x0057 }
            r0 = r5
            com.google.common.base.Supplier<com.google.common.base.Stopwatch> r3 = r9.stopwatchFactory     // Catch:{ all -> 0x0057 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0057 }
            com.google.common.base.Stopwatch r3 = (com.google.common.base.Stopwatch) r3     // Catch:{ all -> 0x0057 }
            r3.start()     // Catch:{ all -> 0x0057 }
            io.grpc.internal.Http2Ping r5 = new io.grpc.internal.Http2Ping     // Catch:{ all -> 0x0057 }
            r5.<init>(r0, r3)     // Catch:{ all -> 0x0057 }
            r9.ping = r5     // Catch:{ all -> 0x0057 }
            r6 = 1
            io.grpc.internal.TransportTracer r7 = r9.transportTracer     // Catch:{ all -> 0x0057 }
            r7.reportKeepAliveSent()     // Catch:{ all -> 0x0057 }
            r3 = r5
            r5 = r6
        L_0x0045:
            if (r5 == 0) goto L_0x0052
            io.grpc.okhttp.ExceptionHandlingFrameWriter r6 = r9.frameWriter     // Catch:{ all -> 0x0057 }
            r7 = 32
            long r7 = r0 >>> r7
            int r7 = (int) r7     // Catch:{ all -> 0x0057 }
            int r8 = (int) r0     // Catch:{ all -> 0x0057 }
            r6.ping(r4, r7, r8)     // Catch:{ all -> 0x0057 }
        L_0x0052:
            monitor-exit(r2)     // Catch:{ all -> 0x0057 }
            r3.addCallback(r10, r11)
            return
        L_0x0057:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0057 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.OkHttpClientTransport.ping(io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor):void");
    }

    public OkHttpClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) {
        Object obj;
        Metadata metadata = headers;
        Preconditions.checkNotNull(method, FirebaseAnalytics.Param.METHOD);
        Preconditions.checkNotNull(metadata, "headers");
        StatsTraceContext statsTraceContext = StatsTraceContext.newClientContext(tracers, getAttributes(), metadata);
        Object obj2 = this.lock;
        synchronized (obj2) {
            try {
                ExceptionHandlingFrameWriter exceptionHandlingFrameWriter = this.frameWriter;
                OutboundFlowController outboundFlowController = this.outboundFlow;
                Object obj3 = this.lock;
                int i = this.maxMessageSize;
                int i2 = this.initialWindowSize;
                String str = this.defaultAuthority;
                String str2 = this.userAgent;
                TransportTracer transportTracer2 = this.transportTracer;
                obj = obj2;
                OkHttpClientStream okHttpClientStream = new OkHttpClientStream(method, headers, exceptionHandlingFrameWriter, this, outboundFlowController, obj3, i, i2, str, str2, statsTraceContext, transportTracer2, callOptions, this.useGetForSafeMethods);
                return okHttpClientStream;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void streamReadyToStart(OkHttpClientStream clientStream) {
        if (this.goAwayStatus != null) {
            clientStream.transportState().transportReportStatus(this.goAwayStatus, ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
        } else if (this.streams.size() >= this.maxConcurrentStreams) {
            this.pendingStreams.add(clientStream);
            setInUse(clientStream);
        } else {
            startStream(clientStream);
        }
    }

    private void startStream(OkHttpClientStream stream) {
        Preconditions.checkState(stream.mo17338id() == -1, "StreamId already assigned");
        this.streams.put(Integer.valueOf(this.nextStreamId), stream);
        setInUse(stream);
        stream.transportState().start(this.nextStreamId);
        if (!(stream.getType() == MethodDescriptor.MethodType.UNARY || stream.getType() == MethodDescriptor.MethodType.SERVER_STREAMING) || stream.useGet()) {
            this.frameWriter.flush();
        }
        int i = this.nextStreamId;
        if (i >= 2147483645) {
            this.nextStreamId = Integer.MAX_VALUE;
            startGoAway(Integer.MAX_VALUE, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
            return;
        }
        this.nextStreamId = i + 2;
    }

    /* access modifiers changed from: private */
    public boolean startPendingStreams() {
        boolean hasStreamStarted = false;
        while (!this.pendingStreams.isEmpty() && this.streams.size() < this.maxConcurrentStreams) {
            startStream(this.pendingStreams.poll());
            hasStreamStarted = true;
        }
        return hasStreamStarted;
    }

    /* access modifiers changed from: package-private */
    public void removePendingStream(OkHttpClientStream pendingStream) {
        this.pendingStreams.remove(pendingStream);
        maybeClearInUse(pendingStream);
    }

    /* JADX INFO: finally extract failed */
    public Runnable start(ManagedClientTransport.Listener listener2) {
        this.listener = (ManagedClientTransport.Listener) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (this.enableKeepAlive) {
            this.scheduler = (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE);
            KeepAliveManager keepAliveManager2 = new KeepAliveManager(new KeepAliveManager.ClientKeepAlivePinger(this), this.scheduler, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            this.keepAliveManager = keepAliveManager2;
            keepAliveManager2.onTransportStarted();
        }
        if (isForTest()) {
            synchronized (this.lock) {
                this.frameWriter = new ExceptionHandlingFrameWriter(this, this.testFrameWriter, this.testFrameLogger);
                this.outboundFlow = new OutboundFlowController(this, this.frameWriter);
            }
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    if (OkHttpClientTransport.this.connectingCallback != null) {
                        OkHttpClientTransport.this.connectingCallback.run();
                    }
                    OkHttpClientTransport okHttpClientTransport = OkHttpClientTransport.this;
                    OkHttpClientTransport okHttpClientTransport2 = OkHttpClientTransport.this;
                    ClientFrameHandler unused = okHttpClientTransport.clientFrameHandler = new ClientFrameHandler(okHttpClientTransport2.testFrameReader, OkHttpClientTransport.this.testFrameLogger);
                    OkHttpClientTransport.this.executor.execute(OkHttpClientTransport.this.clientFrameHandler);
                    synchronized (OkHttpClientTransport.this.lock) {
                        int unused2 = OkHttpClientTransport.this.maxConcurrentStreams = Integer.MAX_VALUE;
                        boolean unused3 = OkHttpClientTransport.this.startPendingStreams();
                    }
                    OkHttpClientTransport.this.connectedFuture.set(null);
                }
            });
            return null;
        }
        final AsyncSink asyncSink = AsyncSink.sink(this.serializingExecutor, this);
        final Variant variant = new Http2();
        FrameWriter rawFrameWriter = variant.newWriter(Okio.buffer((Sink) asyncSink), true);
        synchronized (this.lock) {
            this.frameWriter = new ExceptionHandlingFrameWriter(this, rawFrameWriter);
            this.outboundFlow = new OutboundFlowController(this, this.frameWriter);
        }
        final CountDownLatch latch = new CountDownLatch(1);
        this.serializingExecutor.execute(new Runnable() {
            public void run() {
                Socket sock;
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                BufferedSource source = Okio.buffer((Source) new Source() {
                    public long read(Buffer sink, long byteCount) {
                        return -1;
                    }

                    public Timeout timeout() {
                        return Timeout.NONE;
                    }

                    public void close() {
                    }
                });
                SSLSession sslSession = null;
                try {
                    if (OkHttpClientTransport.this.proxiedAddr == null) {
                        sock = OkHttpClientTransport.this.socketFactory.createSocket(OkHttpClientTransport.this.address.getAddress(), OkHttpClientTransport.this.address.getPort());
                    } else if (OkHttpClientTransport.this.proxiedAddr.getProxyAddress() instanceof InetSocketAddress) {
                        OkHttpClientTransport okHttpClientTransport = OkHttpClientTransport.this;
                        sock = okHttpClientTransport.createHttpProxySocket(okHttpClientTransport.proxiedAddr.getTargetAddress(), (InetSocketAddress) OkHttpClientTransport.this.proxiedAddr.getProxyAddress(), OkHttpClientTransport.this.proxiedAddr.getUsername(), OkHttpClientTransport.this.proxiedAddr.getPassword());
                    } else {
                        throw Status.INTERNAL.withDescription("Unsupported SocketAddress implementation " + OkHttpClientTransport.this.proxiedAddr.getProxyAddress().getClass()).asException();
                    }
                    if (OkHttpClientTransport.this.sslSocketFactory != null) {
                        SSLSocket sslSocket = OkHttpTlsUpgrader.upgrade(OkHttpClientTransport.this.sslSocketFactory, OkHttpClientTransport.this.hostnameVerifier, sock, OkHttpClientTransport.this.getOverridenHost(), OkHttpClientTransport.this.getOverridenPort(), OkHttpClientTransport.this.connectionSpec);
                        sslSession = sslSocket.getSession();
                        sock = sslSocket;
                    }
                    sock.setTcpNoDelay(true);
                    BufferedSource source2 = Okio.buffer(Okio.source(sock));
                    asyncSink.becomeConnected(Okio.sink(sock), sock);
                    OkHttpClientTransport okHttpClientTransport2 = OkHttpClientTransport.this;
                    Attributes unused = okHttpClientTransport2.attributes = okHttpClientTransport2.attributes.toBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, sock.getRemoteSocketAddress()).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, sock.getLocalSocketAddress()).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, sslSession).set(GrpcAttributes.ATTR_SECURITY_LEVEL, sslSession == null ? SecurityLevel.NONE : SecurityLevel.PRIVACY_AND_INTEGRITY).build();
                    ClientFrameHandler unused2 = OkHttpClientTransport.this.clientFrameHandler = new ClientFrameHandler(OkHttpClientTransport.this, variant.newReader(source2, true));
                    synchronized (OkHttpClientTransport.this.lock) {
                        Socket unused3 = OkHttpClientTransport.this.socket = (Socket) Preconditions.checkNotNull(sock, "socket");
                        if (sslSession != null) {
                            InternalChannelz.Security unused4 = OkHttpClientTransport.this.securityInfo = new InternalChannelz.Security(new InternalChannelz.Tls(sslSession));
                        }
                    }
                } catch (StatusException e2) {
                    OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, e2.getStatus());
                    ClientFrameHandler unused5 = OkHttpClientTransport.this.clientFrameHandler = new ClientFrameHandler(OkHttpClientTransport.this, variant.newReader(source, true));
                } catch (Exception e3) {
                    OkHttpClientTransport.this.onException(e3);
                    ClientFrameHandler unused6 = OkHttpClientTransport.this.clientFrameHandler = new ClientFrameHandler(OkHttpClientTransport.this, variant.newReader(source, true));
                } catch (Throwable th) {
                    ClientFrameHandler unused7 = OkHttpClientTransport.this.clientFrameHandler = new ClientFrameHandler(OkHttpClientTransport.this, variant.newReader(source, true));
                    throw th;
                }
            }
        });
        try {
            sendConnectionPrefaceAndSettings();
            latch.countDown();
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    OkHttpClientTransport.this.executor.execute(OkHttpClientTransport.this.clientFrameHandler);
                    synchronized (OkHttpClientTransport.this.lock) {
                        int unused = OkHttpClientTransport.this.maxConcurrentStreams = Integer.MAX_VALUE;
                        boolean unused2 = OkHttpClientTransport.this.startPendingStreams();
                    }
                }
            });
            return null;
        } catch (Throwable th) {
            latch.countDown();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void sendConnectionPrefaceAndSettings() {
        synchronized (this.lock) {
            this.frameWriter.connectionPreface();
            Settings settings = new Settings();
            OkHttpSettingsUtil.set(settings, 7, this.initialWindowSize);
            this.frameWriter.settings(settings);
            int i = this.initialWindowSize;
            if (i > 65535) {
                this.frameWriter.windowUpdate(0, (long) (i - 65535));
            }
        }
    }

    /* access modifiers changed from: private */
    public Socket createHttpProxySocket(InetSocketAddress address2, InetSocketAddress proxyAddress, String proxyUsername, String proxyPassword) throws StatusException {
        Socket sock;
        try {
            if (proxyAddress.getAddress() != null) {
                sock = this.socketFactory.createSocket(proxyAddress.getAddress(), proxyAddress.getPort());
            } else {
                sock = this.socketFactory.createSocket(proxyAddress.getHostName(), proxyAddress.getPort());
            }
            sock.setTcpNoDelay(true);
            Source source = Okio.source(sock);
            BufferedSink sink = Okio.buffer(Okio.sink(sock));
            try {
                Request proxyRequest = createHttpProxyRequest(address2, proxyUsername, proxyPassword);
                HttpUrl url = proxyRequest.httpUrl();
                sink.writeUtf8(String.format("CONNECT %s:%d HTTP/1.1", new Object[]{url.host(), Integer.valueOf(url.port())})).writeUtf8("\r\n");
                int size = proxyRequest.headers().size();
                for (int i = 0; i < size; i++) {
                    sink.writeUtf8(proxyRequest.headers().name(i)).writeUtf8(": ").writeUtf8(proxyRequest.headers().value(i)).writeUtf8("\r\n");
                }
                sink.writeUtf8("\r\n");
                sink.flush();
                StatusLine statusLine = StatusLine.parse(readUtf8LineStrictUnbuffered(source));
                while (!readUtf8LineStrictUnbuffered(source).equals("")) {
                }
                if (statusLine.code >= 200 && statusLine.code < 300) {
                    return sock;
                }
                Buffer body = new Buffer();
                try {
                    sock.shutdownOutput();
                    source.read(body, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
                } catch (IOException ex) {
                    body.writeUtf8("Unable to read body: " + ex.toString());
                }
                try {
                    sock.close();
                } catch (IOException e) {
                }
                throw Status.UNAVAILABLE.withDescription(String.format("Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s", new Object[]{Integer.valueOf(statusLine.code), statusLine.message, body.readUtf8()})).asException();
            } catch (IOException e2) {
                e = e2;
                throw Status.UNAVAILABLE.withDescription("Failed trying to connect with proxy").withCause(e).asException();
            }
        } catch (IOException e3) {
            e = e3;
            InetSocketAddress inetSocketAddress = address2;
            String str = proxyUsername;
            String str2 = proxyPassword;
            throw Status.UNAVAILABLE.withDescription("Failed trying to connect with proxy").withCause(e).asException();
        }
    }

    private Request createHttpProxyRequest(InetSocketAddress address2, String proxyUsername, String proxyPassword) {
        HttpUrl tunnelUrl = new HttpUrl.Builder().scheme("https").host(address2.getHostName()).port(address2.getPort()).build();
        Request.Builder request = new Request.Builder().url(tunnelUrl).header(HttpHeaders.HOST, tunnelUrl.host() + ":" + tunnelUrl.port()).header(HttpHeaders.USER_AGENT, this.userAgent);
        if (!(proxyUsername == null || proxyPassword == null)) {
            request.header(HttpHeaders.PROXY_AUTHORIZATION, Credentials.basic(proxyUsername, proxyPassword));
        }
        return request.build();
    }

    private static String readUtf8LineStrictUnbuffered(Source source) throws IOException {
        Buffer buffer = new Buffer();
        while (source.read(buffer, 1) != -1) {
            if (buffer.getByte(buffer.size() - 1) == 10) {
                return buffer.readUtf8LineStrict();
            }
        }
        throw new EOFException("\\n not found: " + buffer.readByteString().hex());
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("address", (Object) this.address).toString();
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* access modifiers changed from: package-private */
    public String getOverridenHost() {
        URI uri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (uri.getHost() != null) {
            return uri.getHost();
        }
        return this.defaultAuthority;
    }

    /* access modifiers changed from: package-private */
    public int getOverridenPort() {
        URI uri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (uri.getPort() != -1) {
            return uri.getPort();
        }
        return this.address.getPort();
    }

    public void shutdown(Status reason) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = reason;
                this.listener.transportShutdown(reason);
                stopIfNecessary();
            }
        }
    }

    public void shutdownNow(Status reason) {
        shutdown(reason);
        synchronized (this.lock) {
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, OkHttpClientStream> entry = it.next();
                it.remove();
                entry.getValue().transportState().transportReportStatus(reason, false, new Metadata());
                maybeClearInUse(entry.getValue());
            }
            for (OkHttpClientStream stream : this.pendingStreams) {
                stream.transportState().transportReportStatus(reason, true, new Metadata());
                maybeClearInUse(stream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    /* access modifiers changed from: package-private */
    public OkHttpClientStream[] getActiveStreams() {
        OkHttpClientStream[] okHttpClientStreamArr;
        synchronized (this.lock) {
            okHttpClientStreamArr = (OkHttpClientStream[]) this.streams.values().toArray(EMPTY_STREAM_ARRAY);
        }
        return okHttpClientStreamArr;
    }

    /* access modifiers changed from: package-private */
    public ClientFrameHandler getHandler() {
        return this.clientFrameHandler;
    }

    /* access modifiers changed from: package-private */
    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    /* access modifiers changed from: package-private */
    public int getPendingStreamSize() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    public void onException(Throwable failureCause) {
        Preconditions.checkNotNull(failureCause, "failureCause");
        startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withCause(failureCause));
    }

    /* access modifiers changed from: private */
    public void onError(ErrorCode errorCode, String moreDetail) {
        startGoAway(0, errorCode, toGrpcStatus(errorCode).augmentDescription(moreDetail));
    }

    /* access modifiers changed from: private */
    public void startGoAway(int lastKnownStreamId, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, errorCode, new byte[0]);
            }
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, OkHttpClientStream> entry = it.next();
                if (entry.getKey().intValue() > lastKnownStreamId) {
                    it.remove();
                    entry.getValue().transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, false, new Metadata());
                    maybeClearInUse(entry.getValue());
                }
            }
            for (OkHttpClientStream stream : this.pendingStreams) {
                stream.transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
                maybeClearInUse(stream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    /* access modifiers changed from: package-private */
    public void finishStream(int streamId, @Nullable Status status, ClientStreamListener.RpcProgress rpcProgress, boolean stopDelivery, @Nullable ErrorCode errorCode, @Nullable Metadata trailers) {
        synchronized (this.lock) {
            OkHttpClientStream stream = this.streams.remove(Integer.valueOf(streamId));
            if (stream != null) {
                if (errorCode != null) {
                    this.frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                }
                if (status != null) {
                    stream.transportState().transportReportStatus(status, rpcProgress, stopDelivery, trailers != null ? trailers : new Metadata());
                }
                if (!startPendingStreams()) {
                    stopIfNecessary();
                    maybeClearInUse(stream);
                }
            }
        }
    }

    private void stopIfNecessary() {
        if (this.goAwayStatus != null && this.streams.isEmpty() && this.pendingStreams.isEmpty() && !this.stopped) {
            this.stopped = true;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportTermination();
                this.scheduler = (ScheduledExecutorService) SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.scheduler);
            }
            Http2Ping http2Ping = this.ping;
            if (http2Ping != null) {
                http2Ping.failed(getPingFailure());
                this.ping = null;
            }
            if (!this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, ErrorCode.NO_ERROR, new byte[0]);
            }
            this.frameWriter.close();
        }
    }

    private void maybeClearInUse(OkHttpClientStream stream) {
        if (this.hasStream && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.hasStream = false;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportIdle();
            }
        }
        if (stream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(stream, false);
        }
    }

    private void setInUse(OkHttpClientStream stream) {
        if (!this.hasStream) {
            this.hasStream = true;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportActive();
            }
        }
        if (stream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(stream, true);
        }
    }

    private Throwable getPingFailure() {
        synchronized (this.lock) {
            Status status = this.goAwayStatus;
            if (status != null) {
                StatusException asException = status.asException();
                return asException;
            }
            StatusException asException2 = Status.UNAVAILABLE.withDescription("Connection closed").asException();
            return asException2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean mayHaveCreatedStream(int streamId) {
        boolean z;
        synchronized (this.lock) {
            z = true;
            if (streamId >= this.nextStreamId || (streamId & 1) != 1) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public OkHttpClientStream getStream(int streamId) {
        OkHttpClientStream okHttpClientStream;
        synchronized (this.lock) {
            okHttpClientStream = this.streams.get(Integer.valueOf(streamId));
        }
        return okHttpClientStream;
    }

    static Status toGrpcStatus(ErrorCode code) {
        Status status = ERROR_CODE_TO_STATUS.get(code);
        return status != null ? status : Status.UNKNOWN.withDescription("Unknown http2 error code: " + code.httpCode);
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture<InternalChannelz.SocketStats> ret = SettableFuture.create();
        synchronized (this.lock) {
            if (this.socket == null) {
                ret.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), (SocketAddress) null, (SocketAddress) null, new InternalChannelz.SocketOptions.Builder().build(), (InternalChannelz.Security) null));
            } else {
                ret.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), this.socket.getLocalSocketAddress(), this.socket.getRemoteSocketAddress(), Utils.getSocketOptions(this.socket), this.securityInfo));
            }
        }
        return ret;
    }

    /* renamed from: io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler */
    class ClientFrameHandler implements FrameReader.Handler, Runnable {
        boolean firstSettings;
        FrameReader frameReader;
        private final OkHttpFrameLogger logger;

        ClientFrameHandler(OkHttpClientTransport this$02, FrameReader frameReader2) {
            this(frameReader2, new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpClientTransport.class));
        }

        ClientFrameHandler(FrameReader frameReader2, OkHttpFrameLogger frameLogger) {
            this.firstSettings = true;
            this.frameReader = frameReader2;
            this.logger = frameLogger;
        }

        public void run() {
            String str;
            Status status;
            String threadName = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpClientTransport");
            while (this.frameReader.nextFrame(this)) {
                try {
                    if (OkHttpClientTransport.this.keepAliveManager != null) {
                        OkHttpClientTransport.this.keepAliveManager.onDataReceived();
                    }
                } catch (Throwable t) {
                    try {
                        OkHttpClientTransport.this.startGoAway(0, ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("error in frame handler").withCause(t));
                    } finally {
                        try {
                            this.frameReader.close();
                        } catch (IOException ex) {
                            str = "Exception closing frame reader";
                            OkHttpClientTransport.log.log(Level.INFO, str, ex);
                        }
                        OkHttpClientTransport.this.listener.transportTerminated();
                        Thread.currentThread().setName(threadName);
                    }
                }
            }
            synchronized (OkHttpClientTransport.this.lock) {
                status = OkHttpClientTransport.this.goAwayStatus;
            }
            if (status == null) {
                status = Status.UNAVAILABLE.withDescription("End of stream or IOException");
            }
            OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, status);
            try {
                this.frameReader.close();
            } catch (IOException e) {
                ex = e;
            }
        }

        public void data(boolean inFinished, int streamId, BufferedSource in, int length) throws IOException {
            this.logger.logData(OkHttpFrameLogger.Direction.INBOUND, streamId, in.getBuffer(), length, inFinished);
            OkHttpClientStream stream = OkHttpClientTransport.this.getStream(streamId);
            if (stream != null) {
                in.require((long) length);
                Buffer buf = new Buffer();
                buf.write(in.getBuffer(), (long) length);
                PerfMark.event("OkHttpClientTransport$ClientFrameHandler.data", stream.transportState().tag());
                synchronized (OkHttpClientTransport.this.lock) {
                    stream.transportState().transportDataReceived(buf, inFinished);
                }
            } else if (OkHttpClientTransport.this.mayHaveCreatedStream(streamId)) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.INVALID_STREAM);
                }
                in.skip((long) length);
            } else {
                OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received data for unknown stream: " + streamId);
                return;
            }
            OkHttpClientTransport.access$2412(OkHttpClientTransport.this, length);
            if (((float) OkHttpClientTransport.this.connectionUnacknowledgedBytesRead) >= ((float) OkHttpClientTransport.this.initialWindowSize) * 0.5f) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.windowUpdate(0, (long) OkHttpClientTransport.this.connectionUnacknowledgedBytesRead);
                }
                int unused = OkHttpClientTransport.this.connectionUnacknowledgedBytesRead = 0;
            }
        }

        public void headers(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock, HeadersMode headersMode) {
            int metadataSize;
            this.logger.logHeaders(OkHttpFrameLogger.Direction.INBOUND, streamId, headerBlock, inFinished);
            boolean unknownStream = false;
            Status failedStatus = null;
            if (OkHttpClientTransport.this.maxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = headerBlockSize(headerBlock)) > OkHttpClientTransport.this.maxInboundMetadataSize) {
                Status status = Status.RESOURCE_EXHAUSTED;
                Object[] objArr = new Object[3];
                objArr[0] = inFinished ? "trailer" : "header";
                objArr[1] = Integer.valueOf(OkHttpClientTransport.this.maxInboundMetadataSize);
                objArr[2] = Integer.valueOf(metadataSize);
                failedStatus = status.withDescription(String.format("Response %s metadata larger than %d: %d", objArr));
            }
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientStream stream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream == null) {
                    if (OkHttpClientTransport.this.mayHaveCreatedStream(streamId)) {
                        OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.INVALID_STREAM);
                    } else {
                        unknownStream = true;
                    }
                } else if (failedStatus == null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.headers", stream.transportState().tag());
                    stream.transportState().transportHeadersReceived(headerBlock, inFinished);
                } else {
                    if (!inFinished) {
                        OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                    }
                    stream.transportState().transportReportStatus(failedStatus, false, new Metadata());
                }
            }
            if (unknownStream) {
                OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received header for unknown stream: " + streamId);
            }
        }

        private int headerBlockSize(List<Header> headerBlock) {
            long size = 0;
            for (int i = 0; i < headerBlock.size(); i++) {
                Header header = headerBlock.get(i);
                size += (long) (header.name.size() + 32 + header.value.size());
            }
            return (int) Math.min(size, 2147483647L);
        }

        public void rstStream(int streamId, ErrorCode errorCode) {
            this.logger.logRstStream(OkHttpFrameLogger.Direction.INBOUND, streamId, errorCode);
            Status status = OkHttpClientTransport.toGrpcStatus(errorCode).augmentDescription("Rst Stream");
            boolean stopDelivery = status.getCode() == Status.Code.CANCELLED || status.getCode() == Status.Code.DEADLINE_EXCEEDED;
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientStream stream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream != null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.rstStream", stream.transportState().tag());
                    OkHttpClientTransport.this.finishStream(streamId, status, errorCode == ErrorCode.REFUSED_STREAM ? ClientStreamListener.RpcProgress.REFUSED : ClientStreamListener.RpcProgress.PROCESSED, stopDelivery, (ErrorCode) null, (Metadata) null);
                }
            }
        }

        public void settings(boolean clearPrevious, Settings settings) {
            this.logger.logSettings(OkHttpFrameLogger.Direction.INBOUND, settings);
            boolean outboundWindowSizeIncreased = false;
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpSettingsUtil.isSet(settings, 4)) {
                    int unused = OkHttpClientTransport.this.maxConcurrentStreams = OkHttpSettingsUtil.get(settings, 4);
                }
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    outboundWindowSizeIncreased = OkHttpClientTransport.this.outboundFlow.initialOutboundWindowSize(OkHttpSettingsUtil.get(settings, 7));
                }
                if (this.firstSettings != 0) {
                    OkHttpClientTransport.this.listener.transportReady();
                    this.firstSettings = false;
                }
                OkHttpClientTransport.this.frameWriter.ackSettings(settings);
                if (outboundWindowSizeIncreased) {
                    OkHttpClientTransport.this.outboundFlow.writeStreams();
                }
                boolean unused2 = OkHttpClientTransport.this.startPendingStreams();
            }
        }

        public void ping(boolean ack, int payload1, int payload2) {
            long ackPayload = (((long) payload1) << 32) | (((long) payload2) & 4294967295L);
            this.logger.logPing(OkHttpFrameLogger.Direction.INBOUND, ackPayload);
            if (!ack) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.ping(true, payload1, payload2);
                }
                return;
            }
            Http2Ping p = null;
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpClientTransport.this.ping == null) {
                    OkHttpClientTransport.log.warning("Received unexpected ping ack. No ping outstanding");
                } else if (OkHttpClientTransport.this.ping.payload() == ackPayload) {
                    p = OkHttpClientTransport.this.ping;
                    Http2Ping unused = OkHttpClientTransport.this.ping = null;
                } else {
                    OkHttpClientTransport.log.log(Level.WARNING, String.format("Received unexpected ping ack. Expecting %d, got %d", new Object[]{Long.valueOf(OkHttpClientTransport.this.ping.payload()), Long.valueOf(ackPayload)}));
                }
            }
            if (p != null) {
                p.complete();
            }
        }

        public void ackSettings() {
        }

        public void goAway(int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
            this.logger.logGoAway(OkHttpFrameLogger.Direction.INBOUND, lastGoodStreamId, errorCode, debugData);
            if (errorCode == ErrorCode.ENHANCE_YOUR_CALM) {
                String data = debugData.utf8();
                OkHttpClientTransport.log.log(Level.WARNING, String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", new Object[]{this, data}));
                if ("too_many_pings".equals(data)) {
                    OkHttpClientTransport.this.tooManyPingsRunnable.run();
                }
            }
            Status status = GrpcUtil.Http2Error.statusForCode((long) errorCode.httpCode).augmentDescription("Received Goaway");
            if (debugData.size() > 0) {
                status = status.augmentDescription(debugData.utf8());
            }
            OkHttpClientTransport.this.startGoAway(lastGoodStreamId, (ErrorCode) null, status);
        }

        public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
            this.logger.logPushPromise(OkHttpFrameLogger.Direction.INBOUND, streamId, promisedStreamId, requestHeaders);
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.PROTOCOL_ERROR);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
            if (r0 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
            p004io.grpc.okhttp.OkHttpClientTransport.access$2300(r10.this$0, p004io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Received window_update for unknown stream: " + r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void windowUpdate(int r11, long r12) {
            /*
                r10 = this;
                io.grpc.okhttp.OkHttpFrameLogger r0 = r10.logger
                io.grpc.okhttp.OkHttpFrameLogger$Direction r1 = p004io.grpc.okhttp.OkHttpFrameLogger.Direction.INBOUND
                r0.logWindowsUpdate(r1, r11, r12)
                r0 = 0
                int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x002c
                java.lang.String r0 = "Received 0 flow control window increment."
                if (r11 != 0) goto L_0x0019
                io.grpc.okhttp.OkHttpClientTransport r1 = p004io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r2 = p004io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r1.onError(r2, r0)
                goto L_0x002b
            L_0x0019:
                io.grpc.okhttp.OkHttpClientTransport r3 = p004io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.Status r1 = p004io.grpc.Status.INTERNAL
                io.grpc.Status r5 = r1.withDescription(r0)
                io.grpc.internal.ClientStreamListener$RpcProgress r6 = p004io.grpc.internal.ClientStreamListener.RpcProgress.PROCESSED
                r7 = 0
                io.grpc.okhttp.internal.framed.ErrorCode r8 = p004io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r9 = 0
                r4 = r11
                r3.finishStream(r4, r5, r6, r7, r8, r9)
            L_0x002b:
                return
            L_0x002c:
                r0 = 0
                io.grpc.okhttp.OkHttpClientTransport r1 = p004io.grpc.okhttp.OkHttpClientTransport.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                if (r11 != 0) goto L_0x0043
                io.grpc.okhttp.OkHttpClientTransport r2 = p004io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x0087 }
                io.grpc.okhttp.OutboundFlowController r2 = r2.outboundFlow     // Catch:{ all -> 0x0087 }
                r3 = 0
                int r4 = (int) r12     // Catch:{ all -> 0x0087 }
                r2.windowUpdate(r3, r4)     // Catch:{ all -> 0x0087 }
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                return
            L_0x0043:
                io.grpc.okhttp.OkHttpClientTransport r2 = p004io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x0087 }
                java.util.Map r2 = r2.streams     // Catch:{ all -> 0x0087 }
                java.lang.Integer r3 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0087 }
                java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x0087 }
                io.grpc.okhttp.OkHttpClientStream r2 = (p004io.grpc.okhttp.OkHttpClientStream) r2     // Catch:{ all -> 0x0087 }
                if (r2 == 0) goto L_0x0060
                io.grpc.okhttp.OkHttpClientTransport r3 = p004io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x0087 }
                io.grpc.okhttp.OutboundFlowController r3 = r3.outboundFlow     // Catch:{ all -> 0x0087 }
                int r4 = (int) r12     // Catch:{ all -> 0x0087 }
                r3.windowUpdate(r2, r4)     // Catch:{ all -> 0x0087 }
                goto L_0x0069
            L_0x0060:
                io.grpc.okhttp.OkHttpClientTransport r3 = p004io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x0087 }
                boolean r3 = r3.mayHaveCreatedStream(r11)     // Catch:{ all -> 0x0087 }
                if (r3 != 0) goto L_0x0069
                r0 = 1
            L_0x0069:
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                if (r0 == 0) goto L_0x0086
                io.grpc.okhttp.OkHttpClientTransport r1 = p004io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r2 = p004io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Received window_update for unknown stream: "
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.StringBuilder r3 = r3.append(r11)
                java.lang.String r3 = r3.toString()
                r1.onError(r2, r3)
            L_0x0086:
                return
            L_0x0087:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.OkHttpClientTransport.ClientFrameHandler.windowUpdate(int, long):void");
        }

        public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
        }

        public void alternateService(int streamId, String origin, ByteString protocol, String host, int port, long maxAge) {
        }
    }
}
