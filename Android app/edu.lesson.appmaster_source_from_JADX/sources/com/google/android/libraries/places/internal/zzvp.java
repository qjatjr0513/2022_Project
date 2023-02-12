package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzvp extends zzabs<zzvp, zzvo> implements zzada {
    /* access modifiers changed from: private */
    public static final zzvp zzb;
    private int zze;
    private String zzf = "";
    private zzna zzg;
    private String zzh = "";
    private zzabz<String> zzi = zzabs.zzB();
    private String zzj = "";
    private String zzk = "";
    private byte zzl = 2;

    static {
        zzvp zzvp = new zzvp();
        zzb = zzvp;
        zzabs.zzG(zzvp.class, zzvp);
    }

    private zzvp() {
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzl);
            case 2:
                return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဈ\u0002\u0004\u001a\u0005ဈ\u0003\u0006ဈ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zzvp();
            case 4:
                return new zzvo((zztv) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzl = b;
                return null;
        }
    }
}
