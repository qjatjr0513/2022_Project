package com.google.android.libraries.places.internal;

import java.util.Arrays;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzaec {
    private static final zzaec zza = new zzaec(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;

    private zzaec() {
        this(0, new int[8], new Object[8], true);
    }

    private zzaec(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = iArr;
        this.zzd = objArr;
    }

    public static zzaec zzc() {
        return zza;
    }

    static zzaec zzd(zzaec zzaec, zzaec zzaec2) {
        int i = zzaec.zzb;
        int i2 = zzaec2.zzb;
        int[] copyOf = Arrays.copyOf(zzaec.zzc, 0);
        int[] iArr = zzaec2.zzc;
        int i3 = zzaec.zzb;
        int i4 = zzaec2.zzb;
        System.arraycopy(iArr, 0, copyOf, 0, 0);
        Object[] copyOf2 = Arrays.copyOf(zzaec.zzd, 0);
        Object[] objArr = zzaec2.zzd;
        int i5 = zzaec.zzb;
        int i6 = zzaec2.zzb;
        System.arraycopy(objArr, 0, copyOf2, 0, 0);
        return new zzaec(0, copyOf, copyOf2, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzaec)) {
            return false;
        }
        zzaec zzaec = (zzaec) obj;
        int[] iArr = zzaec.zzc;
        Object[] objArr = zzaec.zzd;
        return true;
    }

    public final int hashCode() {
        return 506991;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final void zze(StringBuilder sb, int i) {
    }
}
