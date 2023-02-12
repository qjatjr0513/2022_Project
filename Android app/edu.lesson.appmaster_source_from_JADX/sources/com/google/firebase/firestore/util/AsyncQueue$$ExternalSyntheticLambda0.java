package com.google.firebase.firestore.util;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class AsyncQueue$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ AsyncQueue$$ExternalSyntheticLambda0(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    public final Object then(Task task) {
        return AsyncQueue.lambda$callTask$0(this.f$0, task);
    }
}
