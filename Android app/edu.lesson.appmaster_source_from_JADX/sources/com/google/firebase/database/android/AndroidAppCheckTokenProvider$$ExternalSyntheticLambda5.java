package com.google.firebase.database.android;

import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.database.core.TokenProvider;

public final /* synthetic */ class AndroidAppCheckTokenProvider$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ TokenProvider.TokenChangeListener f$0;
    public final /* synthetic */ AppCheckTokenResult f$1;

    public /* synthetic */ AndroidAppCheckTokenProvider$$ExternalSyntheticLambda5(TokenProvider.TokenChangeListener tokenChangeListener, AppCheckTokenResult appCheckTokenResult) {
        this.f$0 = tokenChangeListener;
        this.f$1 = appCheckTokenResult;
    }

    public final void run() {
        this.f$0.onTokenChange(this.f$1.getToken());
    }
}
