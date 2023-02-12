package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzil {
    static int zza(byte[] bArr, int i, zzik zzik) throws zzkh {
        int zzj = zzj(bArr, i, zzik);
        int i2 = zzik.zza;
        if (i2 < 0) {
            throw zzkh.zzd();
        } else if (i2 > bArr.length - zzj) {
            throw zzkh.zzf();
        } else if (i2 == 0) {
            zzik.zzc = zzix.zzb;
            return zzj;
        } else {
            zzik.zzc = zzix.zzl(bArr, zzj, i2);
            return zzj + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int zzc(zzln zzln, byte[] bArr, int i, int i2, int i3, zzik zzik) throws IOException {
        zzlf zzlf = (zzlf) zzln;
        Object zze = zzlf.zze();
        int zzc = zzlf.zzc(zze, bArr, i, i2, i3, zzik);
        zzlf.zzf(zze);
        zzik.zzc = zze;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzd(com.google.android.gms.internal.measurement.zzln r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.measurement.zzik r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000e
            int r0 = zzk(r8, r7, r0, r10)
            int r8 = r10.zza
            r3 = r0
            goto L_0x000f
        L_0x000e:
            r3 = r0
        L_0x000f:
            if (r8 < 0) goto L_0x0027
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0027
            java.lang.Object r9 = r6.zze()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zzh(r1, r2, r3, r4, r5)
            r6.zzf(r9)
            r10.zzc = r9
            return r8
        L_0x0027:
            com.google.android.gms.internal.measurement.zzkh r6 = com.google.android.gms.internal.measurement.zzkh.zzf()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzil.zzd(com.google.android.gms.internal.measurement.zzln, byte[], int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    static int zze(zzln<?> zzln, int i, byte[] bArr, int i2, int i3, zzke<?> zzke, zzik zzik) throws IOException {
        int zzd = zzd(zzln, bArr, i2, i3, zzik);
        zzke.add(zzik.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzik);
            if (i != zzik.zza) {
                break;
            }
            zzd = zzd(zzln, bArr, zzj, i3, zzik);
            zzke.add(zzik.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzke<?> zzke, zzik zzik) throws IOException {
        zzjy zzjy = (zzjy) zzke;
        int zzj = zzj(bArr, i, zzik);
        int i2 = zzik.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzik);
            zzjy.zzh(zzik.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzkh.zzf();
    }

    static int zzg(byte[] bArr, int i, zzik zzik) throws zzkh {
        int zzj = zzj(bArr, i, zzik);
        int i2 = zzik.zza;
        if (i2 < 0) {
            throw zzkh.zzd();
        } else if (i2 == 0) {
            zzik.zzc = "";
            return zzj;
        } else {
            zzik.zzc = new String(bArr, zzj, i2, zzkf.zzb);
            return zzj + i2;
        }
    }

    static int zzh(byte[] bArr, int i, zzik zzik) throws zzkh {
        int zzj = zzj(bArr, i, zzik);
        int i2 = zzik.zza;
        if (i2 < 0) {
            throw zzkh.zzd();
        } else if (i2 == 0) {
            zzik.zzc = "";
            return zzj;
        } else {
            zzik.zzc = zzmq.zzd(bArr, zzj, i2);
            return zzj + i2;
        }
    }

    static int zzj(byte[] bArr, int i, zzik zzik) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzik);
        }
        zzik.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzik zzik) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzik.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Ascii.DEL) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzik.zza = i5 | (b2 << Ascii.f63SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Ascii.DEL) << Ascii.f63SO);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzik.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Ascii.DEL) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzik.zza = i9 | (b4 << Ascii.f56FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Ascii.DEL) << Ascii.f56FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzik.zza = i11;
                return i12;
            }
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzke<?> zzke, zzik zzik) {
        zzjy zzjy = (zzjy) zzke;
        int zzj = zzj(bArr, i2, zzik);
        zzjy.zzh(zzik.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzik);
            if (i != zzik.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzik);
            zzjy.zzh(zzik.zza);
        }
        return zzj;
    }

    static int zzm(byte[] bArr, int i, zzik zzik) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzik.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Ascii.DEL)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Ascii.DEL)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzik.zzb = j2;
        return i3;
    }

    static long zzn(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzmc zzmc, zzik zzik) throws zzkh {
        if ((i >>> 3) != 0) {
            switch (i & 7) {
                case 0:
                    int zzm = zzm(bArr, i2, zzik);
                    zzmc.zzh(i, Long.valueOf(zzik.zzb));
                    return zzm;
                case 1:
                    zzmc.zzh(i, Long.valueOf(zzn(bArr, i2)));
                    return i2 + 8;
                case 2:
                    int zzj = zzj(bArr, i2, zzik);
                    int i4 = zzik.zza;
                    if (i4 < 0) {
                        throw zzkh.zzd();
                    } else if (i4 <= bArr.length - zzj) {
                        if (i4 == 0) {
                            zzmc.zzh(i, zzix.zzb);
                        } else {
                            zzmc.zzh(i, zzix.zzl(bArr, zzj, i4));
                        }
                        return zzj + i4;
                    } else {
                        throw zzkh.zzf();
                    }
                case 3:
                    int i5 = (i & -8) | 4;
                    zzmc zze = zzmc.zze();
                    int i6 = 0;
                    while (true) {
                        if (i2 < i3) {
                            int zzj2 = zzj(bArr, i2, zzik);
                            int i7 = zzik.zza;
                            if (i7 == i5) {
                                i6 = i7;
                                i2 = zzj2;
                            } else {
                                i6 = i7;
                                i2 = zzi(i7, bArr, zzj2, i3, zze, zzik);
                            }
                        }
                    }
                    if (i2 > i3 || i6 != i5) {
                        throw zzkh.zze();
                    }
                    zzmc.zzh(i, zze);
                    return i2;
                case 5:
                    zzmc.zzh(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                default:
                    throw zzkh.zzb();
            }
        } else {
            throw zzkh.zzb();
        }
    }
}
