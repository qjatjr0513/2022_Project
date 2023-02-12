package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzgg extends zzzw<zzgg, zzgf> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzgg zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzyu zzf = zzyu.zzb;

    static {
        zzgg zzgg = new zzgg();
        zzb = zzgg;
        zzzw.zzF(zzgg.class, zzgg);
    }

    private zzgg() {
    }

    public static zzgf zzb() {
        return (zzgf) zzb.zzt();
    }

    public static zzgg zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzgg) zzzw.zzw(zzb, zzyu, zzzj);
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
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 3:
                return new zzgg();
            case 4:
                return new zzgf((zzge) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
