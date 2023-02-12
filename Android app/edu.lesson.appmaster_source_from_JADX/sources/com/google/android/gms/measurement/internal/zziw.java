package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zziw implements Runnable {
    final /* synthetic */ zzih zza;
    final /* synthetic */ zzjo zzb;

    zziw(zzjo zzjo, zzih zzih) {
        this.zzb = zzjo;
        this.zza = zzih;
    }

    public final void run() {
        zzeb zzh = this.zzb.zzb;
        if (zzh == null) {
            this.zzb.zzs.zzay().zzd().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzih zzih = this.zza;
            if (zzih == null) {
                zzh.zzq(0, (String) null, (String) null, this.zzb.zzs.zzau().getPackageName());
            } else {
                zzh.zzq(zzih.zzc, zzih.zza, zzih.zzb, this.zzb.zzs.zzau().getPackageName());
            }
            this.zzb.zzQ();
        } catch (RemoteException e) {
            this.zzb.zzs.zzay().zzd().zzb("Failed to send current screen to the service", e);
        }
    }
}
