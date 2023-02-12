package p004io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import javax.annotation.Nullable;

/* renamed from: io.grpc.HttpConnectProxiedSocketAddress */
public final class HttpConnectProxiedSocketAddress extends ProxiedSocketAddress {
    private static final long serialVersionUID = 0;
    @Nullable
    private final String password;
    private final SocketAddress proxyAddress;
    private final InetSocketAddress targetAddress;
    @Nullable
    private final String username;

    private HttpConnectProxiedSocketAddress(SocketAddress proxyAddress2, InetSocketAddress targetAddress2, @Nullable String username2, @Nullable String password2) {
        Preconditions.checkNotNull(proxyAddress2, "proxyAddress");
        Preconditions.checkNotNull(targetAddress2, "targetAddress");
        if (proxyAddress2 instanceof InetSocketAddress) {
            Preconditions.checkState(!((InetSocketAddress) proxyAddress2).isUnresolved(), "The proxy address %s is not resolved", (Object) proxyAddress2);
        }
        this.proxyAddress = proxyAddress2;
        this.targetAddress = targetAddress2;
        this.username = username2;
        this.password = password2;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Nullable
    public String getUsername() {
        return this.username;
    }

    public SocketAddress getProxyAddress() {
        return this.proxyAddress;
    }

    public InetSocketAddress getTargetAddress() {
        return this.targetAddress;
    }

    public boolean equals(Object o) {
        if (!(o instanceof HttpConnectProxiedSocketAddress)) {
            return false;
        }
        HttpConnectProxiedSocketAddress that = (HttpConnectProxiedSocketAddress) o;
        if (!Objects.equal(this.proxyAddress, that.proxyAddress) || !Objects.equal(this.targetAddress, that.targetAddress) || !Objects.equal(this.username, that.username) || !Objects.equal(this.password, that.password)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.proxyAddress, this.targetAddress, this.username, this.password);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("proxyAddr", (Object) this.proxyAddress).add("targetAddr", (Object) this.targetAddress).add("username", (Object) this.username).add("hasPassword", this.password != null).toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* renamed from: io.grpc.HttpConnectProxiedSocketAddress$Builder */
    public static final class Builder {
        @Nullable
        private String password;
        private SocketAddress proxyAddress;
        private InetSocketAddress targetAddress;
        @Nullable
        private String username;

        private Builder() {
        }

        public Builder setProxyAddress(SocketAddress proxyAddress2) {
            this.proxyAddress = (SocketAddress) Preconditions.checkNotNull(proxyAddress2, "proxyAddress");
            return this;
        }

        public Builder setTargetAddress(InetSocketAddress targetAddress2) {
            this.targetAddress = (InetSocketAddress) Preconditions.checkNotNull(targetAddress2, "targetAddress");
            return this;
        }

        public Builder setUsername(@Nullable String username2) {
            this.username = username2;
            return this;
        }

        public Builder setPassword(@Nullable String password2) {
            this.password = password2;
            return this;
        }

        public HttpConnectProxiedSocketAddress build() {
            return new HttpConnectProxiedSocketAddress(this.proxyAddress, this.targetAddress, this.username, this.password);
        }
    }
}
