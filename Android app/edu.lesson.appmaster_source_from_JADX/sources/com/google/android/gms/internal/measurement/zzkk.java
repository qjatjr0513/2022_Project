package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public class zzkk {
    private static final zzjj zzb = zzjj.zza();
    protected volatile zzlc zza;
    private volatile zzix zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkk)) {
            return false;
        }
        zzkk zzkk = (zzkk) obj;
        zzlc zzlc = this.zza;
        zzlc zzlc2 = zzkk.zza;
        if (zzlc == null && zzlc2 == null) {
            return zzb().equals(zzkk.zzb());
        }
        if (zzlc != null && zzlc2 != null) {
            return zzlc.equals(zzlc2);
        }
        if (zzlc != null) {
            zzkk.zzc(zzlc.zzbL());
            return zzlc.equals(zzkk.zza);
        }
        zzc(zzlc2.zzbL());
        return this.zza.equals(zzlc2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zziv) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzbt();
        }
        return 0;
    }

    public final zzix zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzix zzix = this.zzc;
                return zzix;
            }
            if (this.zza == null) {
                this.zzc = zzix.zzb;
            } else {
                this.zzc = this.zza.zzbp();
            }
            zzix zzix2 = this.zzc;
            return zzix2;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzlc zzlc) {
        if (this.zza == null) {
            synchronized (this) {
                if (this.zza == null) {
                    try {
                        this.zza = zzlc;
                        this.zzc = zzix.zzb;
                    } catch (zzkh e) {
                        this.zza = zzlc;
                        this.zzc = zzix.zzb;
                    }
                }
            }
        }
    }
}
