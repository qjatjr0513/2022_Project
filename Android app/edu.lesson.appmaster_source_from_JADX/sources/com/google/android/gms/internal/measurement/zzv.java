package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzv extends zzai {
    private final zzz zza;

    public zzv(zzz zzz) {
        super("internal.registerCallback");
        this.zza = zzz;
    }

    public final zzap zza(zzg zzg, List<zzap> list) {
        int i;
        zzh.zzh(this.zzd, 3, list);
        String zzi = zzg.zzb(list.get(0)).zzi();
        zzap zzb = zzg.zzb(list.get(1));
        if (zzb instanceof zzao) {
            zzap zzb2 = zzg.zzb(list.get(2));
            if (zzb2 instanceof zzam) {
                zzam zzam = (zzam) zzb2;
                if (zzam.zzt("type")) {
                    String zzi2 = zzam.zzf("type").zzi();
                    if (zzam.zzt("priority")) {
                        i = zzh.zzb(zzam.zzf("priority").zzh().doubleValue());
                    } else {
                        i = 1000;
                    }
                    this.zza.zza(zzi, i, (zzao) zzb, zzi2);
                    return zzap.zzf;
                }
                throw new IllegalArgumentException("Undefined rule type");
            }
            throw new IllegalArgumentException("Invalid callback params");
        }
        throw new IllegalArgumentException("Invalid callback type");
    }
}
