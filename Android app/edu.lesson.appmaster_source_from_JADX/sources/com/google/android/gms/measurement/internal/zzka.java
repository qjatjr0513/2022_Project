package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzka extends zzam {
    final /* synthetic */ zzkb zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzka(zzkb zzkb, zzgq zzgq) {
        super(zzgq);
        this.zza = zzkb;
    }

    public final void zzc() {
        zzkb zzkb = this.zza;
        zzkb.zzc.zzg();
        zzkb.zzd(false, false, zzkb.zzc.zzs.zzav().elapsedRealtime());
        zzkb.zzc.zzs.zzd().zzf(zzkb.zzc.zzs.zzav().elapsedRealtime());
    }
}
