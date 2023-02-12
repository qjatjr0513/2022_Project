package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.maps.model.LatLngBounds;
import javax.annotation.Nullable;

@Deprecated
public interface GeoDataApi {
    @Deprecated
    PendingResult<PlaceBuffer> addPlace(GoogleApiClient googleApiClient, AddPlaceRequest addPlaceRequest);

    PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(GoogleApiClient googleApiClient, @Nullable String str, @Nullable LatLngBounds latLngBounds, @Nullable AutocompleteFilter autocompleteFilter);

    PendingResult<PlaceBuffer> getPlaceById(GoogleApiClient googleApiClient, String... strArr);

    PendingResult<PlacePhotoMetadataResult> getPlacePhotos(GoogleApiClient googleApiClient, String str);
}
