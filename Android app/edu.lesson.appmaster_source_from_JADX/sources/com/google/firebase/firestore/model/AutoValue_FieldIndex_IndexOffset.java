package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

final class AutoValue_FieldIndex_IndexOffset extends FieldIndex.IndexOffset {
    private final DocumentKey documentKey;
    private final int largestBatchId;
    private final SnapshotVersion readTime;

    AutoValue_FieldIndex_IndexOffset(SnapshotVersion readTime2, DocumentKey documentKey2, int largestBatchId2) {
        if (readTime2 != null) {
            this.readTime = readTime2;
            if (documentKey2 != null) {
                this.documentKey = documentKey2;
                this.largestBatchId = largestBatchId2;
                return;
            }
            throw new NullPointerException("Null documentKey");
        }
        throw new NullPointerException("Null readTime");
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    public int getLargestBatchId() {
        return this.largestBatchId;
    }

    public String toString() {
        return "IndexOffset{readTime=" + this.readTime + ", documentKey=" + this.documentKey + ", largestBatchId=" + this.largestBatchId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIndex.IndexOffset)) {
            return false;
        }
        FieldIndex.IndexOffset that = (FieldIndex.IndexOffset) o;
        if (!this.readTime.equals(that.getReadTime()) || !this.documentKey.equals(that.getDocumentKey()) || this.largestBatchId != that.getLargestBatchId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.readTime.hashCode()) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ this.largestBatchId;
    }
}
