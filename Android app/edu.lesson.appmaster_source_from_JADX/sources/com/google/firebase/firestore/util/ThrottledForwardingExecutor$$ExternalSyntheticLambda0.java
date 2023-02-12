package com.google.firebase.firestore.util;

public final /* synthetic */ class ThrottledForwardingExecutor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ThrottledForwardingExecutor f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ThrottledForwardingExecutor$$ExternalSyntheticLambda0(ThrottledForwardingExecutor throttledForwardingExecutor, Runnable runnable) {
        this.f$0 = throttledForwardingExecutor;
        this.f$1 = runnable;
    }

    public final void run() {
        this.f$0.mo10003xc0e914ff(this.f$1);
    }
}
