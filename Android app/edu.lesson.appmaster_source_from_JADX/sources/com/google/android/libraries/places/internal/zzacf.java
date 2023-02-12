package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class zzacf {
    private static final zzabh zzb = zzabh.zza();
    protected volatile zzacz zza;
    private volatile zzaax zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzacf)) {
            return false;
        }
        zzacf zzacf = (zzacf) obj;
        zzacz zzacz = this.zza;
        zzacz zzacz2 = zzacf.zza;
        if (zzacz == null && zzacz2 == null) {
            return zzb().equals(zzacf.zzb());
        }
        if (zzacz != null && zzacz2 != null) {
            return zzacz.equals(zzacz2);
        }
        if (zzacz != null) {
            zzacf.zzc(zzacz.zzw());
            return zzacz.equals(zzacf.zza);
        }
        zzc(zzacz2.zzw());
        return this.zza.equals(zzacz2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzaau) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzv();
        }
        return 0;
    }

    public final zzaax zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzaax zzaax = this.zzc;
                return zzaax;
            }
            if (this.zza == null) {
                this.zzc = zzaax.zzb;
            } else {
                this.zzc = this.zza.zzs();
            }
            zzaax zzaax2 = this.zzc;
            return zzaax2;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzacz zzacz) {
        if (this.zza == null) {
            synchronized (this) {
                if (this.zza == null) {
                    try {
                        this.zza = zzacz;
                        this.zzc = zzaax.zzb;
                    } catch (zzacc e) {
                        this.zza = zzacz;
                        this.zzc = zzaax.zzb;
                    }
                }
            }
        }
    }
}
