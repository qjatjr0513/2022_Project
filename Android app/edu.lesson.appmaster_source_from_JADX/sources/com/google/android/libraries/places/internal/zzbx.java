package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzbx implements Continuation {
    public final /* synthetic */ zzcb zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzbx(zzcb zzcb, long j) {
        this.zza = zzcb;
        this.zzb = j;
    }

    public final Object then(Task task) {
        return this.zza.zze(this.zzb, task);
    }
}
