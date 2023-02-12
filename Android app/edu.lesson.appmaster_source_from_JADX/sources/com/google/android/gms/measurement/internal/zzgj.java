package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzgj implements Runnable {
    final /* synthetic */ zzkv zza;
    final /* synthetic */ zzp zzb;
    final /* synthetic */ zzgn zzc;

    zzgj(zzgn zzgn, zzkv zzkv, zzp zzp) {
        this.zzc = zzgn;
        this.zza = zzkv;
        this.zzb = zzp;
    }

    public final void run() {
        this.zzc.zza.zzA();
        if (this.zza.zza() == null) {
            this.zzc.zza.zzO(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzU(this.zza, this.zzb);
        }
    }
}
