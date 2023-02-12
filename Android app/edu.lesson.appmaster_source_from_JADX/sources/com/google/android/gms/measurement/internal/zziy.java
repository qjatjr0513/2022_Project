package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zziy extends zzam {
    final /* synthetic */ zzjo zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zziy(zzjo zzjo, zzgq zzgq) {
        super(zzgq);
        this.zza = zzjo;
    }

    public final void zzc() {
        zzjo zzjo = this.zza;
        zzjo.zzg();
        if (zzjo.zzL()) {
            zzjo.zzs.zzay().zzj().zza("Inactivity, disconnecting from the service");
            zzjo.zzs();
        }
    }
}
