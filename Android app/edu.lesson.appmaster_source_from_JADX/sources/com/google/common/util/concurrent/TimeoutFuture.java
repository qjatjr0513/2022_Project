package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class TimeoutFuture<V> extends FluentFuture.TrustedFuture<V> {
    /* access modifiers changed from: private */
    @NullableDecl
    public ListenableFuture<V> delegateRef;
    /* access modifiers changed from: private */
    @NullableDecl
    public ScheduledFuture<?> timer;

    static <V> ListenableFuture<V> create(ListenableFuture<V> delegate, long time, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
        TimeoutFuture<V> result = new TimeoutFuture<>(delegate);
        Fire<V> fire = new Fire<>(result);
        result.timer = scheduledExecutor.schedule(fire, time, unit);
        delegate.addListener(fire, MoreExecutors.directExecutor());
        return result;
    }

    private TimeoutFuture(ListenableFuture<V> delegate) {
        this.delegateRef = (ListenableFuture) Preconditions.checkNotNull(delegate);
    }

    private static final class Fire<V> implements Runnable {
        @NullableDecl
        TimeoutFuture<V> timeoutFutureRef;

        Fire(TimeoutFuture<V> timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        public void run() {
            ListenableFuture<V> delegate;
            String message;
            TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture != null && (delegate = timeoutFuture.delegateRef) != null) {
                this.timeoutFutureRef = null;
                if (delegate.isDone()) {
                    timeoutFuture.setFuture(delegate);
                    return;
                }
                try {
                    ScheduledFuture<?> timer = timeoutFuture.timer;
                    ScheduledFuture unused = timeoutFuture.timer = null;
                    message = "Timed out";
                    if (timer != null) {
                        long overDelayMs = Math.abs(timer.getDelay(TimeUnit.MILLISECONDS));
                        if (overDelayMs > 10) {
                            String valueOf = String.valueOf(message);
                            message = new StringBuilder(String.valueOf(valueOf).length() + 66).append(valueOf).append(" (timeout delayed by ").append(overDelayMs).append(" ms after scheduled time)").toString();
                        }
                    }
                    String valueOf2 = String.valueOf(message);
                    String valueOf3 = String.valueOf(delegate);
                    timeoutFuture.setException(new TimeoutFutureException(new StringBuilder(String.valueOf(valueOf2).length() + 2 + String.valueOf(valueOf3).length()).append(valueOf2).append(": ").append(valueOf3).toString()));
                    delegate.cancel(true);
                } catch (Throwable th) {
                    delegate.cancel(true);
                    throw th;
                }
            }
        }
    }

    private static final class TimeoutFutureException extends TimeoutException {
        private TimeoutFutureException(String message) {
            super(message);
        }

        public synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ListenableFuture<V> listenableFuture = this.delegateRef;
        ScheduledFuture<?> localTimer = this.timer;
        if (listenableFuture == null) {
            return null;
        }
        String valueOf = String.valueOf(listenableFuture);
        String message = new StringBuilder(String.valueOf(valueOf).length() + 14).append("inputFuture=[").append(valueOf).append("]").toString();
        if (localTimer == null) {
            return message;
        }
        long delay = localTimer.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return message;
        }
        String valueOf2 = String.valueOf(message);
        return new StringBuilder(String.valueOf(valueOf2).length() + 43).append(valueOf2).append(", remaining delay=[").append(delay).append(" ms]").toString();
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
        maybePropagateCancellationTo(this.delegateRef);
        Future<?> localTimer = this.timer;
        if (localTimer != null) {
            localTimer.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }
}
