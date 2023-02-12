package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzcf;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zziz implements Runnable {
    final /* synthetic */ zzat zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzcf zzc;
    final /* synthetic */ zzjo zzd;

    zziz(zzjo zzjo, zzat zzat, String str, zzcf zzcf) {
        this.zzd = zzjo;
        this.zza = zzat;
        this.zzb = str;
        this.zzc = zzcf;
    }

    public final void run() {
        zzfv zzfv;
        byte[] bArr = null;
        try {
            zzeb zzh = this.zzd.zzb;
            if (zzh == null) {
                this.zzd.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                zzfv = this.zzd.zzs;
            } else {
                bArr = zzh.zzu(this.zza, this.zzb);
                try {
                    this.zzd.zzQ();
                    zzfv = this.zzd.zzs;
                } catch (RemoteException e) {
                    e = e;
                    try {
                        this.zzd.zzs.zzay().zzd().zzb("Failed to send event to the service to bundle", e);
                        zzfv = this.zzd.zzs;
                        zzfv.zzv().zzR(this.zzc, bArr);
                    } catch (Throwable th) {
                        th = th;
                        this.zzd.zzs.zzv().zzR(this.zzc, bArr);
                        throw th;
                    }
                }
            }
        } catch (RemoteException e2) {
            e = e2;
            this.zzd.zzs.zzay().zzd().zzb("Failed to send event to the service to bundle", e);
            zzfv = this.zzd.zzs;
            zzfv.zzv().zzR(this.zzc, bArr);
        } catch (Throwable th2) {
            th = th2;
            this.zzd.zzs.zzv().zzR(this.zzc, bArr);
            throw th;
        }
        zzfv.zzv().zzR(this.zzc, bArr);
    }
}
