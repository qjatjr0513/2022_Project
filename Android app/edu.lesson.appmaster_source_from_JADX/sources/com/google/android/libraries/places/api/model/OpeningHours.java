package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzfm;
import com.google.android.libraries.places.internal.zzge;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class OpeningHours implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public OpeningHours build() {
            OpeningHours zza = zza();
            for (String isEmpty : zza.getWeekdayText()) {
                zzfm.zzi(!TextUtils.isEmpty(isEmpty), "WeekdayText must not contain null or empty values.");
            }
            setPeriods(zzge.zzk(zza.getPeriods()));
            setWeekdayText(zzge.zzk(zza.getWeekdayText()));
            return zza();
        }

        public abstract List<Period> getPeriods();

        public abstract List<String> getWeekdayText();

        public abstract Builder setPeriods(List<Period> list);

        public abstract Builder setWeekdayText(List<String> list);

        /* access modifiers changed from: package-private */
        public abstract OpeningHours zza();
    }

    public static Builder builder() {
        zzk zzk = new zzk();
        zzk.setPeriods(new ArrayList());
        zzk.setWeekdayText(new ArrayList());
        return zzk;
    }

    public abstract List<Period> getPeriods();

    public abstract List<String> getWeekdayText();
}
