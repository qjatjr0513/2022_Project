package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhz */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzhz extends zzzw<zzhz, zzhy> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzhz zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzhz zzhz = new zzhz();
        zzb = zzhz;
        zzzw.zzF(zzhz.class, zzhz);
    }

    private zzhz() {
    }

    public static zzhy zzc() {
        return (zzhy) zzb.zzt();
    }

    public static zzhz zze() {
        return zzb;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzhq zzb() {
        zzhq zzb2 = zzhq.zzb(this.zze);
        return zzb2 == null ? zzhq.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zze", "zzf"});
            case 3:
                return new zzhz();
            case 4:
                return new zzhy((zzhx) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
