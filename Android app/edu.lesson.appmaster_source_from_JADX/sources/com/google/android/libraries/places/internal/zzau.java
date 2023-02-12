package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzau implements SuccessContinuation {
    public final /* synthetic */ zzav zza;
    public final /* synthetic */ FindCurrentPlaceRequest zzb;

    public /* synthetic */ zzau(zzav zzav, FindCurrentPlaceRequest findCurrentPlaceRequest) {
        this.zza = zzav;
        this.zzb = findCurrentPlaceRequest;
    }

    public final Task then(Object obj) {
        return this.zza.zza(this.zzb, (Location) obj);
    }
}
