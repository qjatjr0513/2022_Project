package com.google.android.libraries.places.internal;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzn implements OnCompleteListener {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ LocationCallback zzb;
    public final /* synthetic */ TaskCompletionSource zzc;

    public /* synthetic */ zzn(zzp zzp, LocationCallback locationCallback, TaskCompletionSource taskCompletionSource) {
        this.zza = zzp;
        this.zzb = locationCallback;
        this.zzc = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.zza.zzc(this.zzb, this.zzc, task);
    }
}
