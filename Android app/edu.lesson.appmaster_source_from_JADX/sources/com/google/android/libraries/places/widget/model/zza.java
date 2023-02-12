package com.google.android.libraries.places.widget.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zza implements Parcelable.Creator<AutocompleteActivityMode> {
    zza() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        if (readString != null) {
            return AutocompleteActivityMode.valueOf(readString);
        }
        throw null;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new AutocompleteActivityMode[i];
    }
}
