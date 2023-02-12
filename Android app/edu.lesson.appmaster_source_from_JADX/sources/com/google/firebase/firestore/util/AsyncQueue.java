package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;

public class AsyncQueue {
    private final ArrayList<DelayedTask> delayedTasks = new ArrayList<>();
    /* access modifiers changed from: private */
    public final SynchronizedShutdownAwareExecutor executor = new SynchronizedShutdownAwareExecutor();
    private final ArrayList<TimerId> timerIdsToSkip = new ArrayList<>();

    public enum TimerId {
        ALL,
        LISTEN_STREAM_IDLE,
        LISTEN_STREAM_CONNECTION_BACKOFF,
        WRITE_STREAM_IDLE,
        WRITE_STREAM_CONNECTION_BACKOFF,
        HEALTH_CHECK_TIMEOUT,
        ONLINE_STATE_TIMEOUT,
        GARBAGE_COLLECTION,
        RETRY_TRANSACTION,
        CONNECTIVITY_ATTEMPT_TIMER,
        INDEX_BACKFILL
    }

    public class DelayedTask {
        private ScheduledFuture scheduledFuture;
        /* access modifiers changed from: private */
        public final long targetTimeMs;
        private final Runnable task;
        /* access modifiers changed from: private */
        public final TimerId timerId;

        private DelayedTask(TimerId timerId2, long targetTimeMs2, Runnable task2) {
            this.timerId = timerId2;
            this.targetTimeMs = targetTimeMs2;
            this.task = task2;
        }

        /* access modifiers changed from: private */
        public void start(long delayMs) {
            this.scheduledFuture = AsyncQueue.this.executor.schedule(new AsyncQueue$DelayedTask$$ExternalSyntheticLambda0(this), delayMs, TimeUnit.MILLISECONDS);
        }

        /* access modifiers changed from: package-private */
        public void skipDelay() {
            handleDelayElapsed();
        }

        public void cancel() {
            AsyncQueue.this.verifyIsCurrentThread();
            ScheduledFuture scheduledFuture2 = this.scheduledFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
                markDone();
            }
        }

        /* access modifiers changed from: private */
        public void handleDelayElapsed() {
            AsyncQueue.this.verifyIsCurrentThread();
            if (this.scheduledFuture != null) {
                markDone();
                this.task.run();
            }
        }

