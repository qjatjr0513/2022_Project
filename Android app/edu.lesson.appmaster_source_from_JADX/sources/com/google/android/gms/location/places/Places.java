package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.internal.zzae;
import com.google.android.gms.location.places.internal.zzaf;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.location.places.internal.zzq;
import com.google.android.gms.location.places.internal.zzs;
import com.google.android.gms.location.places.internal.zzz;

@Deprecated
public class Places {
    public static final Api<PlacesOptions> GEO_DATA_API;
    @Deprecated
    public static final GeoDataApi GeoDataApi = new zzh();
    public static final Api<PlacesOptions> PLACE_DETECTION_API;
    @Deprecated
    public static final PlaceDetectionApi PlaceDetectionApi = new zzz();
    private static final Api.ClientKey<zzq> zzaq;
    private static final Api.ClientKey<zzae> zzar;

    private Places() {
    }

    @Deprecated
    public static PlaceDetectionClient getPlaceDetectionClient(Activity activity) {
        return getPlaceDetectionClient(activity, (PlacesOptions) null);
    }

    @Deprecated
    public static PlaceDetectionClient getPlaceDetectionClient(Activity activity, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new PlaceDetectionClient(activity, placesOptions);
    }

    @Deprecated
    public static PlaceDetectionClient getPlaceDetectionClient(Context context) {
        return getPlaceDetectionClient(context, (PlacesOptions) null);
    }

    @Deprecated
    public static PlaceDetectionClient getPlaceDetectionClient(Context context, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new PlaceDetectionClient(context, placesOptions);
    }

    @Deprecated
    public static GeoDataClient getGeoDataClient(Activity activity) {
        return getGeoDataClient(activity, (PlacesOptions) null);
    }

    @Deprecated
    public static GeoDataClient getGeoDataClient(Activity activity, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new GeoDataClient(activity, placesOptions);
    }

    @Deprecated
    public static GeoDataClient getGeoDataClient(Context context) {
        return getGeoDataClient(context, (PlacesOptions) null);
    }

    @Deprecated
    public static GeoDataClient getGeoDataClient(Context context, PlacesOptions placesOptions) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new GeoDataClient(context, placesOptions);
    }

    static {
        Api.ClientKey<zzq> clientKey = new Api.ClientKey<>();
        zzaq = clientKey;
        Api.ClientKey<zzae> clientKey2 = new Api.ClientKey<>();
        zzar = clientKey2;
        GEO_DATA_API = new Api<>("Places.GEO_DATA_API", new zzs(), clientKey);
        PLACE_DETECTION_API = new Api<>("Places.PLACE_DETECTION_API", new zzaf(), clientKey2);
    }
}
