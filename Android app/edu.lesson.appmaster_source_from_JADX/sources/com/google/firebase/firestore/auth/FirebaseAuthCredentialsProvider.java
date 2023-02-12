package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;

public final class FirebaseAuthCredentialsProvider extends CredentialsProvider<User> {
    private static final String LOG_TAG = "FirebaseAuthCredentialsProvider";
    private Listener<User> changeListener;
    private boolean forceRefresh;
    private final IdTokenListener idTokenListener = new FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda1(this);
    private InternalAuthProvider internalAuthProvider;
    private int tokenCounter;

    /* renamed from: lambda$new$0$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider */
    public /* synthetic */ void mo8705x85cee08e(InternalTokenResult result) {
        onIdTokenChanged();
    }

    public FirebaseAuthCredentialsProvider(Deferred<InternalAuthProvider> deferredAuthProvider) {
        deferredAuthProvider.whenAvailable(new FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$new$1$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider */
    public /* synthetic */ void mo8706x223cdced(Provider provider) {
        synchronized (this) {
            this.internalAuthProvider = (InternalAuthProvider) provider.get();
            onIdTokenChanged();
            this.internalAuthProvider.addIdTokenListener(this.idTokenListener);
        }
    }

    public synchronized Task<String> getToken() {
        InternalAuthProvider internalAuthProvider2 = this.internalAuthProvider;
        if (internalAuthProvider2 == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("auth is not available"));
        }
        Task<GetTokenResult> res = internalAuthProvider2.getAccessToken(this.forceRefresh);
        this.forceRefresh = false;
        return res.continueWithTask(Executors.DIRECT_EXECUTOR, new FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda0(this, this.tokenCounter));
    }

    /* renamed from: lambda$getToken$2$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider */
    public /* synthetic */ Task mo8704x41a0a62f(int savedCounter, Task task) throws Exception {
        synchronized (this) {
            if (savedCounter != this.tokenCounter) {
                Logger.debug(LOG_TAG, "getToken aborted due to token change", new Object[0]);
                Task<String> token = getToken();
                return token;
            } else if (task.isSuccessful()) {
                Task forResult = Tasks.forResult(((GetTokenResult) task.getResult()).getToken());
                return forResult;
            } else {
                Task forException = Tasks.forException(task.getException());
                return forException;
            }
        }
    }

    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    public synchronized void setChangeListener(Listener<User> changeListener2) {
        this.changeListener = changeListener2;
        changeListener2.onValue(getUser());
    }

    public synchronized void removeChangeListener() {
        this.changeListener = null;
        InternalAuthProvider internalAuthProvider2 = this.internalAuthProvider;
        if (internalAuthProvider2 != null) {
            internalAuthProvider2.removeIdTokenListener(this.idTokenListener);
        }
    }

    private synchronized void onIdTokenChanged() {
        this.tokenCounter++;
        Listener<User> listener = this.changeListener;
        if (listener != null) {
            listener.onValue(getUser());
        }
    }

    private synchronized User getUser() {
        String uid;
        InternalAuthProvider internalAuthProvider2 = this.internalAuthProvider;
        uid = internalAuthProvider2 == null ? null : internalAuthProvider2.getUid();
        return uid != null ? new User(uid) : User.UNAUTHENTICATED;
    }
}
