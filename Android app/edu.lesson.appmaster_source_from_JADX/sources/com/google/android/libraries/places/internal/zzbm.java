package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.core.ServerValues;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzbm extends zzbr<Object, FindCurrentPlaceRequest> {
    private final Location zza;
    private final zzge<zzs> zzb;

    zzbm(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzge<zzs> zzge, Locale locale, String str, boolean z, zzdl zzdl) {
        super(findCurrentPlaceRequest, locale, str, false, zzdl);
        this.zza = location;
        this.zzb = zzge;
    }

    /* access modifiers changed from: protected */
    public final String zze() {
        return "findplacefromuserlocation/json";
    }

    public final Map<String, String> zzf() {
        HashMap hashMap = new HashMap();
        zzg(hashMap, FirebaseAnalytics.Param.LOCATION, zzcj.zzc(this.zza), (Object) null);
        zzg(hashMap, "wifiaccesspoints", zzcj.zzg(this.zzb, 4000), (Object) null);
        zzg(hashMap, "precision", zzcj.zza(this.zza), (Object) null);
        zzg(hashMap, ServerValues.NAME_OP_TIMESTAMP, Long.valueOf(this.zza.getTime()), (Object) null);
        zzg(hashMap, "fields", zzck.zza(((FindCurrentPlaceRequest) zzb()).getPlaceFields()), (Object) null);
        return hashMap;
    }
}
