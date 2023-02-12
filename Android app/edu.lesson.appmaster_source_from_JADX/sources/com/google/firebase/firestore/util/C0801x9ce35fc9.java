package com.google.firebase.firestore.util;

import java.util.concurrent.Callable;

/* renamed from: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda2 */
public final /* synthetic */ class C0801x9ce35fc9 implements Callable {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ C0801x9ce35fc9(Runnable runnable) {
        this.f$0 = runnable;
    }

    public final Object call() {
        return this.f$0.run();
    }
}
