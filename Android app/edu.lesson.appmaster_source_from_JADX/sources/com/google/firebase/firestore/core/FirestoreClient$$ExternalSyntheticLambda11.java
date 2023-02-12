package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Listener;
import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda11 implements Listener {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ TaskCompletionSource f$2;
    public final /* synthetic */ AsyncQueue f$3;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda11(FirestoreClient firestoreClient, AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue) {
        this.f$0 = firestoreClient;
        this.f$1 = atomicBoolean;
        this.f$2 = taskCompletionSource;
        this.f$3 = asyncQueue;
    }

    public final void onValue(Object obj) {
        this.f$0.m448lambda$new$2$comgooglefirebasefirestorecoreFirestoreClient(this.f$1, this.f$2, this.f$3, (User) obj);
    }
}
