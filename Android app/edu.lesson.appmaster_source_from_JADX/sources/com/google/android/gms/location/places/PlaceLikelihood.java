package com.google.android.gms.location.places;

import com.google.android.gms.common.data.Freezable;

@Deprecated
public interface PlaceLikelihood extends Freezable<PlaceLikelihood> {
    float getLikelihood();

    Place getPlace();
}
