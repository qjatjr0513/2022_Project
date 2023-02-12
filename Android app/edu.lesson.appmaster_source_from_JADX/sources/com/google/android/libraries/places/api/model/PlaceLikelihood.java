package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import com.google.android.libraries.places.internal.zzfr;
import com.google.android.libraries.places.internal.zzgp;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class PlaceLikelihood implements Parcelable {
    public static final double LIKELIHOOD_MAX_VALUE = 1.0d;
    public static final double LIKELIHOOD_MIN_VALUE = 0.0d;

    public static PlaceLikelihood newInstance(Place place, double likelihood) {
        Double valueOf = Double.valueOf(0.0d);
        Double valueOf2 = Double.valueOf(1.0d);
        zzgp zzc = zzgp.zzc(valueOf, valueOf2);
        Double valueOf3 = Double.valueOf(likelihood);
        if (zzc.zze(valueOf3)) {
            return new zzat(place, likelihood);
        }
        throw new IllegalArgumentException(zzfr.zza("Likelihood must not be out-of-range: %s to %s, but was: %s.", valueOf, valueOf2, valueOf3));
    }

    public abstract double getLikelihood();

    public abstract Place getPlace();
}
