package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzhf extends zzzs<zzhg, zzhf> implements zzaba {
    private zzhf() {
        super(zzhg.zzb);
    }

    public final zzhf zza(zzha zzha) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzhg.zzk((zzhg) this.zza, zzha);
        return this;
    }

    public final zzhf zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzhg) this.zza).zze = 0;
        return this;
    }

    public final zzhf zzc(zzyu zzyu) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzhg) this.zza).zzg = zzyu;
        return this;
    }

    public final zzhf zzd(zzyu zzyu) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzhg) this.zza).zzh = zzyu;
        return this;
    }

    /* synthetic */ zzhf(zzhe zzhe) {
        super(zzhg.zzb);
    }
}
