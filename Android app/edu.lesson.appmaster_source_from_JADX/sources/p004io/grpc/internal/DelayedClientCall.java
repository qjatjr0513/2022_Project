package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.ClientCall;
import p004io.grpc.Context;
import p004io.grpc.Deadline;
import p004io.grpc.Metadata;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.DelayedClientCall */
public class DelayedClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void start(ClientCall.Listener<Object> listener, Metadata headers) {
        }

        public void request(int numMessages) {
        }

        public void cancel(String message, Throwable cause) {
        }

        public void halfClose() {
        }

        public void sendMessage(Object message) {
        }

        public boolean isReady() {
            return false;
        }
    };
    private static final Logger logger = Logger.getLogger(DelayedClientCall.class.getName());
    private final Executor callExecutor;
    /* access modifiers changed from: private */
    public final Context context;
    private DelayedListener<RespT> delayedListener;
    private Status error;
    @Nullable
    private final ScheduledFuture<?> initialDeadlineMonitor;
    private ClientCall.Listener<RespT> listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingRunnables = new ArrayList();
    /* access modifiers changed from: private */
    public ClientCall<ReqT, RespT> realCall;

    protected DelayedClientCall(Executor callExecutor2, ScheduledExecutorService scheduler, @Nullable Deadline deadline) {
        this.callExecutor = (Executor) Preconditions.checkNotNull(callExecutor2, "callExecutor");
        Preconditions.checkNotNull(scheduler, "scheduler");
        this.context = Context.current();
        this.initialDeadlineMonitor = scheduleDeadlineIfNeeded(scheduler, deadline);
    }

    @Nullable
    private ScheduledFuture<?> scheduleDeadlineIfNeeded(ScheduledExecutorService scheduler, @Nullable Deadline deadline) {
        Deadline contextDeadline = this.context.getDeadline();
        if (deadline == null && contextDeadline == null) {
            return null;
        }
        long remainingNanos = Long.MAX_VALUE;
        if (deadline != null) {
            remainingNanos = Math.min(Long.MAX_VALUE, deadline.timeRemaining(TimeUnit.NANOSECONDS));
        }
        if (contextDeadline != null && contextDeadline.timeRemaining(TimeUnit.NANOSECONDS) < remainingNanos) {
            remainingNanos = contextDeadline.timeRemaining(TimeUnit.NANOSECONDS);
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                StringBuilder builder = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", new Object[]{Long.valueOf(remainingNanos)}));
                if (deadline == null) {
                    builder.append(" Explicit call timeout was not set.");
                } else {
                    builder.append(String.format(" Explicit call timeout was '%d' ns.", new Object[]{Long.valueOf(deadline.timeRemaining(TimeUnit.NANOSECONDS))}));
                }
                logger2.fine(builder.toString());
            }
        }
        long seconds = Math.abs(remainingNanos) / TimeUnit.SECONDS.toNanos(1);
        long nanos = Math.abs(remainingNanos) % TimeUnit.SECONDS.toNanos(1);
        final StringBuilder buf = new StringBuilder();
        if (remainingNanos < 0) {
            buf.append("ClientCall started after deadline exceeded. Deadline exceeded after -");
        } else {
            buf.append("Deadline exceeded after ");
        }
        buf.append(seconds);
        buf.append(String.format(Locale.US, ".%09d", new Object[]{Long.valueOf(nanos)}));
        buf.append("s. ");
        return scheduler.schedule(new Runnable() {
            public void run() {
                DelayedClientCall.this.cancel(Status.DEADLINE_EXCEEDED.withDescription(buf.toString()), true);
            }
        }, remainingNanos, TimeUnit.NANOSECONDS);
    }

    public final void setCall(ClientCall<ReqT, RespT> call) {
        synchronized (this) {
            if (this.realCall == null) {
                setRealCall((ClientCall) Preconditions.checkNotNull(call, NotificationCompat.CATEGORY_CALL));
                drainPendingCalls();
            }
        }
    }

    public final void start(ClientCall.Listener<RespT> listener2, final Metadata headers) {
        Status savedError;
        boolean savedPassThrough;
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            this.listener = (ClientCall.Listener) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            savedError = this.error;
            savedPassThrough = this.passThrough;
            if (!savedPassThrough) {
                DelayedListener<RespT> delayedListener2 = new DelayedListener<>(listener2);
                this.delayedListener = delayedListener2;
                listener2 = delayedListener2;
            }
        }
        if (savedError != null) {
            this.callExecutor.execute(new CloseListenerRunnable(listener2, savedError));
        } else if (savedPassThrough) {
            this.realCall.start(listener2, headers);
        } else {
            final ClientCall.Listener<RespT> finalListener = listener2;
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.start(finalListener, headers);
                }
            });
        }
    }

    public final void cancel(@Nullable String message, @Nullable Throwable cause) {
        Status status;
        Status status2 = Status.CANCELLED;
        if (message != null) {
            status = status2.withDescription(message);
        } else {
            status = status2.withDescription("Call cancelled without message");
        }
        if (cause != null) {
            status = status.withCause(cause);
        }
        cancel(status, false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r0 == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        delayOrExecute(new p004io.grpc.internal.DelayedClientCall.C12002(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r1 == null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        r4.callExecutor.execute(new p004io.grpc.internal.DelayedClientCall.CloseListenerRunnable(r4, r1, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        drainPendingCalls();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        callCancelled();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel(final p004io.grpc.Status r5, boolean r6) {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            monitor-enter(r4)
            io.grpc.ClientCall<ReqT, RespT> r2 = r4.realCall     // Catch:{ all -> 0x0037 }
            if (r2 != 0) goto L_0x0013
            io.grpc.ClientCall<java.lang.Object, java.lang.Object> r2 = NOOP_CALL     // Catch:{ all -> 0x0037 }
            r4.setRealCall(r2)     // Catch:{ all -> 0x0037 }
            r0 = 0
            io.grpc.ClientCall$Listener<RespT> r3 = r4.listener     // Catch:{ all -> 0x0037 }
            r1 = r3
            r4.error = r5     // Catch:{ all -> 0x0037 }
            goto L_0x0017
        L_0x0013:
            if (r6 == 0) goto L_0x0017
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            return
        L_0x0017:
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0024
            io.grpc.internal.DelayedClientCall$2 r2 = new io.grpc.internal.DelayedClientCall$2
            r2.<init>(r5)
            r4.delayOrExecute(r2)
            goto L_0x0033
        L_0x0024:
            if (r1 == 0) goto L_0x0030
            java.util.concurrent.Executor r2 = r4.callExecutor
            io.grpc.internal.DelayedClientCall$CloseListenerRunnable r3 = new io.grpc.internal.DelayedClientCall$CloseListenerRunnable
            r3.<init>(r1, r5)
            r2.execute(r3)
        L_0x0030:
            r4.drainPendingCalls()
        L_0x0033:
            r4.callCancelled()
            return
        L_0x0037:
            r2 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedClientCall.cancel(io.grpc.Status, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void callCancelled() {
    }

    private void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        r2 = r1;
        r5.callExecutor.execute(new p004io.grpc.internal.DelayedClientCall.AnonymousClass1DrainListenerRunnable(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        r1 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        if (r1.hasNext() == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        r1.next().run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drainPendingCalls() {
        /*
            r5 = this;
            io.grpc.ClientCall<ReqT, RespT> r0 = r5.realCall
            if (r0 == 0) goto L_0x0055
            boolean r0 = r5.passThrough
            if (r0 != 0) goto L_0x004f
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x000d:
            monitor-enter(r5)
            java.util.List<java.lang.Runnable> r1 = r5.pendingRunnables     // Catch:{ all -> 0x004c }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x002d
            r1 = 0
            r5.pendingRunnables = r1     // Catch:{ all -> 0x004c }
            r1 = 1
            r5.passThrough = r1     // Catch:{ all -> 0x004c }
            io.grpc.internal.DelayedClientCall$DelayedListener<RespT> r1 = r5.delayedListener     // Catch:{ all -> 0x004c }
            monitor-exit(r5)     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x002c
            r2 = r1
            java.util.concurrent.Executor r3 = r5.callExecutor
            io.grpc.internal.DelayedClientCall$1DrainListenerRunnable r4 = new io.grpc.internal.DelayedClientCall$1DrainListenerRunnable
            r4.<init>(r2)
            r3.execute(r4)
        L_0x002c:
            return
        L_0x002d:
            r1 = r0
            java.util.List<java.lang.Runnable> r2 = r5.pendingRunnables     // Catch:{ all -> 0x004c }
            r0 = r2
            r5.pendingRunnables = r1     // Catch:{ all -> 0x004c }
            monitor-exit(r5)     // Catch:{ all -> 0x004c }
            java.util.Iterator r1 = r0.iterator()
        L_0x0038:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0048
            java.lang.Object r2 = r1.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L_0x0038
        L_0x0048:
            r0.clear()
            goto L_0x000d
        L_0x004c:
            r1 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x004c }
            throw r1
        L_0x004f:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x0055:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedClientCall.drainPendingCalls():void");
    }

    private void setRealCall(ClientCall<ReqT, RespT> realCall2) {
        ClientCall<ReqT, RespT> clientCall = this.realCall;
        Preconditions.checkState(clientCall == null, "realCall already set to %s", (Object) clientCall);
        ScheduledFuture<?> scheduledFuture = this.initialDeadlineMonitor;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.realCall = realCall2;
    }

    /* access modifiers changed from: package-private */
    public final ClientCall<ReqT, RespT> getRealCall() {
        return this.realCall;
    }

    public final void sendMessage(final ReqT message) {
        if (this.passThrough) {
            this.realCall.sendMessage(message);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.sendMessage(message);
                }
            });
        }
    }

    public final void setMessageCompression(final boolean enable) {
        if (this.passThrough) {
            this.realCall.setMessageCompression(enable);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.setMessageCompression(enable);
                }
            });
        }
    }

    public final void request(final int numMessages) {
        if (this.passThrough) {
            this.realCall.request(numMessages);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.request(numMessages);
                }
            });
        }
    }

    public final void halfClose() {
        delayOrExecute(new Runnable() {
            public void run() {
                DelayedClientCall.this.realCall.halfClose();
            }
        });
    }

    public final boolean isReady() {
        if (this.passThrough) {
            return this.realCall.isReady();
        }
        return false;
    }

    public final Attributes getAttributes() {
        ClientCall<ReqT, RespT> savedRealCall;
        synchronized (this) {
            savedRealCall = this.realCall;
        }
        if (savedRealCall != null) {
            return savedRealCall.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("realCall", (Object) this.realCall).toString();
    }

    /* renamed from: io.grpc.internal.DelayedClientCall$CloseListenerRunnable */
    private final class CloseListenerRunnable extends ContextRunnable {
        final ClientCall.Listener<RespT> listener;
        final Status status;

        CloseListenerRunnable(ClientCall.Listener<RespT> listener2, Status status2) {
            super(DelayedClientCall.this.context);
            this.listener = listener2;
            this.status = status2;
        }

        public void runInContext() {
            this.listener.onClose(this.status, new Metadata());
        }
    }

    /* renamed from: io.grpc.internal.DelayedClientCall$DelayedListener */
    private static final class DelayedListener<RespT> extends ClientCall.Listener<RespT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        /* access modifiers changed from: private */
        public final ClientCall.Listener<RespT> realListener;

        static {
            Class<DelayedClientCall> cls = DelayedClientCall.class;
        }

        public DelayedListener(ClientCall.Listener<RespT> listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        public void onHeaders(final Metadata headers) {
            if (this.passThrough) {
                this.realListener.onHeaders(headers);
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedListener.this.realListener.onHeaders(headers);
                    }
                });
            }
        }

        public void onMessage(final RespT message) {
            if (this.passThrough) {
                this.realListener.onMessage(message);
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedListener.this.realListener.onMessage(message);
                    }
                });
            }
        }

        public void onClose(final Status status, final Metadata trailers) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedListener.this.realListener.onClose(status, trailers);
                }
            });
        }

        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedListener.this.realListener.onReady();
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
            r1 = r0.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            if (r1.hasNext() == false) goto L_0x0035;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
            r1.next().run();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainPendingCallbacks() {
            /*
                r3 = this;
                boolean r0 = r3.passThrough
                if (r0 != 0) goto L_0x003c
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
            L_0x0009:
                monitor-enter(r3)
                java.util.List<java.lang.Runnable> r1 = r3.pendingCallbacks     // Catch:{ all -> 0x0039 }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0039 }
                if (r1 == 0) goto L_0x001a
                r1 = 0
                r3.pendingCallbacks = r1     // Catch:{ all -> 0x0039 }
                r1 = 1
                r3.passThrough = r1     // Catch:{ all -> 0x0039 }
                monitor-exit(r3)     // Catch:{ all -> 0x0039 }
                return
            L_0x001a:
                r1 = r0
                java.util.List<java.lang.Runnable> r2 = r3.pendingCallbacks     // Catch:{ all -> 0x0039 }
                r0 = r2
                r3.pendingCallbacks = r1     // Catch:{ all -> 0x0039 }
                monitor-exit(r3)     // Catch:{ all -> 0x0039 }
                java.util.Iterator r1 = r0.iterator()
            L_0x0025:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x0035
                java.lang.Object r2 = r1.next()
                java.lang.Runnable r2 = (java.lang.Runnable) r2
                r2.run()
                goto L_0x0025
            L_0x0035:
                r0.clear()
                goto L_0x0009
            L_0x0039:
                r1 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0039 }
                throw r1
            L_0x003c:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedClientCall.DelayedListener.drainPendingCallbacks():void");
        }
    }
}
