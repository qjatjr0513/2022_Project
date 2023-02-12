package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Response;

@Deprecated
public class PlacePhotoMetadataResponse extends Response<PlacePhotoMetadataResult> {
    PlacePhotoMetadataResponse() {
    }

    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return ((PlacePhotoMetadataResult) getResult()).getPhotoMetadata();
    }
}
