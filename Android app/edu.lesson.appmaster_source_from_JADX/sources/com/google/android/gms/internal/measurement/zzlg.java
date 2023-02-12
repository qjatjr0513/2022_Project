package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzlg<T> implements zzln<T> {
    private final zzlc zza;
    private final zzmb<?, ?> zzb;
    private final boolean zzc;
    private final zzjk<?> zzd;

    private zzlg(zzmb<?, ?> zzmb, zzjk<?> zzjk, zzlc zzlc) {
        this.zzb = zzmb;
        this.zzc = zzjk.zzc(zzlc);
        this.zzd = zzjk;
        this.zza = zzlc;
    }

    static <T> zzlg<T> zzc(zzmb<?, ?> zzmb, zzjk<?> zzjk, zzlc zzlc) {
        return new zzlg<>(zzmb, zzjk, zzlc);
    }

    public final int zza(T t) {
        zzmb<?, ?> zzmb = this.zzb;
        int zzb2 = zzmb.zzb(zzmb.zzc(t));
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

    public final T zze() {
        return this.zza.zzbC().zzaC();
    }

    public final void zzf(T t) {
        this.zzb.zzg(t);
        this.zzd.zzb(t);
    }

    public final void zzg(T t, T t2) {
        zzlp.zzF(this.zzb, t, t2);
        if (this.zzc) {
            zzlp.zzE(this.zzd, t, t2);
        }
    }

    public final void zzh(T t, byte[] bArr, int i, int i2, zzik zzik) throws IOException {
        zzjx zzjx = (zzjx) t;
        if (zzjx.zzc == zzmc.zzc()) {
            zzjx.zzc = zzmc.zze();
        }
        zzju zzju = (zzju) t;
        throw null;
    }

    public final boolean zzi(T t, T t2) {
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

    public final boolean zzj(T t) {
        this.zzd.zza(t);
        throw null;
    }

    public final void zzm(T t, zzjf zzjf) throws IOException {
        this.zzd.zza(t);
        throw null;
    }
}
