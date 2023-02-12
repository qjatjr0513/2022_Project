package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.OpeningHours;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzk extends OpeningHours.Builder {
    private List<Period> zza;
    private List<String> zzb;

    zzk() {
    }

    public final List<Period> getPeriods() {
        List<Period> list = this.zza;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"periods\" has not been set");
    }

    public final List<String> getWeekdayText() {
        List<String> list = this.zzb;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"weekdayText\" has not been set");
    }

    public final OpeningHours.Builder setPeriods(List<Period> list) {
        if (list != null) {
            this.zza = list;
            return this;
        }
        throw new NullPointerException("Null periods");
    }

    public final OpeningHours.Builder setWeekdayText(List<String> list) {
        if (list != null) {
            this.zzb = list;
            return this;
        }
        throw new NullPointerException("Null weekdayText");
    }

    /* access modifiers changed from: package-private */
    public final OpeningHours zza() {
        List<String> list;
        List<Period> list2 = this.zza;
        if (list2 != null && (list = this.zzb) != null) {
            return new zzal(list2, list);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" periods");
        }
        if (this.zzb == null) {
            sb.append(" weekdayText");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
