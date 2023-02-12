package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzao implements Parcelable.Creator<zzap> {
    zzao() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzap(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzap[i];
    }
}
