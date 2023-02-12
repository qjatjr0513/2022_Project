package com.google.firebase.firestore.util;

import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public final /* synthetic */ class AsyncQueue$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Callable f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ AsyncQueue$$ExternalSyntheticLambda4(Callable callable, Executor executor, TaskCompletionSource taskCompletionSource) {
        this.f$0 = callable;
        this.f$1 = executor;
        this.f$2 = taskCompletionSource;
    }

    public final void run() {
        AsyncQueue.lambda$callTask$1(this.f$0, this.f$1, this.f$2);
    }
}
