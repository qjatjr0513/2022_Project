package com.google.firebase.database.android;

import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.ExecutorService;

public final /* synthetic */ class AndroidAuthTokenProvider$$ExternalSyntheticLambda4 implements Deferred.DeferredHandler {
    public final /* synthetic */ ExecutorService f$0;
    public final /* synthetic */ TokenProvider.TokenChangeListener f$1;

    public /* synthetic */ AndroidAuthTokenProvider$$ExternalSyntheticLambda4(ExecutorService executorService, TokenProvider.TokenChangeListener tokenChangeListener) {
        this.f$0 = executorService;
        this.f$1 = tokenChangeListener;
    }

    public final void handle(Provider provider) {
        ((InternalAuthProvider) provider.get()).addIdTokenListener(new AndroidAuthTokenProvider$$ExternalSyntheticLambda2(this.f$0, this.f$1));
    }
}
