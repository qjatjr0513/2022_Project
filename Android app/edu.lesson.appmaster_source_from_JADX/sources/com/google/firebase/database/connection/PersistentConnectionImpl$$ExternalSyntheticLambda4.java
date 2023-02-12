package com.google.firebase.database.connection;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.connection.PersistentConnectionImpl;

public final /* synthetic */ class PersistentConnectionImpl$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ PersistentConnectionImpl f$0;
    public final /* synthetic */ PersistentConnectionImpl.OutstandingGet f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TaskCompletionSource f$3;

    public /* synthetic */ PersistentConnectionImpl$$ExternalSyntheticLambda4(PersistentConnectionImpl persistentConnectionImpl, PersistentConnectionImpl.OutstandingGet outstandingGet, long j, TaskCompletionSource taskCompletionSource) {
        this.f$0 = persistentConnectionImpl;
        this.f$1 = outstandingGet;
        this.f$2 = j;
        this.f$3 = taskCompletionSource;
    }

    public final void run() {
        this.f$0.mo7601x1e81b14e(this.f$1, this.f$2, this.f$3);
    }
}
