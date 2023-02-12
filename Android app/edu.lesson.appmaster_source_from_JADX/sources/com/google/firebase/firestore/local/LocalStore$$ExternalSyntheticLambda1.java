package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.util.Supplier;
import java.util.List;
import java.util.Set;

public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ LocalStore f$0;
    public final /* synthetic */ Set f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ Timestamp f$3;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda1(LocalStore localStore, Set set, List list, Timestamp timestamp) {
        this.f$0 = localStore;
        this.f$1 = set;
        this.f$2 = list;
        this.f$3 = timestamp;
    }

    public final Object get() {
        return this.f$0.mo9169x4f0ff958(this.f$1, this.f$2, this.f$3);
    }
}
