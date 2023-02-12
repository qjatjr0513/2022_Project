package com.google.android.gms.location;

import com.google.android.gms.common.Feature;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public final class zzu {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature zze;
    public static final Feature[] zzf;

    static {
        Feature feature = new Feature("name_ulr_private", 1);
        zza = feature;
        Feature feature2 = new Feature("name_sleep_segment_request", 1);
        zzb = feature2;
        Feature feature3 = new Feature("support_context_feature_id", 1);
        zzc = feature3;
        Feature feature4 = new Feature("get_current_location", 1);
        zzd = feature4;
        Feature feature5 = new Feature("get_last_activity_feature_id", 1);
        zze = feature5;
        zzf = new Feature[]{feature, feature2, feature3, feature4, feature5};
    }
}
