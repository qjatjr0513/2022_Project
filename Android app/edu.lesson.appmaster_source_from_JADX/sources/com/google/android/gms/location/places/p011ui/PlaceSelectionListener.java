package com.google.android.gms.location.places.p011ui;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;

@Deprecated
/* renamed from: com.google.android.gms.location.places.ui.PlaceSelectionListener */
public interface PlaceSelectionListener {
    void onError(Status status);

    void onPlaceSelected(Place place);
}
