package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;
import java.util.Comparator;

class DocumentReference {
    static final Comparator<DocumentReference> BY_KEY = DocumentReference$$ExternalSyntheticLambda0.INSTANCE;
    static final Comparator<DocumentReference> BY_TARGET = DocumentReference$$ExternalSyntheticLambda1.INSTANCE;
    private final DocumentKey key;
    private final int targetOrBatchId;

    public DocumentReference(DocumentKey key2, int targetOrBatchId2) {
        this.key = key2;
        this.targetOrBatchId = targetOrBatchId2;
    }

    /* access modifiers changed from: package-private */
    public DocumentKey getKey() {
        return this.key;
    }

    /* access modifiers changed from: package-private */
    public int getId() {
        return this.targetOrBatchId;
    }

    static /* synthetic */ int lambda$static$0(DocumentReference o1, DocumentReference o2) {
        int keyComp = o1.key.compareTo(o2.key);
        if (keyComp != 0) {
            return keyComp;
        }
        return Util.compareIntegers(o1.targetOrBatchId, o2.targetOrBatchId);
    }

    static /* synthetic */ int lambda$static$1(DocumentReference o1, DocumentReference o2) {
        int targetComp = Util.compareIntegers(o1.targetOrBatchId, o2.targetOrBatchId);
        if (targetComp != 0) {
            return targetComp;
        }
        return o1.key.compareTo(o2.key);
    }
}
