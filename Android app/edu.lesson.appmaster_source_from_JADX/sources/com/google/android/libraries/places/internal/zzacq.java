package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzacq implements zzacx {
    private final zzacx[] zza;

    zzacq(zzacx... zzacxArr) {
        this.zza = zzacxArr;
    }

    public final zzacw zzb(Class<?> cls) {
        zzacx[] zzacxArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzacx zzacx = zzacxArr[i];
            if (zzacx.zzc(cls)) {
                return zzacx.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }

    public final boolean zzc(Class<?> cls) {
        zzacx[] zzacxArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzacxArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
