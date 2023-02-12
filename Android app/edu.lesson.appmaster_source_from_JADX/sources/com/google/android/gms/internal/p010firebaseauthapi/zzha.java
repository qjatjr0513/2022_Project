package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzha */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzha extends zzzw<zzha, zzgz> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzha zzb;
    private zzhj zze;
    private zzgu zzf;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzha zzha = new zzha();
        zzb = zzha;
        zzzw.zzF(zzha.class, zzha);
    }

    private zzha() {
    }

    public static zzgz zzc() {
        return (zzgz) zzb.zzt();
    }

    public static zzha zze() {
        return zzb;
    }

    static /* synthetic */ void zzg(zzha zzha, zzhj zzhj) {
        zzhj.getClass();
        zzha.zze = zzhj;
    }

    static /* synthetic */ void zzh(zzha zzha, zzgu zzgu) {
        zzgu.getClass();
        zzha.zzf = zzgu;
    }

    public final zzgr zza() {
        zzgr zzb2 = zzgr.zzb(this.zzg);
        return zzb2 == null ? zzgr.UNRECOGNIZED : zzb2;
    }

    public final zzgu zzb() {
        zzgu zzgu = this.zzf;
        return zzgu == null ? zzgu.zzc() : zzgu;
    }

    public final zzhj zzf() {
        zzhj zzhj = this.zze;
        return zzhj == null ? zzhj.zzc() : zzhj;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzha();
            case 4:
                return new zzgz((zzgy) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
