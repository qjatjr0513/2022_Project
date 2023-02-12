package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzabn {
    private static final Class<?> zza;
    private static final zzabz<?, ?> zzb = zzab(false);
    private static final zzabz<?, ?> zzc = zzab(true);
    private static final zzabz<?, ?> zzd = new zzacb();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        zza = cls;
    }

    public static zzabz<?, ?> zzA() {
        return zzc;
    }

    public static zzabz<?, ?> zzB() {
        return zzd;
    }

    static <UT, UB> UB zzC(int i, List<Integer> list, zzaaa zzaaa, UB ub, zzabz<UT, UB> zzabz) {
        if (zzaaa == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzaaa.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zzD(i, intValue, ub, zzabz);
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
                if (!zzaaa.zza()) {
                    ub = zzD(i, intValue2, ub, zzabz);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zzD(int i, int i2, UB ub, zzabz<UT, UB> zzabz) {
        if (ub == null) {
            ub = zzabz.zzf();
        }
        zzabz.zzl(ub, i, (long) i2);
        return ub;
    }

    static <T, FT extends zzzn<FT>> void zzE(zzzk<FT> zzzk, T t, T t2) {
        zzzk.zza(t2);
        throw null;
    }

    static <T, UT, UB> void zzF(zzabz<UT, UB> zzabz, T t, T t2) {
        zzabz.zzo(t, zzabz.zze(zzabz.zzd(t), zzabz.zzd(t2)));
    }

    public static void zzG(Class<?> cls) {
        Class<?> cls2;
        if (!zzzw.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzH(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zzI(zzaau zzaau, T t, T t2, long j) {
        zzacj.zzs(t, j, zzaau.zzc(zzacj.zzf(t, j), zzacj.zzf(t2, j)));
    }

    public static void zzJ(int i, List<Boolean> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzc(i, list, z);
        }
    }

    public static void zzK(int i, List<zzyu> list, zzzf zzzf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zze(i, list);
        }
    }

    public static void zzL(int i, List<Double> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzg(i, list, z);
        }
    }

    public static void zzM(int i, List<Integer> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzj(i, list, z);
        }
    }

    public static void zzN(int i, List<Integer> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzl(i, list, z);
        }
    }

    public static void zzO(int i, List<Long> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzn(i, list, z);
        }
    }

    public static void zzP(int i, List<Float> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzp(i, list, z);
        }
    }

    public static void zzQ(int i, List<?> list, zzzf zzzf, zzabl zzabl) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzzf.zzq(i, list.get(i2), zzabl);
            }
        }
    }

    public static void zzR(int i, List<Integer> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzs(i, list, z);
        }
    }

    public static void zzS(int i, List<Long> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzu(i, list, z);
        }
    }

    public static void zzT(int i, List<?> list, zzzf zzzf, zzabl zzabl) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzzf.zzv(i, list.get(i2), zzabl);
            }
        }
    }

    public static void zzU(int i, List<Integer> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzx(i, list, z);
        }
    }

    public static void zzV(int i, List<Long> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzz(i, list, z);
        }
    }

    public static void zzW(int i, List<Integer> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzB(i, list, z);
        }
    }

    public static void zzX(int i, List<Long> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzD(i, list, z);
        }
    }

    public static void zzY(int i, List<String> list, zzzf zzzf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzG(i, list);
        }
    }

    public static void zzZ(int i, List<Integer> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzI(i, list, z);
        }
    }

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzze.zzE(i << 3) + 1);
    }

    public static void zzaa(int i, List<Long> list, zzzf zzzf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzzf.zzK(i, list, z);
        }
    }

    private static zzabz<?, ?> zzab(boolean z) {
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
            return (zzabz) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th2) {
            return null;
        }
    }

    static int zzb(List<?> list) {
        return list.size();
    }

    static int zzc(int i, List<zzyu> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = size * zzze.zzD(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzD += zzze.zzw(list.get(i2));
        }
        return zzD;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzze.zzD(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            i = 0;
            while (i2 < size) {
                i += zzze.zzy(zzzx.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzze.zzy(list.get(i2).intValue());
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
        return size * (zzze.zzE(i << 3) + 4);
    }

    static int zzg(List<?> list) {
        return list.size() * 4;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzze.zzE(i << 3) + 8);
    }

    static int zzi(List<?> list) {
        return list.size() * 8;
    }

    static int zzj(int i, List<zzaaz> list, zzabl zzabl) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzze.zzx(i, list.get(i3), zzabl);
        }
        return i2;
    }

    static int zzk(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzze.zzD(i));
    }

    static int zzl(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            i = 0;
            while (i2 < size) {
                i += zzze.zzy(zzzx.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzze.zzy(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzm(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzze.zzD(i));
    }

    static int zzn(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            i = 0;
            while (i2 < size) {
                i += zzze.zzF(zzaao.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzze.zzF(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, Object obj, zzabl zzabl) {
        if (!(obj instanceof zzaah)) {
            return zzze.zzE(i << 3) + zzze.zzA((zzaaz) obj, zzabl);
        }
        int zzE = zzze.zzE(i << 3);
        int zza2 = ((zzaah) obj).zza();
        return zzE + zzze.zzE(zza2) + zza2;
    }

    static int zzp(int i, List<?> list, zzabl zzabl) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = zzze.zzD(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzaah) {
                i2 = zzze.zzz((zzaah) obj);
            } else {
                i2 = zzze.zzA((zzaaz) obj, zzabl);
            }
            zzD += i2;
        }
        return zzD;
    }

    static int zzq(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzze.zzD(i));
    }

    static int zzr(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            i = 0;
            while (i2 < size) {
                int zze = zzzx.zze(i2);
                i += zzze.zzE((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = list.get(i2).intValue();
                i3 = i + zzze.zzE((intValue >> 31) ^ (intValue + intValue));
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
        return zzt(list) + (size * zzze.zzD(i));
    }

    static int zzt(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            i = 0;
            while (i2 < size) {
                long zze = zzaao.zze(i2);
                i += zzze.zzF((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = list.get(i2).longValue();
                i3 = i + zzze.zzF((longValue >> 63) ^ (longValue + longValue));
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
        int zzD = zzze.zzD(i) * size;
        if (list instanceof zzaaj) {
            zzaaj zzaaj = (zzaaj) list;
            while (i4 < size) {
                Object zzf = zzaaj.zzf(i4);
                if (zzf instanceof zzyu) {
                    i3 = zzze.zzw((zzyu) zzf);
                } else {
                    i3 = zzze.zzC((String) zzf);
                }
                zzD += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzyu) {
                    i2 = zzze.zzw((zzyu) obj);
                } else {
                    i2 = zzze.zzC((String) obj);
                }
                zzD += i2;
                i4++;
            }
        }
        return zzD;
    }

    static int zzv(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzze.zzD(i));
    }

    static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            i = 0;
            while (i2 < size) {
                i += zzze.zzE(zzzx.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzze.zzE(list.get(i2).intValue());
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
        return zzy(list) + (size * zzze.zzD(i));
    }

    static int zzy(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            i = 0;
            while (i2 < size) {
                i += zzze.zzF(zzaao.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzze.zzF(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzabz<?, ?> zzz() {
        return zzb;
    }
}
