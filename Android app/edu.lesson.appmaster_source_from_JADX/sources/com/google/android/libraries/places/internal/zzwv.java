package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzwv extends zzabs<zzwv, zzwt> implements zzada {
    /* access modifiers changed from: private */
    public static final zzwv zzb;
    private int zze;
    private zzvf zzf;
    private int zzg;
    private int zzh;
    private zzxn zzi;

    static {
        zzwv zzwv = new zzwv();
        zzb = zzwv;
        zzabs.zzG(zzwv.class, zzwv);
    }

    private zzwv() {
    }

    public static zzwt zza() {
        return (zzwt) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzwv zzwv, int i) {
        zzwv.zze |= 4;
        zzwv.zzh = i;
    }

    static /* synthetic */ void zze(zzwv zzwv, zzxn zzxn) {
        zzxn.getClass();
        zzwv.zzi = zzxn;
        zzwv.zze |= 8;
    }

    static /* synthetic */ void zzf(zzwv zzwv, int i) {
        zzwv.zzg = i - 1;
        zzwv.zze |= 2;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004ဉ\u0003", new Object[]{"zze", "zzf", "zzg", zzwu.zza, "zzh", "zzi"});
            case 3:
                return new zzwv();
            case 4:
                return new zzwt((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
