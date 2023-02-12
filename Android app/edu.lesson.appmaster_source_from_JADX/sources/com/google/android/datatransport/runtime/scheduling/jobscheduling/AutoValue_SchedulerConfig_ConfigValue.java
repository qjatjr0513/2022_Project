package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import java.util.Set;

final class AutoValue_SchedulerConfig_ConfigValue extends SchedulerConfig.ConfigValue {
    private final long delta;
    private final Set<SchedulerConfig.Flag> flags;
    private final long maxAllowedDelay;

    private AutoValue_SchedulerConfig_ConfigValue(long delta2, long maxAllowedDelay2, Set<SchedulerConfig.Flag> flags2) {
        this.delta = delta2;
        this.maxAllowedDelay = maxAllowedDelay2;
        this.flags = flags2;
    }

    /* access modifiers changed from: package-private */
    public long getDelta() {
        return this.delta;
    }

    /* access modifiers changed from: package-private */
    public long getMaxAllowedDelay() {
        return this.maxAllowedDelay;
    }

    /* access modifiers changed from: package-private */
    public Set<SchedulerConfig.Flag> getFlags() {
        return this.flags;
    }

    public String toString() {
        return "ConfigValue{delta=" + this.delta + ", maxAllowedDelay=" + this.maxAllowedDelay + ", flags=" + this.flags + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SchedulerConfig.ConfigValue)) {
            return false;
        }
        SchedulerConfig.ConfigValue that = (SchedulerConfig.ConfigValue) o;
        if (this.delta == that.getDelta() && this.maxAllowedDelay == that.getMaxAllowedDelay() && this.flags.equals(that.getFlags())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.delta;
        long j2 = this.maxAllowedDelay;
        return (((((1 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.flags.hashCode();
    }

    static final class Builder extends SchedulerConfig.ConfigValue.Builder {
        private Long delta;
        private Set<SchedulerConfig.Flag> flags;
        private Long maxAllowedDelay;

        Builder() {
        }

        public SchedulerConfig.ConfigValue.Builder setDelta(long delta2) {
            this.delta = Long.valueOf(delta2);
            return this;
        }

        public SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long maxAllowedDelay2) {
            this.maxAllowedDelay = Long.valueOf(maxAllowedDelay2);
            return this;
        }

        public SchedulerConfig.ConfigValue.Builder setFlags(Set<SchedulerConfig.Flag> flags2) {
            if (flags2 != null) {
                this.flags = flags2;
                return this;
            }
            throw new NullPointerException("Null flags");
        }

        public SchedulerConfig.ConfigValue build() {
            String missing = "";
            if (this.delta == null) {
                missing = missing + " delta";
            }
            if (this.maxAllowedDelay == null) {
                missing = missing + " maxAllowedDelay";
            }
            if (this.flags == null) {
                missing = missing + " flags";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SchedulerConfig_ConfigValue(this.delta.longValue(), this.maxAllowedDelay.longValue(), this.flags);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
