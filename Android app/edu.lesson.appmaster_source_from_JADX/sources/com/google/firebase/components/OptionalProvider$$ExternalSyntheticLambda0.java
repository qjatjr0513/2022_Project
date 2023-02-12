package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class OptionalProvider$$ExternalSyntheticLambda0 implements Deferred.DeferredHandler {
    public final /* synthetic */ Deferred.DeferredHandler f$0;
    public final /* synthetic */ Deferred.DeferredHandler f$1;

    public /* synthetic */ OptionalProvider$$ExternalSyntheticLambda0(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2) {
        this.f$0 = deferredHandler;
        this.f$1 = deferredHandler2;
    }

    public final void handle(Provider provider) {
        OptionalProvider.lambda$whenAvailable$2(this.f$0, this.f$1, provider);
    }
}
