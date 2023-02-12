package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzho */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzho extends zzzw<zzho, zzhn> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzho zzb;
    /* access modifiers changed from: private */
    public zzyu zze = zzyu.zzb;
    private zziw zzf;

    static {
        zzho zzho = new zzho();
        zzb = zzho;
        zzzw.zzF(zzho.class, zzho);
    }

    private zzho() {
    }

    public static zzhn zza() {
        return (zzhn) zzb.zzt();
    }

    public static zzho zzc(byte[] bArr, zzzj zzzj) throws zzaae {
        return (zzho) zzzw.zzx(zzb, bArr, zzzj);
    }

    static /* synthetic */ void zzf(zzho zzho, zziw zziw) {
        zziw.getClass();
        zzho.zzf = zziw;
    }

    public final zzyu zzd() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", new Object[]{"zze", "zzf"});
            case 3:
                return new zzho();
            case 4:
                return new zzhn((zzhm) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
