package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzfl implements zzo {
    final /* synthetic */ String zza;
    final /* synthetic */ zzfm zzb;

    zzfl(zzfm zzfm, String str) {
        this.zzb = zzfm;
        this.zza = str;
    }

    public final String zza(String str) {
        Map map = (Map) this.zzb.zze.get(this.zza);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }
}
