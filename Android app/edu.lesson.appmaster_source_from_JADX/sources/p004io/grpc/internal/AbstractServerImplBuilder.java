package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p004io.grpc.BinaryLog;
import p004io.grpc.BindableService;
import p004io.grpc.CompressorRegistry;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.HandlerRegistry;
import p004io.grpc.Server;
import p004io.grpc.ServerBuilder;
import p004io.grpc.ServerCallExecutorSupplier;
import p004io.grpc.ServerInterceptor;
import p004io.grpc.ServerServiceDefinition;
import p004io.grpc.ServerStreamTracer;
import p004io.grpc.ServerTransportFilter;

/* renamed from: io.grpc.internal.AbstractServerImplBuilder */
public abstract class AbstractServerImplBuilder<T extends ServerBuilder<T>> extends ServerBuilder<T> {
    /* access modifiers changed from: protected */
    public abstract ServerBuilder<?> delegate();

    protected AbstractServerImplBuilder() {
    }

    public static ServerBuilder<?> forPort(int port) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public T directExecutor() {
        delegate().directExecutor();
        return thisT();
    }

    public T callExecutor(ServerCallExecutorSupplier executorSupplier) {
        delegate().callExecutor(executorSupplier);
        return thisT();
    }

    public T executor(@Nullable Executor executor) {
        delegate().executor(executor);
        return thisT();
    }

    public T addService(ServerServiceDefinition service) {
        delegate().addService(service);
        return thisT();
    }

    public T addService(BindableService bindableService) {
        delegate().addService(bindableService);
        return thisT();
    }

    public T intercept(ServerInterceptor interceptor) {
        delegate().intercept(interceptor);
        return thisT();
    }

    public T addTransportFilter(ServerTransportFilter filter) {
        delegate().addTransportFilter(filter);
        return thisT();
    }

    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        delegate().addStreamTracerFactory(factory);
        return thisT();
    }

    public T fallbackHandlerRegistry(@Nullable HandlerRegistry fallbackRegistry) {
        delegate().fallbackHandlerRegistry(fallbackRegistry);
        return thisT();
    }

    public T useTransportSecurity(File certChain, File privateKey) {
        delegate().useTransportSecurity(certChain, privateKey);
        return thisT();
    }

    public T useTransportSecurity(InputStream certChain, InputStream privateKey) {
        delegate().useTransportSecurity(certChain, privateKey);
        return thisT();
    }

    public T decompressorRegistry(@Nullable DecompressorRegistry registry) {
        delegate().decompressorRegistry(registry);
        return thisT();
    }

    public T compressorRegistry(@Nullable CompressorRegistry registry) {
        delegate().compressorRegistry(registry);
        return thisT();
    }

    public T handshakeTimeout(long timeout, TimeUnit unit) {
        delegate().handshakeTimeout(timeout, unit);
        return thisT();
    }

    public T maxInboundMessageSize(int bytes) {
        delegate().maxInboundMessageSize(bytes);
        return thisT();
    }

    public T maxInboundMetadataSize(int bytes) {
        delegate().maxInboundMetadataSize(bytes);
        return thisT();
    }

    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return thisT();
    }

    public Server build() {
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
