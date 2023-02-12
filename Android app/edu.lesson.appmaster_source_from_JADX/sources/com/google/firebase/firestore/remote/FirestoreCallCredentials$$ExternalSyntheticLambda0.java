package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import p004io.grpc.CallCredentials;

public final /* synthetic */ class FirestoreCallCredentials$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ Task f$0;
    public final /* synthetic */ CallCredentials.MetadataApplier f$1;
    public final /* synthetic */ Task f$2;

    public /* synthetic */ FirestoreCallCredentials$$ExternalSyntheticLambda0(Task task, CallCredentials.MetadataApplier metadataApplier, Task task2) {
        this.f$0 = task;
        this.f$1 = metadataApplier;
        this.f$2 = task2;
    }

    public final void onComplete(Task task) {
        FirestoreCallCredentials.lambda$applyRequestMetadata$0(this.f$0, this.f$1, this.f$2, task);
    }
}
