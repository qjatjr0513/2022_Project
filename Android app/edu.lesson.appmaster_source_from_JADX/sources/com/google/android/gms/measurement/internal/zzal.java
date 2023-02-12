package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzal implements Runnable {
    final /* synthetic */ zzgq zza;
    final /* synthetic */ zzam zzb;

    zzal(zzam zzam, zzgq zzgq) {
        this.zzb = zzam;
        this.zza = zzgq;
    }

    public final void run() {
        this.zza.zzaw();
        if (zzaa.zza()) {
            this.zza.zzaz().zzp(this);
            return;
        }
        boolean zze = this.zzb.zze();
        this.zzb.zzd = 0;
        if (zze) {
            this.zzb.zzc();
        }
    }
}
