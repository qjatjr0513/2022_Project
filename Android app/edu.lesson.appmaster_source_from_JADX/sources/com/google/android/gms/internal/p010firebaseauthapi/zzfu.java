package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfu extends zzzw<zzfu, zzft> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzfu zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzyu zzf = zzyu.zzb;

    static {
        zzfu zzfu = new zzfu();
        zzb = zzfu;
        zzzw.zzF(zzfu.class, zzfu);
    }

    private zzfu() {
    }

    public static zzft zzb() {
        return (zzft) zzb.zzt();
    }

    public static zzfu zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzfu) zzzw.zzw(zzb, zzyu, zzzj);
    }

    public final int zza() {
        return this.zze;
    }

    public final zzyu zze() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
            case 3:
                return new zzfu();
            case 4:
                return new zzft((zzfs) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
