package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhu implements Runnable {
    final /* synthetic */ zzag zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ boolean zze;
    final /* synthetic */ zzia zzf;

    zzhu(zzia zzia, zzag zzag, long j, int i, long j2, boolean z) {
        this.zzf = zzia;
        this.zza = zzag;
        this.zzb = j;
        this.zzc = i;
        this.zzd = j2;
        this.zze = z;
    }

    public final void run() {
        this.zzf.zzW(this.zza);
        this.zzf.zzL(this.zzb, false);
        zzia.zzv(this.zzf, this.zza, this.zzc, this.zzd, true, this.zze);
    }
}
