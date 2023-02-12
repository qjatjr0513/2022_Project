package com.google.firebase.firestore;

public final /* synthetic */ class FirebaseFirestore$$ExternalSyntheticLambda1 implements EventListener {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ FirebaseFirestore$$ExternalSyntheticLambda1(Runnable runnable) {
        this.f$0 = runnable;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        FirebaseFirestore.lambda$addSnapshotsInSyncListener$4(this.f$0, (Void) obj, firebaseFirestoreException);
    }
}
