package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzje */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzje extends zzzs<zzjf, zzje> implements zzaba {
    private zzje() {
        super(zzjf.zzb);
    }

    public final zzje zza(zzji zzji) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzjf.zzg((zzjf) this.zza, zzji);
        return this;
    }

    public final zzje zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzjf) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzje(zzjd zzjd) {
        super(zzjf.zzb);
    }
}
