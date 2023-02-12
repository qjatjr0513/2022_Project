package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientTransport;

/* renamed from: io.grpc.internal.KeepAliveManager */
public class KeepAliveManager {
    private static final long MIN_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(10);
    private static final long MIN_KEEPALIVE_TIME_NANOS = TimeUnit.SECONDS.toNanos(10);
    private final boolean keepAliveDuringTransportIdle;
    /* access modifiers changed from: private */
    public final KeepAlivePinger keepAlivePinger;
    /* access modifiers changed from: private */
    public final long keepAliveTimeInNanos;
    /* access modifiers changed from: private */
    public final long keepAliveTimeoutInNanos;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> pingFuture;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public final Runnable sendPing;
    /* access modifiers changed from: private */
    public final Runnable shutdown;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> shutdownFuture;
    /* access modifiers changed from: private */
    public State state;
    /* access modifiers changed from: private */
    public final Stopwatch stopwatch;

    /* renamed from: io.grpc.internal.KeepAliveManager$KeepAlivePinger */
    public interface KeepAlivePinger {
        void onPingTimeout();

        void ping();
    }

    /* renamed from: io.grpc.internal.KeepAliveManager$State */
    private enum State {
        IDLE,
        PING_SCHEDULED,
        PING_DELAYED,
        PING_SENT,
        IDLE_AND_PING_SENT,
        DISCONNECTED
    }

    public KeepAliveManager(KeepAlivePinger keepAlivePinger2, ScheduledExecutorService scheduler2, long keepAliveTimeInNanos2, long keepAliveTimeoutInNanos2, boolean keepAliveDuringTransportIdle2) {
        this(keepAlivePinger2, scheduler2, Stopwatch.createUnstarted(), keepAliveTimeInNanos2, keepAliveTimeoutInNanos2, keepAliveDuringTransportIdle2);
    }

