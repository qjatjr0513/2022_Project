package com.google.android.gms.location;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzx implements zzan {
    private final FusedLocationProviderClient zza;
    private final zzap zzb;
    private final LocationCallback zzc;
    private final zzan zzd;

    zzx(FusedLocationProviderClient fusedLocationProviderClient, zzap zzap, LocationCallback locationCallback, zzan zzan) {
        this.zza = fusedLocationProviderClient;
        this.zzb = zzap;
        this.zzc = locationCallback;
        this.zzd = zzan;
    }

    public final void zza() {
        FusedLocationProviderClient fusedLocationProviderClient = this.zza;
        zzap zzap = this.zzb;
        LocationCallback locationCallback = this.zzc;
        zzan zzan = this.zzd;
        zzap.zzb(false);
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        if (zzan != null) {
            zzan.zza();
        }
    }
}
