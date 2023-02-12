package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final /* synthetic */ class Datastore$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ Datastore f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ Datastore$$ExternalSyntheticLambda1(Datastore datastore, List list) {
        this.f$0 = datastore;
        this.f$1 = list;
    }

    public final Object then(Task task) {
        return this.f$0.m452lambda$lookup$1$comgooglefirebasefirestoreremoteDatastore(this.f$1, task);
    }
}
