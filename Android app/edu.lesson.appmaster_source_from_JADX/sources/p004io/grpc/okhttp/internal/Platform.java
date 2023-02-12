package p004io.grpc.okhttp.internal;

import com.google.android.gms.security.ProviderInstaller;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import okio.Buffer;

/* renamed from: io.grpc.okhttp.internal.Platform */
public class Platform {
    private static final String[] ANDROID_SECURITY_PROVIDERS = {"com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider", "com.google.android.libraries.stitch.sslguard.SslGuardProvider"};
    private static final Platform PLATFORM = findPlatform();
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    private final Provider sslProvider;

    /* renamed from: io.grpc.okhttp.internal.Platform$TlsExtensionType */
    public enum TlsExtensionType {
        ALPN_AND_NPN,
        NPN,
        NONE
    }

    public static Platform get() {
        return PLATFORM;
    }

    public Platform(Provider sslProvider2) {
        this.sslProvider = sslProvider2;
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

    public Provider getProvider() {
        return this.sslProvider;
    }

    public TlsExtensionType getTlsExtensionType() {
        return TlsExtensionType.NONE;
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

    private static Platform findPlatform() {
        Method trafficStatsUntagSocket;
        Method trafficStatsTagSocket;
        TlsExtensionType tlsExtensionType;
        Class<byte[]> cls = byte[].class;
        Provider androidOrAppEngineProvider = getAndroidSecurityProvider();
        if (androidOrAppEngineProvider != null) {
            OptionalMethod<Socket> setUseSessionTickets = new OptionalMethod<>((Class<?>) null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod<Socket> setHostname = new OptionalMethod<>((Class<?>) null, "setHostname", String.class);
            Method trafficStatsTagSocket2 = null;
            Method trafficStatsUntagSocket2 = null;
            OptionalMethod<Socket> getAlpnSelectedProtocol = new OptionalMethod<>(cls, "getAlpnSelectedProtocol", new Class[0]);
            OptionalMethod<Socket> setAlpnProtocols = new OptionalMethod<>((Class<?>) null, "setAlpnProtocols", cls);
            try {
                Class<?> trafficStats = Class.forName("android.net.TrafficStats");
                trafficStatsTagSocket2 = trafficStats.getMethod("tagSocket", new Class[]{Socket.class});
                trafficStatsUntagSocket2 = trafficStats.getMethod("untagSocket", new Class[]{Socket.class});
            } catch (ClassNotFoundException e) {
            } catch (NoSuchMethodException e2) {
                trafficStatsTagSocket = null;
                trafficStatsUntagSocket = null;
            }
            trafficStatsTagSocket = trafficStatsTagSocket2;
            trafficStatsUntagSocket = trafficStatsUntagSocket2;
            if (androidOrAppEngineProvider.getName().equals(ProviderInstaller.PROVIDER_NAME) || androidOrAppEngineProvider.getName().equals("Conscrypt") || androidOrAppEngineProvider.getName().equals("Ssl_Guard")) {
                tlsExtensionType = TlsExtensionType.ALPN_AND_NPN;
            } else if (isAtLeastAndroid5()) {
                tlsExtensionType = TlsExtensionType.ALPN_AND_NPN;
            } else if (isAtLeastAndroid41()) {
                tlsExtensionType = TlsExtensionType.NPN;
            } else {
                tlsExtensionType = TlsExtensionType.NONE;
            }
            return new Android(setUseSessionTickets, setHostname, trafficStatsTagSocket, trafficStatsUntagSocket, getAlpnSelectedProtocol, setAlpnProtocols, androidOrAppEngineProvider, tlsExtensionType);
        }
        try {
            Provider sslProvider2 = SSLContext.getDefault().getProvider();
            try {
                SSLContext context = SSLContext.getInstance("TLS", sslProvider2);
                context.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
                ((Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                    public Method run() throws Exception {
                        return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                })).invoke(context.createSSLEngine(), new Object[0]);
                return new JdkAlpnPlatform(sslProvider2, (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                    public Method run() throws Exception {
                        return SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class});
                    }
                }), (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                    public Method run() throws Exception {
                        return SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                }));
            } catch (IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException e3) {
                try {
                    Class<?> negoClass = Class.forName("org.eclipse.jetty.alpn.ALPN");
                    Class<?> providerClass = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$Provider");
                    return new JdkWithJettyBootPlatform(negoClass.getMethod("put", new Class[]{SSLSocket.class, providerClass}), negoClass.getMethod("get", new Class[]{SSLSocket.class}), negoClass.getMethod("remove", new Class[]{SSLSocket.class}), Class.forName("org.eclipse.jetty.alpn.ALPN" + "$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN" + "$ServerProvider"), sslProvider2);
                } catch (ClassNotFoundException | NoSuchMethodException e4) {
                    return new Platform(sslProvider2);
                }
            }
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }

    private static boolean isAtLeastAndroid5() {
        try {
            Platform.class.getClassLoader().loadClass("android.net.Network");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", e);
            return false;
        }
    }

    private static boolean isAtLeastAndroid41() {
        try {
            Platform.class.getClassLoader().loadClass("android.app.ActivityOptions");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", e);
            return false;
        }
    }

    private static Provider getAndroidSecurityProvider() {
        for (Provider availableProvider : Security.getProviders()) {
            for (String providerClassName : ANDROID_SECURITY_PROVIDERS) {
                if (providerClassName.equals(availableProvider.getClass().getName())) {
                    logger.log(Level.FINE, "Found registered provider {0}", providerClassName);
                    return availableProvider;
                }
            }
        }
        logger.log(Level.WARNING, "Unable to find Conscrypt");
        return null;
    }

    /* renamed from: io.grpc.okhttp.internal.Platform$Android */
    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final TlsExtensionType tlsExtensionType;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> setUseSessionTickets2, OptionalMethod<Socket> setHostname2, Method trafficStatsTagSocket2, Method trafficStatsUntagSocket2, OptionalMethod<Socket> getAlpnSelectedProtocol2, OptionalMethod<Socket> setAlpnProtocols2, Provider provider, TlsExtensionType tlsExtensionType2) {
            super(provider);
            this.setUseSessionTickets = setUseSessionTickets2;
            this.setHostname = setHostname2;
            this.trafficStatsTagSocket = trafficStatsTagSocket2;
            this.trafficStatsUntagSocket = trafficStatsUntagSocket2;
            this.getAlpnSelectedProtocol = getAlpnSelectedProtocol2;
            this.setAlpnProtocols = setAlpnProtocols2;
            this.tlsExtensionType = tlsExtensionType2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return this.tlsExtensionType;
        }

