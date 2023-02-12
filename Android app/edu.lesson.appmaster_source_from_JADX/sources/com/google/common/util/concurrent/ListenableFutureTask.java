package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {
    private final ExecutionList executionList = new ExecutionList();

    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<>(callable);
    }

    public static <V> ListenableFutureTask<V> create(Runnable runnable, @NullableDecl V result) {
        return new ListenableFutureTask<>(runnable, result);
    }

    ListenableFutureTask(Callable<V> callable) {
        super(callable);
    }

    ListenableFutureTask(Runnable runnable, @NullableDecl V result) {
        super(runnable, result);
    }

    public void addListener(Runnable listener, Executor exec) {
        this.executionList.add(listener, exec);
    }

    public V get(long timeout, TimeUnit unit) throws TimeoutException, InterruptedException, ExecutionException {
        long timeoutNanos = unit.toNanos(timeout);
        if (timeoutNanos <= 2147483647999999999L) {
            return super.get(timeout, unit);
        }
        return super.get(Math.min(timeoutNanos, 2147483647999999999L), TimeUnit.NANOSECONDS);
    }

    /* access modifiers changed from: protected */
    public void done() {
        this.executionList.execute();
    }
}
