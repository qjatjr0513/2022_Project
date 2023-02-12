package p004io.grpc.internal;

import p004io.grpc.Context;

/* renamed from: io.grpc.internal.ContextRunnable */
abstract class ContextRunnable implements Runnable {
    private final Context context;

    public abstract void runInContext();

    protected ContextRunnable(Context context2) {
        this.context = context2;
    }

    public final void run() {
        Context previous = this.context.attach();
        try {
            runInContext();
        } finally {
            this.context.detach(previous);
        }
    }
}
