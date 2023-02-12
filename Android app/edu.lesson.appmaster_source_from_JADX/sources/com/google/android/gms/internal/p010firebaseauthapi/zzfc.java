package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfc */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfc extends zzzw<zzfc, zzfb> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzfc zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzfi zzf;
    /* access modifiers changed from: private */
    public zzyu zzg = zzyu.zzb;

    static {
        zzfc zzfc = new zzfc();
        zzb = zzfc;
        zzzw.zzF(zzfc.class, zzfc);
    }

    private zzfc() {
    }

    public static zzfb zzb() {
        return (zzfb) zzb.zzt();
    }

    public static zzfc zzd() {
        return zzb;
    }

    public static zzfc zze(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzfc) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzi(zzfc zzfc, zzfi zzfi) {
        zzfi.getClass();
        zzfc.zzf = zzfi;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzfi zzf() {
        zzfi zzfi = this.zzf;
        return zzfi == null ? zzfi.zzd() : zzfi;
    }

    public final zzyu zzg() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzfc();
            case 4:
                return new zzfb((zzfa) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
