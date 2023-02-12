package com.google.firebase.database.connection;

import com.google.android.gms.tasks.OnFailureListener;

public final /* synthetic */ class PersistentConnectionImpl$$ExternalSyntheticLambda0 implements OnFailureListener {
    public final /* synthetic */ PersistentConnectionImpl f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ PersistentConnectionImpl$$ExternalSyntheticLambda0(PersistentConnectionImpl persistentConnectionImpl, long j) {
        this.f$0 = persistentConnectionImpl;
        this.f$1 = j;
    }

    public final void onFailure(Exception exc) {
        this.f$0.mo7604x61b0bb23(this.f$1, exc);
    }
}
