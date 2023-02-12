package com.google.firebase.messaging;

import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda4 implements FirebaseInstanceIdInternal.NewTokenListener {
    public /* synthetic */ FirebaseMessaging f$0;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda4(FirebaseMessaging firebaseMessaging) {
        this.f$0 = firebaseMessaging;
    }

    public final void onNewToken(String str) {
        this.f$0.invokeOnTokenRefresh(str);
    }
}
