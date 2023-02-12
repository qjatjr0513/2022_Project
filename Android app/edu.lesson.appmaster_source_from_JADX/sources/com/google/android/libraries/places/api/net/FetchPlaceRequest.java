package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzcz;
import com.google.android.libraries.places.internal.zzge;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class FetchPlaceRequest implements zzcz {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public FetchPlaceRequest build() {
            zza(zzge.zzk(zzc().getPlaceFields()));
            return zzc();
        }

        public abstract CancellationToken getCancellationToken();

        public abstract AutocompleteSessionToken getSessionToken();

        public abstract Builder setCancellationToken(CancellationToken cancellationToken);

        public abstract Builder setSessionToken(AutocompleteSessionToken autocompleteSessionToken);

        /* access modifiers changed from: package-private */
        public abstract Builder zza(List<Place.Field> list);

        /* access modifiers changed from: package-private */
        public abstract FetchPlaceRequest zzc();
    }

    public static Builder builder(String placeId, List<Place.Field> placeFields) {
        zze zze = new zze();
        zze.zzb(placeId);
        zze.zza(placeFields);
        return zze;
    }

    public static FetchPlaceRequest newInstance(String placeId, List<Place.Field> placeFields) {
        return builder(placeId, placeFields).build();
    }

    public abstract CancellationToken getCancellationToken();

    public abstract List<Place.Field> getPlaceFields();

    public abstract String getPlaceId();

    public abstract AutocompleteSessionToken getSessionToken();
}
