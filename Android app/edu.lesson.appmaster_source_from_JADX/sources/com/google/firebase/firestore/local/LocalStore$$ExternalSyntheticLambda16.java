package com.google.firebase.firestore.local;

import com.google.firebase.firestore.util.Supplier;

public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda16 implements Supplier {
    public final /* synthetic */ LocalStore f$0;
    public final /* synthetic */ LruGarbageCollector f$1;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda16(LocalStore localStore, LruGarbageCollector lruGarbageCollector) {
        this.f$0 = localStore;
        this.f$1 = lruGarbageCollector;
    }

    public final Object get() {
        return this.f$0.mo9156x1db5499a(this.f$1);
    }
}
