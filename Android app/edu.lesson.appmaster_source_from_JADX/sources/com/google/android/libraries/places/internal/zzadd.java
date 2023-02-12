package com.google.android.libraries.places.internal;

import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzadd<T> implements zzadk<T> {
    private final zzacz zza;
    private final zzaeb<?, ?> zzb;
    private final boolean zzc;
    private final zzabi<?> zzd;

    private zzadd(zzaeb<?, ?> zzaeb, zzabi<?> zzabi, zzacz zzacz) {
        this.zzb = zzaeb;
        this.zzc = zzabi.zzc(zzacz);
        this.zzd = zzabi;
        this.zza = zzacz;
    }

    static <T> zzadd<T> zzg(zzaeb<?, ?> zzaeb, zzabi<?> zzabi, zzacz zzacz) {
        return new zzadd<>(zzaeb, zzabi, zzacz);
    }

    public final int zza(T t) {
        zzaeb<?, ?> zzaeb = this.zzb;
        int zzb2 = zzaeb.zzb(zzaeb.zzc(t));
        if (!this.zzc) {
            return zzb2;
        }
        this.zzd.zza(t);
        throw null;
    }

    public final int zzb(T t) {
        int hashCode = this.zzb.zzc(t).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(t);
        throw null;
    }

    public final void zzc(T t) {
        this.zzb.zze(t);
        this.zzd.zzb(t);
    }

    public final void zzd(T t, T t2) {
        zzadm.zzD(this.zzb, t, t2);
        if (this.zzc) {
            zzadm.zzC(this.zzd, t, t2);
        }
    }

    public final boolean zze(T t, T t2) {
        if (!this.zzb.zzc(t).equals(this.zzb.zzc(t2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(t);
        this.zzd.zza(t2);
        throw null;
    }

    public final boolean zzf(T t) {
        this.zzd.zza(t);
        throw null;
    }

    public final void zzi(T t, zzabg zzabg) throws IOException {
        this.zzd.zza(t);
        throw null;
    }
}
