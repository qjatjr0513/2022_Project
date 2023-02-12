package p004io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: io.grpc.internal.Rescheduler */
final class Rescheduler {
    /* access modifiers changed from: private */
    public boolean enabled;
    /* access modifiers changed from: private */
    public long runAtNanos;
    /* access modifiers changed from: private */
    public final Runnable runnable;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public final Executor serializingExecutor;
    private final Stopwatch stopwatch;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> wakeUp;

    Rescheduler(Runnable r, Executor serializingExecutor2, ScheduledExecutorService scheduler2, Stopwatch stopwatch2) {
        this.runnable = r;
        this.serializingExecutor = serializingExecutor2;
        this.scheduler = scheduler2;
        this.stopwatch = stopwatch2;
        stopwatch2.start();
    }

    /* access modifiers changed from: package-private */
    public void reschedule(long delay, TimeUnit timeUnit) {
        long delayNanos = timeUnit.toNanos(delay);
        long newRunAtNanos = nanoTime() + delayNanos;
        this.enabled = true;
        if (newRunAtNanos - this.runAtNanos < 0 || this.wakeUp == null) {
            ScheduledFuture<?> scheduledFuture = this.wakeUp;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.wakeUp = this.scheduler.schedule(new FutureRunnable(), delayNanos, TimeUnit.NANOSECONDS);
        }
        this.runAtNanos = newRunAtNanos;
    }

    /* access modifiers changed from: package-private */
    public void cancel(boolean permanent) {
        ScheduledFuture<?> scheduledFuture;
        this.enabled = false;
        if (permanent && (scheduledFuture = this.wakeUp) != null) {
            scheduledFuture.cancel(false);
            this.wakeUp = null;
        }
    }

    /* renamed from: io.grpc.internal.Rescheduler$FutureRunnable */
    private final class FutureRunnable implements Runnable {
        private FutureRunnable() {
        }

        public void run() {
            Rescheduler.this.serializingExecutor.execute(new ChannelFutureRunnable());
        }

        /* access modifiers changed from: private */
        public boolean isEnabled() {
            return Rescheduler.this.enabled;
        }
    }

    /* renamed from: io.grpc.internal.Rescheduler$ChannelFutureRunnable */
    private final class ChannelFutureRunnable implements Runnable {
        private ChannelFutureRunnable() {
        }

        public void run() {
            if (!Rescheduler.this.enabled) {
                ScheduledFuture unused = Rescheduler.this.wakeUp = null;
                return;
            }
            long now = Rescheduler.this.nanoTime();
            if (Rescheduler.this.runAtNanos - now > 0) {
                Rescheduler rescheduler = Rescheduler.this;
                ScheduledFuture unused2 = rescheduler.wakeUp = rescheduler.scheduler.schedule(new FutureRunnable(), Rescheduler.this.runAtNanos - now, TimeUnit.NANOSECONDS);
                return;
            }
            boolean unused3 = Rescheduler.this.enabled = false;
            ScheduledFuture unused4 = Rescheduler.this.wakeUp = null;
            Rescheduler.this.runnable.run();
        }
    }

    static boolean isEnabled(Runnable r) {
        return ((FutureRunnable) r).isEnabled();
    }

    /* access modifiers changed from: private */
    public long nanoTime() {
        return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
    }
}
