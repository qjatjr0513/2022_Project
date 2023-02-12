package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzoa;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzt {
    final /* synthetic */ zzz zza;
    private String zzb;
    private boolean zzc;
    private zzgd zzd;
    /* access modifiers changed from: private */
    public BitSet zze;
    private BitSet zzf;
    private Map<Integer, Long> zzg;
    private Map<Integer, List<Long>> zzh;

    /* synthetic */ zzt(zzz zzz, String str, zzs zzs) {
        this.zza = zzz;
        this.zzb = str;
        this.zzc = true;
        this.zze = new BitSet();
        this.zzf = new BitSet();
        this.zzg = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    /* access modifiers changed from: package-private */
    public final zzfk zza(int i) {
        ArrayList arrayList;
        List list;
        zzfj zzb2 = zzfk.zzb();
        zzb2.zza(i);
        zzb2.zzc(this.zzc);
        zzgd zzgd = this.zzd;
        if (zzgd != null) {
            zzb2.zzd(zzgd);
        }
        zzgc zzf2 = zzgd.zzf();
        zzf2.zzb(zzku.zzs(this.zze));
        zzf2.zzd(zzku.zzs(this.zzf));
        Map<Integer, Long> map = this.zzg;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Integer intValue : this.zzg.keySet()) {
                int intValue2 = intValue.intValue();
                Long l = this.zzg.get(Integer.valueOf(intValue2));
                if (l != null) {
                    zzfl zzc2 = zzfm.zzc();
                    zzc2.zzb(intValue2);
                    zzc2.zza(l.longValue());
                    arrayList2.add((zzfm) zzc2.zzaA());
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            zzf2.zza(arrayList);
        }
        Map<Integer, List<Long>> map2 = this.zzh;
        if (map2 == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList3 = new ArrayList(map2.size());
            for (Integer next : this.zzh.keySet()) {
                zzge zzd2 = zzgf.zzd();
                zzd2.zzb(next.intValue());
                List list2 = this.zzh.get(next);
                if (list2 != null) {
                    Collections.sort(list2);
                    zzd2.zza(list2);
                }
                arrayList3.add((zzgf) zzd2.zzaA());
            }
            list = arrayList3;
        }
        zzf2.zzc(list);
        zzb2.zzb(zzf2);
        return (zzfk) zzb2.zzaA();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzx zzx) {
        int zza2 = zzx.zza();
        Boolean bool = zzx.zzd;
        if (bool != null) {
            this.zzf.set(zza2, bool.booleanValue());
        }
        Boolean bool2 = zzx.zze;
        if (bool2 != null) {
            this.zze.set(zza2, bool2.booleanValue());
        }
        if (zzx.zzf != null) {
            Map<Integer, Long> map = this.zzg;
            Integer valueOf = Integer.valueOf(zza2);
            Long l = map.get(valueOf);
            long longValue = zzx.zzf.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.zzg.put(valueOf, Long.valueOf(longValue));
            }
        }
        if (zzx.zzg != null) {
            Map<Integer, List<Long>> map2 = this.zzh;
            Integer valueOf2 = Integer.valueOf(zza2);
            List list = map2.get(valueOf2);
            if (list == null) {
                list = new ArrayList();
                this.zzh.put(valueOf2, list);
            }
            if (zzx.zzc()) {
                list.clear();
            }
            zzoa.zzc();
            if (this.zza.zzs.zzf().zzs(this.zzb, zzdy.zzY) && zzx.zzb()) {
                list.clear();
            }
            zzoa.zzc();
            if (this.zza.zzs.zzf().zzs(this.zzb, zzdy.zzY)) {
                Long valueOf3 = Long.valueOf(zzx.zzg.longValue() / 1000);
                if (!list.contains(valueOf3)) {
                    list.add(valueOf3);
                    return;
                }
                return;
            }
            list.add(Long.valueOf(zzx.zzg.longValue() / 1000));
        }
    }

    /* synthetic */ zzt(zzz zzz, String str, zzgd zzgd, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzs zzs) {
        this.zza = zzz;
        this.zzb = str;
        this.zze = bitSet;
        this.zzf = bitSet2;
        this.zzg = map;
        this.zzh = new ArrayMap();
        for (Integer num : map2.keySet()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((Long) map2.get(num));
            this.zzh.put(num, arrayList);
        }
        this.zzc = false;
        this.zzd = zzgd;
    }
}
