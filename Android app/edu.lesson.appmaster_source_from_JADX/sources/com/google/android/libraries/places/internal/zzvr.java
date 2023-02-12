package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzvr extends zzabs<zzvr, zzvq> implements zzada {
    /* access modifiers changed from: private */
    public static final zzvr zzb;
    private int zze;
    private String zzf = "";
    private zznc zzg;
    private zzvf zzh;
    private byte zzi = 2;

    static {
        zzvr zzvr = new zzvr();
        zzb = zzvr;
        zzabs.zzG(zzvr.class, zzvr);
    }

    private zzvr() {
    }

    public static zzvq zza() {
        return (zzvq) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzvr zzvr, zzvf zzvf) {
        zzvr.zzh = zzvf;
        zzvr.zze |= 4;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzi);
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zzvr();
            case 4:
                return new zzvq((zztv) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzi = b;
                return null;
        }
    }
}
