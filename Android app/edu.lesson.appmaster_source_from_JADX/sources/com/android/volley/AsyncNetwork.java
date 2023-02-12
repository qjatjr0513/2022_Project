package com.android.volley;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AsyncNetwork implements Network {
    private ExecutorService mBlockingExecutor;
    private ExecutorService mNonBlockingExecutor;
    private ScheduledExecutorService mNonBlockingScheduledExecutor;

    public interface OnRequestComplete {
        void onError(VolleyError volleyError);

        void onSuccess(NetworkResponse networkResponse);
    }

    public abstract void performRequest(Request<?> request, OnRequestComplete onRequestComplete);

    protected AsyncNetwork() {
    }

    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<NetworkResponse> response = new AtomicReference<>();
        final AtomicReference<VolleyError> error = new AtomicReference<>();
        performRequest(request, new OnRequestComplete() {
            public void onSuccess(NetworkResponse networkResponse) {
                response.set(networkResponse);
                latch.countDown();
            }

            public void onError(VolleyError volleyError) {
                error.set(volleyError);
                latch.countDown();
            }
        });
        try {
            latch.await();
            if (response.get() != null) {
                return response.get();
            }
            if (error.get() != null) {
                throw error.get();
            }
            throw new VolleyError("Neither response entry was set");
        } catch (InterruptedException e) {
            VolleyLog.m393e(e, "while waiting for CountDownLatch", new Object[0]);
            Thread.currentThread().interrupt();
            throw new VolleyError((Throwable) e);
        }
    }

    public void setNonBlockingExecutor(ExecutorService executor) {
        this.mNonBlockingExecutor = executor;
    }

    public void setBlockingExecutor(ExecutorService executor) {
        this.mBlockingExecutor = executor;
    }

    public void setNonBlockingScheduledExecutor(ScheduledExecutorService executor) {
        this.mNonBlockingScheduledExecutor = executor;
    }

    /* access modifiers changed from: protected */
    public ExecutorService getBlockingExecutor() {
        return this.mBlockingExecutor;
    }

    /* access modifiers changed from: protected */
    public ExecutorService getNonBlockingExecutor() {
        return this.mNonBlockingExecutor;
    }

    /* access modifiers changed from: protected */
    public ScheduledExecutorService getNonBlockingScheduledExecutor() {
        return this.mNonBlockingScheduledExecutor;
    }
}
