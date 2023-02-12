package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzjq extends zzzw<zzjq, zzjp> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzjq zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzyu zzf = zzyu.zzb;

    static {
        zzjq zzjq = new zzjq();
        zzb = zzjq;
        zzzw.zzF(zzjq.class, zzjq);
    }

    private zzjq() {
    }

    public static zzjp zzb() {
        return (zzjp) zzb.zzt();
    }

    public static zzjq zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzjq) zzzw.zzw(zzb, zzyu, zzzj);
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
                return new zzjq();
            case 4:
                return new zzjp((zzjo) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
