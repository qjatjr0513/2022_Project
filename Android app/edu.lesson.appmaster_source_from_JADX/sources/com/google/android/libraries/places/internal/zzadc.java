package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzadc<T> implements zzadk<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzael.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzacz zze;
    private final boolean zzf;
    private final boolean zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzacn zzk;
    private final zzaeb<?, ?> zzl;
    private final zzabi<?> zzm;
    private final zzade zzn;
    private final zzacu zzo;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.google.android.libraries.places.internal.zzacz} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: com.google.android.libraries.places.internal.zzade} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.libraries.places.internal.zzacu} */
    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.libraries.places.internal.zzabi<?>, com.google.android.libraries.places.internal.zzabi] */
    /* JADX WARNING: type inference failed for: r3v6, types: [int] */
    /* JADX WARNING: type inference failed for: r3v9, types: [com.google.android.libraries.places.internal.zzacn] */
    /* JADX WARNING: type inference failed for: r3v10, types: [com.google.android.libraries.places.internal.zzaeb<?, ?>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzadc(int[] r6, int[] r7, java.lang.Object[] r8, int r9, int r10, com.google.android.libraries.places.internal.zzacz r11, boolean r12, boolean r13, int[] r14, int r15, int r16, com.google.android.libraries.places.internal.zzade r17, com.google.android.libraries.places.internal.zzacn r18, com.google.android.libraries.places.internal.zzaeb<?, ?> r19, com.google.android.libraries.places.internal.zzabi<?> r20, com.google.android.libraries.places.internal.zzacu r21) {
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
            r3 = r11
            r0.zzg = r3
            r3 = 0
            if (r2 == 0) goto L_0x001a
            boolean r4 = r2.zzc(r10)
            if (r4 == 0) goto L_0x001a
            r3 = 1
        L_0x001a:
            r0.zzf = r3
            r3 = r13
            r0.zzh = r3
            r3 = r14
            r0.zzi = r3
            r3 = r15
            r0.zzj = r3
            r3 = r16
            r0.zzn = r3
            r3 = r17
            r0.zzk = r3
            r3 = r18
            r0.zzl = r3
            r0.zzm = r2
            r0.zze = r1
            r1 = r20
            r0.zzo = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzadc.<init>(int[], java.lang.Object[], int, int, com.google.android.libraries.places.internal.zzacz, boolean, boolean, int[], int, int, com.google.android.libraries.places.internal.zzade, com.google.android.libraries.places.internal.zzacn, com.google.android.libraries.places.internal.zzaeb, com.google.android.libraries.places.internal.zzabi, com.google.android.libraries.places.internal.zzacu, byte[]):void");
    }

    private final boolean zzA(T t, int i) {
        int zzo2 = zzo(i);
        long j = (long) (zzo2 & 1048575);
        if (j != 1048575) {
            return (zzael.zzc(t, j) & (1 << (zzo2 >>> 20))) != 0;
        }
        int zzq = zzq(i);
        long j2 = (long) (zzq & 1048575);
        switch (zzp(zzq)) {
            case 0:
                return zzael.zza(t, j2) != 0.0d;
            case 1:
                return zzael.zzb(t, j2) != 0.0f;
            case 2:
                return zzael.zzd(t, j2) != 0;
            case 3:
                return zzael.zzd(t, j2) != 0;
            case 4:
                return zzael.zzc(t, j2) != 0;
            case 5:
                return zzael.zzd(t, j2) != 0;
            case 6:
                return zzael.zzc(t, j2) != 0;
            case 7:
                return zzael.zzw(t, j2);
            case 8:
                Object zzf2 = zzael.zzf(t, j2);
                if (zzf2 instanceof String) {
                    return !((String) zzf2).isEmpty();
                }
                if (zzf2 instanceof zzaax) {
                    return !zzaax.zzb.equals(zzf2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzael.zzf(t, j2) != null;
            case 10:
                return !zzaax.zzb.equals(zzael.zzf(t, j2));
            case 11:
                return zzael.zzc(t, j2) != 0;
            case 12:
                return zzael.zzc(t, j2) != 0;
            case 13:
                return zzael.zzc(t, j2) != 0;
            case 14:
                return zzael.zzd(t, j2) != 0;
            case 15:
                return zzael.zzc(t, j2) != 0;
            case 16:
                return zzael.zzd(t, j2) != 0;
            case 17:
                return zzael.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzB(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzA(t, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzC(Object obj, int i, zzadk zzadk) {
        return zzadk.zzf(zzael.zzf(obj, (long) (i & 1048575)));
    }

    private final boolean zzD(T t, int i, int i2) {
        return zzael.zzc(t, (long) (zzo(i2) & 1048575)) == i;
    }

    private static <T> boolean zzE(T t, long j) {
        return ((Boolean) zzael.zzf(t, j)).booleanValue();
    }

    private final void zzF(T t, zzabg zzabg) throws IOException {
        int i;
        T t2 = t;
        zzabg zzabg2 = zzabg;
        if (!this.zzf) {
            int length = this.zzc.length;
            Unsafe unsafe = zzb;
            int i2 = 1048575;
            int i3 = 1048575;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                int zzq = zzq(i4);
                int i6 = this.zzc[i4];
                int zzp = zzp(zzq);
                if (zzp <= 17) {
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
                long j = (long) (zzq & i2);
                switch (zzp) {
                    case 0:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzf(i6, zzael.zza(t2, j));
                            break;
                        }
                    case 1:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzn(i6, zzael.zzb(t2, j));
                            break;
                        }
                    case 2:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzs(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 3:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzH(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 4:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzq(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 5:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzl(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 6:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzj(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 7:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzb(i6, zzael.zzw(t2, j));
                            break;
                        }
                    case 8:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzH(i6, unsafe.getObject(t2, j), zzabg2);
                            break;
                        }
                    case 9:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzu(i6, unsafe.getObject(t2, j), zzs(i4));
                            break;
                        }
                    case 10:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzd(i6, (zzaax) unsafe.getObject(t2, j));
                            break;
                        }
                    case 11:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzF(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 12:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzh(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 13:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzv(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 14:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzx(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 15:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzz(i6, unsafe.getInt(t2, j));
                            break;
                        }
                    case 16:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzB(i6, unsafe.getLong(t2, j));
                            break;
                        }
                    case 17:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzabg2.zzp(i6, unsafe.getObject(t2, j), zzs(i4));
                            break;
                        }
                    case 18:
                        zzadm.zzJ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 19:
                        zzadm.zzN(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 20:
                        zzadm.zzQ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 21:
                        zzadm.zzY(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 22:
                        zzadm.zzP(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 23:
                        zzadm.zzM(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 24:
                        zzadm.zzL(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 25:
                        zzadm.zzH(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 26:
                        zzadm.zzW(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2);
                        break;
                    case 27:
                        zzadm.zzR(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, zzs(i4));
                        break;
                    case 28:
                        zzadm.zzI(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2);
                        break;
                    case 29:
                        zzadm.zzX(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 30:
                        zzadm.zzK(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 31:
                        zzadm.zzS(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 32:
                        zzadm.zzT(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 33:
                        zzadm.zzU(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 34:
                        zzadm.zzV(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, false);
                        break;
                    case 35:
                        zzadm.zzJ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 36:
                        zzadm.zzN(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 37:
                        zzadm.zzQ(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 38:
                        zzadm.zzY(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 39:
                        zzadm.zzP(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 40:
                        zzadm.zzM(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 41:
                        zzadm.zzL(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 42:
                        zzadm.zzH(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 43:
                        zzadm.zzX(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 44:
                        zzadm.zzK(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 45:
                        zzadm.zzS(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 46:
                        zzadm.zzT(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 47:
                        zzadm.zzU(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 48:
                        zzadm.zzV(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, true);
                        break;
                    case 49:
                        zzadm.zzO(this.zzc[i4], (List) unsafe.getObject(t2, j), zzabg2, zzs(i4));
                        break;
                    case 50:
                        zzG(zzabg2, i6, unsafe.getObject(t2, j), i4);
                        break;
                    case 51:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzf(i6, zzj(t2, j));
                            break;
                        }
                    case 52:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzn(i6, zzk(t2, j));
                            break;
                        }
                    case 53:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzs(i6, zzr(t2, j));
                            break;
                        }
                    case 54:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzH(i6, zzr(t2, j));
                            break;
                        }
                    case 55:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzq(i6, zzn(t2, j));
                            break;
                        }
                    case 56:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzl(i6, zzr(t2, j));
                            break;
                        }
                    case 57:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzj(i6, zzn(t2, j));
                            break;
                        }
                    case 58:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzb(i6, zzE(t2, j));
                            break;
                        }
                    case 59:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzH(i6, unsafe.getObject(t2, j), zzabg2);
                            break;
                        }
                    case 60:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzu(i6, unsafe.getObject(t2, j), zzs(i4));
                            break;
                        }
                    case 61:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzd(i6, (zzaax) unsafe.getObject(t2, j));
                            break;
                        }
                    case 62:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzF(i6, zzn(t2, j));
                            break;
                        }
                    case 63:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzh(i6, zzn(t2, j));
                            break;
                        }
                    case 64:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzv(i6, zzn(t2, j));
                            break;
                        }
                    case 65:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzx(i6, zzr(t2, j));
                            break;
                        }
                    case 66:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzz(i6, zzn(t2, j));
                            break;
                        }
                    case 67:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzB(i6, zzr(t2, j));
                            break;
                        }
                    case 68:
                        if (!zzD(t2, i6, i4)) {
                            break;
                        } else {
                            zzabg2.zzp(i6, unsafe.getObject(t2, j), zzs(i4));
                            break;
                        }
                }
                i4 += 3;
                i2 = 1048575;
            }
            zzaeb<?, ?> zzaeb = this.zzl;
            zzaeb.zzg(zzaeb.zzc(t2), zzabg2);
            return;
        }
        this.zzm.zza(t2);
        throw null;
    }

    private final <K, V> void zzG(zzabg zzabg, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzacs zzacs = (zzacs) zzt(i2);
            throw null;
        }
    }

    private static final void zzH(int i, Object obj, zzabg zzabg) throws IOException {
        if (obj instanceof String) {
            zzabg.zzD(i, (String) obj);
        } else {
            zzabg.zzd(i, (zzaax) obj);
        }
    }

    static <T> zzadc<T> zzg(Class<T> cls, zzacw zzacw, zzade zzade, zzacn zzacn, zzaeb<?, ?> zzaeb, zzabi<?> zzabi, zzacu zzacu) {
        if (zzacw instanceof zzadj) {
            return zzh((zzadj) zzacw, zzade, zzacn, zzaeb, zzabi, zzacu);
        }
        zzady zzady = (zzady) zzacw;
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:158:0x034b  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x03ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.libraries.places.internal.zzadc<T> zzh(com.google.android.libraries.places.internal.zzadj r34, com.google.android.libraries.places.internal.zzade r35, com.google.android.libraries.places.internal.zzacn r36, com.google.android.libraries.places.internal.zzaeb<?, ?> r37, com.google.android.libraries.places.internal.zzabi<?> r38, com.google.android.libraries.places.internal.zzacu r39) {
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
            com.google.android.libraries.places.internal.zzacz r18 = r34.zza()
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
            java.lang.reflect.Field r12 = zzu(r1, r12)
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
            java.lang.reflect.Field r8 = zzu(r1, r8)
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
            java.lang.reflect.Field r8 = zzu(r1, r8)
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
            java.lang.reflect.Field r11 = zzu(r1, r11)
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
            com.google.android.libraries.places.internal.zzadc r0 = new com.google.android.libraries.places.internal.zzadc
            r4 = r0
            com.google.android.libraries.places.internal.zzacz r9 = r34.zza()
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzadc.zzh(com.google.android.libraries.places.internal.zzadj, com.google.android.libraries.places.internal.zzade, com.google.android.libraries.places.internal.zzacn, com.google.android.libraries.places.internal.zzaeb, com.google.android.libraries.places.internal.zzabi, com.google.android.libraries.places.internal.zzacu):com.google.android.libraries.places.internal.zzadc");
    }

    private static <T> double zzj(T t, long j) {
        return ((Double) zzael.zzf(t, j)).doubleValue();
    }

    private static <T> float zzk(T t, long j) {
        return ((Float) zzael.zzf(t, j)).floatValue();
    }

    private final int zzl(T t) {
        int i;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzc.length; i5 += 3) {
            int zzq = zzq(i5);
            int i6 = this.zzc[i5];
            int zzp = zzp(zzq);
            if (zzp <= 17) {
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
            long j = (long) (zzq & 1048575);
            switch (zzp) {
                case 0:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 8;
                        break;
                    }
                case 1:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 4;
                        break;
                    }
                case 2:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzB(unsafe.getLong(t, j));
                        break;
                    }
                case 3:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzB(unsafe.getLong(t, j));
                        break;
                    }
                case 4:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzv(unsafe.getInt(t, j));
                        break;
                    }
                case 5:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 8;
                        break;
                    }
                case 6:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 4;
                        break;
                    }
                case 7:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 1;
                        break;
                    }
                case 8:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(t, j);
                        if (!(object instanceof zzaax)) {
                            i3 += zzabf.zzA(i6 << 3) + zzabf.zzy((String) object);
                            break;
                        } else {
                            int zzA = zzabf.zzA(i6 << 3);
                            int zzd2 = ((zzaax) object).zzd();
                            i3 += zzA + zzabf.zzA(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzadm.zzo(i6, unsafe.getObject(t, j), zzs(i5));
                        break;
                    }
                case 10:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        int zzA2 = zzabf.zzA(i6 << 3);
                        int zzd3 = ((zzaax) unsafe.getObject(t, j)).zzd();
                        i3 += zzA2 + zzabf.zzA(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzA(unsafe.getInt(t, j));
                        break;
                    }
                case 12:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzv(unsafe.getInt(t, j));
                        break;
                    }
                case 13:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 4;
                        break;
                    }
                case 14:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 8;
                        break;
                    }
                case 15:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        int i9 = unsafe.getInt(t, j);
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzA((i9 >> 31) ^ (i9 + i9));
                        break;
                    }
                case 16:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(t, j);
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzB((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += zzabf.zzu(i6, (zzacz) unsafe.getObject(t, j), zzs(i5));
                        break;
                    }
                case 18:
                    i3 += zzadm.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 19:
                    i3 += zzadm.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 20:
                    i3 += zzadm.zzm(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 21:
                    i3 += zzadm.zzx(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 22:
                    i3 += zzadm.zzk(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 23:
                    i3 += zzadm.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 24:
                    i3 += zzadm.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 25:
                    i3 += zzadm.zza(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 26:
                    i3 += zzadm.zzu(i6, (List) unsafe.getObject(t, j));
                    break;
                case 27:
                    i3 += zzadm.zzp(i6, (List) unsafe.getObject(t, j), zzs(i5));
                    break;
                case 28:
                    i3 += zzadm.zzc(i6, (List) unsafe.getObject(t, j));
                    break;
                case 29:
                    i3 += zzadm.zzv(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 30:
                    i3 += zzadm.zzd(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 31:
                    i3 += zzadm.zzf(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 32:
                    i3 += zzadm.zzh(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 33:
                    i3 += zzadm.zzq(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 34:
                    i3 += zzadm.zzs(i6, (List) unsafe.getObject(t, j), false);
                    break;
                case 35:
                    int zzi2 = zzadm.zzi((List) unsafe.getObject(t, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzadm.zzg((List) unsafe.getObject(t, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzadm.zzn((List) unsafe.getObject(t, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzadm.zzy((List) unsafe.getObject(t, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzadm.zzl((List) unsafe.getObject(t, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzadm.zzi((List) unsafe.getObject(t, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzadm.zzg((List) unsafe.getObject(t, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzadm.zzb((List) unsafe.getObject(t, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzadm.zzw((List) unsafe.getObject(t, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzadm.zze((List) unsafe.getObject(t, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzadm.zzg((List) unsafe.getObject(t, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzadm.zzi((List) unsafe.getObject(t, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr = zzadm.zzr((List) unsafe.getObject(t, j));
                    if (zzr <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzr) + zzr;
                        break;
                    }
                case 48:
                    int zzt = zzadm.zzt((List) unsafe.getObject(t, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i3 += zzabf.zzz(i6) + zzabf.zzA(zzt) + zzt;
                        break;
                    }
                case 49:
                    i3 += zzadm.zzj(i6, (List) unsafe.getObject(t, j), zzs(i5));
                    break;
                case 50:
                    zzacu.zza(i6, unsafe.getObject(t, j), zzt(i5));
                    break;
                case 51:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzB(zzr(t, j));
                        break;
                    }
                case 54:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzB(zzr(t, j));
                        break;
                    }
                case 55:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzv(zzn(t, j));
                        break;
                    }
                case 56:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(t, j);
                        if (!(object2 instanceof zzaax)) {
                            i3 += zzabf.zzA(i6 << 3) + zzabf.zzy((String) object2);
                            break;
                        } else {
                            int zzA3 = zzabf.zzA(i6 << 3);
                            int zzd4 = ((zzaax) object2).zzd();
                            i3 += zzA3 + zzabf.zzA(zzd4) + zzd4;
                            break;
                        }
                    }
                case 60:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzadm.zzo(i6, unsafe.getObject(t, j), zzs(i5));
                        break;
                    }
                case 61:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        int zzA4 = zzabf.zzA(i6 << 3);
                        int zzd5 = ((zzaax) unsafe.getObject(t, j)).zzd();
                        i3 += zzA4 + zzabf.zzA(zzd5) + zzd5;
                        break;
                    }
                case 62:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzA(zzn(t, j));
                        break;
                    }
                case 63:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzv(zzn(t, j));
                        break;
                    }
                case 64:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzA(i6 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        int zzn3 = zzn(t, j);
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzA((zzn3 >> 31) ^ (zzn3 + zzn3));
                        break;
                    }
                case 67:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        long zzr2 = zzr(t, j);
                        i3 += zzabf.zzA(i6 << 3) + zzabf.zzB((zzr2 >> 63) ^ (zzr2 + zzr2));
                        break;
                    }
                case 68:
                    if (!zzD(t, i6, i5)) {
                        break;
                    } else {
                        i3 += zzabf.zzu(i6, (zzacz) unsafe.getObject(t, j), zzs(i5));
                        break;
                    }
            }
        }
        zzaeb<?, ?> zzaeb = this.zzl;
        int zza2 = i3 + zzaeb.zza(zzaeb.zzc(t));
        if (!this.zzf) {
            return zza2;
        }
        this.zzm.zza(t);
        throw null;
    }

    private final int zzm(T t) {
        Unsafe unsafe = zzb;
        int i = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzq = zzq(i2);
            int zzp = zzp(zzq);
            int i3 = this.zzc[i2];
            long j = (long) (zzq & 1048575);
            if (zzp >= zzabn.DOUBLE_LIST_PACKED.zza() && zzp <= zzabn.SINT64_LIST_PACKED.zza()) {
                int i4 = this.zzc[i2 + 2];
            }
            switch (zzp) {
                case 0:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 8;
                        break;
                    }
                case 1:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 4;
                        break;
                    }
                case 2:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzB(zzael.zzd(t, j));
                        break;
                    }
                case 3:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzB(zzael.zzd(t, j));
                        break;
                    }
                case 4:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzv(zzael.zzc(t, j));
                        break;
                    }
                case 5:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 8;
                        break;
                    }
                case 6:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 4;
                        break;
                    }
                case 7:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 1;
                        break;
                    }
                case 8:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        Object zzf2 = zzael.zzf(t, j);
                        if (!(zzf2 instanceof zzaax)) {
                            i += zzabf.zzA(i3 << 3) + zzabf.zzy((String) zzf2);
                            break;
                        } else {
                            int zzA = zzabf.zzA(i3 << 3);
                            int zzd2 = ((zzaax) zzf2).zzd();
                            i += zzA + zzabf.zzA(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzadm.zzo(i3, zzael.zzf(t, j), zzs(i2));
                        break;
                    }
                case 10:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        int zzA2 = zzabf.zzA(i3 << 3);
                        int zzd3 = ((zzaax) zzael.zzf(t, j)).zzd();
                        i += zzA2 + zzabf.zzA(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzA(zzael.zzc(t, j));
                        break;
                    }
                case 12:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzv(zzael.zzc(t, j));
                        break;
                    }
                case 13:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 4;
                        break;
                    }
                case 14:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 8;
                        break;
                    }
                case 15:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        int zzc2 = zzael.zzc(t, j);
                        i += zzabf.zzA(i3 << 3) + zzabf.zzA((zzc2 >> 31) ^ (zzc2 + zzc2));
                        break;
                    }
                case 16:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        long zzd4 = zzael.zzd(t, j);
                        i += zzabf.zzA(i3 << 3) + zzabf.zzB((zzd4 >> 63) ^ (zzd4 + zzd4));
                        break;
                    }
                case 17:
                    if (!zzA(t, i2)) {
                        break;
                    } else {
                        i += zzabf.zzu(i3, (zzacz) zzael.zzf(t, j), zzs(i2));
                        break;
                    }
                case 18:
                    i += zzadm.zzh(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 19:
                    i += zzadm.zzf(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 20:
                    i += zzadm.zzm(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 21:
                    i += zzadm.zzx(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 22:
                    i += zzadm.zzk(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 23:
                    i += zzadm.zzh(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 24:
                    i += zzadm.zzf(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 25:
                    i += zzadm.zza(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 26:
                    i += zzadm.zzu(i3, (List) zzael.zzf(t, j));
                    break;
                case 27:
                    i += zzadm.zzp(i3, (List) zzael.zzf(t, j), zzs(i2));
                    break;
                case 28:
                    i += zzadm.zzc(i3, (List) zzael.zzf(t, j));
                    break;
                case 29:
                    i += zzadm.zzv(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 30:
                    i += zzadm.zzd(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 31:
                    i += zzadm.zzf(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 32:
                    i += zzadm.zzh(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 33:
                    i += zzadm.zzq(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 34:
                    i += zzadm.zzs(i3, (List) zzael.zzf(t, j), false);
                    break;
                case 35:
                    int zzi2 = zzadm.zzi((List) unsafe.getObject(t, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzadm.zzg((List) unsafe.getObject(t, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzadm.zzn((List) unsafe.getObject(t, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzadm.zzy((List) unsafe.getObject(t, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzadm.zzl((List) unsafe.getObject(t, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzadm.zzi((List) unsafe.getObject(t, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzadm.zzg((List) unsafe.getObject(t, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzadm.zzb((List) unsafe.getObject(t, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzadm.zzw((List) unsafe.getObject(t, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzadm.zze((List) unsafe.getObject(t, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzadm.zzg((List) unsafe.getObject(t, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzadm.zzi((List) unsafe.getObject(t, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr = zzadm.zzr((List) unsafe.getObject(t, j));
                    if (zzr <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzr) + zzr;
                        break;
                    }
                case 48:
                    int zzt = zzadm.zzt((List) unsafe.getObject(t, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i += zzabf.zzz(i3) + zzabf.zzA(zzt) + zzt;
                        break;
                    }
                case 49:
                    i += zzadm.zzj(i3, (List) zzael.zzf(t, j), zzs(i2));
                    break;
                case 50:
                    zzacu.zza(i3, zzael.zzf(t, j), zzt(i2));
                    break;
                case 51:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzB(zzr(t, j));
                        break;
                    }
                case 54:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzB(zzr(t, j));
                        break;
                    }
                case 55:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzv(zzn(t, j));
                        break;
                    }
                case 56:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        Object zzf3 = zzael.zzf(t, j);
                        if (!(zzf3 instanceof zzaax)) {
                            i += zzabf.zzA(i3 << 3) + zzabf.zzy((String) zzf3);
                            break;
                        } else {
                            int zzA3 = zzabf.zzA(i3 << 3);
                            int zzd5 = ((zzaax) zzf3).zzd();
                            i += zzA3 + zzabf.zzA(zzd5) + zzd5;
                            break;
                        }
                    }
                case 60:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzadm.zzo(i3, zzael.zzf(t, j), zzs(i2));
                        break;
                    }
                case 61:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        int zzA4 = zzabf.zzA(i3 << 3);
                        int zzd6 = ((zzaax) zzael.zzf(t, j)).zzd();
                        i += zzA4 + zzabf.zzA(zzd6) + zzd6;
                        break;
                    }
                case 62:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzA(zzn(t, j));
                        break;
                    }
                case 63:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + zzabf.zzv(zzn(t, j));
                        break;
                    }
                case 64:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzA(i3 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        int zzn3 = zzn(t, j);
                        i += zzabf.zzA(i3 << 3) + zzabf.zzA((zzn3 >> 31) ^ (zzn3 + zzn3));
                        break;
                    }
                case 67:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        long zzr2 = zzr(t, j);
                        i += zzabf.zzA(i3 << 3) + zzabf.zzB((zzr2 >> 63) ^ (zzr2 + zzr2));
                        break;
                    }
                case 68:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i += zzabf.zzu(i3, (zzacz) zzael.zzf(t, j), zzs(i2));
                        break;
                    }
            }
        }
        zzaeb<?, ?> zzaeb = this.zzl;
        return i + zzaeb.zza(zzaeb.zzc(t));
    }

    private static <T> int zzn(T t, long j) {
        return ((Integer) zzael.zzf(t, j)).intValue();
    }

    private final int zzo(int i) {
        return this.zzc[i + 2];
    }

    private static int zzp(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzq(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzr(T t, long j) {
        return ((Long) zzael.zzf(t, j)).longValue();
    }

    private final zzadk zzs(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzadk zzadk = (zzadk) this.zzd[i3];
        if (zzadk != null) {
            return zzadk;
        }
        zzadk zzb2 = zzadh.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzt(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzu(Class<?> cls, String str) {
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

    private final void zzv(T t, T t2, int i) {
        long zzq = (long) (zzq(i) & 1048575);
        if (zzA(t2, i)) {
            Object zzf2 = zzael.zzf(t, zzq);
            Object zzf3 = zzael.zzf(t2, zzq);
            if (zzf2 != null && zzf3 != null) {
                zzael.zzs(t, zzq, zzaca.zzg(zzf2, zzf3));
                zzx(t, i);
            } else if (zzf3 != null) {
                zzael.zzs(t, zzq, zzf3);
                zzx(t, i);
            }
        }
    }

    private final void zzw(T t, T t2, int i) {
        Object obj;
        int zzq = zzq(i);
        int i2 = this.zzc[i];
        long j = (long) (zzq & 1048575);
        if (zzD(t2, i2, i)) {
            if (zzD(t, i2, i)) {
                obj = zzael.zzf(t, j);
            } else {
                obj = null;
            }
            Object zzf2 = zzael.zzf(t2, j);
            if (obj != null && zzf2 != null) {
                zzael.zzs(t, j, zzaca.zzg(obj, zzf2));
                zzy(t, i2, i);
            } else if (zzf2 != null) {
                zzael.zzs(t, j, zzf2);
                zzy(t, i2, i);
            }
        }
    }

    private final void zzx(T t, int i) {
        int zzo2 = zzo(i);
        long j = (long) (1048575 & zzo2);
        if (j != 1048575) {
            zzael.zzq(t, j, (1 << (zzo2 >>> 20)) | zzael.zzc(t, j));
        }
    }

    private final void zzy(T t, int i, int i2) {
        zzael.zzq(t, (long) (zzo(i2) & 1048575), i);
    }

    private final boolean zzz(T t, T t2, int i) {
        return zzA(t, i) == zzA(t2, i);
    }

    public final int zza(T t) {
        return this.zzg ? zzm(t) : zzl(t);
    }

    public final int zzb(T t) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzq = zzq(i2);
            int i3 = this.zzc[i2];
            long j = (long) (1048575 & zzq);
            int i4 = 37;
            switch (zzp(zzq)) {
                case 0:
                    i = (i * 53) + zzaca.zzc(Double.doubleToLongBits(zzael.zza(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzael.zzb(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzaca.zzc(zzael.zzd(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzaca.zzc(zzael.zzd(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzael.zzc(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzaca.zzc(zzael.zzd(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzael.zzc(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzaca.zza(zzael.zzw(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzael.zzf(t, j)).hashCode();
                    break;
                case 9:
                    Object zzf2 = zzael.zzf(t, j);
                    if (zzf2 != null) {
                        i4 = zzf2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzael.zzf(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzael.zzc(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzael.zzc(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzael.zzc(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzaca.zzc(zzael.zzd(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzael.zzc(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzaca.zzc(zzael.zzd(t, j));
                    break;
                case 17:
                    Object zzf3 = zzael.zzf(t, j);
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
                    i = (i * 53) + zzael.zzf(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzael.zzf(t, j).hashCode();
                    break;
                case 51:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zzc(Double.doubleToLongBits(zzj(t, j)));
                        break;
                    }
                case 52:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzk(t, j));
                        break;
                    }
                case 53:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zzc(zzr(t, j));
                        break;
                    }
                case 54:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zzc(zzr(t, j));
                        break;
                    }
                case 55:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzn(t, j);
                        break;
                    }
                case 56:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zzc(zzr(t, j));
                        break;
                    }
                case 57:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzn(t, j);
                        break;
                    }
                case 58:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zza(zzE(t, j));
                        break;
                    }
                case 59:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzael.zzf(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzael.zzf(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzael.zzf(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzn(t, j);
                        break;
                    }
                case 63:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzn(t, j);
                        break;
                    }
                case 64:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzn(t, j);
                        break;
                    }
                case 65:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zzc(zzr(t, j));
                        break;
                    }
                case 66:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzn(t, j);
                        break;
                    }
                case 67:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzaca.zzc(zzr(t, j));
                        break;
                    }
                case 68:
                    if (!zzD(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzael.zzf(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzl.zzc(t).hashCode();
        if (!this.zzf) {
            return hashCode;
        }
        this.zzm.zza(t);
        throw null;
    }

    public final void zzc(T t) {
        int i;
        int i2 = this.zzi;
        while (true) {
            i = this.zzj;
            if (i2 >= i) {
                break;
            }
            long zzq = (long) (zzq(this.zzh[i2]) & 1048575);
            Object zzf2 = zzael.zzf(t, zzq);
            if (zzf2 != null) {
                ((zzact) zzf2).zzb();
                zzael.zzs(t, zzq, zzf2);
            }
            i2++;
        }
        int length = this.zzh.length;
        while (i < length) {
            this.zzk.zza(t, (long) this.zzh[i]);
            i++;
        }
        this.zzl.zze(t);
        if (this.zzf) {
            this.zzm.zzb(t);
        }
    }

    public final void zzd(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzq = zzq(i);
                long j = (long) (1048575 & zzq);
                int i2 = this.zzc[i];
                switch (zzp(zzq)) {
                    case 0:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzo(t, j, zzael.zza(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 1:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzp(t, j, zzael.zzb(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 2:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzr(t, j, zzael.zzd(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 3:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzr(t, j, zzael.zzd(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 4:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzq(t, j, zzael.zzc(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 5:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzr(t, j, zzael.zzd(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 6:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzq(t, j, zzael.zzc(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 7:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzm(t, j, zzael.zzw(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 8:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzs(t, j, zzael.zzf(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 9:
                        zzv(t, t2, i);
                        break;
                    case 10:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzs(t, j, zzael.zzf(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 11:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzq(t, j, zzael.zzc(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 12:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzq(t, j, zzael.zzc(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 13:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzq(t, j, zzael.zzc(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 14:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzr(t, j, zzael.zzd(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 15:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzq(t, j, zzael.zzc(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 16:
                        if (!zzA(t2, i)) {
                            break;
                        } else {
                            zzael.zzr(t, j, zzael.zzd(t2, j));
                            zzx(t, i);
                            break;
                        }
                    case 17:
                        zzv(t, t2, i);
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
                        this.zzk.zzb(t, t2, j);
                        break;
                    case 50:
                        zzadm.zzG(this.zzo, t, t2, j);
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
                        if (!zzD(t2, i2, i)) {
                            break;
                        } else {
                            zzael.zzs(t, j, zzael.zzf(t2, j));
                            zzy(t, i2, i);
                            break;
                        }
                    case 60:
                        zzw(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zzD(t2, i2, i)) {
                            break;
                        } else {
                            zzael.zzs(t, j, zzael.zzf(t2, j));
                            zzy(t, i2, i);
                            break;
                        }
                    case 68:
                        zzw(t, t2, i);
                        break;
                }
            }
            zzadm.zzD(this.zzl, t, t2);
            if (this.zzf) {
                zzadm.zzC(this.zzm, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    public final boolean zze(T t, T t2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzq = zzq(i);
            long j = (long) (zzq & 1048575);
            switch (zzp(zzq)) {
                case 0:
                    if (zzz(t, t2, i) && Double.doubleToLongBits(zzael.zza(t, j)) == Double.doubleToLongBits(zzael.zza(t2, j))) {
                        continue;
                    }
                case 1:
                    if (zzz(t, t2, i) && Float.floatToIntBits(zzael.zzb(t, j)) == Float.floatToIntBits(zzael.zzb(t2, j))) {
                        continue;
                    }
                case 2:
                    if (zzz(t, t2, i) && zzael.zzd(t, j) == zzael.zzd(t2, j)) {
                        continue;
                    }
                case 3:
                    if (zzz(t, t2, i) && zzael.zzd(t, j) == zzael.zzd(t2, j)) {
                        continue;
                    }
                case 4:
                    if (zzz(t, t2, i) && zzael.zzc(t, j) == zzael.zzc(t2, j)) {
                        continue;
                    }
                case 5:
                    if (zzz(t, t2, i) && zzael.zzd(t, j) == zzael.zzd(t2, j)) {
                        continue;
                    }
                case 6:
                    if (zzz(t, t2, i) && zzael.zzc(t, j) == zzael.zzc(t2, j)) {
                        continue;
                    }
                case 7:
                    if (zzz(t, t2, i) && zzael.zzw(t, j) == zzael.zzw(t2, j)) {
                        continue;
                    }
                case 8:
                    if (zzz(t, t2, i) && zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j))) {
                        continue;
                    }
                case 9:
                    if (zzz(t, t2, i) && zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j))) {
                        continue;
                    }
                case 10:
                    if (zzz(t, t2, i) && zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j))) {
                        continue;
                    }
                case 11:
                    if (zzz(t, t2, i) && zzael.zzc(t, j) == zzael.zzc(t2, j)) {
                        continue;
                    }
                case 12:
                    if (zzz(t, t2, i) && zzael.zzc(t, j) == zzael.zzc(t2, j)) {
                        continue;
                    }
                case 13:
                    if (zzz(t, t2, i) && zzael.zzc(t, j) == zzael.zzc(t2, j)) {
                        continue;
                    }
                case 14:
                    if (zzz(t, t2, i) && zzael.zzd(t, j) == zzael.zzd(t2, j)) {
                        continue;
                    }
                case 15:
                    if (zzz(t, t2, i) && zzael.zzc(t, j) == zzael.zzc(t2, j)) {
                        continue;
                    }
                case 16:
                    if (zzz(t, t2, i) && zzael.zzd(t, j) == zzael.zzd(t2, j)) {
                        continue;
                    }
                case 17:
                    if (zzz(t, t2, i) && zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j))) {
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
                    z = zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j));
                    break;
                case 50:
                    z = zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j));
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
                    long zzo2 = (long) (zzo(i) & 1048575);
                    if (zzael.zzc(t, zzo2) == zzael.zzc(t2, zzo2) && zzadm.zzF(zzael.zzf(t, j), zzael.zzf(t2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzl.zzc(t).equals(this.zzl.zzc(t2))) {
            return false;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzm.zza(t);
        this.zzm.zza(t2);
        throw null;
    }

    public final boolean zzf(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzi) {
            int i6 = this.zzh[i5];
            int i7 = this.zzc[i6];
            int zzq = zzq(i6);
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
            if ((268435456 & zzq) != 0 && !zzB(t, i6, i2, i, i10)) {
                return false;
            }
            switch (zzp(zzq)) {
                case 9:
                case 17:
                    if (zzB(t, i6, i2, i, i10) && !zzC(t2, zzq, zzs(i6))) {
                        return false;
                    }
                case 27:
                case 49:
                    List list = (List) zzael.zzf(t2, (long) (zzq & 1048575));
                    if (!list.isEmpty()) {
                        zzadk zzs = zzs(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzs.zzf(list.get(i11))) {
                                return false;
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                case 50:
                    if (((zzact) zzael.zzf(t2, (long) (zzq & 1048575))).isEmpty()) {
                        break;
                    } else {
                        zzacs zzacs = (zzacs) zzt(i6);
                        throw null;
                    }
                case 60:
                case 68:
                    if (zzD(t2, i7, i6) && !zzC(t2, zzq, zzs(i6))) {
                        return false;
                    }
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzm.zza(t2);
        throw null;
    }

    public final void zzi(T t, zzabg zzabg) throws IOException {
        if (!this.zzg) {
            zzF(t, zzabg);
        } else if (!this.zzf) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzq = zzq(i);
                int i2 = this.zzc[i];
                switch (zzp(zzq)) {
                    case 0:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzf(i2, zzael.zza(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzn(i2, zzael.zzb(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzs(i2, zzael.zzd(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzH(i2, zzael.zzd(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzq(i2, zzael.zzc(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzl(i2, zzael.zzd(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzj(i2, zzael.zzc(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzb(i2, zzael.zzw(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzH(i2, zzael.zzf(t, (long) (zzq & 1048575)), zzabg);
                            break;
                        }
                    case 9:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzu(i2, zzael.zzf(t, (long) (zzq & 1048575)), zzs(i));
                            break;
                        }
                    case 10:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzd(i2, (zzaax) zzael.zzf(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzF(i2, zzael.zzc(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzh(i2, zzael.zzc(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzv(i2, zzael.zzc(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzx(i2, zzael.zzd(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzz(i2, zzael.zzc(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzB(i2, zzael.zzd(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzA(t, i)) {
                            break;
                        } else {
                            zzabg.zzp(i2, zzael.zzf(t, (long) (zzq & 1048575)), zzs(i));
                            break;
                        }
                    case 18:
                        zzadm.zzJ(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 19:
                        zzadm.zzN(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 20:
                        zzadm.zzQ(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 21:
                        zzadm.zzY(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 22:
                        zzadm.zzP(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 23:
                        zzadm.zzM(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 24:
                        zzadm.zzL(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 25:
                        zzadm.zzH(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 26:
                        zzadm.zzW(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg);
                        break;
                    case 27:
                        zzadm.zzR(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, zzs(i));
                        break;
                    case 28:
                        zzadm.zzI(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg);
                        break;
                    case 29:
                        zzadm.zzX(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 30:
                        zzadm.zzK(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 31:
                        zzadm.zzS(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 32:
                        zzadm.zzT(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 33:
                        zzadm.zzU(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 34:
                        zzadm.zzV(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, false);
                        break;
                    case 35:
                        zzadm.zzJ(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 36:
                        zzadm.zzN(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 37:
                        zzadm.zzQ(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 38:
                        zzadm.zzY(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 39:
                        zzadm.zzP(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 40:
                        zzadm.zzM(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 41:
                        zzadm.zzL(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 42:
                        zzadm.zzH(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 43:
                        zzadm.zzX(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 44:
                        zzadm.zzK(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 45:
                        zzadm.zzS(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 46:
                        zzadm.zzT(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 47:
                        zzadm.zzU(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 48:
                        zzadm.zzV(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, true);
                        break;
                    case 49:
                        zzadm.zzO(this.zzc[i], (List) zzael.zzf(t, (long) (zzq & 1048575)), zzabg, zzs(i));
                        break;
                    case 50:
                        zzG(zzabg, i2, zzael.zzf(t, (long) (zzq & 1048575)), i);
                        break;
                    case 51:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzf(i2, zzj(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzn(i2, zzk(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzs(i2, zzr(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzH(i2, zzr(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzq(i2, zzn(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzl(i2, zzr(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzj(i2, zzn(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 58:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzb(i2, zzE(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzH(i2, zzael.zzf(t, (long) (zzq & 1048575)), zzabg);
                            break;
                        }
                    case 60:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzu(i2, zzael.zzf(t, (long) (zzq & 1048575)), zzs(i));
                            break;
                        }
                    case 61:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzd(i2, (zzaax) zzael.zzf(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 62:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzF(i2, zzn(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 63:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzh(i2, zzn(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzv(i2, zzn(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzx(i2, zzr(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 66:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzz(i2, zzn(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzB(i2, zzr(t, (long) (zzq & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzD(t, i2, i)) {
                            break;
                        } else {
                            zzabg.zzp(i2, zzael.zzf(t, (long) (zzq & 1048575)), zzs(i));
                            break;
                        }
                }
            }
            zzaeb<?, ?> zzaeb = this.zzl;
            zzaeb.zzg(zzaeb.zzc(t), zzabg);
        } else {
            this.zzm.zza(t);
            throw null;
        }
    }
}
