package com.google.android.libraries.places.api.net;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzge;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class FindAutocompletePredictionsResponse {
    public static FindAutocompletePredictionsResponse newInstance(List<AutocompletePrediction> autocompletePredictions) {
        return new zzl(zzge.zzk(autocompletePredictions));
    }

    public abstract List<AutocompletePrediction> getAutocompletePredictions();
}
