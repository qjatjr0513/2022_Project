package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfl extends zzjt<zzfm, zzfl> implements zzld {
    private zzfl() {
        super(zzfm.zza);
    }

    public final zzfl zza(long j) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfm.zzf((zzfm) this.zza, j);
        return this;
    }

    public final zzfl zzb(int i) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfm.zze((zzfm) this.zza, i);
        return this;
    }

    /* synthetic */ zzfl(zzff zzff) {
        super(zzfm.zza);
    }
}
