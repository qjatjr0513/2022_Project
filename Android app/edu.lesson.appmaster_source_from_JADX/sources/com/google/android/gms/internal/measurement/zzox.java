package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzox implements zzow {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Long> zzb;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.validation.internal_limits_internal_event_params", false);
        zzb = zzhr.zzc("measurement.id.validation.internal_limits_internal_event_params", 0);
    }

    public final boolean zza() {
        return zza.zzb().booleanValue();
    }
}
