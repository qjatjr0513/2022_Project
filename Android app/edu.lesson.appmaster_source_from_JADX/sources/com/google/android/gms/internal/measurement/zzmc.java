package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public final class zzmc {
    private static final zzmc zza = new zzmc(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzmc() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmc(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzmc zzc() {
        return zza;
    }

    static zzmc zzd(zzmc zzmc, zzmc zzmc2) {
        int i = zzmc.zzb + zzmc2.zzb;
        int[] copyOf = Arrays.copyOf(zzmc.zzc, i);
        System.arraycopy(zzmc2.zzc, 0, copyOf, zzmc.zzb, zzmc2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzmc.zzd, i);
        System.arraycopy(zzmc2.zzd, 0, copyOf2, zzmc.zzb, zzmc2.zzb);
        return new zzmc(i, copyOf, copyOf2, true);
    }

    static zzmc zze() {
        return new zzmc(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmc)) {
            return false;
        }
        zzmc zzmc = (zzmc) obj;
        int i = this.zzb;
        if (i == zzmc.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmc.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzmc.zzd;
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
                    i2 += zzje.zzA(i5 << 3) + zzje.zzB(((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    ((Long) this.zzd[i3]).longValue();
                    i2 += zzje.zzA(i5 << 3) + 8;
                    break;
                case 2:
                    int zzA = zzje.zzA(i5 << 3);
                    int zzd2 = ((zzix) this.zzd[i3]).zzd();
                    i2 += zzA + zzje.zzA(zzd2) + zzd2;
                    break;
                case 3:
                    int zzz = zzje.zzz(i5);
                    i2 += zzz + zzz + ((zzmc) this.zzd[i3]).zza();
                    break;
                case 5:
                    ((Integer) this.zzd[i3]).intValue();
                    i2 += zzje.zzA(i5 << 3) + 4;
                    break;
                default:
                    throw new IllegalStateException(zzkh.zza());
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
            int zzA = zzje.zzA(8);
            int zzd2 = ((zzix) this.zzd[i3]).zzd();
            i2 += zzA + zzA + zzje.zzA(16) + zzje.zzA(i4 >>> 3) + zzje.zzA(24) + zzje.zzA(zzd2) + zzd2;
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
            zzle.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
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

    public final void zzi(zzjf zzjf) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                switch (i2 & 7) {
                    case 0:
                        zzjf.zzt(i3, ((Long) obj).longValue());
                        break;
                    case 1:
                        zzjf.zzm(i3, ((Long) obj).longValue());
                        break;
                    case 2:
                        zzjf.zzd(i3, (zzix) obj);
                        break;
                    case 3:
                        zzjf.zzE(i3);
                        ((zzmc) obj).zzi(zzjf);
                        zzjf.zzh(i3);
                        break;
                    case 5:
                        zzjf.zzk(i3, ((Integer) obj).intValue());
                        break;
                    default:
                        throw new RuntimeException(zzkh.zza());
                }
            }
        }
    }
}
