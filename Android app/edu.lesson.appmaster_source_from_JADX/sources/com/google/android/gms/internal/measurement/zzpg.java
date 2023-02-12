package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzpg implements zzpf {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.module.pixie.ees", true);
        zzb = zzhr.zze("measurement.module.pixie.fix_array", true);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }

    public final boolean zzc() {
        return zzb.zzb().booleanValue();
    }
}
