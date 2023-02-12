package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class MoreExecutors {
    private MoreExecutors() {
    }

    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(executor, terminationTimeout, timeUnit);
    }

    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
        return new Application().getExitingExecutorService(executor);
    }

    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(executor, terminationTimeout, timeUnit);
    }

    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
        return new Application().getExitingScheduledExecutorService(executor);
    }

    public static void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(service, terminationTimeout, timeUnit);
    }

    static class Application {
        Application() {
        }

        /* access modifiers changed from: package-private */
        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ExecutorService service = Executors.unconfigurableExecutorService(executor);
            addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return service;
        }

        /* access modifiers changed from: package-private */
        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
            return getExitingExecutorService(executor, 120, TimeUnit.SECONDS);
        }

        /* access modifiers changed from: package-private */
        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(executor);
            ScheduledExecutorService service = Executors.unconfigurableScheduledExecutorService(executor);
            addDelayedShutdownHook(executor, terminationTimeout, timeUnit);
            return service;
        }

        /* access modifiers changed from: package-private */
        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
            return getExitingScheduledExecutorService(executor, 120, TimeUnit.SECONDS);
        }

        /* access modifiers changed from: package-private */
        public final void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
            Preconditions.checkNotNull(service);
            Preconditions.checkNotNull(timeUnit);
            String valueOf = String.valueOf(service);
            final ExecutorService executorService = service;
            final long j = terminationTimeout;
            final TimeUnit timeUnit2 = timeUnit;
            addShutdownHook(MoreExecutors.newThread(new StringBuilder(String.valueOf(valueOf).length() + 24).append("DelayedShutdownHook-for-").append(valueOf).toString(), new Runnable(this) {
                public void run() {
                    try {
                        executorService.shutdown();
                        executorService.awaitTermination(j, timeUnit2);
                    } catch (InterruptedException e) {
                    }
                }
            }));
        }

        /* access modifiers changed from: package-private */
        public void addShutdownHook(Thread hook) {
            Runtime.getRuntime().addShutdownHook(hook);
        }
    }

    /* access modifiers changed from: private */
    public static void useDaemonThreadFactory(ThreadPoolExecutor executor) {
        executor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(executor.getThreadFactory()).build());
    }

    private static final class DirectExecutorService extends AbstractListeningExecutorService {
        private final Object lock;
        private int runningTasks;
        private boolean shutdown;

        private DirectExecutorService() {
            this.lock = new Object();
            this.runningTasks = 0;
            this.shutdown = false;
        }

        public void execute(Runnable command) {
            startTask();
            try {
                command.run();
            } finally {
                endTask();
            }
        }

        public boolean isShutdown() {
            boolean z;
            synchronized (this.lock) {
                z = this.shutdown;
            }
            return z;
        }

        public void shutdown() {
            synchronized (this.lock) {
                this.shutdown = true;
                if (this.runningTasks == 0) {
                    this.lock.notifyAll();
                }
            }
        }

        public List<Runnable> shutdownNow() {
            shutdown();
            return Collections.emptyList();
        }

        public boolean isTerminated() {
            boolean z;
            synchronized (this.lock) {
                z = this.shutdown && this.runningTasks == 0;
            }
            return z;
        }

        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            long nanos = unit.toNanos(timeout);
            synchronized (this.lock) {
                while (true) {
                    if (this.shutdown && this.runningTasks == 0) {
                        return true;
                    }
                    if (nanos <= 0) {
                        return false;
                    }
                    long now = System.nanoTime();
                    TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                    nanos -= System.nanoTime() - now;
                }
            }
        }

        private void startTask() {
            synchronized (this.lock) {
                if (!this.shutdown) {
                    this.runningTasks++;
                } else {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
            }
        }

        private void endTask() {
            synchronized (this.lock) {
                int numRunning = this.runningTasks - 1;
                this.runningTasks = numRunning;
                if (numRunning == 0) {
                    this.lock.notifyAll();
                }
            }
        }
    }

    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService();
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    public static Executor newSequentialExecutor(Executor delegate) {
        return new SequentialExecutor(delegate);
    }

    public static ListeningExecutorService listeningDecorator(ExecutorService delegate) {
        if (delegate instanceof ListeningExecutorService) {
            return (ListeningExecutorService) delegate;
        }
        if (delegate instanceof ScheduledExecutorService) {
            return new ScheduledListeningDecorator((ScheduledExecutorService) delegate);
        }
        return new ListeningDecorator(delegate);
    }

    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService delegate) {
        if (delegate instanceof ListeningScheduledExecutorService) {
            return (ListeningScheduledExecutorService) delegate;
        }
        return new ScheduledListeningDecorator(delegate);
    }

    private static class ListeningDecorator extends AbstractListeningExecutorService {
        private final ExecutorService delegate;

        ListeningDecorator(ExecutorService delegate2) {
            this.delegate = (ExecutorService) Preconditions.checkNotNull(delegate2);
        }

        public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.awaitTermination(timeout, unit);
        }

        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public final void shutdown() {
            this.delegate.shutdown();
        }

        public final List<Runnable> shutdownNow() {
            return this.delegate.shutdownNow();
        }

        public final void execute(Runnable command) {
            this.delegate.execute(command);
        }
    }

    private static final class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
        final ScheduledExecutorService delegate;

        ScheduledListeningDecorator(ScheduledExecutorService delegate2) {
            super(delegate2);
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(delegate2);
        }

        public ListenableScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            TrustedListenableFutureTask<Void> task = TrustedListenableFutureTask.create(command, null);
            return new ListenableScheduledTask(task, this.delegate.schedule(task, delay, unit));
        }

        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(callable);
            return new ListenableScheduledTask(task, this.delegate.schedule(task, delay, unit));
        }

        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
            return new ListenableScheduledTask(task, this.delegate.scheduleAtFixedRate(task, initialDelay, period, unit));
        }

        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            NeverSuccessfulListenableFutureTask task = new NeverSuccessfulListenableFutureTask(command);
            return new ListenableScheduledTask(task, this.delegate.scheduleWithFixedDelay(task, initialDelay, delay, unit));
        }

        private static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            private final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> listenableDelegate, ScheduledFuture<?> scheduledDelegate2) {
                super(listenableDelegate);
                this.scheduledDelegate = scheduledDelegate2;
            }

            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean cancelled = super.cancel(mayInterruptIfRunning);
                if (cancelled) {
                    this.scheduledDelegate.cancel(mayInterruptIfRunning);
                }
                return cancelled;
            }

            public long getDelay(TimeUnit unit) {
                return this.scheduledDelegate.getDelay(unit);
            }

            public int compareTo(Delayed other) {
                return this.scheduledDelegate.compareTo(other);
            }
        }

        private static final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture<Void> implements Runnable {
            private final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable delegate2) {
                this.delegate = (Runnable) Preconditions.checkNotNull(delegate2);
            }

            public void run() {
                try {
                    this.delegate.run();
                } catch (Throwable t) {
                    setException(t);
                    throw Throwables.propagate(t);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ed A[LOOP:2: B:51:0x00e7->B:53:0x00ed, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService r22, java.util.Collection<? extends java.util.concurrent.Callable<T>> r23, boolean r24, long r25, java.util.concurrent.TimeUnit r27) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        /*
            r1 = r22
            com.google.common.base.Preconditions.checkNotNull(r22)
            com.google.common.base.Preconditions.checkNotNull(r27)
            int r2 = r23.size()
            if (r2 <= 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            com.google.common.base.Preconditions.checkArgument(r0)
            java.util.ArrayList r4 = com.google.common.collect.Lists.newArrayListWithCapacity(r2)
            java.util.concurrent.LinkedBlockingQueue r5 = com.google.common.collect.Queues.newLinkedBlockingQueue()
            r6 = r25
            r8 = r27
            long r9 = r8.toNanos(r6)
            r0 = 0
            if (r24 == 0) goto L_0x002f
            long r11 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002c }
            goto L_0x0031
        L_0x002c:
            r0 = move-exception
            goto L_0x00e3
        L_0x002f:
            r11 = 0
        L_0x0031:
            java.util.Iterator r13 = r23.iterator()     // Catch:{ all -> 0x002c }
            java.lang.Object r14 = r13.next()     // Catch:{ all -> 0x002c }
            java.util.concurrent.Callable r14 = (java.util.concurrent.Callable) r14     // Catch:{ all -> 0x002c }
            com.google.common.util.concurrent.ListenableFuture r14 = submitAndAddQueueListener(r1, r14, r5)     // Catch:{ all -> 0x002c }
            r4.add(r14)     // Catch:{ all -> 0x002c }
            int r2 = r2 + -1
            r14 = 1
            r21 = r2
            r2 = r0
            r0 = r14
            r14 = r11
            r10 = r9
            r9 = r21
        L_0x004d:
            java.lang.Object r12 = r5.poll()     // Catch:{ all -> 0x00e0 }
            java.util.concurrent.Future r12 = (java.util.concurrent.Future) r12     // Catch:{ all -> 0x00e0 }
            if (r12 != 0) goto L_0x009e
            if (r9 <= 0) goto L_0x006b
            int r9 = r9 + -1
            java.lang.Object r16 = r13.next()     // Catch:{ all -> 0x00e0 }
            r3 = r16
            java.util.concurrent.Callable r3 = (java.util.concurrent.Callable) r3     // Catch:{ all -> 0x00e0 }
            com.google.common.util.concurrent.ListenableFuture r3 = submitAndAddQueueListener(r1, r3, r5)     // Catch:{ all -> 0x00e0 }
            r4.add(r3)     // Catch:{ all -> 0x00e0 }
            int r0 = r0 + 1
            goto L_0x009e
        L_0x006b:
            if (r0 != 0) goto L_0x0079
            if (r2 != 0) goto L_0x0077
            java.util.concurrent.ExecutionException r3 = new java.util.concurrent.ExecutionException     // Catch:{ all -> 0x00e0 }
            r12 = 0
            r3.<init>(r12)     // Catch:{ all -> 0x00e0 }
            r2 = r3
        L_0x0077:
            throw r2     // Catch:{ all -> 0x00e0 }
        L_0x0079:
            if (r24 == 0) goto L_0x0097
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x00e0 }
            java.lang.Object r3 = r5.poll(r10, r3)     // Catch:{ all -> 0x00e0 }
            java.util.concurrent.Future r3 = (java.util.concurrent.Future) r3     // Catch:{ all -> 0x00e0 }
            r12 = r3
            if (r12 == 0) goto L_0x0091
            long r17 = java.lang.System.nanoTime()     // Catch:{ all -> 0x00e0 }
            long r19 = r17 - r14
            long r10 = r10 - r19
            r14 = r17
            goto L_0x009e
        L_0x0091:
            java.util.concurrent.TimeoutException r3 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x00e0 }
            r3.<init>()     // Catch:{ all -> 0x00e0 }
            throw r3     // Catch:{ all -> 0x00e0 }
        L_0x0097:
            java.lang.Object r3 = r5.take()     // Catch:{ all -> 0x00e0 }
            java.util.concurrent.Future r3 = (java.util.concurrent.Future) r3     // Catch:{ all -> 0x00e0 }
            r12 = r3
        L_0x009e:
            if (r12 == 0) goto L_0x00da
            int r3 = r0 + -1
            java.lang.Object r0 = r12.get()     // Catch:{ ExecutionException -> 0x00d1, RuntimeException -> 0x00c4 }
            java.util.Iterator r16 = r4.iterator()
        L_0x00aa:
            boolean r17 = r16.hasNext()
            if (r17 == 0) goto L_0x00c3
            java.lang.Object r17 = r16.next()
            r1 = r17
            java.util.concurrent.Future r1 = (java.util.concurrent.Future) r1
            r17 = r2
            r2 = 1
            r1.cancel(r2)
            r1 = r22
            r2 = r17
            goto L_0x00aa
        L_0x00c3:
            return r0
        L_0x00c4:
            r0 = move-exception
            r17 = r2
            r1 = r0
            r0 = r1
            java.util.concurrent.ExecutionException r1 = new java.util.concurrent.ExecutionException     // Catch:{ all -> 0x00e0 }
            r1.<init>(r0)     // Catch:{ all -> 0x00e0 }
            r2 = r1
            r0 = r3
            goto L_0x00dc
        L_0x00d1:
            r0 = move-exception
            r17 = r2
            r1 = r0
            r0 = r1
            r2 = r0
            r0 = r3
            goto L_0x00dc
        L_0x00da:
            r17 = r2
        L_0x00dc:
            r1 = r22
            goto L_0x004d
        L_0x00e0:
            r0 = move-exception
            r2 = r9
            r9 = r10
        L_0x00e3:
            java.util.Iterator r1 = r4.iterator()
        L_0x00e7:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00f8
            java.lang.Object r3 = r1.next()
            java.util.concurrent.Future r3 = (java.util.concurrent.Future) r3
            r11 = 1
            r3.cancel(r11)
            goto L_0x00e7
        L_0x00f8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.MoreExecutors.invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService, java.util.Collection, boolean, long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService executorService, Callable<T> task, final BlockingQueue<Future<T>> queue) {
        final ListenableFuture<T> future = executorService.submit(task);
        future.addListener(new Runnable() {
            public void run() {
                queue.add(future);
            }
        }, directExecutor());
        return future;
    }

    public static ThreadFactory platformThreadFactory() {
        if (!isAppEngineWithApiClasses()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e3);
        } catch (InvocationTargetException e4) {
            throw Throwables.propagate(e4.getCause());
        }
    }

    private static boolean isAppEngineWithApiClasses() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            Class.forName("com.google.appengine.api.utils.SystemProperty");
            try {
                if (Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke((Object) null, new Object[0]) != null) {
                    return true;
                }
                return false;
            } catch (ClassNotFoundException e) {
                return false;
            } catch (InvocationTargetException e2) {
                return false;
            } catch (IllegalAccessException e3) {
                return false;
            } catch (NoSuchMethodException e4) {
                return false;
            }
        } catch (ClassNotFoundException e5) {
            return false;
        }
    }

    static Thread newThread(String name, Runnable runnable) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(runnable);
        Thread result = platformThreadFactory().newThread(runnable);
        try {
            result.setName(name);
        } catch (SecurityException e) {
        }
        return result;
    }

    static Executor renamingDecorator(final Executor executor, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(nameSupplier);
        return new Executor() {
            public void execute(Runnable command) {
                executor.execute(Callables.threadRenaming(command, (Supplier<String>) nameSupplier));
            }
        };
    }

    static ExecutorService renamingDecorator(ExecutorService service, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        return new WrappingExecutorService(service) {
            /* access modifiers changed from: protected */
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) nameSupplier);
            }

            /* access modifiers changed from: protected */
            public Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>) nameSupplier);
            }
        };
    }

    static ScheduledExecutorService renamingDecorator(ScheduledExecutorService service, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        return new WrappingScheduledExecutorService(service) {
            /* access modifiers changed from: protected */
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) nameSupplier);
            }

            /* access modifiers changed from: protected */
            public Runnable wrapTask(Runnable command) {
                return Callables.threadRenaming(command, (Supplier<String>) nameSupplier);
            }
        };
    }

    public static boolean shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
        long halfTimeoutNanos = unit.toNanos(timeout) / 2;
        service.shutdown();
        try {
            if (!service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS)) {
                service.shutdownNow();
                service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            service.shutdownNow();
        }
        return service.isTerminated();
    }

    static Executor rejectionPropagatingExecutor(final Executor delegate, final AbstractFuture<?> future) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(future);
        if (delegate == directExecutor()) {
            return delegate;
        }
        return new Executor() {
            public void execute(Runnable command) {
                try {
                    delegate.execute(command);
                } catch (RejectedExecutionException e) {
                    future.setException(e);
                }
            }
        };
    }
}
