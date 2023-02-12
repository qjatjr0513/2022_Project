package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzkt implements zzla {
    private final zzla[] zza;

    zzkt(zzla... zzlaArr) {
        this.zza = zzlaArr;
    }

    public final zzkz zzb(Class<?> cls) {
        zzla[] zzlaArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzla zzla = zzlaArr[i];
            if (zzla.zzc(cls)) {
                return zzla.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }

    public final boolean zzc(Class<?> cls) {
        zzla[] zzlaArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzlaArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
