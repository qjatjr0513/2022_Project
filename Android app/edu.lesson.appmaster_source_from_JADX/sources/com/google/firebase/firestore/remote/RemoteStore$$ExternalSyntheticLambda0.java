package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.core.OnlineState;
import com.google.firebase.firestore.remote.OnlineStateTracker;
import com.google.firebase.firestore.remote.RemoteStore;

public final /* synthetic */ class RemoteStore$$ExternalSyntheticLambda0 implements OnlineStateTracker.OnlineStateCallback {
    public final /* synthetic */ RemoteStore.RemoteStoreCallback f$0;

    public /* synthetic */ RemoteStore$$ExternalSyntheticLambda0(RemoteStore.RemoteStoreCallback remoteStoreCallback) {
        this.f$0 = remoteStoreCallback;
    }

    public final void handleOnlineStateChange(OnlineState onlineState) {
        this.f$0.handleOnlineStateChange(onlineState);
    }
}
