package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzba;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzab implements RemoteCall {
    private final FusedLocationProviderClient zza;
    private final CancellationToken zzb;
    private final zzba zzc;

    zzab(FusedLocationProviderClient fusedLocationProviderClient, CancellationToken cancellationToken, zzba zzba) {
        this.zza = fusedLocationProviderClient;
        this.zzb = cancellationToken;
        this.zzc = zzba;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zzc(this.zzb, this.zzc, (zzaz) obj, (TaskCompletionSource) obj2);
    }
}
