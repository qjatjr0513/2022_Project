package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzkf extends zzam {
    final /* synthetic */ zzkg zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkf(zzkg zzkg, zzgq zzgq) {
        super(zzgq);
        this.zza = zzkg;
    }

    public final void zzc() {
        this.zza.zza();
        this.zza.zzs.zzay().zzj().zza("Starting upload from DelayedRunnable");
        this.zza.zzf.zzV();
    }
}
