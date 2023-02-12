package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzhy extends zzzs<zzhz, zzhy> implements zzaba {
    private zzhy() {
        super(zzhz.zzb);
    }

    public final zzhy zza(zzhq zzhq) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzhz) this.zza).zze = zzhq.zza();
        return this;
    }

    public final zzhy zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzhz) this.zza).zzf = i;
        return this;
    }

    /* synthetic */ zzhy(zzhx zzhx) {
        super(zzhz.zzb);
    }
}
