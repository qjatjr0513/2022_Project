package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzwo extends zzabs<zzwo, zzwn> implements zzada {
    /* access modifiers changed from: private */
    public static final zzwo zzb;
    private int zze;
    private zzabz<String> zzf = zzabs.zzB();
    private int zzg;
    private int zzh;
    private zzxn zzi;

    static {
        zzwo zzwo = new zzwo();
        zzb = zzwo;
        zzabs.zzG(zzwo.class, zzwo);
    }

    private zzwo() {
    }

    public static zzwn zza() {
        return (zzwn) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzwo zzwo, int i) {
        zzwo.zze |= 2;
        zzwo.zzh = 1;
    }

    static /* synthetic */ void zze(zzwo zzwo, zzxn zzxn) {
        zzxn.getClass();
        zzwo.zzi = zzxn;
        zzwo.zze |= 4;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001a\u0002ဌ\u0000\u0003ဋ\u0001\u0004ဉ\u0002", new Object[]{"zze", "zzf", "zzg", zzuk.zza, "zzh", "zzi"});
            case 3:
                return new zzwo();
            case 4:
                return new zzwn((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
