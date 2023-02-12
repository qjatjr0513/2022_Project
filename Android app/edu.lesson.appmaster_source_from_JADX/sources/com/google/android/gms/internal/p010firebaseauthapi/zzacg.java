package com.google.android.gms.internal.p010firebaseauthapi;

import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzacg extends zzaci {
    zzacg(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    public final void zzc(Object obj, long j, boolean z) {
        if (zzacj.zzb) {
            zzacj.zzD(obj, j, r3 ? (byte) 1 : 0);
        } else {
            zzacj.zzE(obj, j, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j, byte b) {
        if (zzacj.zzb) {
            zzacj.zzD(obj, j, b);
        } else {
            zzacj.zzE(obj, j, b);
        }
    }

    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    public final boolean zzg(Object obj, long j) {
        if (zzacj.zzb) {
            return zzacj.zzt(obj, j);
        }
        return zzacj.zzu(obj, j);
    }
}
