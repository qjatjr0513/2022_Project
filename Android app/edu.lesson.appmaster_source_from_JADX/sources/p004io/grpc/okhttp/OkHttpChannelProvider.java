package p004io.grpc.okhttp;

import p004io.grpc.ChannelCredentials;
import p004io.grpc.InternalServiceProviders;
import p004io.grpc.ManagedChannelProvider;
import p004io.grpc.okhttp.OkHttpChannelBuilder;

/* renamed from: io.grpc.okhttp.OkHttpChannelProvider */
public final class OkHttpChannelProvider extends ManagedChannelProvider {
    public boolean isAvailable() {
        return true;
    }

    public int priority() {
        return InternalServiceProviders.isAndroid(getClass().getClassLoader()) ? 8 : 3;
    }

    public OkHttpChannelBuilder builderForAddress(String name, int port) {
        return OkHttpChannelBuilder.forAddress(name, port);
    }

    public OkHttpChannelBuilder builderForTarget(String target) {
        return OkHttpChannelBuilder.forTarget(target);
    }

    public ManagedChannelProvider.NewChannelBuilderResult newChannelBuilder(String target, ChannelCredentials creds) {
        OkHttpChannelBuilder.SslSocketFactoryResult result = OkHttpChannelBuilder.sslSocketFactoryFrom(creds);
        if (result.error != null) {
            return ManagedChannelProvider.NewChannelBuilderResult.error(result.error);
        }
        return ManagedChannelProvider.NewChannelBuilderResult.channelBuilder(new OkHttpChannelBuilder(target, creds, result.callCredentials, result.factory));
    }
}
