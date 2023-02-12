package p004io.grpc.inprocess;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import p004io.grpc.Attributes;
import p004io.grpc.ServerStreamTracer;
import p004io.grpc.internal.ConnectionClientTransport;
import p004io.grpc.internal.ObjectPool;
import p004io.grpc.internal.ServerListener;

/* renamed from: io.grpc.inprocess.InternalInProcess */
public final class InternalInProcess {
    private InternalInProcess() {
    }

    public static ConnectionClientTransport createInProcessTransport(String name, int maxInboundMetadataSize, String authority, String userAgent, Attributes eagAttrs, ObjectPool<ScheduledExecutorService> serverSchedulerPool, List<ServerStreamTracer.Factory> serverStreamTracerFactories, ServerListener serverListener) {
        return new InProcessTransport(name, maxInboundMetadataSize, authority, userAgent, eagAttrs, serverSchedulerPool, serverStreamTracerFactories, serverListener);
    }
}
