package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzez */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzez extends zzzw<zzez, zzey> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzez zzb;
    private zzff zze;
    private zzhw zzf;

    static {
        zzez zzez = new zzez();
        zzb = zzez;
        zzzw.zzF(zzez.class, zzez);
    }

    private zzez() {
    }

    public static zzey zza() {
        return (zzey) zzb.zzt();
    }

    public static zzez zzc(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzez) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzf(zzez zzez, zzff zzff) {
        zzff.getClass();
        zzez.zze = zzff;
    }

    static /* synthetic */ void zzg(zzez zzez, zzhw zzhw) {
        zzhw.getClass();
        zzez.zzf = zzhw;
    }

    public final zzff zzd() {
        zzff zzff = this.zze;
        return zzff == null ? zzff.zzd() : zzff;
    }

    public final zzhw zze() {
        zzhw zzhw = this.zzf;
        return zzhw == null ? zzhw.zzd() : zzhw;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zze", "zzf"});
            case 3:
                return new zzez();
            case 4:
                return new zzey((zzex) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
