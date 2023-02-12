package com.google.firebase.database.connection;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.connection.PersistentConnectionImpl;
import java.util.Map;

public final /* synthetic */ class PersistentConnectionImpl$$ExternalSyntheticLambda2 implements PersistentConnectionImpl.ConnectionRequestCallback {
    public final /* synthetic */ PersistentConnectionImpl f$0;
    public final /* synthetic */ PersistentConnectionImpl.QuerySpec f$1;
    public final /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ PersistentConnectionImpl$$ExternalSyntheticLambda2(PersistentConnectionImpl persistentConnectionImpl, PersistentConnectionImpl.QuerySpec querySpec, TaskCompletionSource taskCompletionSource) {
        this.f$0 = persistentConnectionImpl;
        this.f$1 = querySpec;
        this.f$2 = taskCompletionSource;
    }

    public final void onResponse(Map map) {
        this.f$0.mo7600xb452292f(this.f$1, this.f$2, map);
    }
}
