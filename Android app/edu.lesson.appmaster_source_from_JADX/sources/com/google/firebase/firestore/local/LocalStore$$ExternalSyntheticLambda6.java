package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.bundle.NamedQuery;

public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ LocalStore f$0;
    public final /* synthetic */ NamedQuery f$1;
    public final /* synthetic */ TargetData f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ImmutableSortedSet f$4;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda6(LocalStore localStore, NamedQuery namedQuery, TargetData targetData, int i, ImmutableSortedSet immutableSortedSet) {
        this.f$0 = localStore;
        this.f$1 = namedQuery;
        this.f$2 = targetData;
        this.f$3 = i;
        this.f$4 = immutableSortedSet;
    }

    public final void run() {
        this.f$0.mo9165x3730c4c0(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
