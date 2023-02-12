package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzuj extends zzabs<zzuj, zzui> implements zzada {
    /* access modifiers changed from: private */
    public static final zzuj zzb;
    private int zze;
    private int zzf;

    static {
        zzuj zzuj = new zzuj();
        zzb = zzuj;
        zzabs.zzG(zzuj.class, zzuj);
    }

    private zzuj() {
    }

    public static zzui zza() {
        return (zzui) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzuj zzuj, int i) {
        zzuj.zze |= 1;
        zzuj.zzf = i;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
            case 3:
                return new zzuj();
            case 4:
                return new zzui((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
