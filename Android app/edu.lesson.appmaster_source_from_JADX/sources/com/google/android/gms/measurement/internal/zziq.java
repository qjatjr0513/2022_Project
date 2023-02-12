package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zziq implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzkv zzc;
    final /* synthetic */ zzjo zzd;

    zziq(zzjo zzjo, zzp zzp, boolean z, zzkv zzkv) {
        this.zzd = zzjo;
        this.zza = zzp;
        this.zzb = z;
        this.zzc = zzkv;
    }

    public final void run() {
        zzkv zzkv;
        zzeb zzh = this.zzd.zzb;
        if (zzh == null) {
            this.zzd.zzs.zzay().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        zzjo zzjo = this.zzd;
        if (this.zzb) {
            zzkv = null;
        } else {
            zzkv = this.zzc;
        }
        zzjo.zzD(zzh, zzkv, this.zza);
        this.zzd.zzQ();
    }
}
