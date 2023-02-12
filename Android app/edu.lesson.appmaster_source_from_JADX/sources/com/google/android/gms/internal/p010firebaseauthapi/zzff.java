package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzff */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzff extends zzzw<zzff, zzfe> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzff zzb;
    private zzfi zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzff zzff = new zzff();
        zzb = zzff;
        zzzw.zzF(zzff.class, zzff);
    }

    private zzff() {
    }

    public static zzfe zzb() {
        return (zzfe) zzb.zzt();
    }

    public static zzff zzd() {
        return zzb;
    }

    public static zzff zze(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzff) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzg(zzff zzff, zzfi zzfi) {
        zzfi.getClass();
        zzff.zze = zzfi;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzfi zzf() {
        zzfi zzfi = this.zze;
        return zzfi == null ? zzfi.zzd() : zzfi;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zze", "zzf"});
            case 3:
                return new zzff();
            case 4:
                return new zzfe((zzfd) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
