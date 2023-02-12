package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzw extends zzai {
    final Map<String, zzai> zza = new HashMap();
    private final zzj zzb;

    public zzw(zzj zzj) {
        super("require");
        this.zzb = zzj;
    }

    public final zzap zza(zzg zzg, List<zzap> list) {
        zzap zzap;
        zzh.zzh("require", 1, list);
        String zzi = zzg.zzb(list.get(0)).zzi();
        if (this.zza.containsKey(zzi)) {
            return this.zza.get(zzi);
        }
        zzj zzj = this.zzb;
        if (zzj.zza.containsKey(zzi)) {
            try {
                zzap = (zzap) zzj.zza.get(zzi).call();
            } catch (Exception e) {
                String valueOf = String.valueOf(zzi);
                throw new IllegalStateException(valueOf.length() != 0 ? "Failed to create API implementation: ".concat(valueOf) : new String("Failed to create API implementation: "));
            }
        } else {
            zzap = zzap.zzf;
        }
        if (zzap instanceof zzai) {
            this.zza.put(zzi, (zzai) zzap);
        }
        return zzap;
    }
}
