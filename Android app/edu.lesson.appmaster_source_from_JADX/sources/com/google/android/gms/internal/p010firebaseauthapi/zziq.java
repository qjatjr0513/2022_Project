package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zziq extends zzzw<zziq, zzip> implements zzaba {
    /* access modifiers changed from: private */
    public static final zziq zzb;
    private zzie zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;
    /* access modifiers changed from: private */
    public int zzh;

    static {
        zziq zziq = new zziq();
        zzb = zziq;
        zzzw.zzF(zziq.class, zziq);
    }

    private zziq() {
    }

    public static zzip zzd() {
        return (zzip) zzb.zzt();
    }

    static /* synthetic */ void zzg(zziq zziq, zzie zzie) {
        zzie.getClass();
        zziq.zze = zzie;
    }

    public final int zza() {
        return this.zzg;
    }

    public final zzie zzb() {
        zzie zzie = this.zze;
        return zzie == null ? zzie.zzd() : zzie;
    }

    public final zzig zzc() {
        zzig zzb2 = zzig.zzb(this.zzf);
        return zzb2 == null ? zzig.UNRECOGNIZED : zzb2;
    }

    public final zzjk zzf() {
        zzjk zzb2 = zzjk.zzb(this.zzh);
        return zzb2 == null ? zzjk.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zziq();
            case 4:
                return new zzip((zzin) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }

    public final boolean zzl() {
        return this.zze != null;
    }
}
