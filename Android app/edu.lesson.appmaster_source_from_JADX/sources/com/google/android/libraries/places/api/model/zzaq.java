package com.google.android.libraries.places.api.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;
import java.util.ArrayList;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzaq implements Parcelable.Creator<zzar> {
    zzaq() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Integer num;
        Double d;
        Integer num2;
        Integer num3;
        String str;
        Parcel parcel2 = parcel;
        String readString = parcel.readInt() == 0 ? parcel.readString() : null;
        AddressComponents addressComponents = (AddressComponents) parcel2.readParcelable(Place.class.getClassLoader());
        Place.BusinessStatus businessStatus = (Place.BusinessStatus) parcel2.readParcelable(Place.class.getClassLoader());
        ArrayList readArrayList = parcel2.readArrayList(Place.class.getClassLoader());
        String readString2 = parcel.readInt() == 0 ? parcel.readString() : null;
        LatLng latLng = (LatLng) parcel2.readParcelable(Place.class.getClassLoader());
        String readString3 = parcel.readInt() == 0 ? parcel.readString() : null;
        OpeningHours openingHours = (OpeningHours) parcel2.readParcelable(Place.class.getClassLoader());
        String readString4 = parcel.readInt() == 0 ? parcel.readString() : null;
        ArrayList readArrayList2 = parcel2.readArrayList(Place.class.getClassLoader());
        PlusCode plusCode = (PlusCode) parcel2.readParcelable(Place.class.getClassLoader());
        if (parcel.readInt() == 0) {
            num = Integer.valueOf(parcel.readInt());
        } else {
            num = null;
        }
        if (parcel.readInt() == 0) {
            d = Double.valueOf(parcel.readDouble());
        } else {
            d = null;
        }
        ArrayList readArrayList3 = parcel2.readArrayList(Place.class.getClassLoader());
        if (parcel.readInt() == 0) {
            num2 = Integer.valueOf(parcel.readInt());
        } else {
            num2 = null;
        }
        if (parcel.readInt() == 0) {
            num3 = Integer.valueOf(parcel.readInt());
        } else {
            num3 = null;
        }
        LatLngBounds latLngBounds = (LatLngBounds) parcel2.readParcelable(Place.class.getClassLoader());
        Uri uri = (Uri) parcel2.readParcelable(Place.class.getClassLoader());
        if (parcel.readInt() == 0) {
            str = parcel.readString();
        } else {
            str = null;
        }
        return new zzar(readString, addressComponents, businessStatus, readArrayList, readString2, latLng, readString3, openingHours, readString4, readArrayList2, plusCode, num, d, readArrayList3, num2, num3, latLngBounds, uri, str, parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzar[i];
    }
}
