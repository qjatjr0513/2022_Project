package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzaet<T> implements zzaeu<T> {
    private static final Object zza = new Object();
    private volatile zzaeu<T> zzb;
    private volatile Object zzc = zza;

    private zzaet(zzaeu<T> zzaeu) {
        this.zzb = zzaeu;
    }

    public static <P extends zzaeu<T>, T> zzaeu<T> zza(P p) {
        return new zzaet(p);
    }

    public final T zzb() {
        T t = this.zzc;
        if (t != zza) {
            return t;
        }
        if (this.zzb == null) {
            return this.zzc;
        }
        T zze = new zze();
        this.zzc = zze;
        this.zzb = null;
        return zze;
    }
}
