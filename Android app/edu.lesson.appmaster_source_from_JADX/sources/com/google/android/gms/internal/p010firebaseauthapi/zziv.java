package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zziv extends zzzw<zziv, zziu> implements zzaba {
    /* access modifiers changed from: private */
    public static final zziv zzb;
    private String zze = "";
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;
    /* access modifiers changed from: private */
    public int zzh;

    static {
        zziv zziv = new zziv();
        zzb = zziv;
        zzzw.zzF(zziv.class, zziv);
    }

    private zziv() {
    }

    public static zziu zzb() {
        return (zziu) zzb.zzt();
    }

    static /* synthetic */ void zzd(zziv zziv, String str) {
        str.getClass();
        zziv.zze = str;
    }

    public final int zza() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zziv();
            case 4:
                return new zziu((zzis) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
