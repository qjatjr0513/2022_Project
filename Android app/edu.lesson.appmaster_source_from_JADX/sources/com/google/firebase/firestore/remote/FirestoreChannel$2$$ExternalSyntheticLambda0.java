package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.OnSuccessListener;
import p004io.grpc.ClientCall;

public final /* synthetic */ class FirestoreChannel$2$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public static final /* synthetic */ FirestoreChannel$2$$ExternalSyntheticLambda0 INSTANCE = new FirestoreChannel$2$$ExternalSyntheticLambda0();

    private /* synthetic */ FirestoreChannel$2$$ExternalSyntheticLambda0() {
    }

    public final void onSuccess(Object obj) {
        ((ClientCall) obj).halfClose();
    }
}
