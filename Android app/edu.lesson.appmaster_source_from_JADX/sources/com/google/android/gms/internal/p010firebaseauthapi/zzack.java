package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.common.base.Ascii;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzack */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzack {
    static /* synthetic */ void zza(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws zzaae {
        if (zze(b2) || (((b << Ascii.f56FS) + (b2 + 112)) >> 30) != 0 || zze(b3) || zze(b4)) {
            throw zzaae.zzd();
        }
        byte b5 = ((b & 7) << Ascii.DC2) | ((b2 & 63) << Ascii.f55FF) | ((b3 & 63) << 6) | (b4 & 63);
        cArr[i] = (char) ((b5 >>> 10) + 55232);
        cArr[i + 1] = (char) ((b5 & 1023) + 56320);
    }

    static /* synthetic */ void zzb(byte b, byte b2, char[] cArr, int i) throws zzaae {
        if (b < -62 || zze(b2)) {
            throw zzaae.zzd();
        }
        cArr[i] = (char) (((b & Ascii.f65US) << 6) | (b2 & 63));
    }

    static /* synthetic */ boolean zzd(byte b) {
        return b >= 0;
    }

    private static boolean zze(byte b) {
        return b > -65;
    }

    static /* synthetic */ void zzc(byte b, byte b2, byte b3, char[] cArr, int i) throws zzaae {
        if (!zze(b2)) {
            if (b == -32) {
                if (b2 >= -96) {
                    b = -32;
                }
            }
            if (b == -19) {
                if (b2 < -96) {
                    b = -19;
                }
            }
            if (!zze(b3)) {
                cArr[i] = (char) (((b & Ascii.f62SI) << Ascii.f55FF) | ((b2 & 63) << 6) | (b3 & 63));
                return;
            }
        }
        throw zzaae.zzd();
    }
}
