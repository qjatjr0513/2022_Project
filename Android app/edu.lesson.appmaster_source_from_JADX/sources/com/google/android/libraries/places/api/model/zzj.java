package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzj extends LocalTime {
    private final int zza;
    private final int zzb;

    zzj(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LocalTime) {
            LocalTime localTime = (LocalTime) obj;
            return this.zza == localTime.getHours() && this.zzb == localTime.getMinutes();
        }
    }

    public final int getHours() {
        return this.zza;
    }

    public final int getMinutes() {
        return this.zzb;
    }

    public final int hashCode() {
        return ((this.zza ^ 1000003) * 1000003) ^ this.zzb;
    }

    public final String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        StringBuilder sb = new StringBuilder(49);
        sb.append("LocalTime{hours=");
        sb.append(i);
        sb.append(", minutes=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
