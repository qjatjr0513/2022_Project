package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class FirestoreChannel$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ FirestoreChannel f$0;
    public final /* synthetic */ TaskCompletionSource f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ FirestoreChannel$$ExternalSyntheticLambda0(FirestoreChannel firestoreChannel, TaskCompletionSource taskCompletionSource, Object obj) {
        this.f$0 = firestoreChannel;
        this.f$1 = taskCompletionSource;
        this.f$2 = obj;
    }

    public final void onComplete(Task task) {
        this.f$0.mo9806x7c1edc40(this.f$1, this.f$2, task);
    }
}
