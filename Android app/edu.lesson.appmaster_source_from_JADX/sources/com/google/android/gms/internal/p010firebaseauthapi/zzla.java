package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.common.base.Ascii;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzla */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzla {
    static byte[] zza(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            long zzb = zzb(bArr3, 0, 0);
            int i = 2;
            long zzb2 = zzb(bArr3, 3, 2) & 67108611;
            long zzb3 = zzb(bArr3, 6, 4) & 67092735;
            long zzb4 = zzb(bArr3, 9, 6) & 66076671;
            long zzb5 = zzb(bArr3, 12, 8) & 1048575;
            long j = zzb2 * 5;
            long j2 = zzb3 * 5;
            long j3 = zzb4 * 5;
            long j4 = zzb5 * 5;
            int i2 = 17;
            byte[] bArr5 = new byte[17];
            long j5 = 0;
            int i3 = 0;
            long j6 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            while (true) {
                int length = bArr4.length;
                if (i3 < length) {
                    int min = Math.min(16, length - i3);
                    System.arraycopy(bArr4, i3, bArr5, 0, min);
                    bArr5[min] = 1;
                    if (min != 16) {
                        Arrays.fill(bArr5, min + 1, i2, (byte) 0);
                    }
                    long zzb6 = j9 + zzb(bArr5, 0, 0);
                    long zzb7 = j6 + zzb(bArr5, 3, i);
                    long zzb8 = j5 + zzb(bArr5, 6, 4);
                    long zzb9 = j7 + zzb(bArr5, 9, 6);
                    long zzb10 = j8 + (zzb(bArr5, 12, 8) | ((long) (bArr5[16] << Ascii.CAN)));
                    long j10 = (zzb6 * zzb) + (zzb7 * j4) + (zzb8 * j3) + (zzb9 * j2) + (zzb10 * j);
                    long j11 = (zzb6 * zzb2) + (zzb7 * zzb) + (zzb8 * j4) + (zzb9 * j3) + (zzb10 * j2) + (j10 >> 26);
                    long j12 = (zzb6 * zzb3) + (zzb7 * zzb2) + (zzb8 * zzb) + (zzb9 * j4) + (zzb10 * j3) + (j11 >> 26);
                    long j13 = (zzb6 * zzb4) + (zzb7 * zzb3) + (zzb8 * zzb2) + (zzb9 * zzb) + (zzb10 * j4) + (j12 >> 26);
                    long j14 = (zzb6 * zzb5) + (zzb7 * zzb4) + (zzb8 * zzb3) + (zzb9 * zzb2) + (zzb10 * zzb) + (j13 >> 26);
                    j8 = j14 & 67108863;
                    long j15 = (j10 & 67108863) + ((j14 >> 26) * 5);
                    j9 = j15 & 67108863;
                    j6 = (j11 & 67108863) + (j15 >> 26);
                    i3 += 16;
                    j7 = j13 & 67108863;
                    j5 = j12 & 67108863;
                    i2 = 17;
                    i = 2;
                } else {
                    long j16 = j5 + (j6 >> 26);
                    long j17 = j16 & 67108863;
                    long j18 = j7 + (j16 >> 26);
                    long j19 = j18 & 67108863;
                    long j20 = j8 + (j18 >> 26);
                    long j21 = j20 & 67108863;
                    long j22 = j9 + ((j20 >> 26) * 5);
                    long j23 = j22 & 67108863;
                    long j24 = (j6 & 67108863) + (j22 >> 26);
                    long j25 = j23 + 5;
                    long j26 = (j25 >> 26) + j24;
                    long j27 = j17 + (j26 >> 26);
                    long j28 = j19 + (j27 >> 26);
                    long j29 = (j21 + (j28 >> 26)) - 67108864;
                    long j30 = j29 >> 63;
                    long j31 = ~j30;
                    long j32 = (j24 & j30) | (j26 & 67108863 & j31);
                    long j33 = (j17 & j30) | (j27 & 67108863 & j31);
                    long j34 = (j19 & j30) | (j28 & 67108863 & j31);
                    long zzc = (((j23 & j30) | (j25 & 67108863 & j31) | (j32 << 26)) & 4294967295L) + zzc(bArr3, 16);
                    long zzc2 = (((j32 >> 6) | (j33 << 20)) & 4294967295L) + zzc(bArr3, 20) + (zzc >> 32);
                    long zzc3 = (((j33 >> 12) | (j34 << 14)) & 4294967295L) + zzc(bArr3, 24) + (zzc2 >> 32);
                    long zzc4 = zzc(bArr3, 28);
                    byte[] bArr6 = new byte[16];
                    zzd(bArr6, zzc & 4294967295L, 0);
                    zzd(bArr6, zzc2 & 4294967295L, 4);
                    zzd(bArr6, zzc3 & 4294967295L, 8);
                    zzd(bArr6, ((((((j29 & j31) | (j30 & j21)) << 8) | (j34 >> 18)) & 4294967295L) + zzc4 + (zzc3 >> 32)) & 4294967295L, 12);
                    return bArr6;
                }
            }
        } else {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
    }

    private static long zzb(byte[] bArr, int i, int i2) {
        return (zzc(bArr, i) >> i2) & 67108863;
    }

    private static long zzc(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE))) & 4294967295L;
    }

    private static void zzd(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }
}
