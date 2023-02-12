package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.TypeFilter;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzcl {
    private static final zzgg<TypeFilter, String> zza;

    static {
        zzgf zzgf = new zzgf();
        zzgf.zza(TypeFilter.ADDRESS, "address");
        zzgf.zza(TypeFilter.CITIES, "(cities)");
        zzgf.zza(TypeFilter.ESTABLISHMENT, "establishment");
        zzgf.zza(TypeFilter.GEOCODE, "geocode");
        zzgf.zza(TypeFilter.REGIONS, "(regions)");
        zza = zzgf.zzb();
    }

    public static String zza(TypeFilter typeFilter) {
        return zza.get(typeFilter);
    }
}
