package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzam implements zzel {
    private final zzaj zzem;

    public static zzam zzb(zzaj zzaj) {
        if (zzaj.zzes != null) {
            return zzaj.zzes;
        }
        return new zzam(zzaj);
    }

    private zzam(zzaj zzaj) {
        zzaj zzaj2 = (zzaj) zzbd.zzb(zzaj, "output");
        this.zzem = zzaj2;
        zzaj2.zzes = this;
    }

    public final int zzam() {
        return zzbc.zze.zziw;
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzem.zzg(i, i2);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzem.zzb(i, j);
    }

    public final void zzk(int i, long j) throws IOException {
        this.zzem.zzd(i, j);
    }

    public final void zzb(int i, float f) throws IOException {
        this.zzem.zzb(i, f);
    }

    public final void zzb(int i, double d) throws IOException {
        this.zzem.zzb(i, d);
    }

    public final void zzo(int i, int i2) throws IOException {
        this.zzem.zzd(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzem.zzb(i, j);
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zzem.zzd(i, i2);
    }

    public final void zzd(int i, long j) throws IOException {
        this.zzem.zzd(i, j);
    }

    public final void zzg(int i, int i2) throws IOException {
        this.zzem.zzg(i, i2);
    }

    public final void zzc(int i, boolean z) throws IOException {
        this.zzem.zzc(i, z);
    }

    public final void zzb(int i, String str) throws IOException {
        this.zzem.zzb(i, str);
    }

    public final void zzb(int i, zzw zzw) throws IOException {
        this.zzem.zzb(i, zzw);
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzem.zze(i, i2);
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzem.zzf(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzem.zzc(i, j);
    }

    public final void zzb(int i, Object obj, zzda zzda) throws IOException {
        this.zzem.zzb(i, (zzck) obj, zzda);
    }

    public final void zzc(int i, Object obj, zzda zzda) throws IOException {
        zzaj zzaj = this.zzem;
        zzaj.zzc(i, 3);
        zzda.zzb((zzck) obj, zzaj.zzes);
        zzaj.zzc(i, 4);
    }

    public final void zzaa(int i) throws IOException {
        this.zzem.zzc(i, 3);
    }

    public final void zzab(int i) throws IOException {
        this.zzem.zzc(i, 4);
    }

    public final void zzb(int i, Object obj) throws IOException {
        if (obj instanceof zzw) {
            this.zzem.zzc(i, (zzw) obj);
        } else {
            this.zzem.zzb(i, (zzck) obj);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzs(list.get(i4).intValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzv(list.get(i4).intValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzf(list.get(i4).longValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzg(list.get(i4).longValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzi(list.get(i4).longValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zze(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzd(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zze(list.get(i4).floatValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzd(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzb(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzc(list.get(i4).doubleValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzb(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzx(list.get(i4).intValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzj(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzd(list.get(i4).booleanValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzc(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzc(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzbr) {
            zzbr zzbr = (zzbr) list;
            while (i2 < list.size()) {
                Object zzae = zzbr.zzae(i2);
                if (zzae instanceof String) {
                    this.zzem.zzb(i, (String) zzae);
                } else {
                    this.zzem.zzb(i, (zzw) zzae);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzb(i, list.get(i2));
            i2++;
        }
    }

    public final void zzc(int i, List<zzw> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzem.zzb(i, list.get(i2));
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzt(list.get(i4).intValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzo(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzw(list.get(i4).intValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzj(list.get(i4).longValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zze(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzd(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzu(list.get(i4).intValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzp(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzo(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzem.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzaj.zzh(list.get(i4).longValue());
            }
            this.zzem.zzo(i3);
            while (i2 < list.size()) {
                this.zzem.zzd(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzem.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzb(int i, List<?> list, zzda zzda) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzda);
        }
    }

    public final void zzc(int i, List<?> list, zzda zzda) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzc(i, (Object) list.get(i2), zzda);
        }
    }

    public final <K, V> void zzb(int i, zzcb<K, V> zzcb, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zzem.zzc(i, 2);
            this.zzem.zzo(zzcc.zzb(zzcb, next.getKey(), next.getValue()));
            zzcc.zzb(this.zzem, zzcb, next.getKey(), next.getValue());
        }
    }
}
