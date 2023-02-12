package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import p004io.grpc.ClientCall;

public final /* synthetic */ class FirestoreChannel$$ExternalSyntheticLambda2 implements OnCompleteListener {
    public final /* synthetic */ FirestoreChannel f$0;
    public final /* synthetic */ ClientCall[] f$1;
    public final /* synthetic */ IncomingStreamObserver f$2;

    public /* synthetic */ FirestoreChannel$$ExternalSyntheticLambda2(FirestoreChannel firestoreChannel, ClientCall[] clientCallArr, IncomingStreamObserver incomingStreamObserver) {
        this.f$0 = firestoreChannel;
        this.f$1 = clientCallArr;
        this.f$2 = incomingStreamObserver;
    }

    public final void onComplete(Task task) {
        this.f$0.mo9805xae2b8084(this.f$1, this.f$2, task);
    }
}
