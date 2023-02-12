package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzev */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzev extends zzzs<zzew, zzev> implements zzaba {
    private zzev() {
        super(zzew.zzb);
    }

    public final zzev zza(zzfc zzfc) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzew.zzh((zzew) this.zza, zzfc);
        return this;
    }

    public final zzev zzb(zzht zzht) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzew.zzi((zzew) this.zza, zzht);
        return this;
    }

    public final zzev zzc(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzew) this.zza).zze = i;
        return this;
    }

    /* synthetic */ zzev(zzeu zzeu) {
        super(zzew.zzb);
    }
}
