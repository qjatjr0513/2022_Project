package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzdk {
    public static zzjr zza(zzwh zzwh) {
        zzjp zza = zzjr.zza();
        zza.zzb(1);
        zza.zza(zzwh);
        return (zzjr) zza.zzt();
    }

    public static zzwc zzb(zzdf zzdf) {
        int i;
        int zzc = zzdf.zzc();
        int i2 = zzc - 1;
        if (zzc != 0) {
            switch (i2) {
                case 0:
                    i = 2;
                    break;
                case 1:
                    i = 4;
                    break;
                default:
                    i = 1;
                    break;
            }
            zzwc zza = zzwh.zza();
            zzjt zza2 = zzjy.zza();
            zza2.zza(zzdf.zzb());
            zza2.zzb(zzdf.zza());
            zza.zzb((zzjy) zza2.zzt());
            zza.zzf(true);
            zza.zzk(i);
            zza.zzi("2.5.0");
            return zza;
        }
        throw null;
    }
}
