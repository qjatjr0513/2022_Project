package p004io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.CallCredentials;
import p004io.grpc.ChannelCredentials;
import p004io.grpc.ChannelLogger;
import p004io.grpc.HttpConnectProxiedSocketAddress;

/* renamed from: io.grpc.internal.ClientTransportFactory */
public interface ClientTransportFactory extends Closeable {
    void close();

    ScheduledExecutorService getScheduledExecutorService();

    ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger);

    @CheckReturnValue
    @Nullable
    SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials);

    /* renamed from: io.grpc.internal.ClientTransportFactory$ClientTransportOptions */
    public static final class ClientTransportOptions {
        private String authority = "unknown-authority";
        private ChannelLogger channelLogger;
        @Nullable
        private HttpConnectProxiedSocketAddress connectProxiedSocketAddr;
        private Attributes eagAttributes = Attributes.EMPTY;
        @Nullable
        private String userAgent;

        public ChannelLogger getChannelLogger() {
            return this.channelLogger;
        }

        public ClientTransportOptions setChannelLogger(ChannelLogger channelLogger2) {
            this.channelLogger = channelLogger2;
            return this;
        }

        public String getAuthority() {
            return this.authority;
        }

        public ClientTransportOptions setAuthority(String authority2) {
            this.authority = (String) Preconditions.checkNotNull(authority2, "authority");
            return this;
        }

        public Attributes getEagAttributes() {
            return this.eagAttributes;
        }

        public ClientTransportOptions setEagAttributes(Attributes eagAttributes2) {
            Preconditions.checkNotNull(eagAttributes2, "eagAttributes");
            this.eagAttributes = eagAttributes2;
            return this;
        }

        @Nullable
        public String getUserAgent() {
            return this.userAgent;
        }

        public ClientTransportOptions setUserAgent(@Nullable String userAgent2) {
            this.userAgent = userAgent2;
            return this;
        }

        @Nullable
        public HttpConnectProxiedSocketAddress getHttpConnectProxiedSocketAddress() {
            return this.connectProxiedSocketAddr;
        }

        public ClientTransportOptions setHttpConnectProxiedSocketAddress(@Nullable HttpConnectProxiedSocketAddress connectProxiedSocketAddr2) {
            this.connectProxiedSocketAddr = connectProxiedSocketAddr2;
            return this;
        }

        public int hashCode() {
            return Objects.hashCode(this.authority, this.eagAttributes, this.userAgent, this.connectProxiedSocketAddr);
        }

        public boolean equals(Object o) {
            if (!(o instanceof ClientTransportOptions)) {
                return false;
            }
            ClientTransportOptions that = (ClientTransportOptions) o;
            if (!this.authority.equals(that.authority) || !this.eagAttributes.equals(that.eagAttributes) || !Objects.equal(this.userAgent, that.userAgent) || !Objects.equal(this.connectProxiedSocketAddr, that.connectProxiedSocketAddr)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: io.grpc.internal.ClientTransportFactory$SwapChannelCredentialsResult */
    public static final class SwapChannelCredentialsResult {
        @Nullable
        final CallCredentials callCredentials;
        final ClientTransportFactory transportFactory;

        public SwapChannelCredentialsResult(ClientTransportFactory transportFactory2, @Nullable CallCredentials callCredentials2) {
            this.transportFactory = (ClientTransportFactory) Preconditions.checkNotNull(transportFactory2, "transportFactory");
            this.callCredentials = callCredentials2;
        }
    }
}
