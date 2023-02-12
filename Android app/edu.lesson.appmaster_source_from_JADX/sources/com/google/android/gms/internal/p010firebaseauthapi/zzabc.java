package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabc */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzabc<T> implements zzabl<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzacj.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaaz zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzaan zzn;
    private final zzabz<?, ?> zzo;
    private final zzzk<?> zzp;
    private final zzabe zzq;
    private final zzaau zzr;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaaz} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzabe} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaau} */
    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.firebase-auth-api.zzzk<?>, com.google.android.gms.internal.firebase-auth-api.zzzk] */
    /* JADX WARNING: type inference failed for: r3v2, types: [int] */
    /* JADX WARNING: type inference failed for: r3v9, types: [int] */
    /* JADX WARNING: type inference failed for: r3v12, types: [com.google.android.gms.internal.firebase-auth-api.zzaan] */
    /* JADX WARNING: type inference failed for: r3v13, types: [com.google.android.gms.internal.firebase-auth-api.zzabz<?, ?>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzabc(int[] r6, int[] r7, java.lang.Object[] r8, int r9, int r10, com.google.android.gms.internal.p010firebaseauthapi.zzaaz r11, boolean r12, boolean r13, int[] r14, int r15, int r16, com.google.android.gms.internal.p010firebaseauthapi.zzabe r17, com.google.android.gms.internal.p010firebaseauthapi.zzaan r18, com.google.android.gms.internal.p010firebaseauthapi.zzabz<?, ?> r19, com.google.android.gms.internal.p010firebaseauthapi.zzzk<?> r20, com.google.android.gms.internal.p010firebaseauthapi.zzaau r21) {
        /*
            r5 = this;
            r0 = r5
            r1 = r10
            r2 = r19
            r5.<init>()
            r3 = r6
            r0.zzc = r3
            r3 = r7
            r0.zzd = r3
            r3 = r8
            r0.zze = r3
            r3 = r9
            r0.zzf = r3
            boolean r3 = r1 instanceof com.google.android.gms.internal.p010firebaseauthapi.zzzw
            r0.zzi = r3
            r3 = r11
            r0.zzj = r3
            r3 = 0
            if (r2 == 0) goto L_0x0025
            boolean r4 = r2.zzh(r10)
            if (r4 == 0) goto L_0x0025
            r3 = 1
            goto L_0x0026
        L_0x0025:
        L_0x0026:
            r0.zzh = r3
            r3 = r13
            r0.zzk = r3
            r3 = r14
            r0.zzl = r3
            r3 = r15
            r0.zzm = r3
            r3 = r16
            r0.zzq = r3
            r3 = r17
            r0.zzn = r3
            r3 = r18
            r0.zzo = r3
            r0.zzp = r2
            r0.zzg = r1
            r1 = r20
            r0.zzr = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzabc.<init>(int[], java.lang.Object[], int, int, com.google.android.gms.internal.firebase-auth-api.zzaaz, boolean, boolean, int[], int, int, com.google.android.gms.internal.firebase-auth-api.zzabe, com.google.android.gms.internal.firebase-auth-api.zzaan, com.google.android.gms.internal.firebase-auth-api.zzabz, com.google.android.gms.internal.firebase-auth-api.zzzk, com.google.android.gms.internal.firebase-auth-api.zzaau, byte[]):void");
    }

    private final int zzA(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzB(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzC(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzD(T t, long j) {
        return ((Long) zzacj.zzf(t, j)).longValue();
    }

    private final zzaaa zzE(int i) {
        int i2 = i / 3;
        return (zzaaa) this.zzd[i2 + i2 + 1];
    }

    private final zzabl zzF(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzabl zzabl = (zzabl) this.zzd[i3];
        if (zzabl != null) {
            return zzabl;
        }
        zzabl zzb2 = zzabh.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final <UT, UB> UB zzG(Object obj, int i, UB ub, zzabz<UT, UB> zzabz) {
        int i2 = this.zzc[i];
        Object zzf2 = zzacj.zzf(obj, (long) (zzC(i) & 1048575));
        if (zzf2 == null || zzE(i) == null) {
            return ub;
        }
        zzaat zzaat = (zzaat) zzf2;
        zzaas zzaas = (zzaas) zzH(i);
        throw null;
    }

    private final Object zzH(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzI(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzJ(T t, T t2, int i) {
        long zzC = (long) (zzC(i) & 1048575);
        if (zzQ(t2, i)) {
            Object zzf2 = zzacj.zzf(t, zzC);
            Object zzf3 = zzacj.zzf(t2, zzC);
            if (zzf2 != null && zzf3 != null) {
                zzacj.zzs(t, zzC, zzaac.zzg(zzf2, zzf3));
                zzM(t, i);
            } else if (zzf3 != null) {
                zzacj.zzs(t, zzC, zzf3);
                zzM(t, i);
            }
        }
    }

    private final void zzK(T t, T t2, int i) {
        Object obj;
        int zzC = zzC(i);
        int i2 = this.zzc[i];
        long j = (long) (zzC & 1048575);
        if (zzT(t2, i2, i)) {
            if (zzT(t, i2, i)) {
                obj = zzacj.zzf(t, j);
            } else {
                obj = null;
            }
            Object zzf2 = zzacj.zzf(t2, j);
            if (obj != null && zzf2 != null) {
                zzacj.zzs(t, j, zzaac.zzg(obj, zzf2));
                zzN(t, i2, i);
            } else if (zzf2 != null) {
                zzacj.zzs(t, j, zzf2);
                zzN(t, i2, i);
            }
        }
    }

    private final void zzL(Object obj, int i, zzabk zzabk) throws IOException {
        if (zzP(i)) {
            zzacj.zzs(obj, (long) (i & 1048575), zzabk.zzu());
        } else if (this.zzi) {
            zzacj.zzs(obj, (long) (i & 1048575), zzabk.zzt());
        } else {
            zzacj.zzs(obj, (long) (i & 1048575), zzabk.zzp());
        }
    }

    private final void zzM(T t, int i) {
        int zzz = zzz(i);
        long j = (long) (1048575 & zzz);
        if (j != 1048575) {
            zzacj.zzq(t, j, (1 << (zzz >>> 20)) | zzacj.zzc(t, j));
        }
    }

    private final void zzN(T t, int i, int i2) {
        zzacj.zzq(t, (long) (zzz(i2) & 1048575), i);
    }

    private final boolean zzO(T t, T t2, int i) {
        return zzQ(t, i) == zzQ(t2, i);
    }

    private static boolean zzP(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzQ(T t, int i) {
        int zzz = zzz(i);
        long j = (long) (zzz & 1048575);
        if (j != 1048575) {
            return (zzacj.zzc(t, j) & (1 << (zzz >>> 20))) != 0;
        }
        int zzC = zzC(i);
        long j2 = (long) (zzC & 1048575);
        switch (zzB(zzC)) {
            case 0:
                return zzacj.zza(t, j2) != 0.0d;
            case 1:
                return zzacj.zzb(t, j2) != 0.0f;
            case 2:
                return zzacj.zzd(t, j2) != 0;
            case 3:
                return zzacj.zzd(t, j2) != 0;
            case 4:
                return zzacj.zzc(t, j2) != 0;
            case 5:
                return zzacj.zzd(t, j2) != 0;
            case 6:
                return zzacj.zzc(t, j2) != 0;
            case 7:
                return zzacj.zzw(t, j2);
            case 8:
                Object zzf2 = zzacj.zzf(t, j2);
                if (zzf2 instanceof String) {
                    return !((String) zzf2).isEmpty();
                }
                if (zzf2 instanceof zzyu) {
                    return !zzyu.zzb.equals(zzf2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzacj.zzf(t, j2) != null;
            case 10:
                return !zzyu.zzb.equals(zzacj.zzf(t, j2));
            case 11:
                return zzacj.zzc(t, j2) != 0;
            case 12:
                return zzacj.zzc(t, j2) != 0;
            case 13:
                return zzacj.zzc(t, j2) != 0;
            case 14:
                return zzacj.zzd(t, j2) != 0;
            case 15:
                return zzacj.zzc(t, j2) != 0;
            case 16:
                return zzacj.zzd(t, j2) != 0;
            case 17:
                return zzacj.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzR(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzQ(t, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzS(Object obj, int i, zzabl zzabl) {
        return zzabl.zzk(zzacj.zzf(obj, (long) (i & 1048575)));
    }

    private final boolean zzT(T t, int i, int i2) {
        return zzacj.zzc(t, (long) (zzz(i2) & 1048575)) == i;
    }

    private static <T> boolean zzU(T t, long j) {
        return ((Boolean) zzacj.zzf(t, j)).booleanValue();
    }

    private final void zzV(T t, zzzf zzzf) throws IOException {
        int i;
        T t2 = t;
        zzzf zzzf2 = zzzf;
        if (!this.zzh) {
            int length = this.zzc.length;
            Unsafe unsafe = zzb;
            int i2 = 1048575;
            int i3 = 1048575;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                int zzC = zzC(i4);
                int i6 = this.zzc[i4];
                int zzB = zzB(zzC);
                if (zzB <= 17) {
                    int i7 = this.zzc[i4 + 2];
                    int i8 = i7 & i2;
                    if (i8 != i3) {
                        i5 = unsafe.getInt(t2, (long) i8);
                        i3 = i8;
                    }
                    i = 1 << (i7 >>> 20);
                } else {
                    i = 0;
                }
                long j = (long) (zzC & i2);
                switch (zzB) {
                    case 0:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzf(i6, zzacj.zza(t2, j));
                            break;
                        }
                    case 1:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzo(i6, zzacj.zzb(t2, j));
                            break;
                        }
                    case 2:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzt(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 3:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzJ(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 4:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzr(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 5:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzm(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 6:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzk(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 7:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzb(i6, zzacj.zzw(t2, j));
                            break;
                        }
                    case 8:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzX(i6, unsafe.getObject(t2, j), zzzf2);
                            break;
                        }
                    case 9:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzv(i6, unsafe.getObject(t2, j), zzF(i4));
                            break;
                        }
                    case 10:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzd(i6, (zzyu) unsafe.getObject(t2, j));
                            break;
                        }
                    case 11:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzH(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 12:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzi(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 13:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzw(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 14:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzy(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 15:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzA(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 16:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzC(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 17:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzzf2.zzq(i6, unsafe.getObject(t2, j), zzF(i4));
                            break;
                        }
                    case 18:
                        zzabn.zzL(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 19:
                        zzabn.zzP(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 20:
                        zzabn.zzS(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 21:
                        zzabn.zzaa(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 22:
                        zzabn.zzR(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 23:
                        zzabn.zzO(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 24:
                        zzabn.zzN(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 25:
                        zzabn.zzJ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 26:
                        zzabn.zzY(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2);
                        break;
                    case 27:
                        zzabn.zzT(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, zzF(i4));
                        break;
                    case 28:
                        zzabn.zzK(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2);
                        break;
                    case 29:
                        zzabn.zzZ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 30:
                        zzabn.zzM(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 31:
                        zzabn.zzU(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 32:
                        zzabn.zzV(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 33:
                        zzabn.zzW(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 34:
                        zzabn.zzX(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, false);
                        break;
                    case 35:
                        zzabn.zzL(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 36:
                        zzabn.zzP(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 37:
                        zzabn.zzS(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 38:
                        zzabn.zzaa(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 39:
                        zzabn.zzR(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 40:
                        zzabn.zzO(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 41:
                        zzabn.zzN(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 42:
                        zzabn.zzJ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 43:
                        zzabn.zzZ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 44:
                        zzabn.zzM(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 45:
                        zzabn.zzU(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 46:
                        zzabn.zzV(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 47:
                        zzabn.zzW(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 48:
                        zzabn.zzX(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, true);
                        break;
                    case 49:
                        zzabn.zzQ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzzf2, zzF(i4));
                        break;
                    case 50:
                        zzW(zzzf2, i6, unsafe.getObject(t2, j), i4);
                        break;
                    case 51:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzf(i6, zzo(t2, j));
                            break;
                        }
                    case 52:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzo(i6, zzp(t2, j));
                            break;
                        }
                    case 53:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzt(i6, zzD(t2, j));
                            break;
                        }
                    case 54:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzJ(i6, zzD(t2, j));
                            break;
                        }
                    case 55:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzr(i6, zzs(t2, j));
                            break;
                        }
                    case 56:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzm(i6, zzD(t2, j));
                            break;
                        }
                    case 57:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzk(i6, zzs(t2, j));
                            break;
                        }
                    case 58:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzb(i6, zzU(t2, j));
                            break;
                        }
                    case 59:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzX(i6, unsafe.getObject(t2, j), zzzf2);
                            break;
                        }
                    case 60:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzv(i6, unsafe.getObject(t2, j), zzF(i4));
                            break;
                        }
                    case 61:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzd(i6, (zzyu) unsafe.getObject(t2, j));
                            break;
                        }
                    case 62:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzH(i6, zzs(t2, j));
                            break;
                        }
                    case 63:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzi(i6, zzs(t2, j));
                            break;
                        }
                    case 64:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzw(i6, zzs(t2, j));
                            break;
                        }
                    case 65:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzy(i6, zzD(t2, j));
                            break;
                        }
                    case 66:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzA(i6, zzs(t2, j));
                            break;
                        }
                    case 67:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzC(i6, zzD(t2, j));
                            break;
                        }
                    case 68:
                        if (!zzT(t2, i6, i4)) {
                            break;
                        } else {
                            zzzf2.zzq(i6, unsafe.getObject(t2, j), zzF(i4));
                            break;
                        }
                }
                i4 += 3;
                i2 = 1048575;
            }
            zzabz<?, ?> zzabz = this.zzo;
            zzabz.zzr(zzabz.zzd(t2), zzzf2);
            return;
        }
        this.zzp.zza(t2);
        throw null;
    }

    private static final void zzX(int i, Object obj, zzzf zzzf) throws IOException {
        if (obj instanceof String) {
            zzzf.zzF(i, (String) obj);
        } else {
            zzzf.zzd(i, (zzyu) obj);
        }
    }

    static zzaca zzd(Object obj) {
        zzzw zzzw = (zzzw) obj;
        zzaca zzaca = zzzw.zzc;
        if (zzaca != zzaca.zzc()) {
            return zzaca;
        }
        zzaca zze2 = zzaca.zze();
        zzzw.zzc = zze2;
        return zze2;
    }

    static <T> zzabc<T> zzl(Class<T> cls, zzaaw zzaaw, zzabe zzabe, zzaan zzaan, zzabz<?, ?> zzabz, zzzk<?> zzzk, zzaau zzaau) {
        if (zzaaw instanceof zzabj) {
            return zzm((zzabj) zzaaw, zzabe, zzaan, zzabz, zzzk, zzaau);
        }
        zzabw zzabw = (zzabw) zzaaw;
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:158:0x034b  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x03ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.p010firebaseauthapi.zzabc<T> zzm(com.google.android.gms.internal.p010firebaseauthapi.zzabj r34, com.google.android.gms.internal.p010firebaseauthapi.zzabe r35, com.google.android.gms.internal.p010firebaseauthapi.zzaan r36, com.google.android.gms.internal.p010firebaseauthapi.zzabz<?, ?> r37, com.google.android.gms.internal.p010firebaseauthapi.zzzk<?> r38, com.google.android.gms.internal.p010firebaseauthapi.zzaau r39) {
        /*
            int r0 = r34.zzc()
            r1 = 0
            r3 = 2
            if (r0 != r3) goto L_0x000a
            r10 = 1
            goto L_0x000b
        L_0x000a:
            r10 = r1
        L_0x000b:
            java.lang.String r0 = r34.zzd()
            int r3 = r0.length()
            char r4 = r0.charAt(r1)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0027
            r4 = 1
        L_0x001d:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0028
            r4 = r6
            goto L_0x001d
        L_0x0027:
            r6 = 1
        L_0x0028:
            int r4 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0048
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0034:
            int r9 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0044
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r8
            r6 = r6 | r4
            int r8 = r8 + 13
            r4 = r9
            goto L_0x0034
        L_0x0044:
            int r4 = r4 << r8
            r6 = r6 | r4
            r4 = r9
            goto L_0x0049
        L_0x0048:
        L_0x0049:
            if (r6 != 0) goto L_0x0059
            int[] r6 = zza
            r8 = r1
            r9 = r8
            r11 = r9
            r12 = r11
            r14 = r12
            r16 = r14
            r13 = r6
            r6 = r16
            goto L_0x0178
        L_0x0059:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0079
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0065:
            int r9 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0075
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r8
            r4 = r4 | r6
            int r8 = r8 + 13
            r6 = r9
            goto L_0x0065
        L_0x0075:
            int r6 = r6 << r8
            r4 = r4 | r6
            r6 = r9
            goto L_0x007a
        L_0x0079:
        L_0x007a:
            int r8 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x009a
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0086:
            int r11 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x0096
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r6 = r6 | r8
            int r9 = r9 + 13
            r8 = r11
            goto L_0x0086
        L_0x0096:
            int r8 = r8 << r9
            r6 = r6 | r8
            r8 = r11
            goto L_0x009b
        L_0x009a:
        L_0x009b:
            int r9 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x00bb
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a7:
            int r12 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00b7
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r11
            r8 = r8 | r9
            int r11 = r11 + 13
            r9 = r12
            goto L_0x00a7
        L_0x00b7:
            int r9 = r9 << r11
            r8 = r8 | r9
            r9 = r12
            goto L_0x00bc
        L_0x00bb:
        L_0x00bc:
            int r11 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00dc
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c8:
            int r13 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00d8
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r9 = r9 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c8
        L_0x00d8:
            int r11 = r11 << r12
            r9 = r9 | r11
            r11 = r13
            goto L_0x00dd
        L_0x00dc:
        L_0x00dd:
            int r12 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00fd
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00e9:
            int r14 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x00f9
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00e9
        L_0x00f9:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
            goto L_0x00fe
        L_0x00fd:
        L_0x00fe:
            int r13 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x011e
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x010a:
            int r15 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x011a
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x010a
        L_0x011a:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
            goto L_0x011f
        L_0x011e:
        L_0x011f:
            int r14 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0141
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x012b:
            int r16 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x013c
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x012b
        L_0x013c:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
            goto L_0x0142
        L_0x0141:
        L_0x0142:
            int r15 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x0166
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x014e:
            int r17 = r15 + 1
            char r15 = r0.charAt(r15)
            if (r15 < r5) goto L_0x0160
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x014e
        L_0x0160:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
            goto L_0x0167
        L_0x0166:
        L_0x0167:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int[] r13 = new int[r13]
            int r16 = r4 + r4
            int r16 = r16 + r6
            r6 = r4
            r4 = r15
            r33 = r12
            r12 = r9
            r9 = r33
        L_0x0178:
            sun.misc.Unsafe r15 = zzb
            java.lang.Object[] r17 = r34.zze()
            com.google.android.gms.internal.firebase-auth-api.zzaaz r18 = r34.zza()
            java.lang.Class r1 = r18.getClass()
            int r7 = r11 * 3
            int[] r7 = new int[r7]
            int r11 = r11 + r11
            java.lang.Object[] r11 = new java.lang.Object[r11]
            int r21 = r14 + r9
            r22 = r14
            r23 = r21
            r9 = 0
            r20 = 0
        L_0x0196:
            if (r4 >= r3) goto L_0x03e9
            int r24 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x01be
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r2 = r24
            r24 = 13
        L_0x01a6:
            int r26 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 < r5) goto L_0x01b8
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r24
            r4 = r4 | r2
            int r24 = r24 + 13
            r2 = r26
            goto L_0x01a6
        L_0x01b8:
            int r2 = r2 << r24
            r4 = r4 | r2
            r2 = r26
            goto L_0x01c0
        L_0x01be:
            r2 = r24
        L_0x01c0:
            int r24 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 < r5) goto L_0x01ed
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r5 = r24
            r24 = 13
        L_0x01ce:
            int r27 = r5 + 1
            char r5 = r0.charAt(r5)
            r28 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r3) goto L_0x01e7
            r3 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r2 = r2 | r3
            int r24 = r24 + 13
            r5 = r27
            r3 = r28
            goto L_0x01ce
        L_0x01e7:
            int r3 = r5 << r24
            r2 = r2 | r3
            r3 = r27
            goto L_0x01f1
        L_0x01ed:
            r28 = r3
            r3 = r24
        L_0x01f1:
            r5 = r2 & 255(0xff, float:3.57E-43)
            r24 = r14
            r14 = r2 & 1024(0x400, float:1.435E-42)
            if (r14 == 0) goto L_0x01ff
            int r14 = r20 + 1
            r13[r20] = r9
            r20 = r14
        L_0x01ff:
            r14 = 51
            if (r5 < r14) goto L_0x02aa
            int r14 = r3 + 1
            char r3 = r0.charAt(r3)
            r27 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r14) goto L_0x0235
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r14 = r27
            r27 = 13
        L_0x0216:
            int r31 = r14 + 1
            char r14 = r0.charAt(r14)
            r32 = r12
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r14 < r12) goto L_0x022f
            r12 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r27
            r3 = r3 | r12
            int r27 = r27 + 13
            r14 = r31
            r12 = r32
            goto L_0x0216
        L_0x022f:
            int r12 = r14 << r27
            r3 = r3 | r12
            r14 = r31
            goto L_0x0239
        L_0x0235:
            r32 = r12
            r14 = r27
        L_0x0239:
            int r12 = r5 + -51
            r27 = r14
            r14 = 9
            if (r12 == r14) goto L_0x025c
            r14 = 17
            if (r12 != r14) goto L_0x0246
            goto L_0x025c
        L_0x0246:
            r14 = 12
            if (r12 != r14) goto L_0x026b
            if (r10 != 0) goto L_0x026b
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
            r16 = r14
            goto L_0x026b
        L_0x025c:
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
            r16 = r14
        L_0x026b:
            int r3 = r3 + r3
            r12 = r17[r3]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0275
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            goto L_0x027d
        L_0x0275:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zzI(r1, r12)
            r17[r3] = r12
        L_0x027d:
            r31 = r7
            r14 = r8
            long r7 = r15.objectFieldOffset(r12)
            int r7 = (int) r7
            int r3 = r3 + 1
            r8 = r17[r3]
            boolean r12 = r8 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x0290
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x0298
        L_0x0290:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzI(r1, r8)
            r17[r3] = r8
        L_0x0298:
            r3 = r7
            long r7 = r15.objectFieldOffset(r8)
            int r7 = (int) r7
            r30 = r0
            r8 = r1
            r0 = r7
            r29 = r11
            r25 = 1
            r7 = r3
            r3 = 0
            goto L_0x03b0
        L_0x02aa:
            r31 = r7
            r14 = r8
            r32 = r12
            int r7 = r16 + 1
            r8 = r17[r16]
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzI(r1, r8)
            r12 = 9
            if (r5 == r12) goto L_0x0329
            r12 = 17
            if (r5 != r12) goto L_0x02c5
            r25 = 1
            goto L_0x032b
        L_0x02c5:
            r12 = 27
            if (r5 == r12) goto L_0x0319
            r12 = 49
            if (r5 != r12) goto L_0x02ce
            goto L_0x0319
        L_0x02ce:
            r12 = 12
            if (r5 == r12) goto L_0x0304
            r12 = 30
            if (r5 == r12) goto L_0x0304
            r12 = 44
            if (r5 != r12) goto L_0x02db
            goto L_0x0304
        L_0x02db:
            r12 = 50
            if (r5 != r12) goto L_0x0303
            int r12 = r22 + 1
            r13[r22] = r9
            int r22 = r9 / 3
            int r22 = r22 + r22
            int r27 = r7 + 1
            r7 = r17[r7]
            r11[r22] = r7
            r7 = r2 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x02fc
            int r7 = r27 + 1
            int r22 = r22 + 1
            r27 = r17[r27]
            r11[r22] = r27
            r22 = r12
            goto L_0x0300
        L_0x02fc:
            r22 = r12
            r7 = r27
        L_0x0300:
            r25 = 1
            goto L_0x0336
        L_0x0303:
            goto L_0x0300
        L_0x0304:
            if (r10 != 0) goto L_0x0318
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
            r7 = r27
        L_0x0315:
            r25 = 1
            goto L_0x0336
        L_0x0318:
            goto L_0x0315
        L_0x0319:
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
            r7 = r27
            goto L_0x0336
        L_0x0329:
            r25 = 1
        L_0x032b:
            int r12 = r9 / 3
            int r12 = r12 + r12
            int r12 = r12 + 1
            java.lang.Class r27 = r8.getType()
            r11[r12] = r27
        L_0x0336:
            r12 = r7
            long r7 = r15.objectFieldOffset(r8)
            int r7 = (int) r7
            r8 = r2 & 4096(0x1000, float:5.74E-42)
            r27 = 1048575(0xfffff, float:1.469367E-39)
            r29 = r11
            r11 = 4096(0x1000, float:5.74E-42)
            if (r8 != r11) goto L_0x0394
            r8 = 17
            if (r5 > r8) goto L_0x0394
            int r8 = r3 + 1
            char r3 = r0.charAt(r3)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r11) goto L_0x0370
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
        L_0x035a:
            int r27 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r11) goto L_0x036c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r26
            r3 = r3 | r8
            int r26 = r26 + 13
            r8 = r27
            goto L_0x035a
        L_0x036c:
            int r8 = r8 << r26
            r3 = r3 | r8
            goto L_0x0372
        L_0x0370:
            r27 = r8
        L_0x0372:
            int r8 = r6 + r6
            int r26 = r3 / 32
            int r8 = r8 + r26
            r11 = r17[r8]
            r30 = r0
            boolean r0 = r11 instanceof java.lang.reflect.Field
            if (r0 == 0) goto L_0x0383
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
            goto L_0x038b
        L_0x0383:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = zzI(r1, r11)
            r17[r8] = r11
        L_0x038b:
            r8 = r1
            long r0 = r15.objectFieldOffset(r11)
            int r0 = (int) r0
            int r3 = r3 % 32
            goto L_0x039c
        L_0x0394:
            r30 = r0
            r8 = r1
            r0 = r27
            r27 = r3
            r3 = 0
        L_0x039c:
            r1 = 18
            if (r5 < r1) goto L_0x03ad
            r1 = 49
            if (r5 > r1) goto L_0x03ad
            int r1 = r23 + 1
            r13[r23] = r7
            r23 = r1
            r16 = r12
            goto L_0x03b0
        L_0x03ad:
            r16 = r12
        L_0x03b0:
            int r1 = r9 + 1
            r31[r9] = r4
            int r4 = r1 + 1
            r9 = r2 & 512(0x200, float:7.175E-43)
            if (r9 == 0) goto L_0x03bd
            r9 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03be
        L_0x03bd:
            r9 = 0
        L_0x03be:
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x03c5
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03c6
        L_0x03c5:
            r2 = 0
        L_0x03c6:
            r2 = r2 | r9
            int r5 = r5 << 20
            r2 = r2 | r5
            r2 = r2 | r7
            r31[r1] = r2
            int r9 = r4 + 1
            int r1 = r3 << 20
            r0 = r0 | r1
            r31[r4] = r0
            r1 = r8
            r8 = r14
            r14 = r24
            r4 = r27
            r3 = r28
            r11 = r29
            r0 = r30
            r7 = r31
            r12 = r32
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0196
        L_0x03e9:
            r31 = r7
            r29 = r11
            r32 = r12
            r24 = r14
            r14 = r8
            com.google.android.gms.internal.firebase-auth-api.zzabc r0 = new com.google.android.gms.internal.firebase-auth-api.zzabc
            r4 = r0
            com.google.android.gms.internal.firebase-auth-api.zzaaz r9 = r34.zza()
            r11 = 0
            r1 = r29
            r20 = 0
            r5 = r31
            r6 = r1
            r7 = r14
            r8 = r32
            r12 = r13
            r13 = r24
            r14 = r21
            r15 = r35
            r16 = r36
            r17 = r37
            r18 = r38
            r19 = r39
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzabc.zzm(com.google.android.gms.internal.firebase-auth-api.zzabj, com.google.android.gms.internal.firebase-auth-api.zzabe, com.google.android.gms.internal.firebase-auth-api.zzaan, com.google.android.gms.internal.firebase-auth-api.zzabz, com.google.android.gms.internal.firebase-auth-api.zzzk, com.google.android.gms.internal.firebase-auth-api.zzaau):com.google.android.gms.internal.firebase-auth-api.zzabc");
    }

    private static <T> double zzo(T t, long j) {
        return ((Double) zzacj.zzf(t, j)).doubleValue();
    }

    private static <T> float zzp(T t, long j) {
        return ((Float) zzacj.zzf(t, j)).floatValue();
    }

    private final int zzq(T t) {
        int i;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzc.length; i5 += 3) {
            int zzC = zzC(i5);
            int i6 = this.zzc[i5];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
                int i7 = this.zzc[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i2) {
                    i4 = unsafe.getInt(t, (long) i8);
                    i2 = i8;
                }
            } else {
                i = 0;
            }
            long j = (long) (zzC & 1048575);
            switch (zzB) {
                case 0:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 8;
                        break;
                    }
                case 1:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 4;
                        break;
                    }
                case 2:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzF(unsafe.getLong(t, j));
                        break;
                    }
                case 3:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzF(unsafe.getLong(t, j));
                        break;
                    }
                case 4:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzy(unsafe.getInt(t, j));
                        break;
                    }
                case 5:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 8;
                        break;
                    }
                case 6:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 4;
                        break;
                    }
                case 7:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 1;
                        break;
                    }
                case 8:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(t, j);
                        if (!(object instanceof zzyu)) {
                            i3 += zzze.zzE(i6 << 3) + zzze.zzC((String) object);
                            break;
                        } else {
                            int zzE = zzze.zzE(i6 << 3);
                            int zzd2 = ((zzyu) object).zzd();
                            i3 += zzE + zzze.zzE(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabn.zzo(i6, unsafe.getObject(t, j), zzF(i5));
                        break;
                    }
                case 10:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        int zzE2 = zzze.zzE(i6 << 3);
                        int zzd3 = ((zzyu) unsafe.getObject(t, j)).zzd();
                        i3 += zzE2 + zzze.zzE(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzE(unsafe.getInt(t, j));
                        break;
                    }
                case 12:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzy(unsafe.getInt(t, j));
                        break;
                    }
                case 13:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 4;
                        break;
                    }
                case 14:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 8;
                        break;
                    }
                case 15:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        int i9 = unsafe.getInt(t, j);
                        i3 += zzze.zzE(i6 << 3) + zzze.zzE((i9 >> 31) ^ (i9 + i9));
                        break;
                    }
                case 16:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(t, j);
                        i3 += zzze.zzE(i6 << 3) + zzze.zzF((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzze.zzx(i6, (zzaaz) unsafe.getObject(t, j), zzF(i5));
                        break;
                    }
                case 18:
                    i3 += zzabn.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 19:
                    i3 += zzabn.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 20:
                    i3 += zzabn.zzm(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 21:
                    i3 += zzabn.zzx(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 22:
                    i3 += zzabn.zzk(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 23:
                    i3 += zzabn.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 24:
                    i3 += zzabn.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 25:
                    i3 += zzabn.zza(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 26:
                    i3 += zzabn.zzu(i6, (List) unsafe.getObject(t, j));
                    break;
                case 27:
                    i3 += zzabn.zzp(i6, (List) unsafe.getObject(t, j), zzF(i5));
                    break;
                case 28:
                    i3 += zzabn.zzc(i6, (List) unsafe.getObject(t, j));
                    break;
                case 29:
                    i3 += zzabn.zzv(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 30:
                    i3 += zzabn.zzd(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 31:
                    i3 += zzabn.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 32:
                    i3 += zzabn.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 33:
                    i3 += zzabn.zzq(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 34:
                    i3 += zzabn.zzs(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 35:
                    int zzi2 = zzabn.zzi((List) unsafe.getObject(t, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzabn.zzg((List) unsafe.getObject(t, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzabn.zzn((List) unsafe.getObject(t, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzabn.zzy((List) unsafe.getObject(t, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzabn.zzl((List) unsafe.getObject(t, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzabn.zzi((List) unsafe.getObject(t, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzabn.zzg((List) unsafe.getObject(t, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzabn.zzb((List) unsafe.getObject(t, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzabn.zzw((List) unsafe.getObject(t, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzabn.zze((List) unsafe.getObject(t, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzabn.zzg((List) unsafe.getObject(t, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzabn.zzi((List) unsafe.getObject(t, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr2 = zzabn.zzr((List) unsafe.getObject(t, j));
                    if (zzr2 <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzr2) + zzr2;
                        break;
                    }
                case 48:
                    int zzt = zzabn.zzt((List) unsafe.getObject(t, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i3 += zzze.zzD(i6) + zzze.zzE(zzt) + zzt;
                        break;
                    }
                case 49:
                    i3 += zzabn.zzj(i6, (List) unsafe.getObject(t, j), zzF(i5));
                    break;
                case 50:
                    zzaau.zza(i6, unsafe.getObject(t, j), zzH(i5));
                    break;
                case 51:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzF(zzD(t, j));
                        break;
                    }
                case 54:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzF(zzD(t, j));
                        break;
                    }
                case 55:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzy(zzs(t, j));
                        break;
                    }
                case 56:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(t, j);
                        if (!(object2 instanceof zzyu)) {
                            i3 += zzze.zzE(i6 << 3) + zzze.zzC((String) object2);
                            break;
                        } else {
                            int zzE3 = zzze.zzE(i6 << 3);
                            int zzd4 = ((zzyu) object2).zzd();
                            i3 += zzE3 + zzze.zzE(zzd4) + zzd4;
                            break;
                        }
                    }
                case 60:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabn.zzo(i6, unsafe.getObject(t, j), zzF(i5));
                        break;
                    }
                case 61:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        int zzE4 = zzze.zzE(i6 << 3);
                        int zzd5 = ((zzyu) unsafe.getObject(t, j)).zzd();
                        i3 += zzE4 + zzze.zzE(zzd5) + zzd5;
                        break;
                    }
                case 62:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzE(zzs(t, j));
                        break;
                    }
                case 63:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + zzze.zzy(zzs(t, j));
                        break;
                    }
                case 64:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzE(i6 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        int zzs = zzs(t, j);
                        i3 += zzze.zzE(i6 << 3) + zzze.zzE((zzs >> 31) ^ (zzs + zzs));
                        break;
                    }
                case 67:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        long zzD = zzD(t, j);
                        i3 += zzze.zzE(i6 << 3) + zzze.zzF((zzD >> 63) ^ (zzD + zzD));
                        break;
                    }
                case 68:
                    if (!zzT(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzze.zzx(i6, (zzaaz) unsafe.getObject(t, j), zzF(i5));
                        break;
                    }
            }
        }
        zzabz<?, ?> zzabz = this.zzo;
        int zza2 = i3 + zzabz.zza(zzabz.zzd(t));
        if (!this.zzh) {
            return zza2;
        }
        this.zzp.zza(t);
        throw null;
    }

    private final int zzr(T t) {
        Unsafe unsafe = zzb;
        int i = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzC = zzC(i2);
            int zzB = zzB(zzC);
            int i3 = this.zzc[i2];
            long j = (long) (zzC & 1048575);
            if (zzB >= zzzp.DOUBLE_LIST_PACKED.zza() && zzB <= zzzp.SINT64_LIST_PACKED.zza()) {
                int i4 = this.zzc[i2 + 2];
            }
            switch (zzB) {
                case 0:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 8;
                        break;
                    }
                case 1:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 4;
                        break;
                    }
                case 2:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzF(zzacj.zzd(t, j));
                        break;
                    }
                case 3:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzF(zzacj.zzd(t, j));
                        break;
                    }
                case 4:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzy(zzacj.zzc(t, j));
                        break;
                    }
                case 5:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 8;
                        break;
                    }
                case 6:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 4;
                        break;
                    }
                case 7:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 1;
                        break;
                    }
                case 8:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        Object zzf2 = zzacj.zzf(t, j);
                        if (!(zzf2 instanceof zzyu)) {
                            i += zzze.zzE(i3 << 3) + zzze.zzC((String) zzf2);
                            break;
                        } else {
                            int zzE = zzze.zzE(i3 << 3);
                            int zzd2 = ((zzyu) zzf2).zzd();
                            i += zzE + zzze.zzE(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzabn.zzo(i3, zzacj.zzf(t, j), zzF(i2));
                        break;
                    }
                case 10:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        int zzE2 = zzze.zzE(i3 << 3);
                        int zzd3 = ((zzyu) zzacj.zzf(t, j)).zzd();
                        i += zzE2 + zzze.zzE(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzE(zzacj.zzc(t, j));
                        break;
                    }
                case 12:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzy(zzacj.zzc(t, j));
                        break;
                    }
                case 13:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 4;
                        break;
                    }
                case 14:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 8;
                        break;
                    }
                case 15:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        int zzc2 = zzacj.zzc(t, j);
                        i += zzze.zzE(i3 << 3) + zzze.zzE((zzc2 >> 31) ^ (zzc2 + zzc2));
                        break;
                    }
                case 16:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        long zzd4 = zzacj.zzd(t, j);
                        i += zzze.zzE(i3 << 3) + zzze.zzF((zzd4 >> 63) ^ (zzd4 + zzd4));
                        break;
                    }
                case 17:
                    if (!zzQ(t, i2)) {
                        break;
                    } else {
                        i += zzze.zzx(i3, (zzaaz) zzacj.zzf(t, j), zzF(i2));
                        break;
                    }
                case 18:
                    i += zzabn.zzh(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 19:
                    i += zzabn.zzf(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 20:
                    i += zzabn.zzm(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 21:
                    i += zzabn.zzx(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 22:
                    i += zzabn.zzk(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 23:
                    i += zzabn.zzh(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 24:
                    i += zzabn.zzf(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 25:
                    i += zzabn.zza(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 26:
                    i += zzabn.zzu(i3, (List) zzacj.zzf(t, j));
                    break;
                case 27:
                    i += zzabn.zzp(i3, (List) zzacj.zzf(t, j), zzF(i2));
                    break;
                case 28:
                    i += zzabn.zzc(i3, (List) zzacj.zzf(t, j));
                    break;
                case 29:
                    i += zzabn.zzv(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 30:
                    i += zzabn.zzd(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 31:
                    i += zzabn.zzf(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 32:
                    i += zzabn.zzh(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 33:
                    i += zzabn.zzq(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 34:
                    i += zzabn.zzs(i3, (List) zzacj.zzf(t, j), false);
                    break;
                case 35:
                    int zzi2 = zzabn.zzi((List) unsafe.getObject(t, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzabn.zzg((List) unsafe.getObject(t, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzabn.zzn((List) unsafe.getObject(t, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzabn.zzy((List) unsafe.getObject(t, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzabn.zzl((List) unsafe.getObject(t, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzabn.zzi((List) unsafe.getObject(t, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzabn.zzg((List) unsafe.getObject(t, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzabn.zzb((List) unsafe.getObject(t, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzabn.zzw((List) unsafe.getObject(t, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzabn.zze((List) unsafe.getObject(t, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzabn.zzg((List) unsafe.getObject(t, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzabn.zzi((List) unsafe.getObject(t, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr2 = zzabn.zzr((List) unsafe.getObject(t, j));
                    if (zzr2 <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzr2) + zzr2;
                        break;
                    }
                case 48:
                    int zzt = zzabn.zzt((List) unsafe.getObject(t, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i += zzze.zzD(i3) + zzze.zzE(zzt) + zzt;
                        break;
                    }
                case 49:
                    i += zzabn.zzj(i3, (List) zzacj.zzf(t, j), zzF(i2));
                    break;
                case 50:
                    zzaau.zza(i3, zzacj.zzf(t, j), zzH(i2));
                    break;
                case 51:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzF(zzD(t, j));
                        break;
                    }
                case 54:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzF(zzD(t, j));
                        break;
                    }
                case 55:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzy(zzs(t, j));
                        break;
                    }
                case 56:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        Object zzf3 = zzacj.zzf(t, j);
                        if (!(zzf3 instanceof zzyu)) {
                            i += zzze.zzE(i3 << 3) + zzze.zzC((String) zzf3);
                            break;
                        } else {
                            int zzE3 = zzze.zzE(i3 << 3);
                            int zzd5 = ((zzyu) zzf3).zzd();
                            i += zzE3 + zzze.zzE(zzd5) + zzd5;
                            break;
                        }
                    }
                case 60:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabn.zzo(i3, zzacj.zzf(t, j), zzF(i2));
                        break;
                    }
                case 61:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        int zzE4 = zzze.zzE(i3 << 3);
                        int zzd6 = ((zzyu) zzacj.zzf(t, j)).zzd();
                        i += zzE4 + zzze.zzE(zzd6) + zzd6;
                        break;
                    }
                case 62:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzE(zzs(t, j));
                        break;
                    }
                case 63:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + zzze.zzy(zzs(t, j));
                        break;
                    }
                case 64:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzE(i3 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        int zzs = zzs(t, j);
                        i += zzze.zzE(i3 << 3) + zzze.zzE((zzs >> 31) ^ (zzs + zzs));
                        break;
                    }
                case 67:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        long zzD = zzD(t, j);
                        i += zzze.zzE(i3 << 3) + zzze.zzF((zzD >> 63) ^ (zzD + zzD));
                        break;
                    }
                case 68:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i += zzze.zzx(i3, (zzaaz) zzacj.zzf(t, j), zzF(i2));
                        break;
                    }
            }
        }
        zzabz<?, ?> zzabz = this.zzo;
        return i + zzabz.zza(zzabz.zzd(t));
    }

    private static <T> int zzs(T t, long j) {
        return ((Integer) zzacj.zzf(t, j)).intValue();
    }

    private final <K, V> int zzt(T t, byte[] bArr, int i, int i2, int i3, long j, zzyh zzyh) throws IOException {
        Unsafe unsafe = zzb;
        Object zzH = zzH(i3);
        Object object = unsafe.getObject(t, j);
        if (zzaau.zzb(object)) {
            zzaat zzb2 = zzaat.zza().zzb();
            zzaau.zzc(zzb2, object);
            unsafe.putObject(t, j, zzb2);
        }
        zzaas zzaas = (zzaas) zzH;
        throw null;
    }

    private final int zzu(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzyh zzyh) throws IOException {
        boolean z;
        Object obj;
        Object obj2;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        zzyh zzyh2 = zzyh;
        Unsafe unsafe = zzb;
        long j3 = (long) (this.zzc[i13 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Double.valueOf(Double.longBitsToDouble(zzyi.zzn(bArr, i))));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Float.valueOf(Float.intBitsToFloat(zzyi.zzb(bArr, i))));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int zzm2 = zzyi.zzm(bArr2, i9, zzyh2);
                    unsafe.putObject(t2, j2, Long.valueOf(zzyh2.zzb));
                    unsafe.putInt(t2, j3, i11);
                    return zzm2;
                }
                break;
            case 55:
            case 62:
                if (i12 == 0) {
                    int zzj2 = zzyi.zzj(bArr2, i9, zzyh2);
                    unsafe.putObject(t2, j2, Integer.valueOf(zzyh2.zza));
                    unsafe.putInt(t2, j3, i11);
                    return zzj2;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Long.valueOf(zzyi.zzn(bArr, i)));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Integer.valueOf(zzyi.zzb(bArr, i)));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 58:
                if (i12 == 0) {
                    int zzm3 = zzyi.zzm(bArr2, i9, zzyh2);
                    if (zzyh2.zzb != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    unsafe.putObject(t2, j2, Boolean.valueOf(z));
                    unsafe.putInt(t2, j3, i11);
                    return zzm3;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int zzj3 = zzyi.zzj(bArr2, i9, zzyh2);
                    int i14 = zzyh2.zza;
                    if (i14 == 0) {
                        unsafe.putObject(t2, j2, "");
                    } else if ((i6 & 536870912) == 0 || zzaco.zzf(bArr2, zzj3, zzj3 + i14)) {
                        unsafe.putObject(t2, j2, new String(bArr2, zzj3, i14, zzaac.zza));
                        zzj3 += i14;
                    } else {
                        throw zzaae.zzd();
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzj3;
                }
                break;
            case 60:
                if (i12 == 2) {
                    int zzd2 = zzyi.zzd(zzF(i13), bArr2, i9, i2, zzyh2);
                    if (unsafe.getInt(t2, j3) == i11) {
                        obj = unsafe.getObject(t2, j2);
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        unsafe.putObject(t2, j2, zzyh2.zzc);
                    } else {
                        unsafe.putObject(t2, j2, zzaac.zzg(obj, zzyh2.zzc));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzd2;
                }
                break;
            case 61:
                if (i12 == 2) {
                    int zza2 = zzyi.zza(bArr2, i9, zzyh2);
                    unsafe.putObject(t2, j2, zzyh2.zzc);
                    unsafe.putInt(t2, j3, i11);
                    return zza2;
                }
                break;
            case 63:
                if (i12 == 0) {
                    int zzj4 = zzyi.zzj(bArr2, i9, zzyh2);
                    int i15 = zzyh2.zza;
                    zzaaa zzE = zzE(i13);
                    if (zzE == null || zzE.zza()) {
                        unsafe.putObject(t2, j2, Integer.valueOf(i15));
                        unsafe.putInt(t2, j3, i11);
                    } else {
                        zzd(t).zzh(i10, Long.valueOf((long) i15));
                    }
                    return zzj4;
                }
                break;
            case 66:
                if (i12 == 0) {
                    int zzj5 = zzyi.zzj(bArr2, i9, zzyh2);
                    unsafe.putObject(t2, j2, Integer.valueOf(zzyx.zzs(zzyh2.zza)));
                    unsafe.putInt(t2, j3, i11);
                    return zzj5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int zzm4 = zzyi.zzm(bArr2, i9, zzyh2);
                    unsafe.putObject(t2, j2, Long.valueOf(zzyx.zzt(zzyh2.zzb)));
                    unsafe.putInt(t2, j3, i11);
                    return zzm4;
                }
                break;
            case 68:
                if (i12 == 3) {
                    int zzc2 = zzyi.zzc(zzF(i13), bArr, i, i2, (i10 & -8) | 4, zzyh);
                    if (unsafe.getInt(t2, j3) == i11) {
                        obj2 = unsafe.getObject(t2, j2);
                    } else {
                        obj2 = null;
                    }
                    if (obj2 == null) {
                        unsafe.putObject(t2, j2, zzyh2.zzc);
                    } else {
                        unsafe.putObject(t2, j2, zzaac.zzg(obj2, zzyh2.zzc));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzc2;
                }
                break;
        }
        return i9;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzv(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.p010firebaseauthapi.zzyh r35) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            sun.misc.Unsafe r9 = zzb
            r10 = -1
            r16 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r33
            r7 = r8
            r1 = r10
            r2 = r16
            r6 = r2
        L_0x0019:
            if (r0 >= r13) goto L_0x0439
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002b
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzk(r0, r12, r3, r11)
            int r3 = r11.zza
            r4 = r0
            r17 = r3
            goto L_0x002e
        L_0x002b:
            r17 = r0
            r4 = r3
        L_0x002e:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x003c
            int r2 = r2 / 3
            int r0 = r15.zzy(r5, r2)
            r2 = r0
            goto L_0x0041
        L_0x003c:
            int r0 = r15.zzx(r5)
            r2 = r0
        L_0x0041:
            if (r2 != r10) goto L_0x004e
            r2 = r4
            r20 = r5
            r28 = r9
            r18 = r10
            r19 = r16
            goto L_0x0412
        L_0x004e:
            int[] r0 = r15.zzc
            int r1 = r2 + 1
            r1 = r0[r1]
            int r0 = zzB(r1)
            r10 = r1 & r8
            r19 = r9
            long r8 = (long) r10
            r10 = 17
            r33 = r5
            if (r0 > r10) goto L_0x02c1
            int[] r10 = r15.zzc
            int r21 = r2 + 2
            r10 = r10[r21]
            int r21 = r10 >>> 20
            r5 = 1
            int r21 = r5 << r21
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r10 & r13
            if (r10 == r7) goto L_0x0096
            if (r7 == r13) goto L_0x0081
            r23 = r1
            r20 = r2
            long r1 = (long) r7
            r7 = r19
            r7.putInt(r14, r1, r6)
            goto L_0x0087
        L_0x0081:
            r23 = r1
            r20 = r2
            r7 = r19
        L_0x0087:
            if (r10 == r13) goto L_0x008f
            long r1 = (long) r10
            int r6 = r7.getInt(r14, r1)
            goto L_0x0090
        L_0x008f:
        L_0x0090:
            r29 = r10
            r10 = r7
            r7 = r29
            goto L_0x009c
        L_0x0096:
            r23 = r1
            r20 = r2
            r10 = r19
        L_0x009c:
            r1 = 5
            switch(r0) {
                case 0: goto L_0x0295;
                case 1: goto L_0x0272;
                case 2: goto L_0x024d;
                case 3: goto L_0x024d;
                case 4: goto L_0x022d;
                case 5: goto L_0x0203;
                case 6: goto L_0x01df;
                case 7: goto L_0x01b2;
                case 8: goto L_0x0182;
                case 9: goto L_0x0143;
                case 10: goto L_0x0120;
                case 11: goto L_0x022d;
                case 12: goto L_0x00fe;
                case 13: goto L_0x01df;
                case 14: goto L_0x0203;
                case 15: goto L_0x00d8;
                case 16: goto L_0x00a8;
                default: goto L_0x00a0;
            }
        L_0x00a0:
            r19 = r13
            r13 = r20
            r20 = r33
            goto L_0x02b8
        L_0x00a8:
            if (r3 != 0) goto L_0x00cf
            int r17 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzm(r12, r4, r11)
            long r0 = r11.zzb
            long r4 = com.google.android.gms.internal.p010firebaseauthapi.zzyx.zzt(r0)
            r0 = r10
            r1 = r31
            r13 = r20
            r2 = r8
            r20 = r33
            r0.putLong(r1, r2, r4)
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r0 = r17
            r1 = r20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x00cf:
            r13 = r20
            r20 = r33
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x02b8
        L_0x00d8:
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x00f9
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzj(r12, r4, r11)
            int r1 = r11.zza
            int r1 = com.google.android.gms.internal.p010firebaseauthapi.zzyx.zzs(r1)
            r10.putInt(r14, r8, r1)
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r1 = r20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x00f9:
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x02b8
        L_0x00fe:
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x011b
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzj(r12, r4, r11)
            int r1 = r11.zza
            r10.putInt(r14, r8, r1)
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r1 = r20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x011b:
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x02b8
        L_0x0120:
            r13 = r20
            r20 = r33
            r0 = 2
            if (r3 != r0) goto L_0x013e
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zza(r12, r4, r11)
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r1 = r20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x013e:
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x02b8
        L_0x0143:
            r13 = r20
            r0 = 2
            r20 = r33
            if (r3 != r0) goto L_0x017b
            com.google.android.gms.internal.firebase-auth-api.zzabl r0 = r15.zzF(r13)
            r2 = r34
            r19 = 1048575(0xfffff, float:1.469367E-39)
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzd(r0, r12, r4, r2, r11)
            java.lang.Object r1 = r10.getObject(r14, r8)
            if (r1 != 0) goto L_0x0163
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x016c
        L_0x0163:
            java.lang.Object r3 = r11.zzc
            java.lang.Object r1 = com.google.android.gms.internal.p010firebaseauthapi.zzaac.zzg(r1, r3)
            r10.putObject(r14, r8, r1)
        L_0x016c:
            r6 = r6 | r21
            r9 = r10
            r8 = r19
            r1 = r20
            r10 = -1
            r29 = r13
            r13 = r2
            r2 = r29
            goto L_0x0019
        L_0x017b:
            r2 = r34
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x02b8
        L_0x0182:
            r2 = r34
            r19 = r13
            r13 = r20
            r20 = r33
            r0 = 2
            if (r3 != r0) goto L_0x01b0
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r23 & r0
            if (r0 != 0) goto L_0x0198
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzg(r12, r4, r11)
            goto L_0x019c
        L_0x0198:
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzh(r12, r4, r11)
        L_0x019c:
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            r6 = r6 | r21
            r9 = r10
            r8 = r19
            r1 = r20
            r10 = -1
            r29 = r13
            r13 = r2
            r2 = r29
            goto L_0x0019
        L_0x01b0:
            goto L_0x02b8
        L_0x01b2:
            r2 = r34
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x01dd
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzm(r12, r4, r11)
            long r3 = r11.zzb
            r22 = 0
            int r1 = (r3 > r22 ? 1 : (r3 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x01c9
            goto L_0x01cb
        L_0x01c9:
            r5 = r16
        L_0x01cb:
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzm(r14, r8, r5)
            r6 = r6 | r21
            r9 = r10
            r8 = r19
            r1 = r20
            r10 = -1
            r29 = r13
            r13 = r2
            r2 = r29
            goto L_0x0019
        L_0x01dd:
            goto L_0x02b8
        L_0x01df:
            r2 = r34
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != r1) goto L_0x0201
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzb(r12, r4)
            r10.putInt(r14, r8, r0)
            int r0 = r4 + 4
            r6 = r6 | r21
            r9 = r10
            r8 = r19
            r1 = r20
            r10 = -1
            r29 = r13
            r13 = r2
            r2 = r29
            goto L_0x0019
        L_0x0201:
            goto L_0x02b8
        L_0x0203:
            r2 = r34
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != r5) goto L_0x022a
            long r22 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzn(r12, r4)
            r0 = r10
            r1 = r31
            r2 = r8
            r8 = r4
            r4 = r22
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r8 = r19
            r1 = r20
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x022a:
            r8 = r4
            goto L_0x02b8
        L_0x022d:
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x024b
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzj(r12, r4, r11)
            int r1 = r11.zza
            r10.putInt(r14, r8, r1)
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r8 = r19
            r1 = r20
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x024b:
            goto L_0x02b8
        L_0x024d:
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != 0) goto L_0x0271
            int r17 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzm(r12, r4, r11)
            long r4 = r11.zzb
            r0 = r10
            r1 = r31
            r2 = r8
            r0.putLong(r1, r2, r4)
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r0 = r17
            r8 = r19
            r1 = r20
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x0271:
            goto L_0x02b8
        L_0x0272:
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != r1) goto L_0x0294
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzb(r12, r4)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzp(r14, r8, r0)
            int r0 = r4 + 4
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r8 = r19
            r1 = r20
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x0294:
            goto L_0x02b8
        L_0x0295:
            r19 = r13
            r13 = r20
            r20 = r33
            if (r3 != r5) goto L_0x02b7
            long r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzn(r12, r4)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzo(r14, r8, r0)
            int r0 = r4 + 8
            r6 = r6 | r21
            r9 = r10
            r2 = r13
            r8 = r19
            r1 = r20
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x02b7:
        L_0x02b8:
            r2 = r4
            r28 = r10
            r19 = r13
            r18 = -1
            goto L_0x0412
        L_0x02c1:
            r20 = r33
            r23 = r1
            r13 = r2
            r10 = r19
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r1 = 27
            if (r0 != r1) goto L_0x031e
            r0 = 2
            if (r3 != r0) goto L_0x0310
            java.lang.Object r0 = r10.getObject(r14, r8)
            com.google.android.gms.internal.firebase-auth-api.zzaab r0 = (com.google.android.gms.internal.p010firebaseauthapi.zzaab) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x02f1
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02e7
            r1 = 10
            goto L_0x02e8
        L_0x02e7:
            int r1 = r1 + r1
        L_0x02e8:
            com.google.android.gms.internal.firebase-auth-api.zzaab r0 = r0.zzd(r1)
            r10.putObject(r14, r8, r0)
            r5 = r0
            goto L_0x02f2
        L_0x02f1:
            r5 = r0
        L_0x02f2:
            com.google.android.gms.internal.firebase-auth-api.zzabl r0 = r15.zzF(r13)
            r1 = r17
            r2 = r32
            r3 = r4
            r4 = r34
            r8 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zze(r0, r1, r2, r3, r4, r5, r6)
            r6 = r8
            r9 = r10
            r2 = r13
            r8 = r19
            r1 = r20
            r10 = -1
            r13 = r34
            goto L_0x0019
        L_0x0310:
            r8 = r6
            r15 = r4
            r25 = r7
            r24 = r8
            r28 = r10
            r19 = r13
            r18 = -1
            goto L_0x03d0
        L_0x031e:
            r1 = 49
            if (r0 > r1) goto L_0x037b
            r1 = r23
            long r1 = (long) r1
            r5 = r0
            r0 = r30
            r21 = r1
            r1 = r31
            r2 = r32
            r33 = r3
            r3 = r4
            r15 = r4
            r4 = r34
            r23 = r5
            r5 = r17
            r24 = r6
            r6 = r20
            r25 = r7
            r7 = r33
            r26 = r8
            r9 = r19
            r8 = r13
            r28 = r10
            r18 = -1
            r9 = r21
            r11 = r23
            r19 = r13
            r12 = r26
            r14 = r35
            int r0 = r0.zzw(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x0374
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r10 = r18
            r2 = r19
            r1 = r20
            r6 = r24
            r7 = r25
            r9 = r28
            r8 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0019
        L_0x0374:
            r2 = r0
            r6 = r24
            r7 = r25
            goto L_0x0412
        L_0x037b:
            r33 = r3
            r15 = r4
            r24 = r6
            r25 = r7
            r26 = r8
            r28 = r10
            r19 = r13
            r1 = r23
            r18 = -1
            r23 = r0
            r0 = 50
            r9 = r23
            if (r9 != r0) goto L_0x03d6
            r7 = r33
            r0 = 2
            if (r7 != r0) goto L_0x03cf
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r19
            r6 = r26
            r8 = r35
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x03c9
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r10 = r18
            r2 = r19
            r1 = r20
            r6 = r24
            r7 = r25
            r9 = r28
            r8 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0019
        L_0x03c9:
            r2 = r0
            r6 = r24
            r7 = r25
            goto L_0x0412
        L_0x03cf:
        L_0x03d0:
            r2 = r15
            r6 = r24
            r7 = r25
            goto L_0x0412
        L_0x03d6:
            r7 = r33
            r0 = r30
            r8 = r1
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r17
            r6 = r20
            r10 = r26
            r12 = r19
            r13 = r35
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x040d
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r10 = r18
            r2 = r19
            r1 = r20
            r6 = r24
            r7 = r25
            r9 = r28
            r8 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0019
        L_0x040d:
            r2 = r0
            r6 = r24
            r7 = r25
        L_0x0412:
            com.google.android.gms.internal.firebase-auth-api.zzaca r4 = zzd(r31)
            r0 = r17
            r1 = r32
            r3 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.p010firebaseauthapi.zzyi.zzi(r0, r1, r2, r3, r4, r5)
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r10 = r18
            r2 = r19
            r1 = r20
            r9 = r28
            r8 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0019
        L_0x0439:
            r24 = r6
            r25 = r7
            r28 = r9
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 == r1) goto L_0x044e
            long r1 = (long) r7
            r3 = r31
            r6 = r24
            r4 = r28
            r4.putInt(r3, r1, r6)
        L_0x044e:
            r1 = r34
            if (r0 != r1) goto L_0x0453
            return r0
        L_0x0453:
            com.google.android.gms.internal.firebase-auth-api.zzaae r0 = com.google.android.gms.internal.p010firebaseauthapi.zzaae.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzabc.zzv(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzyh):int");
    }

    private final int zzw(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzyh zzyh) throws IOException {
        boolean z;
        boolean z2;
        boolean z3;
        int i8;
        int i9;
        int i10;
        int i11;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i12 = i;
        int i13 = i2;
        int i14 = i3;
        int i15 = i5;
        int i16 = i6;
        long j3 = j2;
        zzyh zzyh2 = zzyh;
        Unsafe unsafe = zzb;
        zzaab zzaab = (zzaab) unsafe.getObject(t2, j3);
        if (!zzaab.zzc()) {
            int size = zzaab.size();
            zzaab = zzaab.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(t2, j3, zzaab);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    zzzg zzzg = (zzzg) zzaab;
                    int zzj2 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i17 = zzyh2.zza + zzj2;
                    while (zzj2 < i17) {
                        zzzg.zze(Double.longBitsToDouble(zzyi.zzn(bArr2, zzj2)));
                        zzj2 += 8;
                    }
                    if (zzj2 == i17) {
                        return zzj2;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 1) {
                    zzzg zzzg2 = (zzzg) zzaab;
                    zzzg2.zze(Double.longBitsToDouble(zzyi.zzn(bArr, i)));
                    int i18 = i12 + 8;
                    while (i18 < i13) {
                        int zzj3 = zzyi.zzj(bArr2, i18, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return i18;
                        }
                        zzzg2.zze(Double.longBitsToDouble(zzyi.zzn(bArr2, zzj3)));
                        i18 = zzj3 + 8;
                    }
                    return i18;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    zzzq zzzq = (zzzq) zzaab;
                    int zzj4 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i19 = zzyh2.zza + zzj4;
                    while (zzj4 < i19) {
                        zzzq.zze(Float.intBitsToFloat(zzyi.zzb(bArr2, zzj4)));
                        zzj4 += 4;
                    }
                    if (zzj4 == i19) {
                        return zzj4;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 5) {
                    zzzq zzzq2 = (zzzq) zzaab;
                    zzzq2.zze(Float.intBitsToFloat(zzyi.zzb(bArr, i)));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zzj5 = zzyi.zzj(bArr2, i20, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return i20;
                        }
                        zzzq2.zze(Float.intBitsToFloat(zzyi.zzb(bArr2, zzj5)));
                        i20 = zzj5 + 4;
                    }
                    return i20;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i15 == 2) {
                    zzaao zzaao = (zzaao) zzaab;
                    int zzj6 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i21 = zzyh2.zza + zzj6;
                    while (zzj6 < i21) {
                        zzj6 = zzyi.zzm(bArr2, zzj6, zzyh2);
                        zzaao.zzf(zzyh2.zzb);
                    }
                    if (zzj6 == i21) {
                        return zzj6;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 0) {
                    zzaao zzaao2 = (zzaao) zzaab;
                    int zzm2 = zzyi.zzm(bArr2, i12, zzyh2);
                    zzaao2.zzf(zzyh2.zzb);
                    while (zzm2 < i13) {
                        int zzj7 = zzyi.zzj(bArr2, zzm2, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return zzm2;
                        }
                        zzm2 = zzyi.zzm(bArr2, zzj7, zzyh2);
                        zzaao2.zzf(zzyh2.zzb);
                    }
                    return zzm2;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzyi.zzf(bArr2, i12, zzaab, zzyh2);
                }
                if (i15 == 0) {
                    return zzyi.zzl(i3, bArr, i, i2, zzaab, zzyh);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    zzaao zzaao3 = (zzaao) zzaab;
                    int zzj8 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i22 = zzyh2.zza + zzj8;
                    while (zzj8 < i22) {
                        zzaao3.zzf(zzyi.zzn(bArr2, zzj8));
                        zzj8 += 8;
                    }
                    if (zzj8 == i22) {
                        return zzj8;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 1) {
                    zzaao zzaao4 = (zzaao) zzaab;
                    zzaao4.zzf(zzyi.zzn(bArr, i));
                    int i23 = i12 + 8;
                    while (i23 < i13) {
                        int zzj9 = zzyi.zzj(bArr2, i23, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return i23;
                        }
                        zzaao4.zzf(zzyi.zzn(bArr2, zzj9));
                        i23 = zzj9 + 8;
                    }
                    return i23;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i15 == 2) {
                    zzzx zzzx = (zzzx) zzaab;
                    int zzj10 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i24 = zzyh2.zza + zzj10;
                    while (zzj10 < i24) {
                        zzzx.zzf(zzyi.zzb(bArr2, zzj10));
                        zzj10 += 4;
                    }
                    if (zzj10 == i24) {
                        return zzj10;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 5) {
                    zzzx zzzx2 = (zzzx) zzaab;
                    zzzx2.zzf(zzyi.zzb(bArr, i));
                    int i25 = i12 + 4;
                    while (i25 < i13) {
                        int zzj11 = zzyi.zzj(bArr2, i25, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return i25;
                        }
                        zzzx2.zzf(zzyi.zzb(bArr2, zzj11));
                        i25 = zzj11 + 4;
                    }
                    return i25;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    zzyj zzyj = (zzyj) zzaab;
                    int zzj12 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i26 = zzyh2.zza + zzj12;
                    while (zzj12 < i26) {
                        zzj12 = zzyi.zzm(bArr2, zzj12, zzyh2);
                        if (zzyh2.zzb != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        zzyj.zze(z3);
                    }
                    if (zzj12 == i26) {
                        return zzj12;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 0) {
                    zzyj zzyj2 = (zzyj) zzaab;
                    int zzm3 = zzyi.zzm(bArr2, i12, zzyh2);
                    if (zzyh2.zzb != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zzyj2.zze(z);
                    while (zzm3 < i13) {
                        int zzj13 = zzyi.zzj(bArr2, zzm3, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return zzm3;
                        }
                        zzm3 = zzyi.zzm(bArr2, zzj13, zzyh2);
                        if (zzyh2.zzb != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zzyj2.zze(z2);
                    }
                    return zzm3;
                }
                break;
            case 26:
                if (i15 == 2) {
                    if ((j & 536870912) == 0) {
                        int zzj14 = zzyi.zzj(bArr2, i12, zzyh2);
                        int i27 = zzyh2.zza;
                        if (i27 >= 0) {
                            if (i27 == 0) {
                                zzaab.add("");
                            } else {
                                zzaab.add(new String(bArr2, zzj14, i27, zzaac.zza));
                                zzj14 += i27;
                            }
                            while (i9 < i13) {
                                int zzj15 = zzyi.zzj(bArr2, i9, zzyh2);
                                if (i14 != zzyh2.zza) {
                                    return i9;
                                }
                                i9 = zzyi.zzj(bArr2, zzj15, zzyh2);
                                int i28 = zzyh2.zza;
                                if (i28 < 0) {
                                    throw zzaae.zzf();
                                } else if (i28 == 0) {
                                    zzaab.add("");
                                } else {
                                    zzaab.add(new String(bArr2, i9, i28, zzaac.zza));
                                    i9 += i28;
                                }
                            }
                            return i9;
                        }
                        throw zzaae.zzf();
                    }
                    int zzj16 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i29 = zzyh2.zza;
                    if (i29 >= 0) {
                        if (i29 == 0) {
                            zzaab.add("");
                        } else {
                            int i30 = zzj16 + i29;
                            if (zzaco.zzf(bArr2, zzj16, i30)) {
                                zzaab.add(new String(bArr2, zzj16, i29, zzaac.zza));
                                zzj16 = i30;
                            } else {
                                throw zzaae.zzd();
                            }
                        }
                        while (i8 < i13) {
                            int zzj17 = zzyi.zzj(bArr2, i8, zzyh2);
                            if (i14 != zzyh2.zza) {
                                return i8;
                            }
                            i8 = zzyi.zzj(bArr2, zzj17, zzyh2);
                            int i31 = zzyh2.zza;
                            if (i31 < 0) {
                                throw zzaae.zzf();
                            } else if (i31 == 0) {
                                zzaab.add("");
                            } else {
                                int i32 = i8 + i31;
                                if (zzaco.zzf(bArr2, i8, i32)) {
                                    zzaab.add(new String(bArr2, i8, i31, zzaac.zza));
                                    i8 = i32;
                                } else {
                                    throw zzaae.zzd();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzaae.zzf();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zzyi.zze(zzF(i16), i3, bArr, i, i2, zzaab, zzyh);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zzj18 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i33 = zzyh2.zza;
                    if (i33 < 0) {
                        throw zzaae.zzf();
                    } else if (i33 <= bArr2.length - zzj18) {
                        if (i33 == 0) {
                            zzaab.add(zzyu.zzb);
                        } else {
                            zzaab.add(zzyu.zzo(bArr2, zzj18, i33));
                            zzj18 += i33;
                        }
                        while (i10 < i13) {
                            int zzj19 = zzyi.zzj(bArr2, i10, zzyh2);
                            if (i14 != zzyh2.zza) {
                                return i10;
                            }
                            i10 = zzyi.zzj(bArr2, zzj19, zzyh2);
                            int i34 = zzyh2.zza;
                            if (i34 < 0) {
                                throw zzaae.zzf();
                            } else if (i34 > bArr2.length - i10) {
                                throw zzaae.zzi();
                            } else if (i34 == 0) {
                                zzaab.add(zzyu.zzb);
                            } else {
                                zzaab.add(zzyu.zzo(bArr2, i10, i34));
                                i10 += i34;
                            }
                        }
                        return i10;
                    } else {
                        throw zzaae.zzi();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzyi.zzf(bArr2, i12, zzaab, zzyh2);
                } else if (i15 == 0) {
                    i11 = zzyi.zzl(i3, bArr, i, i2, zzaab, zzyh);
                }
                zzzw zzzw = (zzzw) t2;
                zzaca zzaca = zzzw.zzc;
                if (zzaca == zzaca.zzc()) {
                    zzaca = null;
                }
                Object zzC = zzabn.zzC(i4, zzaab, zzE(i16), zzaca, this.zzo);
                if (zzC == null) {
                    return i11;
                }
                zzzw.zzc = (zzaca) zzC;
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    zzzx zzzx3 = (zzzx) zzaab;
                    int zzj20 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i35 = zzyh2.zza + zzj20;
                    while (zzj20 < i35) {
                        zzj20 = zzyi.zzj(bArr2, zzj20, zzyh2);
                        zzzx3.zzf(zzyx.zzs(zzyh2.zza));
                    }
                    if (zzj20 == i35) {
                        return zzj20;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 0) {
                    zzzx zzzx4 = (zzzx) zzaab;
                    int zzj21 = zzyi.zzj(bArr2, i12, zzyh2);
                    zzzx4.zzf(zzyx.zzs(zzyh2.zza));
                    while (zzj21 < i13) {
                        int zzj22 = zzyi.zzj(bArr2, zzj21, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return zzj21;
                        }
                        zzj21 = zzyi.zzj(bArr2, zzj22, zzyh2);
                        zzzx4.zzf(zzyx.zzs(zzyh2.zza));
                    }
                    return zzj21;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    zzaao zzaao5 = (zzaao) zzaab;
                    int zzj23 = zzyi.zzj(bArr2, i12, zzyh2);
                    int i36 = zzyh2.zza + zzj23;
                    while (zzj23 < i36) {
                        zzj23 = zzyi.zzm(bArr2, zzj23, zzyh2);
                        zzaao5.zzf(zzyx.zzt(zzyh2.zzb));
                    }
                    if (zzj23 == i36) {
                        return zzj23;
                    }
                    throw zzaae.zzi();
                } else if (i15 == 0) {
                    zzaao zzaao6 = (zzaao) zzaab;
                    int zzm4 = zzyi.zzm(bArr2, i12, zzyh2);
                    zzaao6.zzf(zzyx.zzt(zzyh2.zzb));
                    while (zzm4 < i13) {
                        int zzj24 = zzyi.zzj(bArr2, zzm4, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return zzm4;
                        }
                        zzm4 = zzyi.zzm(bArr2, zzj24, zzyh2);
                        zzaao6.zzf(zzyx.zzt(zzyh2.zzb));
                    }
                    return zzm4;
                }
                break;
            default:
                if (i15 == 3) {
                    zzabl zzF = zzF(i16);
                    int i37 = (i14 & -8) | 4;
                    int zzc2 = zzyi.zzc(zzF, bArr, i, i2, i37, zzyh);
                    zzaab.add(zzyh2.zzc);
                    while (zzc2 < i13) {
                        int zzj25 = zzyi.zzj(bArr2, zzc2, zzyh2);
                        if (i14 != zzyh2.zza) {
                            return zzc2;
                        }
                        zzc2 = zzyi.zzc(zzF, bArr, zzj25, i2, i37, zzyh);
                        zzaab.add(zzyh2.zzc);
                    }
                    return zzc2;
                }
                break;
        }
        return i12;
    }

    private final int zzx(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, 0);
    }

    private final int zzy(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, i2);
    }

    private final int zzz(int i) {
        return this.zzc[i + 2];
    }

    public final int zza(T t) {
        return this.zzj ? zzr(t) : zzq(t);
    }

    public final int zzb(T t) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzC = zzC(i2);
            int i3 = this.zzc[i2];
            long j = (long) (1048575 & zzC);
            int i4 = 37;
            switch (zzB(zzC)) {
                case 0:
                    i = (i * 53) + zzaac.zzc(Double.doubleToLongBits(zzacj.zza(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzacj.zzb(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzaac.zzc(zzacj.zzd(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzaac.zzc(zzacj.zzd(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzacj.zzc(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzaac.zzc(zzacj.zzd(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzacj.zzc(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzaac.zza(zzacj.zzw(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzacj.zzf(t, j)).hashCode();
                    break;
                case 9:
                    Object zzf2 = zzacj.zzf(t, j);
                    if (zzf2 != null) {
                        i4 = zzf2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzacj.zzf(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzacj.zzc(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzacj.zzc(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzacj.zzc(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzaac.zzc(zzacj.zzd(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzacj.zzc(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzaac.zzc(zzacj.zzd(t, j));
                    break;
                case 17:
                    Object zzf3 = zzacj.zzf(t, j);
                    if (zzf3 != null) {
                        i4 = zzf3.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzacj.zzf(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzacj.zzf(t, j).hashCode();
                    break;
                case 51:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zzc(Double.doubleToLongBits(zzo(t, j)));
                        break;
                    }
                case 52:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzp(t, j));
                        break;
                    }
                case 53:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zzc(zzD(t, j));
                        break;
                    }
                case 54:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zzc(zzD(t, j));
                        break;
                    }
                case 55:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(t, j);
                        break;
                    }
                case 56:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zzc(zzD(t, j));
                        break;
                    }
                case 57:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(t, j);
                        break;
                    }
                case 58:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zza(zzU(t, j));
                        break;
                    }
                case 59:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzacj.zzf(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzacj.zzf(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzacj.zzf(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(t, j);
                        break;
                    }
                case 63:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(t, j);
                        break;
                    }
                case 64:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(t, j);
                        break;
                    }
                case 65:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zzc(zzD(t, j));
                        break;
                    }
                case 66:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(t, j);
                        break;
                    }
                case 67:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaac.zzc(zzD(t, j));
                        break;
                    }
                case 68:
                    if (!zzT(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzacj.zzf(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzo.zzd(t).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzp.zza(t);
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(T t, byte[] bArr, int i, int i2, int i3, zzyh zzyh) throws IOException {
        Unsafe unsafe;
        int i4;
        T t2;
        zzabc zzabc;
        Object obj;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        T t3;
        int i13;
        zzyh zzyh2;
        int zzi2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        boolean z;
        zzabc zzabc2 = this;
        T t4 = t;
        byte[] bArr2 = bArr;
        int i23 = i2;
        int i24 = i3;
        zzyh zzyh3 = zzyh;
        Unsafe unsafe2 = zzb;
        int i25 = i;
        int i26 = 0;
        int i27 = 0;
        int i28 = 0;
        int i29 = -1;
        int i30 = 1048575;
        while (true) {
            if (i25 < i23) {
                int i31 = i25 + 1;
                byte b = bArr2[i25];
                if (b < 0) {
                    i6 = zzyi.zzk(b, bArr2, i31, zzyh3);
                    i5 = zzyh3.zza;
                } else {
                    int i32 = i31;
                    i5 = b;
                    i6 = i32;
                }
                int i33 = i5 >>> 3;
                int i34 = i5 & 7;
                if (i33 > i29) {
                    i7 = zzabc2.zzy(i33, i27 / 3);
                } else {
                    i7 = zzabc2.zzx(i33);
                }
                if (i7 == -1) {
                    i12 = i6;
                    i8 = i5;
                    i11 = i28;
                    i10 = i33;
                    unsafe = unsafe2;
                    i9 = 0;
                } else {
                    int i35 = zzabc2.zzc[i7 + 1];
                    int zzB = zzB(i35);
                    int i36 = i5;
                    int i37 = i6;
                    long j = (long) (i35 & 1048575);
                    if (zzB <= 17) {
                        int i38 = zzabc2.zzc[i7 + 2];
                        int i39 = 1 << (i38 >>> 20);
                        int i40 = i38 & 1048575;
                        if (i40 != i30) {
                            if (i30 != 1048575) {
                                i18 = i7;
                                unsafe2.putInt(t4, (long) i30, i28);
                            } else {
                                i18 = i7;
                            }
                            i17 = i40;
                            i19 = unsafe2.getInt(t4, (long) i40);
                        } else {
                            i18 = i7;
                            i17 = i30;
                            i19 = i28;
                        }
                        switch (zzB) {
                            case 0:
                                i21 = i36;
                                i12 = i37;
                                long j2 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 == 1) {
                                    zzacj.zzo(t4, j2, Double.longBitsToDouble(zzyi.zzn(bArr2, i12)));
                                    i25 = i12 + 8;
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                                break;
                            case 1:
                                i21 = i36;
                                i12 = i37;
                                long j3 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 == 5) {
                                    zzacj.zzp(t4, j3, Float.intBitsToFloat(zzyi.zzb(bArr2, i12)));
                                    i25 = i12 + 4;
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                                break;
                            case 2:
                            case 3:
                                i21 = i36;
                                i12 = i37;
                                long j4 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 == 0) {
                                    int zzm2 = zzyi.zzm(bArr2, i12, zzyh3);
                                    unsafe2.putLong(t, j4, zzyh3.zzb);
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i25 = zzm2;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                                break;
                            case 4:
                            case 11:
                                i21 = i36;
                                i12 = i37;
                                long j5 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 == 0) {
                                    i25 = zzyi.zzj(bArr2, i12, zzyh3);
                                    unsafe2.putInt(t4, j5, zzyh3.zza);
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                                break;
                            case 5:
                            case 14:
                                i21 = i36;
                                int i41 = i37;
                                long j6 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 1) {
                                    i12 = i41;
                                    break;
                                } else {
                                    unsafe2.putLong(t, j6, zzyi.zzn(bArr2, i41));
                                    i25 = i41 + 8;
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 6:
                            case 13:
                                i21 = i36;
                                int i42 = i37;
                                long j7 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 5) {
                                    i12 = i42;
                                    break;
                                } else {
                                    unsafe2.putInt(t4, j7, zzyi.zzb(bArr2, i42));
                                    i25 = i42 + 4;
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 7:
                                i21 = i36;
                                int i43 = i37;
                                long j8 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 0) {
                                    i12 = i43;
                                    break;
                                } else {
                                    i25 = zzyi.zzm(bArr2, i43, zzyh3);
                                    if (zzyh3.zzb != 0) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    zzacj.zzm(t4, j8, z);
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 8:
                                i21 = i36;
                                int i44 = i37;
                                long j9 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 2) {
                                    i12 = i44;
                                    break;
                                } else {
                                    if ((536870912 & i35) == 0) {
                                        i25 = zzyi.zzg(bArr2, i44, zzyh3);
                                    } else {
                                        i25 = zzyi.zzh(bArr2, i44, zzyh3);
                                    }
                                    unsafe2.putObject(t4, j9, zzyh3.zzc);
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 9:
                                i21 = i36;
                                int i45 = i37;
                                long j10 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 2) {
                                    i12 = i45;
                                    break;
                                } else {
                                    i25 = zzyi.zzd(zzabc2.zzF(i22), bArr2, i45, i23, zzyh3);
                                    if ((i19 & i39) == 0) {
                                        unsafe2.putObject(t4, j10, zzyh3.zzc);
                                    } else {
                                        unsafe2.putObject(t4, j10, zzaac.zzg(unsafe2.getObject(t4, j10), zzyh3.zzc));
                                    }
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 10:
                                i21 = i36;
                                int i46 = i37;
                                long j11 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 2) {
                                    i12 = i46;
                                    break;
                                } else {
                                    i25 = zzyi.zza(bArr2, i46, zzyh3);
                                    unsafe2.putObject(t4, j11, zzyh3.zzc);
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 12:
                                i21 = i36;
                                int i47 = i37;
                                long j12 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 0) {
                                    i12 = i47;
                                    break;
                                } else {
                                    i25 = zzyi.zzj(bArr2, i47, zzyh3);
                                    int i48 = zzyh3.zza;
                                    zzaaa zzE = zzabc2.zzE(i22);
                                    if (zzE == null || zzE.zza()) {
                                        unsafe2.putInt(t4, j12, i48);
                                        i28 = i19 | i39;
                                        i27 = i22;
                                        i26 = i21;
                                        i29 = i20;
                                        i30 = i17;
                                        i24 = i3;
                                        break;
                                    } else {
                                        zzd(t).zzh(i21, Long.valueOf((long) i48));
                                        i28 = i19;
                                        i27 = i22;
                                        i26 = i21;
                                        i29 = i20;
                                        i30 = i17;
                                        i24 = i3;
                                        continue;
                                    }
                                }
                                break;
                            case 15:
                                i21 = i36;
                                int i49 = i37;
                                long j13 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 != 0) {
                                    i12 = i49;
                                    break;
                                } else {
                                    i25 = zzyi.zzj(bArr2, i49, zzyh3);
                                    unsafe2.putInt(t4, j13, zzyx.zzs(zzyh3.zza));
                                    i28 = i19 | i39;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            case 16:
                                if (i34 != 0) {
                                    i21 = i36;
                                    i20 = i33;
                                    i22 = i18;
                                    i12 = i37;
                                    break;
                                } else {
                                    int zzm3 = zzyi.zzm(bArr2, i37, zzyh3);
                                    unsafe2.putLong(t, j, zzyx.zzt(zzyh3.zzb));
                                    i28 = i19 | i39;
                                    i27 = i18;
                                    i25 = zzm3;
                                    i26 = i36;
                                    i29 = i33;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                            default:
                                i21 = i36;
                                i12 = i37;
                                long j14 = j;
                                i20 = i33;
                                i22 = i18;
                                if (i34 == 3) {
                                    long j15 = j14;
                                    i25 = zzyi.zzc(zzabc2.zzF(i22), bArr, i12, i2, (i20 << 3) | 4, zzyh);
                                    if ((i19 & i39) == 0) {
                                        unsafe2.putObject(t4, j15, zzyh3.zzc);
                                    } else {
                                        unsafe2.putObject(t4, j15, zzaac.zzg(unsafe2.getObject(t4, j15), zzyh3.zzc));
                                    }
                                    i28 = i19 | i39;
                                    bArr2 = bArr;
                                    i23 = i2;
                                    i27 = i22;
                                    i26 = i21;
                                    i29 = i20;
                                    i30 = i17;
                                    i24 = i3;
                                    continue;
                                }
                                break;
                        }
                        i11 = i19;
                        unsafe = unsafe2;
                        i8 = i21;
                        i10 = i20;
                        i30 = i17;
                        i9 = i22;
                    } else {
                        int i50 = i36;
                        long j16 = j;
                        int i51 = i33;
                        int i52 = i7;
                        int i53 = i37;
                        if (zzB != 27) {
                            i11 = i28;
                            i14 = i30;
                            int i54 = i53;
                            if (zzB <= 49) {
                                int i55 = i54;
                                int i56 = i51;
                                i10 = i51;
                                i9 = i52;
                                unsafe = unsafe2;
                                i8 = i50;
                                i25 = zzw(t, bArr, i54, i2, i50, i56, i34, i9, (long) i35, zzB, j16, zzyh);
                                if (i25 != i55) {
                                    zzabc2 = this;
                                    t4 = t;
                                    bArr2 = bArr;
                                    i23 = i2;
                                    i24 = i3;
                                    zzyh3 = zzyh;
                                    i28 = i11;
                                    i29 = i10;
                                    i27 = i9;
                                    i26 = i8;
                                    i30 = i14;
                                    unsafe2 = unsafe;
                                } else {
                                    i12 = i25;
                                    i30 = i14;
                                }
                            } else {
                                i15 = i54;
                                unsafe = unsafe2;
                                i8 = i50;
                                i10 = i51;
                                i9 = i52;
                                int i57 = zzB;
                                if (i57 != 50) {
                                    i25 = zzu(t, bArr, i15, i2, i8, i10, i34, i35, i57, j16, i9, zzyh);
                                    if (i25 != i15) {
                                        zzabc2 = this;
                                        t4 = t;
                                        bArr2 = bArr;
                                        i23 = i2;
                                        i24 = i3;
                                        zzyh3 = zzyh;
                                        i28 = i11;
                                        i29 = i10;
                                        i27 = i9;
                                        i26 = i8;
                                        i30 = i14;
                                        unsafe2 = unsafe;
                                    } else {
                                        i12 = i25;
                                        i30 = i14;
                                    }
                                } else if (i34 == 2) {
                                    i25 = zzt(t, bArr, i15, i2, i9, j16, zzyh);
                                    if (i25 != i15) {
                                        zzabc2 = this;
                                        t4 = t;
                                        bArr2 = bArr;
                                        i23 = i2;
                                        i24 = i3;
                                        zzyh3 = zzyh;
                                        i28 = i11;
                                        i29 = i10;
                                        i27 = i9;
                                        i26 = i8;
                                        i30 = i14;
                                        unsafe2 = unsafe;
                                    } else {
                                        i12 = i25;
                                        i30 = i14;
                                    }
                                }
                            }
                        } else if (i34 == 2) {
                            zzaab zzaab = (zzaab) unsafe2.getObject(t4, j16);
                            if (!zzaab.zzc()) {
                                int size = zzaab.size();
                                if (size == 0) {
                                    i16 = 10;
                                } else {
                                    i16 = size + size;
                                }
                                zzaab = zzaab.zzd(i16);
                                unsafe2.putObject(t4, j16, zzaab);
                            }
                            i26 = i50;
                            i25 = zzyi.zze(zzabc2.zzF(i52), i26, bArr, i53, i2, zzaab, zzyh);
                            bArr2 = bArr;
                            i23 = i2;
                            i27 = i52;
                            i28 = i28;
                            i29 = i51;
                            i30 = i30;
                            i24 = i3;
                        } else {
                            i11 = i28;
                            i14 = i30;
                            i15 = i53;
                            unsafe = unsafe2;
                            i8 = i50;
                            i10 = i51;
                            i9 = i52;
                        }
                        i12 = i15;
                        i30 = i14;
                    }
                }
                i4 = i3;
                int i58 = i8;
                if (i58 != i4 || i4 == 0) {
                    if (this.zzh) {
                        zzyh2 = zzyh;
                        if (zzyh2.zzd != zzzj.zza()) {
                            i13 = i10;
                            if (zzyh2.zzd.zzb(this.zzg, i13) == null) {
                                zzi2 = zzyi.zzi(i58, bArr, i12, i2, zzd(t), zzyh);
                                t3 = t;
                                i23 = i2;
                                i26 = i58;
                                zzabc2 = this;
                                zzyh3 = zzyh2;
                                i29 = i13;
                                t4 = t3;
                                i28 = i11;
                                i27 = i9;
                                unsafe2 = unsafe;
                                bArr2 = bArr;
                                i24 = i4;
                            } else {
                                zzzt zzzt = (zzzt) t;
                                throw null;
                            }
                        } else {
                            t3 = t;
                            i13 = i10;
                        }
                    } else {
                        t3 = t;
                        zzyh2 = zzyh;
                        i13 = i10;
                    }
                    zzi2 = zzyi.zzi(i58, bArr, i12, i2, zzd(t), zzyh);
                    i23 = i2;
                    i26 = i58;
                    zzabc2 = this;
                    zzyh3 = zzyh2;
                    i29 = i13;
                    t4 = t3;
                    i28 = i11;
                    i27 = i9;
                    unsafe2 = unsafe;
                    bArr2 = bArr;
                    i24 = i4;
                } else {
                    zzabc = this;
                    t2 = t;
                    i25 = i12;
                    i26 = i58;
                    i28 = i11;
                    obj = null;
                }
            } else {
                int i59 = i28;
                int i60 = i30;
                unsafe = unsafe2;
                i4 = i24;
                t2 = t4;
                zzabc = zzabc2;
                obj = null;
            }
        }
        if (i30 != 1048575) {
            unsafe.putInt(t2, (long) i30, i28);
        }
        for (int i61 = zzabc.zzl; i61 < zzabc.zzm; i61++) {
            zzabc.zzG(t2, zzabc.zzk[i61], obj, zzabc.zzo);
        }
        if (i4 == 0) {
            if (i25 != i2) {
                throw zzaae.zzg();
            }
        } else if (i25 > i2 || i26 != i4) {
            throw zzaae.zzg();
        }
        return i25;
    }

    public final T zze() {
        return ((zzzw) this.zzg).zzj(4, (Object) null, (Object) null);
    }

    public final void zzf(T t) {
        int i;
        int i2 = this.zzl;
        while (true) {
            i = this.zzm;
            if (i2 >= i) {
                break;
            }
            long zzC = (long) (zzC(this.zzk[i2]) & 1048575);
            Object zzf2 = zzacj.zzf(t, zzC);
            if (zzf2 != null) {
                ((zzaat) zzf2).zzc();
                zzacj.zzs(t, zzC, zzf2);
            }
            i2++;
        }
        int length = this.zzk.length;
        while (i < length) {
            this.zzn.zzb(t, (long) this.zzk[i]);
            i++;
        }
        this.zzo.zzm(t);
        if (this.zzh) {
            this.zzp.zze(t);
        }
    }

    public final void zzg(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzC = zzC(i);
                long j = (long) (1048575 & zzC);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzo(t, j, zzacj.zza(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 1:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzp(t, j, zzacj.zzb(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 2:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzr(t, j, zzacj.zzd(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 3:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzr(t, j, zzacj.zzd(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 4:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzq(t, j, zzacj.zzc(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 5:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzr(t, j, zzacj.zzd(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 6:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzq(t, j, zzacj.zzc(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 7:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzm(t, j, zzacj.zzw(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 8:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzs(t, j, zzacj.zzf(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 9:
                        zzJ(t, t2, i);
                        break;
                    case 10:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzs(t, j, zzacj.zzf(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 11:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzq(t, j, zzacj.zzc(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 12:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzq(t, j, zzacj.zzc(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 13:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzq(t, j, zzacj.zzc(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 14:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzr(t, j, zzacj.zzd(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 15:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzq(t, j, zzacj.zzc(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 16:
                        if (!zzQ(t2, i)) {
                            break;
                        } else {
                            zzacj.zzr(t, j, zzacj.zzd(t2, j));
                            zzM(t, i);
                            break;
                        }
                    case 17:
                        zzJ(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzn.zzc(t, t2, j);
                        break;
                    case 50:
                        zzabn.zzI(this.zzr, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zzT(t2, i2, i)) {
                            break;
                        } else {
                            zzacj.zzs(t, j, zzacj.zzf(t2, j));
                            zzN(t, i2, i);
                            break;
                        }
                    case 60:
                        zzK(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zzT(t2, i2, i)) {
                            break;
                        } else {
                            zzacj.zzs(t, j, zzacj.zzf(t2, j));
                            zzN(t, i2, i);
                            break;
                        }
                    case 68:
                        zzK(t, t2, i);
                        break;
                }
            }
            zzabn.zzF(this.zzo, t, t2);
            if (this.zzh) {
                zzabn.zzE(this.zzp, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x05c9 A[LOOP:5: B:170:0x05c5->B:172:0x05c9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x05d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzh(T r13, com.google.android.gms.internal.p010firebaseauthapi.zzabk r14, com.google.android.gms.internal.p010firebaseauthapi.zzzj r15) throws java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            if (r15 == 0) goto L_0x05da
            com.google.android.gms.internal.firebase-auth-api.zzabz<?, ?> r8 = r12.zzo
            com.google.android.gms.internal.firebase-auth-api.zzzk<?> r9 = r12.zzp
            r1 = r0
            r10 = r1
        L_0x0009:
            int r2 = r14.zzc()     // Catch:{ all -> 0x05c2 }
            int r3 = r12.zzx(r2)     // Catch:{ all -> 0x05c2 }
            if (r3 >= 0) goto L_0x0079
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r3) goto L_0x002f
            int r14 = r12.zzl
        L_0x001a:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x0029
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x001a
        L_0x0029:
            if (r10 == 0) goto L_0x05bf
        L_0x002b:
            r8.zzn(r13, r10)
            return
        L_0x002f:
            boolean r3 = r12.zzh     // Catch:{ all -> 0x05c2 }
            if (r3 != 0) goto L_0x0035
            r3 = r0
            goto L_0x003c
        L_0x0035:
            com.google.android.gms.internal.firebase-auth-api.zzaaz r3 = r12.zzg     // Catch:{ all -> 0x05c2 }
            java.lang.Object r2 = r9.zzc(r15, r3, r2)     // Catch:{ all -> 0x05c2 }
            r3 = r2
        L_0x003c:
            if (r3 == 0) goto L_0x0053
            if (r1 != 0) goto L_0x0046
            com.google.android.gms.internal.firebase-auth-api.zzzo r1 = r9.zzb(r13)     // Catch:{ all -> 0x05c2 }
            r11 = r1
            goto L_0x0047
        L_0x0046:
            r11 = r1
        L_0x0047:
            r1 = r9
            r2 = r14
            r4 = r15
            r5 = r11
            r6 = r10
            r7 = r8
            java.lang.Object r10 = r1.zzd(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x05c2 }
            r1 = r11
            goto L_0x0009
        L_0x0053:
            r8.zzq(r14)     // Catch:{ all -> 0x05c2 }
            if (r10 != 0) goto L_0x005c
            java.lang.Object r10 = r8.zzc(r13)     // Catch:{ all -> 0x05c2 }
        L_0x005c:
            boolean r2 = r8.zzp(r10, r14)     // Catch:{ all -> 0x0076 }
            if (r2 != 0) goto L_0x0009
            int r14 = r12.zzl
        L_0x0064:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x0073
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x0064
        L_0x0073:
            if (r10 == 0) goto L_0x05bf
            goto L_0x002b
        L_0x0076:
            r14 = move-exception
            goto L_0x05c3
        L_0x0079:
            int r4 = r12.zzC(r3)     // Catch:{ all -> 0x05c2 }
            int r5 = zzB(r4)     // Catch:{ zzaad -> 0x0599 }
            r6 = 1048575(0xfffff, float:1.469367E-39)
            switch(r5) {
                case 0: goto L_0x0569;
                case 1: goto L_0x055b;
                case 2: goto L_0x054d;
                case 3: goto L_0x053f;
                case 4: goto L_0x0531;
                case 5: goto L_0x0523;
                case 6: goto L_0x0514;
                case 7: goto L_0x0505;
                case 8: goto L_0x04fd;
                case 9: goto L_0x04cc;
                case 10: goto L_0x04bd;
                case 11: goto L_0x04ae;
                case 12: goto L_0x048c;
                case 13: goto L_0x047d;
                case 14: goto L_0x046e;
                case 15: goto L_0x045f;
                case 16: goto L_0x0450;
                case 17: goto L_0x041f;
                case 18: goto L_0x0411;
                case 19: goto L_0x0403;
                case 20: goto L_0x03f5;
                case 21: goto L_0x03e7;
                case 22: goto L_0x03d9;
                case 23: goto L_0x03cb;
                case 24: goto L_0x03bd;
                case 25: goto L_0x03af;
                case 26: goto L_0x0385;
                case 27: goto L_0x0373;
                case 28: goto L_0x0365;
                case 29: goto L_0x0357;
                case 30: goto L_0x0342;
                case 31: goto L_0x0334;
                case 32: goto L_0x0326;
                case 33: goto L_0x0318;
                case 34: goto L_0x030a;
                case 35: goto L_0x02fc;
                case 36: goto L_0x02ee;
                case 37: goto L_0x02e0;
                case 38: goto L_0x02d2;
                case 39: goto L_0x02c4;
                case 40: goto L_0x02b6;
                case 41: goto L_0x02a8;
                case 42: goto L_0x029a;
                case 43: goto L_0x028c;
                case 44: goto L_0x0277;
                case 45: goto L_0x0269;
                case 46: goto L_0x025b;
                case 47: goto L_0x024d;
                case 48: goto L_0x023f;
                case 49: goto L_0x022d;
                case 50: goto L_0x01f7;
                case 51: goto L_0x01e5;
                case 52: goto L_0x01d3;
                case 53: goto L_0x01c1;
                case 54: goto L_0x01af;
                case 55: goto L_0x019d;
                case 56: goto L_0x018b;
                case 57: goto L_0x0179;
                case 58: goto L_0x0167;
                case 59: goto L_0x015f;
                case 60: goto L_0x012e;
                case 61: goto L_0x0120;
                case 62: goto L_0x010e;
                case 63: goto L_0x00e9;
                case 64: goto L_0x00d7;
                case 65: goto L_0x00c5;
                case 66: goto L_0x00b3;
                case 67: goto L_0x00a1;
                case 68: goto L_0x008f;
                default: goto L_0x0087;
            }     // Catch:{ zzaad -> 0x0599 }
        L_0x0087:
            if (r10 != 0) goto L_0x0577
            java.lang.Object r10 = r8.zzf()     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0577
        L_0x008f:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r6 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r6 = r14.zzr(r6, r15)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x00a1:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzn()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x00b3:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            int r6 = r14.zzi()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x00c5:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzm()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x00d7:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            int r6 = r14.zzh()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x00e9:
            int r5 = r14.zze()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaaa r7 = r12.zzE(r3)     // Catch:{ zzaad -> 0x0599 }
            if (r7 == 0) goto L_0x0100
            boolean r7 = r7.zza()     // Catch:{ zzaad -> 0x0599 }
            if (r7 == 0) goto L_0x00fa
            goto L_0x0100
        L_0x00fa:
            java.lang.Object r10 = com.google.android.gms.internal.p010firebaseauthapi.zzabn.zzD(r2, r5, r10, r8)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0100:
            r4 = r4 & r6
            long r6 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r6, r4)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x010e:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            int r6 = r14.zzj()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0120:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzyu r6 = r14.zzp()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x012e:
            boolean r5 = r12.zzT(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            if (r5 == 0) goto L_0x014a
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r6 = com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzf(r13, r4)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r7 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r7 = r14.zzs(r7, r15)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r6 = com.google.android.gms.internal.p010firebaseauthapi.zzaac.zzg(r6, r7)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x015a
        L_0x014a:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r6 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r6 = r14.zzs(r6, r15)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
        L_0x015a:
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x015f:
            r12.zzL(r13, r4, r14)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0167:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            boolean r6 = r14.zzN()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0179:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            int r6 = r14.zzf()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x018b:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzk()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x019d:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            int r6 = r14.zzg()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x01af:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzo()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x01c1:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzl()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x01d3:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            float r6 = r14.zzb()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x01e5:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            double r6 = r14.zza()     // Catch:{ zzaad -> 0x0599 }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzN(r13, r2, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x01f7:
            java.lang.Object r2 = r12.zzH(r3)     // Catch:{ zzaad -> 0x0599 }
            int r3 = r12.zzC(r3)     // Catch:{ zzaad -> 0x0599 }
            r3 = r3 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r5 = com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzf(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            if (r5 == 0) goto L_0x021d
            boolean r6 = com.google.android.gms.internal.p010firebaseauthapi.zzaau.zzb(r5)     // Catch:{ zzaad -> 0x0599 }
            if (r6 == 0) goto L_0x0228
            com.google.android.gms.internal.firebase-auth-api.zzaat r6 = com.google.android.gms.internal.p010firebaseauthapi.zzaat.zza()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaat r6 = r6.zzb()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzaau.zzc(r6, r5)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r3, r6)     // Catch:{ zzaad -> 0x0599 }
            r5 = r6
            goto L_0x0228
        L_0x021d:
            com.google.android.gms.internal.firebase-auth-api.zzaat r5 = com.google.android.gms.internal.p010firebaseauthapi.zzaat.zza()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaat r5 = r5.zzb()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r3, r5)     // Catch:{ zzaad -> 0x0599 }
        L_0x0228:
            com.google.android.gms.internal.firebase-auth-api.zzaat r5 = (com.google.android.gms.internal.p010firebaseauthapi.zzaat) r5     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaas r2 = (com.google.android.gms.internal.p010firebaseauthapi.zzaas) r2     // Catch:{ zzaad -> 0x0599 }
            throw r0     // Catch:{ zzaad -> 0x0599 }
        L_0x022d:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r2 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaan r3 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            java.util.List r3 = r3.zza(r13, r4)     // Catch:{ zzaad -> 0x0599 }
            r14.zzC(r3, r2, r15)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x023f:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzJ(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x024d:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzI(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x025b:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzH(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0269:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzG(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0277:
            com.google.android.gms.internal.firebase-auth-api.zzaan r5 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r4 = r4 & r6
            long r6 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            java.util.List r4 = r5.zza(r13, r6)     // Catch:{ zzaad -> 0x0599 }
            r14.zzy(r4)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaaa r3 = r12.zzE(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r10 = com.google.android.gms.internal.p010firebaseauthapi.zzabn.zzC(r2, r4, r3, r10, r8)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x028c:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzL(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x029a:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzv(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02a8:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzz(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02b6:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzA(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02c4:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzD(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02d2:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzM(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02e0:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzE(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02ee:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzB(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x02fc:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzx(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x030a:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzJ(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0318:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzI(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0326:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzH(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0334:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzG(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0342:
            com.google.android.gms.internal.firebase-auth-api.zzaan r5 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r4 = r4 & r6
            long r6 = (long) r4     // Catch:{ zzaad -> 0x0599 }
            java.util.List r4 = r5.zza(r13, r6)     // Catch:{ zzaad -> 0x0599 }
            r14.zzy(r4)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaaa r3 = r12.zzE(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r10 = com.google.android.gms.internal.p010firebaseauthapi.zzabn.zzC(r2, r4, r3, r10, r8)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0357:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzL(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0365:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzw(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0373:
            com.google.android.gms.internal.firebase-auth-api.zzabl r2 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaan r5 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            java.util.List r3 = r5.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzF(r3, r2, r15)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0385:
            boolean r2 = zzP(r4)     // Catch:{ zzaad -> 0x0599 }
            if (r2 == 0) goto L_0x039d
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r3 = r14
            com.google.android.gms.internal.firebase-auth-api.zzyy r3 = (com.google.android.gms.internal.p010firebaseauthapi.zzyy) r3     // Catch:{ zzaad -> 0x0599 }
            r4 = 1
            r3.zzK(r2, r4)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x039d:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r3 = r14
            com.google.android.gms.internal.firebase-auth-api.zzyy r3 = (com.google.android.gms.internal.p010firebaseauthapi.zzyy) r3     // Catch:{ zzaad -> 0x0599 }
            r4 = 0
            r3.zzK(r2, r4)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x03af:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzv(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x03bd:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzz(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x03cb:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzA(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x03d9:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzD(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x03e7:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzM(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x03f5:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzE(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0403:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzB(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0411:
            com.google.android.gms.internal.firebase-auth-api.zzaan r2 = r12.zzn     // Catch:{ zzaad -> 0x0599 }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzaad -> 0x0599 }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            r14.zzx(r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x041f:
            boolean r2 = r12.zzQ(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            if (r2 == 0) goto L_0x043d
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r2 = com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzf(r13, r4)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r3 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r3 = r14.zzr(r3, r15)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r2 = com.google.android.gms.internal.p010firebaseauthapi.zzaac.zzg(r2, r3)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x043d:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r2 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r2 = r14.zzr(r2, r15)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0450:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzn()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzr(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x045f:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            int r2 = r14.zzi()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzq(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x046e:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzm()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzr(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x047d:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            int r2 = r14.zzh()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzq(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x048c:
            int r5 = r14.zze()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzaaa r7 = r12.zzE(r3)     // Catch:{ zzaad -> 0x0599 }
            if (r7 == 0) goto L_0x04a3
            boolean r7 = r7.zza()     // Catch:{ zzaad -> 0x0599 }
            if (r7 == 0) goto L_0x049d
            goto L_0x04a3
        L_0x049d:
            java.lang.Object r10 = com.google.android.gms.internal.p010firebaseauthapi.zzabn.zzD(r2, r5, r10, r8)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x04a3:
            r2 = r4 & r6
            long r6 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzq(r13, r6, r5)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x04ae:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            int r2 = r14.zzj()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzq(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x04bd:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzyu r2 = r14.zzp()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x04cc:
            boolean r2 = r12.zzQ(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            if (r2 == 0) goto L_0x04ea
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r2 = com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzf(r13, r4)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r3 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r3 = r14.zzs(r3, r15)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r2 = com.google.android.gms.internal.p010firebaseauthapi.zzaac.zzg(r2, r3)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x04ea:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.firebase-auth-api.zzabl r2 = r12.zzF(r3)     // Catch:{ zzaad -> 0x0599 }
            java.lang.Object r2 = r14.zzs(r2, r15)     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzs(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x04fd:
            r12.zzL(r13, r4, r14)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0505:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            boolean r2 = r14.zzN()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzm(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0514:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            int r2 = r14.zzf()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzq(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0523:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzk()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzr(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0531:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            int r2 = r14.zzg()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzq(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x053f:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzo()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzr(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x054d:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            long r6 = r14.zzl()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzr(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x055b:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            float r2 = r14.zzb()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzp(r13, r4, r2)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0569:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzaad -> 0x0599 }
            double r6 = r14.zza()     // Catch:{ zzaad -> 0x0599 }
            com.google.android.gms.internal.p010firebaseauthapi.zzacj.zzo(r13, r4, r6)     // Catch:{ zzaad -> 0x0599 }
            r12.zzM(r13, r3)     // Catch:{ zzaad -> 0x0599 }
            goto L_0x0593
        L_0x0577:
            boolean r2 = r8.zzp(r10, r14)     // Catch:{ zzaad -> 0x0597, all -> 0x0595 }
            if (r2 != 0) goto L_0x0592
            int r14 = r12.zzl
        L_0x057f:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x058e
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x057f
        L_0x058e:
            if (r10 == 0) goto L_0x05bf
            goto L_0x002b
        L_0x0592:
        L_0x0593:
            goto L_0x0009
        L_0x0595:
            r14 = move-exception
            goto L_0x05c3
        L_0x0597:
            r2 = move-exception
            goto L_0x059a
        L_0x0599:
            r2 = move-exception
        L_0x059a:
            r8.zzq(r14)     // Catch:{ all -> 0x05c0 }
            if (r10 != 0) goto L_0x05a4
            java.lang.Object r2 = r8.zzc(r13)     // Catch:{ all -> 0x05c0 }
            r10 = r2
        L_0x05a4:
            boolean r2 = r8.zzp(r10, r14)     // Catch:{ all -> 0x05c0 }
            if (r2 != 0) goto L_0x0009
            int r14 = r12.zzl
        L_0x05ac:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x05bb
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x05ac
        L_0x05bb:
            if (r10 == 0) goto L_0x05bf
            goto L_0x002b
        L_0x05bf:
            return
        L_0x05c0:
            r14 = move-exception
            goto L_0x05c3
        L_0x05c2:
            r14 = move-exception
        L_0x05c3:
            int r15 = r12.zzl
        L_0x05c5:
            int r0 = r12.zzm
            if (r15 >= r0) goto L_0x05d4
            int[] r0 = r12.zzk
            r0 = r0[r15]
            java.lang.Object r10 = r12.zzG(r13, r0, r10, r8)
            int r15 = r15 + 1
            goto L_0x05c5
        L_0x05d4:
            if (r10 == 0) goto L_0x05d9
            r8.zzn(r13, r10)
        L_0x05d9:
            throw r14
        L_0x05da:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzabc.zzh(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzabk, com.google.android.gms.internal.firebase-auth-api.zzzj):void");
    }

    public final void zzi(T t, byte[] bArr, int i, int i2, zzyh zzyh) throws IOException {
        if (this.zzj) {
            zzv(t, bArr, i, i2, zzyh);
        } else {
            zzc(t, bArr, i, i2, 0, zzyh);
        }
    }

    public final boolean zzj(T t, T t2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzC = zzC(i);
            long j = (long) (zzC & 1048575);
            switch (zzB(zzC)) {
                case 0:
                    if (zzO(t, t2, i) && Double.doubleToLongBits(zzacj.zza(t, j)) == Double.doubleToLongBits(zzacj.zza(t2, j))) {
                        continue;
                    }
                case 1:
                    if (zzO(t, t2, i) && Float.floatToIntBits(zzacj.zzb(t, j)) == Float.floatToIntBits(zzacj.zzb(t2, j))) {
                        continue;
                    }
                case 2:
                    if (zzO(t, t2, i) && zzacj.zzd(t, j) == zzacj.zzd(t2, j)) {
                        continue;
                    }
                case 3:
                    if (zzO(t, t2, i) && zzacj.zzd(t, j) == zzacj.zzd(t2, j)) {
                        continue;
                    }
                case 4:
                    if (zzO(t, t2, i) && zzacj.zzc(t, j) == zzacj.zzc(t2, j)) {
                        continue;
                    }
                case 5:
                    if (zzO(t, t2, i) && zzacj.zzd(t, j) == zzacj.zzd(t2, j)) {
                        continue;
                    }
                case 6:
                    if (zzO(t, t2, i) && zzacj.zzc(t, j) == zzacj.zzc(t2, j)) {
                        continue;
                    }
                case 7:
                    if (zzO(t, t2, i) && zzacj.zzw(t, j) == zzacj.zzw(t2, j)) {
                        continue;
                    }
                case 8:
                    if (zzO(t, t2, i) && zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j))) {
                        continue;
                    }
                case 9:
                    if (zzO(t, t2, i) && zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j))) {
                        continue;
                    }
                case 10:
                    if (zzO(t, t2, i) && zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j))) {
                        continue;
                    }
                case 11:
                    if (zzO(t, t2, i) && zzacj.zzc(t, j) == zzacj.zzc(t2, j)) {
                        continue;
                    }
                case 12:
                    if (zzO(t, t2, i) && zzacj.zzc(t, j) == zzacj.zzc(t2, j)) {
                        continue;
                    }
                case 13:
                    if (zzO(t, t2, i) && zzacj.zzc(t, j) == zzacj.zzc(t2, j)) {
                        continue;
                    }
                case 14:
                    if (zzO(t, t2, i) && zzacj.zzd(t, j) == zzacj.zzd(t2, j)) {
                        continue;
                    }
                case 15:
                    if (zzO(t, t2, i) && zzacj.zzc(t, j) == zzacj.zzc(t2, j)) {
                        continue;
                    }
                case 16:
                    if (zzO(t, t2, i) && zzacj.zzd(t, j) == zzacj.zzd(t2, j)) {
                        continue;
                    }
                case 17:
                    if (zzO(t, t2, i) && zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j));
                    break;
                case 50:
                    z = zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzz = (long) (zzz(i) & 1048575);
                    if (zzacj.zzc(t, zzz) == zzacj.zzc(t2, zzz) && zzabn.zzH(zzacj.zzf(t, j), zzacj.zzf(t2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzo.zzd(t).equals(this.zzo.zzd(t2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(t);
        this.zzp.zza(t2);
        throw null;
    }

    public final boolean zzk(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzC = zzC(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i3) {
                i2 = i3;
                i = i4;
            } else if (i9 != 1048575) {
                i = zzb.getInt(t2, (long) i9);
                i2 = i9;
            } else {
                i = i4;
                i2 = i9;
            }
            if ((268435456 & zzC) != 0 && !zzR(t, i6, i2, i, i10)) {
                return false;
            }
            switch (zzB(zzC)) {
                case 9:
                case 17:
                    if (zzR(t, i6, i2, i, i10) && !zzS(t2, zzC, zzF(i6))) {
                        return false;
                    }
                case 27:
                case 49:
                    List list = (List) zzacj.zzf(t2, (long) (zzC & 1048575));
                    if (!list.isEmpty()) {
                        zzabl zzF = zzF(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzF.zzk(list.get(i11))) {
                                return false;
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                case 50:
                    if (((zzaat) zzacj.zzf(t2, (long) (zzC & 1048575))).isEmpty()) {
                        break;
                    } else {
                        zzaas zzaas = (zzaas) zzH(i6);
                        throw null;
                    }
                case 60:
                case 68:
                    if (zzT(t2, i7, i6) && !zzS(t2, zzC, zzF(i6))) {
                        return false;
                    }
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(t2);
        throw null;
    }

    public final void zzn(T t, zzzf zzzf) throws IOException {
        if (!this.zzj) {
            zzV(t, zzzf);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzf(i2, zzacj.zza(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzo(i2, zzacj.zzb(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzt(i2, zzacj.zzd(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzJ(i2, zzacj.zzd(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzr(i2, zzacj.zzc(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzm(i2, zzacj.zzd(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzk(i2, zzacj.zzc(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzb(i2, zzacj.zzw(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzX(i2, zzacj.zzf(t, (long) (zzC & 1048575)), zzzf);
                            break;
                        }
                    case 9:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzv(i2, zzacj.zzf(t, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 10:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzd(i2, (zzyu) zzacj.zzf(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzH(i2, zzacj.zzc(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzi(i2, zzacj.zzc(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzw(i2, zzacj.zzc(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzy(i2, zzacj.zzd(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzA(i2, zzacj.zzc(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzC(i2, zzacj.zzd(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzQ(t, i)) {
                            break;
                        } else {
                            zzzf.zzq(i2, zzacj.zzf(t, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 18:
                        zzabn.zzL(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 19:
                        zzabn.zzP(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 20:
                        zzabn.zzS(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 21:
                        zzabn.zzaa(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 22:
                        zzabn.zzR(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 23:
                        zzabn.zzO(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 24:
                        zzabn.zzN(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 25:
                        zzabn.zzJ(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 26:
                        zzabn.zzY(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf);
                        break;
                    case 27:
                        zzabn.zzT(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, zzF(i));
                        break;
                    case 28:
                        zzabn.zzK(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf);
                        break;
                    case 29:
                        zzabn.zzZ(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 30:
                        zzabn.zzM(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 31:
                        zzabn.zzU(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 32:
                        zzabn.zzV(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 33:
                        zzabn.zzW(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 34:
                        zzabn.zzX(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, false);
                        break;
                    case 35:
                        zzabn.zzL(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 36:
                        zzabn.zzP(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 37:
                        zzabn.zzS(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 38:
                        zzabn.zzaa(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 39:
                        zzabn.zzR(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 40:
                        zzabn.zzO(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 41:
                        zzabn.zzN(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 42:
                        zzabn.zzJ(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 43:
                        zzabn.zzZ(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 44:
                        zzabn.zzM(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 45:
                        zzabn.zzU(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 46:
                        zzabn.zzV(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 47:
                        zzabn.zzW(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 48:
                        zzabn.zzX(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, true);
                        break;
                    case 49:
                        zzabn.zzQ(this.zzc[i], (List) zzacj.zzf(t, (long) (zzC & 1048575)), zzzf, zzF(i));
                        break;
                    case 50:
                        zzW(zzzf, i2, zzacj.zzf(t, (long) (zzC & 1048575)), i);
                        break;
                    case 51:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzf(i2, zzo(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzo(i2, zzp(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzt(i2, zzD(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzJ(i2, zzD(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzr(i2, zzs(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzm(i2, zzD(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzk(i2, zzs(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 58:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzb(i2, zzU(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzX(i2, zzacj.zzf(t, (long) (zzC & 1048575)), zzzf);
                            break;
                        }
                    case 60:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzv(i2, zzacj.zzf(t, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 61:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzd(i2, (zzyu) zzacj.zzf(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 62:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzH(i2, zzs(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 63:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzi(i2, zzs(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzw(i2, zzs(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzy(i2, zzD(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 66:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzA(i2, zzs(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzC(i2, zzD(t, (long) (zzC & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzT(t, i2, i)) {
                            break;
                        } else {
                            zzzf.zzq(i2, zzacj.zzf(t, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                }
            }
            zzabz<?, ?> zzabz = this.zzo;
            zzabz.zzr(zzabz.zzd(t), zzzf);
        } else {
            this.zzp.zza(t);
            throw null;
        }
    }

    private final <K, V> void zzW(zzzf zzzf, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzaas zzaas = (zzaas) zzH(i2);
            throw null;
        }
    }
}
