package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfb extends zzzs<zzfc, zzfb> implements zzaba {
    private zzfb() {
        super(zzfc.zzb);
    }

    public final zzfb zza(zzyu zzyu) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzfc) this.zza).zzg = zzyu;
        return this;
    }

    public final zzfb zzb(zzfi zzfi) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzfc.zzi((zzfc) this.zza, zzfi);
        return this;
    }

    public final zzfb zzc(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzfc) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzfb(zzfa zzfa) {
        super(zzfc.zzb);
    }
}
