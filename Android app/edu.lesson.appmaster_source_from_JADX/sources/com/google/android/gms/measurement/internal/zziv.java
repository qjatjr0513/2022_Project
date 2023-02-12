package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zziv implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ zzjo zzb;

    zziv(zzjo zzjo, zzp zzp) {
        this.zzb = zzjo;
        this.zza = zzp;
    }

    public final void run() {
        zzeb zzh = this.zzb.zzb;
        if (zzh == null) {
            this.zzb.zzs.zzay().zzd().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzh.zzj(this.zza);
            this.zzb.zzs.zzi().zzm();
            this.zzb.zzD(zzh, (AbstractSafeParcelable) null, this.zza);
            this.zzb.zzQ();
        } catch (RemoteException e) {
            this.zzb.zzs.zzay().zzd().zzb("Failed to send app launch to the service", e);
        }
    }
}
