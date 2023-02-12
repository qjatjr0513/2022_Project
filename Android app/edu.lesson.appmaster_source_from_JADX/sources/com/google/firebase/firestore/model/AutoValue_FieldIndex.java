package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.List;

final class AutoValue_FieldIndex extends FieldIndex {
    private final String collectionGroup;
    private final int indexId;
    private final FieldIndex.IndexState indexState;
    private final List<FieldIndex.Segment> segments;

    AutoValue_FieldIndex(int indexId2, String collectionGroup2, List<FieldIndex.Segment> segments2, FieldIndex.IndexState indexState2) {
        this.indexId = indexId2;
        if (collectionGroup2 != null) {
            this.collectionGroup = collectionGroup2;
            if (segments2 != null) {
                this.segments = segments2;
                if (indexState2 != null) {
                    this.indexState = indexState2;
                    return;
                }
                throw new NullPointerException("Null indexState");
            }
            throw new NullPointerException("Null segments");
        }
        throw new NullPointerException("Null collectionGroup");
    }

    public int getIndexId() {
        return this.indexId;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public List<FieldIndex.Segment> getSegments() {
        return this.segments;
    }

    public FieldIndex.IndexState getIndexState() {
        return this.indexState;
    }

    public String toString() {
        return "FieldIndex{indexId=" + this.indexId + ", collectionGroup=" + this.collectionGroup + ", segments=" + this.segments + ", indexState=" + this.indexState + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIndex)) {
            return false;
        }
        FieldIndex that = (FieldIndex) o;
        if (this.indexId != that.getIndexId() || !this.collectionGroup.equals(that.getCollectionGroup()) || !this.segments.equals(that.getSegments()) || !this.indexState.equals(that.getIndexState())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.indexId) * 1000003) ^ this.collectionGroup.hashCode()) * 1000003) ^ this.segments.hashCode()) * 1000003) ^ this.indexState.hashCode();
    }
}
