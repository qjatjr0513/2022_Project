package com.google.firebase.firestore.local;

import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class MemoryLruReferenceDelegate$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ long[] f$0;

    public /* synthetic */ MemoryLruReferenceDelegate$$ExternalSyntheticLambda0(long[] jArr) {
        this.f$0 = jArr;
    }

    public final void accept(Object obj) {
        MemoryLruReferenceDelegate.lambda$getSequenceNumberCount$0(this.f$0, (Long) obj);
    }
}
