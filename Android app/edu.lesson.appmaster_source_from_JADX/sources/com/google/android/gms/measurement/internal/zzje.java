package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzje implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzab zzc;
    final /* synthetic */ zzab zzd;
    final /* synthetic */ zzjo zze;

    zzje(zzjo zzjo, boolean z, zzp zzp, boolean z2, zzab zzab, zzab zzab2) {
        this.zze = zzjo;
        this.zza = zzp;
        this.zzb = z2;
        this.zzc = zzab;
        this.zzd = zzab2;
    }

    public final void run() {
        zzab zzab;
        zzeb zzh = this.zze.zzb;
        if (zzh == null) {
            this.zze.zzs.zzay().zzd().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        zzjo zzjo = this.zze;
        if (this.zzb) {
            zzab = null;
        } else {
            zzab = this.zzc;
        }
        zzjo.zzD(zzh, zzab, this.zza);
        this.zze.zzQ();
    }
}
