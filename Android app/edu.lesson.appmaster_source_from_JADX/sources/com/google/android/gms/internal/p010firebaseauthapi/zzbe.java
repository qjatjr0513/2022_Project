package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbe */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzbe implements Comparable<zzbe> {
    private final byte[] zza;

    /* synthetic */ zzbe(byte[] bArr, zzbc zzbc) {
        this.zza = Arrays.copyOf(bArr, bArr.length);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzbe zzbe = (zzbe) obj;
        int length = this.zza.length;
        int length2 = zzbe.zza.length;
        if (length != length2) {
            return length - length2;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return 0;
            }
            byte b = bArr[i];
            byte b2 = zzbe.zza[i];
            if (b != b2) {
                return b - b2;
            }
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbe)) {
            return false;
        }
        return Arrays.equals(this.zza, ((zzbe) obj).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return zzky.zza(this.zza);
    }
}
