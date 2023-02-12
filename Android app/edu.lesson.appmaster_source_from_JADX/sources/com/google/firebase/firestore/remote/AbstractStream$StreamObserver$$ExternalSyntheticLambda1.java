package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.AbstractStream;
import p004io.grpc.Metadata;

public final /* synthetic */ class AbstractStream$StreamObserver$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AbstractStream.StreamObserver f$0;
    public final /* synthetic */ Metadata f$1;

    public /* synthetic */ AbstractStream$StreamObserver$$ExternalSyntheticLambda1(AbstractStream.StreamObserver streamObserver, Metadata metadata) {
        this.f$0 = streamObserver;
        this.f$1 = metadata;
    }

    public final void run() {
        this.f$0.mo9756x509175ff(this.f$1);
    }
}
