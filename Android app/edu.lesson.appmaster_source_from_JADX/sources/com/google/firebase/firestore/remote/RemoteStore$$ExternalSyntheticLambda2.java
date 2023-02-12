package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.ConnectivityMonitor;

public final /* synthetic */ class RemoteStore$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ RemoteStore f$0;
    public final /* synthetic */ ConnectivityMonitor.NetworkStatus f$1;

    public /* synthetic */ RemoteStore$$ExternalSyntheticLambda2(RemoteStore remoteStore, ConnectivityMonitor.NetworkStatus networkStatus) {
        this.f$0 = remoteStore;
        this.f$1 = networkStatus;
    }

    public final void run() {
        this.f$0.m454lambda$new$0$comgooglefirebasefirestoreremoteRemoteStore(this.f$1);
    }
}
