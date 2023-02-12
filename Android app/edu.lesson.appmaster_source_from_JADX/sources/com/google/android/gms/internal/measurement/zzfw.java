package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfw extends zzjx<zzfw, zzfv> implements zzld {
    /* access modifiers changed from: private */
    public static final zzfw zza;
    private zzke<zzfy> zze = zzbA();

    static {
        zzfw zzfw = new zzfw();
        zza = zzfw;
        zzjx.zzbG(zzfw.class, zzfw);
    }

    private zzfw() {
    }

    public static zzfv zza() {
        return (zzfv) zza.zzbu();
    }

    static /* synthetic */ void zze(zzfw zzfw, zzfy zzfy) {
        zzfy.getClass();
        zzke<zzfy> zzke = zzfw.zze;
        if (!zzke.zzc()) {
            zzfw.zze = zzjx.zzbB(zzke);
        }
        zzfw.zze.add(zzfy);
    }

    public final zzfy zzc(int i) {
        return (zzfy) this.zze.get(0);
    }

    public final List<zzfy> zzd() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzfy.class});
            case 3:
                return new zzfw();
            case 4:
                return new zzfv((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
