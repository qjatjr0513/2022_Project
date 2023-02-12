package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzaq implements Continuation {
    public final /* synthetic */ zzav zza;
    public final /* synthetic */ FetchPlaceRequest zzb;
    public final /* synthetic */ zzk zzc;

    public /* synthetic */ zzaq(zzav zzav, FetchPlaceRequest fetchPlaceRequest, zzk zzk) {
        this.zza = zzav;
        this.zzb = fetchPlaceRequest;
        this.zzc = zzk;
    }

    public final Object then(Task task) {
        return this.zza.zzc(this.zzb, this.zzc, task);
    }
}
