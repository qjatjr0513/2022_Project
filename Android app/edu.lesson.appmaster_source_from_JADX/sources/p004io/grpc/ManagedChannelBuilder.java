package p004io.grpc;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p004io.grpc.ManagedChannelBuilder;
import p004io.grpc.NameResolver;

/* renamed from: io.grpc.ManagedChannelBuilder */
public abstract class ManagedChannelBuilder<T extends ManagedChannelBuilder<T>> {
    public abstract ManagedChannel build();

    public abstract T compressorRegistry(CompressorRegistry compressorRegistry);

    public abstract T decompressorRegistry(DecompressorRegistry decompressorRegistry);

    public abstract T directExecutor();

    public abstract T executor(Executor executor);

    public abstract T idleTimeout(long j, TimeUnit timeUnit);

    public abstract T intercept(List<ClientInterceptor> list);

    public abstract T intercept(ClientInterceptor... clientInterceptorArr);

    @Deprecated
    public abstract T nameResolverFactory(NameResolver.Factory factory);

    public abstract T overrideAuthority(String str);

    public abstract T userAgent(String str);

    public static ManagedChannelBuilder<?> forAddress(String name, int port) {
        return ManagedChannelProvider.provider().builderForAddress(name, port);
    }

    public static ManagedChannelBuilder<?> forTarget(String target) {
        return ManagedChannelProvider.provider().builderForTarget(target);
    }

    public T offloadExecutor(Executor executor) {
        throw new UnsupportedOperationException();
    }

    public T usePlaintext() {
        throw new UnsupportedOperationException();
    }

    public T useTransportSecurity() {
        throw new UnsupportedOperationException();
    }

    public T defaultLoadBalancingPolicy(String policy) {
        throw new UnsupportedOperationException();
    }

    public T enableFullStreamDecompression() {
        throw new UnsupportedOperationException();
    }

    public T maxInboundMessageSize(int bytes) {
        Preconditions.checkArgument(bytes >= 0, "bytes must be >= 0");
        return thisT();
    }

    public T maxInboundMetadataSize(int bytes) {
        Preconditions.checkArgument(bytes > 0, "maxInboundMetadataSize must be > 0");
        return thisT();
    }

    public T keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public T keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public T keepAliveWithoutCalls(boolean enable) {
        throw new UnsupportedOperationException();
    }

    public T maxRetryAttempts(int maxRetryAttempts) {
        throw new UnsupportedOperationException();
    }

    public T maxHedgedAttempts(int maxHedgedAttempts) {
        throw new UnsupportedOperationException();
    }

    public T retryBufferSize(long bytes) {
        throw new UnsupportedOperationException();
    }

    public T perRpcBufferLimit(long bytes) {
        throw new UnsupportedOperationException();
    }

    public T disableRetry() {
        throw new UnsupportedOperationException();
    }

    public T enableRetry() {
        throw new UnsupportedOperationException();
    }

    public T setBinaryLog(BinaryLog binaryLog) {
        throw new UnsupportedOperationException();
    }

    public T maxTraceEvents(int maxTraceEvents) {
        throw new UnsupportedOperationException();
    }

    public T proxyDetector(ProxyDetector proxyDetector) {
        throw new UnsupportedOperationException();
    }

    public T defaultServiceConfig(@Nullable Map<String, ?> map) {
        throw new UnsupportedOperationException();
    }

    public T disableServiceConfigLookUp() {
        throw new UnsupportedOperationException();
    }

    private T thisT() {
        return this;
    }
}
