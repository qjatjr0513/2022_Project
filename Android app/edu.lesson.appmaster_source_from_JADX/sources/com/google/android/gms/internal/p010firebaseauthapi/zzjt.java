package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjt */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzjt extends zzzw<zzjt, zzjs> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzjt zzb;
    private int zze;

    static {
        zzjt zzjt = new zzjt();
        zzb = zzjt;
        zzzw.zzF(zzjt.class, zzjt);
    }

    private zzjt() {
    }

    public static zzjt zzb() {
        return zzb;
    }

    public static zzjt zzc(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzjt) zzzw.zzw(zzb, zzyu, zzzj);
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 3:
                return new zzjt();
            case 4:
                return new zzjs((zzjr) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
