package p004io.grpc.internal;

import com.google.common.base.Supplier;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.HttpConnectProxiedSocketAddress;
import p004io.grpc.ProxiedSocketAddress;
import p004io.grpc.ProxyDetector;

/* renamed from: io.grpc.internal.ProxyDetectorImpl */
class ProxyDetectorImpl implements ProxyDetector {
    private static final AuthenticationProvider DEFAULT_AUTHENTICATOR = new AuthenticationProvider() {
        public PasswordAuthentication requestPasswordAuthentication(String host, InetAddress addr, int port, String protocol, String prompt, String scheme) {
            URL url;
            String str = host;
            String str2 = protocol;
            try {
                int i = port;
                try {
                    url = new URL(str2, host, port, "");
                } catch (MalformedURLException e) {
                    ProxyDetectorImpl.log.log(Level.WARNING, String.format("failed to create URL for Authenticator: %s %s", new Object[]{str2, str}));
                    url = null;
                    return Authenticator.requestPasswordAuthentication(host, addr, port, protocol, prompt, scheme, url, Authenticator.RequestorType.PROXY);
                }
            } catch (MalformedURLException e2) {
                int i2 = port;
                ProxyDetectorImpl.log.log(Level.WARNING, String.format("failed to create URL for Authenticator: %s %s", new Object[]{str2, str}));
                url = null;
                return Authenticator.requestPasswordAuthentication(host, addr, port, protocol, prompt, scheme, url, Authenticator.RequestorType.PROXY);
            }
            return Authenticator.requestPasswordAuthentication(host, addr, port, protocol, prompt, scheme, url, Authenticator.RequestorType.PROXY);
        }
    };
    private static final Supplier<ProxySelector> DEFAULT_PROXY_SELECTOR = new Supplier<ProxySelector>() {
        public ProxySelector get() {
            return ProxySelector.getDefault();
        }
    };
    @Deprecated
    private static final String GRPC_PROXY_ENV_VAR = "GRPC_PROXY_EXP";
    static final String PROXY_SCHEME = "https";
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(ProxyDetectorImpl.class.getName());
    private final AuthenticationProvider authenticationProvider;
    private final InetSocketAddress overrideProxyAddress;
    private final Supplier<ProxySelector> proxySelector;

    /* renamed from: io.grpc.internal.ProxyDetectorImpl$AuthenticationProvider */
    interface AuthenticationProvider {
        PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4);
    }

    public ProxyDetectorImpl() {
        this(DEFAULT_PROXY_SELECTOR, DEFAULT_AUTHENTICATOR, System.getenv(GRPC_PROXY_ENV_VAR));
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.common.base.Supplier<java.net.ProxySelector>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ProxyDetectorImpl(com.google.common.base.Supplier<java.net.ProxySelector> r2, p004io.grpc.internal.ProxyDetectorImpl.AuthenticationProvider r3, @javax.annotation.Nullable java.lang.String r4) {
        /*
            r1 = this;
            r1.<init>()
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r2)
            com.google.common.base.Supplier r0 = (com.google.common.base.Supplier) r0
            r1.proxySelector = r0
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r3)
            io.grpc.internal.ProxyDetectorImpl$AuthenticationProvider r0 = (p004io.grpc.internal.ProxyDetectorImpl.AuthenticationProvider) r0
            r1.authenticationProvider = r0
            if (r4 == 0) goto L_0x001c
            java.net.InetSocketAddress r0 = overrideProxy(r4)
            r1.overrideProxyAddress = r0
            goto L_0x001f
        L_0x001c:
            r0 = 0
            r1.overrideProxyAddress = r0
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.ProxyDetectorImpl.<init>(com.google.common.base.Supplier, io.grpc.internal.ProxyDetectorImpl$AuthenticationProvider, java.lang.String):void");
    }

    @Nullable
    public ProxiedSocketAddress proxyFor(SocketAddress targetServerAddress) throws IOException {
        if (!(targetServerAddress instanceof InetSocketAddress)) {
            return null;
        }
        if (this.overrideProxyAddress != null) {
            return HttpConnectProxiedSocketAddress.newBuilder().setProxyAddress(this.overrideProxyAddress).setTargetAddress((InetSocketAddress) targetServerAddress).build();
        }
        return detectProxy((InetSocketAddress) targetServerAddress);
    }

    private ProxiedSocketAddress detectProxy(InetSocketAddress targetAddr) throws IOException {
        InetSocketAddress resolvedProxyAddr;
        String str = null;
        try {
            URI uri = new URI(PROXY_SCHEME, (String) null, GrpcUtil.getHost(targetAddr), targetAddr.getPort(), (String) null, (String) null, (String) null);
            ProxySelector proxySelector2 = this.proxySelector.get();
            if (proxySelector2 == null) {
                log.log(Level.FINE, "proxy selector is null, so continuing without proxy lookup");
                return null;
            }
            List<Proxy> proxies = proxySelector2.select(uri);
            if (proxies.size() > 1) {
                log.warning("More than 1 proxy detected, gRPC will select the first one");
            }
            Proxy proxy = proxies.get(0);
            if (proxy.type() == Proxy.Type.DIRECT) {
                return null;
            }
            InetSocketAddress proxyAddr = (InetSocketAddress) proxy.address();
            PasswordAuthentication auth = this.authenticationProvider.requestPasswordAuthentication(GrpcUtil.getHost(proxyAddr), proxyAddr.getAddress(), proxyAddr.getPort(), PROXY_SCHEME, "", (String) null);
            if (proxyAddr.isUnresolved()) {
                resolvedProxyAddr = new InetSocketAddress(InetAddress.getByName(proxyAddr.getHostName()), proxyAddr.getPort());
            } else {
                resolvedProxyAddr = proxyAddr;
            }
            HttpConnectProxiedSocketAddress.Builder builder = HttpConnectProxiedSocketAddress.newBuilder().setTargetAddress(targetAddr).setProxyAddress(resolvedProxyAddr);
            if (auth == null) {
                return builder.build();
            }
            HttpConnectProxiedSocketAddress.Builder username = builder.setUsername(auth.getUserName());
            if (auth.getPassword() != null) {
                str = new String(auth.getPassword());
            }
            return username.setPassword(str).build();
        } catch (URISyntaxException e) {
            InetSocketAddress inetSocketAddress = targetAddr;
            log.log(Level.WARNING, "Failed to construct URI for proxy lookup, proceeding without proxy", e);
            return null;
        }
    }

    private static InetSocketAddress overrideProxy(String proxyHostPort) {
        if (proxyHostPort == null) {
            return null;
        }
        String[] parts = proxyHostPort.split(":", 2);
        int port = 80;
        if (parts.length > 1) {
            port = Integer.parseInt(parts[1]);
        }
        log.warning("Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
        return new InetSocketAddress(parts[0], port);
    }
}
