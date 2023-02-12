package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzn extends Period {
    private final TimeOfWeek zza;
    private final TimeOfWeek zzb;

    zzn(TimeOfWeek timeOfWeek, TimeOfWeek timeOfWeek2) {
        this.zza = timeOfWeek;
        this.zzb = timeOfWeek2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        TimeOfWeek timeOfWeek = this.zza;
        if (timeOfWeek != null ? timeOfWeek.equals(period.getOpen()) : period.getOpen() == null) {
            TimeOfWeek timeOfWeek2 = this.zzb;
            if (timeOfWeek2 != null ? timeOfWeek2.equals(period.getClose()) : period.getClose() == null) {
                return true;
            }
        }
        return false;
    }

    public final TimeOfWeek getClose() {
        return this.zzb;
    }

    public final TimeOfWeek getOpen() {
        return this.zza;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length());
        sb.append("Period{open=");
        sb.append(valueOf);
        sb.append(", close=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    public final int hashCode() {
        int i;
        TimeOfWeek timeOfWeek = this.zza;
        int i2 = 0;
        if (timeOfWeek == null) {
            i = 0;
        } else {
            i = timeOfWeek.hashCode();
        }
        int i3 = (i ^ 1000003) * 1000003;
        TimeOfWeek timeOfWeek2 = this.zzb;
        if (timeOfWeek2 != null) {
            i2 = timeOfWeek2.hashCode();
        }
        return i3 ^ i2;
    }
}
