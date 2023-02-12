package com.squareup.okhttp.internal;

import android.util.Log;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.tls.AndroidTrustRootIndex;
import com.squareup.okhttp.internal.tls.RealTrustRootIndex;
import com.squareup.okhttp.internal.tls.TrustRootIndex;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okio.Buffer;

public class Platform {
    private static final Platform PLATFORM = findPlatform();

    public static Platform get() {
        return PLATFORM;
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public void logW(String warning) {
        System.out.println(warning);
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        return null;
    }

    public TrustRootIndex trustRootIndex(X509TrustManager trustManager) {
        return new RealTrustRootIndex(trustManager.getAcceptedIssuers());
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> list) {
    }

    public void afterHandshake(SSLSocket sslSocket) {
    }

    public String getSelectedProtocol(SSLSocket socket) {
        return null;
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        socket.connect(address, connectTimeout);
    }

    public void log(String message) {
        System.out.println(message);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0078, code lost:
        r0 = r4;
        r12 = r5;
        r13 = r9;
        r14 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0075 A[ExcHandler: NoSuchMethodException (e java.lang.NoSuchMethodException), PHI: r4 r5 r9 
      PHI: (r4v13 'trafficStatsTagSocket' java.lang.reflect.Method) = (r4v10 'trafficStatsTagSocket' java.lang.reflect.Method), (r4v14 'trafficStatsTagSocket' java.lang.reflect.Method), (r4v14 'trafficStatsTagSocket' java.lang.reflect.Method) binds: [B:9:0x0034, B:12:0x0054, B:13:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r5v7 'trafficStatsUntagSocket' java.lang.reflect.Method) = (r5v4 'trafficStatsUntagSocket' java.lang.reflect.Method), (r5v8 'trafficStatsUntagSocket' java.lang.reflect.Method), (r5v8 'trafficStatsUntagSocket' java.lang.reflect.Method) binds: [B:9:0x0034, B:12:0x0054, B:13:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v5 'getAlpnSelectedProtocol' com.squareup.okhttp.internal.OptionalMethod<java.net.Socket>) = (r9v2 'getAlpnSelectedProtocol' com.squareup.okhttp.internal.OptionalMethod<java.net.Socket>), (r9v2 'getAlpnSelectedProtocol' com.squareup.okhttp.internal.OptionalMethod<java.net.Socket>), (r9v8 'getAlpnSelectedProtocol' com.squareup.okhttp.internal.OptionalMethod<java.net.Socket>) binds: [B:9:0x0034, B:12:0x0054, B:13:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0034] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.squareup.okhttp.internal.Platform findPlatform() {
        /*
            java.lang.Class<byte[]> r0 = byte[].class
            r1 = 1
            r2 = 0
            java.lang.String r3 = "com.android.org.conscrypt.SSLParametersImpl"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x000b }
            goto L_0x0013
        L_0x000b:
            r3 = move-exception
            java.lang.String r4 = "org.apache.harmony.xnet.provider.jsse.SSLParametersImpl"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0088 }
            r3 = r4
        L_0x0013:
            com.squareup.okhttp.internal.OptionalMethod r6 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException -> 0x0088 }
            java.lang.String r4 = "setUseSessionTickets"
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x0088 }
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x0088 }
            r5[r2] = r7     // Catch:{ ClassNotFoundException -> 0x0088 }
            r8 = 0
            r6.<init>(r8, r4, r5)     // Catch:{ ClassNotFoundException -> 0x0088 }
            com.squareup.okhttp.internal.OptionalMethod r7 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException -> 0x0088 }
            java.lang.String r4 = "setHostname"
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x0088 }
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            r5[r2] = r9     // Catch:{ ClassNotFoundException -> 0x0088 }
            r7.<init>(r8, r4, r5)     // Catch:{ ClassNotFoundException -> 0x0088 }
            r4 = 0
            r5 = 0
            r9 = 0
            r10 = 0
            java.lang.String r11 = "android.net.TrafficStats"
            java.lang.Class r11 = java.lang.Class.forName(r11)     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            java.lang.String r12 = "tagSocket"
            java.lang.Class[] r13 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            java.lang.Class<java.net.Socket> r14 = java.net.Socket.class
            r13[r2] = r14     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            java.lang.reflect.Method r12 = r11.getMethod(r12, r13)     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            r4 = r12
            java.lang.String r12 = "untagSocket"
            java.lang.Class[] r13 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            java.lang.Class<java.net.Socket> r14 = java.net.Socket.class
            r13[r2] = r14     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            java.lang.reflect.Method r12 = r11.getMethod(r12, r13)     // Catch:{ ClassNotFoundException -> 0x0077, NoSuchMethodException -> 0x0075 }
            r5 = r12
            java.lang.String r12 = "android.net.Network"
            java.lang.Class.forName(r12)     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            com.squareup.okhttp.internal.OptionalMethod r12 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            java.lang.String r13 = "getAlpnSelectedProtocol"
            java.lang.Class[] r14 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            r12.<init>(r0, r13, r14)     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            r9 = r12
            com.squareup.okhttp.internal.OptionalMethod r12 = new com.squareup.okhttp.internal.OptionalMethod     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            java.lang.String r13 = "setAlpnProtocols"
            java.lang.Class[] r14 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            r14[r2] = r0     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            r12.<init>(r8, r13, r14)     // Catch:{ ClassNotFoundException -> 0x006f, NoSuchMethodException -> 0x0075 }
            r0 = r12
            r10 = r0
            goto L_0x0070
        L_0x006f:
            r0 = move-exception
        L_0x0070:
            r0 = r4
            r12 = r5
            r13 = r9
            r14 = r10
            goto L_0x007c
        L_0x0075:
            r0 = move-exception
            goto L_0x0078
        L_0x0077:
            r0 = move-exception
        L_0x0078:
            r0 = r4
            r12 = r5
            r13 = r9
            r14 = r10
        L_0x007c:
            com.squareup.okhttp.internal.Platform$Android r15 = new com.squareup.okhttp.internal.Platform$Android     // Catch:{ ClassNotFoundException -> 0x0088 }
            r4 = r15
            r5 = r3
            r8 = r0
            r9 = r12
            r10 = r13
            r11 = r14
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ ClassNotFoundException -> 0x0088 }
            return r15
        L_0x0088:
            r0 = move-exception
            java.lang.String r0 = "sun.security.ssl.SSLContextImpl"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0115 }
            java.lang.String r3 = "org.eclipse.jetty.alpn.ALPN"
            r10 = r3
            java.lang.Class r3 = java.lang.Class.forName(r10)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r11 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r3.<init>()     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.StringBuilder r3 = r3.append(r10)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r4 = "$Provider"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r3 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r12 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r3.<init>()     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.StringBuilder r3 = r3.append(r10)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r4 = "$ClientProvider"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r3 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.Class r8 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r3.<init>()     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.StringBuilder r3 = r3.append(r10)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r4 = "$ServerProvider"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r3 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.Class r9 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r3 = "put"
            r4 = 2
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.Class<javax.net.ssl.SSLSocket> r5 = javax.net.ssl.SSLSocket.class
            r4[r2] = r5     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r4[r1] = r12     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.reflect.Method r5 = r11.getMethod(r3, r4)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r3 = "get"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.Class<javax.net.ssl.SSLSocket> r6 = javax.net.ssl.SSLSocket.class
            r4[r2] = r6     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.reflect.Method r6 = r11.getMethod(r3, r4)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.String r3 = "remove"
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.Class<javax.net.ssl.SSLSocket> r4 = javax.net.ssl.SSLSocket.class
            r1[r2] = r4     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            java.lang.reflect.Method r7 = r11.getMethod(r3, r1)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            com.squareup.okhttp.internal.Platform$JdkWithJettyBootPlatform r1 = new com.squareup.okhttp.internal.Platform$JdkWithJettyBootPlatform     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            r3 = r1
            r4 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ ClassNotFoundException -> 0x010e, NoSuchMethodException -> 0x010c }
            return r1
        L_0x010c:
            r1 = move-exception
            goto L_0x010f
        L_0x010e:
            r1 = move-exception
        L_0x010f:
            com.squareup.okhttp.internal.Platform$JdkPlatform r1 = new com.squareup.okhttp.internal.Platform$JdkPlatform     // Catch:{ ClassNotFoundException -> 0x0115 }
            r1.<init>(r0)     // Catch:{ ClassNotFoundException -> 0x0115 }
            return r1
        L_0x0115:
            r0 = move-exception
            com.squareup.okhttp.internal.Platform r0 = new com.squareup.okhttp.internal.Platform
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.Platform.findPlatform():com.squareup.okhttp.internal.Platform");
    }

    private static class Android extends Platform {
        private static final int MAX_LOG_LENGTH = 4000;
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final Class<?> sslParametersClass;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(Class<?> sslParametersClass2, OptionalMethod<Socket> setUseSessionTickets2, OptionalMethod<Socket> setHostname2, Method trafficStatsTagSocket2, Method trafficStatsUntagSocket2, OptionalMethod<Socket> getAlpnSelectedProtocol2, OptionalMethod<Socket> setAlpnProtocols2) {
            this.sslParametersClass = sslParametersClass2;
            this.setUseSessionTickets = setUseSessionTickets2;
            this.setHostname = setHostname2;
            this.trafficStatsTagSocket = trafficStatsTagSocket2;
            this.trafficStatsUntagSocket = trafficStatsUntagSocket2;
            this.getAlpnSelectedProtocol = getAlpnSelectedProtocol2;
            this.setAlpnProtocols = setAlpnProtocols2;
        }

        public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
            try {
                socket.connect(address, connectTimeout);
            } catch (AssertionError e) {
                if (Util.isAndroidGetsocknameError(e)) {
                    throw new IOException(e);
                }
                throw e;
            } catch (SecurityException e2) {
                IOException ioException = new IOException("Exception in connect");
                ioException.initCause(e2);
                throw ioException;
            }
        }

        public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
            Object context = readFieldOrNull(sslSocketFactory, this.sslParametersClass, "sslParameters");
            if (context == null) {
                try {
                    context = readFieldOrNull(sslSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sslSocketFactory.getClass().getClassLoader()), "sslParameters");
                } catch (ClassNotFoundException e) {
                    return null;
                }
            }
            X509TrustManager x509TrustManager = (X509TrustManager) readFieldOrNull(context, X509TrustManager.class, "x509TrustManager");
            if (x509TrustManager != null) {
                return x509TrustManager;
            }
            return (X509TrustManager) readFieldOrNull(context, X509TrustManager.class, "trustManager");
        }

        public TrustRootIndex trustRootIndex(X509TrustManager trustManager) {
            TrustRootIndex result = AndroidTrustRootIndex.get(trustManager);
            if (result != null) {
                return result;
            }
            return Platform.super.trustRootIndex(trustManager);
        }

        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            if (hostname != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, hostname);
            }
            OptionalMethod<Socket> optionalMethod = this.setAlpnProtocols;
            if (optionalMethod != null && optionalMethod.isSupported(sslSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, concatLengthPrefixed(protocols));
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            byte[] alpnResult;
            OptionalMethod<Socket> optionalMethod = this.getAlpnSelectedProtocol;
            if (optionalMethod == null || !optionalMethod.isSupported(socket) || (alpnResult = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(socket, new Object[0])) == null) {
                return null;
            }
            return new String(alpnResult, Util.UTF_8);
        }

        public void tagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsTagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void untagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsUntagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void log(String message) {
            int i = 0;
            int length = message.length();
            while (i < length) {
                int newline = message.indexOf(10, i);
                int newline2 = newline != -1 ? newline : length;
                do {
                    int end = Math.min(newline2, i + MAX_LOG_LENGTH);
                    Log.d("OkHttp", message.substring(i, end));
                    i = end;
                } while (i < newline2);
                i++;
            }
        }
    }

    private static class JdkPlatform extends Platform {
        private final Class<?> sslContextClass;

        public JdkPlatform(Class<?> sslContextClass2) {
            this.sslContextClass = sslContextClass2;
        }

        public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
            Object context = readFieldOrNull(sslSocketFactory, this.sslContextClass, "context");
            if (context == null) {
                return null;
            }
            return (X509TrustManager) readFieldOrNull(context, X509TrustManager.class, "trustManager");
        }
    }

    private static class JdkWithJettyBootPlatform extends JdkPlatform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Class<?> sslContextClass, Method putMethod2, Method getMethod2, Method removeMethod2, Class<?> clientProviderClass2, Class<?> serverProviderClass2) {
            super(sslContextClass);
            this.putMethod = putMethod2;
            this.getMethod = getMethod2;
            this.removeMethod = removeMethod2;
            this.clientProviderClass = clientProviderClass2;
            this.serverProviderClass = serverProviderClass2;
        }

        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            List<String> names = new ArrayList<>(protocols.size());
            int size = protocols.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = protocols.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    names.add(protocol.toString());
                }
            }
            try {
                Object provider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(names));
                this.putMethod.invoke((Object) null, new Object[]{sslSocket, provider});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError(e);
            }
        }

        public void afterHandshake(SSLSocket sslSocket) {
            try {
                this.removeMethod.invoke((Object) null, new Object[]{sslSocket});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError();
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            try {
                JettyNegoProvider provider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke((Object) null, new Object[]{socket}));
                if (!provider.unsupported && provider.selected == null) {
                    Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (provider.unsupported) {
                    return null;
                } else {
                    return provider.selected;
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        /* access modifiers changed from: private */
        public String selected;
        /* access modifiers changed from: private */
        public boolean unsupported;

        public JettyNegoProvider(List<String> protocols2) {
            this.protocols = protocols2;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if (args == null) {
                args = Util.EMPTY_STRING_ARRAY;
            }
            if (methodName.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (methodName.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (methodName.equals("protocols") && args.length == 0) {
                return this.protocols;
            } else {
                if ((methodName.equals("selectProtocol") || methodName.equals("select")) && String.class == returnType && args.length == 1 && (args[0] instanceof List)) {
                    List<String> peerProtocols = (List) args[0];
                    int size = peerProtocols.size();
                    for (int i = 0; i < size; i++) {
                        if (this.protocols.contains(peerProtocols.get(i))) {
                            String str = peerProtocols.get(i);
                            this.selected = str;
                            return str;
                        }
                    }
                    String str2 = this.protocols.get(0);
                    this.selected = str2;
                    return str2;
                } else if ((!methodName.equals("protocolSelected") && !methodName.equals("selected")) || args.length != 1) {
                    return method.invoke(this, args);
                } else {
                    this.selected = (String) args[0];
                    return null;
                }
            }
        }
    }

    static byte[] concatLengthPrefixed(List<Protocol> protocols) {
        Buffer result = new Buffer();
        int size = protocols.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = protocols.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                result.writeByte(protocol.toString().length());
                result.writeUtf8(protocol.toString());
            }
        }
        return result.readByteArray();
    }

    static <T> T readFieldOrNull(Object instance, Class<T> fieldType, String fieldName) {
        Object delegate;
        Class cls = instance.getClass();
        while (cls != Object.class) {
            try {
                Field field = cls.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(instance);
                if (value != null) {
                    if (fieldType.isInstance(value)) {
                        return fieldType.cast(value);
                    }
                }
                return null;
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            }
        }
        if (fieldName.equals("delegate") || (delegate = readFieldOrNull(instance, Object.class, "delegate")) == null) {
            return null;
        }
        return readFieldOrNull(delegate, fieldType, fieldName);
    }
}
