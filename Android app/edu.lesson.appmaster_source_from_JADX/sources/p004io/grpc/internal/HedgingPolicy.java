package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.HedgingPolicy */
final class HedgingPolicy {
    final long hedgingDelayNanos;
    final int maxAttempts;
    final Set<Status.Code> nonFatalStatusCodes;

    HedgingPolicy(int maxAttempts2, long hedgingDelayNanos2, Set<Status.Code> nonFatalStatusCodes2) {
        this.maxAttempts = maxAttempts2;
        this.hedgingDelayNanos = hedgingDelayNanos2;
        this.nonFatalStatusCodes = ImmutableSet.copyOf(nonFatalStatusCodes2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        HedgingPolicy that = (HedgingPolicy) other;
        if (this.maxAttempts == that.maxAttempts && this.hedgingDelayNanos == that.hedgingDelayNanos && Objects.equal(this.nonFatalStatusCodes, that.nonFatalStatusCodes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.hedgingDelayNanos), this.nonFatalStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("maxAttempts", this.maxAttempts).add("hedgingDelayNanos", this.hedgingDelayNanos).add("nonFatalStatusCodes", (Object) this.nonFatalStatusCodes).toString();
    }
}
