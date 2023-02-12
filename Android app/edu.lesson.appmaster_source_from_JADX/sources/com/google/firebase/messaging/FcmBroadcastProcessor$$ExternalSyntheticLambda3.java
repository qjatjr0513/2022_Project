package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class FcmBroadcastProcessor$$ExternalSyntheticLambda3 implements Callable {
    public /* synthetic */ Context f$0;
    public /* synthetic */ Intent f$1;

    public /* synthetic */ FcmBroadcastProcessor$$ExternalSyntheticLambda3(Context context, Intent intent) {
        this.f$0 = context;
        this.f$1 = intent;
    }

    public final Object call() {
        return Integer.valueOf(ServiceStarter.getInstance().startMessagingService(this.f$0, this.f$1));
    }
}
