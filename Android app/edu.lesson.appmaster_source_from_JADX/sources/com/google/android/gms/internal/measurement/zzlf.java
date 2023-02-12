package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzlf<T> implements zzln<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzml.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlc zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzkq zzm;
    private final zzmb<?, ?> zzn;
    private final zzjk<?> zzo;
    private final zzlh zzp;
    private final zzkx zzq;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.google.android.gms.internal.measurement.zzlc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: com.google.android.gms.internal.measurement.zzlh} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.gms.internal.measurement.zzkx} */
    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.measurement.zzjk, com.google.android.gms.internal.measurement.zzjk<?>] */
    /* JADX WARNING: type inference failed for: r3v2, types: [int] */
    /* JADX WARNING: type inference failed for: r3v8, types: [int] */
    /* JADX WARNING: type inference failed for: r3v11, types: [com.google.android.gms.internal.measurement.zzkq] */
    /* JADX WARNING: type inference failed for: r3v12, types: [com.google.android.gms.internal.measurement.zzmb<?, ?>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzlf(int[] r6, int[] r7, java.lang.Object[] r8, int r9, int r10, com.google.android.gms.internal.measurement.zzlc r11, boolean r12, boolean r13, int[] r14, int r15, int r16, com.google.android.gms.internal.measurement.zzlh r17, com.google.android.gms.internal.measurement.zzkq r18, com.google.android.gms.internal.measurement.zzmb<?, ?> r19, com.google.android.gms.internal.measurement.zzjk<?> r20, com.google.android.gms.internal.measurement.zzkx r21) {
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
            r3 = r11
            r0.zzi = r3
            r3 = 0
            if (r2 == 0) goto L_0x0020
            boolean r4 = r2.zzc(r10)
            if (r4 == 0) goto L_0x0020
            r3 = 1
        L_0x0020:
            r0.zzh = r3
            r3 = r13
            r0.zzj = r3
            r3 = r14
            r0.zzk = r3
            r3 = r15
            r0.zzl = r3
            r3 = r16
            r0.zzp = r3
            r3 = r17
            r0.zzm = r3
            r3 = r18
            r0.zzn = r3
            r0.zzo = r2
            r0.zzg = r1
            r1 = r20
            r0.zzq = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlf.<init>(int[], java.lang.Object[], int, int, com.google.android.gms.internal.measurement.zzlc, boolean, boolean, int[], int, int, com.google.android.gms.internal.measurement.zzlh, com.google.android.gms.internal.measurement.zzkq, com.google.android.gms.internal.measurement.zzmb, com.google.android.gms.internal.measurement.zzjk, com.google.android.gms.internal.measurement.zzkx, byte[]):void");
    }

    private static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzB(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzC(T t, long j) {
        return ((Long) zzml.zzf(t, j)).longValue();
    }

    private final zzkb zzD(int i) {
        int i2 = i / 3;
        return (zzkb) this.zzd[i2 + i2 + 1];
    }

    private final zzln zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzln zzln = (zzln) this.zzd[i3];
        if (zzln != null) {
            return zzln;
        }
        zzln zzb2 = zzlk.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzG(Class<?> cls, String str) {
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

    private final void zzH(T t, T t2, int i) {
        long zzB = (long) (zzB(i) & 1048575);
        if (zzM(t2, i)) {
            Object zzf2 = zzml.zzf(t, zzB);
            Object zzf3 = zzml.zzf(t2, zzB);
            if (zzf2 != null && zzf3 != null) {
                zzml.zzs(t, zzB, zzkf.zzg(zzf2, zzf3));
                zzJ(t, i);
            } else if (zzf3 != null) {
                zzml.zzs(t, zzB, zzf3);
                zzJ(t, i);
            }
        }
    }

    private final void zzI(T t, T t2, int i) {
        Object obj;
        int zzB = zzB(i);
        int i2 = this.zzc[i];
        long j = (long) (zzB & 1048575);
        if (zzP(t2, i2, i)) {
            if (zzP(t, i2, i)) {
                obj = zzml.zzf(t, j);
            } else {
                obj = null;
            }
            Object zzf2 = zzml.zzf(t2, j);
            if (obj != null && zzf2 != null) {
                zzml.zzs(t, j, zzkf.zzg(obj, zzf2));
                zzK(t, i2, i);
            } else if (zzf2 != null) {
                zzml.zzs(t, j, zzf2);
                zzK(t, i2, i);
            }
        }
    }

    private final void zzJ(T t, int i) {
        int zzy = zzy(i);
        long j = (long) (1048575 & zzy);
        if (j != 1048575) {
            zzml.zzq(t, j, (1 << (zzy >>> 20)) | zzml.zzc(t, j));
        }
    }

    private final void zzK(T t, int i, int i2) {
        zzml.zzq(t, (long) (zzy(i2) & 1048575), i);
    }

    private final boolean zzL(T t, T t2, int i) {
        return zzM(t, i) == zzM(t2, i);
    }

    private final boolean zzM(T t, int i) {
        int zzy = zzy(i);
        long j = (long) (zzy & 1048575);
        if (j != 1048575) {
            return (zzml.zzc(t, j) & (1 << (zzy >>> 20))) != 0;
        }
        int zzB = zzB(i);
        long j2 = (long) (zzB & 1048575);
        switch (zzA(zzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzml.zza(t, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzml.zzb(t, j2)) != 0;
            case 2:
                return zzml.zzd(t, j2) != 0;
            case 3:
                return zzml.zzd(t, j2) != 0;
            case 4:
                return zzml.zzc(t, j2) != 0;
            case 5:
                return zzml.zzd(t, j2) != 0;
            case 6:
                return zzml.zzc(t, j2) != 0;
            case 7:
                return zzml.zzw(t, j2);
            case 8:
                Object zzf2 = zzml.zzf(t, j2);
                if (zzf2 instanceof String) {
                    return !((String) zzf2).isEmpty();
                }
                if (zzf2 instanceof zzix) {
                    return !zzix.zzb.equals(zzf2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzml.zzf(t, j2) != null;
            case 10:
                return !zzix.zzb.equals(zzml.zzf(t, j2));
            case 11:
                return zzml.zzc(t, j2) != 0;
            case 12:
                return zzml.zzc(t, j2) != 0;
            case 13:
                return zzml.zzc(t, j2) != 0;
            case 14:
                return zzml.zzd(t, j2) != 0;
            case 15:
                return zzml.zzc(t, j2) != 0;
            case 16:
                return zzml.zzd(t, j2) != 0;
            case 17:
                return zzml.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzN(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzM(t, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzO(Object obj, int i, zzln zzln) {
        return zzln.zzj(zzml.zzf(obj, (long) (i & 1048575)));
    }

    private final boolean zzP(T t, int i, int i2) {
        return zzml.zzc(t, (long) (zzy(i2) & 1048575)) == i;
    }

    private static <T> boolean zzQ(T t, long j) {
        return ((Boolean) zzml.zzf(t, j)).booleanValue();
    }

    private final void zzR(T t, zzjf zzjf) throws IOException {
        int i;
        T t2 = t;
        zzjf zzjf2 = zzjf;
        if (!this.zzh) {
            int length = this.zzc.length;
            Unsafe unsafe = zzb;
            int i2 = 1048575;
            int i3 = 1048575;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                int zzB = zzB(i4);
                int i6 = this.zzc[i4];
                int zzA = zzA(zzB);
                if (zzA <= 17) {
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
                long j = (long) (zzB & i2);
                switch (zzA) {
                    case 0:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzf(i6, zzml.zza(t2, j));
                            break;
                        }
                    case 1:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzo(i6, zzml.zzb(t2, j));
                            break;
                        }
                    case 2:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzt(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 3:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzJ(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 4:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzr(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 5:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzm(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 6:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzk(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 7:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzb(i6, zzml.zzw(t2, j));
                            break;
                        }
                    case 8:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzT(i6, unsafe.getObject(t2, j), zzjf2);
                            break;
                        }
                    case 9:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzv(i6, unsafe.getObject(t2, j), zzE(i4));
                            break;
                        }
                    case 10:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzd(i6, (zzix) unsafe.getObject(t2, j));
                            break;
                        }
                    case 11:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzH(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 12:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzi(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 13:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzw(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 14:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzy(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 15:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzA(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 16:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzC(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 17:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzjf2.zzq(i6, unsafe.getObject(t2, j), zzE(i4));
                            break;
                        }
                    case 18:
                        zzlp.zzL(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 19:
                        zzlp.zzP(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 20:
                        zzlp.zzS(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 21:
                        zzlp.zzaa(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 22:
                        zzlp.zzR(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 23:
                        zzlp.zzO(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 24:
                        zzlp.zzN(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 25:
                        zzlp.zzJ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 26:
                        zzlp.zzY(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2);
                        break;
                    case 27:
                        zzlp.zzT(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, zzE(i4));
                        break;
                    case 28:
                        zzlp.zzK(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2);
                        break;
                    case 29:
                        zzlp.zzZ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 30:
                        zzlp.zzM(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 31:
                        zzlp.zzU(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 32:
                        zzlp.zzV(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 33:
                        zzlp.zzW(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 34:
                        zzlp.zzX(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, false);
                        break;
                    case 35:
                        zzlp.zzL(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 36:
                        zzlp.zzP(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 37:
                        zzlp.zzS(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 38:
                        zzlp.zzaa(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 39:
                        zzlp.zzR(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 40:
                        zzlp.zzO(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 41:
                        zzlp.zzN(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 42:
                        zzlp.zzJ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 43:
                        zzlp.zzZ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 44:
                        zzlp.zzM(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 45:
                        zzlp.zzU(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 46:
                        zzlp.zzV(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 47:
                        zzlp.zzW(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 48:
                        zzlp.zzX(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, true);
                        break;
                    case 49:
                        zzlp.zzQ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzjf2, zzE(i4));
                        break;
                    case 50:
                        zzS(zzjf2, i6, unsafe.getObject(t2, j), i4);
                        break;
                    case 51:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzf(i6, zzn(t2, j));
                            break;
                        }
                    case 52:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzo(i6, zzo(t2, j));
                            break;
                        }
                    case 53:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzt(i6, zzC(t2, j));
                            break;
                        }
                    case 54:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzJ(i6, zzC(t2, j));
                            break;
                        }
                    case 55:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzr(i6, zzr(t2, j));
                            break;
                        }
                    case 56:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzm(i6, zzC(t2, j));
                            break;
                        }
                    case 57:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzk(i6, zzr(t2, j));
                            break;
                        }
                    case 58:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzb(i6, zzQ(t2, j));
                            break;
                        }
                    case 59:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzT(i6, unsafe.getObject(t2, j), zzjf2);
                            break;
                        }
                    case 60:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzv(i6, unsafe.getObject(t2, j), zzE(i4));
                            break;
                        }
                    case 61:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzd(i6, (zzix) unsafe.getObject(t2, j));
                            break;
                        }
                    case 62:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzH(i6, zzr(t2, j));
                            break;
                        }
                    case 63:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzi(i6, zzr(t2, j));
                            break;
                        }
                    case 64:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzw(i6, zzr(t2, j));
                            break;
                        }
                    case 65:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzy(i6, zzC(t2, j));
                            break;
                        }
                    case 66:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzA(i6, zzr(t2, j));
                            break;
                        }
                    case 67:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzC(i6, zzC(t2, j));
                            break;
                        }
                    case 68:
                        if (!zzP(t2, i6, i4)) {
                            break;
                        } else {
                            zzjf2.zzq(i6, unsafe.getObject(t2, j), zzE(i4));
                            break;
                        }
                }
                i4 += 3;
                i2 = 1048575;
            }
            zzmb<?, ?> zzmb = this.zzn;
            zzmb.zzi(zzmb.zzc(t2), zzjf2);
            return;
        }
        this.zzo.zza(t2);
        throw null;
    }

    private final <K, V> void zzS(zzjf zzjf, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzkv zzkv = (zzkv) zzF(i2);
            throw null;
        }
    }

    private static final void zzT(int i, Object obj, zzjf zzjf) throws IOException {
        if (obj instanceof String) {
            zzjf.zzF(i, (String) obj);
        } else {
            zzjf.zzd(i, (zzix) obj);
        }
    }

    static zzmc zzd(Object obj) {
        zzjx zzjx = (zzjx) obj;
        zzmc zzmc = zzjx.zzc;
        if (zzmc != zzmc.zzc()) {
            return zzmc;
        }
        zzmc zze2 = zzmc.zze();
        zzjx.zzc = zze2;
        return zze2;
    }

    static <T> zzlf<T> zzk(Class<T> cls, zzkz zzkz, zzlh zzlh, zzkq zzkq, zzmb<?, ?> zzmb, zzjk<?> zzjk, zzkx zzkx) {
        if (zzkz instanceof zzlm) {
            return zzl((zzlm) zzkz, zzlh, zzkq, zzmb, zzjk, zzkx);
        }
        zzly zzly = (zzly) zzkz;
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:158:0x034b  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x03ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.measurement.zzlf<T> zzl(com.google.android.gms.internal.measurement.zzlm r34, com.google.android.gms.internal.measurement.zzlh r35, com.google.android.gms.internal.measurement.zzkq r36, com.google.android.gms.internal.measurement.zzmb<?, ?> r37, com.google.android.gms.internal.measurement.zzjk<?> r38, com.google.android.gms.internal.measurement.zzkx r39) {
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
            com.google.android.gms.internal.measurement.zzlc r18 = r34.zza()
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
            java.lang.reflect.Field r12 = zzG(r1, r12)
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
            java.lang.reflect.Field r8 = zzG(r1, r8)
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
            java.lang.reflect.Field r8 = zzG(r1, r8)
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
            java.lang.reflect.Field r11 = zzG(r1, r11)
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
            com.google.android.gms.internal.measurement.zzlf r0 = new com.google.android.gms.internal.measurement.zzlf
            r4 = r0
            com.google.android.gms.internal.measurement.zzlc r9 = r34.zza()
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlf.zzl(com.google.android.gms.internal.measurement.zzlm, com.google.android.gms.internal.measurement.zzlh, com.google.android.gms.internal.measurement.zzkq, com.google.android.gms.internal.measurement.zzmb, com.google.android.gms.internal.measurement.zzjk, com.google.android.gms.internal.measurement.zzkx):com.google.android.gms.internal.measurement.zzlf");
    }

    private static <T> double zzn(T t, long j) {
        return ((Double) zzml.zzf(t, j)).doubleValue();
    }

    private static <T> float zzo(T t, long j) {
        return ((Float) zzml.zzf(t, j)).floatValue();
    }

    private final int zzp(T t) {
        int i;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzc.length; i5 += 3) {
            int zzB = zzB(i5);
            int i6 = this.zzc[i5];
            int zzA = zzA(zzB);
            if (zzA <= 17) {
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
            long j = (long) (zzB & 1048575);
            switch (zzA) {
                case 0:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 8;
                        break;
                    }
                case 1:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 4;
                        break;
                    }
                case 2:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzB(unsafe.getLong(t, j));
                        break;
                    }
                case 3:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzB(unsafe.getLong(t, j));
                        break;
                    }
                case 4:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzv(unsafe.getInt(t, j));
                        break;
                    }
                case 5:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 8;
                        break;
                    }
                case 6:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 4;
                        break;
                    }
                case 7:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 1;
                        break;
                    }
                case 8:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(t, j);
                        if (!(object instanceof zzix)) {
                            i3 += zzje.zzA(i6 << 3) + zzje.zzy((String) object);
                            break;
                        } else {
                            int zzA2 = zzje.zzA(i6 << 3);
                            int zzd2 = ((zzix) object).zzd();
                            i3 += zzA2 + zzje.zzA(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzlp.zzo(i6, unsafe.getObject(t, j), zzE(i5));
                        break;
                    }
                case 10:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        int zzA3 = zzje.zzA(i6 << 3);
                        int zzd3 = ((zzix) unsafe.getObject(t, j)).zzd();
                        i3 += zzA3 + zzje.zzA(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzA(unsafe.getInt(t, j));
                        break;
                    }
                case 12:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzv(unsafe.getInt(t, j));
                        break;
                    }
                case 13:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 4;
                        break;
                    }
                case 14:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 8;
                        break;
                    }
                case 15:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        int i9 = unsafe.getInt(t, j);
                        i3 += zzje.zzA(i6 << 3) + zzje.zzA((i9 >> 31) ^ (i9 + i9));
                        break;
                    }
                case 16:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(t, j);
                        i3 += zzje.zzA(i6 << 3) + zzje.zzB((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzje.zzu(i6, (zzlc) unsafe.getObject(t, j), zzE(i5));
                        break;
                    }
                case 18:
                    i3 += zzlp.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 19:
                    i3 += zzlp.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 20:
                    i3 += zzlp.zzm(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 21:
                    i3 += zzlp.zzx(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 22:
                    i3 += zzlp.zzk(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 23:
                    i3 += zzlp.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 24:
                    i3 += zzlp.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 25:
                    i3 += zzlp.zza(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 26:
                    i3 += zzlp.zzu(i6, (List) unsafe.getObject(t, j));
                    break;
                case 27:
                    i3 += zzlp.zzp(i6, (List) unsafe.getObject(t, j), zzE(i5));
                    break;
                case 28:
                    i3 += zzlp.zzc(i6, (List) unsafe.getObject(t, j));
                    break;
                case 29:
                    i3 += zzlp.zzv(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 30:
                    i3 += zzlp.zzd(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 31:
                    i3 += zzlp.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 32:
                    i3 += zzlp.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 33:
                    i3 += zzlp.zzq(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 34:
                    i3 += zzlp.zzs(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 35:
                    int zzi2 = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzlp.zzn((List) unsafe.getObject(t, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzlp.zzy((List) unsafe.getObject(t, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzlp.zzl((List) unsafe.getObject(t, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzlp.zzb((List) unsafe.getObject(t, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzlp.zzw((List) unsafe.getObject(t, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzlp.zze((List) unsafe.getObject(t, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr = zzlp.zzr((List) unsafe.getObject(t, j));
                    if (zzr <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzr) + zzr;
                        break;
                    }
                case 48:
                    int zzt = zzlp.zzt((List) unsafe.getObject(t, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i3 += zzje.zzz(i6) + zzje.zzA(zzt) + zzt;
                        break;
                    }
                case 49:
                    i3 += zzlp.zzj(i6, (List) unsafe.getObject(t, j), zzE(i5));
                    break;
                case 50:
                    zzkx.zza(i6, unsafe.getObject(t, j), zzF(i5));
                    break;
                case 51:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzB(zzC(t, j));
                        break;
                    }
                case 54:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzB(zzC(t, j));
                        break;
                    }
                case 55:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzv(zzr(t, j));
                        break;
                    }
                case 56:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(t, j);
                        if (!(object2 instanceof zzix)) {
                            i3 += zzje.zzA(i6 << 3) + zzje.zzy((String) object2);
                            break;
                        } else {
                            int zzA4 = zzje.zzA(i6 << 3);
                            int zzd4 = ((zzix) object2).zzd();
                            i3 += zzA4 + zzje.zzA(zzd4) + zzd4;
                            break;
                        }
                    }
                case 60:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzlp.zzo(i6, unsafe.getObject(t, j), zzE(i5));
                        break;
                    }
                case 61:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        int zzA5 = zzje.zzA(i6 << 3);
                        int zzd5 = ((zzix) unsafe.getObject(t, j)).zzd();
                        i3 += zzA5 + zzje.zzA(zzd5) + zzd5;
                        break;
                    }
                case 62:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzA(zzr(t, j));
                        break;
                    }
                case 63:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + zzje.zzv(zzr(t, j));
                        break;
                    }
                case 64:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzA(i6 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        int zzr2 = zzr(t, j);
                        i3 += zzje.zzA(i6 << 3) + zzje.zzA((zzr2 >> 31) ^ (zzr2 + zzr2));
                        break;
                    }
                case 67:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        long zzC = zzC(t, j);
                        i3 += zzje.zzA(i6 << 3) + zzje.zzB((zzC >> 63) ^ (zzC + zzC));
                        break;
                    }
                case 68:
                    if (!zzP(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzje.zzu(i6, (zzlc) unsafe.getObject(t, j), zzE(i5));
                        break;
                    }
            }
        }
        zzmb<?, ?> zzmb = this.zzn;
        int zza2 = i3 + zzmb.zza(zzmb.zzc(t));
        if (!this.zzh) {
            return zza2;
        }
        this.zzo.zza(t);
        throw null;
    }

    private final int zzq(T t) {
        Unsafe unsafe = zzb;
        int i = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzB = zzB(i2);
            int zzA = zzA(zzB);
            int i3 = this.zzc[i2];
            long j = (long) (zzB & 1048575);
            if (zzA >= zzjp.DOUBLE_LIST_PACKED.zza() && zzA <= zzjp.SINT64_LIST_PACKED.zza()) {
                int i4 = this.zzc[i2 + 2];
            }
            switch (zzA) {
                case 0:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 8;
                        break;
                    }
                case 1:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 4;
                        break;
                    }
                case 2:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzB(zzml.zzd(t, j));
                        break;
                    }
                case 3:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzB(zzml.zzd(t, j));
                        break;
                    }
                case 4:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzv(zzml.zzc(t, j));
                        break;
                    }
                case 5:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 8;
                        break;
                    }
                case 6:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 4;
                        break;
                    }
                case 7:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 1;
                        break;
                    }
                case 8:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        Object zzf2 = zzml.zzf(t, j);
                        if (!(zzf2 instanceof zzix)) {
                            i += zzje.zzA(i3 << 3) + zzje.zzy((String) zzf2);
                            break;
                        } else {
                            int zzA2 = zzje.zzA(i3 << 3);
                            int zzd2 = ((zzix) zzf2).zzd();
                            i += zzA2 + zzje.zzA(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzlp.zzo(i3, zzml.zzf(t, j), zzE(i2));
                        break;
                    }
                case 10:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        int zzA3 = zzje.zzA(i3 << 3);
                        int zzd3 = ((zzix) zzml.zzf(t, j)).zzd();
                        i += zzA3 + zzje.zzA(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzA(zzml.zzc(t, j));
                        break;
                    }
                case 12:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzv(zzml.zzc(t, j));
                        break;
                    }
                case 13:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 4;
                        break;
                    }
                case 14:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 8;
                        break;
                    }
                case 15:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        int zzc2 = zzml.zzc(t, j);
                        i += zzje.zzA(i3 << 3) + zzje.zzA((zzc2 >> 31) ^ (zzc2 + zzc2));
                        break;
                    }
                case 16:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        long zzd4 = zzml.zzd(t, j);
                        i += zzje.zzA(i3 << 3) + zzje.zzB((zzd4 >> 63) ^ (zzd4 + zzd4));
                        break;
                    }
                case 17:
                    if (!zzM(t, i2)) {
                        break;
                    } else {
                        i += zzje.zzu(i3, (zzlc) zzml.zzf(t, j), zzE(i2));
                        break;
                    }
                case 18:
                    i += zzlp.zzh(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 19:
                    i += zzlp.zzf(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 20:
                    i += zzlp.zzm(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 21:
                    i += zzlp.zzx(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 22:
                    i += zzlp.zzk(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 23:
                    i += zzlp.zzh(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 24:
                    i += zzlp.zzf(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 25:
                    i += zzlp.zza(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 26:
                    i += zzlp.zzu(i3, (List) zzml.zzf(t, j));
                    break;
                case 27:
                    i += zzlp.zzp(i3, (List) zzml.zzf(t, j), zzE(i2));
                    break;
                case 28:
                    i += zzlp.zzc(i3, (List) zzml.zzf(t, j));
                    break;
                case 29:
                    i += zzlp.zzv(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 30:
                    i += zzlp.zzd(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 31:
                    i += zzlp.zzf(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 32:
                    i += zzlp.zzh(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 33:
                    i += zzlp.zzq(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 34:
                    i += zzlp.zzs(i3, (List) zzml.zzf(t, j), false);
                    break;
                case 35:
                    int zzi2 = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzlp.zzn((List) unsafe.getObject(t, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzlp.zzy((List) unsafe.getObject(t, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzlp.zzl((List) unsafe.getObject(t, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzlp.zzb((List) unsafe.getObject(t, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzlp.zzw((List) unsafe.getObject(t, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzlp.zze((List) unsafe.getObject(t, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzlp.zzg((List) unsafe.getObject(t, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzlp.zzi((List) unsafe.getObject(t, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr = zzlp.zzr((List) unsafe.getObject(t, j));
                    if (zzr <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzr) + zzr;
                        break;
                    }
                case 48:
                    int zzt = zzlp.zzt((List) unsafe.getObject(t, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i += zzje.zzz(i3) + zzje.zzA(zzt) + zzt;
                        break;
                    }
                case 49:
                    i += zzlp.zzj(i3, (List) zzml.zzf(t, j), zzE(i2));
                    break;
                case 50:
                    zzkx.zza(i3, zzml.zzf(t, j), zzF(i2));
                    break;
                case 51:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzB(zzC(t, j));
                        break;
                    }
                case 54:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzB(zzC(t, j));
                        break;
                    }
                case 55:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzv(zzr(t, j));
                        break;
                    }
                case 56:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        Object zzf3 = zzml.zzf(t, j);
                        if (!(zzf3 instanceof zzix)) {
                            i += zzje.zzA(i3 << 3) + zzje.zzy((String) zzf3);
                            break;
                        } else {
                            int zzA4 = zzje.zzA(i3 << 3);
                            int zzd5 = ((zzix) zzf3).zzd();
                            i += zzA4 + zzje.zzA(zzd5) + zzd5;
                            break;
                        }
                    }
                case 60:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzlp.zzo(i3, zzml.zzf(t, j), zzE(i2));
                        break;
                    }
                case 61:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        int zzA5 = zzje.zzA(i3 << 3);
                        int zzd6 = ((zzix) zzml.zzf(t, j)).zzd();
                        i += zzA5 + zzje.zzA(zzd6) + zzd6;
                        break;
                    }
                case 62:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzA(zzr(t, j));
                        break;
                    }
                case 63:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + zzje.zzv(zzr(t, j));
                        break;
                    }
                case 64:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzA(i3 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        int zzr2 = zzr(t, j);
                        i += zzje.zzA(i3 << 3) + zzje.zzA((zzr2 >> 31) ^ (zzr2 + zzr2));
                        break;
                    }
                case 67:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        long zzC = zzC(t, j);
                        i += zzje.zzA(i3 << 3) + zzje.zzB((zzC >> 63) ^ (zzC + zzC));
                        break;
                    }
                case 68:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i += zzje.zzu(i3, (zzlc) zzml.zzf(t, j), zzE(i2));
                        break;
                    }
            }
        }
        zzmb<?, ?> zzmb = this.zzn;
        return i + zzmb.zza(zzmb.zzc(t));
    }

    private static <T> int zzr(T t, long j) {
        return ((Integer) zzml.zzf(t, j)).intValue();
    }

    private final <K, V> int zzs(T t, byte[] bArr, int i, int i2, int i3, long j, zzik zzik) throws IOException {
        Unsafe unsafe = zzb;
        Object zzF = zzF(i3);
        Object object = unsafe.getObject(t, j);
        if (!((zzkw) object).zze()) {
            zzkw zzb2 = zzkw.zza().zzb();
            zzkx.zzb(zzb2, object);
            unsafe.putObject(t, j, zzb2);
        }
        zzkv zzkv = (zzkv) zzF;
        throw null;
    }

    private final int zzt(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzik zzik) throws IOException {
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
        zzik zzik2 = zzik;
        Unsafe unsafe = zzb;
        long j3 = (long) (this.zzc[i13 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Double.valueOf(Double.longBitsToDouble(zzil.zzn(bArr, i))));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Float.valueOf(Float.intBitsToFloat(zzil.zzb(bArr, i))));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int zzm2 = zzil.zzm(bArr2, i9, zzik2);
                    unsafe.putObject(t2, j2, Long.valueOf(zzik2.zzb));
                    unsafe.putInt(t2, j3, i11);
                    return zzm2;
                }
                break;
            case 55:
            case 62:
                if (i12 == 0) {
                    int zzj2 = zzil.zzj(bArr2, i9, zzik2);
                    unsafe.putObject(t2, j2, Integer.valueOf(zzik2.zza));
                    unsafe.putInt(t2, j3, i11);
                    return zzj2;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(t2, j2, Long.valueOf(zzil.zzn(bArr, i)));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(t2, j2, Integer.valueOf(zzil.zzb(bArr, i)));
                    unsafe.putInt(t2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 58:
                if (i12 == 0) {
                    int zzm3 = zzil.zzm(bArr2, i9, zzik2);
                    if (zzik2.zzb != 0) {
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
                    int zzj3 = zzil.zzj(bArr2, i9, zzik2);
                    int i14 = zzik2.zza;
                    if (i14 == 0) {
                        unsafe.putObject(t2, j2, "");
                    } else if ((i6 & 536870912) == 0 || zzmq.zzf(bArr2, zzj3, zzj3 + i14)) {
                        unsafe.putObject(t2, j2, new String(bArr2, zzj3, i14, zzkf.zzb));
                        zzj3 += i14;
                    } else {
                        throw zzkh.zzc();
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzj3;
                }
                break;
            case 60:
                if (i12 == 2) {
                    int zzd2 = zzil.zzd(zzE(i13), bArr2, i9, i2, zzik2);
                    if (unsafe.getInt(t2, j3) == i11) {
                        obj = unsafe.getObject(t2, j2);
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        unsafe.putObject(t2, j2, zzik2.zzc);
                    } else {
                        unsafe.putObject(t2, j2, zzkf.zzg(obj, zzik2.zzc));
                    }
                    unsafe.putInt(t2, j3, i11);
                    return zzd2;
                }
                break;
            case 61:
                if (i12 == 2) {
                    int zza2 = zzil.zza(bArr2, i9, zzik2);
                    unsafe.putObject(t2, j2, zzik2.zzc);
                    unsafe.putInt(t2, j3, i11);
                    return zza2;
                }
                break;
            case 63:
                if (i12 == 0) {
                    int zzj4 = zzil.zzj(bArr2, i9, zzik2);
                    int i15 = zzik2.zza;
                    zzkb zzD = zzD(i13);
                    if (zzD == null || zzD.zza(i15)) {
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
                    int zzj5 = zzil.zzj(bArr2, i9, zzik2);
                    unsafe.putObject(t2, j2, Integer.valueOf(zzja.zzb(zzik2.zza)));
                    unsafe.putInt(t2, j3, i11);
                    return zzj5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int zzm4 = zzil.zzm(bArr2, i9, zzik2);
                    unsafe.putObject(t2, j2, Long.valueOf(zzja.zzc(zzik2.zzb)));
                    unsafe.putInt(t2, j3, i11);
                    return zzm4;
                }
                break;
            case 68:
                if (i12 == 3) {
                    int zzc2 = zzil.zzc(zzE(i13), bArr, i, i2, (i10 & -8) | 4, zzik);
                    if (unsafe.getInt(t2, j3) == i11) {
                        obj2 = unsafe.getObject(t2, j2);
                    } else {
                        obj2 = null;
                    }
                    if (obj2 == null) {
                        unsafe.putObject(t2, j2, zzik2.zzc);
                    } else {
                        unsafe.putObject(t2, j2, zzkf.zzg(obj2, zzik2.zzc));
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
    private final int zzu(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.measurement.zzik r35) throws java.io.IOException {
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzk(r0, r12, r3, r11)
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
            int r0 = r15.zzx(r5, r2)
            r2 = r0
            goto L_0x0041
        L_0x003c:
            int r0 = r15.zzw(r5)
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
            int r0 = zzA(r1)
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
            int r17 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r4, r11)
            long r0 = r11.zzb
            long r4 = com.google.android.gms.internal.measurement.zzja.zzc(r0)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r4, r11)
            int r1 = r11.zza
            int r1 = com.google.android.gms.internal.measurement.zzja.zzb(r1)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r4, r11)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zza(r12, r4, r11)
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
            com.google.android.gms.internal.measurement.zzln r0 = r15.zzE(r13)
            r2 = r34
            r19 = 1048575(0xfffff, float:1.469367E-39)
            int r0 = com.google.android.gms.internal.measurement.zzil.zzd(r0, r12, r4, r2, r11)
            java.lang.Object r1 = r10.getObject(r14, r8)
            if (r1 != 0) goto L_0x0163
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x016c
        L_0x0163:
            java.lang.Object r3 = r11.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzkf.zzg(r1, r3)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzg(r12, r4, r11)
            goto L_0x019c
        L_0x0198:
            int r0 = com.google.android.gms.internal.measurement.zzil.zzh(r12, r4, r11)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r4, r11)
            long r3 = r11.zzb
            r22 = 0
            int r1 = (r3 > r22 ? 1 : (r3 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x01c9
            goto L_0x01cb
        L_0x01c9:
            r5 = r16
        L_0x01cb:
            com.google.android.gms.internal.measurement.zzml.zzm(r14, r8, r5)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r4)
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
            long r22 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r4)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r4, r11)
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
            int r17 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r4, r11)
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
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r4)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.measurement.zzml.zzp(r14, r8, r0)
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
            long r0 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r4)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.measurement.zzml.zzo(r14, r8, r0)
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
            com.google.android.gms.internal.measurement.zzke r0 = (com.google.android.gms.internal.measurement.zzke) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x02f1
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02e7
            r1 = 10
            goto L_0x02e8
        L_0x02e7:
            int r1 = r1 + r1
        L_0x02e8:
            com.google.android.gms.internal.measurement.zzke r0 = r0.zzd(r1)
            r10.putObject(r14, r8, r0)
            r5 = r0
            goto L_0x02f2
        L_0x02f1:
            r5 = r0
        L_0x02f2:
            com.google.android.gms.internal.measurement.zzln r0 = r15.zzE(r13)
            r1 = r17
            r2 = r32
            r3 = r4
            r4 = r34
            r8 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.measurement.zzil.zze(r0, r1, r2, r3, r4, r5, r6)
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
            int r0 = r0.zzv(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
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
            int r0 = r0.zzs(r1, r2, r3, r4, r5, r6, r8)
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
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
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
            com.google.android.gms.internal.measurement.zzmc r4 = zzd(r31)
            r0 = r17
            r1 = r32
            r3 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.measurement.zzil.zzi(r0, r1, r2, r3, r4, r5)
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
            com.google.android.gms.internal.measurement.zzkh r0 = com.google.android.gms.internal.measurement.zzkh.zze()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlf.zzu(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    private final int zzv(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzik zzik) throws IOException {
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
        zzik zzik2 = zzik;
        Unsafe unsafe = zzb;
        zzke zzke = (zzke) unsafe.getObject(t2, j3);
        if (!zzke.zzc()) {
            int size = zzke.size();
            zzke = zzke.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(t2, j3, zzke);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    zzjg zzjg = (zzjg) zzke;
                    int zzj2 = zzil.zzj(bArr2, i12, zzik2);
                    int i17 = zzik2.zza + zzj2;
                    while (zzj2 < i17) {
                        zzjg.zze(Double.longBitsToDouble(zzil.zzn(bArr2, zzj2)));
                        zzj2 += 8;
                    }
                    if (zzj2 == i17) {
                        return zzj2;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 1) {
                    zzjg zzjg2 = (zzjg) zzke;
                    zzjg2.zze(Double.longBitsToDouble(zzil.zzn(bArr, i)));
                    int i18 = i12 + 8;
                    while (i18 < i13) {
                        int zzj3 = zzil.zzj(bArr2, i18, zzik2);
                        if (i14 != zzik2.zza) {
                            return i18;
                        }
                        zzjg2.zze(Double.longBitsToDouble(zzil.zzn(bArr2, zzj3)));
                        i18 = zzj3 + 8;
                    }
                    return i18;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    zzjq zzjq = (zzjq) zzke;
                    int zzj4 = zzil.zzj(bArr2, i12, zzik2);
                    int i19 = zzik2.zza + zzj4;
                    while (zzj4 < i19) {
                        zzjq.zze(Float.intBitsToFloat(zzil.zzb(bArr2, zzj4)));
                        zzj4 += 4;
                    }
                    if (zzj4 == i19) {
                        return zzj4;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 5) {
                    zzjq zzjq2 = (zzjq) zzke;
                    zzjq2.zze(Float.intBitsToFloat(zzil.zzb(bArr, i)));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zzj5 = zzil.zzj(bArr2, i20, zzik2);
                        if (i14 != zzik2.zza) {
                            return i20;
                        }
                        zzjq2.zze(Float.intBitsToFloat(zzil.zzb(bArr2, zzj5)));
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
                    zzkr zzkr = (zzkr) zzke;
                    int zzj6 = zzil.zzj(bArr2, i12, zzik2);
                    int i21 = zzik2.zza + zzj6;
                    while (zzj6 < i21) {
                        zzj6 = zzil.zzm(bArr2, zzj6, zzik2);
                        zzkr.zzg(zzik2.zzb);
                    }
                    if (zzj6 == i21) {
                        return zzj6;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 0) {
                    zzkr zzkr2 = (zzkr) zzke;
                    int zzm2 = zzil.zzm(bArr2, i12, zzik2);
                    zzkr2.zzg(zzik2.zzb);
                    while (zzm2 < i13) {
                        int zzj7 = zzil.zzj(bArr2, zzm2, zzik2);
                        if (i14 != zzik2.zza) {
                            return zzm2;
                        }
                        zzm2 = zzil.zzm(bArr2, zzj7, zzik2);
                        zzkr2.zzg(zzik2.zzb);
                    }
                    return zzm2;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzil.zzf(bArr2, i12, zzke, zzik2);
                }
                if (i15 == 0) {
                    return zzil.zzl(i3, bArr, i, i2, zzke, zzik);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    zzkr zzkr3 = (zzkr) zzke;
                    int zzj8 = zzil.zzj(bArr2, i12, zzik2);
                    int i22 = zzik2.zza + zzj8;
                    while (zzj8 < i22) {
                        zzkr3.zzg(zzil.zzn(bArr2, zzj8));
                        zzj8 += 8;
                    }
                    if (zzj8 == i22) {
                        return zzj8;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 1) {
                    zzkr zzkr4 = (zzkr) zzke;
                    zzkr4.zzg(zzil.zzn(bArr, i));
                    int i23 = i12 + 8;
                    while (i23 < i13) {
                        int zzj9 = zzil.zzj(bArr2, i23, zzik2);
                        if (i14 != zzik2.zza) {
                            return i23;
                        }
                        zzkr4.zzg(zzil.zzn(bArr2, zzj9));
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
                    zzjy zzjy = (zzjy) zzke;
                    int zzj10 = zzil.zzj(bArr2, i12, zzik2);
                    int i24 = zzik2.zza + zzj10;
                    while (zzj10 < i24) {
                        zzjy.zzh(zzil.zzb(bArr2, zzj10));
                        zzj10 += 4;
                    }
                    if (zzj10 == i24) {
                        return zzj10;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 5) {
                    zzjy zzjy2 = (zzjy) zzke;
                    zzjy2.zzh(zzil.zzb(bArr, i));
                    int i25 = i12 + 4;
                    while (i25 < i13) {
                        int zzj11 = zzil.zzj(bArr2, i25, zzik2);
                        if (i14 != zzik2.zza) {
                            return i25;
                        }
                        zzjy2.zzh(zzil.zzb(bArr2, zzj11));
                        i25 = zzj11 + 4;
                    }
                    return i25;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    zzim zzim = (zzim) zzke;
                    int zzj12 = zzil.zzj(bArr2, i12, zzik2);
                    int i26 = zzik2.zza + zzj12;
                    while (zzj12 < i26) {
                        zzj12 = zzil.zzm(bArr2, zzj12, zzik2);
                        if (zzik2.zzb != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        zzim.zze(z3);
                    }
                    if (zzj12 == i26) {
                        return zzj12;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 0) {
                    zzim zzim2 = (zzim) zzke;
                    int zzm3 = zzil.zzm(bArr2, i12, zzik2);
                    if (zzik2.zzb != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zzim2.zze(z);
                    while (zzm3 < i13) {
                        int zzj13 = zzil.zzj(bArr2, zzm3, zzik2);
                        if (i14 != zzik2.zza) {
                            return zzm3;
                        }
                        zzm3 = zzil.zzm(bArr2, zzj13, zzik2);
                        if (zzik2.zzb != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zzim2.zze(z2);
                    }
                    return zzm3;
                }
                break;
            case 26:
                if (i15 == 2) {
                    if ((j & 536870912) == 0) {
                        int zzj14 = zzil.zzj(bArr2, i12, zzik2);
                        int i27 = zzik2.zza;
                        if (i27 >= 0) {
                            if (i27 == 0) {
                                zzke.add("");
                            } else {
                                zzke.add(new String(bArr2, zzj14, i27, zzkf.zzb));
                                zzj14 += i27;
                            }
                            while (i9 < i13) {
                                int zzj15 = zzil.zzj(bArr2, i9, zzik2);
                                if (i14 != zzik2.zza) {
                                    return i9;
                                }
                                i9 = zzil.zzj(bArr2, zzj15, zzik2);
                                int i28 = zzik2.zza;
                                if (i28 < 0) {
                                    throw zzkh.zzd();
                                } else if (i28 == 0) {
                                    zzke.add("");
                                } else {
                                    zzke.add(new String(bArr2, i9, i28, zzkf.zzb));
                                    i9 += i28;
                                }
                            }
                            return i9;
                        }
                        throw zzkh.zzd();
                    }
                    int zzj16 = zzil.zzj(bArr2, i12, zzik2);
                    int i29 = zzik2.zza;
                    if (i29 >= 0) {
                        if (i29 == 0) {
                            zzke.add("");
                        } else {
                            int i30 = zzj16 + i29;
                            if (zzmq.zzf(bArr2, zzj16, i30)) {
                                zzke.add(new String(bArr2, zzj16, i29, zzkf.zzb));
                                zzj16 = i30;
                            } else {
                                throw zzkh.zzc();
                            }
                        }
                        while (i8 < i13) {
                            int zzj17 = zzil.zzj(bArr2, i8, zzik2);
                            if (i14 != zzik2.zza) {
                                return i8;
                            }
                            i8 = zzil.zzj(bArr2, zzj17, zzik2);
                            int i31 = zzik2.zza;
                            if (i31 < 0) {
                                throw zzkh.zzd();
                            } else if (i31 == 0) {
                                zzke.add("");
                            } else {
                                int i32 = i8 + i31;
                                if (zzmq.zzf(bArr2, i8, i32)) {
                                    zzke.add(new String(bArr2, i8, i31, zzkf.zzb));
                                    i8 = i32;
                                } else {
                                    throw zzkh.zzc();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzkh.zzd();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zzil.zze(zzE(i16), i3, bArr, i, i2, zzke, zzik);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zzj18 = zzil.zzj(bArr2, i12, zzik2);
                    int i33 = zzik2.zza;
                    if (i33 < 0) {
                        throw zzkh.zzd();
                    } else if (i33 <= bArr2.length - zzj18) {
                        if (i33 == 0) {
                            zzke.add(zzix.zzb);
                        } else {
                            zzke.add(zzix.zzl(bArr2, zzj18, i33));
                            zzj18 += i33;
                        }
                        while (i10 < i13) {
                            int zzj19 = zzil.zzj(bArr2, i10, zzik2);
                            if (i14 != zzik2.zza) {
                                return i10;
                            }
                            i10 = zzil.zzj(bArr2, zzj19, zzik2);
                            int i34 = zzik2.zza;
                            if (i34 < 0) {
                                throw zzkh.zzd();
                            } else if (i34 > bArr2.length - i10) {
                                throw zzkh.zzf();
                            } else if (i34 == 0) {
                                zzke.add(zzix.zzb);
                            } else {
                                zzke.add(zzix.zzl(bArr2, i10, i34));
                                i10 += i34;
                            }
                        }
                        return i10;
                    } else {
                        throw zzkh.zzf();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzil.zzf(bArr2, i12, zzke, zzik2);
                } else if (i15 == 0) {
                    i11 = zzil.zzl(i3, bArr, i, i2, zzke, zzik);
                }
                zzjx zzjx = (zzjx) t2;
                zzmc zzmc = zzjx.zzc;
                if (zzmc == zzmc.zzc()) {
                    zzmc = null;
                }
                Object zzC = zzlp.zzC(i4, zzke, zzD(i16), zzmc, this.zzn);
                if (zzC == null) {
                    return i11;
                }
                zzjx.zzc = (zzmc) zzC;
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    zzjy zzjy3 = (zzjy) zzke;
                    int zzj20 = zzil.zzj(bArr2, i12, zzik2);
                    int i35 = zzik2.zza + zzj20;
                    while (zzj20 < i35) {
                        zzj20 = zzil.zzj(bArr2, zzj20, zzik2);
                        zzjy3.zzh(zzja.zzb(zzik2.zza));
                    }
                    if (zzj20 == i35) {
                        return zzj20;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 0) {
                    zzjy zzjy4 = (zzjy) zzke;
                    int zzj21 = zzil.zzj(bArr2, i12, zzik2);
                    zzjy4.zzh(zzja.zzb(zzik2.zza));
                    while (zzj21 < i13) {
                        int zzj22 = zzil.zzj(bArr2, zzj21, zzik2);
                        if (i14 != zzik2.zza) {
                            return zzj21;
                        }
                        zzj21 = zzil.zzj(bArr2, zzj22, zzik2);
                        zzjy4.zzh(zzja.zzb(zzik2.zza));
                    }
                    return zzj21;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    zzkr zzkr5 = (zzkr) zzke;
                    int zzj23 = zzil.zzj(bArr2, i12, zzik2);
                    int i36 = zzik2.zza + zzj23;
                    while (zzj23 < i36) {
                        zzj23 = zzil.zzm(bArr2, zzj23, zzik2);
                        zzkr5.zzg(zzja.zzc(zzik2.zzb));
                    }
                    if (zzj23 == i36) {
                        return zzj23;
                    }
                    throw zzkh.zzf();
                } else if (i15 == 0) {
                    zzkr zzkr6 = (zzkr) zzke;
                    int zzm4 = zzil.zzm(bArr2, i12, zzik2);
                    zzkr6.zzg(zzja.zzc(zzik2.zzb));
                    while (zzm4 < i13) {
                        int zzj24 = zzil.zzj(bArr2, zzm4, zzik2);
                        if (i14 != zzik2.zza) {
                            return zzm4;
                        }
                        zzm4 = zzil.zzm(bArr2, zzj24, zzik2);
                        zzkr6.zzg(zzja.zzc(zzik2.zzb));
                    }
                    return zzm4;
                }
                break;
            default:
                if (i15 == 3) {
                    zzln zzE = zzE(i16);
                    int i37 = (i14 & -8) | 4;
                    int zzc2 = zzil.zzc(zzE, bArr, i, i2, i37, zzik);
                    zzke.add(zzik2.zzc);
                    while (zzc2 < i13) {
                        int zzj25 = zzil.zzj(bArr2, zzc2, zzik2);
                        if (i14 != zzik2.zza) {
                            return zzc2;
                        }
                        zzc2 = zzil.zzc(zzE, bArr, zzj25, i2, i37, zzik);
                        zzke.add(zzik2.zzc);
                    }
                    return zzc2;
                }
                break;
        }
        return i12;
    }

    private final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    private final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private final int zzz(int i, int i2) {
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

    public final int zza(T t) {
        return this.zzi ? zzq(t) : zzp(t);
    }

    public final int zzb(T t) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzB = zzB(i2);
            int i3 = this.zzc[i2];
            long j = (long) (1048575 & zzB);
            int i4 = 37;
            switch (zzA(zzB)) {
                case 0:
                    i = (i * 53) + zzkf.zzc(Double.doubleToLongBits(zzml.zza(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzml.zzb(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzkf.zzc(zzml.zzd(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzkf.zzc(zzml.zzd(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzml.zzc(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzkf.zzc(zzml.zzd(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzml.zzc(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzkf.zza(zzml.zzw(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzml.zzf(t, j)).hashCode();
                    break;
                case 9:
                    Object zzf2 = zzml.zzf(t, j);
                    if (zzf2 != null) {
                        i4 = zzf2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzml.zzf(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzml.zzc(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzml.zzc(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzml.zzc(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzkf.zzc(zzml.zzd(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzml.zzc(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzkf.zzc(zzml.zzd(t, j));
                    break;
                case 17:
                    Object zzf3 = zzml.zzf(t, j);
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
                    i = (i * 53) + zzml.zzf(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzml.zzf(t, j).hashCode();
                    break;
                case 51:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zzc(Double.doubleToLongBits(zzn(t, j)));
                        break;
                    }
                case 52:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzo(t, j));
                        break;
                    }
                case 53:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zzc(zzC(t, j));
                        break;
                    }
                case 54:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zzc(zzC(t, j));
                        break;
                    }
                case 55:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzr(t, j);
                        break;
                    }
                case 56:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zzc(zzC(t, j));
                        break;
                    }
                case 57:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzr(t, j);
                        break;
                    }
                case 58:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zza(zzQ(t, j));
                        break;
                    }
                case 59:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzml.zzf(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzml.zzf(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzml.zzf(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzr(t, j);
                        break;
                    }
                case 63:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzr(t, j);
                        break;
                    }
                case 64:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzr(t, j);
                        break;
                    }
                case 65:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zzc(zzC(t, j));
                        break;
                    }
                case 66:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzr(t, j);
                        break;
                    }
                case 67:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzkf.zzc(zzC(t, j));
                        break;
                    }
                case 68:
                    if (!zzP(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzml.zzf(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzn.zzc(t).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzo.zza(t);
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v45, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v47, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(T r31, byte[] r32, int r33, int r34, int r35, com.google.android.gms.internal.measurement.zzik r36) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r36
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r0 = r33
            r1 = r16
            r3 = r1
            r5 = r3
            r2 = -1
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001a:
            r17 = 0
            if (r0 >= r13) goto L_0x0525
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002d
            int r0 = com.google.android.gms.internal.measurement.zzil.zzk(r0, r12, r1, r9)
            int r1 = r9.zza
            r4 = r1
            r1 = r0
            goto L_0x002e
        L_0x002d:
            r4 = r0
        L_0x002e:
            int r0 = r4 >>> 3
            r7 = r4 & 7
            r8 = 3
            if (r0 <= r2) goto L_0x003b
            int r3 = r3 / r8
            int r2 = r15.zzx(r0, r3)
            goto L_0x003f
        L_0x003b:
            int r2 = r15.zzw(r0)
        L_0x003f:
            r3 = -1
            if (r2 != r3) goto L_0x0051
            r33 = r0
            r2 = r1
            r25 = r3
            r22 = r4
            r20 = r5
            r28 = r10
            r18 = r16
            goto L_0x04b3
        L_0x0051:
            int[] r3 = r15.zzc
            int r20 = r2 + 1
            r3 = r3[r20]
            int r8 = zzA(r3)
            r20 = r0
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r3 & r18
            r22 = r3
            r21 = r4
            long r3 = (long) r0
            r0 = 17
            if (r8 > r0) goto L_0x0373
            int[] r0 = r15.zzc
            int r23 = r2 + 2
            r0 = r0[r23]
            int r23 = r0 >>> 20
            r11 = 1
            int r23 = r11 << r23
            r11 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r11
            if (r0 == r6) goto L_0x008a
            if (r6 == r11) goto L_0x0082
            long r11 = (long) r6
            r10.putInt(r14, r11, r5)
        L_0x0082:
            long r5 = (long) r0
            int r5 = r10.getInt(r14, r5)
            r11 = r0
            r6 = r5
            goto L_0x008c
        L_0x008a:
            r11 = r6
            r6 = r5
        L_0x008c:
            r0 = 5
            switch(r8) {
                case 0: goto L_0x0318;
                case 1: goto L_0x02ee;
                case 2: goto L_0x02be;
                case 3: goto L_0x02be;
                case 4: goto L_0x0297;
                case 5: goto L_0x0267;
                case 6: goto L_0x0240;
                case 7: goto L_0x020f;
                case 8: goto L_0x01dc;
                case 9: goto L_0x019e;
                case 10: goto L_0x0176;
                case 11: goto L_0x0297;
                case 12: goto L_0x012b;
                case 13: goto L_0x0240;
                case 14: goto L_0x0267;
                case 15: goto L_0x0100;
                case 16: goto L_0x00c0;
                default: goto L_0x0090;
            }
        L_0x0090:
            r12 = r32
            r8 = r2
            r19 = -1
            r2 = r1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            r0 = 3
            if (r7 != r0) goto L_0x0360
            com.google.android.gms.internal.measurement.zzln r0 = r15.zzE(r8)
            int r1 = r21 << 3
            r5 = r1 | 4
            r1 = r32
            r12 = r3
            r3 = r34
            r4 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zzc(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r23
            if (r1 != 0) goto L_0x0343
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r12, r1)
            goto L_0x0350
        L_0x00c0:
            if (r7 != 0) goto L_0x00f0
            r12 = r32
            int r7 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r1, r9)
            long r0 = r9.zzb
            long r24 = com.google.android.gms.internal.measurement.zzja.zzc(r0)
            r8 = r20
            r0 = r10
            r1 = r31
            r5 = r2
            r19 = -1
            r2 = r3
            r20 = r11
            r11 = r21
            r21 = r8
            r8 = r5
            r4 = r24
            r0.putLong(r1, r2, r4)
            r5 = r6 | r23
            r0 = r7
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x00f0:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            r2 = r1
            goto L_0x0361
        L_0x0100:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != 0) goto L_0x0128
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r1, r9)
            int r1 = r9.zza
            int r1 = com.google.android.gms.internal.measurement.zzja.zzb(r1)
            r10.putInt(r14, r3, r1)
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0128:
            r2 = r1
            goto L_0x0361
        L_0x012b:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != 0) goto L_0x0173
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r1, r9)
            int r1 = r9.zza
            com.google.android.gms.internal.measurement.zzkb r2 = r15.zzD(r8)
            if (r2 == 0) goto L_0x0164
            boolean r2 = r2.zza(r1)
            if (r2 == 0) goto L_0x014d
            goto L_0x0164
        L_0x014d:
            com.google.android.gms.internal.measurement.zzmc r2 = zzd(r31)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzh(r11, r1)
            r5 = r6
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0164:
            r10.putInt(r14, r3, r1)
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0173:
            r2 = r1
            goto L_0x0361
        L_0x0176:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            r0 = 2
            if (r7 != r0) goto L_0x019b
            int r0 = com.google.android.gms.internal.measurement.zzil.zza(r12, r1, r9)
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r3, r1)
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x019b:
            r2 = r1
            goto L_0x0361
        L_0x019e:
            r12 = r32
            r8 = r2
            r0 = 2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != r0) goto L_0x01d9
            com.google.android.gms.internal.measurement.zzln r0 = r15.zzE(r8)
            int r0 = com.google.android.gms.internal.measurement.zzil.zzd(r0, r12, r1, r13, r9)
            r1 = r6 & r23
            if (r1 != 0) goto L_0x01c0
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r3, r1)
            goto L_0x01cd
        L_0x01c0:
            java.lang.Object r1 = r10.getObject(r14, r3)
            java.lang.Object r2 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzkf.zzg(r1, r2)
            r10.putObject(r14, r3, r1)
        L_0x01cd:
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x01d9:
            r2 = r1
            goto L_0x0361
        L_0x01dc:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            r0 = 2
            if (r7 != r0) goto L_0x020c
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r22 & r0
            if (r0 != 0) goto L_0x01f7
            int r0 = com.google.android.gms.internal.measurement.zzil.zzg(r12, r1, r9)
            goto L_0x01fb
        L_0x01f7:
            int r0 = com.google.android.gms.internal.measurement.zzil.zzh(r12, r1, r9)
        L_0x01fb:
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r3, r1)
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x020c:
            r2 = r1
            goto L_0x0361
        L_0x020f:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != 0) goto L_0x023d
            int r0 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r1, r9)
            long r1 = r9.zzb
            r26 = 0
            int r1 = (r1 > r26 ? 1 : (r1 == r26 ? 0 : -1))
            if (r1 == 0) goto L_0x022c
            r1 = 1
            goto L_0x022e
        L_0x022c:
            r1 = r16
        L_0x022e:
            com.google.android.gms.internal.measurement.zzml.zzm(r14, r3, r1)
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x023d:
            r2 = r1
            goto L_0x0361
        L_0x0240:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != r0) goto L_0x0264
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r1)
            r10.putInt(r14, r3, r0)
            int r0 = r1 + 4
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0264:
            r2 = r1
            goto L_0x0361
        L_0x0267:
            r12 = r32
            r8 = r2
            r19 = -1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            r0 = 1
            if (r7 != r0) goto L_0x0293
            long r24 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r1)
            r0 = r10
            r7 = r1
            r1 = r31
            r2 = r3
            r4 = r24
            r0.putLong(r1, r2, r4)
            int r0 = r7 + 8
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0293:
            r7 = r1
            r2 = r7
            goto L_0x0361
        L_0x0297:
            r12 = r32
            r8 = r2
            r19 = -1
            r2 = r1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != 0) goto L_0x02bc
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r2, r9)
            int r1 = r9.zza
            r10.putInt(r14, r3, r1)
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x02bc:
            goto L_0x0361
        L_0x02be:
            r12 = r32
            r8 = r2
            r19 = -1
            r2 = r1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != 0) goto L_0x02ec
            int r7 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r2, r9)
            long r1 = r9.zzb
            r0 = r10
            r24 = r1
            r1 = r31
            r2 = r3
            r4 = r24
            r0.putLong(r1, r2, r4)
            r5 = r6 | r23
            r0 = r7
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x02ec:
            goto L_0x0361
        L_0x02ee:
            r12 = r32
            r8 = r2
            r19 = -1
            r2 = r1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            if (r7 != r0) goto L_0x0317
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r2)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.measurement.zzml.zzp(r14, r3, r0)
            int r0 = r2 + 4
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0317:
            goto L_0x0361
        L_0x0318:
            r12 = r32
            r8 = r2
            r19 = -1
            r2 = r1
            r29 = r20
            r20 = r11
            r11 = r21
            r21 = r29
            r0 = 1
            if (r7 != r0) goto L_0x0342
            long r0 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r2)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.measurement.zzml.zzo(r14, r3, r0)
            int r0 = r2 + 8
            r5 = r6 | r23
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0342:
            goto L_0x0361
        L_0x0343:
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzkf.zzg(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x0350:
            r5 = r6 | r23
            r12 = r32
            r13 = r34
            r3 = r8
            r1 = r11
            r6 = r20
            r2 = r21
            r11 = r35
            goto L_0x001a
        L_0x0360:
        L_0x0361:
            r18 = r8
            r28 = r10
            r22 = r11
            r25 = r19
            r33 = r21
            r29 = r20
            r20 = r6
            r6 = r29
            goto L_0x04b3
        L_0x0373:
            r12 = r3
            r3 = r8
            r4 = r20
            r11 = r21
            r19 = -1
            r8 = r2
            r2 = r1
            r0 = 27
            if (r3 != r0) goto L_0x03db
            r0 = 2
            if (r7 != r0) goto L_0x03c8
            java.lang.Object r0 = r10.getObject(r14, r12)
            com.google.android.gms.internal.measurement.zzke r0 = (com.google.android.gms.internal.measurement.zzke) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x03a3
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0399
            r1 = 10
            goto L_0x039a
        L_0x0399:
            int r1 = r1 + r1
        L_0x039a:
            com.google.android.gms.internal.measurement.zzke r0 = r0.zzd(r1)
            r10.putObject(r14, r12, r0)
            r7 = r0
            goto L_0x03a4
        L_0x03a3:
            r7 = r0
        L_0x03a4:
            com.google.android.gms.internal.measurement.zzln r0 = r15.zzE(r8)
            r1 = r11
            r3 = r2
            r2 = r32
            r12 = r4
            r4 = r34
            r20 = r5
            r5 = r7
            r21 = r6
            r6 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zze(r0, r1, r2, r3, r4, r5, r6)
            r13 = r34
            r3 = r8
            r2 = r12
            r5 = r20
            r6 = r21
            r12 = r32
            r11 = r35
            goto L_0x001a
        L_0x03c8:
            r3 = r2
            r12 = r4
            r20 = r5
            r21 = r6
            r15 = r3
            r18 = r8
            r28 = r10
            r22 = r11
            r33 = r12
            r25 = r19
            goto L_0x0479
        L_0x03db:
            r20 = r5
            r21 = r6
            r5 = r2
            r6 = r4
            r0 = 49
            if (r3 > r0) goto L_0x042e
            r4 = r22
            long r1 = (long) r4
            r0 = r30
            r22 = r1
            r1 = r31
            r2 = r32
            r24 = r3
            r3 = r5
            r4 = r34
            r15 = r5
            r5 = r11
            r33 = r6
            r18 = r8
            r25 = r19
            r19 = r24
            r28 = r10
            r9 = r22
            r22 = r11
            r11 = r19
            r14 = r36
            int r0 = r0.zzv(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x0429
            r15 = r30
            r14 = r31
            r12 = r32
            r2 = r33
            r13 = r34
            r11 = r35
            r9 = r36
            r3 = r18
            r5 = r20
            r6 = r21
            r1 = r22
            r10 = r28
            goto L_0x001a
        L_0x0429:
            r2 = r0
            r6 = r21
            goto L_0x04b3
        L_0x042e:
            r15 = r5
            r33 = r6
            r18 = r8
            r28 = r10
            r25 = r19
            r4 = r22
            r19 = r3
            r22 = r11
            r0 = 50
            r9 = r19
            if (r9 != r0) goto L_0x047d
            r0 = 2
            if (r7 != r0) goto L_0x0478
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r18
            r6 = r12
            r8 = r36
            int r0 = r0.zzs(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x0474
            r15 = r30
            r14 = r31
            r12 = r32
            r2 = r33
            r13 = r34
            r11 = r35
            r9 = r36
            r3 = r18
            r5 = r20
            r6 = r21
            r1 = r22
            r10 = r28
            goto L_0x001a
        L_0x0474:
            r2 = r0
            r6 = r21
            goto L_0x04b3
        L_0x0478:
        L_0x0479:
            r2 = r15
            r6 = r21
            goto L_0x04b3
        L_0x047d:
            r0 = r30
            r1 = r31
            r2 = r32
            r8 = r4
            r3 = r15
            r4 = r34
            r5 = r22
            r6 = r33
            r10 = r12
            r12 = r18
            r13 = r36
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x04b0
            r15 = r30
            r14 = r31
            r12 = r32
            r2 = r33
            r13 = r34
            r11 = r35
            r9 = r36
            r3 = r18
            r5 = r20
            r6 = r21
            r1 = r22
            r10 = r28
            goto L_0x001a
        L_0x04b0:
            r2 = r0
            r6 = r21
        L_0x04b3:
            r7 = r35
            r8 = r22
            if (r8 != r7) goto L_0x04c5
            if (r7 == 0) goto L_0x04c5
            r9 = r30
            r12 = r31
            r0 = r2
            r1 = r8
            r5 = r20
            goto L_0x052e
        L_0x04c5:
            r9 = r30
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x04fe
            r10 = r36
            com.google.android.gms.internal.measurement.zzjj r0 = r10.zzd
            com.google.android.gms.internal.measurement.zzjj r1 = com.google.android.gms.internal.measurement.zzjj.zza()
            if (r0 == r1) goto L_0x04f9
            com.google.android.gms.internal.measurement.zzlc r0 = r9.zzg
            com.google.android.gms.internal.measurement.zzjj r1 = r10.zzd
            r11 = r33
            com.google.android.gms.internal.measurement.zzjv r0 = r1.zzc(r0, r11)
            if (r0 != 0) goto L_0x04f3
            com.google.android.gms.internal.measurement.zzmc r4 = zzd(r31)
            r0 = r8
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zzi(r0, r1, r2, r3, r4, r5)
            r12 = r31
            goto L_0x0513
        L_0x04f3:
            r12 = r31
            r0 = r12
            com.google.android.gms.internal.measurement.zzju r0 = (com.google.android.gms.internal.measurement.zzju) r0
            throw r17
        L_0x04f9:
            r12 = r31
            r11 = r33
            goto L_0x0504
        L_0x04fe:
            r12 = r31
            r11 = r33
            r10 = r36
        L_0x0504:
            com.google.android.gms.internal.measurement.zzmc r4 = zzd(r31)
            r0 = r8
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zzi(r0, r1, r2, r3, r4, r5)
        L_0x0513:
            r13 = r34
            r1 = r8
            r15 = r9
            r9 = r10
            r2 = r11
            r14 = r12
            r3 = r18
            r5 = r20
            r10 = r28
            r12 = r32
            r11 = r7
            goto L_0x001a
        L_0x0525:
            r20 = r5
            r21 = r6
            r28 = r10
            r7 = r11
            r12 = r14
            r9 = r15
        L_0x052e:
            r2 = 1048575(0xfffff, float:1.469367E-39)
            if (r6 == r2) goto L_0x0539
            long r3 = (long) r6
            r6 = r28
            r6.putInt(r12, r3, r5)
        L_0x0539:
            int r3 = r9.zzk
        L_0x053b:
            int r4 = r9.zzl
            if (r3 >= r4) goto L_0x0566
            int[] r4 = r9.zzj
            r4 = r4[r3]
            int[] r5 = r9.zzc
            r5 = r5[r4]
            int r5 = r9.zzB(r4)
            r5 = r5 & r2
            long r5 = (long) r5
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzml.zzf(r12, r5)
            if (r5 != 0) goto L_0x0554
            goto L_0x055a
        L_0x0554:
            com.google.android.gms.internal.measurement.zzkb r6 = r9.zzD(r4)
            if (r6 != 0) goto L_0x055d
        L_0x055a:
            int r3 = r3 + 1
            goto L_0x053b
        L_0x055d:
            com.google.android.gms.internal.measurement.zzkw r5 = (com.google.android.gms.internal.measurement.zzkw) r5
            java.lang.Object r0 = r9.zzF(r4)
            com.google.android.gms.internal.measurement.zzkv r0 = (com.google.android.gms.internal.measurement.zzkv) r0
            throw r17
        L_0x0566:
            if (r7 != 0) goto L_0x0572
            r2 = r34
            if (r0 != r2) goto L_0x056d
            goto L_0x0578
        L_0x056d:
            com.google.android.gms.internal.measurement.zzkh r0 = com.google.android.gms.internal.measurement.zzkh.zze()
            throw r0
        L_0x0572:
            r2 = r34
            if (r0 > r2) goto L_0x0579
            if (r1 != r7) goto L_0x0579
        L_0x0578:
            return r0
        L_0x0579:
            com.google.android.gms.internal.measurement.zzkh r0 = com.google.android.gms.internal.measurement.zzkh.zze()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlf.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    public final T zze() {
        return ((zzjx) this.zzg).zzl(4, (Object) null, (Object) null);
    }

    public final void zzf(T t) {
        int i;
        int i2 = this.zzk;
        while (true) {
            i = this.zzl;
            if (i2 >= i) {
                break;
            }
            long zzB = (long) (zzB(this.zzj[i2]) & 1048575);
            Object zzf2 = zzml.zzf(t, zzB);
            if (zzf2 != null) {
                ((zzkw) zzf2).zzc();
                zzml.zzs(t, zzB, zzf2);
            }
            i2++;
        }
        int length = this.zzj.length;
        while (i < length) {
            this.zzm.zza(t, (long) this.zzj[i]);
            i++;
        }
        this.zzn.zzg(t);
        if (this.zzh) {
            this.zzo.zzb(t);
        }
    }

    public final void zzg(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzB = zzB(i);
                long j = (long) (1048575 & zzB);
                int i2 = this.zzc[i];
                switch (zzA(zzB)) {
                    case 0:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzo(t, j, zzml.zza(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 1:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzp(t, j, zzml.zzb(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 2:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzr(t, j, zzml.zzd(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 3:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzr(t, j, zzml.zzd(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 4:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzq(t, j, zzml.zzc(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 5:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzr(t, j, zzml.zzd(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 6:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzq(t, j, zzml.zzc(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 7:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzm(t, j, zzml.zzw(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 8:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzs(t, j, zzml.zzf(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 9:
                        zzH(t, t2, i);
                        break;
                    case 10:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzs(t, j, zzml.zzf(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 11:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzq(t, j, zzml.zzc(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 12:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzq(t, j, zzml.zzc(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 13:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzq(t, j, zzml.zzc(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 14:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzr(t, j, zzml.zzd(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 15:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzq(t, j, zzml.zzc(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 16:
                        if (!zzM(t2, i)) {
                            break;
                        } else {
                            zzml.zzr(t, j, zzml.zzd(t2, j));
                            zzJ(t, i);
                            break;
                        }
                    case 17:
                        zzH(t, t2, i);
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
                        this.zzm.zzb(t, t2, j);
                        break;
                    case 50:
                        zzlp.zzI(this.zzq, t, t2, j);
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
                        if (!zzP(t2, i2, i)) {
                            break;
                        } else {
                            zzml.zzs(t, j, zzml.zzf(t2, j));
                            zzK(t, i2, i);
                            break;
                        }
                    case 60:
                        zzI(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zzP(t2, i2, i)) {
                            break;
                        } else {
                            zzml.zzs(t, j, zzml.zzf(t2, j));
                            zzK(t, i2, i);
                            break;
                        }
                    case 68:
                        zzI(t, t2, i);
                        break;
                }
            }
            zzlp.zzF(this.zzn, t, t2);
            if (this.zzh) {
                zzlp.zzE(this.zzo, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    public final void zzh(T t, byte[] bArr, int i, int i2, zzik zzik) throws IOException {
        if (this.zzi) {
            zzu(t, bArr, i, i2, zzik);
        } else {
            zzc(t, bArr, i, i2, 0, zzik);
        }
    }

    public final boolean zzi(T t, T t2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzB = zzB(i);
            long j = (long) (zzB & 1048575);
            switch (zzA(zzB)) {
                case 0:
                    if (zzL(t, t2, i) && Double.doubleToLongBits(zzml.zza(t, j)) == Double.doubleToLongBits(zzml.zza(t2, j))) {
                        continue;
                    }
                case 1:
                    if (zzL(t, t2, i) && Float.floatToIntBits(zzml.zzb(t, j)) == Float.floatToIntBits(zzml.zzb(t2, j))) {
                        continue;
                    }
                case 2:
                    if (zzL(t, t2, i) && zzml.zzd(t, j) == zzml.zzd(t2, j)) {
                        continue;
                    }
                case 3:
                    if (zzL(t, t2, i) && zzml.zzd(t, j) == zzml.zzd(t2, j)) {
                        continue;
                    }
                case 4:
                    if (zzL(t, t2, i) && zzml.zzc(t, j) == zzml.zzc(t2, j)) {
                        continue;
                    }
                case 5:
                    if (zzL(t, t2, i) && zzml.zzd(t, j) == zzml.zzd(t2, j)) {
                        continue;
                    }
                case 6:
                    if (zzL(t, t2, i) && zzml.zzc(t, j) == zzml.zzc(t2, j)) {
                        continue;
                    }
                case 7:
                    if (zzL(t, t2, i) && zzml.zzw(t, j) == zzml.zzw(t2, j)) {
                        continue;
                    }
                case 8:
                    if (zzL(t, t2, i) && zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        continue;
                    }
                case 9:
                    if (zzL(t, t2, i) && zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        continue;
                    }
                case 10:
                    if (zzL(t, t2, i) && zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        continue;
                    }
                case 11:
                    if (zzL(t, t2, i) && zzml.zzc(t, j) == zzml.zzc(t2, j)) {
                        continue;
                    }
                case 12:
                    if (zzL(t, t2, i) && zzml.zzc(t, j) == zzml.zzc(t2, j)) {
                        continue;
                    }
                case 13:
                    if (zzL(t, t2, i) && zzml.zzc(t, j) == zzml.zzc(t2, j)) {
                        continue;
                    }
                case 14:
                    if (zzL(t, t2, i) && zzml.zzd(t, j) == zzml.zzd(t2, j)) {
                        continue;
                    }
                case 15:
                    if (zzL(t, t2, i) && zzml.zzc(t, j) == zzml.zzc(t2, j)) {
                        continue;
                    }
                case 16:
                    if (zzL(t, t2, i) && zzml.zzd(t, j) == zzml.zzd(t2, j)) {
                        continue;
                    }
                case 17:
                    if (zzL(t, t2, i) && zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
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
                    z = zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j));
                    break;
                case 50:
                    z = zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j));
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
                    long zzy = (long) (zzy(i) & 1048575);
                    if (zzml.zzc(t, zzy) == zzml.zzc(t2, zzy) && zzlp.zzH(zzml.zzf(t, j), zzml.zzf(t2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzn.zzc(t).equals(this.zzn.zzc(t2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(t);
        this.zzo.zza(t2);
        throw null;
    }

    public final boolean zzj(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzk) {
            int i6 = this.zzj[i5];
            int i7 = this.zzc[i6];
            int zzB = zzB(i6);
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
            if ((268435456 & zzB) != 0 && !zzN(t, i6, i2, i, i10)) {
                return false;
            }
            switch (zzA(zzB)) {
                case 9:
                case 17:
                    if (zzN(t, i6, i2, i, i10) && !zzO(t2, zzB, zzE(i6))) {
                        return false;
                    }
                case 27:
                case 49:
                    List list = (List) zzml.zzf(t2, (long) (zzB & 1048575));
                    if (!list.isEmpty()) {
                        zzln zzE = zzE(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzE.zzj(list.get(i11))) {
                                return false;
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                case 50:
                    if (((zzkw) zzml.zzf(t2, (long) (zzB & 1048575))).isEmpty()) {
                        break;
                    } else {
                        zzkv zzkv = (zzkv) zzF(i6);
                        throw null;
                    }
                case 60:
                case 68:
                    if (zzP(t2, i7, i6) && !zzO(t2, zzB, zzE(i6))) {
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
        this.zzo.zza(t2);
        throw null;
    }

    public final void zzm(T t, zzjf zzjf) throws IOException {
        if (!this.zzi) {
            zzR(t, zzjf);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzB = zzB(i);
                int i2 = this.zzc[i];
                switch (zzA(zzB)) {
                    case 0:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzf(i2, zzml.zza(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzo(i2, zzml.zzb(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzt(i2, zzml.zzd(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzJ(i2, zzml.zzd(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzr(i2, zzml.zzc(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzm(i2, zzml.zzd(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzk(i2, zzml.zzc(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzb(i2, zzml.zzw(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzT(i2, zzml.zzf(t, (long) (zzB & 1048575)), zzjf);
                            break;
                        }
                    case 9:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzv(i2, zzml.zzf(t, (long) (zzB & 1048575)), zzE(i));
                            break;
                        }
                    case 10:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzd(i2, (zzix) zzml.zzf(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzH(i2, zzml.zzc(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzi(i2, zzml.zzc(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzw(i2, zzml.zzc(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzy(i2, zzml.zzd(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzA(i2, zzml.zzc(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzC(i2, zzml.zzd(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzM(t, i)) {
                            break;
                        } else {
                            zzjf.zzq(i2, zzml.zzf(t, (long) (zzB & 1048575)), zzE(i));
                            break;
                        }
                    case 18:
                        zzlp.zzL(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 19:
                        zzlp.zzP(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 20:
                        zzlp.zzS(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 21:
                        zzlp.zzaa(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 22:
                        zzlp.zzR(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 23:
                        zzlp.zzO(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 24:
                        zzlp.zzN(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 25:
                        zzlp.zzJ(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 26:
                        zzlp.zzY(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf);
                        break;
                    case 27:
                        zzlp.zzT(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, zzE(i));
                        break;
                    case 28:
                        zzlp.zzK(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf);
                        break;
                    case 29:
                        zzlp.zzZ(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 30:
                        zzlp.zzM(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 31:
                        zzlp.zzU(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 32:
                        zzlp.zzV(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 33:
                        zzlp.zzW(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 34:
                        zzlp.zzX(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, false);
                        break;
                    case 35:
                        zzlp.zzL(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 36:
                        zzlp.zzP(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 37:
                        zzlp.zzS(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 38:
                        zzlp.zzaa(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 39:
                        zzlp.zzR(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 40:
                        zzlp.zzO(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 41:
                        zzlp.zzN(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 42:
                        zzlp.zzJ(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 43:
                        zzlp.zzZ(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 44:
                        zzlp.zzM(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 45:
                        zzlp.zzU(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 46:
                        zzlp.zzV(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 47:
                        zzlp.zzW(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 48:
                        zzlp.zzX(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, true);
                        break;
                    case 49:
                        zzlp.zzQ(this.zzc[i], (List) zzml.zzf(t, (long) (zzB & 1048575)), zzjf, zzE(i));
                        break;
                    case 50:
                        zzS(zzjf, i2, zzml.zzf(t, (long) (zzB & 1048575)), i);
                        break;
                    case 51:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzf(i2, zzn(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzo(i2, zzo(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzt(i2, zzC(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzJ(i2, zzC(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzr(i2, zzr(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzm(i2, zzC(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzk(i2, zzr(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 58:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzb(i2, zzQ(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzT(i2, zzml.zzf(t, (long) (zzB & 1048575)), zzjf);
                            break;
                        }
                    case 60:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzv(i2, zzml.zzf(t, (long) (zzB & 1048575)), zzE(i));
                            break;
                        }
                    case 61:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzd(i2, (zzix) zzml.zzf(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 62:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzH(i2, zzr(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 63:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzi(i2, zzr(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzw(i2, zzr(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzy(i2, zzC(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 66:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzA(i2, zzr(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzC(i2, zzC(t, (long) (zzB & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzP(t, i2, i)) {
                            break;
                        } else {
                            zzjf.zzq(i2, zzml.zzf(t, (long) (zzB & 1048575)), zzE(i));
                            break;
                        }
                }
            }
            zzmb<?, ?> zzmb = this.zzn;
            zzmb.zzi(zzmb.zzc(t), zzjf);
        } else {
            this.zzo.zza(t);
            throw null;
        }
    }
}
