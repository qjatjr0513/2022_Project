package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.BinaryLog;
import p004io.grpc.BindableService;
import p004io.grpc.CompressorRegistry;
import p004io.grpc.Context;
import p004io.grpc.Deadline;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.HandlerRegistry;
import p004io.grpc.InternalChannelz;
import p004io.grpc.Server;
import p004io.grpc.ServerBuilder;
import p004io.grpc.ServerCallExecutorSupplier;
import p004io.grpc.ServerInterceptor;
import p004io.grpc.ServerMethodDefinition;
import p004io.grpc.ServerServiceDefinition;
import p004io.grpc.ServerStreamTracer;
import p004io.grpc.ServerTransportFilter;
import p004io.grpc.internal.CallTracer;
import p004io.grpc.internal.InternalHandlerRegistry;

/* renamed from: io.grpc.internal.ServerImplBuilder */
public final class ServerImplBuilder extends ServerBuilder<ServerImplBuilder> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new DefaultFallbackRegistry();
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    private static final Logger log = Logger.getLogger(ServerImplBuilder.class.getName());
    @Nullable
    BinaryLog binlog;
    CallTracer.Factory callTracerFactory = CallTracer.getDefaultFactory();
    InternalChannelz channelz = InternalChannelz.instance();
    private final ClientTransportServersBuilder clientTransportServersBuilder;
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    @Nullable
    ServerCallExecutorSupplier executorSupplier;
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    final List<ServerInterceptor> interceptors = new ArrayList();
    private boolean recordFinishedRpcs = true;
    private boolean recordRealTimeMetrics = false;
    private boolean recordStartedRpcs = true;
    final InternalHandlerRegistry.Builder registryBuilder = new InternalHandlerRegistry.Builder();
    private boolean statsEnabled = true;
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList();
    Deadline.Ticker ticker = Deadline.getSystemTicker();
    private boolean tracingEnabled = true;
    final List<ServerTransportFilter> transportFilters = new ArrayList();

    /* renamed from: io.grpc.internal.ServerImplBuilder$ClientTransportServersBuilder */
    public interface ClientTransportServersBuilder {
        InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list);
    }

    public static ServerBuilder<?> forPort(int port) {
        throw new UnsupportedOperationException("ClientTransportServersBuilder is required, use a constructor");
    }

    public ServerImplBuilder(ClientTransportServersBuilder clientTransportServersBuilder2) {
        this.clientTransportServersBuilder = (ClientTransportServersBuilder) Preconditions.checkNotNull(clientTransportServersBuilder2, "clientTransportServersBuilder");
    }

    public ServerImplBuilder directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    public ServerImplBuilder executor(@Nullable Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<>(executor) : DEFAULT_EXECUTOR_POOL;
        return this;
    }

    public ServerImplBuilder callExecutor(ServerCallExecutorSupplier executorSupplier2) {
        this.executorSupplier = (ServerCallExecutorSupplier) Preconditions.checkNotNull(executorSupplier2);
        return this;
    }

    public ServerImplBuilder addService(ServerServiceDefinition service) {
        this.registryBuilder.addService((ServerServiceDefinition) Preconditions.checkNotNull(service, NotificationCompat.CATEGORY_SERVICE));
        return this;
    }

    public ServerImplBuilder addService(BindableService bindableService) {
        return addService(((BindableService) Preconditions.checkNotNull(bindableService, "bindableService")).bindService());
    }

    public ServerImplBuilder addTransportFilter(ServerTransportFilter filter) {
        this.transportFilters.add((ServerTransportFilter) Preconditions.checkNotNull(filter, "filter"));
        return this;
    }

    public ServerImplBuilder intercept(ServerInterceptor interceptor) {
        this.interceptors.add((ServerInterceptor) Preconditions.checkNotNull(interceptor, "interceptor"));
        return this;
    }

    public ServerImplBuilder addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.streamTracerFactories.add((ServerStreamTracer.Factory) Preconditions.checkNotNull(factory, "factory"));
        return this;
    }

    public ServerImplBuilder fallbackHandlerRegistry(@Nullable HandlerRegistry registry) {
        this.fallbackRegistry = registry != null ? registry : DEFAULT_FALLBACK_REGISTRY;
        return this;
    }

    public ServerImplBuilder decompressorRegistry(@Nullable DecompressorRegistry registry) {
        this.decompressorRegistry = registry != null ? registry : DEFAULT_DECOMPRESSOR_REGISTRY;
        return this;
    }

    public ServerImplBuilder compressorRegistry(@Nullable CompressorRegistry registry) {
        this.compressorRegistry = registry != null ? registry : DEFAULT_COMPRESSOR_REGISTRY;
        return this;
    }

    public ServerImplBuilder handshakeTimeout(long timeout, TimeUnit unit) {
        Preconditions.checkArgument(timeout > 0, "handshake timeout is %s, but must be positive", timeout);
        this.handshakeTimeoutMillis = ((TimeUnit) Preconditions.checkNotNull(unit, "unit")).toMillis(timeout);
        return this;
    }

    public ServerImplBuilder setBinaryLog(@Nullable BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return this;
    }

    public void setStatsEnabled(boolean value) {
        this.statsEnabled = value;
    }

    public void setStatsRecordStartedRpcs(boolean value) {
        this.recordStartedRpcs = value;
    }

    public void setStatsRecordFinishedRpcs(boolean value) {
        this.recordFinishedRpcs = value;
    }

    public void setStatsRecordRealTimeMetrics(boolean value) {
        this.recordRealTimeMetrics = value;
    }

    public void setTracingEnabled(boolean value) {
        this.tracingEnabled = value;
    }

    public void setDeadlineTicker(Deadline.Ticker ticker2) {
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(ticker2, "ticker");
    }

    public Server build() {
        return new ServerImpl(this, this.clientTransportServersBuilder.buildClientTransportServers(getTracerFactories()), Context.ROOT);
    }

    /* access modifiers changed from: package-private */
    public List<? extends ServerStreamTracer.Factory> getTracerFactories() {
        ArrayList<ServerStreamTracer.Factory> tracerFactories = new ArrayList<>();
        if (this.statsEnabled) {
            ServerStreamTracer.Factory censusStatsTracerFactory = null;
            try {
                censusStatsTracerFactory = (ServerStreamTracer.Factory) Class.forName("io.grpc.census.InternalCensusStatsAccessor").getDeclaredMethod("getServerStreamTracerFactory", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE}).invoke((Object) null, new Object[]{Boolean.valueOf(this.recordStartedRpcs), Boolean.valueOf(this.recordFinishedRpcs), Boolean.valueOf(this.recordRealTimeMetrics)});
            } catch (ClassNotFoundException e) {
                log.log(Level.FINE, "Unable to apply census stats", e);
            } catch (NoSuchMethodException e2) {
                log.log(Level.FINE, "Unable to apply census stats", e2);
            } catch (IllegalAccessException e3) {
                log.log(Level.FINE, "Unable to apply census stats", e3);
            } catch (InvocationTargetException e4) {
                log.log(Level.FINE, "Unable to apply census stats", e4);
            }
            if (censusStatsTracerFactory != null) {
                tracerFactories.add(censusStatsTracerFactory);
            }
        }
        if (this.tracingEnabled) {
            ServerStreamTracer.Factory tracingStreamTracerFactory = null;
            try {
                tracingStreamTracerFactory = (ServerStreamTracer.Factory) Class.forName("io.grpc.census.InternalCensusTracingAccessor").getDeclaredMethod("getServerStreamTracerFactory", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (ClassNotFoundException e5) {
                log.log(Level.FINE, "Unable to apply census stats", e5);
            } catch (NoSuchMethodException e6) {
                log.log(Level.FINE, "Unable to apply census stats", e6);
            } catch (IllegalAccessException e7) {
                log.log(Level.FINE, "Unable to apply census stats", e7);
            } catch (InvocationTargetException e8) {
                log.log(Level.FINE, "Unable to apply census stats", e8);
            }
            if (tracingStreamTracerFactory != null) {
                tracerFactories.add(tracingStreamTracerFactory);
            }
        }
        tracerFactories.addAll(this.streamTracerFactories);
        tracerFactories.trimToSize();
        return Collections.unmodifiableList(tracerFactories);
    }

    public InternalChannelz getChannelz() {
        return this.channelz;
    }

    /* renamed from: io.grpc.internal.ServerImplBuilder$DefaultFallbackRegistry */
    private static final class DefaultFallbackRegistry extends HandlerRegistry {
        private DefaultFallbackRegistry() {
        }

        public List<ServerServiceDefinition> getServices() {
            return Collections.emptyList();
        }

        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
            return null;
        }
    }

    public ObjectPool<? extends Executor> getExecutorPool() {
        return this.executorPool;
    }

    public ServerImplBuilder useTransportSecurity(File certChain, File privateKey) {
        throw new UnsupportedOperationException("TLS not supported in ServerImplBuilder");
    }
}
