package com.google.android.libraries.places.api.model;

import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzl extends OpeningHours {
    private final List<Period> zza;
    private final List<String> zzb;

    zzl(List<Period> list, List<String> list2) {
        if (list != null) {
            this.zza = list;
            if (list2 != null) {
                this.zzb = list2;
                return;
            }
            throw new NullPointerException("Null weekdayText");
        }
        throw new NullPointerException("Null periods");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OpeningHours) {
            OpeningHours openingHours = (OpeningHours) obj;
            return this.zza.equals(openingHours.getPeriods()) && this.zzb.equals(openingHours.getWeekdayText());
        }
    }

    public final List<Period> getPeriods() {
        return this.zza;
    }

    public final List<String> getWeekdayText() {
        return this.zzb;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36 + String.valueOf(valueOf2).length());
        sb.append("OpeningHours{periods=");
        sb.append(valueOf);
        sb.append(", weekdayText=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
