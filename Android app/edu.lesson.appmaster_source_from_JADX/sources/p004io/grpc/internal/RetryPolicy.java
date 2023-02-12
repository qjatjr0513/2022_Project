package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.RetryPolicy */
final class RetryPolicy {
    final double backoffMultiplier;
    final long initialBackoffNanos;
    final int maxAttempts;
    final long maxBackoffNanos;
    @Nullable
    final Long perAttemptRecvTimeoutNanos;
    final Set<Status.Code> retryableStatusCodes;

    RetryPolicy(int maxAttempts2, long initialBackoffNanos2, long maxBackoffNanos2, double backoffMultiplier2, @Nullable Long perAttemptRecvTimeoutNanos2, @Nonnull Set<Status.Code> retryableStatusCodes2) {
        this.maxAttempts = maxAttempts2;
        this.initialBackoffNanos = initialBackoffNanos2;
        this.maxBackoffNanos = maxBackoffNanos2;
        this.backoffMultiplier = backoffMultiplier2;
        this.perAttemptRecvTimeoutNanos = perAttemptRecvTimeoutNanos2;
        this.retryableStatusCodes = ImmutableSet.copyOf(retryableStatusCodes2);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.initialBackoffNanos), Long.valueOf(this.maxBackoffNanos), Double.valueOf(this.backoffMultiplier), this.perAttemptRecvTimeoutNanos, this.retryableStatusCodes);
    }

    public boolean equals(Object other) {
        if (!(other instanceof RetryPolicy)) {
            return false;
        }
        RetryPolicy that = (RetryPolicy) other;
        if (this.maxAttempts == that.maxAttempts && this.initialBackoffNanos == that.initialBackoffNanos && this.maxBackoffNanos == that.maxBackoffNanos && Double.compare(this.backoffMultiplier, that.backoffMultiplier) == 0 && Objects.equal(this.perAttemptRecvTimeoutNanos, that.perAttemptRecvTimeoutNanos) && Objects.equal(this.retryableStatusCodes, that.retryableStatusCodes)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("maxAttempts", this.maxAttempts).add("initialBackoffNanos", this.initialBackoffNanos).add("maxBackoffNanos", this.maxBackoffNanos).add("backoffMultiplier", this.backoffMultiplier).add("perAttemptRecvTimeoutNanos", (Object) this.perAttemptRecvTimeoutNanos).add("retryableStatusCodes", (Object) this.retryableStatusCodes).toString();
    }
}
