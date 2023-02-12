package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {
    @NullableDecl
    Class<X> exceptionType;
    @NullableDecl
    F fallback;
    @NullableDecl
    ListenableFuture<? extends V> inputFuture;

    /* access modifiers changed from: package-private */
    @NullableDecl
    public abstract T doFallback(F f, X x) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void setResult(@NullableDecl T t);

    static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType2, Function<? super X, ? extends V> fallback2, Executor executor) {
        CatchingFuture<V, X> future = new CatchingFuture<>(input, exceptionType2, fallback2);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType2, AsyncFunction<? super X, ? extends V> fallback2, Executor executor) {
        AsyncCatchingFuture<V, X> future = new AsyncCatchingFuture<>(input, exceptionType2, fallback2);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    AbstractCatchingFuture(ListenableFuture<? extends V> inputFuture2, Class<X> exceptionType2, F fallback2) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
        this.exceptionType = (Class) Preconditions.checkNotNull(exceptionType2);
        this.fallback = Preconditions.checkNotNull(fallback2);
    }

    public final void run() {
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        boolean z = true;
        boolean z2 = (localInputFuture == null) | (localExceptionType == null);
        if (localFallback != null) {
            z = false;
        }
        if ((!z && !z2) && !isCancelled()) {
            this.inputFuture = null;
            V sourceResult = null;
            Throwable throwable = null;
            try {
                if (localInputFuture instanceof InternalFutureFailureAccess) {
                    throwable = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) localInputFuture);
                }
                if (throwable == null) {
                    sourceResult = Futures.getDone(localInputFuture);
                }
            } catch (ExecutionException e) {
                throwable = e.getCause();
                if (throwable == null) {
                    String valueOf = String.valueOf(localInputFuture.getClass());
                    String valueOf2 = String.valueOf(e.getClass());
                    throwable = new NullPointerException(new StringBuilder(String.valueOf(valueOf).length() + 35 + String.valueOf(valueOf2).length()).append("Future type ").append(valueOf).append(" threw ").append(valueOf2).append(" without a cause").toString());
                }
            } catch (Throwable e2) {
                throwable = e2;
            }
            if (throwable == null) {
                set(sourceResult);
            } else if (!Platform.isInstanceOfThrowableClass(throwable, localExceptionType)) {
                setFuture(localInputFuture);
            } else {
                try {
                    T fallbackResult = doFallback(localFallback, throwable);
                    this.exceptionType = null;
                    this.fallback = null;
                    setResult(fallbackResult);
                } catch (Throwable t) {
                    this.exceptionType = null;
                    this.fallback = null;
                    throw t;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            String valueOf = String.valueOf(localInputFuture);
            resultString = new StringBuilder(String.valueOf(valueOf).length() + 16).append("inputFuture=[").append(valueOf).append("], ").toString();
        }
        if (localExceptionType != null && localFallback != null) {
            String valueOf2 = String.valueOf(localExceptionType);
            String valueOf3 = String.valueOf(localFallback);
            return new StringBuilder(String.valueOf(resultString).length() + 29 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length()).append(resultString).append("exceptionType=[").append(valueOf2).append("], fallback=[").append(valueOf3).append("]").toString();
        } else if (superString == null) {
            return null;
        } else {
            String valueOf4 = String.valueOf(resultString);
            String valueOf5 = String.valueOf(superString);
            return valueOf5.length() != 0 ? valueOf4.concat(valueOf5) : new String(valueOf4);
        }
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    private static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        AsyncCatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> fallback, X cause) throws Exception {
            ListenableFuture<? extends V> replacement = fallback.apply(cause);
            Preconditions.checkNotNull(replacement, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) fallback);
            return replacement;
        }

        /* access modifiers changed from: package-private */
        public void setResult(ListenableFuture<? extends V> result) {
            setFuture(result);
        }
    }

    private static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        CatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* access modifiers changed from: package-private */
        @NullableDecl
        public V doFallback(Function<? super X, ? extends V> fallback, X cause) throws Exception {
            return fallback.apply(cause);
        }

        /* access modifiers changed from: package-private */
        public void setResult(@NullableDecl V result) {
            set(result);
        }
    }
}
