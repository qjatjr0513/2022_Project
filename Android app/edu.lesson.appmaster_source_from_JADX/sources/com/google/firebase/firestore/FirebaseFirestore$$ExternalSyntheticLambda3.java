package com.google.firebase.firestore;

import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.util.Function;
import java.util.concurrent.Executor;

public final /* synthetic */ class FirebaseFirestore$$ExternalSyntheticLambda3 implements Function {
    public final /* synthetic */ FirebaseFirestore f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ Transaction.Function f$2;

    public /* synthetic */ FirebaseFirestore$$ExternalSyntheticLambda3(FirebaseFirestore firebaseFirestore, Executor executor, Transaction.Function function) {
        this.f$0 = firebaseFirestore;
        this.f$1 = executor;
        this.f$2 = function;
    }

    public final Object apply(Object obj) {
        return this.f$0.mo8511x911046dd(this.f$1, this.f$2, (com.google.firebase.firestore.core.Transaction) obj);
    }
}
