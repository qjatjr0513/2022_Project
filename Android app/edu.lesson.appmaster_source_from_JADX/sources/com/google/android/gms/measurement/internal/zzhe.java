package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhe implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzia zzb;

    zzhe(zzia zzia, long j) {
        this.zzb = zzia;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzs.zzm().zzf.zzb(this.zza);
        this.zzb.zzs.zzay().zzc().zzb("Session timeout duration set", Long.valueOf(this.zza));
    }
}
