package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final /* synthetic */ class zzgz implements Runnable {
    public final /* synthetic */ zzia zza;

    public /* synthetic */ zzgz(zzia zzia) {
        this.zza = zzia;
    }

    public final void run() {
        zzia zzia = this.zza;
        zzia.zzg();
        if (!zzia.zzs.zzm().zzm.zzb()) {
            long zza2 = zzia.zzs.zzm().zzn.zza();
            zzia.zzs.zzm().zzn.zzb(1 + zza2);
            zzia.zzs.zzf();
            if (zza2 >= 5) {
                zzia.zzs.zzay().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                zzia.zzs.zzm().zzm.zza(true);
                return;
            }
            zzia.zzs.zzE();
            return;
        }
        zzia.zzs.zzay().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
    }
}
