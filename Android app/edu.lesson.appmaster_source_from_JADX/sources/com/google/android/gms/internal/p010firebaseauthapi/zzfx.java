package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfx extends zzzw<zzfx, zzfw> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzfx zzb;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzfx zzfx = new zzfx();
        zzb = zzfx;
        zzzw.zzF(zzfx.class, zzfx);
    }

    private zzfx() {
    }

    public static zzfw zzb() {
        return (zzfw) zzb.zzt();
    }

    public static zzfx zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzfx) zzzw.zzw(zzb, zzyu, zzzj);
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
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf"});
            case 3:
                return new zzfx();
            case 4:
                return new zzfw((zzfv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
