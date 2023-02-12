package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzip */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzip extends zzzs<zziq, zzip> implements zzaba {
    private zzip() {
        super(zziq.zzb);
    }

    public final zzip zza(zzie zzie) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zziq.zzg((zziq) this.zza, zzie);
        return this;
    }

    public final zzip zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziq) this.zza).zzg = i;
        return this;
    }

    public final zzip zzc(zzjk zzjk) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziq) this.zza).zzh = zzjk.zza();
        return this;
    }

    public final zzip zzd(zzig zzig) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zziq) this.zza).zzf = zzig.zza();
        return this;
    }

    /* synthetic */ zzip(zzin zzin) {
        super(zziq.zzb);
    }
}
