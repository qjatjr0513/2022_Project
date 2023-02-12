package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzgx extends zzzw<zzgx, zzgw> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzgx zzb;
    private zzha zze;

    static {
        zzgx zzgx = new zzgx();
        zzb = zzgx;
        zzzw.zzF(zzgx.class, zzgx);
    }

    private zzgx() {
    }

    public static zzgw zza() {
        return (zzgw) zzb.zzt();
    }

    public static zzgx zzc(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzgx) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zze(zzgx zzgx, zzha zzha) {
        zzha.getClass();
        zzgx.zze = zzha;
    }

    public final zzha zzd() {
        zzha zzha = this.zze;
        return zzha == null ? zzha.zze() : zzha;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zze"});
            case 3:
                return new zzgx();
            case 4:
                return new zzgw((zzgv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
