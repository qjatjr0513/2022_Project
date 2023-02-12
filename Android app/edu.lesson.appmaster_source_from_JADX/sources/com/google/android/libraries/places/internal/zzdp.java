package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdp implements Parcelable.Creator<zzdq> {
    zzdp() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str;
        String str2;
        AutocompleteActivityMode autocompleteActivityMode = (AutocompleteActivityMode) parcel.readParcelable(zzdx.class.getClassLoader());
        zzge zzk = zzge.zzk(parcel.readArrayList(Place.Field.class.getClassLoader()));
        zzdv zzdv = (zzdv) parcel.readParcelable(zzdx.class.getClassLoader());
        if (parcel.readInt() == 0) {
            str = parcel.readString();
        } else {
            str = null;
        }
        if (parcel.readInt() == 0) {
            str2 = parcel.readString();
        } else {
            str2 = null;
        }
        return new zzdq(autocompleteActivityMode, zzk, zzdv, str, str2, (LocationBias) parcel.readParcelable(zzdx.class.getClassLoader()), (LocationRestriction) parcel.readParcelable(zzdx.class.getClassLoader()), zzge.zzk(parcel.readArrayList(String.class.getClassLoader())), (TypeFilter) parcel.readParcelable(zzdx.class.getClassLoader()), parcel.readInt(), parcel.readInt());
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzdq[i];
    }
}
