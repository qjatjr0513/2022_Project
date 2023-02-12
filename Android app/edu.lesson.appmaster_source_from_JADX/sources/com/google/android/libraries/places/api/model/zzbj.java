package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzbj implements Parcelable.Creator<TypeFilter> {
    zzbj() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        if (readString != null) {
            return TypeFilter.valueOf(readString);
        }
        throw null;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new TypeFilter[i];
    }
}
