package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzabd<T> implements zzabl<T> {
    private final zzaaz zza;
    private final zzabz<?, ?> zzb;
    private final boolean zzc;
    private final zzzk<?> zzd;

    private zzabd(zzabz<?, ?> zzabz, zzzk<?> zzzk, zzaaz zzaaz) {
        this.zzb = zzabz;
        this.zzc = zzzk.zzh(zzaaz);
        this.zzd = zzzk;
        this.zza = zzaaz;
    }

    static <T> zzabd<T> zzc(zzabz<?, ?> zzabz, zzzk<?> zzzk, zzaaz zzaaz) {
        return new zzabd<>(zzabz, zzzk, zzaaz);
    }

    public final int zza(T t) {
        zzabz<?, ?> zzabz = this.zzb;
        int zzb2 = zzabz.zzb(zzabz.zzd(t));
        if (!this.zzc) {
            return zzb2;
        }
        this.zzd.zza(t);
        throw null;
    }

    public final int zzb(T t) {
        int hashCode = this.zzb.zzd(t).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(t);
        throw null;
    }

    public final T zze() {
        return this.zza.zzB().zzm();
    }

    public final void zzf(T t) {
        this.zzb.zzm(t);
        this.zzd.zze(t);
    }

    public final void zzg(T t, T t2) {
        zzabn.zzF(this.zzb, t, t2);
        if (this.zzc) {
            zzabn.zzE(this.zzd, t, t2);
        }
    }

    public final void zzh(T t, zzabk zzabk, zzzj zzzj) throws IOException {
        boolean z;
        zzabz<?, ?> zzabz = this.zzb;
        zzzk<?> zzzk = this.zzd;
        Object zzc2 = zzabz.zzc(t);
        zzzo<?> zzb2 = zzzk.zzb(t);
        while (zzabk.zzc() != Integer.MAX_VALUE) {
            int zzd2 = zzabk.zzd();
            if (zzd2 != 11) {
                if ((zzd2 & 7) == 2) {
                    Object zzc3 = zzzk.zzc(zzzj, this.zza, zzd2 >>> 3);
                    if (zzc3 != null) {
                        zzzk.zzf(zzabk, zzc3, zzzj, zzb2);
                    } else {
                        z = zzabz.zzp(zzc2, zzabk);
                    }
                } else {
                    z = zzabk.zzO();
                }
                if (!z) {
                    break;
                }
            } else {
                int i = 0;
                Object obj = null;
                zzyu zzyu = null;
                while (true) {
                    try {
                        if (zzabk.zzc() == Integer.MAX_VALUE) {
                            break;
                        }
                        int zzd3 = zzabk.zzd();
                        if (zzd3 == 16) {
                            i = zzabk.zzj();
                            obj = zzzk.zzc(zzzj, this.zza, i);
                        } else if (zzd3 == 26) {
                            if (obj != null) {
                                zzzk.zzf(zzabk, obj, zzzj, zzb2);
                            } else {
                                zzyu = zzabk.zzp();
                            }
                        } else if (!zzabk.zzO()) {
                            break;
                        }
                    } catch (Throwable th) {
                        zzabz.zzn(t, zzc2);
                        throw th;
                    }
                }
                if (zzabk.zzd() != 12) {
                    throw zzaae.zzb();
                } else if (zzyu != null) {
                    if (obj != null) {
                        zzzk.zzg(zzyu, obj, zzzj, zzb2);
                    } else {
                        zzabz.zzk(zzc2, i, zzyu);
                    }
                }
            }
        }
        zzabz.zzn(t, zzc2);
    }

    public final void zzi(T t, byte[] bArr, int i, int i2, zzyh zzyh) throws IOException {
        zzzw zzzw = (zzzw) t;
        if (zzzw.zzc == zzaca.zzc()) {
            zzzw.zzc = zzaca.zze();
        }
        zzzt zzzt = (zzzt) t;
        throw null;
    }

    public final boolean zzj(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(t);
        this.zzd.zza(t2);
        throw null;
    }

    public final boolean zzk(T t) {
        this.zzd.zza(t);
        throw null;
    }

    public final void zzn(T t, zzzf zzzf) throws IOException {
        this.zzd.zza(t);
        throw null;
    }
}
