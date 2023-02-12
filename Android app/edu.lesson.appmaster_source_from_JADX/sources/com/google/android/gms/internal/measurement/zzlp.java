package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzlp {
    private static final Class<?> zza;
    private static final zzmb<?, ?> zzb = zzab(false);
    private static final zzmb<?, ?> zzc = zzab(true);
    private static final zzmb<?, ?> zzd = new zzmd();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        zza = cls;
    }

    public static zzmb<?, ?> zzA() {
        return zzc;
    }

    public static zzmb<?, ?> zzB() {
        return zzd;
    }

    static <UT, UB> UB zzC(int i, List<Integer> list, zzkb zzkb, UB ub, zzmb<UT, UB> zzmb) {
        if (zzkb == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzkb.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zzD(i, intValue, ub, zzmb);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub;
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzkb.zza(intValue2)) {
                    ub = zzD(i, intValue2, ub, zzmb);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zzD(int i, int i2, UB ub, zzmb<UT, UB> zzmb) {
        if (ub == null) {
            ub = zzmb.zze();
        }
        zzmb.zzf(ub, i, (long) i2);
        return ub;
    }

    static <T, FT extends zzjn<FT>> void zzE(zzjk<FT> zzjk, T t, T t2) {
        zzjk.zza(t2);
        throw null;
    }

    static <T, UT, UB> void zzF(zzmb<UT, UB> zzmb, T t, T t2) {
        zzmb.zzh(t, zzmb.zzd(zzmb.zzc(t), zzmb.zzc(t2)));
    }

    public static void zzG(Class<?> cls) {
        Class<?> cls2;
        if (!zzjx.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzH(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zzI(zzkx zzkx, T t, T t2, long j) {
        zzml.zzs(t, j, zzkx.zzb(zzml.zzf(t, j), zzml.zzf(t2, j)));
    }

    public static void zzJ(int i, List<Boolean> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzc(i, list, z);
        }
    }

    public static void zzK(int i, List<zzix> list, zzjf zzjf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zze(i, list);
        }
    }

    public static void zzL(int i, List<Double> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzg(i, list, z);
        }
    }

    public static void zzM(int i, List<Integer> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzj(i, list, z);
        }
    }

    public static void zzN(int i, List<Integer> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzl(i, list, z);
        }
    }

    public static void zzO(int i, List<Long> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzn(i, list, z);
        }
    }

    public static void zzP(int i, List<Float> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzp(i, list, z);
        }
    }

    public static void zzQ(int i, List<?> list, zzjf zzjf, zzln zzln) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzjf.zzq(i, list.get(i2), zzln);
            }
        }
    }

    public static void zzR(int i, List<Integer> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzs(i, list, z);
        }
    }

    public static void zzS(int i, List<Long> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzu(i, list, z);
        }
    }

    public static void zzT(int i, List<?> list, zzjf zzjf, zzln zzln) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzjf.zzv(i, list.get(i2), zzln);
            }
        }
    }

    public static void zzU(int i, List<Integer> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzx(i, list, z);
        }
    }

    public static void zzV(int i, List<Long> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzz(i, list, z);
        }
    }

    public static void zzW(int i, List<Integer> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzB(i, list, z);
        }
    }

    public static void zzX(int i, List<Long> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzD(i, list, z);
        }
    }

    public static void zzY(int i, List<String> list, zzjf zzjf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzG(i, list);
        }
    }

    public static void zzZ(int i, List<Integer> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzI(i, list, z);
        }
    }

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzje.zzA(i << 3) + 1);
    }

    public static void zzaa(int i, List<Long> list, zzjf zzjf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjf.zzK(i, list, z);
        }
    }

    private static zzmb<?, ?> zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzmb) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th2) {
            return null;
        }
    }

    static int zzb(List<?> list) {
        return list.size();
    }

    static int zzc(int i, List<zzix> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzje.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzz += zzje.zzt(list.get(i2));
        }
        return zzz;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzje.zzz(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjy) {
            zzjy zzjy = (zzjy) list;
            i = 0;
            while (i2 < size) {
                i += zzje.zzv(zzjy.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzje.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzje.zzA(i << 3) + 4);
    }

    static int zzg(List<?> list) {
        return list.size() * 4;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzje.zzA(i << 3) + 8);
    }

    static int zzi(List<?> list) {
        return list.size() * 8;
    }

    static int zzj(int i, List<zzlc> list, zzln zzln) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzje.zzu(i, list.get(i3), zzln);
        }
        return i2;
    }

    static int zzk(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzje.zzz(i));
    }

    static int zzl(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjy) {
            zzjy zzjy = (zzjy) list;
            i = 0;
            while (i2 < size) {
                i += zzje.zzv(zzjy.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzje.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzm(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzje.zzz(i));
    }

    static int zzn(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkr) {
            zzkr zzkr = (zzkr) list;
            i = 0;
            while (i2 < size) {
                i += zzje.zzB(zzkr.zza(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzje.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, Object obj, zzln zzln) {
        if (!(obj instanceof zzkk)) {
            return zzje.zzA(i << 3) + zzje.zzx((zzlc) obj, zzln);
        }
        int zzA = zzje.zzA(i << 3);
        int zza2 = ((zzkk) obj).zza();
        return zzA + zzje.zzA(zza2) + zza2;
    }

    static int zzp(int i, List<?> list, zzln zzln) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzje.zzz(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzkk) {
                i2 = zzje.zzw((zzkk) obj);
            } else {
                i2 = zzje.zzx((zzlc) obj, zzln);
            }
            zzz += i2;
        }
        return zzz;
    }

    static int zzq(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzje.zzz(i));
    }

    static int zzr(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjy) {
            zzjy zzjy = (zzjy) list;
            i = 0;
            while (i2 < size) {
                int zze = zzjy.zze(i2);
                i += zzje.zzA((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = list.get(i2).intValue();
                i3 = i + zzje.zzA((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzje.zzz(i));
    }

    static int zzt(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkr) {
            zzkr zzkr = (zzkr) list;
            i = 0;
            while (i2 < size) {
                long zza2 = zzkr.zza(i2);
                i += zzje.zzB((zza2 >> 63) ^ (zza2 + zza2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = list.get(i2).longValue();
                i3 = i + zzje.zzB((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzje.zzz(i) * size;
        if (list instanceof zzkm) {
            zzkm zzkm = (zzkm) list;
            while (i4 < size) {
                Object zzf = zzkm.zzf(i4);
                if (zzf instanceof zzix) {
                    i3 = zzje.zzt((zzix) zzf);
                } else {
                    i3 = zzje.zzy((String) zzf);
                }
                zzz += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzix) {
                    i2 = zzje.zzt((zzix) obj);
                } else {
                    i2 = zzje.zzy((String) obj);
                }
                zzz += i2;
                i4++;
            }
        }
        return zzz;
    }

    static int zzv(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzje.zzz(i));
    }

    static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjy) {
            zzjy zzjy = (zzjy) list;
            i = 0;
            while (i2 < size) {
                i += zzje.zzA(zzjy.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzje.zzA(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzx(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzje.zzz(i));
    }

    static int zzy(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkr) {
            zzkr zzkr = (zzkr) list;
            i = 0;
            while (i2 < size) {
                i += zzje.zzB(zzkr.zza(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzje.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzmb<?, ?> zzz() {
        return zzb;
    }
}
