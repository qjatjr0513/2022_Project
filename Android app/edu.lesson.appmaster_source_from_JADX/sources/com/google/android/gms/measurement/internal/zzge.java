package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzge implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ zzgn zzb;

    zzge(zzgn zzgn, zzp zzp) {
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
        zzc.zzd(zzp);
    }
}
