package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda17 implements Runnable {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ TaskCompletionSource f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ FirebaseFirestoreSettings f$3;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda17(FirestoreClient firestoreClient, TaskCompletionSource taskCompletionSource, Context context, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        this.f$0 = firestoreClient;
        this.f$1 = taskCompletionSource;
        this.f$2 = context;
        this.f$3 = firebaseFirestoreSettings;
    }

    public final void run() {
        this.f$0.m446lambda$new$0$comgooglefirebasefirestorecoreFirestoreClient(this.f$1, this.f$2, this.f$3);
    }
}
