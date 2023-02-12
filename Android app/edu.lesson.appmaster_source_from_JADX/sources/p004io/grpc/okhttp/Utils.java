package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.squareup.okhttp.CipherSuite;
import com.squareup.okhttp.TlsVersion;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalMetadata;
import p004io.grpc.Metadata;
import p004io.grpc.internal.TransportFrameUtil;
import p004io.grpc.okhttp.internal.ConnectionSpec;
import p004io.grpc.okhttp.internal.framed.Header;

/* renamed from: io.grpc.okhttp.Utils */
class Utils {
    static final int CONNECTION_STREAM_ID = 0;
    static final int DEFAULT_WINDOW_SIZE = 65535;
    static final float DEFAULT_WINDOW_UPDATE_RATIO = 0.5f;
    private static final Logger log = Logger.getLogger(Utils.class.getName());

    public static Metadata convertHeaders(List<Header> http2Headers) {
        return InternalMetadata.newMetadata(convertHeadersToArray(http2Headers));
    }

    public static Metadata convertTrailers(List<Header> http2Headers) {
        return InternalMetadata.newMetadata(convertHeadersToArray(http2Headers));
    }

    @CheckReturnValue
    private static byte[][] convertHeadersToArray(List<Header> http2Headers) {
        byte[][] headerValues = new byte[(http2Headers.size() * 2)][];
        int i = 0;
        for (Header header : http2Headers) {
            int i2 = i + 1;
            headerValues[i] = header.name.toByteArray();
            i = i2 + 1;
            headerValues[i2] = header.value.toByteArray();
        }
        return TransportFrameUtil.toRawSerializedHeaders(headerValues);
    }

    static ConnectionSpec convertSpec(com.squareup.okhttp.ConnectionSpec spec) {
        Preconditions.checkArgument(spec.isTls(), "plaintext ConnectionSpec is not accepted");
        List<TlsVersion> tlsVersionList = spec.tlsVersions();
        String[] tlsVersions = new String[tlsVersionList.size()];
        for (int i = 0; i < tlsVersions.length; i++) {
            tlsVersions[i] = tlsVersionList.get(i).javaName();
        }
        List<CipherSuite> cipherSuiteList = spec.cipherSuites();
        p004io.grpc.okhttp.internal.CipherSuite[] cipherSuites = new p004io.grpc.okhttp.internal.CipherSuite[cipherSuiteList.size()];
        for (int i2 = 0; i2 < cipherSuites.length; i2++) {
            cipherSuites[i2] = p004io.grpc.okhttp.internal.CipherSuite.valueOf(cipherSuiteList.get(i2).name());
        }
        return new ConnectionSpec.Builder(spec.isTls()).supportsTlsExtensions(spec.supportsTlsExtensions()).tlsVersions(tlsVersions).cipherSuites(cipherSuites).build();
    }

    static InternalChannelz.SocketOptions getSocketOptions(Socket socket) {
        InternalChannelz.SocketOptions.Builder builder = new InternalChannelz.SocketOptions.Builder();
        try {
            builder.setSocketOptionLingerSeconds(Integer.valueOf(socket.getSoLinger()));
        } catch (SocketException e) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e);
            builder.addOption("SO_LINGER", "channelz_internal_error");
        }
        try {
            builder.setSocketOptionTimeoutMillis(Integer.valueOf(socket.getSoTimeout()));
        } catch (Exception e2) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e2);
            builder.addOption("SO_TIMEOUT", "channelz_internal_error");
        }
        try {
            builder.addOption("TCP_NODELAY", socket.getTcpNoDelay());
        } catch (SocketException e3) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e3);
            builder.addOption("TCP_NODELAY", "channelz_internal_error");
        }
        try {
            builder.addOption("SO_REUSEADDR", socket.getReuseAddress());
        } catch (SocketException e4) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e4);
            builder.addOption("SO_REUSEADDR", "channelz_internal_error");
        }
        try {
            builder.addOption("SO_SNDBUF", socket.getSendBufferSize());
        } catch (SocketException e5) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e5);
            builder.addOption("SO_SNDBUF", "channelz_internal_error");
        }
        try {
            builder.addOption("SO_RECVBUF", socket.getReceiveBufferSize());
        } catch (SocketException e6) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e6);
            builder.addOption("SO_RECVBUF", "channelz_internal_error");
        }
        try {
            builder.addOption("SO_KEEPALIVE", socket.getKeepAlive());
        } catch (SocketException e7) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e7);
            builder.addOption("SO_KEEPALIVE", "channelz_internal_error");
        }
        try {
            builder.addOption("SO_OOBINLINE", socket.getOOBInline());
        } catch (SocketException e8) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e8);
            builder.addOption("SO_OOBINLINE", "channelz_internal_error");
        }
        try {
            builder.addOption("IP_TOS", socket.getTrafficClass());
        } catch (SocketException e9) {
            log.log(Level.SEVERE, "Exception caught while reading socket option", e9);
            builder.addOption("IP_TOS", "channelz_internal_error");
        }
        return builder.build();
    }

    private Utils() {
    }
}
