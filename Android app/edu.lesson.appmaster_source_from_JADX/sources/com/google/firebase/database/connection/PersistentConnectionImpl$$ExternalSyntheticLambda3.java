package com.google.firebase.database.connection;

import com.google.firebase.database.connection.PersistentConnectionImpl;
import java.util.Map;

public final /* synthetic */ class PersistentConnectionImpl$$ExternalSyntheticLambda3 implements PersistentConnectionImpl.ConnectionRequestCallback {
    public final /* synthetic */ PersistentConnectionImpl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ PersistentConnectionImpl$$ExternalSyntheticLambda3(PersistentConnectionImpl persistentConnectionImpl, boolean z) {
        this.f$0 = persistentConnectionImpl;
        this.f$1 = z;
    }

    public final void onResponse(Map map) {
        this.f$0.mo7602x9b5ee8e8(this.f$1, map);
    }
}
