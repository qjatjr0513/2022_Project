package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zznz implements zzny {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zze("measurement.client.consent.suppress_1p_in_ga4f_install", true);
        zzb = zzhr.zze("measurement.client.consent.gmpappid_worker_thread_fix", true);
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
