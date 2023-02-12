package com.google.firebase.messaging;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class ProxyNotificationInitializer$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ Context f$0;
    public /* synthetic */ boolean f$1;
    public /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ ProxyNotificationInitializer$$ExternalSyntheticLambda0(Context context, boolean z, TaskCompletionSource taskCompletionSource) {
        this.f$0 = context;
        this.f$1 = z;
        this.f$2 = taskCompletionSource;
    }

    public final void run() {
        ProxyNotificationInitializer.lambda$setEnableProxyNotification$0(this.f$0, this.f$1, this.f$2);
    }
}
