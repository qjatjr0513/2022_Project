package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzen */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzen extends zzzw<zzen, zzem> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzen zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzyu zzf = zzyu.zzb;
    private zzet zzg;

    static {
        zzen zzen = new zzen();
        zzb = zzen;
        zzzw.zzF(zzen.class, zzen);
    }

    private zzen() {
    }

    public static zzem zzb() {
        return (zzem) zzb.zzt();
    }

    public static zzen zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzen) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzi(zzen zzen, zzet zzet) {
        zzet.getClass();
        zzen.zzg = zzet;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzet zze() {
        zzet zzet = this.zzg;
        return zzet == null ? zzet.zzd() : zzet;
    }

    public final zzyu zzf() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzen();
            case 4:
                return new zzem((zzel) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
