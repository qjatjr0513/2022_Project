package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzaf extends zzg {
    public static final Parcelable.Creator<zzaf> CREATOR = new zzae();

    zzaf(int i, int i2) {
        super(i, i2);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(zzb());
        parcel.writeInt(zza());
    }
}
