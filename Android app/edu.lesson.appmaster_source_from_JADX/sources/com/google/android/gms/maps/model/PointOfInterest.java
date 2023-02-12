package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class PointOfInterest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzk();
    public final LatLng latLng;
    public final String name;
    public final String placeId;

    public PointOfInterest(LatLng latLng2, String placeId2, String name2) {
        this.latLng = latLng2;
        this.placeId = placeId2;
        this.name = name2;
    }

    public void writeToParcel(Parcel out, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeParcelable(out, 2, this.latLng, flags, false);
        SafeParcelWriter.writeString(out, 3, this.placeId, false);
        SafeParcelWriter.writeString(out, 4, this.name, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }
}
