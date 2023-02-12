package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdu implements Parcelable.Creator<zzdv> {
    zzdu() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzdv.valueOf(parcel.readString());
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzdv[i];
    }
}
