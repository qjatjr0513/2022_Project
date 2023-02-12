package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzg extends zzbb {
    private final int zza;
    private final int zzb;

    zzg(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbb) {
            zzbb zzbb = (zzbb) obj;
            return this.zza == zzbb.zzb() && this.zzb == zzbb.zza();
        }
    }

    public final int hashCode() {
        return ((this.zza ^ 1000003) * 1000003) ^ this.zzb;
    }

    public final String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        StringBuilder sb = new StringBuilder(54);
        sb.append("SubstringMatch{offset=");
        sb.append(i);
        sb.append(", length=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zza;
    }
}
