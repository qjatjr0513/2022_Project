package com.google.android.gms.internal.places;

final class zzbz implements zzch {
    private zzch[] zzki;

    zzbz(zzch... zzchArr) {
        this.zzki = zzchArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzch zzb : this.zzki) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzci zzc(Class<?> cls) {
        for (zzch zzch : this.zzki) {
            if (zzch.zzb(cls)) {
                return zzch.zzc(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
