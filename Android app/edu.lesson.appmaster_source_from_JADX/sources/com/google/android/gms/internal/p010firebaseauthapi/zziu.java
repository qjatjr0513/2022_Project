package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zziu extends zzzs<zziv, zziu> implements zzaba {
    private zziu() {
        super(zziv.zzb);
    }

    public final zziu zza(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziv) this.zza).zzg = i;
        return this;
    }

    public final zziu zzb(zzjk zzjk) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziv) this.zza).zzh = zzjk.zza();
        return this;
    }

    public final zziu zzc(zzig zzig) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziv) this.zza).zzf = zzig.zza();
        return this;
    }

    public final zziu zzd(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zziv.zzd((zziv) this.zza, str);
        return this;
    }

    /* synthetic */ zziu(zzis zzis) {
        super(zziv.zzb);
    }
}
