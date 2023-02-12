package com.google.firebase.database.android;

import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public class AndroidAppCheckTokenProvider implements TokenProvider {
    private final Deferred<InternalAppCheckTokenProvider> deferredAppCheckProvider;
    private final AtomicReference<InternalAppCheckTokenProvider> internalAppCheck = new AtomicReference<>();

    public AndroidAppCheckTokenProvider(Deferred<InternalAppCheckTokenProvider> deferredAppCheckProvider2) {
        this.deferredAppCheckProvider = deferredAppCheckProvider2;
        deferredAppCheckProvider2.whenAvailable(new AndroidAppCheckTokenProvider$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$new$0$com-google-firebase-database-android-AndroidAppCheckTokenProvider */
    public /* synthetic */ void mo7419x13f0d4b1(Provider authProvider) {
        this.internalAppCheck.set((InternalAppCheckTokenProvider) authProvider.get());
    }

    public void getToken(boolean forceRefresh, TokenProvider.GetTokenCompletionListener listener) {
        InternalAppCheckTokenProvider appCheckProvider = this.internalAppCheck.get();
        if (appCheckProvider != null) {
            appCheckProvider.getToken(forceRefresh).addOnSuccessListener(new AndroidAppCheckTokenProvider$$ExternalSyntheticLambda1(listener)).addOnFailureListener(new AndroidAppCheckTokenProvider$$ExternalSyntheticLambda0(listener));
        } else {
            listener.onSuccess((String) null);
        }
    }

    public void addTokenChangeListener(ExecutorService executorService, TokenProvider.TokenChangeListener tokenListener) {
        this.deferredAppCheckProvider.whenAvailable(new AndroidAppCheckTokenProvider$$ExternalSyntheticLambda4(executorService, tokenListener));
    }

    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenListener) {
    }
}
