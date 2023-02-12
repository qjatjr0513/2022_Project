package com.google.android.gms.internal.places;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzdc {
    private static final Class<?> zzlv = zzdc();
    private static final zzds<?, ?> zzlw = zzf(false);
    private static final zzds<?, ?> zzlx = zzf(true);
    private static final zzds<?, ?> zzly = new zzdu();

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzbc.class.isAssignableFrom(cls) && (cls2 = zzlv) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzb(int i, List<Double> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzh(i, list, z);
        }
    }

    public static void zzc(int i, List<Float> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzg(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zze(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzo(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzf(i, list, z);
        }
    }

    public static void zzh(int i, List<Long> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzm(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzb(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzk(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzn(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzc(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzl(i, list, z);
        }
    }

    public static void zzn(int i, List<Integer> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzi(i, list, z);
        }
    }

    public static void zzo(int i, List<Boolean> list, zzel zzel, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzj(i, list, z);
        }
    }

    public static void zzb(int i, List<String> list, zzel zzel) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzb(i, list);
        }
    }

    public static void zzc(int i, List<zzw> list, zzel zzel) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzc(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzel zzel, zzda zzda) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzb(i, list, zzda);
        }
    }

    public static void zzc(int i, List<?> list, zzel zzel, zzda zzda) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzel.zzc(i, list, zzda);
        }
    }

    static int zze(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzby) {
            zzby zzby = (zzby) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzf(zzby.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zze(list) + (list.size() * zzaj.zzr(i));
    }

    static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzby) {
            zzby zzby = (zzby) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzg(zzby.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzaj.zzr(i));
    }

    static int zzg(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzby) {
            zzby zzby = (zzby) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzh(zzby.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzh(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzaj.zzr(i));
    }

    static int zzh(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbe = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzx(zzbe.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzaj.zzr(i));
    }

    static int zzi(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbe = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzs(zzbe.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzs(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzaj.zzr(i));
    }

    static int zzj(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbe = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzt(zzbe.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzt(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzaj.zzr(i));
    }

    static int zzk(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbe) {
            zzbe zzbe = (zzbe) list;
            i = 0;
            while (i2 < size) {
                i += zzaj.zzu(zzbe.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzaj.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzv(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzaj.zzr(i));
    }

    static int zzl(List<?> list) {
        return list.size() << 2;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaj.zzk(i, 0);
    }

    static int zzm(List<?> list) {
        return list.size() << 3;
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaj.zzh(i, 0);
    }

    static int zzn(List<?> list) {
        return list.size();
    }

    static int zzy(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaj.zzd(i, true);
    }

    static int zzd(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzaj.zzr(i) * size;
        if (list instanceof zzbr) {
            zzbr zzbr = (zzbr) list;
            while (i4 < size) {
                Object zzae = zzbr.zzae(i4);
                if (zzae instanceof zzw) {
                    i3 = zzaj.zzc((zzw) zzae);
                } else {
                    i3 = zzaj.zzk((String) zzae);
                }
                zzr += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzw) {
                    i2 = zzaj.zzc((zzw) obj);
                } else {
                    i2 = zzaj.zzk((String) obj);
                }
                zzr += i2;
                i4++;
            }
        }
        return zzr;
    }

    static int zzd(int i, Object obj, zzda zzda) {
        if (obj instanceof zzbp) {
            return zzaj.zzb(i, (zzbp) obj);
        }
        return zzaj.zzc(i, (zzck) obj, zzda);
    }

    static int zzd(int i, List<?> list, zzda zzda) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzaj.zzr(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzbp) {
                i2 = zzaj.zzb((zzbp) obj);
            } else {
                i2 = zzaj.zzb((zzck) obj, zzda);
            }
            zzr += i2;
        }
        return zzr;
    }

    static int zze(int i, List<zzw> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzaj.zzr(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzr += zzaj.zzc(list.get(i2));
        }
        return zzr;
    }

    static int zze(int i, List<zzck> list, zzda zzda) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzaj.zzd(i, list.get(i3), zzda);
        }
        return i2;
    }

    public static zzds<?, ?> zzcz() {
        return zzlw;
    }

    public static zzds<?, ?> zzda() {
        return zzlx;
    }

    public static zzds<?, ?> zzdb() {
        return zzly;
    }

    private static zzds<?, ?> zzf(boolean z) {
        try {
            Class<?> zzdd = zzdd();
            if (zzdd == null) {
                return null;
            }
            return (zzds) zzdd.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzdc() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzdd() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zzb(zzcd zzcd, T t, T t2, long j) {
        zzdy.zzb((Object) t, j, zzcd.zzc(zzdy.zzp(t, j), zzdy.zzp(t2, j)));
    }

    static <T, FT extends zzax<FT>> void zzb(zzar<FT> zzar, T t, T t2) {
        zzav<FT> zzb = zzar.zzb((Object) t2);
        if (!zzb.zzfj.isEmpty()) {
            zzar.zzc(t).zzb(zzb);
        }
    }

    static <T, UT, UB> void zzb(zzds<UT, UB> zzds, T t, T t2) {
        zzds.zzf(t, zzds.zzh(zzds.zzr(t), zzds.zzr(t2)));
    }

    static <UT, UB> UB zzb(int i, List<Integer> list, zzbf zzbf, UB ub, zzds<UT, UB> zzds) {
        if (zzbf == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzbf.zzad(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zzb(i, intValue, ub, zzds);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzbf.zzad(intValue2)) {
                    ub = zzb(i, intValue2, ub, zzds);
                    it.remove();
                }
            }
        }
        return ub;
    }

    private static <UT, UB> UB zzb(int i, int i2, UB ub, zzds<UT, UB> zzds) {
        if (ub == null) {
            ub = zzds.zzdk();
        }
        zzds.zzb(ub, i, (long) i2);
        return ub;
    }
}
