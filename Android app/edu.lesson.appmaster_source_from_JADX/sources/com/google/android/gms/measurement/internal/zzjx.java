package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final /* synthetic */ class zzjx implements Runnable {
    public final /* synthetic */ zzjy zza;

    public /* synthetic */ zzjx(zzjy zzjy) {
        this.zza = zzjy;
    }

    public final void run() {
        zzjy zzjy = this.zza;
        zzjz zzjz = zzjy.zzc;
        long j = zzjy.zza;
        long j2 = zzjy.zzb;
        zzjz.zza.zzg();
        zzjz.zza.zzs.zzay().zzc().zza("Application going to the background");
        boolean z = true;
        zzjz.zza.zzs.zzm().zzl.zza(true);
        Bundle bundle = new Bundle();
        if (!zzjz.zza.zzs.zzf().zzu()) {
            zzjz.zza.zzb.zzb(j2);
            if (zzjz.zza.zzs.zzf().zzs((String) null, zzdy.zzaf)) {
                zzkb zzkb = zzjz.zza.zzb;
                long j3 = zzkb.zzb;
                zzkb.zzb = j2;
                bundle.putLong("_et", j2 - j3);
                zzkz.zzJ(zzjz.zza.zzs.zzs().zzj(true), bundle, true);
            } else {
                z = false;
            }
            zzjz.zza.zzb.zzd(false, z, j2);
        }
        zzjz.zza.zzs.zzq().zzH("auto", "_ab", j, bundle);
    }
}
