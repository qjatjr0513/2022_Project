package p004io.grpc;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p004io.grpc.ServerBuilder;
import p004io.grpc.ServerStreamTracer;

/* renamed from: io.grpc.ServerBuilder */
public abstract class ServerBuilder<T extends ServerBuilder<T>> {
    public abstract T addService(BindableService bindableService);

    public abstract T addService(ServerServiceDefinition serverServiceDefinition);

    public abstract Server build();

    public abstract T compressorRegistry(@Nullable CompressorRegistry compressorRegistry);

    public abstract T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry);

    public abstract T directExecutor();

    public abstract T executor(@Nullable Executor executor);

    public abstract T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry);

    public abstract T useTransportSecurity(File file, File file2);

    public static ServerBuilder<?> forPort(int port) {
        return ServerProvider.provider().builderForPort(port);
    }

    public T callExecutor(ServerCallExecutorSupplier executorSupplier) {
        return thisT();
    }

    public final T addServices(List<ServerServiceDefinition> services) {
        Preconditions.checkNotNull(services, "services");
        for (ServerServiceDefinition service : services) {
            addService(service);
        }
        return thisT();
    }

    public T intercept(ServerInterceptor interceptor) {
        throw new UnsupportedOperationException();
    }

    public T addTransportFilter(ServerTransportFilter filter) {
        throw new UnsupportedOperationException();
    }

    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        throw new UnsupportedOperationException();
    }

    public T useTransportSecurity(InputStream certChain, InputStream privateKey) {
        throw new UnsupportedOperationException();
    }

    public T handshakeTimeout(long timeout, TimeUnit unit) {
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

    public T setBinaryLog(BinaryLog binaryLog) {
        throw new UnsupportedOperationException();
    }

    private T thisT() {
        return this;
    }
}
