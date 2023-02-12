package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public enum zzdv implements Parcelable {
    FRAGMENT,
    INTENT;
    
    public static final Parcelable.Creator<zzdv> CREATOR = null;

    static {
        CREATOR = new zzdu();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
