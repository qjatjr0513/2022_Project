package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfe */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfe extends zzzs<zzff, zzfe> implements zzaba {
    private zzfe() {
        super(zzff.zzb);
    }

    public final zzfe zza(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzff) this.zza).zzf = i;
        return this;
    }

    public final zzfe zzb(zzfi zzfi) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzff.zzg((zzff) this.zza, zzfi);
        return this;
    }

    /* synthetic */ zzfe(zzfd zzfd) {
        super(zzff.zzb);
    }
}
