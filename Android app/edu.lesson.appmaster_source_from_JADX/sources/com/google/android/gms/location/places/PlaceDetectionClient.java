package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

@Deprecated
public class PlaceDetectionClient extends GoogleApi<PlacesOptions> {
    PlaceDetectionClient(Context context, PlacesOptions placesOptions) {
        this(context, Places.PLACE_DETECTION_API, placesOptions);
    }

    private PlaceDetectionClient(Context context, Api<PlacesOptions> api, PlacesOptions placesOptions) {
        super(context, api, placesOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    PlaceDetectionClient(Activity activity, PlacesOptions placesOptions) {
        super(activity, Places.PLACE_DETECTION_API, placesOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<PlaceLikelihoodBufferResponse> getCurrentPlace(PlaceFilter placeFilter) {
        return PendingResultUtil.toResponseTask(Places.PlaceDetectionApi.getCurrentPlace(asGoogleApiClient(), placeFilter), new PlaceLikelihoodBufferResponse());
    }

    public Task<Void> reportDeviceAtPlace(PlaceReport placeReport) {
        return PendingResultUtil.toVoidTask(Places.PlaceDetectionApi.reportDeviceAtPlace(asGoogleApiClient(), placeReport));
    }
}
