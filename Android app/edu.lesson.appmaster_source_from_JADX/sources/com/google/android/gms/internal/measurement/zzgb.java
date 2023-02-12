package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgb extends zzjx<zzgb, zzfz> implements zzld {
    /* access modifiers changed from: private */
    public static final zzgb zza;
    private int zze;
    private int zzf = 1;
    private zzke<zzfq> zzg = zzbA();

    static {
        zzgb zzgb = new zzgb();
        zza = zzgb;
        zzjx.zzbG(zzgb.class, zzgb);
    }

    private zzgb() {
    }

    public static zzfz zza() {
        return (zzfz) zza.zzbu();
    }

    static /* synthetic */ void zzc(zzgb zzgb, zzfq zzfq) {
        zzfq.getClass();
        zzke<zzfq> zzke = zzgb.zzg;
        if (!zzke.zzc()) {
            zzgb.zzg = zzjx.zzbB(zzke);
        }
        zzgb.zzg.add(zzfq);
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzga.zza, "zzg", zzfq.class});
            case 3:
                return new zzgb();
            case 4:
                return new zzfz((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
