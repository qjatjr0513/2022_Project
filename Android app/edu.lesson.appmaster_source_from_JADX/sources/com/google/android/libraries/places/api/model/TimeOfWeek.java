package com.google.android.libraries.places.api.model;

import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class TimeOfWeek implements Parcelable {
    public static TimeOfWeek newInstance(DayOfWeek day, LocalTime localTime) {
        return new zzaz(day, localTime);
    }

    public abstract DayOfWeek getDay();

    public abstract LocalTime getTime();
}