    KeepAliveManager(KeepAlivePinger keepAlivePinger2, ScheduledExecutorService scheduler2, Stopwatch stopwatch2, long keepAliveTimeInNanos2, long keepAliveTimeoutInNanos2, boolean keepAliveDuringTransportIdle2) {
        this.state = State.IDLE;
        this.shutdown = new LogExceptionRunnable(new Runnable() {
            public void run() {
                boolean shouldShutdown = false;
                synchronized (KeepAliveManager.this) {
                    if (KeepAliveManager.this.state != State.DISCONNECTED) {
                        State unused = KeepAliveManager.this.state = State.DISCONNECTED;
                        shouldShutdown = true;
                    }
                }
                if (shouldShutdown) {
                    KeepAliveManager.this.keepAlivePinger.onPingTimeout();
                }
            }
        });
        this.sendPing = new LogExceptionRunnable(new Runnable() {
            public void run() {
                boolean shouldSendPing = false;
                synchronized (KeepAliveManager.this) {
                    ScheduledFuture unused = KeepAliveManager.this.pingFuture = null;
                    if (KeepAliveManager.this.state == State.PING_SCHEDULED) {
                        shouldSendPing = true;
                        State unused2 = KeepAliveManager.this.state = State.PING_SENT;
                        KeepAliveManager keepAliveManager = KeepAliveManager.this;
                        ScheduledFuture unused3 = keepAliveManager.shutdownFuture = keepAliveManager.scheduler.schedule(KeepAliveManager.this.shutdown, KeepAliveManager.this.keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
                    } else if (KeepAliveManager.this.state == State.PING_DELAYED) {
                        KeepAliveManager keepAliveManager2 = KeepAliveManager.this;
                        ScheduledFuture unused4 = keepAliveManager2.pingFuture = keepAliveManager2.scheduler.schedule(KeepAliveManager.this.sendPing, KeepAliveManager.this.keepAliveTimeInNanos - KeepAliveManager.this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
                        State unused5 = KeepAliveManager.this.state = State.PING_SCHEDULED;
                    }
                }
                if (shouldSendPing) {
                    KeepAliveManager.this.keepAlivePinger.ping();
                }
            }
        });
        this.keepAlivePinger = (KeepAlivePinger) Preconditions.checkNotNull(keepAlivePinger2, "keepAlivePinger");
        this.scheduler = (ScheduledExecutorService) Preconditions.checkNotNull(scheduler2, "scheduler");
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch2, NotificationCompat.CATEGORY_STOPWATCH);
        this.keepAliveTimeInNanos = keepAliveTimeInNanos2;
        this.keepAliveTimeoutInNanos = keepAliveTimeoutInNanos2;
        this.keepAliveDuringTransportIdle = keepAliveDuringTransportIdle2;
        stopwatch2.reset().start();
    }

    public synchronized void onTransportStarted() {
        if (this.keepAliveDuringTransportIdle) {
            onTransportActive();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onDataReceived() {
        /*
            r5 = this;
            monitor-enter(r5)
            com.google.common.base.Stopwatch r0 = r5.stopwatch     // Catch:{ all -> 0x0053 }
            com.google.common.base.Stopwatch r0 = r0.reset()     // Catch:{ all -> 0x0053 }
            r0.start()     // Catch:{ all -> 0x0053 }
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0053 }
            io.grpc.internal.KeepAliveManager$State r1 = p004io.grpc.internal.KeepAliveManager.State.PING_SCHEDULED     // Catch:{ all -> 0x0053 }
            if (r0 != r1) goto L_0x0015
            io.grpc.internal.KeepAliveManager$State r0 = p004io.grpc.internal.KeepAliveManager.State.PING_DELAYED     // Catch:{ all -> 0x0053 }
            r5.state = r0     // Catch:{ all -> 0x0053 }
            goto L_0x0051
        L_0x0015:
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0053 }
            io.grpc.internal.KeepAliveManager$State r1 = p004io.grpc.internal.KeepAliveManager.State.PING_SENT     // Catch:{ all -> 0x0053 }
            if (r0 == r1) goto L_0x0021
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0053 }
            io.grpc.internal.KeepAliveManager$State r1 = p004io.grpc.internal.KeepAliveManager.State.IDLE_AND_PING_SENT     // Catch:{ all -> 0x0053 }
            if (r0 != r1) goto L_0x0051
        L_0x0021:
            java.util.concurrent.ScheduledFuture<?> r0 = r5.shutdownFuture     // Catch:{ all -> 0x0053 }
            r1 = 0
            if (r0 == 0) goto L_0x0029
            r0.cancel(r1)     // Catch:{ all -> 0x0053 }
        L_0x0029:
            io.grpc.internal.KeepAliveManager$State r0 = r5.state     // Catch:{ all -> 0x0053 }
            io.grpc.internal.KeepAliveManager$State r2 = p004io.grpc.internal.KeepAliveManager.State.IDLE_AND_PING_SENT     // Catch:{ all -> 0x0053 }
            if (r0 != r2) goto L_0x0035
            io.grpc.internal.KeepAliveManager$State r0 = p004io.grpc.internal.KeepAliveManager.State.IDLE     // Catch:{ all -> 0x0053 }
            r5.state = r0     // Catch:{ all -> 0x0053 }
            monitor-exit(r5)
            return
        L_0x0035:
            io.grpc.internal.KeepAliveManager$State r0 = p004io.grpc.internal.KeepAliveManager.State.PING_SCHEDULED     // Catch:{ all -> 0x0053 }
            r5.state = r0     // Catch:{ all -> 0x0053 }
            java.util.concurrent.ScheduledFuture<?> r0 = r5.pingFuture     // Catch:{ all -> 0x0053 }
            if (r0 != 0) goto L_0x003e
            r1 = 1
        L_0x003e:
            java.lang.String r0 = "There should be no outstanding pingFuture"
            com.google.common.base.Preconditions.checkState(r1, r0)     // Catch:{ all -> 0x0053 }
            java.util.concurrent.ScheduledExecutorService r0 = r5.scheduler     // Catch:{ all -> 0x0053 }
            java.lang.Runnable r1 = r5.sendPing     // Catch:{ all -> 0x0053 }
            long r2 = r5.keepAliveTimeInNanos     // Catch:{ all -> 0x0053 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x0053 }
            java.util.concurrent.ScheduledFuture r0 = r0.schedule(r1, r2, r4)     // Catch:{ all -> 0x0053 }
            r5.pingFuture = r0     // Catch:{ all -> 0x0053 }
        L_0x0051:
            monitor-exit(r5)
            return
        L_0x0053:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.KeepAliveManager.onDataReceived():void");
    }

    public synchronized void onTransportActive() {
        if (this.state == State.IDLE) {
            this.state = State.PING_SCHEDULED;
            if (this.pingFuture == null) {
                this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos - this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
            }
        } else if (this.state == State.IDLE_AND_PING_SENT) {
            this.state = State.PING_SENT;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onTransportIdle() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.keepAliveDuringTransportIdle     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            io.grpc.internal.KeepAliveManager$State r0 = r2.state     // Catch:{ all -> 0x0023 }
            io.grpc.internal.KeepAliveManager$State r1 = p004io.grpc.internal.KeepAliveManager.State.PING_SCHEDULED     // Catch:{ all -> 0x0023 }
            if (r0 == r1) goto L_0x0013
            io.grpc.internal.KeepAliveManager$State r0 = r2.state     // Catch:{ all -> 0x0023 }
            io.grpc.internal.KeepAliveManager$State r1 = p004io.grpc.internal.KeepAliveManager.State.PING_DELAYED     // Catch:{ all -> 0x0023 }
            if (r0 != r1) goto L_0x0017
        L_0x0013:
            io.grpc.internal.KeepAliveManager$State r0 = p004io.grpc.internal.KeepAliveManager.State.IDLE     // Catch:{ all -> 0x0023 }
            r2.state = r0     // Catch:{ all -> 0x0023 }
        L_0x0017:
            io.grpc.internal.KeepAliveManager$State r0 = r2.state     // Catch:{ all -> 0x0023 }
            io.grpc.internal.KeepAliveManager$State r1 = p004io.grpc.internal.KeepAliveManager.State.PING_SENT     // Catch:{ all -> 0x0023 }
            if (r0 != r1) goto L_0x0021
            io.grpc.internal.KeepAliveManager$State r0 = p004io.grpc.internal.KeepAliveManager.State.IDLE_AND_PING_SENT     // Catch:{ all -> 0x0023 }
            r2.state = r0     // Catch:{ all -> 0x0023 }
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.KeepAliveManager.onTransportIdle():void");
    }

    public synchronized void onTransportTermination() {
        if (this.state != State.DISCONNECTED) {
            this.state = State.DISCONNECTED;
            ScheduledFuture<?> scheduledFuture = this.shutdownFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledFuture<?> scheduledFuture2 = this.pingFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
                this.pingFuture = null;
            }
        }
    }

    public static long clampKeepAliveTimeInNanos(long keepAliveTimeInNanos2) {
        return Math.max(keepAliveTimeInNanos2, MIN_KEEPALIVE_TIME_NANOS);
    }

    public static long clampKeepAliveTimeoutInNanos(long keepAliveTimeoutInNanos2) {
        return Math.max(keepAliveTimeoutInNanos2, MIN_KEEPALIVE_TIMEOUT_NANOS);
    }

    /* renamed from: io.grpc.internal.KeepAliveManager$ClientKeepAlivePinger */
    public static final class ClientKeepAlivePinger implements KeepAlivePinger {
        /* access modifiers changed from: private */
        public final ConnectionClientTransport transport;

        public ClientKeepAlivePinger(ConnectionClientTransport transport2) {
            this.transport = transport2;
        }

        public void ping() {
            this.transport.ping(new ClientTransport.PingCallback() {
                public void onSuccess(long roundTripTimeNanos) {
                }

                public void onFailure(Throwable cause) {
                    ClientKeepAlivePinger.this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
                }
            }, MoreExecutors.directExecutor());
        }

        public void onPingTimeout() {
            this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
        }
    }
}
