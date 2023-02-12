package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzgd extends zzzw<zzgd, zzgc> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzgd zzb;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzgd zzgd = new zzgd();
        zzb = zzgd;
        zzzw.zzF(zzgd.class, zzgd);
    }

    private zzgd() {
    }

    public static zzgc zzb() {
        return (zzgc) zzb.zzt();
    }

    public static zzgd zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzgd) zzzw.zzw(zzb, zzyu, zzzj);
    }

    public final int zza() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zzf", "zze"});
            case 3:
                return new zzgd();
            case 4:
                return new zzgc((zzgb) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
