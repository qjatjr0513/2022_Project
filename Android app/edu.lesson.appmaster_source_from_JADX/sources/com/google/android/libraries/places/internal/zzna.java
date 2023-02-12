package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzna extends zzabs<zzna, zzmz> implements zzada {
    /* access modifiers changed from: private */
    public static final zzna zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private byte zzh = 2;

    static {
        zzna zzna = new zzna();
        zzb = zzna;
        zzabs.zzG(zzna.class, zzna);
    }

    private zzna() {
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzh);
            case 2:
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔆ\u0000\u0002ᔆ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzna();
            case 4:
                return new zzmz((zzmy) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzh = b;
                return null;
        }
    }
}
