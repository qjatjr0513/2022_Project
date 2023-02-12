package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p004io.grpc.BinaryLog;
import p004io.grpc.ClientInterceptor;
import p004io.grpc.CompressorRegistry;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.ManagedChannel;
import p004io.grpc.ManagedChannelBuilder;
import p004io.grpc.NameResolver;
import p004io.grpc.ProxyDetector;

/* renamed from: io.grpc.internal.AbstractManagedChannelImplBuilder */
public abstract class AbstractManagedChannelImplBuilder<T extends ManagedChannelBuilder<T>> extends ManagedChannelBuilder<T> {
    protected int maxInboundMessageSize = 4194304;

    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> delegate();

    protected AbstractManagedChannelImplBuilder() {
    }

    public static ManagedChannelBuilder<?> forAddress(String name, int port) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String target) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public T directExecutor() {
        delegate().directExecutor();
        return thisT();
    }

    public T executor(Executor executor) {
        delegate().executor(executor);
        return thisT();
    }

    public T offloadExecutor(Executor executor) {
        delegate().offloadExecutor(executor);
        return thisT();
    }

    public T intercept(List<ClientInterceptor> interceptors) {
        delegate().intercept(interceptors);
        return thisT();
    }

    public T intercept(ClientInterceptor... interceptors) {
        delegate().intercept(interceptors);
        return thisT();
    }

    public T userAgent(String userAgent) {
        delegate().userAgent(userAgent);
        return thisT();
    }

    public T overrideAuthority(String authority) {
        delegate().overrideAuthority(authority);
        return thisT();
    }

    public T usePlaintext() {
        delegate().usePlaintext();
        return thisT();
    }

    public T useTransportSecurity() {
        delegate().useTransportSecurity();
        return thisT();
    }

    @Deprecated
    public T nameResolverFactory(NameResolver.Factory resolverFactory) {
        delegate().nameResolverFactory(resolverFactory);
        return thisT();
    }

    public T defaultLoadBalancingPolicy(String policy) {
        delegate().defaultLoadBalancingPolicy(policy);
        return thisT();
    }

    public T enableFullStreamDecompression() {
        delegate().enableFullStreamDecompression();
        return thisT();
    }

    public T decompressorRegistry(DecompressorRegistry registry) {
        delegate().decompressorRegistry(registry);
        return thisT();
    }

    public T compressorRegistry(CompressorRegistry registry) {
        delegate().compressorRegistry(registry);
        return thisT();
    }

    public T idleTimeout(long value, TimeUnit unit) {
        delegate().idleTimeout(value, unit);
        return thisT();
    }

    public T maxInboundMessageSize(int max) {
        Preconditions.checkArgument(max >= 0, "negative max");
        this.maxInboundMessageSize = max;
        return thisT();
    }

    public T maxInboundMetadataSize(int max) {
        delegate().maxInboundMetadataSize(max);
        return thisT();
    }

    public T keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        delegate().keepAliveTime(keepAliveTime, timeUnit);
        return thisT();
    }

    public T keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        delegate().keepAliveTimeout(keepAliveTimeout, timeUnit);
        return thisT();
    }

    public T keepAliveWithoutCalls(boolean enable) {
        delegate().keepAliveWithoutCalls(enable);
        return thisT();
    }

    public T maxRetryAttempts(int maxRetryAttempts) {
        delegate().maxRetryAttempts(maxRetryAttempts);
        return thisT();
    }

    public T maxHedgedAttempts(int maxHedgedAttempts) {
        delegate().maxHedgedAttempts(maxHedgedAttempts);
        return thisT();
    }

    public T retryBufferSize(long bytes) {
        delegate().retryBufferSize(bytes);
        return thisT();
    }

    public T perRpcBufferLimit(long bytes) {
        delegate().perRpcBufferLimit(bytes);
        return thisT();
    }

    public T disableRetry() {
        delegate().disableRetry();
        return thisT();
    }

    public T enableRetry() {
        delegate().enableRetry();
        return thisT();
    }

    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return thisT();
    }

    public T maxTraceEvents(int maxTraceEvents) {
        delegate().maxTraceEvents(maxTraceEvents);
        return thisT();
    }

    public T proxyDetector(ProxyDetector proxyDetector) {
        delegate().proxyDetector(proxyDetector);
        return thisT();
    }

    public T defaultServiceConfig(@Nullable Map<String, ?> serviceConfig) {
        delegate().defaultServiceConfig(serviceConfig);
        return thisT();
    }

    public T disableServiceConfigLookUp() {
        delegate().disableServiceConfigLookUp();
        return thisT();
    }

    public ManagedChannel build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }

    /* access modifiers changed from: protected */
    public final T thisT() {
        return this;
    }
}
