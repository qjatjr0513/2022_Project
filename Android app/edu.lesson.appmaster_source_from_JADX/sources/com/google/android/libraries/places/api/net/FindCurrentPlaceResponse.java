package com.google.android.libraries.places.api.net;

import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.internal.zzge;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class FindCurrentPlaceResponse {
    public static FindCurrentPlaceResponse newInstance(List<PlaceLikelihood> placeLikelihoods) {
        return new zzp(zzge.zzk(placeLikelihoods));
    }

    public abstract List<PlaceLikelihood> getPlaceLikelihoods();
}
