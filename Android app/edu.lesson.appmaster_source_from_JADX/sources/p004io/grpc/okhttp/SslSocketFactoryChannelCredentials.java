package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: io.grpc.okhttp.SslSocketFactoryChannelCredentials */
public final class SslSocketFactoryChannelCredentials {
    private SslSocketFactoryChannelCredentials() {
    }

    public static p004io.grpc.ChannelCredentials create(SSLSocketFactory factory) {
        return new ChannelCredentials(factory);
    }

    /* renamed from: io.grpc.okhttp.SslSocketFactoryChannelCredentials$ChannelCredentials */
    static final class ChannelCredentials extends p004io.grpc.ChannelCredentials {
        private final SSLSocketFactory factory;

        private ChannelCredentials(SSLSocketFactory factory2) {
            this.factory = (SSLSocketFactory) Preconditions.checkNotNull(factory2, "factory");
        }

        public SSLSocketFactory getFactory() {
            return this.factory;
        }

        public p004io.grpc.ChannelCredentials withoutBearerTokens() {
            return this;
        }
    }
}
