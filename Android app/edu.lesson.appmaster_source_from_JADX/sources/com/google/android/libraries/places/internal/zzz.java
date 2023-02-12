package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public interface zzz {
    Task<FetchPhotoResponse> zza(FetchPhotoRequest fetchPhotoRequest);

    Task<FetchPlaceResponse> zzb(FetchPlaceRequest fetchPlaceRequest);

    Task<FindAutocompletePredictionsResponse> zzc(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest);

    Task<FindCurrentPlaceResponse> zzd(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzge<zzs> zzge);
}
