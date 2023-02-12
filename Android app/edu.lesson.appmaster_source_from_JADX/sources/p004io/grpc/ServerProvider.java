package p004io.grpc;

import com.google.common.base.Preconditions;
import p004io.grpc.ManagedChannelProvider;

/* renamed from: io.grpc.ServerProvider */
public abstract class ServerProvider {
    /* access modifiers changed from: protected */
    public abstract ServerBuilder<?> builderForPort(int i);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    public static ServerProvider provider() {
        ServerProvider provider = ServerRegistry.getDefaultRegistry().provider();
        if (provider != null) {
            return provider;
        }
        throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
    }

    /* access modifiers changed from: protected */
    public NewServerBuilderResult newServerBuilderForPort(int port, ServerCredentials creds) {
        return NewServerBuilderResult.error("ServerCredentials are unsupported");
    }

    /* renamed from: io.grpc.ServerProvider$NewServerBuilderResult */
    public static final class NewServerBuilderResult {
        private final String error;
        private final ServerBuilder<?> serverBuilder;

        private NewServerBuilderResult(ServerBuilder<?> serverBuilder2, String error2) {
            this.serverBuilder = serverBuilder2;
            this.error = error2;
        }

        public static NewServerBuilderResult serverBuilder(ServerBuilder<?> builder) {
            return new NewServerBuilderResult((ServerBuilder) Preconditions.checkNotNull(builder), (String) null);
        }

        public static NewServerBuilderResult error(String error2) {
            return new NewServerBuilderResult((ServerBuilder<?>) null, (String) Preconditions.checkNotNull(error2));
        }

        public ServerBuilder<?> getServerBuilder() {
            return this.serverBuilder;
        }

        public String getError() {
            return this.error;
        }
    }
}
