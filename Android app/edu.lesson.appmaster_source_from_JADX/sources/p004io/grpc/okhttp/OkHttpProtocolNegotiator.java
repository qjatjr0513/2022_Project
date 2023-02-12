package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import p004io.grpc.internal.GrpcUtil;
import p004io.grpc.okhttp.internal.OptionalMethod;
import p004io.grpc.okhttp.internal.Platform;
import p004io.grpc.okhttp.internal.Protocol;
import p004io.grpc.okhttp.internal.Util;

/* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator */
class OkHttpProtocolNegotiator {
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR;
    /* access modifiers changed from: private */
    public static final Logger logger;
    protected final Platform platform;

    static {
        Class<OkHttpProtocolNegotiator> cls = OkHttpProtocolNegotiator.class;
        logger = Logger.getLogger(cls.getName());
        NEGOTIATOR = createNegotiator(cls.getClassLoader());
    }

    OkHttpProtocolNegotiator(Platform platform2) {
        this.platform = (Platform) Preconditions.checkNotNull(platform2, "platform");
    }

    public static OkHttpProtocolNegotiator get() {
        return NEGOTIATOR;
    }

    static OkHttpProtocolNegotiator createNegotiator(ClassLoader loader) {
        boolean android2 = true;
        try {
            loader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e1) {
            logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", e1);
            try {
                loader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", e2);
                android2 = false;
            }
        }
        if (android2) {
            return new AndroidNegotiator(DEFAULT_PLATFORM);
        }
        return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
    }

