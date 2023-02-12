package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzbe extends zzbr<Object, FetchPlaceRequest> {
    zzbe(FetchPlaceRequest fetchPlaceRequest, Locale locale, String str, boolean z, zzdl zzdl) {
        super(fetchPlaceRequest, locale, str, false, zzdl);
    }

    /* access modifiers changed from: protected */
    public final String zze() {
        return "details/json";
    }

    public final Map<String, String> zzf() {
        FetchPlaceRequest fetchPlaceRequest = (FetchPlaceRequest) zzb();
        HashMap hashMap = new HashMap();
        zzg(hashMap, "placeid", fetchPlaceRequest.getPlaceId(), (Object) null);
        zzg(hashMap, "sessiontoken", fetchPlaceRequest.getSessionToken(), (Object) null);
        zzg(hashMap, "fields", zzck.zza(fetchPlaceRequest.getPlaceFields()), (Object) null);
        return hashMap;
    }
}
