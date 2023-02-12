package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaca */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzaca {
    private static final zzaca zza = new zzaca(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzaca() {
        this(0, new int[8], new Object[8], true);
    }

    private zzaca(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzaca zzc() {
        return zza;
    }

    static zzaca zzd(zzaca zzaca, zzaca zzaca2) {
        int i = zzaca.zzb + zzaca2.zzb;
        int[] copyOf = Arrays.copyOf(zzaca.zzc, i);
        System.arraycopy(zzaca2.zzc, 0, copyOf, zzaca.zzb, zzaca2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzaca.zzd, i);
        System.arraycopy(zzaca2.zzd, 0, copyOf2, zzaca.zzb, zzaca2.zzb);
        return new zzaca(i, copyOf, copyOf2, true);
    }

    static zzaca zze() {
        return new zzaca(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzaca)) {
            return false;
        }
        zzaca zzaca = (zzaca) obj;
        int i = this.zzb;
        if (i == zzaca.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzaca.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzaca.zzd;
                    int i3 = this.zzb;
                    int i4 = 0;
                    while (i4 < i3) {
                        if (objArr[i4].equals(objArr2[i4])) {
                            i4++;
                        }
                    }
                    return true;
                } else if (iArr[i2] != iArr2[i2]) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            switch (i4 & 7) {
                case 0:
                    i2 += zzze.zzE(i5 << 3) + zzze.zzF(((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    ((Long) this.zzd[i3]).longValue();
                    i2 += zzze.zzE(i5 << 3) + 8;
                    break;
                case 2:
                    int zzE = zzze.zzE(i5 << 3);
                    int zzd2 = ((zzyu) this.zzd[i3]).zzd();
                    i2 += zzE + zzze.zzE(zzd2) + zzd2;
                    break;
                case 3:
                    int zzD = zzze.zzD(i5);
                    i2 += zzD + zzD + ((zzaca) this.zzd[i3]).zza();
                    break;
                case 5:
                    ((Integer) this.zzd[i3]).intValue();
                    i2 += zzze.zzE(i5 << 3) + 4;
                    break;
                default:
                    throw new IllegalStateException(zzaae.zza());
            }
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int zzE = zzze.zzE(8);
            int zzd2 = ((zzyu) this.zzd[i3]).zzd();
            i2 += zzE + zzE + zzze.zzE(16) + zzze.zzE(i4 >>> 3) + zzze.zzE(24) + zzze.zzE(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    public final void zzf() {
        this.zzf = false;
    }

    /* access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzabb.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzh(int i, Object obj) {
        int i2;
        if (this.zzf) {
            int i3 = this.zzb;
            int[] iArr = this.zzc;
            if (i3 == iArr.length) {
                if (i3 < 4) {
                    i2 = 8;
                } else {
                    i2 = i3 >> 1;
                }
                int i4 = i3 + i2;
                this.zzc = Arrays.copyOf(iArr, i4);
                this.zzd = Arrays.copyOf(this.zzd, i4);
            }
            int[] iArr2 = this.zzc;
            int i5 = this.zzb;
            iArr2[i5] = i;
            this.zzd[i5] = obj;
            this.zzb = i5 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzi(zzzf zzzf) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                switch (i2 & 7) {
                    case 0:
                        zzzf.zzt(i3, ((Long) obj).longValue());
                        break;
                    case 1:
                        zzzf.zzm(i3, ((Long) obj).longValue());
                        break;
                    case 2:
                        zzzf.zzd(i3, (zzyu) obj);
                        break;
                    case 3:
                        zzzf.zzE(i3);
                        ((zzaca) obj).zzi(zzzf);
                        zzzf.zzh(i3);
                        break;
                    case 5:
                        zzzf.zzk(i3, ((Integer) obj).intValue());
                        break;
                    default:
                        throw new RuntimeException(zzaae.zza());
                }
            }
        }
    }
}
