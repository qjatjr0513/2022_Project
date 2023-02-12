package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzoc implements zzob {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Boolean> zzc;
    public static final zzhu<Boolean> zzd;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.service.audience.fix_skip_audience_with_failed_filters", true);
        zzb = zzhr.zze("measurement.audience.refresh_event_count_filters_timestamp", false);
        zzc = zzhr.zze("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false);
        zzd = zzhr.zze("measurement.audience.use_bundle_timestamp_for_event_count_filters", false);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zzb.zzb().booleanValue();
    }

    public final boolean zzc() {
        return zzc.zzb().booleanValue();
    }

    public final boolean zzd() {
        return zzd.zzb().booleanValue();
    }
}
