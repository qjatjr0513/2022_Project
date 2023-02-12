package com.google.firebase.messaging;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class TopicsSubscriber$$ExternalSyntheticLambda0 implements Callable {
    public /* synthetic */ Context f$0;
    public /* synthetic */ ScheduledExecutorService f$1;
    public /* synthetic */ FirebaseMessaging f$2;
    public /* synthetic */ Metadata f$3;
    public /* synthetic */ GmsRpc f$4;

    public /* synthetic */ TopicsSubscriber$$ExternalSyntheticLambda0(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging, Metadata metadata, GmsRpc gmsRpc) {
        this.f$0 = context;
        this.f$1 = scheduledExecutorService;
        this.f$2 = firebaseMessaging;
        this.f$3 = metadata;
        this.f$4 = gmsRpc;
    }

    public final Object call() {
        return TopicsSubscriber.lambda$createInstance$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
