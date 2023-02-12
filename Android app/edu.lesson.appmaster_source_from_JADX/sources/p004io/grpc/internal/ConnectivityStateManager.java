package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import p004io.grpc.ConnectivityState;

/* renamed from: io.grpc.internal.ConnectivityStateManager */
final class ConnectivityStateManager {
    private ArrayList<Listener> listeners = new ArrayList<>();
    private volatile ConnectivityState state = ConnectivityState.IDLE;

    ConnectivityStateManager() {
    }

    /* access modifiers changed from: package-private */
    public void notifyWhenStateChanged(Runnable callback, Executor executor, ConnectivityState source) {
        Preconditions.checkNotNull(callback, "callback");
        Preconditions.checkNotNull(executor, "executor");
        Preconditions.checkNotNull(source, "source");
        Listener stateChangeListener = new Listener(callback, executor);
        if (this.state != source) {
            stateChangeListener.runInExecutor();
        } else {
            this.listeners.add(stateChangeListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void gotoState(@Nonnull ConnectivityState newState) {
        Preconditions.checkNotNull(newState, "newState");
        if (this.state != newState && this.state != ConnectivityState.SHUTDOWN) {
            this.state = newState;
            if (!this.listeners.isEmpty()) {
                ArrayList<Listener> savedListeners = this.listeners;
                this.listeners = new ArrayList<>();
                Iterator<Listener> it = savedListeners.iterator();
                while (it.hasNext()) {
                    it.next().runInExecutor();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ConnectivityState getState() {
        ConnectivityState stateCopy = this.state;
        if (stateCopy != null) {
            return stateCopy;
        }
        throw new UnsupportedOperationException("Channel state API is not implemented");
    }

    /* renamed from: io.grpc.internal.ConnectivityStateManager$Listener */
    private static final class Listener {
        final Runnable callback;
        final Executor executor;

        Listener(Runnable callback2, Executor executor2) {
            this.callback = callback2;
            this.executor = executor2;
        }

        /* access modifiers changed from: package-private */
        public void runInExecutor() {
            this.executor.execute(this.callback);
        }
    }
}
