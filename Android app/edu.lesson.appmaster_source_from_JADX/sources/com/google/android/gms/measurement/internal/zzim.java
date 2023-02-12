package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzim implements Runnable {
    final /* synthetic */ zzih zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzio zzc;

    zzim(zzio zzio, zzih zzih, long j) {
        this.zzc = zzio;
        this.zza = zzih;
        this.zzb = j;
    }

    public final void run() {
        this.zzc.zzC(this.zza, false, this.zzb);
        zzio zzio = this.zzc;
        zzio.zza = null;
        zzio.zzs.zzt().zzG((zzih) null);
    }
}
