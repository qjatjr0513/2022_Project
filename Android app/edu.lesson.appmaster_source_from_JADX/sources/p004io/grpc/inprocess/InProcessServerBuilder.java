package p004io.grpc.inprocess;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.Preconditions;
import java.io.File;
import java.net.SocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p004io.grpc.Deadline;
import p004io.grpc.ServerBuilder;
import p004io.grpc.ServerStreamTracer;
import p004io.grpc.internal.AbstractServerImplBuilder;
import p004io.grpc.internal.FixedObjectPool;
import p004io.grpc.internal.GrpcUtil;
import p004io.grpc.internal.InternalServer;
import p004io.grpc.internal.ObjectPool;
import p004io.grpc.internal.ServerImplBuilder;
import p004io.grpc.internal.SharedResourcePool;

/* renamed from: io.grpc.inprocess.InProcessServerBuilder */
public final class InProcessServerBuilder extends AbstractServerImplBuilder<InProcessServerBuilder> {
    final SocketAddress listenAddress;
    int maxInboundMetadataSize = Integer.MAX_VALUE;
    ObjectPool<ScheduledExecutorService> schedulerPool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
    private final ServerImplBuilder serverImplBuilder;

    public static InProcessServerBuilder forName(String name) {
        return forAddress(new InProcessSocketAddress((String) Preconditions.checkNotNull(name, AppMeasurementSdk.ConditionalUserProperty.NAME)));
    }

    public static InProcessServerBuilder forAddress(SocketAddress listenAddress2) {
        return new InProcessServerBuilder(listenAddress2);
    }

    public static InProcessServerBuilder forPort(int port) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static String generateName() {
        return UUID.randomUUID().toString();
    }

    private InProcessServerBuilder(SocketAddress listenAddress2) {
        this.listenAddress = (SocketAddress) Preconditions.checkNotNull(listenAddress2, "listenAddress");
        ServerImplBuilder serverImplBuilder2 = new ServerImplBuilder(new ServerImplBuilder.ClientTransportServersBuilder() {
            public InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> streamTracerFactories) {
                return InProcessServerBuilder.this.buildTransportServers(streamTracerFactories);
            }
        });
        this.serverImplBuilder = serverImplBuilder2;
        serverImplBuilder2.setStatsRecordStartedRpcs(false);
        serverImplBuilder2.setStatsRecordFinishedRpcs(false);
        handshakeTimeout(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: protected */
    public ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    public InProcessServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.schedulerPool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public InProcessServerBuilder deadlineTicker(Deadline.Ticker ticker) {
        this.serverImplBuilder.setDeadlineTicker(ticker);
        return this;
    }

    public InProcessServerBuilder maxInboundMetadataSize(int bytes) {
        Preconditions.checkArgument(bytes > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = bytes;
        return this;
    }

    /* access modifiers changed from: package-private */
    public InProcessServer buildTransportServers(List<? extends ServerStreamTracer.Factory> streamTracerFactories) {
        return new InProcessServer(this, streamTracerFactories);
    }

    public InProcessServerBuilder useTransportSecurity(File certChain, File privateKey) {
        throw new UnsupportedOperationException("TLS not supported in InProcessServer");
    }

    /* access modifiers changed from: package-private */
    public void setStatsEnabled(boolean value) {
        this.serverImplBuilder.setStatsEnabled(value);
    }
}
