package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzij */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzij extends zzzw<zzij, zzii> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzij zzb;
    private String zze = "";
    /* access modifiers changed from: private */
    public zzyu zzf = zzyu.zzb;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzij zzij = new zzij();
        zzb = zzij;
        zzzw.zzF(zzij.class, zzij);
    }

    private zzij() {
    }

    public static zzii zza() {
        return (zzii) zzb.zzt();
    }

    public static zzij zzc() {
        return zzb;
    }

    static /* synthetic */ void zzg(zzij zzij, String str) {
        str.getClass();
        zzij.zze = str;
    }

    public final zzjk zzd() {
        zzjk zzb2 = zzjk.zzb(this.zzg);
        return zzb2 == null ? zzjk.UNRECOGNIZED : zzb2;
    }

    public final zzyu zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzij();
            case 4:
                return new zzii((zzih) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
