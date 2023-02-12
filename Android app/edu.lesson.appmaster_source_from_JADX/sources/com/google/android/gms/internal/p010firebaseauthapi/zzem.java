package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzem */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzem extends zzzs<zzen, zzem> implements zzaba {
    private zzem() {
        super(zzen.zzb);
    }

    public final zzem zza(zzyu zzyu) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzen) this.zza).zzf = zzyu;
        return this;
    }

    public final zzem zzb(zzet zzet) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzen.zzi((zzen) this.zza, zzet);
        return this;
    }

    public final zzem zzc(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzen) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzem(zzel zzel) {
        super(zzen.zzb);
    }
}
