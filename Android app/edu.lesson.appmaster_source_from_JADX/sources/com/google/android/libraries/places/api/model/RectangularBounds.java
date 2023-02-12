package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class RectangularBounds implements LocationBias, LocationRestriction {
    public static RectangularBounds newInstance(LatLng southwest, LatLng northeast) {
        return newInstance(new LatLngBounds(southwest, northeast));
    }

    public abstract LatLng getNortheast();

    public abstract LatLng getSouthwest();

    public static RectangularBounds newInstance(LatLngBounds bounds) {
        zzv zzv = new zzv();
        zzv.zzb(bounds.southwest);
        zzv.zza(bounds.northeast);
        return zzv.zzc();
    }
}
