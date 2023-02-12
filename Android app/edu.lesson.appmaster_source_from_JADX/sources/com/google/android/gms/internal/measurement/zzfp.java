package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfp extends zzjt<zzfq, zzfp> implements zzld {
    private zzfp() {
        super(zzfq.zza);
    }

    public final zzfp zza(long j) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfq.zzd((zzfq) this.zza, j);
        return this;
    }

    public final zzfp zzb(String str) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfq.zzc((zzfq) this.zza, str);
        return this;
    }

    /* synthetic */ zzfp(zzff zzff) {
        super(zzfq.zza);
    }
}
