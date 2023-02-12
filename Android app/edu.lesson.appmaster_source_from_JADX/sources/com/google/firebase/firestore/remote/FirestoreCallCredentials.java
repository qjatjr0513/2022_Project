package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.Executor;
import p004io.grpc.CallCredentials;
import p004io.grpc.Metadata;
import p004io.grpc.Status;

final class FirestoreCallCredentials extends CallCredentials {
    private static final Metadata.Key<String> AUTHORIZATION_HEADER = Metadata.Key.m347of(HttpHeaders.AUTHORIZATION, Metadata.ASCII_STRING_MARSHALLER);
    private static final String LOG_TAG = "FirestoreCallCredentials";
    private static final Metadata.Key<String> X_FIREBASE_APPCHECK = Metadata.Key.m347of("x-firebase-appcheck", Metadata.ASCII_STRING_MARSHALLER);
    private final CredentialsProvider<String> appCheckProvider;
    private final CredentialsProvider<User> authProvider;

    FirestoreCallCredentials(CredentialsProvider<User> authProvider2, CredentialsProvider<String> appCheckProvider2) {
        this.authProvider = authProvider2;
        this.appCheckProvider = appCheckProvider2;
    }

    public void thisUsesUnstableApi() {
    }

    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, CallCredentials.MetadataApplier metadataApplier) {
        Task<String> authTask = this.authProvider.getToken();
        Task<String> appCheckTask = this.appCheckProvider.getToken();
        Tasks.whenAll((Task<?>[]) new Task[]{authTask, appCheckTask}).addOnCompleteListener(Executors.DIRECT_EXECUTOR, new FirestoreCallCredentials$$ExternalSyntheticLambda0(authTask, metadataApplier, appCheckTask));
    }

    static /* synthetic */ void lambda$applyRequestMetadata$0(Task authTask, CallCredentials.MetadataApplier metadataApplier, Task appCheckTask, Task unused) {
        Metadata metadata = new Metadata();
        if (authTask.isSuccessful()) {
            String token = (String) authTask.getResult();
            Logger.debug(LOG_TAG, "Successfully fetched auth token.", new Object[0]);
            if (token != null) {
                metadata.put(AUTHORIZATION_HEADER, "Bearer " + token);
            }
        } else {
            Exception exception = authTask.getException();
            if (exception instanceof FirebaseApiNotAvailableException) {
                Logger.debug(LOG_TAG, "Firebase Auth API not available, not using authentication.", new Object[0]);
            } else if (exception instanceof FirebaseNoSignedInUserException) {
                Logger.debug(LOG_TAG, "No user signed in, not using authentication.", new Object[0]);
            } else {
                Logger.warn(LOG_TAG, "Failed to get auth token: %s.", exception);
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(exception));
                return;
            }
        }
        if (appCheckTask.isSuccessful()) {
            String token2 = (String) appCheckTask.getResult();
            if (token2 != null && !token2.isEmpty()) {
                Logger.debug(LOG_TAG, "Successfully fetched AppCheck token.", new Object[0]);
                metadata.put(X_FIREBASE_APPCHECK, token2);
            }
        } else {
            Exception exception2 = appCheckTask.getException();
            if (exception2 instanceof FirebaseApiNotAvailableException) {
                Logger.debug(LOG_TAG, "Firebase AppCheck API not available.", new Object[0]);
            } else {
                Logger.warn(LOG_TAG, "Failed to get AppCheck token: %s.", exception2);
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(exception2));
                return;
            }
        }
        metadataApplier.apply(metadata);
    }
}
