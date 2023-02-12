package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class AggregateFuture<InputT, OutputT> extends AggregateFutureState<OutputT> {
    private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    private final boolean allMustSucceed;
    private final boolean collectsValues;
    /* access modifiers changed from: private */
    @NullableDecl
    public ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

    enum ReleaseResourcesReason {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED
    }

    /* access modifiers changed from: package-private */
    public abstract void collectOneValue(int i, @NullableDecl InputT inputt);

    /* access modifiers changed from: package-private */
    public abstract void handleAllCompleted();

    AggregateFuture(ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures2, boolean allMustSucceed2, boolean collectsValues2) {
        super(futures2.size());
        this.futures = (ImmutableCollection) Preconditions.checkNotNull(futures2);
        this.allMustSucceed = allMustSucceed2;
        this.collectsValues = collectsValues2;
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        super.afterDone();
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        releaseResources(ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
        if (isCancelled() && (immutableCollection != null)) {
            boolean wasInterrupted = wasInterrupted();
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = immutableCollection.iterator();
            while (it.hasNext()) {
                ((Future) it.next()).cancel(wasInterrupted);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        if (immutableCollection == null) {
            return super.pendingToString();
        }
        String valueOf = String.valueOf(immutableCollection);
        return new StringBuilder(String.valueOf(valueOf).length() + 8).append("futures=").append(valueOf).toString();
    }

    /* access modifiers changed from: package-private */
    public final void init() {
        if (this.futures.isEmpty()) {
            handleAllCompleted();
        } else if (this.allMustSucceed) {
            final int index = 0;
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
            while (it.hasNext()) {
                final ListenableFuture<? extends InputT> future = (ListenableFuture) it.next();
                future.addListener(new Runnable() {
                    public void run() {
                        try {
                            if (future.isCancelled()) {
                                ImmutableCollection unused = AggregateFuture.this.futures = null;
                                AggregateFuture.this.cancel(false);
                            } else {
                                AggregateFuture.this.collectValueFromNonCancelledFuture(index, future);
                            }
                        } finally {
                            AggregateFuture.this.decrementCountAndMaybeComplete((ImmutableCollection) null);
                        }
                    }
                }, MoreExecutors.directExecutor());
                index++;
            }
        } else {
            final ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.collectsValues ? this.futures : null;
            Runnable listener = new Runnable() {
                public void run() {
                    AggregateFuture.this.decrementCountAndMaybeComplete(immutableCollection);
                }
            };
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.futures.iterator();
            while (it2.hasNext()) {
                ((ListenableFuture) it2.next()).addListener(listener, MoreExecutors.directExecutor());
            }
        }
    }

    private void handleException(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        if (this.allMustSucceed && !setException(throwable) && addCausalChain(getOrInitSeenExceptions(), throwable)) {
            log(throwable);
        } else if (throwable instanceof Error) {
            log(throwable);
        }
    }

    private void log(Throwable throwable) {
        String message;
        if (throwable instanceof Error) {
            message = "Input Future failed with Error";
        } else {
            message = "An additional input failed after the first. Logging it after adding the first failure as a suppressed exception.";
        }
        logger.log(Level.SEVERE, message, throwable);
    }

    /* access modifiers changed from: package-private */
    public final void addInitialException(Set<Throwable> seen) {
        Preconditions.checkNotNull(seen);
        if (!isCancelled()) {
            addCausalChain(seen, tryInternalFastPathGetFailure());
        }
    }

    /* access modifiers changed from: private */
    public void collectValueFromNonCancelledFuture(int index, Future<? extends InputT> future) {
        try {
            collectOneValue(index, Futures.getDone(future));
        } catch (ExecutionException e) {
            handleException(e.getCause());
        } catch (Throwable t) {
            handleException(t);
        }
    }

    /* access modifiers changed from: private */
    public void decrementCountAndMaybeComplete(@NullableDecl ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
        int newRemaining = decrementRemainingAndGet();
        Preconditions.checkState(newRemaining >= 0, "Less than 0 remaining futures");
        if (newRemaining == 0) {
            processCompleted(futuresIfNeedToCollectAtCompletion);
        }
    }

    private void processCompleted(@NullableDecl ImmutableCollection<? extends Future<? extends InputT>> futuresIfNeedToCollectAtCompletion) {
        if (futuresIfNeedToCollectAtCompletion != null) {
            int i = 0;
            UnmodifiableIterator<? extends Future<? extends InputT>> it = futuresIfNeedToCollectAtCompletion.iterator();
            while (it.hasNext()) {
                Future<? extends InputT> future = (Future) it.next();
                if (!future.isCancelled()) {
                    collectValueFromNonCancelledFuture(i, future);
                }
                i++;
            }
        }
        clearSeenExceptions();
        handleAllCompleted();
        releaseResources(ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
    }

    /* access modifiers changed from: package-private */
    public void releaseResources(ReleaseResourcesReason reason) {
        Preconditions.checkNotNull(reason);
        this.futures = null;
    }

    private static boolean addCausalChain(Set<Throwable> seen, Throwable t) {
        while (t != null) {
            if (!seen.add(t)) {
                return false;
            }
            t = t.getCause();
        }
        return true;
    }
}
