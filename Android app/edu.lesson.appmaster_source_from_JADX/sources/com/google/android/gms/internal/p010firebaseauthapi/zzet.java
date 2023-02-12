package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzet */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzet extends zzzw<zzet, zzes> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzet zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzet zzet = new zzet();
        zzb = zzet;
        zzzw.zzF(zzet.class, zzet);
    }

    private zzet() {
    }

    public static zzes zzb() {
        return (zzes) zzb.zzt();
    }

    public static zzet zzd() {
        return zzb;
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
                return zzE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 3:
                return new zzet();
            case 4:
                return new zzes((zzer) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
