package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzit */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzit extends zzzs<zziw, zzit> implements zzaba {
    private zzit() {
        super(zziw.zzb);
    }

    public final zzit zza(zziv zziv) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zziw.zze((zziw) this.zza, zziv);
        return this;
    }

    public final zzit zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziw) this.zza).zze = i;
        return this;
    }

    /* synthetic */ zzit(zzis zzis) {
        super(zziw.zzb);
    }
}
