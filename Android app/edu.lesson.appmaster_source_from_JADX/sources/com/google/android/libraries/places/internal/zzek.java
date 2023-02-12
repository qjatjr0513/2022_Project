package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzek<T> {
    private Task<T> zza;

    /* synthetic */ zzek(zzej zzej) {
    }

    public abstract CancellationTokenSource zza();

    public final Task<T> zzc() {
        return this.zza;
    }

    public final void zzd(Task<T> task) {
        this.zza = task;
    }
}
