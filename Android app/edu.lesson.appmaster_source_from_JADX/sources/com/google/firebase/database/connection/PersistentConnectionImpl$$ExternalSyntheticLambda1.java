package com.google.firebase.database.connection;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class PersistentConnectionImpl$$ExternalSyntheticLambda1 implements OnSuccessListener {
    public final /* synthetic */ PersistentConnectionImpl f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Task f$2;
    public final /* synthetic */ Task f$3;

    public /* synthetic */ PersistentConnectionImpl$$ExternalSyntheticLambda1(PersistentConnectionImpl persistentConnectionImpl, long j, Task task, Task task2) {
        this.f$0 = persistentConnectionImpl;
        this.f$1 = j;
        this.f$2 = task;
        this.f$3 = task2;
    }

    public final void onSuccess(Object obj) {
        this.f$0.mo7603xf7813304(this.f$1, this.f$2, this.f$3, (Void) obj);
    }
}
