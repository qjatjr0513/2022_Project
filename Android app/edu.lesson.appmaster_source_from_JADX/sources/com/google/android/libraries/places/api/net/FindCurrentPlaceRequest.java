package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzcz;
import com.google.android.libraries.places.internal.zzge;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class FindCurrentPlaceRequest implements zzcz {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public FindCurrentPlaceRequest build() {
            zza(zzge.zzk(zzb().getPlaceFields()));
            return zzb();
        }

        public abstract CancellationToken getCancellationToken();

        public abstract Builder setCancellationToken(CancellationToken cancellationToken);

        /* access modifiers changed from: package-private */
        public abstract Builder zza(List<Place.Field> list);

        /* access modifiers changed from: package-private */
        public abstract FindCurrentPlaceRequest zzb();
    }

    public static Builder builder(List<Place.Field> placeFields) {
        zzm zzm = new zzm();
        zzm.zza(placeFields);
        return zzm;
    }

    public static FindCurrentPlaceRequest newInstance(List<Place.Field> placeFields) {
        return builder(placeFields).build();
    }

    public abstract CancellationToken getCancellationToken();

    public abstract List<Place.Field> getPlaceFields();
}
