package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzba;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzae implements RemoteCall {
    private final FusedLocationProviderClient zza;
    private final zzap zzb;
    private final LocationCallback zzc;
    private final zzan zzd;
    private final zzba zze;
    private final ListenerHolder zzf;

    zzae(FusedLocationProviderClient fusedLocationProviderClient, zzap zzap, LocationCallback locationCallback, zzan zzan, zzba zzba, ListenerHolder listenerHolder) {
        this.zza = fusedLocationProviderClient;
        this.zzb = zzap;
        this.zzc = locationCallback;
        this.zzd = zzan;
        this.zze = zzba;
        this.zzf = listenerHolder;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zzb(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, (zzaz) obj, (TaskCompletionSource) obj2);
    }
}
