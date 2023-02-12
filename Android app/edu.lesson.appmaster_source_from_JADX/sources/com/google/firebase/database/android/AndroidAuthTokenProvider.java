package com.google.firebase.database.android;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public class AndroidAuthTokenProvider implements TokenProvider {
    private final Deferred<InternalAuthProvider> deferredAuthProvider;
    private final AtomicReference<InternalAuthProvider> internalAuth = new AtomicReference<>();

    public AndroidAuthTokenProvider(Deferred<InternalAuthProvider> deferredAuthProvider2) {
        this.deferredAuthProvider = deferredAuthProvider2;
        deferredAuthProvider2.whenAvailable(new AndroidAuthTokenProvider$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$new$0$com-google-firebase-database-android-AndroidAuthTokenProvider */
    public /* synthetic */ void mo7422x1833a710(Provider authProvider) {
        this.internalAuth.set((InternalAuthProvider) authProvider.get());
    }

    public void getToken(boolean forceRefresh, TokenProvider.GetTokenCompletionListener listener) {
        InternalAuthProvider authProvider = this.internalAuth.get();
        if (authProvider != null) {
            authProvider.getAccessToken(forceRefresh).addOnSuccessListener(new AndroidAuthTokenProvider$$ExternalSyntheticLambda1(listener)).addOnFailureListener(new AndroidAuthTokenProvider$$ExternalSyntheticLambda0(listener));
        } else {
            listener.onSuccess((String) null);
        }
    }

    static /* synthetic */ void lambda$getToken$2(TokenProvider.GetTokenCompletionListener listener, Exception e) {
        if (isUnauthenticatedUsage(e)) {
            listener.onSuccess((String) null);
        } else {
            listener.onError(e.getMessage());
        }
    }

    public void addTokenChangeListener(ExecutorService executorService, TokenProvider.TokenChangeListener tokenListener) {
        this.deferredAuthProvider.whenAvailable(new AndroidAuthTokenProvider$$ExternalSyntheticLambda4(executorService, tokenListener));
    }

    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenListener) {
    }

    private static boolean isUnauthenticatedUsage(Exception e) {
        return (e instanceof FirebaseApiNotAvailableException) || (e instanceof FirebaseNoSignedInUserException);
    }
}
