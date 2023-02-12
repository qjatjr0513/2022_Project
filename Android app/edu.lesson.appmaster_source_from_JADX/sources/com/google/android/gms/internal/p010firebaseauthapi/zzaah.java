package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaah */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class zzaah {
    private static final zzzj zzb = zzzj.zza();
    protected volatile zzaaz zza;
    private volatile zzyu zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaah)) {
            return false;
        }
        zzaah zzaah = (zzaah) obj;
        zzaaz zzaaz = this.zza;
        zzaaz zzaaz2 = zzaah.zza;
        if (zzaaz == null && zzaaz2 == null) {
            return zzb().equals(zzaah.zzb());
        }
        if (zzaaz != null && zzaaz2 != null) {
            return zzaaz.equals(zzaaz2);
        }
        if (zzaaz != null) {
            zzaah.zzc(zzaaz.zzI());
            return zzaaz.equals(zzaah.zza);
        }
        zzc(zzaaz2.zzI());
        return this.zza.equals(zzaaz2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzys) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzs();
        }
        return 0;
    }

    public final zzyu zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzyu zzyu = this.zzc;
                return zzyu;
            }
            if (this.zza == null) {
                this.zzc = zzyu.zzb;
            } else {
                this.zzc = this.zza.zzo();
            }
            zzyu zzyu2 = this.zzc;
            return zzyu2;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzaaz zzaaz) {
        if (this.zza == null) {
            synchronized (this) {
                if (this.zza == null) {
                    try {
                        this.zza = zzaaz;
                        this.zzc = zzyu.zzb;
                    } catch (zzaae e) {
                        this.zza = zzaaz;
                        this.zzc = zzyu.zzb;
                    }
                }
            }
        }
    }
}
