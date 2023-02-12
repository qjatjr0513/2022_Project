package com.google.android.libraries.places.api.net;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzcz;
import com.google.android.libraries.places.internal.zzge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class FindAutocompletePredictionsRequest implements zzcz {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public FindAutocompletePredictionsRequest build() {
            setCountries((List<String>) zzge.zzk(getCountries()));
            return zza();
        }

        public abstract CancellationToken getCancellationToken();

        public abstract List<String> getCountries();

        public abstract LocationBias getLocationBias();

        public abstract LocationRestriction getLocationRestriction();

        public abstract LatLng getOrigin();

        public abstract String getQuery();

        public abstract AutocompleteSessionToken getSessionToken();

        public abstract TypeFilter getTypeFilter();

        public abstract Builder setCancellationToken(CancellationToken cancellationToken);

        public abstract Builder setCountries(List<String> list);

        public Builder setCountries(String... countries) {
            return setCountries((List<String>) zzge.zzl(countries));
        }

        public Builder setCountry(String countryCode) {
            setCountries((List<String>) countryCode == null ? zzge.zzm() : zzge.zzn(countryCode));
            return this;
        }

        public abstract Builder setLocationBias(LocationBias locationBias);

        public abstract Builder setLocationRestriction(LocationRestriction locationRestriction);

        public abstract Builder setOrigin(LatLng latLng);

        public abstract Builder setQuery(String str);

        public abstract Builder setSessionToken(AutocompleteSessionToken autocompleteSessionToken);

        public abstract Builder setTypeFilter(TypeFilter typeFilter);

        /* access modifiers changed from: package-private */
        public abstract FindAutocompletePredictionsRequest zza();
    }

    public static Builder builder() {
        zzi zzi = new zzi();
        zzi.setCountries(new ArrayList());
        return zzi;
    }

    public static FindAutocompletePredictionsRequest newInstance(String query) {
        Builder builder = builder();
        builder.setQuery(query);
        return builder.build();
    }

    public abstract CancellationToken getCancellationToken();

    public abstract List<String> getCountries();

    public String getCountry() {
        T t;
        if (getCountries().size() <= 1) {
            Iterator<T> it = getCountries().iterator();
            if (it.hasNext()) {
                t = it.next();
            } else {
                t = null;
            }
            return (String) t;
        }
        throw new UnsupportedOperationException("Multiple countries found in this request - use getCountries() instead of getCountry().");
    }

    public abstract LocationBias getLocationBias();

    public abstract LocationRestriction getLocationRestriction();

    public abstract LatLng getOrigin();

    public abstract String getQuery();

    public abstract AutocompleteSessionToken getSessionToken();

    public abstract TypeFilter getTypeFilter();
}
