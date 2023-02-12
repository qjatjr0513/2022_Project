package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;

public final /* synthetic */ class AsyncQueue$DelayedTask$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AsyncQueue.DelayedTask f$0;

    public /* synthetic */ AsyncQueue$DelayedTask$$ExternalSyntheticLambda0(AsyncQueue.DelayedTask delayedTask) {
        this.f$0 = delayedTask;
    }

    public final void run() {
        this.f$0.handleDelayElapsed();
    }
}
