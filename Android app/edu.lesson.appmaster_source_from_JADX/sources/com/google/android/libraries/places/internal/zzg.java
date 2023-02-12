package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzg {
    static zzg zza = null;
    private final zzh zzb;

    private zzg(zzh zzh) {
        this.zzb = zzh;
    }

    public static zzg zza() {
        zzg zzg = zza;
        if (zzg != null) {
            zzh zzh = zzg.zzb;
        }
        zzg zzg2 = new zzg(new zzf());
        zza = zzg2;
        return zzg2;
    }
}
