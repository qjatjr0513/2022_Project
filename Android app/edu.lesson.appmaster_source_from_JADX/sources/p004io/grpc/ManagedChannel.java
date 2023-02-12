package p004io.grpc;

import java.util.concurrent.TimeUnit;

/* renamed from: io.grpc.ManagedChannel */
public abstract class ManagedChannel extends Channel {
    public abstract boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException;

    public abstract boolean isShutdown();

    public abstract boolean isTerminated();

    public abstract ManagedChannel shutdown();

    public abstract ManagedChannel shutdownNow();

    public ConnectivityState getState(boolean requestConnection) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void notifyWhenStateChanged(ConnectivityState source, Runnable callback) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void resetConnectBackoff() {
    }

    public void enterIdle() {
    }
}
