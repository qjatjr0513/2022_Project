package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import java.io.IOException;
import java.util.Arrays;

public final class zzdr {
    private static final zzdr zzmh = new zzdr(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzdy;
    private int zzii;
    private Object[] zzkt;
    private int[] zzmi;

    public static zzdr zzdh() {
        return zzmh;
    }

    static zzdr zzdi() {
        return new zzdr();
    }

    static zzdr zzb(zzdr zzdr, zzdr zzdr2) {
        int i = zzdr.count + zzdr2.count;
        int[] copyOf = Arrays.copyOf(zzdr.zzmi, i);
        System.arraycopy(zzdr2.zzmi, 0, copyOf, zzdr.count, zzdr2.count);
        Object[] copyOf2 = Arrays.copyOf(zzdr.zzkt, i);
        System.arraycopy(zzdr2.zzkt, 0, copyOf2, zzdr.count, zzdr2.count);
        return new zzdr(i, copyOf, copyOf2, true);
    }

    private zzdr() {
        this(0, new int[8], new Object[8], true);
    }

    private zzdr(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzii = -1;
        this.count = i;
        this.zzmi = iArr;
        this.zzkt = objArr;
        this.zzdy = z;
    }

    public final void zzab() {
        this.zzdy = false;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzel zzel) throws IOException {
        if (zzel.zzam() == zzbc.zze.zzix) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzel.zzb(this.zzmi[i] >>> 3, this.zzkt[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzel.zzb(this.zzmi[i2] >>> 3, this.zzkt[i2]);
        }
    }

    public final void zzc(zzel zzel) throws IOException {
        if (this.count != 0) {
            if (zzel.zzam() == zzbc.zze.zziw) {
                for (int i = 0; i < this.count; i++) {
                    zzc(this.zzmi[i], this.zzkt[i], zzel);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzc(this.zzmi[i2], this.zzkt[i2], zzel);
            }
        }
    }

    private static void zzc(int i, Object obj, zzel zzel) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzel.zzj(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzel.zzd(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzel.zzb(i2, (zzw) obj);
                return;
            case 3:
                if (zzel.zzam() == zzbc.zze.zziw) {
                    zzel.zzaa(i2);
                    ((zzdr) obj).zzc(zzel);
                    zzel.zzab(i2);
                    return;
                }
                zzel.zzab(i2);
                ((zzdr) obj).zzc(zzel);
                zzel.zzaa(i2);
                return;
            case 5:
                zzel.zzg(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzbk.zzbs());
        }
    }

    public final int zzdj() {
        int i = this.zzii;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzaj.zze(this.zzmi[i3] >>> 3, (zzw) this.zzkt[i3]);
        }
        this.zzii = i2;
        return i2;
    }

    public final int zzbh() {
        int i;
        int i2 = this.zzii;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzmi[i4];
            int i6 = i5 >>> 3;
            switch (i5 & 7) {
                case 0:
                    i = zzaj.zzf(i6, ((Long) this.zzkt[i4]).longValue());
                    break;
                case 1:
                    i = zzaj.zzh(i6, ((Long) this.zzkt[i4]).longValue());
                    break;
                case 2:
                    i = zzaj.zzd(i6, (zzw) this.zzkt[i4]);
                    break;
                case 3:
                    i = (zzaj.zzr(i6) << 1) + ((zzdr) this.zzkt[i4]).zzbh();
                    break;
                case 5:
                    i = zzaj.zzk(i6, ((Integer) this.zzkt[i4]).intValue());
                    break;
                default:
                    throw new IllegalStateException(zzbk.zzbs());
            }
            i3 += i;
        }
        this.zzii = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzdr)) {
            return false;
        }
        zzdr zzdr = (zzdr) obj;
        int i = this.count;
        if (i == zzdr.count) {
            int[] iArr = this.zzmi;
            int[] iArr2 = zzdr.zzmi;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzkt;
                Object[] objArr2 = zzdr.zzkt;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (!z2) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzmi;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzkt;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzcl.zzb(sb, i, String.valueOf(this.zzmi[i2] >>> 3), this.zzkt[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(int i, Object obj) {
        if (this.zzdy) {
            int i2 = this.count;
            int[] iArr = this.zzmi;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.zzmi = Arrays.copyOf(iArr, i3);
                this.zzkt = Arrays.copyOf(this.zzkt, i3);
            }
            int[] iArr2 = this.zzmi;
            int i4 = this.count;
            iArr2[i4] = i;
            this.zzkt[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
