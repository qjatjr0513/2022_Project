package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Closeable;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@DoNotMock("Use ClosingFuture.from(Futures.immediate*Future)")
public final class ClosingFuture<V> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ClosingFuture.class.getName());
    /* access modifiers changed from: private */
    public final CloseableList closeables;
    /* access modifiers changed from: private */
    public final FluentFuture<V> future;
    private final AtomicReference<State> state;

    public interface AsyncClosingCallable<V> {
        ClosingFuture<V> call(DeferredCloser deferredCloser) throws Exception;
    }

    public interface AsyncClosingFunction<T, U> {
        ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl T t) throws Exception;
    }

    public interface ClosingCallable<V> {
        @NullableDecl
        V call(DeferredCloser deferredCloser) throws Exception;
    }

    public interface ClosingFunction<T, U> {
        @NullableDecl
        U apply(DeferredCloser deferredCloser, @NullableDecl T t) throws Exception;
    }

    enum State {
        OPEN,
        SUBSUMED,
        WILL_CLOSE,
        CLOSING,
        CLOSED,
        WILL_CREATE_VALUE_AND_CLOSER
    }

    public interface ValueAndCloserConsumer<V> {
        void accept(ValueAndCloser<V> valueAndCloser);
    }

    public static final class DeferredCloser {
        private final CloseableList list;

        DeferredCloser(CloseableList list2) {
            this.list = list2;
        }

        @NullableDecl
        public <C extends Closeable> C eventuallyClose(@NullableDecl C closeable, Executor closingExecutor) {
            Preconditions.checkNotNull(closingExecutor);
            if (closeable != null) {
                this.list.add((Closeable) closeable, closingExecutor);
            }
            return closeable;
        }
    }

    public static final class ValueAndCloser<V> {
        private final ClosingFuture<? extends V> closingFuture;

        ValueAndCloser(ClosingFuture<? extends V> closingFuture2) {
            this.closingFuture = (ClosingFuture) Preconditions.checkNotNull(closingFuture2);
        }

        @NullableDecl
        public V get() throws ExecutionException {
            return Futures.getDone(this.closingFuture.future);
        }

        public void closeAsync() {
            this.closingFuture.close();
        }
    }

    public static <V> ClosingFuture<V> submit(ClosingCallable<V> callable, Executor executor) {
        return new ClosingFuture<>(callable, executor);
    }

    public static <V> ClosingFuture<V> submitAsync(AsyncClosingCallable<V> callable, Executor executor) {
        return new ClosingFuture<>(callable, executor);
    }

    public static <V> ClosingFuture<V> from(ListenableFuture<V> future2) {
        return new ClosingFuture<>(future2);
    }

    @Deprecated
    public static <C extends Closeable> ClosingFuture<C> eventuallyClosing(ListenableFuture<C> future2, final Executor closingExecutor) {
        Preconditions.checkNotNull(closingExecutor);
        ClosingFuture<C> closingFuture = new ClosingFuture<>(Futures.nonCancellationPropagating(future2));
        Futures.addCallback(future2, new FutureCallback<Closeable>() {
            public void onSuccess(@NullableDecl Closeable result) {
                ClosingFuture.this.closeables.closer.eventuallyClose(result, closingExecutor);
            }

            public void onFailure(Throwable t) {
            }
        }, MoreExecutors.directExecutor());
        return closingFuture;
    }

    public static Combiner whenAllComplete(Iterable<? extends ClosingFuture<?>> futures) {
        return new Combiner(false, futures);
    }

    public static Combiner whenAllComplete(ClosingFuture<?> future1, ClosingFuture<?>... moreFutures) {
        return whenAllComplete(Lists.asList(future1, moreFutures));
    }

    public static Combiner whenAllSucceed(Iterable<? extends ClosingFuture<?>> futures) {
        return new Combiner(true, futures);
    }

    public static <V1, V2> Combiner2<V1, V2> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2) {
        return new Combiner2<>(future2);
    }

    public static <V1, V2, V3> Combiner3<V1, V2, V3> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3) {
        return new Combiner3<>(future2, future3);
    }

    public static <V1, V2, V3, V4> Combiner4<V1, V2, V3, V4> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3, ClosingFuture<V4> future4) {
        return new Combiner4(future2, future3, future4);
    }

    public static <V1, V2, V3, V4, V5> Combiner5<V1, V2, V3, V4, V5> whenAllSucceed(ClosingFuture<V1> future1, ClosingFuture<V2> future2, ClosingFuture<V3> future3, ClosingFuture<V4> future4, ClosingFuture<V5> future5) {
        return new Combiner5(future2, future3, future4, future5);
    }

    public static Combiner whenAllSucceed(ClosingFuture<?> future1, ClosingFuture<?> future2, ClosingFuture<?> future3, ClosingFuture<?> future4, ClosingFuture<?> future5, ClosingFuture<?> future6, ClosingFuture<?>... moreFutures) {
        return whenAllSucceed(FluentIterable.m32of(future1, future2, future3, future4, future5, future6).append((E[]) moreFutures));
    }

    private ClosingFuture(ListenableFuture<V> future2) {
        this.state = new AtomicReference<>(State.OPEN);
        this.closeables = new CloseableList();
        this.future = FluentFuture.from(future2);
    }

    private ClosingFuture(final ClosingCallable<V> callable, Executor executor) {
        this.state = new AtomicReference<>(State.OPEN);
        this.closeables = new CloseableList();
        Preconditions.checkNotNull(callable);
        TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(new Callable<V>() {
            public V call() throws Exception {
                return callable.call(ClosingFuture.this.closeables.closer);
            }

            public String toString() {
                return callable.toString();
            }
        });
        executor.execute(task);
        this.future = task;
    }

    private ClosingFuture(final AsyncClosingCallable<V> callable, Executor executor) {
        this.state = new AtomicReference<>(State.OPEN);
        this.closeables = new CloseableList();
        Preconditions.checkNotNull(callable);
        TrustedListenableFutureTask<V> task = TrustedListenableFutureTask.create(new AsyncCallable<V>() {
            public ListenableFuture<V> call() throws Exception {
                CloseableList newCloseables = new CloseableList();
                try {
                    ClosingFuture<V> closingFuture = callable.call(newCloseables.closer);
                    closingFuture.becomeSubsumedInto(ClosingFuture.this.closeables);
                    return closingFuture.future;
                } finally {
                    ClosingFuture.this.closeables.add(newCloseables, MoreExecutors.directExecutor());
                }
            }

            public String toString() {
                return callable.toString();
            }
        });
        executor.execute(task);
        this.future = task;
    }

    public ListenableFuture<?> statusFuture() {
        return Futures.nonCancellationPropagating(this.future.transform(Functions.constant(null), MoreExecutors.directExecutor()));
    }

    public <U> ClosingFuture<U> transform(final ClosingFunction<? super V, U> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return derive(this.future.transformAsync(new AsyncFunction<V, U>() {
            public ListenableFuture<U> apply(V input) throws Exception {
                return ClosingFuture.this.closeables.applyClosingFunction(function, input);
            }

            public String toString() {
                return function.toString();
            }
        }, executor));
    }

    public <U> ClosingFuture<U> transformAsync(final AsyncClosingFunction<? super V, U> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return derive(this.future.transformAsync(new AsyncFunction<V, U>() {
            public ListenableFuture<U> apply(V input) throws Exception {
                return ClosingFuture.this.closeables.applyAsyncClosingFunction(function, input);
            }

            public String toString() {
                return function.toString();
            }
        }, executor));
    }

    public static <V, U> AsyncClosingFunction<V, U> withoutCloser(final AsyncFunction<V, U> function) {
        Preconditions.checkNotNull(function);
        return new AsyncClosingFunction<V, U>() {
            public ClosingFuture<U> apply(DeferredCloser closer, V input) throws Exception {
                return ClosingFuture.from(AsyncFunction.this.apply(input));
            }
        };
    }

    public <X extends Throwable> ClosingFuture<V> catching(Class<X> exceptionType, ClosingFunction<? super X, ? extends V> fallback, Executor executor) {
        return catchingMoreGeneric(exceptionType, fallback, executor);
    }

    private <X extends Throwable, W extends V> ClosingFuture<V> catchingMoreGeneric(Class<X> exceptionType, final ClosingFunction<? super X, W> fallback, Executor executor) {
        Preconditions.checkNotNull(fallback);
        return derive(this.future.catchingAsync(exceptionType, new AsyncFunction<X, W>() {
            public ListenableFuture<W> apply(X exception) throws Exception {
                return ClosingFuture.this.closeables.applyClosingFunction(fallback, exception);
            }

            public String toString() {
                return fallback.toString();
            }
        }, executor));
    }

    public <X extends Throwable> ClosingFuture<V> catchingAsync(Class<X> exceptionType, AsyncClosingFunction<? super X, ? extends V> fallback, Executor executor) {
        return catchingAsyncMoreGeneric(exceptionType, fallback, executor);
    }

    private <X extends Throwable, W extends V> ClosingFuture<V> catchingAsyncMoreGeneric(Class<X> exceptionType, final AsyncClosingFunction<? super X, W> fallback, Executor executor) {
        Preconditions.checkNotNull(fallback);
        return derive(this.future.catchingAsync(exceptionType, new AsyncFunction<X, W>() {
            public ListenableFuture<W> apply(X exception) throws Exception {
                return ClosingFuture.this.closeables.applyAsyncClosingFunction(fallback, exception);
            }

            public String toString() {
                return fallback.toString();
            }
        }, executor));
    }

    public FluentFuture<V> finishToFuture() {
        if (compareAndUpdateState(State.OPEN, State.WILL_CLOSE)) {
            logger.log(Level.FINER, "will close {0}", this);
            this.future.addListener(new Runnable() {
                public void run() {
                    ClosingFuture.this.checkAndUpdateState(State.WILL_CLOSE, State.CLOSING);
                    ClosingFuture.this.close();
                    ClosingFuture.this.checkAndUpdateState(State.CLOSING, State.CLOSED);
                }
            }, MoreExecutors.directExecutor());
        } else {
            switch (C053412.$SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[this.state.get().ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot call finishToFuture() after deriving another step");
                case 2:
                    throw new IllegalStateException("Cannot call finishToFuture() after calling finishToValueAndCloser()");
                case 3:
                case 4:
                case 5:
                    throw new IllegalStateException("Cannot call finishToFuture() twice");
                case 6:
                    throw new AssertionError();
            }
        }
        return this.future;
    }

    /* renamed from: com.google.common.util.concurrent.ClosingFuture$12 */
    static /* synthetic */ class C053412 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State = iArr;
            try {
                iArr[State.SUBSUMED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[State.WILL_CREATE_VALUE_AND_CLOSER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[State.WILL_CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[State.CLOSING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[State.CLOSED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[State.OPEN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public void finishToValueAndCloser(final ValueAndCloserConsumer<? super V> consumer, Executor executor) {
        Preconditions.checkNotNull(consumer);
        if (!compareAndUpdateState(State.OPEN, State.WILL_CREATE_VALUE_AND_CLOSER)) {
            switch (C053412.$SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[this.state.get().ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot call finishToValueAndCloser() after deriving another step");
                case 2:
                    throw new IllegalStateException("Cannot call finishToValueAndCloser() twice");
                case 3:
                case 4:
                case 5:
                    throw new IllegalStateException("Cannot call finishToValueAndCloser() after calling finishToFuture()");
                default:
                    throw new AssertionError(this.state);
            }
        } else {
            this.future.addListener(new Runnable() {
                public void run() {
                    ClosingFuture.provideValueAndCloser(consumer, ClosingFuture.this);
                }
            }, executor);
        }
    }

    /* access modifiers changed from: private */
    public static <C, V extends C> void provideValueAndCloser(ValueAndCloserConsumer<C> consumer, ClosingFuture<V> closingFuture) {
        consumer.accept(new ValueAndCloser(closingFuture));
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        logger.log(Level.FINER, "cancelling {0}", this);
        boolean cancelled = this.future.cancel(mayInterruptIfRunning);
        if (cancelled) {
            close();
        }
        return cancelled;
    }

    /* access modifiers changed from: private */
    public void close() {
        logger.log(Level.FINER, "closing {0}", this);
        this.closeables.close();
    }

    private <U> ClosingFuture<U> derive(FluentFuture<U> future2) {
        ClosingFuture<U> derived = new ClosingFuture<>(future2);
        becomeSubsumedInto(derived.closeables);
        return derived;
    }

    /* access modifiers changed from: private */
    public void becomeSubsumedInto(CloseableList otherCloseables) {
        checkAndUpdateState(State.OPEN, State.SUBSUMED);
        otherCloseables.add(this.closeables, MoreExecutors.directExecutor());
    }

    public static final class Peeker {
        private volatile boolean beingCalled;
        private final ImmutableList<ClosingFuture<?>> futures;

        private Peeker(ImmutableList<ClosingFuture<?>> futures2) {
            this.futures = (ImmutableList) Preconditions.checkNotNull(futures2);
        }

        @NullableDecl
        public final <D> D getDone(ClosingFuture<D> closingFuture) throws ExecutionException {
            Preconditions.checkState(this.beingCalled);
            Preconditions.checkArgument(this.futures.contains(closingFuture));
            return Futures.getDone(closingFuture.future);
        }

        /* access modifiers changed from: private */
        @NullableDecl
        public <V> V call(Combiner.CombiningCallable<V> combiner, CloseableList closeables) throws Exception {
            this.beingCalled = true;
            CloseableList newCloseables = new CloseableList();
            try {
                return combiner.call(newCloseables.closer, this);
            } finally {
                closeables.add(newCloseables, MoreExecutors.directExecutor());
                this.beingCalled = false;
            }
        }

        /* access modifiers changed from: private */
        public <V> FluentFuture<V> callAsync(Combiner.AsyncCombiningCallable<V> combiner, CloseableList closeables) throws Exception {
            this.beingCalled = true;
            CloseableList newCloseables = new CloseableList();
            try {
                ClosingFuture<V> closingFuture = combiner.call(newCloseables.closer, this);
                closingFuture.becomeSubsumedInto(closeables);
                return closingFuture.future;
            } finally {
                closeables.add(newCloseables, MoreExecutors.directExecutor());
                this.beingCalled = false;
            }
        }
    }

    @DoNotMock("Use ClosingFuture.whenAllSucceed() or .whenAllComplete() instead.")
    public static class Combiner {
        private static final Function<ClosingFuture<?>, FluentFuture<?>> INNER_FUTURE = new Function<ClosingFuture<?>, FluentFuture<?>>() {
            public FluentFuture<?> apply(ClosingFuture<?> future) {
                return future.future;
            }
        };
        private final boolean allMustSucceed;
        /* access modifiers changed from: private */
        public final CloseableList closeables;
        protected final ImmutableList<ClosingFuture<?>> inputs;

        public interface AsyncCombiningCallable<V> {
            ClosingFuture<V> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        public interface CombiningCallable<V> {
            @NullableDecl
            V call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        private Combiner(boolean allMustSucceed2, Iterable<? extends ClosingFuture<?>> inputs2) {
            this.closeables = new CloseableList();
            this.allMustSucceed = allMustSucceed2;
            this.inputs = ImmutableList.copyOf(inputs2);
            for (ClosingFuture<?> input : inputs2) {
                input.becomeSubsumedInto(this.closeables);
            }
        }

        public <V> ClosingFuture<V> call(final CombiningCallable<V> combiningCallable, Executor executor) {
            ClosingFuture<V> derived = new ClosingFuture<>((ListenableFuture) futureCombiner().call(new Callable<V>() {
                public V call() throws Exception {
                    return new Peeker(Combiner.this.inputs).call(combiningCallable, Combiner.this.closeables);
                }

                public String toString() {
                    return combiningCallable.toString();
                }
            }, executor));
            derived.closeables.add(this.closeables, MoreExecutors.directExecutor());
            return derived;
        }

        public <V> ClosingFuture<V> callAsync(final AsyncCombiningCallable<V> combiningCallable, Executor executor) {
            ClosingFuture<V> derived = new ClosingFuture<>((ListenableFuture) futureCombiner().callAsync(new AsyncCallable<V>() {
                public ListenableFuture<V> call() throws Exception {
                    return new Peeker(Combiner.this.inputs).callAsync(combiningCallable, Combiner.this.closeables);
                }

                public String toString() {
                    return combiningCallable.toString();
                }
            }, executor));
            derived.closeables.add(this.closeables, MoreExecutors.directExecutor());
            return derived;
        }

        private Futures.FutureCombiner<Object> futureCombiner() {
            if (this.allMustSucceed) {
                return Futures.whenAllSucceed(inputFutures());
            }
            return Futures.whenAllComplete(inputFutures());
        }

        private ImmutableList<FluentFuture<?>> inputFutures() {
            return FluentIterable.from(this.inputs).transform(INNER_FUTURE).toList();
        }
    }

    public static final class Combiner2<V1, V2> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;

        public interface AsyncClosingFunction2<V1, V2, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2) throws Exception;
        }

        public interface ClosingFunction2<V1, V2, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2) throws Exception;
        }

        private Combiner2(ClosingFuture<V1> future12, ClosingFuture<V2> future22) {
            super(true, ImmutableList.m43of(future12, future22));
            this.future1 = future12;
            this.future2 = future22;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction2<V1, V2, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @NullableDecl
                public U call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner2.this.future1), peeker.getDone(Combiner2.this.future2));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction2<V1, V2, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner2.this.future1), peeker.getDone(Combiner2.this.future2));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }
    }

    public static final class Combiner3<V1, V2, V3> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;

        public interface AsyncClosingFunction3<V1, V2, V3, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3) throws Exception;
        }

        public interface ClosingFunction3<V1, V2, V3, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3) throws Exception;
        }

        private Combiner3(ClosingFuture<V1> future12, ClosingFuture<V2> future22, ClosingFuture<V3> future32) {
            super(true, ImmutableList.m44of(future12, future22, future32));
            this.future1 = future12;
            this.future2 = future22;
            this.future3 = future32;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction3<V1, V2, V3, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @NullableDecl
                public U call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner3.this.future1), peeker.getDone(Combiner3.this.future2), peeker.getDone(Combiner3.this.future3));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction3<V1, V2, V3, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner3.this.future1), peeker.getDone(Combiner3.this.future2), peeker.getDone(Combiner3.this.future3));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }
    }

    public static final class Combiner4<V1, V2, V3, V4> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;
        /* access modifiers changed from: private */
        public final ClosingFuture<V4> future4;

        public interface AsyncClosingFunction4<V1, V2, V3, V4, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4) throws Exception;
        }

        public interface ClosingFunction4<V1, V2, V3, V4, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4) throws Exception;
        }

        private Combiner4(ClosingFuture<V1> future12, ClosingFuture<V2> future22, ClosingFuture<V3> future32, ClosingFuture<V4> future42) {
            super(true, ImmutableList.m45of(future12, future22, future32, future42));
            this.future1 = future12;
            this.future2 = future22;
            this.future3 = future32;
            this.future4 = future42;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction4<V1, V2, V3, V4, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @NullableDecl
                public U call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner4.this.future1), peeker.getDone(Combiner4.this.future2), peeker.getDone(Combiner4.this.future3), peeker.getDone(Combiner4.this.future4));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction4<V1, V2, V3, V4, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner4.this.future1), peeker.getDone(Combiner4.this.future2), peeker.getDone(Combiner4.this.future3), peeker.getDone(Combiner4.this.future4));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }
    }

    public static final class Combiner5<V1, V2, V3, V4, V5> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;
        /* access modifiers changed from: private */
        public final ClosingFuture<V4> future4;
        /* access modifiers changed from: private */
        public final ClosingFuture<V5> future5;

        public interface AsyncClosingFunction5<V1, V2, V3, V4, V5, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4, @NullableDecl V5 v5) throws Exception;
        }

        public interface ClosingFunction5<V1, V2, V3, V4, V5, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4, @NullableDecl V5 v5) throws Exception;
        }

        private Combiner5(ClosingFuture<V1> future12, ClosingFuture<V2> future22, ClosingFuture<V3> future32, ClosingFuture<V4> future42, ClosingFuture<V5> future52) {
            super(true, ImmutableList.m46of(future12, future22, future32, future42, future52));
            this.future1 = future12;
            this.future2 = future22;
            this.future3 = future32;
            this.future4 = future42;
            this.future5 = future52;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction5<V1, V2, V3, V4, V5, U> function, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @NullableDecl
                public U call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner5.this.future1), peeker.getDone(Combiner5.this.future2), peeker.getDone(Combiner5.this.future3), peeker.getDone(Combiner5.this.future4), peeker.getDone(Combiner5.this.future5));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction5<V1, V2, V3, V4, V5, U> function, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser closer, Peeker peeker) throws Exception {
                    return function.apply(closer, peeker.getDone(Combiner5.this.future1), peeker.getDone(Combiner5.this.future2), peeker.getDone(Combiner5.this.future3), peeker.getDone(Combiner5.this.future4), peeker.getDone(Combiner5.this.future5));
                }

                public String toString() {
                    return function.toString();
                }
            }, executor);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("state", (Object) this.state.get()).addValue((Object) this.future).toString();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (this.state.get().equals(State.OPEN)) {
            logger.log(Level.SEVERE, "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
            finishToFuture();
        }
    }

    /* access modifiers changed from: private */
    public static void closeQuietly(final Closeable closeable, Executor executor) {
        if (closeable != null) {
            try {
                executor.execute(new Runnable() {
                    public void run() {
                        try {
                            closeable.close();
                        } catch (IOException | RuntimeException e) {
                            ClosingFuture.logger.log(Level.WARNING, "thrown by close()", e);
                        }
                    }
                });
            } catch (RejectedExecutionException e) {
                Logger logger2 = logger;
                if (logger2.isLoggable(Level.WARNING)) {
                    logger2.log(Level.WARNING, String.format("while submitting close to %s; will close inline", new Object[]{executor}), e);
                }
                closeQuietly(closeable, MoreExecutors.directExecutor());
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkAndUpdateState(State oldState, State newState) {
        Preconditions.checkState(compareAndUpdateState(oldState, newState), "Expected state to be %s, but it was %s", (Object) oldState, (Object) newState);
    }

    private boolean compareAndUpdateState(State oldState, State newState) {
        return this.state.compareAndSet(oldState, newState);
    }

    private static final class CloseableList extends IdentityHashMap<Closeable, Executor> implements Closeable {
        private volatile boolean closed;
        /* access modifiers changed from: private */
        public final DeferredCloser closer;
        private volatile CountDownLatch whenClosed;

        private CloseableList() {
            this.closer = new DeferredCloser(this);
        }

        /* access modifiers changed from: package-private */
        public <V, U> ListenableFuture<U> applyClosingFunction(ClosingFunction<? super V, U> transformation, V input) throws Exception {
            CloseableList newCloseables = new CloseableList();
            try {
                return Futures.immediateFuture(transformation.apply(newCloseables.closer, input));
            } finally {
                add(newCloseables, MoreExecutors.directExecutor());
            }
        }

        /* access modifiers changed from: package-private */
        public <V, U> FluentFuture<U> applyAsyncClosingFunction(AsyncClosingFunction<V, U> transformation, V input) throws Exception {
            CloseableList newCloseables = new CloseableList();
            try {
                ClosingFuture<U> closingFuture = transformation.apply(newCloseables.closer, input);
                closingFuture.becomeSubsumedInto(newCloseables);
                return closingFuture.future;
            } finally {
                add(newCloseables, MoreExecutors.directExecutor());
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
            r0 = entrySet().iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
            if (r0.hasNext() == false) goto L_0x0034;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
            r1 = (java.util.Map.Entry) r0.next();
            com.google.common.util.concurrent.ClosingFuture.access$3300(r1.getKey(), r1.getValue());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
            clear();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
            if (r4.whenClosed == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
            r4.whenClosed.countDown();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() {
            /*
                r4 = this;
                boolean r0 = r4.closed
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r4)
                boolean r0 = r4.closed     // Catch:{ all -> 0x0041 }
                if (r0 == 0) goto L_0x000c
                monitor-exit(r4)     // Catch:{ all -> 0x0041 }
                return
            L_0x000c:
                r0 = 1
                r4.closed = r0     // Catch:{ all -> 0x0041 }
                monitor-exit(r4)     // Catch:{ all -> 0x0041 }
                java.util.Set r0 = r4.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L_0x0018:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0034
                java.lang.Object r1 = r0.next()
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                java.lang.Object r2 = r1.getKey()
                java.io.Closeable r2 = (java.io.Closeable) r2
                java.lang.Object r3 = r1.getValue()
                java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
                com.google.common.util.concurrent.ClosingFuture.closeQuietly(r2, r3)
                goto L_0x0018
            L_0x0034:
                r4.clear()
                java.util.concurrent.CountDownLatch r0 = r4.whenClosed
                if (r0 == 0) goto L_0x0040
                java.util.concurrent.CountDownLatch r0 = r4.whenClosed
                r0.countDown()
            L_0x0040:
                return
            L_0x0041:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0041 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ClosingFuture.CloseableList.close():void");
        }

        /* access modifiers changed from: package-private */
        public void add(@NullableDecl Closeable closeable, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (closeable != null) {
                synchronized (this) {
                    if (!this.closed) {
                        put(closeable, executor);
                    } else {
                        ClosingFuture.closeQuietly(closeable, executor);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public CountDownLatch whenClosedCountDown() {
            boolean z = false;
            if (this.closed) {
                return new CountDownLatch(0);
            }
            synchronized (this) {
                if (this.closed) {
                    CountDownLatch countDownLatch = new CountDownLatch(0);
                    return countDownLatch;
                }
                if (this.whenClosed == null) {
                    z = true;
                }
                Preconditions.checkState(z);
                CountDownLatch countDownLatch2 = new CountDownLatch(1);
                this.whenClosed = countDownLatch2;
                return countDownLatch2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CountDownLatch whenClosedCountDown() {
        return this.closeables.whenClosedCountDown();
    }
}
