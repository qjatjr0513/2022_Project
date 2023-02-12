package okio;

import java.util.concurrent.TimeUnit;

final class PushableTimeout extends Timeout {
    private long originalDeadlineNanoTime;
    private boolean originalHasDeadline;
    private long originalTimeoutNanos;
    private Timeout pushed;

    PushableTimeout() {
    }

    /* access modifiers changed from: package-private */
    public void push(Timeout pushed2) {
        this.pushed = pushed2;
        boolean hasDeadline = pushed2.hasDeadline();
        this.originalHasDeadline = hasDeadline;
        this.originalDeadlineNanoTime = hasDeadline ? pushed2.deadlineNanoTime() : -1;
        long timeoutNanos = pushed2.timeoutNanos();
        this.originalTimeoutNanos = timeoutNanos;
        pushed2.timeout(minTimeout(timeoutNanos, timeoutNanos()), TimeUnit.NANOSECONDS);
        if (this.originalHasDeadline && hasDeadline()) {
            pushed2.deadlineNanoTime(Math.min(deadlineNanoTime(), this.originalDeadlineNanoTime));
        } else if (hasDeadline()) {
            pushed2.deadlineNanoTime(deadlineNanoTime());
        }
    }

    /* access modifiers changed from: package-private */
    public void pop() {
        this.pushed.timeout(this.originalTimeoutNanos, TimeUnit.NANOSECONDS);
        if (this.originalHasDeadline) {
            this.pushed.deadlineNanoTime(this.originalDeadlineNanoTime);
        } else {
            this.pushed.clearDeadline();
        }
    }
}