        private void markDone() {
            Assert.hardAssert(this.scheduledFuture != null, "Caller should have verified scheduledFuture is non-null.", new Object[0]);
            this.scheduledFuture = null;
            AsyncQueue.this.removeDelayedTask(this);
        }
    }

    public static <TResult> Task<TResult> callTask(Executor executor2, Callable<Task<TResult>> task) {
        TaskCompletionSource<TResult> tcs = new TaskCompletionSource<>();
        executor2.execute(new AsyncQueue$$ExternalSyntheticLambda4(task, executor2, tcs));
        return tcs.getTask();
    }

    static /* synthetic */ void lambda$callTask$1(Callable task, Executor executor2, TaskCompletionSource tcs) {
        try {
            ((Task) task.call()).continueWith(executor2, new AsyncQueue$$ExternalSyntheticLambda0(tcs));
        } catch (Exception e) {
            tcs.setException(e);
        } catch (Throwable t) {
            tcs.setException(new IllegalStateException("Unhandled throwable in callTask.", t));
        }
    }

    static /* synthetic */ Void lambda$callTask$0(TaskCompletionSource tcs, Task task1) throws Exception {
        if (task1.isSuccessful()) {
            tcs.setResult(task1.getResult());
            return null;
        }
        tcs.setException(task1.getException());
        return null;
    }

    private class SynchronizedShutdownAwareExecutor implements Executor {
        private final ScheduledThreadPoolExecutor internalExecutor;
        private boolean isShuttingDown = false;
        /* access modifiers changed from: private */
        public final Thread thread;

        private class DelayedStartFactory implements Runnable, ThreadFactory {
            private Runnable delegate;
            private final CountDownLatch latch;

            private DelayedStartFactory() {
                this.latch = new CountDownLatch(1);
            }

            public void run() {
                try {
                    this.latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                this.delegate.run();
            }

            public Thread newThread(Runnable runnable) {
                Assert.hardAssert(this.delegate == null, "Only one thread may be created in an AsyncQueue.", new Object[0]);
                this.delegate = runnable;
                this.latch.countDown();
                return SynchronizedShutdownAwareExecutor.this.thread;
            }
        }

        SynchronizedShutdownAwareExecutor() {
            DelayedStartFactory threadFactory = new DelayedStartFactory();
            Thread newThread = Executors.defaultThreadFactory().newThread(threadFactory);
            this.thread = newThread;
            newThread.setName("FirestoreWorker");
            newThread.setDaemon(true);
            newThread.setUncaughtExceptionHandler(new C0800x9ce35fc8(this));
            C07981 r1 = new ScheduledThreadPoolExecutor(1, threadFactory, AsyncQueue.this) {
                /* access modifiers changed from: protected */
                public void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r, t);
                    if (t == null && (r instanceof Future)) {
                        Future<?> future = (Future) r;
                        try {
                            if (future.isDone()) {
                                future.get();
                            }
                        } catch (CancellationException e) {
                        } catch (ExecutionException ee) {
                            t = ee.getCause();
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (t != null) {
                        AsyncQueue.this.panic(t);
                    }
                }
            };
            this.internalExecutor = r1;
            r1.setKeepAliveTime(3, TimeUnit.SECONDS);
        }

        /* renamed from: lambda$new$0$com-google-firebase-firestore-util-AsyncQueue$SynchronizedShutdownAwareExecutor */
        public /* synthetic */ void mo9965x23fada6e(Thread crashingThread, Throwable throwable) {
            AsyncQueue.this.panic(throwable);
        }

        /* access modifiers changed from: private */
        public synchronized boolean isShuttingDown() {
            return this.isShuttingDown;
        }

        public synchronized void execute(Runnable command) {
            if (!this.isShuttingDown) {
                this.internalExecutor.execute(command);
            }
        }

        public void executeEvenAfterShutdown(Runnable command) {
            try {
                this.internalExecutor.execute(command);
            } catch (RejectedExecutionException e) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
        }

        /* access modifiers changed from: private */
        public <T> Task<T> executeAndReportResult(Callable<T> task) {
            TaskCompletionSource<T> completionSource = new TaskCompletionSource<>();
            try {
                execute(new C0799x9ce35fc7(completionSource, task));
            } catch (RejectedExecutionException e) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
            return completionSource.getTask();
        }

        static /* synthetic */ void lambda$executeAndReportResult$1(TaskCompletionSource completionSource, Callable task) {
            try {
                completionSource.setResult(task.call());
            } catch (Exception e) {
                completionSource.setException(e);
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public synchronized Task<Void> executeAndInitiateShutdown(Runnable task) {
            if (isShuttingDown()) {
                TaskCompletionSource<Void> source = new TaskCompletionSource<>();
                source.setResult(null);
                return source.getTask();
            }
            Task<Void> t = executeAndReportResult(new C0801x9ce35fc9(task));
            this.isShuttingDown = true;
            return t;
        }

        /* access modifiers changed from: private */
        public synchronized ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            if (this.isShuttingDown) {
                return null;
            }
            return this.internalExecutor.schedule(command, delay, unit);
        }

        /* access modifiers changed from: private */
        public void shutdownNow() {
            this.internalExecutor.shutdownNow();
        }

        /* access modifiers changed from: private */
        public void setCorePoolSize(int size) {
            this.internalExecutor.setCorePoolSize(size);
        }
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void verifyIsCurrentThread() {
        Thread current = Thread.currentThread();
        if (this.executor.thread != current) {
            throw Assert.fail("We are running on the wrong thread. Expected to be on the AsyncQueue thread %s/%d but was %s/%d", this.executor.thread.getName(), Long.valueOf(this.executor.thread.getId()), current.getName(), Long.valueOf(current.getId()));
        }
    }

    @CheckReturnValue
    public <T> Task<T> enqueue(Callable<T> task) {
        return this.executor.executeAndReportResult(task);
    }

    @CheckReturnValue
    public Task<Void> enqueue(Runnable task) {
        return enqueue(new AsyncQueue$$ExternalSyntheticLambda6(task));
    }

    public Task<Void> enqueueAndInitiateShutdown(Runnable task) {
        return this.executor.executeAndInitiateShutdown(task);
    }

    public void enqueueAndForgetEvenAfterShutdown(Runnable task) {
        this.executor.executeEvenAfterShutdown(task);
    }

    public boolean isShuttingDown() {
        return this.executor.isShuttingDown();
    }

    public void enqueueAndForget(Runnable task) {
        enqueue(task);
    }

    public DelayedTask enqueueAfterDelay(TimerId timerId, long delayMs, Runnable task) {
        if (this.timerIdsToSkip.contains(timerId)) {
            delayMs = 0;
        }
        DelayedTask delayedTask = createAndScheduleDelayedTask(timerId, delayMs, task);
        this.delayedTasks.add(delayedTask);
        return delayedTask;
    }

    public void skipDelaysForTimerId(TimerId timerId) {
        this.timerIdsToSkip.add(timerId);
    }

    public void panic(Throwable t) {
        this.executor.shutdownNow();
        new Handler(Looper.getMainLooper()).post(new AsyncQueue$$ExternalSyntheticLambda3(t));
    }

    static /* synthetic */ void lambda$panic$3(Throwable t) {
        if (t instanceof OutOfMemoryError) {
            OutOfMemoryError error = new OutOfMemoryError("Firestore (24.1.0) ran out of memory. Check your queries to make sure they are not loading an excessive amount of data.");
            error.initCause(t);
            throw error;
        }
        throw new RuntimeException("Internal error in Cloud Firestore (24.1.0).", t);
    }

    public void runSync(Runnable task) throws InterruptedException {
        Semaphore done = new Semaphore(0);
        Throwable[] t = new Throwable[1];
        enqueueAndForget(new AsyncQueue$$ExternalSyntheticLambda2(task, t, done));
        done.acquire(1);
        if (t[0] != null) {
            throw new RuntimeException("Synchronous task failed", t[0]);
        }
    }

    static /* synthetic */ void lambda$runSync$4(Runnable task, Throwable[] t, Semaphore done) {
        try {
            task.run();
        } catch (Throwable throwable) {
            t[0] = throwable;
        }
        done.release();
    }

    public boolean containsDelayedTask(TimerId timerId) {
        Iterator<DelayedTask> it = this.delayedTasks.iterator();
        while (it.hasNext()) {
            if (it.next().timerId == timerId) {
                return true;
            }
        }
        return false;
    }

    public void runDelayedTasksUntil(TimerId lastTimerId) throws InterruptedException {
        runSync(new AsyncQueue$$ExternalSyntheticLambda1(this, lastTimerId));
    }

    /* renamed from: lambda$runDelayedTasksUntil$6$com-google-firebase-firestore-util-AsyncQueue */
    public /* synthetic */ void mo9954xc3727432(TimerId lastTimerId) {
        Assert.hardAssert(lastTimerId == TimerId.ALL || containsDelayedTask(lastTimerId), "Attempted to run tasks until missing TimerId: %s", lastTimerId);
        Collections.sort(this.delayedTasks, AsyncQueue$$ExternalSyntheticLambda5.INSTANCE);
        Iterator it = new ArrayList(this.delayedTasks).iterator();
        while (it.hasNext()) {
            DelayedTask delayedTask = (DelayedTask) it.next();
            delayedTask.skipDelay();
            if (lastTimerId != TimerId.ALL && delayedTask.timerId == lastTimerId) {
                return;
            }
        }
    }

    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    private DelayedTask createAndScheduleDelayedTask(TimerId timerId, long delayMs, Runnable task) {
        DelayedTask delayedTask = new DelayedTask(timerId, System.currentTimeMillis() + delayMs, task);
        delayedTask.start(delayMs);
        return delayedTask;
    }

    /* access modifiers changed from: private */
    public void removeDelayedTask(DelayedTask task) {
        Assert.hardAssert(this.delayedTasks.remove(task), "Delayed task not found.", new Object[0]);
    }
}
