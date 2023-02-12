package com.google.firebase.firestore.auth;

import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;

public final /* synthetic */ class FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda1 implements IdTokenListener {
    public final /* synthetic */ FirebaseAuthCredentialsProvider f$0;

    public /* synthetic */ FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda1(FirebaseAuthCredentialsProvider firebaseAuthCredentialsProvider) {
        this.f$0 = firebaseAuthCredentialsProvider;
    }

    public final void onIdTokenChanged(InternalTokenResult internalTokenResult) {
        this.f$0.mo8705x85cee08e(internalTokenResult);
    }
}
