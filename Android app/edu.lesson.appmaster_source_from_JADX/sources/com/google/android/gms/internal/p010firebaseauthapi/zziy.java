package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zziy extends zzzs<zziz, zziy> implements zzaba {
    private zziy() {
        super(zziz.zzb);
    }

    public final zziy zza(zzjc zzjc) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zziz.zzg((zziz) this.zza, zzjc);
        return this;
    }

    public final zziy zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziz) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zziy(zzix zzix) {
        super(zziz.zzb);
    }
}
