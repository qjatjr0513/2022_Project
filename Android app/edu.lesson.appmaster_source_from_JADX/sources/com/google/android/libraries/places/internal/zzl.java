package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzl implements Continuation {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ CancellationToken zzb;

    public /* synthetic */ zzl(zzp zzp, CancellationToken cancellationToken) {
        this.zza = zzp;
        this.zzb = cancellationToken;
    }

    public final Object then(Task task) {
        return this.zza.zzb(this.zzb, task);
    }
}
