package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzpd implements zzpc {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Double> zzb;
    public static final zzhu<Long> zzc;
    public static final zzhu<Long> zzd;
    public static final zzhu<String> zze;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.test.boolean_flag", false);
        zzb = zzhr.zzb("measurement.test.double_flag", -3.0d);
        zzc = zzhr.zzc("measurement.test.int_flag", -2);
        zzd = zzhr.zzc("measurement.test.long_flag", -1);
        zze = zzhr.zzd("measurement.test.string_flag", "---");
    }

    public final double zza() {
        return zzb.zzb().doubleValue();
    }

    public final long zzb() {
        return zzc.zzb().longValue();
    }

    public final long zzc() {
        return zzd.zzb().longValue();
    }

    public final String zzd() {
        return zze.zzb();
    }

    public final boolean zze() {
        return zza.zzb().booleanValue();
    }
}
