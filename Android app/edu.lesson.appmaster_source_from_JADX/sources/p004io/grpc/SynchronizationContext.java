package p004io.grpc;

import com.google.common.base.Preconditions;
import java.lang.Thread;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: io.grpc.SynchronizationContext */
public final class SynchronizationContext implements Executor {
    private final AtomicReference<Thread> drainingThread = new AtomicReference<>();
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue();
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public SynchronizationContext(Thread.UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler2, "uncaughtExceptionHandler");
    }

    public final void drain() {
        while (this.drainingThread.compareAndSet((Object) null, Thread.currentThread())) {
            while (true) {
                try {
                    Runnable poll = this.queue.poll();
                    Runnable runnable = poll;
                    if (poll != null) {
                        runnable.run();
                    } else {
                        this.drainingThread.set((Object) null);
                        if (this.queue.isEmpty()) {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    this.drainingThread.set((Object) null);
                    throw th;
                }
            }
        }
    }

    public final void executeLater(Runnable runnable) {
        this.queue.add((Runnable) Preconditions.checkNotNull(runnable, "runnable is null"));
    }

    public final void execute(Runnable task) {
        executeLater(task);
        drain();
    }

    public void throwIfNotInThisSynchronizationContext() {
        Preconditions.checkState(Thread.currentThread() == this.drainingThread.get(), "Not called from the SynchronizationContext");
    }

    public final ScheduledHandle schedule(final Runnable task, long delay, TimeUnit unit, ScheduledExecutorService timerService) {
        final ManagedRunnable runnable = new ManagedRunnable(task);
        return new ScheduledHandle(runnable, timerService.schedule(new Runnable() {
            public void run() {
                SynchronizationContext.this.execute(runnable);
            }

            public String toString() {
                return task.toString() + "(scheduled in SynchronizationContext)";
            }
        }, delay, unit));
    }

    /* renamed from: io.grpc.SynchronizationContext$ManagedRunnable */
    private static class ManagedRunnable implements Runnable {
        boolean hasStarted;
        boolean isCancelled;
        final Runnable task;

        ManagedRunnable(Runnable task2) {
            this.task = (Runnable) Preconditions.checkNotNull(task2, "task");
        }

        public void run() {
            if (!this.isCancelled) {
                this.hasStarted = true;
                this.task.run();
            }
        }
    }

    /* renamed from: io.grpc.SynchronizationContext$ScheduledHandle */
    public static final class ScheduledHandle {
        private final ScheduledFuture<?> future;
        private final ManagedRunnable runnable;

        private ScheduledHandle(ManagedRunnable runnable2, ScheduledFuture<?> future2) {
            this.runnable = (ManagedRunnable) Preconditions.checkNotNull(runnable2, "runnable");
            this.future = (ScheduledFuture) Preconditions.checkNotNull(future2, "future");
        }

        public void cancel() {
            this.runnable.isCancelled = true;
            this.future.cancel(false);
        }

        public boolean isPending() {
            return !this.runnable.hasStarted && !this.runnable.isCancelled;
        }
    }
}
