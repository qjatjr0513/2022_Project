package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import p004io.grpc.okhttp.internal.ConnectionSpec;
import p004io.grpc.okhttp.internal.OkHostnameVerifier;
import p004io.grpc.okhttp.internal.Protocol;

/* renamed from: io.grpc.okhttp.OkHttpTlsUpgrader */
final class OkHttpTlsUpgrader {
    static final List<Protocol> TLS_PROTOCOLS = Collections.unmodifiableList(Arrays.asList(new Protocol[]{Protocol.HTTP_2}));

    OkHttpTlsUpgrader() {
    }

    public static SSLSocket upgrade(SSLSocketFactory sslSocketFactory, HostnameVerifier hostnameVerifier, Socket socket, String host, int port, ConnectionSpec spec) throws IOException {
        Preconditions.checkNotNull(sslSocketFactory, "sslSocketFactory");
        Preconditions.checkNotNull(socket, "socket");
        Preconditions.checkNotNull(spec, "spec");
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, host, port, true);
        spec.apply(sslSocket, false);
        String negotiatedProtocol = OkHttpProtocolNegotiator.get().negotiate(sslSocket, host, spec.supportsTlsExtensions() ? TLS_PROTOCOLS : null);
        List<Protocol> list = TLS_PROTOCOLS;
        Preconditions.checkState(list.contains(Protocol.get(negotiatedProtocol)), "Only " + list + " are supported, but negotiated protocol is %s", (Object) negotiatedProtocol);
        if (hostnameVerifier == null) {
            hostnameVerifier = OkHostnameVerifier.INSTANCE;
        }
        if (hostnameVerifier.verify(canonicalizeHost(host), sslSocket.getSession())) {
            return sslSocket;
        }
        throw new SSLPeerUnverifiedException("Cannot verify hostname: " + host);
    }

    static String canonicalizeHost(String host) {
        if (!host.startsWith("[") || !host.endsWith("]")) {
            return host;
        }
        return host.substring(1, host.length() - 1);
    }
}
