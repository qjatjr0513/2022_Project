package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfn extends zzzs<zzfo, zzfn> implements zzaba {
    private zzfn() {
        super(zzfo.zzb);
    }

    public final zzfn zza(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzfo) this.zza).zzf = i;
        return this;
    }

    public final zzfn zzb(zzfr zzfr) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzfo.zzf((zzfo) this.zza, zzfr);
        return this;
    }

    /* synthetic */ zzfn(zzfm zzfm) {
        super(zzfo.zzb);
    }
}
