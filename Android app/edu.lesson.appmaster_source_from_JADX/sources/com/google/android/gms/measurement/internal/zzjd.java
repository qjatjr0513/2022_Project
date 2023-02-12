package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzjd implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzat zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzjo zze;

    zzjd(zzjo zzjo, boolean z, zzp zzp, boolean z2, zzat zzat, String str) {
        this.zze = zzjo;
        this.zza = zzp;
        this.zzb = z2;
        this.zzc = zzat;
        this.zzd = str;
    }

    public final void run() {
        zzat zzat;
        zzeb zzh = this.zze.zzb;
        if (zzh == null) {
            this.zze.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        zzjo zzjo = this.zze;
        if (this.zzb) {
            zzat = null;
        } else {
            zzat = this.zzc;
        }
        zzjo.zzD(zzh, zzat, this.zza);
        this.zze.zzQ();
    }
}
