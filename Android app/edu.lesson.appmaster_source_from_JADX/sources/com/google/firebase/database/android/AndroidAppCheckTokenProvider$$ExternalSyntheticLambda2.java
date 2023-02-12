package com.google.firebase.database.android;

import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.database.core.TokenProvider;
import java.util.concurrent.ExecutorService;

public final /* synthetic */ class AndroidAppCheckTokenProvider$$ExternalSyntheticLambda2 implements AppCheckTokenListener {
    public final /* synthetic */ ExecutorService f$0;
    public final /* synthetic */ TokenProvider.TokenChangeListener f$1;

    public /* synthetic */ AndroidAppCheckTokenProvider$$ExternalSyntheticLambda2(ExecutorService executorService, TokenProvider.TokenChangeListener tokenChangeListener) {
        this.f$0 = executorService;
        this.f$1 = tokenChangeListener;
    }

    public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
        this.f$0.execute(new AndroidAppCheckTokenProvider$$ExternalSyntheticLambda5(this.f$1, appCheckTokenResult));
    }
}
