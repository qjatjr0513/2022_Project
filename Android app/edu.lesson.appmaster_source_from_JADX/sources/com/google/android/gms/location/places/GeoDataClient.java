package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
public class GeoDataClient extends GoogleApi<PlacesOptions> {

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoundsMode {
        public static final int BIAS = 1;
        public static final int STRICT = 2;
    }

    GeoDataClient(Context context, PlacesOptions placesOptions) {
        super(context, Places.GEO_DATA_API, placesOptions, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    GeoDataClient(Activity activity, PlacesOptions placesOptions) {
        super(activity, Places.GEO_DATA_API, placesOptions, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    @Deprecated
    public Task<PlaceBufferResponse> addPlace(AddPlaceRequest addPlaceRequest) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.addPlace(asGoogleApiClient(), addPlaceRequest), new PlaceBufferResponse());
    }

    public Task<PlaceBufferResponse> getPlaceById(String... strArr) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.getPlaceById(asGoogleApiClient(), strArr), new PlaceBufferResponse());
    }

    public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) {
        return PendingResultUtil.toResponseTask(((zzh) Places.GeoDataApi).zzb(asGoogleApiClient(), str, latLngBounds, i, autocompleteFilter), new AutocompletePredictionBufferResponse());
    }

    public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        return getAutocompletePredictions(str, latLngBounds, 1, autocompleteFilter);
    }

    public Task<PlacePhotoMetadataResponse> getPlacePhotos(String str) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.getPlacePhotos(asGoogleApiClient(), str), new PlacePhotoMetadataResponse());
    }

    public Task<PlacePhotoResponse> getPhoto(PlacePhotoMetadata placePhotoMetadata) {
        return getScaledPhoto(placePhotoMetadata, placePhotoMetadata.getMaxWidth(), placePhotoMetadata.getMaxHeight());
    }

    public Task<PlacePhotoResponse> getScaledPhoto(PlacePhotoMetadata placePhotoMetadata, int i, int i2) {
        return PendingResultUtil.toResponseTask(((zzh) Places.GeoDataApi).zzb(asGoogleApiClient(), placePhotoMetadata, i, i2), new PlacePhotoResponse());
    }
}
