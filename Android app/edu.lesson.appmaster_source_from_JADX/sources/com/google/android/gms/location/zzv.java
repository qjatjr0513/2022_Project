package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzv implements RemoteCall {
    private final FusedLocationProviderClient zza;

    zzv(FusedLocationProviderClient fusedLocationProviderClient) {
        this.zza = fusedLocationProviderClient;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zzd((zzaz) obj, (TaskCompletionSource) obj2);
    }
}
