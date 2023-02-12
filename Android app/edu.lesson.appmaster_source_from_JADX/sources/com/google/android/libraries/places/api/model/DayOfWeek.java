package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public enum DayOfWeek implements Parcelable {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;
    
    public static final Parcelable.Creator<DayOfWeek> CREATOR = null;

    static {
        CREATOR = new zzbc();
    }

    static DayOfWeek zza(Parcel parcel) {
        String readString = parcel.readString();
        if (readString != null) {
            return valueOf(readString);
        }
        throw null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name());
    }
}
