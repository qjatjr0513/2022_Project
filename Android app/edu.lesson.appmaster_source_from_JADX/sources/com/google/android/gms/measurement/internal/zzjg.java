package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzjg implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzp zzc;
    final /* synthetic */ zzcf zzd;
    final /* synthetic */ zzjo zze;

    zzjg(zzjo zzjo, String str, String str2, zzp zzp, zzcf zzcf) {
        this.zze = zzjo;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzp;
        this.zzd = zzcf;
    }

    public final void run() {
        zzfv zzfv;
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            zzeb zzh = this.zze.zzb;
            if (zzh == null) {
                this.zze.zzs.zzay().zzd().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                zzfv = this.zze.zzs;
            } else {
                Preconditions.checkNotNull(this.zzc);
                arrayList = zzkz.zzG(zzh.zzf(this.zza, this.zzb, this.zzc));
                try {
                    this.zze.zzQ();
                    zzfv = this.zze.zzs;
                } catch (RemoteException e) {
                    e = e;
                    try {
                        this.zze.zzs.zzay().zzd().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
                        zzfv = this.zze.zzs;
                        zzfv.zzv().zzP(this.zzd, arrayList);
                    } catch (Throwable th) {
                        th = th;
                        this.zze.zzs.zzv().zzP(this.zzd, arrayList);
                        throw th;
                    }
                }
            }
        } catch (RemoteException e2) {
            e = e2;
            this.zze.zzs.zzay().zzd().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
            zzfv = this.zze.zzs;
            zzfv.zzv().zzP(this.zzd, arrayList);
        } catch (Throwable th2) {
            th = th2;
            this.zze.zzs.zzv().zzP(this.zzd, arrayList);
            throw th;
        }
        zzfv.zzv().zzP(this.zzd, arrayList);
    }
}
