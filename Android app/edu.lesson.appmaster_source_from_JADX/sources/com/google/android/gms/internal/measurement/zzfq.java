package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfq extends zzjx<zzfq, zzfp> implements zzld {
    /* access modifiers changed from: private */
    public static final zzfq zza;
    private int zze;
    private String zzf = "";
    private long zzg;

    static {
        zzfq zzfq = new zzfq();
        zza = zzfq;
        zzjx.zzbG(zzfq.class, zzfq);
    }

    private zzfq() {
    }

    public static zzfp zza() {
        return (zzfp) zza.zzbu();
    }

    static /* synthetic */ void zzc(zzfq zzfq, String str) {
        str.getClass();
        zzfq.zze |= 1;
        zzfq.zzf = str;
    }

    static /* synthetic */ void zzd(zzfq zzfq, long j) {
        zzfq.zze |= 2;
        zzfq.zzg = j;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzfq();
            case 4:
                return new zzfp((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
