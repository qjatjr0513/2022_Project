package com.google.android.libraries.places.api.net;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzi extends FindAutocompletePredictionsRequest.Builder {
    private String zza;
    private LocationBias zzb;
    private LocationRestriction zzc;
    private LatLng zzd;
    private List<String> zze;
    private AutocompleteSessionToken zzf;
    private TypeFilter zzg;
    private CancellationToken zzh;

    zzi() {
    }

    public final CancellationToken getCancellationToken() {
        return this.zzh;
    }

    public final List<String> getCountries() {
        List<String> list = this.zze;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"countries\" has not been set");
    }

    public final LocationBias getLocationBias() {
        return this.zzb;
    }

    public final LocationRestriction getLocationRestriction() {
        return this.zzc;
    }

    public final LatLng getOrigin() {
        return this.zzd;
    }

    public final String getQuery() {
        return this.zza;
    }

    public final AutocompleteSessionToken getSessionToken() {
        return this.zzf;
    }

    public final TypeFilter getTypeFilter() {
        return this.zzg;
    }

    public final FindAutocompletePredictionsRequest.Builder setCancellationToken(CancellationToken cancellationToken) {
        this.zzh = cancellationToken;
        return this;
    }

    public final FindAutocompletePredictionsRequest.Builder setCountries(List<String> list) {
        if (list != null) {
            this.zze = list;
            return this;
        }
        throw new NullPointerException("Null countries");
    }

    public final FindAutocompletePredictionsRequest.Builder setLocationBias(LocationBias locationBias) {
        this.zzb = locationBias;
        return this;
    }

    public final FindAutocompletePredictionsRequest.Builder setLocationRestriction(LocationRestriction locationRestriction) {
        this.zzc = locationRestriction;
        return this;
    }

    public final FindAutocompletePredictionsRequest.Builder setOrigin(LatLng latLng) {
        this.zzd = latLng;
        return this;
    }

    public final FindAutocompletePredictionsRequest.Builder setQuery(String str) {
        this.zza = str;
        return this;
    }

    public final FindAutocompletePredictionsRequest.Builder setSessionToken(AutocompleteSessionToken autocompleteSessionToken) {
        this.zzf = autocompleteSessionToken;
        return this;
    }

    public final FindAutocompletePredictionsRequest.Builder setTypeFilter(TypeFilter typeFilter) {
        this.zzg = typeFilter;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final FindAutocompletePredictionsRequest zza() {
        List<String> list = this.zze;
        if (list != null) {
            return new zzk(this.zza, this.zzb, this.zzc, this.zzd, list, this.zzf, this.zzg, this.zzh, (zzj) null);
        }
        throw new IllegalStateException("Missing required properties: countries");
    }
}
