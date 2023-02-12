package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzgf implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ zzgn zzb;

    zzgf(zzgn zzgn, zzp zzp) {
        this.zzb = zzgn;
        this.zza = zzp;
    }

    public final void run() {
        this.zzb.zza.zzA();
        zzks zzc = this.zzb.zza;
        zzp zzp = this.zza;
        zzc.zzaz().zzg();
        zzc.zzB();
        Preconditions.checkNotEmpty(zzp.zza);
        zzag zzb2 = zzag.zzb(zzp.zzv);
        zzag zzh = zzc.zzh(zzp.zza);
        zzc.zzay().zzj().zzc("Setting consent, package, consent", zzp.zza, zzb2);
        zzc.zzT(zzp.zza, zzb2);
        if (zzb2.zzm(zzh)) {
            zzc.zzP(zzp);
        }
    }
}
