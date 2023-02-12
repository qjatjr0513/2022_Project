package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzap implements Continuation {
    public final /* synthetic */ zzav zza;
    public final /* synthetic */ FetchPhotoRequest zzb;
    public final /* synthetic */ zzk zzc;

    public /* synthetic */ zzap(zzav zzav, FetchPhotoRequest fetchPhotoRequest, zzk zzk) {
        this.zza = zzav;
        this.zzb = fetchPhotoRequest;
        this.zzc = zzk;
    }

    public final Object then(Task task) {
        return this.zza.zzb(this.zzb, this.zzc, task);
    }
}
