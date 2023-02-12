package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.util.Supplier;

public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda18 implements Supplier {
    public final /* synthetic */ LocalStore f$0;
    public final /* synthetic */ RemoteEvent f$1;
    public final /* synthetic */ SnapshotVersion f$2;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda18(LocalStore localStore, RemoteEvent remoteEvent, SnapshotVersion snapshotVersion) {
        this.f$0 = localStore;
        this.f$1 = remoteEvent;
        this.f$2 = snapshotVersion;
    }

    public final Object get() {
        return this.f$0.mo9155xfb616aa7(this.f$1, this.f$2);
    }
}
