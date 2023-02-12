package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.lang.Thread;

/* renamed from: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda1 */
public final /* synthetic */ class C0800x9ce35fc8 implements Thread.UncaughtExceptionHandler {
    public final /* synthetic */ AsyncQueue.SynchronizedShutdownAwareExecutor f$0;

    public /* synthetic */ C0800x9ce35fc8(AsyncQueue.SynchronizedShutdownAwareExecutor synchronizedShutdownAwareExecutor) {
        this.f$0 = synchronizedShutdownAwareExecutor;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        this.f$0.mo9965x23fada6e(thread, th);
    }
}
