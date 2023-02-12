package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import com.google.android.gms.common.api.Response;

@Deprecated
public class PlacePhotoResponse extends Response<PlacePhotoResult> {
    PlacePhotoResponse() {
    }

    public Bitmap getBitmap() {
        return ((PlacePhotoResult) getResult()).getBitmap();
    }
}
