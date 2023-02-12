package p004io.grpc;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p004io.grpc.Context;

/* renamed from: io.grpc.Server */
public abstract class Server {
    static final Context.Key<Server> SERVER_CONTEXT_KEY = Context.key("io.grpc.Server");

    public abstract void awaitTermination() throws InterruptedException;

    public abstract boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException;

    public abstract boolean isShutdown();

    public abstract boolean isTerminated();

    public abstract Server shutdown();

    public abstract Server shutdownNow();

    public abstract Server start() throws IOException;

    public int getPort() {
        return -1;
    }

    public List<? extends SocketAddress> getListenSockets() {
        throw new UnsupportedOperationException();
    }

    public List<ServerServiceDefinition> getServices() {
        return Collections.emptyList();
    }

    public List<ServerServiceDefinition> getImmutableServices() {
        return Collections.emptyList();
    }

    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.emptyList();
    }
}
