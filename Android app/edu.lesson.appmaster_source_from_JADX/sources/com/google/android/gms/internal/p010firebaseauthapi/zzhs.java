package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhs */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzhs extends zzzs<zzht, zzhs> implements zzaba {
    private zzhs() {
        super(zzht.zzb);
    }

    public final zzhs zza(zzyu zzyu) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzht) this.zza).zzg = zzyu;
        return this;
    }

    public final zzhs zzb(zzhz zzhz) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzht.zzi((zzht) this.zza, zzhz);
        return this;
    }

    public final zzhs zzc(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzht) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzhs(zzhr zzhr) {
        super(zzht.zzb);
    }
}
