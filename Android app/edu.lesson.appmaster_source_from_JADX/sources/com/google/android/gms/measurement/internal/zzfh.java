package com.google.android.gms.measurement.internal;

import java.util.HashMap;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final /* synthetic */ class zzfh implements Callable {
    public final /* synthetic */ zzfm zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzfh(zzfm zzfm, String str) {
        this.zza = zzfm;
        this.zzb = str;
    }

    public final Object call() {
        zzfm zzfm = this.zza;
        String str = this.zzb;
        zzg zzj = zzfm.zzf.zzi().zzj(str);
        HashMap hashMap = new HashMap();
        hashMap.put("platform", "android");
        hashMap.put("package_name", str);
        zzfm.zzs.zzf().zzh();
        hashMap.put("gmp_version", 46000L);
        if (zzj != null) {
            String zzw = zzj.zzw();
            if (zzw != null) {
                hashMap.put("app_version", zzw);
            }
            hashMap.put("app_version_int", Long.valueOf(zzj.zzb()));
            hashMap.put("dynamite_version", Long.valueOf(zzj.zzk()));
        }
        return hashMap;
    }
}
