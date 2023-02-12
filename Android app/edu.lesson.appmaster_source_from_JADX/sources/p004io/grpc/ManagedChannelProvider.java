package p004io.grpc;

import com.google.common.base.Preconditions;

/* renamed from: io.grpc.ManagedChannelProvider */
public abstract class ManagedChannelProvider {
    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForTarget(String str);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider provider = ManagedChannelRegistry.getDefaultRegistry().provider();
        if (provider != null) {
            return provider;
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }

    /* access modifiers changed from: protected */
    public NewChannelBuilderResult newChannelBuilder(String target, ChannelCredentials creds) {
        return NewChannelBuilderResult.error("ChannelCredentials are unsupported");
    }

    /* renamed from: io.grpc.ManagedChannelProvider$NewChannelBuilderResult */
    public static final class NewChannelBuilderResult {
        private final ManagedChannelBuilder<?> channelBuilder;
        private final String error;

        private NewChannelBuilderResult(ManagedChannelBuilder<?> channelBuilder2, String error2) {
            this.channelBuilder = channelBuilder2;
            this.error = error2;
        }

        public static NewChannelBuilderResult channelBuilder(ManagedChannelBuilder<?> builder) {
            return new NewChannelBuilderResult((ManagedChannelBuilder) Preconditions.checkNotNull(builder), (String) null);
        }

        public static NewChannelBuilderResult error(String error2) {
            return new NewChannelBuilderResult((ManagedChannelBuilder<?>) null, (String) Preconditions.checkNotNull(error2));
        }

        public ManagedChannelBuilder<?> getChannelBuilder() {
            return this.channelBuilder;
        }

        public String getError() {
            return this.error;
        }
    }

    /* renamed from: io.grpc.ManagedChannelProvider$ProviderNotFoundException */
    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String msg) {
            super(msg);
        }
    }
}
