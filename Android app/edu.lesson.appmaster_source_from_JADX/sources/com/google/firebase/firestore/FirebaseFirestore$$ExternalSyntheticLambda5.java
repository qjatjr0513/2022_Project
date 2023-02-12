package com.google.firebase.firestore;

import com.google.firebase.firestore.Transaction;
import java.util.concurrent.Callable;

public final /* synthetic */ class FirebaseFirestore$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ FirebaseFirestore f$0;
    public final /* synthetic */ Transaction.Function f$1;
    public final /* synthetic */ com.google.firebase.firestore.core.Transaction f$2;

    public /* synthetic */ FirebaseFirestore$$ExternalSyntheticLambda5(FirebaseFirestore firebaseFirestore, Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) {
        this.f$0 = firebaseFirestore;
        this.f$1 = function;
        this.f$2 = transaction;
    }

    public final Object call() {
        return this.f$0.mo8510x9186acdc(this.f$1, this.f$2);
    }
}
