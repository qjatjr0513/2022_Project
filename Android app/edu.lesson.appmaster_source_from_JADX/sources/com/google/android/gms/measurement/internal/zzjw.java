package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzjw implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzkd zzb;

    zzjw(zzkd zzkd, long j) {
        this.zzb = zzkd;
        this.zza = j;
    }

    public final void run() {
        zzkd.zzj(this.zzb, this.zza);
    }
}
