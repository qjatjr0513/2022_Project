package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class CollectionReference$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ DocumentReference f$0;

    public /* synthetic */ CollectionReference$$ExternalSyntheticLambda0(DocumentReference documentReference) {
        this.f$0 = documentReference;
    }

    public final Object then(Task task) {
        return task.getResult();
    }
}
