package com.google.firebase.firestore.util;

import java.util.concurrent.Callable;

public final /* synthetic */ class AsyncQueue$$ExternalSyntheticLambda6 implements Callable {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ AsyncQueue$$ExternalSyntheticLambda6(Runnable runnable) {
        this.f$0 = runnable;
    }

    public final Object call() {
        return this.f$0.run();
    }
}
