package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.util.Date;

public class ExponentialBackoff {
    public static final double DEFAULT_BACKOFF_FACTOR = 1.5d;
    public static final long DEFAULT_BACKOFF_INITIAL_DELAY_MS = 1000;
    public static final long DEFAULT_BACKOFF_MAX_DELAY_MS = 60000;
    private final double backoffFactor;
    private long currentBaseMs;
    private final long initialDelayMs;
    private long lastAttemptTime;
    private final long maxDelayMs;
    private long nextMaxDelayMs;
    private final AsyncQueue queue;
    private final AsyncQueue.TimerId timerId;
    private AsyncQueue.DelayedTask timerTask;

    public ExponentialBackoff(AsyncQueue queue2, AsyncQueue.TimerId timerId2, long initialDelayMs2, double backoffFactor2, long maxDelayMs2) {
        this.queue = queue2;
        this.timerId = timerId2;
        this.initialDelayMs = initialDelayMs2;
        this.backoffFactor = backoffFactor2;
        this.maxDelayMs = maxDelayMs2;
        this.nextMaxDelayMs = maxDelayMs2;
        this.lastAttemptTime = new Date().getTime();
        reset();
    }

    public ExponentialBackoff(AsyncQueue queue2, AsyncQueue.TimerId timerId2) {
        this(queue2, timerId2, 1000, 1.5d, DEFAULT_BACKOFF_MAX_DELAY_MS);
    }

    public void reset() {
        this.currentBaseMs = 0;
    }

    public void resetToMax() {
        this.currentBaseMs = this.nextMaxDelayMs;
    }

    public void setTemporaryMaxDelay(long newMax) {
        this.nextMaxDelayMs = newMax;
    }

    public void backoffAndRun(Runnable task) {
        cancel();
        long desiredDelayWithJitterMs = this.currentBaseMs + jitterDelayMs();
        long delaySoFarMs = Math.max(0, new Date().getTime() - this.lastAttemptTime);
        long remainingDelayMs = Math.max(0, desiredDelayWithJitterMs - delaySoFarMs);
        if (this.currentBaseMs > 0) {
            Logger.debug(getClass().getSimpleName(), "Backing off for %d ms (base delay: %d ms, delay with jitter: %d ms, last attempt: %d ms ago)", Long.valueOf(remainingDelayMs), Long.valueOf(this.currentBaseMs), Long.valueOf(desiredDelayWithJitterMs), Long.valueOf(delaySoFarMs));
        }
        this.timerTask = this.queue.enqueueAfterDelay(this.timerId, remainingDelayMs, new ExponentialBackoff$$ExternalSyntheticLambda0(this, task));
        long j = (long) (((double) this.currentBaseMs) * this.backoffFactor);
        this.currentBaseMs = j;
        long j2 = this.initialDelayMs;
        if (j < j2) {
            this.currentBaseMs = j2;
        } else {
            long j3 = this.nextMaxDelayMs;
            if (j > j3) {
                this.currentBaseMs = j3;
            }
        }
        this.nextMaxDelayMs = this.maxDelayMs;
    }

    /* renamed from: lambda$backoffAndRun$0$com-google-firebase-firestore-util-ExponentialBackoff */
    public /* synthetic */ void mo9996x589b7455(Runnable task) {
        this.lastAttemptTime = new Date().getTime();
        task.run();
    }

    public void cancel() {
        AsyncQueue.DelayedTask delayedTask = this.timerTask;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.timerTask = null;
        }
    }

    private long jitterDelayMs() {
        return (long) ((Math.random() - 0.5d) * ((double) this.currentBaseMs));
    }
}
