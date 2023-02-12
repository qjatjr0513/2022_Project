package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class Transaction$$ExternalSyntheticLambda1 implements Continuation {
    public static final /* synthetic */ Transaction$$ExternalSyntheticLambda1 INSTANCE = new Transaction$$ExternalSyntheticLambda1();

    private /* synthetic */ Transaction$$ExternalSyntheticLambda1() {
    }

    public final Object then(Task task) {
        return Transaction.lambda$commit$1(task);
    }
}
