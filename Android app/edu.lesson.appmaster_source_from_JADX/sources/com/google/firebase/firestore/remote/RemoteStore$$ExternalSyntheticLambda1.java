package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class RemoteStore$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ RemoteStore f$0;
    public final /* synthetic */ AsyncQueue f$1;

    public /* synthetic */ RemoteStore$$ExternalSyntheticLambda1(RemoteStore remoteStore, AsyncQueue asyncQueue) {
        this.f$0 = remoteStore;
        this.f$1 = asyncQueue;
    }

    public final void accept(Object obj) {
        this.f$0.m455lambda$new$1$comgooglefirebasefirestoreremoteRemoteStore(this.f$1, (ConnectivityMonitor.NetworkStatus) obj);
    }
}
