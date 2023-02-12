package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzel implements zzee {
    public static final /* synthetic */ int zza = 0;
    private static final zzge<Place.Field> zzb = zzge.zzo(Place.Field.ID, Place.Field.TYPES);
    private final PlacesClient zzc;
    private final zzdx zzd;
    private final AutocompleteSessionToken zze;
    private zzeh zzf;
    private zzei zzg;

    public zzel(PlacesClient placesClient, zzdx zzdx, AutocompleteSessionToken autocompleteSessionToken) {
        this.zzc = placesClient;
        this.zzd = zzdx;
        this.zze = autocompleteSessionToken;
    }

    public final Task<FetchPlaceResponse> zza(AutocompletePrediction autocompletePrediction) {
        List<Place.Type> list = null;
        if (zzb.containsAll(this.zzd.zzj())) {
            Place.Builder builder = Place.builder();
            builder.setId(autocompletePrediction.getPlaceId());
            if (!autocompletePrediction.getPlaceTypes().isEmpty()) {
                list = autocompletePrediction.getPlaceTypes();
            }
            builder.setTypes(list);
            return Tasks.forResult(FetchPlaceResponse.newInstance(builder.build()));
        }
        zzei zzei = this.zzg;
        if (zzei != null) {
            if (zzei.zzb().equals(autocompletePrediction.getPlaceId())) {
                Task<FetchPlaceResponse> zzc2 = zzei.zzc();
                if (zzc2 != null) {
                    return zzc2;
                }
                throw null;
            }
            zzei.zza().cancel();
        }
        zzed zzed = new zzed(new CancellationTokenSource(), autocompletePrediction.getPlaceId());
        this.zzg = zzed;
        PlacesClient placesClient = this.zzc;
        FetchPlaceRequest.Builder builder2 = FetchPlaceRequest.builder(autocompletePrediction.getPlaceId(), this.zzd.zzj());
        builder2.setSessionToken(this.zze);
        builder2.setCancellationToken(zzed.zza().getToken());
        Task<TContinuationResult> continueWithTask = placesClient.fetchPlace(builder2.build()).continueWithTask(new zzeg(zzed));
        zzed.zzd(continueWithTask);
        return continueWithTask;
    }

    public final Task<FindAutocompletePredictionsResponse> zzb(String str) {
        zzfm.zzd(!TextUtils.isEmpty(str));
        zzeh zzeh = this.zzf;
        if (zzeh != null) {
            if (zzeh.zzb().equals(str)) {
                Task<FindAutocompletePredictionsResponse> zzc2 = zzeh.zzc();
                if (zzc2 != null) {
                    return zzc2;
                }
                throw null;
            }
            zzeh.zza().cancel();
        }
        zzec zzec = new zzec(new CancellationTokenSource(), str);
        this.zzf = zzec;
        PlacesClient placesClient = this.zzc;
        FindAutocompletePredictionsRequest.Builder builder = FindAutocompletePredictionsRequest.builder();
        builder.setQuery(str);
        builder.setLocationBias(this.zzd.zzc());
        builder.setLocationRestriction(this.zzd.zzd());
        builder.setCountries((List<String>) this.zzd.zzi());
        builder.setTypeFilter(this.zzd.zze());
        builder.setSessionToken(this.zze);
        builder.setCancellationToken(zzec.zza().getToken());
        Task<TContinuationResult> continueWithTask = placesClient.findAutocompletePredictions(builder.build()).continueWithTask(new zzef(zzec));
        zzec.zzd(continueWithTask);
        return continueWithTask;
    }

    public final void zzc() {
        zzeh zzeh = this.zzf;
        if (zzeh != null) {
            zzeh.zza().cancel();
        }
        zzei zzei = this.zzg;
        if (zzei != null) {
            zzei.zza().cancel();
        }
        this.zzf = null;
        this.zzg = null;
    }
}
