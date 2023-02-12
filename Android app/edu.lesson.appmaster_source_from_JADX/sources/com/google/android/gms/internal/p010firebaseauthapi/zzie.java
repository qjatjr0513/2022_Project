package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzie */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzie extends zzzw<zzie, zzib> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzie zzb;
    /* access modifiers changed from: private */
    public String zze = "";
    /* access modifiers changed from: private */
    public zzyu zzf = zzyu.zzb;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzie zzie = new zzie();
        zzb = zzie;
        zzzw.zzF(zzie.class, zzie);
    }

    private zzie() {
    }

    public static zzib zza() {
        return (zzib) zzb.zzt();
    }

    public static zzie zzd() {
        return zzb;
    }

    public final zzid zzb() {
        zzid zzb2 = zzid.zzb(this.zzg);
        return zzb2 == null ? zzid.UNRECOGNIZED : zzb2;
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
                return new zzie();
            case 4:
                return new zzib((zzia) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
