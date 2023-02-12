package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzbi extends zzbr<Object, FindAutocompletePredictionsRequest> {
    zzbi(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, Locale locale, String str, boolean z, zzdl zzdl) {
        super(findAutocompletePredictionsRequest, locale, str, false, zzdl);
    }

    /* access modifiers changed from: protected */
    public final String zze() {
        return "autocomplete/json";
    }

    public final Map<String, String> zzf() {
        String str;
        FindAutocompletePredictionsRequest findAutocompletePredictionsRequest = (FindAutocompletePredictionsRequest) zzb();
        HashMap hashMap = new HashMap();
        String query = findAutocompletePredictionsRequest.getQuery();
        if (query == null) {
            str = null;
        } else {
            str = query.replaceFirst("^\\s+", "").replaceFirst("\\s+$", " ");
        }
        zzg(hashMap, "input", str, (Object) null);
        zzg(hashMap, "types", zzcl.zza(findAutocompletePredictionsRequest.getTypeFilter()), (Object) null);
        zzg(hashMap, "sessiontoken", findAutocompletePredictionsRequest.getSessionToken(), (Object) null);
        zzg(hashMap, "origin", zzcj.zzd(findAutocompletePredictionsRequest.getOrigin()), (Object) null);
        zzg(hashMap, "locationbias", zzcj.zze(findAutocompletePredictionsRequest.getLocationBias()), (Object) null);
        zzg(hashMap, "locationrestriction", zzcj.zzf(findAutocompletePredictionsRequest.getLocationRestriction()), (Object) null);
        zzg(hashMap, "components", zzcj.zzb(findAutocompletePredictionsRequest.getCountries()), (Object) null);
        return hashMap;
    }
}