        public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
            try {
                socket.connect(address, connectTimeout);
            } catch (SecurityException se) {
                IOException ioException = new IOException("Exception in connect");
                ioException.initCause(se);
                throw ioException;
            }
        }

        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            if (hostname != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, hostname);
            }
            if (this.setAlpnProtocols.isSupported(sslSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, concatLengthPrefixed(protocols));
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            byte[] alpnResult;
            if (this.getAlpnSelectedProtocol.isSupported(socket) && (alpnResult = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(socket, new Object[0])) != null) {
                return new String(alpnResult, Util.UTF_8);
            }
            return null;
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
    }

    /* renamed from: io.grpc.okhttp.internal.Platform$JdkAlpnPlatform */
    private static class JdkAlpnPlatform extends Platform {
        private final Method getApplicationProtocol;
        private final Method setApplicationProtocols;

        private JdkAlpnPlatform(Provider provider, Method setApplicationProtocols2, Method getApplicationProtocol2) {
            super(provider);
            this.setApplicationProtocols = setApplicationProtocols2;
            this.getApplicationProtocol = getApplicationProtocol2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            SSLParameters parameters = sslSocket.getSSLParameters();
            List<String> names = new ArrayList<>(protocols.size());
            for (Protocol protocol : protocols) {
                if (protocol != Protocol.HTTP_1_0) {
                    names.add(protocol.toString());
                }
            }
            try {
                this.setApplicationProtocols.invoke(parameters, new Object[]{names.toArray(new String[names.size()])});
                sslSocket.setSSLParameters(parameters);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            try {
                return (String) this.getApplicationProtocol.invoke(socket, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* renamed from: io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform */
    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method putMethod2, Method getMethod2, Method removeMethod2, Class<?> clientProviderClass2, Class<?> serverProviderClass2, Provider provider) {
            super(provider);
            this.putMethod = putMethod2;
            this.getMethod = getMethod2;
            this.removeMethod = removeMethod2;
            this.clientProviderClass = clientProviderClass2;
            this.serverProviderClass = serverProviderClass2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
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
            } catch (InvocationTargetException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void afterHandshake(SSLSocket sslSocket) {
            try {
                this.removeMethod.invoke((Object) null, new Object[]{sslSocket});
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException ex) {
                logger.log(Level.FINE, "Failed to remove SSLSocket from Jetty ALPN", ex);
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            try {
                JettyNegoProvider provider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke((Object) null, new Object[]{socket}));
                if (!provider.unsupported && provider.selected == null) {
                    logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (provider.unsupported) {
                    return null;
                } else {
                    return provider.selected;
                }
            } catch (InvocationTargetException e) {
                throw new AssertionError();
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            }
        }
    }

    /* renamed from: io.grpc.okhttp.internal.Platform$JettyNegoProvider */
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

    public static byte[] concatLengthPrefixed(List<Protocol> protocols) {
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
}
