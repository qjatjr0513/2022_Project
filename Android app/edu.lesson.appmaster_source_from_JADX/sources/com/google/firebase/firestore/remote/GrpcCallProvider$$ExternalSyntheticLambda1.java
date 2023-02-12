package com.google.firebase.firestore.remote;

import p004io.grpc.ManagedChannel;

public final /* synthetic */ class GrpcCallProvider$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ GrpcCallProvider f$0;
    public final /* synthetic */ ManagedChannel f$1;

    public /* synthetic */ GrpcCallProvider$$ExternalSyntheticLambda1(GrpcCallProvider grpcCallProvider, ManagedChannel managedChannel) {
        this.f$0 = grpcCallProvider;
        this.f$1 = managedChannel;
    }

    public final void run() {
        this.f$0.mo9820x7eb62bdc(this.f$1);
    }
}
