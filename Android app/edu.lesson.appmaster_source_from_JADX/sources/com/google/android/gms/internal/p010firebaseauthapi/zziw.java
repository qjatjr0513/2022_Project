package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziw */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zziw extends zzzw<zziw, zzit> implements zzaba {
    /* access modifiers changed from: private */
    public static final zziw zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzaab<zziv> zzf = zzz();

    static {
        zziw zziw = new zziw();
        zzb = zziw;
        zzzw.zzF(zziw.class, zziw);
    }

    private zziw() {
    }

    public static zzit zza() {
        return (zzit) zzb.zzt();
    }

    static /* synthetic */ void zze(zziw zziw, zziv zziv) {
        zziv.getClass();
        zzaab<zziv> zzaab = zziw.zzf;
        if (!zzaab.zzc()) {
            zziw.zzf = zzzw.zzA(zzaab);
        }
        zziw.zzf.add(zziv);
    }

    public final zziv zzb(int i) {
        return (zziv) this.zzf.get(0);
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zziv.class});
            case 3:
                return new zziw();
            case 4:
                return new zzit((zzis) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
