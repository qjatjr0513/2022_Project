package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class RequestDeduplicator$$ExternalSyntheticLambda0 implements Continuation {
    public /* synthetic */ RequestDeduplicator f$0;
    public /* synthetic */ String f$1;

    public /* synthetic */ RequestDeduplicator$$ExternalSyntheticLambda0(RequestDeduplicator requestDeduplicator, String str) {
        this.f$0 = requestDeduplicator;
        this.f$1 = str;
    }

    public final Object then(Task task) {
        this.f$0.mo10326x7161fc54(this.f$1, task);
        return task;
    }
}
