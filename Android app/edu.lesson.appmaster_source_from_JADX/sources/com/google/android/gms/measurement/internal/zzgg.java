package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpe;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzgg implements Runnable {
    final /* synthetic */ zzat zza;
    final /* synthetic */ zzp zzb;
    final /* synthetic */ zzgn zzc;

    zzgg(zzgn zzgn, zzat zzat, zzp zzp) {
        this.zzc = zzgn;
        this.zza = zzat;
        this.zzb = zzp;
    }

    public final void run() {
        zzat zzb2 = this.zzc.zzb(this.zza, this.zzb);
        zzpe.zzc();
        if (this.zzc.zza.zzg().zzs((String) null, zzdy.zzat)) {
            this.zzc.zzw(zzb2, this.zzb);
        } else {
            this.zzc.zzB(zzb2, this.zzb);
        }
    }
}
