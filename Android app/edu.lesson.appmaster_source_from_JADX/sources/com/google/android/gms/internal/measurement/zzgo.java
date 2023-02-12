package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgo extends zzjx<zzgo, zzgn> implements zzld {
    /* access modifiers changed from: private */
    public static final zzgo zza;
    private int zze;
    private zzke<zzgt> zzf = zzbA();
    private zzgk zzg;

    static {
        zzgo zzgo = new zzgo();
        zza = zzgo;
        zzjx.zzbG(zzgo.class, zzgo);
    }

    private zzgo() {
    }

    public final zzgk zza() {
        zzgk zzgk = this.zzg;
        return zzgk == null ? zzgk.zzc() : zzgk;
    }

    public final List<zzgt> zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zze", "zzf", zzgt.class, "zzg"});
            case 3:
                return new zzgo();
            case 4:
                return new zzgn((zzgi) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
