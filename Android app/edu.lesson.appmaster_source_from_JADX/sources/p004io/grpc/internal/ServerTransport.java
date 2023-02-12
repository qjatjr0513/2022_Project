package p004io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalInstrumented;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.ServerTransport */
public interface ServerTransport extends InternalInstrumented<InternalChannelz.SocketStats> {
    ScheduledExecutorService getScheduledExecutorService();

    void shutdown();

    void shutdownNow(Status status);
}
