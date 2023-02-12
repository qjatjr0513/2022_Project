package com.google.firebase.firestore;

import com.google.firebase.firestore.core.ViewSnapshot;

public final /* synthetic */ class Query$$ExternalSyntheticLambda2 implements EventListener {
    public final /* synthetic */ Query f$0;
    public final /* synthetic */ EventListener f$1;

    public /* synthetic */ Query$$ExternalSyntheticLambda2(Query query, EventListener eventListener) {
        this.f$0 = query;
        this.f$1 = eventListener;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        this.f$0.mo8616xb1c289cb(this.f$1, (ViewSnapshot) obj, firebaseFirestoreException);
    }
}
