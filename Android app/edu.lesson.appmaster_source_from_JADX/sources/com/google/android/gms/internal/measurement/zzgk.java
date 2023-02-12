package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgk extends zzjx<zzgk, zzgj> implements zzld {
    /* access modifiers changed from: private */
    public static final zzgk zza;
    private zzke<zzgm> zze = zzbA();

    static {
        zzgk zzgk = new zzgk();
        zza = zzgk;
        zzjx.zzbG(zzgk.class, zzgk);
    }

    private zzgk() {
    }

    public static zzgk zzc() {
        return zza;
    }

    public final int zza() {
        return this.zze.size();
    }

    public final List<zzgm> zzd() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzgm.class});
            case 3:
                return new zzgk();
            case 4:
                return new zzgj((zzgi) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
