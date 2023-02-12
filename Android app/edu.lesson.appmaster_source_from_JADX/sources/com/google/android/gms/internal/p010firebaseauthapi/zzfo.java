package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfo */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzfo extends zzzw<zzfo, zzfn> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzfo zzb;
    private zzfr zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzfo zzfo = new zzfo();
        zzb = zzfo;
        zzzw.zzF(zzfo.class, zzfo);
    }

    private zzfo() {
    }

    public static zzfn zzb() {
        return (zzfn) zzb.zzt();
    }

    public static zzfo zzd(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzfo) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzf(zzfo zzfo, zzfr zzfr) {
        zzfr.getClass();
        zzfo.zze = zzfr;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzfr zze() {
        zzfr zzfr = this.zze;
        return zzfr == null ? zzfr.zzd() : zzfr;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zze", "zzf"});
            case 3:
                return new zzfo();
            case 4:
                return new zzfn((zzfm) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