    public String negotiate(SSLSocket sslSocket, String hostname, @Nullable List<Protocol> protocols) throws IOException {
        if (protocols != null) {
            configureTlsExtensions(sslSocket, hostname, protocols);
        }
        try {
            sslSocket.startHandshake();
            String negotiatedProtocol = getSelectedProtocol(sslSocket);
            if (negotiatedProtocol != null) {
                return negotiatedProtocol;
            }
            throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + protocols);
        } finally {
            this.platform.afterHandshake(sslSocket);
        }
    }

    /* access modifiers changed from: protected */
    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        this.platform.configureTlsExtensions(sslSocket, hostname, protocols);
    }

    public String getSelectedProtocol(SSLSocket socket) {
        return this.platform.getSelectedProtocol(socket);
    }

    /* renamed from: io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator */
    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOLS;
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL;
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS;
        private static final Method SET_APPLICATION_PROTOCOLS;
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>((Class<?>) null, "setHostname", String.class);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS;
        private static final Method SET_SERVER_NAMES;
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>((Class<?>) null, "setUseSessionTickets", Boolean.TYPE);
        private static final Constructor<?> SNI_HOST_NAME;
        private static final Method SSL_SOCKETS_IS_SUPPORTED_SOCKET;
        private static final Method SSL_SOCKETS_SET_USE_SESSION_TICKET;

        static {
            Class<byte[]> cls = byte[].class;
            GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(cls, "getAlpnSelectedProtocol", new Class[0]);
            SET_ALPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setAlpnProtocols", cls);
            GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(cls, "getNpnSelectedProtocol", new Class[0]);
            SET_NPN_PROTOCOLS = new OptionalMethod<>((Class<?>) null, "setNpnProtocols", cls);
            Method setApplicationProtocolsMethod = null;
            Method getApplicationProtocolsMethod = null;
            Method getApplicationProtocolMethod = null;
            Method sslSocketsIsSupportedSocketMethod = null;
            Method sslSocketsSetUseSessionTicketsMethod = null;
            Class<SSLParameters> cls2 = SSLParameters.class;
            try {
                setApplicationProtocolsMethod = cls2.getMethod("setApplicationProtocols", new Class[]{String[].class});
                getApplicationProtocolsMethod = cls2.getMethod("getApplicationProtocols", new Class[0]);
                getApplicationProtocolMethod = SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                Class<?> sslSockets = Class.forName("android.net.ssl.SSLSockets");
                sslSocketsIsSupportedSocketMethod = sslSockets.getMethod("isSupportedSocket", new Class[]{SSLSocket.class});
                sslSocketsSetUseSessionTicketsMethod = sslSockets.getMethod("setUseSessionTickets", new Class[]{SSLSocket.class, Boolean.TYPE});
            } catch (ClassNotFoundException e) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e);
            } catch (NoSuchMethodException e2) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", e2);
            }
            SET_APPLICATION_PROTOCOLS = setApplicationProtocolsMethod;
            GET_APPLICATION_PROTOCOLS = getApplicationProtocolsMethod;
            GET_APPLICATION_PROTOCOL = getApplicationProtocolMethod;
            SSL_SOCKETS_IS_SUPPORTED_SOCKET = sslSocketsIsSupportedSocketMethod;
            SSL_SOCKETS_SET_USE_SESSION_TICKET = sslSocketsSetUseSessionTicketsMethod;
            Method setServerNamesMethod = null;
            Constructor<?> sniHostNameConstructor = null;
            try {
                setServerNamesMethod = SSLParameters.class.getMethod("setServerNames", new Class[]{List.class});
                sniHostNameConstructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(new Class[]{String.class});
            } catch (ClassNotFoundException e3) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", e3);
            } catch (NoSuchMethodException e4) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", e4);
            }
            SET_SERVER_NAMES = setServerNamesMethod;
            SNI_HOST_NAME = sniHostNameConstructor;
        }

        AndroidNegotiator(Platform platform) {
            super(platform);
        }

        public String negotiate(SSLSocket sslSocket, String hostname, List<Protocol> protocols) throws IOException {
            String negotiatedProtocol = getSelectedProtocol(sslSocket);
            if (negotiatedProtocol == null) {
                return OkHttpProtocolNegotiator.super.negotiate(sslSocket, hostname, protocols);
            }
            return negotiatedProtocol;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object[]} */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e3, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e9, code lost:
            throw new java.lang.RuntimeException(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f1, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f7, code lost:
            throw new java.lang.RuntimeException(r2);
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x004d A[Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3, IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0061 A[Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3, IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00e3 A[ExcHandler: InstantiationException (r2v3 'e' java.lang.InstantiationException A[CUSTOM_DECLARE]), Splitter:B:2:0x000c] */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00f1 A[ExcHandler: IllegalAccessException (r2v1 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:2:0x000c] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void configureTlsExtensions(javax.net.ssl.SSLSocket r11, java.lang.String r12, java.util.List<p004io.grpc.okhttp.internal.Protocol> r13) {
            /*
                r10 = this;
                java.lang.String[] r0 = p004io.grpc.okhttp.OkHttpProtocolNegotiator.protocolIds(r13)
                javax.net.ssl.SSLParameters r1 = r11.getSSLParameters()
                r2 = 1
                r3 = 0
                if (r12 == 0) goto L_0x006a
                boolean r4 = isValidHostName(r12)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r4 == 0) goto L_0x006a
                java.lang.reflect.Method r4 = SSL_SOCKETS_IS_SUPPORTED_SOCKET     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r4 == 0) goto L_0x0038
                java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r5[r3] = r11     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r6 = 0
                java.lang.Object r4 = r4.invoke(r6, r5)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                boolean r4 = r4.booleanValue()     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r4 == 0) goto L_0x0038
                java.lang.reflect.Method r4 = SSL_SOCKETS_SET_USE_SESSION_TICKET     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r5[r3] = r11     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r5[r2] = r7     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r4.invoke(r6, r5)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                goto L_0x0045
            L_0x0038:
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r4 = SET_USE_SESSION_TICKETS     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r5[r3] = r6     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r4.invokeOptionalWithoutCheckedException(r11, r5)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
            L_0x0045:
                java.lang.reflect.Method r4 = SET_SERVER_NAMES     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r4 == 0) goto L_0x0061
                java.lang.reflect.Constructor<?> r5 = SNI_HOST_NAME     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r5 == 0) goto L_0x0061
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r7[r3] = r12     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Object r5 = r5.newInstance(r7)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.util.List r5 = java.util.Collections.singletonList(r5)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r6[r3] = r5     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r4.invoke(r1, r6)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                goto L_0x006a
            L_0x0061:
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r4 = SET_HOSTNAME     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r5[r3] = r12     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                r4.invokeOptionalWithoutCheckedException(r11, r5)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
            L_0x006a:
                r4 = 0
                java.lang.reflect.Method r5 = GET_APPLICATION_PROTOCOL     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r5 == 0) goto L_0x0096
                java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3 }
                r5.invoke(r11, r6)     // Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3 }
                java.lang.reflect.Method r5 = SET_APPLICATION_PROTOCOLS     // Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3 }
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3 }
                r6[r3] = r0     // Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3 }
                r5.invoke(r1, r6)     // Catch:{ InvocationTargetException -> 0x007f, IllegalAccessException -> 0x00f1, InstantiationException -> 0x00e3 }
                r4 = 1
                goto L_0x0096
            L_0x007f:
                r5 = move-exception
                java.lang.Throwable r6 = r5.getTargetException()     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                boolean r7 = r6 instanceof java.lang.UnsupportedOperationException     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r7 == 0) goto L_0x0094
                java.util.logging.Logger r7 = p004io.grpc.okhttp.OkHttpProtocolNegotiator.logger     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.util.logging.Level r8 = java.util.logging.Level.FINER     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.String r9 = "setApplicationProtocol unsupported, will try old methods"
                r7.log(r8, r9)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                goto L_0x0096
            L_0x0094:
                throw r5     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
            L_0x0096:
                r11.setSSLParameters(r1)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r4 == 0) goto L_0x00b3
                java.lang.reflect.Method r5 = GET_APPLICATION_PROTOCOLS     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r5 == 0) goto L_0x00b3
                javax.net.ssl.SSLParameters r6 = r11.getSSLParameters()     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.Object r5 = r5.invoke(r6, r7)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                boolean r6 = java.util.Arrays.equals(r0, r5)     // Catch:{ IllegalAccessException -> 0x00f1, InvocationTargetException -> 0x00ea, InstantiationException -> 0x00e3 }
                if (r6 == 0) goto L_0x00b3
                return
            L_0x00b3:
                java.lang.Object[] r2 = new java.lang.Object[r2]
                byte[] r4 = p004io.grpc.okhttp.internal.Platform.concatLengthPrefixed(r13)
                r2[r3] = r4
                io.grpc.okhttp.internal.Platform r3 = r10.platform
                io.grpc.okhttp.internal.Platform$TlsExtensionType r3 = r3.getTlsExtensionType()
                io.grpc.okhttp.internal.Platform$TlsExtensionType r4 = p004io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
                if (r3 != r4) goto L_0x00cb
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r3 = SET_ALPN_PROTOCOLS
                r3.invokeWithoutCheckedException(r11, r2)
            L_0x00cb:
                io.grpc.okhttp.internal.Platform r3 = r10.platform
                io.grpc.okhttp.internal.Platform$TlsExtensionType r3 = r3.getTlsExtensionType()
                io.grpc.okhttp.internal.Platform$TlsExtensionType r4 = p004io.grpc.okhttp.internal.Platform.TlsExtensionType.NONE
                if (r3 == r4) goto L_0x00db
                io.grpc.okhttp.internal.OptionalMethod<java.net.Socket> r3 = SET_NPN_PROTOCOLS
                r3.invokeWithoutCheckedException(r11, r2)
                return
            L_0x00db:
                java.lang.RuntimeException r3 = new java.lang.RuntimeException
                java.lang.String r4 = "We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS"
                r3.<init>(r4)
                throw r3
            L_0x00e3:
                r2 = move-exception
                java.lang.RuntimeException r3 = new java.lang.RuntimeException
                r3.<init>(r2)
                throw r3
            L_0x00ea:
                r2 = move-exception
                java.lang.RuntimeException r3 = new java.lang.RuntimeException
                r3.<init>(r2)
                throw r3
            L_0x00f1:
                r2 = move-exception
                java.lang.RuntimeException r3 = new java.lang.RuntimeException
                r3.<init>(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.OkHttpProtocolNegotiator.AndroidNegotiator.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List):void");
        }

        public String getSelectedProtocol(SSLSocket socket) {
            Method method = GET_APPLICATION_PROTOCOL;
            if (method != null) {
                try {
                    return (String) method.invoke(socket, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    if (e2.getTargetException() instanceof UnsupportedOperationException) {
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "Socket unsupported for getApplicationProtocol, will try old methods");
                    } else {
                        throw new RuntimeException(e2);
                    }
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                try {
                    byte[] alpnResult = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket, new Object[0]);
                    if (alpnResult != null) {
                        return new String(alpnResult, Util.UTF_8);
                    }
                } catch (Exception e3) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", e3);
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.NONE) {
                return null;
            }
            try {
                byte[] npnResult = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket, new Object[0]);
                if (npnResult != null) {
                    return new String(npnResult, Util.UTF_8);
                }
                return null;
            } catch (Exception e4) {
                OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", e4);
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static String[] protocolIds(List<Protocol> protocols) {
        List<String> result = new ArrayList<>();
        for (Protocol protocol : protocols) {
            result.add(protocol.toString());
        }
        return (String[]) result.toArray(new String[0]);
    }

    static boolean isValidHostName(String name) {
        if (name.contains("_")) {
            return false;
        }
        try {
            GrpcUtil.checkAuthority(name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
