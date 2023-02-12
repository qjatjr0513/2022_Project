package com.google.firebase.firestore.auth;

import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;

public final /* synthetic */ class FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda1 implements AppCheckTokenListener {
    public final /* synthetic */ FirebaseAppCheckTokenProvider f$0;

    public /* synthetic */ FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda1(FirebaseAppCheckTokenProvider firebaseAppCheckTokenProvider) {
        this.f$0 = firebaseAppCheckTokenProvider;
    }

    public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
        this.f$0.mo8702xabf6b76c(appCheckTokenResult);
    }
}
