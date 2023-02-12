package com.google.firebase.firestore;

import com.google.firebase.firestore.core.ViewSnapshot;

public final /* synthetic */ class DocumentReference$$ExternalSyntheticLambda2 implements EventListener {
    public final /* synthetic */ DocumentReference f$0;
    public final /* synthetic */ EventListener f$1;

    public /* synthetic */ DocumentReference$$ExternalSyntheticLambda2(DocumentReference documentReference, EventListener eventListener) {
        this.f$0 = documentReference;
        this.f$1 = eventListener;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        this.f$0.mo8436xb5c5a0f3(this.f$1, (ViewSnapshot) obj, firebaseFirestoreException);
    }
}
