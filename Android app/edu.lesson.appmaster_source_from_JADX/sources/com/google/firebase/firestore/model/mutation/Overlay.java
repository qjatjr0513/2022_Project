package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.DocumentKey;

public abstract class Overlay {
    public abstract int getLargestBatchId();

    public abstract Mutation getMutation();

    public static Overlay create(int largestBatchId, Mutation mutation) {
        return new AutoValue_Overlay(largestBatchId, mutation);
    }

    public DocumentKey getKey() {
        return getMutation().getKey();
    }
}
