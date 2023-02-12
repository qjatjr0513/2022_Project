package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import p004io.grpc.MethodDescriptor;

public final /* synthetic */ class GrpcCallProvider$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ GrpcCallProvider f$0;
    public final /* synthetic */ MethodDescriptor f$1;

    public /* synthetic */ GrpcCallProvider$$ExternalSyntheticLambda0(GrpcCallProvider grpcCallProvider, MethodDescriptor methodDescriptor) {
        this.f$0 = grpcCallProvider;
        this.f$1 = methodDescriptor;
    }

    public final Object then(Task task) {
        return this.f$0.mo9819x1673c39e(this.f$1, task);
    }
}
