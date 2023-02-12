package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class ExecutionSequencer {
    /* access modifiers changed from: private */
    public ThreadConfinedTaskQueue latestTaskQueue = new ThreadConfinedTaskQueue();
    private final AtomicReference<ListenableFuture<Void>> ref = new AtomicReference<>(Futures.immediateVoidFuture());

    enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED
    }

    private ExecutionSequencer() {
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    private static final class ThreadConfinedTaskQueue {
        Executor nextExecutor;
        Runnable nextTask;
        Thread thread;

        private ThreadConfinedTaskQueue() {
        }
    }

    public <T> ListenableFuture<T> submit(final Callable<T> callable, Executor executor) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(executor);
        return submitAsync(new AsyncCallable<T>(this) {
            public ListenableFuture<T> call() throws Exception {
                return Futures.immediateFuture(callable.call());
            }

            public String toString() {
                return callable.toString();
            }
        }, executor);
    }

    public <T> ListenableFuture<T> submitAsync(AsyncCallable<T> callable, Executor executor) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(executor);
        final TaskNonReentrantExecutor taskExecutor = new TaskNonReentrantExecutor(executor, this);
        final AsyncCallable<T> asyncCallable = callable;
        AsyncCallable<T> task = new AsyncCallable<T>(this) {
            public ListenableFuture<T> call() throws Exception {
                if (!taskExecutor.trySetStarted()) {
                    return Futures.immediateCancelledFuture();
                }
                return asyncCallable.call();
            }

            public String toString() {
                return asyncCallable.toString();
            }
        };
        SettableFuture create = SettableFuture.create();
        ListenableFuture<Void> oldFuture = this.ref.getAndSet(create);
        TrustedListenableFutureTask<T> taskFuture = TrustedListenableFutureTask.create(task);
        oldFuture.addListener(taskFuture, taskExecutor);
        ListenableFuture<T> outputFuture = Futures.nonCancellationPropagating(taskFuture);
        final TrustedListenableFutureTask<T> trustedListenableFutureTask = taskFuture;
        final SettableFuture settableFuture = create;
        final ListenableFuture<Void> listenableFuture = oldFuture;
        final ListenableFuture<T> listenableFuture2 = outputFuture;
        final TaskNonReentrantExecutor taskNonReentrantExecutor = taskExecutor;
        Runnable listener = new Runnable(this) {
            public void run() {
                if (trustedListenableFutureTask.isDone()) {
                    settableFuture.setFuture(listenableFuture);
                } else if (listenableFuture2.isCancelled() && taskNonReentrantExecutor.trySetCancelled()) {
                    trustedListenableFutureTask.cancel(false);
                }
            }
        };
        outputFuture.addListener(listener, MoreExecutors.directExecutor());
        taskFuture.addListener(listener, MoreExecutors.directExecutor());
        return outputFuture;
    }

    private static final class TaskNonReentrantExecutor extends AtomicReference<RunningState> implements Executor, Runnable {
        Executor delegate;
        ExecutionSequencer sequencer;
        Thread submitting;
        Runnable task;

        private TaskNonReentrantExecutor(Executor delegate2, ExecutionSequencer sequencer2) {
            super(RunningState.NOT_RUN);
            this.delegate = delegate2;
            this.sequencer = sequencer2;
        }

        public void execute(Runnable task2) {
            if (get() == RunningState.CANCELLED) {
                this.delegate = null;
                this.sequencer = null;
                return;
            }
            this.submitting = Thread.currentThread();
            try {
                ThreadConfinedTaskQueue submittingTaskQueue = this.sequencer.latestTaskQueue;
                if (submittingTaskQueue.thread == this.submitting) {
                    this.sequencer = null;
                    Preconditions.checkState(submittingTaskQueue.nextTask == null);
                    submittingTaskQueue.nextTask = task2;
                    submittingTaskQueue.nextExecutor = this.delegate;
                    this.delegate = null;
                } else {
                    Executor localDelegate = this.delegate;
                    this.delegate = null;
                    this.task = task2;
                    localDelegate.execute(this);
                }
            } finally {
                this.submitting = null;
            }
        }

        public void run() {
            Thread currentThread = Thread.currentThread();
            if (currentThread != this.submitting) {
                Runnable localTask = this.task;
                this.task = null;
                localTask.run();
                return;
            }
            ThreadConfinedTaskQueue executingTaskQueue = new ThreadConfinedTaskQueue();
            executingTaskQueue.thread = currentThread;
            ThreadConfinedTaskQueue unused = this.sequencer.latestTaskQueue = executingTaskQueue;
            this.sequencer = null;
            try {
                Runnable localTask2 = this.task;
                this.task = null;
                localTask2.run();
                while (true) {
                    Runnable runnable = executingTaskQueue.nextTask;
                    Runnable queuedTask = runnable;
                    boolean z = true;
                    boolean z2 = runnable != null;
                    Executor executor = executingTaskQueue.nextExecutor;
                    Executor queuedExecutor = executor;
                    if (executor == null) {
                        z = false;
                    }
                    if (z2 && z) {
                        executingTaskQueue.nextTask = null;
                        executingTaskQueue.nextExecutor = null;
                        queuedExecutor.execute(queuedTask);
                    } else {
                        return;
                    }
                }
            } finally {
                executingTaskQueue.thread = null;
            }
        }

        /* access modifiers changed from: private */
        public boolean trySetStarted() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.STARTED);
        }

        /* access modifiers changed from: private */
        public boolean trySetCancelled() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.CANCELLED);
        }
    }
}
