package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzei extends zzjt<zzej, zzei> implements zzld {
    private zzei() {
        super(zzej.zza);
    }

    public final int zza() {
        return ((zzej) this.zza).zza();
    }

    public final zzei zzb(String str) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzej.zzi((zzej) this.zza, str);
        return this;
    }

    public final zzei zzc(int i, zzel zzel) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzej.zzj((zzej) this.zza, i, zzel);
        return this;
    }

    public final zzel zzd(int i) {
        return ((zzej) this.zza).zze(i);
    }

    public final String zze() {
        return ((zzej) this.zza).zzg();
    }

    /* synthetic */ zzei(zzef zzef) {
        super(zzej.zza);
    }
}
