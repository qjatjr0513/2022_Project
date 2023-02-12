package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;

interface ReferenceDelegate {
    void addReference(DocumentKey documentKey);

    long getCurrentSequenceNumber();

    void onTransactionCommitted();

    void onTransactionStarted();

    void removeMutationReference(DocumentKey documentKey);

    void removeReference(DocumentKey documentKey);

    void removeTarget(TargetData targetData);

    void setInMemoryPins(ReferenceSet referenceSet);

    void updateLimboDocument(DocumentKey documentKey);
}
