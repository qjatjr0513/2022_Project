package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzor implements zzoq {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Boolean> zzc;
    public static final zzhu<Long> zzd;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zzhr.zze("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzc = zzhr.zze("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zzhr.zzc("measurement.id.sdk.collection.last_deep_link_referrer2", 0);
    }

    public final boolean zza() {
        return zza.zzb().booleanValue();
    }

    public final boolean zzb() {
        return zzc.zzb().booleanValue();
    }
}
