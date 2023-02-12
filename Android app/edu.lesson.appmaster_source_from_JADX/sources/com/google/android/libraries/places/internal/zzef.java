package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzef implements Continuation {
    public final /* synthetic */ zzeh zza;

    public /* synthetic */ zzef(zzeh zzeh) {
        this.zza = zzeh;
    }

    public final Object then(Task task) {
        zzeh zzeh = this.zza;
        int i = zzel.zza;
        return zzeh.zza().getToken().isCancellationRequested() ? Tasks.forCanceled() : task;
    }
}
