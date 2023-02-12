package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class EnhancedIntentService$$ExternalSyntheticLambda1 implements Runnable {
    public /* synthetic */ EnhancedIntentService f$0;
    public /* synthetic */ Intent f$1;
    public /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ EnhancedIntentService$$ExternalSyntheticLambda1(EnhancedIntentService enhancedIntentService, Intent intent, TaskCompletionSource taskCompletionSource) {
        this.f$0 = enhancedIntentService;
        this.f$1 = intent;
        this.f$2 = taskCompletionSource;
    }

    public final void run() {
        this.f$0.mo10169x624ce8b2(this.f$1, this.f$2);
    }
}
