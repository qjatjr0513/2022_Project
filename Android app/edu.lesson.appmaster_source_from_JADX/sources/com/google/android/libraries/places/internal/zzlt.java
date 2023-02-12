package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzlt extends zzabs<zzlt, zzlr> implements zzada {
    /* access modifiers changed from: private */
    public static final zzlt zzb;
    private int zze;
    private int zzf = 1;
    private zzlv zzg;
    private zzlx zzh;
    private zzaae zzi;
    private byte zzj = 2;

    static {
        zzlt zzlt = new zzlt();
        zzb = zzlt;
        zzabs.zzG(zzlt.class, zzlt);
    }

    private zzlt() {
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzj);
            case 2:
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003", new Object[]{"zze", "zzf", zzls.zza, "zzg", "zzh", "zzi"});
            case 3:
                return new zzlt();
            case 4:
                return new zzlr((zzlq) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzj = b;
                return null;
        }
    }
}
