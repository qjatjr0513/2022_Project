package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgm extends zzjx<zzgm, zzgl> implements zzld {
    /* access modifiers changed from: private */
    public static final zzgm zza;
    private int zze;
    private String zzf = "";
    private zzke<zzgt> zzg = zzbA();

    static {
        zzgm zzgm = new zzgm();
        zza = zzgm;
        zzjx.zzbG(zzgm.class, zzgm);
    }

    private zzgm() {
    }

    public final String zzb() {
        return this.zzf;
    }

    public final List<zzgt> zzc() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzgt.class});
            case 3:
                return new zzgm();
            case 4:
                return new zzgl((zzgi) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
