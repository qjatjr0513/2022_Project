package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzis implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ zzjo zzb;

    zzis(zzjo zzjo, zzp zzp) {
        this.zzb = zzjo;
        this.zza = zzp;
    }

    public final void run() {
        zzeb zzh = this.zzb.zzb;
        if (zzh == null) {
            this.zzb.zzs.zzay().zzd().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzh.zzm(this.zza);
        } catch (RemoteException e) {
            this.zzb.zzs.zzay().zzd().zzb("Failed to reset data on the service: remote exception", e);
        }
        this.zzb.zzQ();
    }
}
