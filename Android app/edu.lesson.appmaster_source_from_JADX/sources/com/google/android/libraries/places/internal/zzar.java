package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzar implements Continuation {
    public final /* synthetic */ zzav zza;
    public final /* synthetic */ FindAutocompletePredictionsRequest zzb;
    public final /* synthetic */ zzk zzc;

    public /* synthetic */ zzar(zzav zzav, FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, zzk zzk) {
        this.zza = zzav;
        this.zzb = findAutocompletePredictionsRequest;
        this.zzc = zzk;
    }

    public final Object then(Task task) {
        return this.zza.zzd(this.zzb, this.zzc, task);
    }
}
