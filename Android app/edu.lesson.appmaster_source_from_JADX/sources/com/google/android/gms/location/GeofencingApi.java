package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public interface GeofencingApi {
    PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent);

    @Deprecated
    PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, List<Geofence> list, PendingIntent pendingIntent);

    PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, List<String> list);
}
