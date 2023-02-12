package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig;

final class AutoValue_EventStoreConfig extends EventStoreConfig {
    private final int criticalSectionEnterTimeoutMs;
    private final long eventCleanUpAge;
    private final int loadBatchSize;
    private final int maxBlobByteSizePerRow;
    private final long maxStorageSizeInBytes;

    private AutoValue_EventStoreConfig(long maxStorageSizeInBytes2, int loadBatchSize2, int criticalSectionEnterTimeoutMs2, long eventCleanUpAge2, int maxBlobByteSizePerRow2) {
        this.maxStorageSizeInBytes = maxStorageSizeInBytes2;
        this.loadBatchSize = loadBatchSize2;
        this.criticalSectionEnterTimeoutMs = criticalSectionEnterTimeoutMs2;
        this.eventCleanUpAge = eventCleanUpAge2;
        this.maxBlobByteSizePerRow = maxBlobByteSizePerRow2;
    }

    /* access modifiers changed from: package-private */
    public long getMaxStorageSizeInBytes() {
        return this.maxStorageSizeInBytes;
    }

    /* access modifiers changed from: package-private */
    public int getLoadBatchSize() {
        return this.loadBatchSize;
    }

    /* access modifiers changed from: package-private */
    public int getCriticalSectionEnterTimeoutMs() {
        return this.criticalSectionEnterTimeoutMs;
    }

    /* access modifiers changed from: package-private */
    public long getEventCleanUpAge() {
        return this.eventCleanUpAge;
    }

    /* access modifiers changed from: package-private */
    public int getMaxBlobByteSizePerRow() {
        return this.maxBlobByteSizePerRow;
    }

    public String toString() {
        return "EventStoreConfig{maxStorageSizeInBytes=" + this.maxStorageSizeInBytes + ", loadBatchSize=" + this.loadBatchSize + ", criticalSectionEnterTimeoutMs=" + this.criticalSectionEnterTimeoutMs + ", eventCleanUpAge=" + this.eventCleanUpAge + ", maxBlobByteSizePerRow=" + this.maxBlobByteSizePerRow + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventStoreConfig)) {
            return false;
        }
        EventStoreConfig that = (EventStoreConfig) o;
        if (this.maxStorageSizeInBytes == that.getMaxStorageSizeInBytes() && this.loadBatchSize == that.getLoadBatchSize() && this.criticalSectionEnterTimeoutMs == that.getCriticalSectionEnterTimeoutMs() && this.eventCleanUpAge == that.getEventCleanUpAge() && this.maxBlobByteSizePerRow == that.getMaxBlobByteSizePerRow()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.maxStorageSizeInBytes;
        long j2 = this.eventCleanUpAge;
        return (((((((((1 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.loadBatchSize) * 1000003) ^ this.criticalSectionEnterTimeoutMs) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.maxBlobByteSizePerRow;
    }

    static final class Builder extends EventStoreConfig.Builder {
        private Integer criticalSectionEnterTimeoutMs;
        private Long eventCleanUpAge;
        private Integer loadBatchSize;
        private Integer maxBlobByteSizePerRow;
        private Long maxStorageSizeInBytes;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public EventStoreConfig.Builder setMaxStorageSizeInBytes(long maxStorageSizeInBytes2) {
            this.maxStorageSizeInBytes = Long.valueOf(maxStorageSizeInBytes2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public EventStoreConfig.Builder setLoadBatchSize(int loadBatchSize2) {
            this.loadBatchSize = Integer.valueOf(loadBatchSize2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public EventStoreConfig.Builder setCriticalSectionEnterTimeoutMs(int criticalSectionEnterTimeoutMs2) {
            this.criticalSectionEnterTimeoutMs = Integer.valueOf(criticalSectionEnterTimeoutMs2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public EventStoreConfig.Builder setEventCleanUpAge(long eventCleanUpAge2) {
            this.eventCleanUpAge = Long.valueOf(eventCleanUpAge2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public EventStoreConfig.Builder setMaxBlobByteSizePerRow(int maxBlobByteSizePerRow2) {
            this.maxBlobByteSizePerRow = Integer.valueOf(maxBlobByteSizePerRow2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public EventStoreConfig build() {
            String missing = "";
            if (this.maxStorageSizeInBytes == null) {
                missing = missing + " maxStorageSizeInBytes";
            }
            if (this.loadBatchSize == null) {
                missing = missing + " loadBatchSize";
            }
            if (this.criticalSectionEnterTimeoutMs == null) {
                missing = missing + " criticalSectionEnterTimeoutMs";
            }
            if (this.eventCleanUpAge == null) {
                missing = missing + " eventCleanUpAge";
            }
            if (this.maxBlobByteSizePerRow == null) {
                missing = missing + " maxBlobByteSizePerRow";
            }
            if (missing.isEmpty()) {
                return new AutoValue_EventStoreConfig(this.maxStorageSizeInBytes.longValue(), this.loadBatchSize.intValue(), this.criticalSectionEnterTimeoutMs.intValue(), this.eventCleanUpAge.longValue(), this.maxBlobByteSizePerRow.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
