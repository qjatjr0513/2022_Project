package com.google.android.libraries.places.internal;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzjj {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    /* access modifiers changed from: private */
    public final char[] zzf;
    private final byte[] zzg;

    zzjj(String str, char[] cArr) {
        boolean z;
        this.zze = str;
        if (cArr != null) {
            this.zzf = cArr;
            try {
                int length = cArr.length;
                int zzb2 = zzze.zzb(length, RoundingMode.UNNECESSARY);
                this.zzb = zzb2;
                int min = Math.min(8, Integer.lowestOneBit(zzb2));
                try {
                    this.zzc = 8 / min;
                    this.zzd = zzb2 / min;
                    this.zza = length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    int i = 0;
                    while (true) {
                        boolean z2 = true;
                        if (i >= cArr.length) {
                            break;
                        }
                        char c = cArr[i];
                        if (c < 128) {
                            z = true;
                        } else {
                            z = false;
                        }
                        zzfm.zzf(z, "Non-ASCII character: %s", c);
                        if (bArr[c] != -1) {
                            z2 = false;
                        }
                        zzfm.zzf(z2, "Duplicate character: %s", c);
                        bArr[c] = (byte) i;
                        i++;
                    }
                    this.zzg = bArr;
                    boolean[] zArr = new boolean[this.zzc];
                    for (int i2 = 0; i2 < this.zzd; i2++) {
                        zArr[zzze.zza(i2 * 8, this.zzb, RoundingMode.CEILING)] = true;
                    }
                } catch (ArithmeticException e) {
                    String str2 = new String(cArr);
                    throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e);
                }
            } catch (ArithmeticException e2) {
                int length2 = cArr.length;
                StringBuilder sb = new StringBuilder(35);
                sb.append("Illegal alphabet length ");
                sb.append(length2);
                throw new IllegalArgumentException(sb.toString(), e2);
            }
        } else {
            throw null;
        }
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzjj) {
            return Arrays.equals(this.zzf, ((zzjj) obj).zzf);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf);
    }

    public final String toString() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final char zza(int i) {
        return this.zzf[i];
    }

    public final boolean zzb(char c) {
        return c < 128 && this.zzg[c] != -1;
    }
}
