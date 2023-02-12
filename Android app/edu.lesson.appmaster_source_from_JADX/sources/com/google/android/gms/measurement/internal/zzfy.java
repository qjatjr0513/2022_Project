package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzfy implements Runnable {
    final /* synthetic */ zzab zza;
    final /* synthetic */ zzgn zzb;

    zzfy(zzgn zzgn, zzab zzab) {
        this.zzb = zzgn;
        this.zza = zzab;
    }

    public final void run() {
        this.zzb.zza.zzA();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zzM(this.zza);
        } else {
            this.zzb.zza.zzR(this.zza);
        }
    }
}
