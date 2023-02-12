package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.AbstractStream;
import p004io.grpc.Status;

public final /* synthetic */ class AbstractStream$StreamObserver$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ AbstractStream.StreamObserver f$0;
    public final /* synthetic */ Status f$1;

    public /* synthetic */ AbstractStream$StreamObserver$$ExternalSyntheticLambda2(AbstractStream.StreamObserver streamObserver, Status status) {
        this.f$0 = streamObserver;
        this.f$1 = status;
    }

    public final void run() {
        this.f$0.mo9755x427512ea(this.f$1);
    }
}
