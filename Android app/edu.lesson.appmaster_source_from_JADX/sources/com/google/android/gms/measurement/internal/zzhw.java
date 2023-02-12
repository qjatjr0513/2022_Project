package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhw implements Runnable {
    final /* synthetic */ zzag zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzia zze;

    zzhw(zzia zzia, zzag zzag, int i, long j, boolean z) {
        this.zze = zzia;
        this.zza = zzag;
        this.zzb = i;
        this.zzc = j;
        this.zzd = z;
    }

    public final void run() {
        this.zze.zzW(this.zza);
        zzia.zzv(this.zze, this.zza, this.zzb, this.zzc, false, this.zzd);
    }
}
