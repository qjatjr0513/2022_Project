package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledFuture;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public /* synthetic */ ScheduledFuture f$0;

    public /* synthetic */ WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda0(ScheduledFuture scheduledFuture) {
        this.f$0 = scheduledFuture;
    }

    public final void onComplete(Task task) {
        this.f$0.cancel(false);
    }
}
