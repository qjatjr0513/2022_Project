package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzadm {
    private static final Class<?> zza;
    private static final zzaeb<?, ?> zzb = zzZ(false);
    private static final zzaeb<?, ?> zzc = zzZ(true);
    private static final zzaeb<?, ?> zzd = new zzaed();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        zza = cls;
    }

    public static zzaeb<?, ?> zzA() {
        return zzc;
    }

    public static zzaeb<?, ?> zzB() {
        return zzd;
    }

    static <T, FT extends zzabl<FT>> void zzC(zzabi<FT> zzabi, T t, T t2) {
        zzabi.zza(t2);
        throw null;
    }

    static <T, UT, UB> void zzD(zzaeb<UT, UB> zzaeb, T t, T t2) {
        zzaeb.zzf(t, zzaeb.zzd(zzaeb.zzc(t), zzaeb.zzc(t2)));
    }

    public static void zzE(Class<?> cls) {
        Class<?> cls2;
        if (!zzabs.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzF(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zzG(zzacu zzacu, T t, T t2, long j) {
        zzact zzact = (zzact) zzael.zzf(t, j);
        zzact zzact2 = (zzact) zzael.zzf(t2, j);
        if (!zzact2.isEmpty()) {
            if (!zzact.zzd()) {
                zzact = zzact.zza();
            }
            zzact.zzc(zzact2);
        }
        zzael.zzs(t, j, zzact);
    }

    public static void zzH(int i, List<Boolean> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzc(i, list, z);
        }
    }

    public static void zzI(int i, List<zzaax> list, zzabg zzabg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zze(i, list);
        }
    }

    public static void zzJ(int i, List<Double> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzg(i, list, z);
        }
    }

    public static void zzK(int i, List<Integer> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzi(i, list, z);
        }
    }

    public static void zzL(int i, List<Integer> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzk(i, list, z);
        }
    }

    public static void zzM(int i, List<Long> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzm(i, list, z);
        }
    }

    public static void zzN(int i, List<Float> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzo(i, list, z);
        }
    }

    public static void zzO(int i, List<?> list, zzabg zzabg, zzadk zzadk) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzabg.zzp(i, list.get(i2), zzadk);
            }
        }
    }

    public static void zzP(int i, List<Integer> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzr(i, list, z);
        }
    }

    public static void zzQ(int i, List<Long> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzt(i, list, z);
        }
    }

    public static void zzR(int i, List<?> list, zzabg zzabg, zzadk zzadk) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzabg.zzu(i, list.get(i2), zzadk);
            }
        }
    }

    public static void zzS(int i, List<Integer> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzw(i, list, z);
        }
    }

    public static void zzT(int i, List<Long> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzy(i, list, z);
        }
    }

    public static void zzU(int i, List<Integer> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzA(i, list, z);
        }
    }

    public static void zzV(int i, List<Long> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzC(i, list, z);
        }
    }

    public static void zzW(int i, List<String> list, zzabg zzabg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzE(i, list);
        }
    }

    public static void zzX(int i, List<Integer> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzG(i, list, z);
        }
    }

    public static void zzY(int i, List<Long> list, zzabg zzabg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzabg.zzI(i, list, z);
        }
    }

    private static zzaeb<?, ?> zzZ(boolean z) {
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
            return (zzaeb) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th2) {
            return null;
        }
    }

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzabf.zzA(i << 3) + 1);
    }

    static int zzb(List<?> list) {
        return list.size();
    }

    static int zzc(int i, List<zzaax> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzabf.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzz += zzabf.zzt(list.get(i2));
        }
        return zzz;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzabf.zzz(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzabt) {
            zzabt zzabt = (zzabt) list;
            i = 0;
            while (i2 < size) {
                i += zzabf.zzv(zzabt.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzabf.zzv(list.get(i2).intValue());
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
        return size * (zzabf.zzA(i << 3) + 4);
    }

    static int zzg(List<?> list) {
        return list.size() * 4;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzabf.zzA(i << 3) + 8);
    }

    static int zzi(List<?> list) {
        return list.size() * 8;
    }

    static int zzj(int i, List<zzacz> list, zzadk zzadk) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzabf.zzu(i, list.get(i3), zzadk);
        }
        return i2;
    }

    static int zzk(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzabf.zzz(i));
    }

    static int zzl(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzabt) {
            zzabt zzabt = (zzabt) list;
            i = 0;
            while (i2 < size) {
                i += zzabf.zzv(zzabt.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzabf.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzm(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzabf.zzz(i));
    }

    static int zzn(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaco) {
            zzaco zzaco = (zzaco) list;
            i = 0;
            while (i2 < size) {
                i += zzabf.zzB(zzaco.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzabf.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, Object obj, zzadk zzadk) {
        if (!(obj instanceof zzacf)) {
            return zzabf.zzA(i << 3) + zzabf.zzx((zzacz) obj, zzadk);
        }
        int zzA = zzabf.zzA(i << 3);
        int zza2 = ((zzacf) obj).zza();
        return zzA + zzabf.zzA(zza2) + zza2;
    }

    static int zzp(int i, List<?> list, zzadk zzadk) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzabf.zzz(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzacf) {
                i2 = zzabf.zzw((zzacf) obj);
            } else {
                i2 = zzabf.zzx((zzacz) obj, zzadk);
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
        return zzr(list) + (size * zzabf.zzz(i));
    }

    static int zzr(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzabt) {
            zzabt zzabt = (zzabt) list;
            i = 0;
            while (i2 < size) {
                int zzd2 = zzabt.zzd(i2);
                i += zzabf.zzA((zzd2 >> 31) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = list.get(i2).intValue();
                i3 = i + zzabf.zzA((intValue >> 31) ^ (intValue + intValue));
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
        return zzt(list) + (size * zzabf.zzz(i));
    }

    static int zzt(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaco) {
            zzaco zzaco = (zzaco) list;
            i = 0;
            while (i2 < size) {
                long zzd2 = zzaco.zzd(i2);
                i += zzabf.zzB((zzd2 >> 63) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = list.get(i2).longValue();
                i3 = i + zzabf.zzB((longValue >> 63) ^ (longValue + longValue));
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
        int zzz = zzabf.zzz(i) * size;
        if (list instanceof zzach) {
            zzach zzach = (zzach) list;
            while (i4 < size) {
                Object zze = zzach.zze(i4);
                if (zze instanceof zzaax) {
                    i3 = zzabf.zzt((zzaax) zze);
                } else {
                    i3 = zzabf.zzy((String) zze);
                }
                zzz += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzaax) {
                    i2 = zzabf.zzt((zzaax) obj);
                } else {
                    i2 = zzabf.zzy((String) obj);
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
        return zzw(list) + (size * zzabf.zzz(i));
    }

    static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzabt) {
            zzabt zzabt = (zzabt) list;
            i = 0;
            while (i2 < size) {
                i += zzabf.zzA(zzabt.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzabf.zzA(list.get(i2).intValue());
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
        return zzy(list) + (size * zzabf.zzz(i));
    }

    static int zzy(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaco) {
            zzaco zzaco = (zzaco) list;
            i = 0;
            while (i2 < size) {
                i += zzabf.zzB(zzaco.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzabf.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzaeb<?, ?> zzz() {
        return zzb;
    }
}
