package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzjr extends zzabs<zzjr, zzjp> implements zzada {
    /* access modifiers changed from: private */
    public static final zzjr zzb;
    private int zze;
    private int zzf = 1;
    private zzwh zzg;
    private zzlt zzh;
    private zzzc zzi;
    private zzte zzj;
    private zzpb zzk;
    private zzmm zzl;
    private zzln zzm;
    private zzkt zzn;
    private zzmg zzo;
    private zzqo zzp;
    private zzrc zzq;
    private zzrf zzr;
    private zzkd zzs;
    private zzni zzt;
    private byte zzu = 2;

    static {
        zzjr zzjr = new zzjr();
        zzb = zzjr;
        zzabs.zzG(zzjr.class, zzjr);
    }

    private zzjr() {
    }

    public static zzjp zza() {
        return (zzjp) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzjr zzjr, zzwh zzwh) {
        zzwh.getClass();
        zzjr.zzg = zzwh;
        zzjr.zze |= 2;
    }

    static /* synthetic */ void zze(zzjr zzjr, int i) {
        zzjr.zzf = 1;
        zzjr.zze = 1 | zzjr.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzu);
            case 2:
                return zzF(zzb, "\u0001\u000f\u0000\u0001\u0001\u0010\u000f\u0000\u0000\u0002\u0001ဌ\u0000\u0002ᐉ\u0001\u0003ᐉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f\u000fဉ\r\u0010ဉ\u000e", new Object[]{"zze", "zzf", zzjq.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt"});
            case 3:
                return new zzjr();
            case 4:
                return new zzjp((zzjo) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzu = b;
                return null;
        }
    }
}
