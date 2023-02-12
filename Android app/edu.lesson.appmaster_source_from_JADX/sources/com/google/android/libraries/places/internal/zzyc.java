package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzyc extends zzabs<zzyc, zzya> implements zzada {
    /* access modifiers changed from: private */
    public static final zzyc zzb;
    private int zze;
    private zznc zzf;
    private int zzg;
    private int zzh;
    private String zzi = "";
    private int zzj;
    private byte zzk = 2;

    static {
        zzyc zzyc = new zzyc();
        zzb = zzyc;
        zzabs.zzG(zzyc.class, zzyc);
    }

    private zzyc() {
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzk);
            case 2:
                return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ᐉ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005ဌ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzyb.zza});
            case 3:
                return new zzyc();
            case 4:
                return new zzya((zztv) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzk = b;
                return null;
        }
    }
}
