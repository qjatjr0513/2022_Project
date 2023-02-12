package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfm extends zzjx<zzfm, zzfl> implements zzld {
    /* access modifiers changed from: private */
    public static final zzfm zza;
    private int zze;
    private int zzf;
    private long zzg;

    static {
        zzfm zzfm = new zzfm();
        zza = zzfm;
        zzjx.zzbG(zzfm.class, zzfm);
    }

    private zzfm() {
    }

    public static zzfl zzc() {
        return (zzfl) zza.zzbu();
    }

    static /* synthetic */ void zze(zzfm zzfm, int i) {
        zzfm.zze |= 1;
        zzfm.zzf = i;
    }

    static /* synthetic */ void zzf(zzfm zzfm, long j) {
        zzfm.zze |= 2;
        zzfm.zzg = j;
    }

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzfm();
            case 4:
                return new zzfl((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
