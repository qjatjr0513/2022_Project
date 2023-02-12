package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

public class BundleMetadata implements BundleElement {
    private final String bundleId;
    private final SnapshotVersion createTime;
    private final int schemaVersion;
    private final long totalBytes;
    private final int totalDocuments;

    public BundleMetadata(String bundleId2, int schemaVersion2, SnapshotVersion createTime2, int totalDocuments2, long totalBytes2) {
        this.bundleId = bundleId2;
        this.schemaVersion = schemaVersion2;
        this.createTime = createTime2;
        this.totalDocuments = totalDocuments2;
        this.totalBytes = totalBytes2;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public int getSchemaVersion() {
        return this.schemaVersion;
    }

    public SnapshotVersion getCreateTime() {
        return this.createTime;
    }

    public int getTotalDocuments() {
        return this.totalDocuments;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BundleMetadata that = (BundleMetadata) o;
        if (this.schemaVersion == that.schemaVersion && this.totalDocuments == that.totalDocuments && this.totalBytes == that.totalBytes && this.bundleId.equals(that.bundleId)) {
            return this.createTime.equals(that.createTime);
        }
        return false;
    }

    public int hashCode() {
        long j = this.totalBytes;
        return (((((((this.bundleId.hashCode() * 31) + this.schemaVersion) * 31) + this.totalDocuments) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.createTime.hashCode();
    }
}
