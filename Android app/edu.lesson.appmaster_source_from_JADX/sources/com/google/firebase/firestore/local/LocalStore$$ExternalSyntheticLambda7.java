package com.google.firebase.firestore.local;

import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.LocalStore;

public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ LocalStore f$0;
    public final /* synthetic */ LocalStore.AllocateQueryHolder f$1;
    public final /* synthetic */ Target f$2;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda7(LocalStore localStore, LocalStore.AllocateQueryHolder allocateQueryHolder, Target target) {
        this.f$0 = localStore;
        this.f$1 = allocateQueryHolder;
        this.f$2 = target;
    }

    public final void run() {
        this.f$0.mo9153x5de91a9d(this.f$1, this.f$2);
    }
}
