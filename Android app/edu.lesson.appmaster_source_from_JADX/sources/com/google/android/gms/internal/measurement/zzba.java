package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzba implements Comparator<zzap> {
    final /* synthetic */ zzai zza;
    final /* synthetic */ zzg zzb;

    zzba(zzai zzai, zzg zzg) {
        this.zza = zzai;
        this.zzb = zzg;
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzap zzap = (zzap) obj;
        zzap zzap2 = (zzap) obj2;
        zzai zzai = this.zza;
        zzg zzg = this.zzb;
        if (zzap instanceof zzau) {
            return !(zzap2 instanceof zzau) ? 1 : 0;
        }
        if (zzap2 instanceof zzau) {
            return -1;
        }
        if (zzai == null) {
            return zzap.zzi().compareTo(zzap2.zzi());
        }
        return (int) zzh.zza(zzai.zza(zzg, Arrays.asList(new zzap[]{zzap, zzap2})).zzh().doubleValue());
    }
}
