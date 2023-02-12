package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzil implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzio zzb;

    zzil(zzio zzio, long j) {
        this.zzb = zzio;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzs.zzd().zzf(this.zza);
        this.zzb.zza = null;
    }
}
