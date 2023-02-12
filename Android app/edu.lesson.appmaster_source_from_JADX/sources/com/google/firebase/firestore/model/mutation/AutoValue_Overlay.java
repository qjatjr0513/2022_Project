package com.google.firebase.firestore.model.mutation;

final class AutoValue_Overlay extends Overlay {
    private final int largestBatchId;
    private final Mutation mutation;

    AutoValue_Overlay(int largestBatchId2, Mutation mutation2) {
        this.largestBatchId = largestBatchId2;
        if (mutation2 != null) {
            this.mutation = mutation2;
            return;
        }
        throw new NullPointerException("Null mutation");
    }

    public int getLargestBatchId() {
        return this.largestBatchId;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public String toString() {
        return "Overlay{largestBatchId=" + this.largestBatchId + ", mutation=" + this.mutation + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Overlay)) {
            return false;
        }
        Overlay that = (Overlay) o;
        if (this.largestBatchId != that.getLargestBatchId() || !this.mutation.equals(that.getMutation())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.largestBatchId) * 1000003) ^ this.mutation.hashCode();
    }
}
