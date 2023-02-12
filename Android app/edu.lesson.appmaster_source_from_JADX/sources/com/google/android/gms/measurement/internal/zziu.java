package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zziu implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ zzcf zzb;
    final /* synthetic */ zzjo zzc;

    zziu(zzjo zzjo, zzp zzp, zzcf zzcf) {
        this.zzc = zzjo;
        this.zza = zzp;
        this.zzb = zzcf;
    }

    public final void run() {
        zzfv zzfv;
        String str = null;
        try {
            if (!this.zzc.zzs.zzm().zzc().zzk()) {
                this.zzc.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzs.zzq().zzO((String) null);
                this.zzc.zzs.zzm().zze.zzb((String) null);
                zzfv = this.zzc.zzs;
            } else {
                zzeb zzh = this.zzc.zzb;
                if (zzh == null) {
                    this.zzc.zzs.zzay().zzd().zza("Failed to get app instance id");
                    zzfv = this.zzc.zzs;
                } else {
                    Preconditions.checkNotNull(this.zza);
                    str = zzh.zzd(this.zza);
                    if (str != null) {
                        try {
                            this.zzc.zzs.zzq().zzO(str);
                            this.zzc.zzs.zzm().zze.zzb(str);
                        } catch (RemoteException e) {
                            e = e;
                            try {
                                this.zzc.zzs.zzay().zzd().zzb("Failed to get app instance id", e);
                                zzfv = this.zzc.zzs;
                                zzfv.zzv().zzU(this.zzb, str);
                            } catch (Throwable th) {
                                th = th;
                                this.zzc.zzs.zzv().zzU(this.zzb, str);
                                throw th;
                            }
                        }
                    }
                    this.zzc.zzQ();
                    zzfv = this.zzc.zzs;
                }
            }
        } catch (RemoteException e2) {
            e = e2;
            this.zzc.zzs.zzay().zzd().zzb("Failed to get app instance id", e);
            zzfv = this.zzc.zzs;
            zzfv.zzv().zzU(this.zzb, str);
        } catch (Throwable th2) {
            th = th2;
            this.zzc.zzs.zzv().zzU(this.zzb, str);
            throw th;
        }
        zzfv.zzv().zzU(this.zzb, str);
    }
}
