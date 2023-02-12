package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.location.places.PlacesOptions;

public final class zzs extends Api.AbstractClientBuilder<zzq, PlacesOptions> {
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        PlacesOptions placesOptions;
        PlacesOptions placesOptions2 = (PlacesOptions) obj;
        if (placesOptions2 == null) {
            placesOptions = new PlacesOptions.Builder().build();
        } else {
            placesOptions = placesOptions2;
        }
        return new zzq(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, context.getPackageName(), placesOptions);
    }
}
