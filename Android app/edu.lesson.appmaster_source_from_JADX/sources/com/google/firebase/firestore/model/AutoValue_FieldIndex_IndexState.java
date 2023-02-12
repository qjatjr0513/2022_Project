package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

final class AutoValue_FieldIndex_IndexState extends FieldIndex.IndexState {
    private final FieldIndex.IndexOffset offset;
    private final long sequenceNumber;

    AutoValue_FieldIndex_IndexState(long sequenceNumber2, FieldIndex.IndexOffset offset2) {
        this.sequenceNumber = sequenceNumber2;
        if (offset2 != null) {
            this.offset = offset2;
            return;
        }
        throw new NullPointerException("Null offset");
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public FieldIndex.IndexOffset getOffset() {
        return this.offset;
    }

    public String toString() {
        return "IndexState{sequenceNumber=" + this.sequenceNumber + ", offset=" + this.offset + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIndex.IndexState)) {
            return false;
        }
        FieldIndex.IndexState that = (FieldIndex.IndexState) o;
        if (this.sequenceNumber != that.getSequenceNumber() || !this.offset.equals(that.getOffset())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.sequenceNumber;
        return (((1 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.offset.hashCode();
    }
}
