package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final class FirebaseAppCheckTokenProvider extends CredentialsProvider<String> {
    private static final String LOG_TAG = "FirebaseAppCheckTokenProvider";
    private Listener<String> changeListener;
    private boolean forceRefresh;
    private InternalAppCheckTokenProvider internalAppCheckTokenProvider;
    private final AppCheckTokenListener tokenListener = new FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda1(this);

    public FirebaseAppCheckTokenProvider(Deferred<InternalAppCheckTokenProvider> deferredAppCheckTokenProvider) {
        deferredAppCheckTokenProvider.whenAvailable(new FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$new$1$com-google-firebase-firestore-auth-FirebaseAppCheckTokenProvider */
    public /* synthetic */ void mo8703x16263f8b(Provider provider) {
        synchronized (this) {
            InternalAppCheckTokenProvider internalAppCheckTokenProvider2 = (InternalAppCheckTokenProvider) provider.get();
            this.internalAppCheckTokenProvider = internalAppCheckTokenProvider2;
            if (internalAppCheckTokenProvider2 != null) {
                internalAppCheckTokenProvider2.addAppCheckTokenListener(this.tokenListener);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onTokenChanged */
    public synchronized void mo8702xabf6b76c(AppCheckTokenResult result) {
        if (result.getError() != null) {
            Logger.warn(LOG_TAG, "Error getting App Check token; using placeholder token instead. Error: " + result.getError(), new Object[0]);
        }
        Listener<String> listener = this.changeListener;
        if (listener != null) {
            listener.onValue(result.getToken());
        }
    }

    public synchronized Task<String> getToken() {
        InternalAppCheckTokenProvider internalAppCheckTokenProvider2 = this.internalAppCheckTokenProvider;
        if (internalAppCheckTokenProvider2 == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("AppCheck is not available"));
        }
        Task<AppCheckTokenResult> res = internalAppCheckTokenProvider2.getToken(this.forceRefresh);
        this.forceRefresh = false;
        return res.continueWithTask(Executors.DIRECT_EXECUTOR, FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda0.INSTANCE);
    }

    static /* synthetic */ Task lambda$getToken$2(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(((AppCheckTokenResult) task.getResult()).getToken());
        }
        return Tasks.forException(task.getException());
    }

    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    public synchronized void removeChangeListener() {
        this.changeListener = null;
        InternalAppCheckTokenProvider internalAppCheckTokenProvider2 = this.internalAppCheckTokenProvider;
        if (internalAppCheckTokenProvider2 != null) {
            internalAppCheckTokenProvider2.removeAppCheckTokenListener(this.tokenListener);
        }
    }

    public synchronized void setChangeListener(Listener<String> changeListener2) {
        this.changeListener = changeListener2;
    }
}
