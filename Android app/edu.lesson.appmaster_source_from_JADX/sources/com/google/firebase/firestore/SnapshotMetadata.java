package com.google.firebase.firestore;

public class SnapshotMetadata {
    private final boolean hasPendingWrites;
    private final boolean isFromCache;

    SnapshotMetadata(boolean hasPendingWrites2, boolean isFromCache2) {
        this.hasPendingWrites = hasPendingWrites2;
        this.isFromCache = isFromCache2;
    }

    public boolean hasPendingWrites() {
        return this.hasPendingWrites;
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        SnapshotMetadata other = (SnapshotMetadata) obj;
        if (this.hasPendingWrites == other.hasPendingWrites && this.isFromCache == other.isFromCache) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((int) this.hasPendingWrites) * true) + (this.isFromCache ? 1 : 0);
    }

    public String toString() {
        return "SnapshotMetadata{hasPendingWrites=" + this.hasPendingWrites + ", isFromCache=" + this.isFromCache + '}';
    }
}
