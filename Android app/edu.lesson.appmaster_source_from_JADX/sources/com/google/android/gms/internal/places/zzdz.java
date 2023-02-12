package com.google.android.gms.internal.places;

import com.google.common.base.Ascii;

final class zzdz {
    /* access modifiers changed from: private */
    public static boolean zze(byte b) {
        return b >= 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzf(byte b) {
        return b < -32;
    }

    /* access modifiers changed from: private */
    public static boolean zzg(byte b) {
        return b < -16;
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, char[] cArr, int i) {
        cArr[i] = (char) b;
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, byte b2, char[] cArr, int i) throws zzbk {
        if (b < -62 || zzh(b2)) {
            throw zzbk.zzbu();
        }
        cArr[i] = (char) (((b & Ascii.f65US) << 6) | (b2 & 63));
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, byte b2, byte b3, char[] cArr, int i) throws zzbk {
        if (zzh(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || zzh(b3)))) {
            throw zzbk.zzbu();
        }
        cArr[i] = (char) (((b & Ascii.f62SI) << Ascii.f55FF) | ((b2 & 63) << 6) | (b3 & 63));
    }

    /* access modifiers changed from: private */
    public static void zzb(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws zzbk {
        if (zzh(b2) || (((b << Ascii.f56FS) + (b2 + 112)) >> 30) != 0 || zzh(b3) || zzh(b4)) {
            throw zzbk.zzbu();
        }
        byte b5 = ((b & 7) << Ascii.DC2) | ((b2 & 63) << Ascii.f55FF) | ((b3 & 63) << 6) | (b4 & 63);
        cArr[i] = (char) ((b5 >>> 10) + 55232);
        cArr[i + 1] = (char) ((b5 & 1023) + 56320);
    }

    private static boolean zzh(byte b) {
        return b > -65;
    }
}
