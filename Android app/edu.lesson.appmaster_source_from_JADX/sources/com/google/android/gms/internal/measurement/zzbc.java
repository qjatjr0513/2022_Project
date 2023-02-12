package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzbc extends zzaw {
    protected zzbc() {
        this.zza.add(zzbl.AND);
        this.zza.add(zzbl.NOT);
        this.zza.add(zzbl.OR);
    }

    public final zzap zza(String str, zzg zzg, List<zzap> list) {
        zzbl zzbl = zzbl.ADD;
        switch (zzh.zze(str).ordinal()) {
            case 1:
                zzh.zzh(zzbl.AND.name(), 2, list);
                zzap zzb = zzg.zzb(list.get(0));
                if (!zzb.zzg().booleanValue()) {
                    return zzb;
                }
                return zzg.zzb(list.get(1));
            case 47:
                zzh.zzh(zzbl.NOT.name(), 1, list);
                return new zzaf(Boolean.valueOf(!zzg.zzb(list.get(0)).zzg().booleanValue()));
            case 50:
                zzh.zzh(zzbl.OR.name(), 2, list);
                zzap zzb2 = zzg.zzb(list.get(0));
                if (zzb2.zzg().booleanValue()) {
                    return zzb2;
                }
                return zzg.zzb(list.get(1));
            default:
                return super.zzb(str);
        }
    }
}
