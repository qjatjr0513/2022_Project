package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzhv extends zzzs<zzhw, zzhv> implements zzaba {
    private zzhv() {
        super(zzhw.zzb);
    }

    public final zzhv zza(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzhw) this.zza).zzf = i;
        return this;
    }

    public final zzhv zzb(zzhz zzhz) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzhw.zzg((zzhw) this.zza, zzhz);
        return this;
    }

    /* synthetic */ zzhv(zzhu zzhu) {
        super(zzhw.zzb);
    }
}
