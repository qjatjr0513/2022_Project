package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgh extends zzjx<zzgh, zzgg> implements zzld {
    /* access modifiers changed from: private */
    public static final zzgh zza;
    private int zze;
    private long zzf;
    private String zzg = "";
    private String zzh = "";
    private long zzi;
    private float zzj;
    private double zzk;

    static {
        zzgh zzgh = new zzgh();
        zza = zzgh;
        zzjx.zzbG(zzgh.class, zzgh);
    }

    private zzgh() {
    }

    public static zzgg zzd() {
        return (zzgg) zza.zzbu();
    }

    static /* synthetic */ void zzh(zzgh zzgh, long j) {
        zzgh.zze |= 1;
        zzgh.zzf = j;
    }

    static /* synthetic */ void zzi(zzgh zzgh, String str) {
        str.getClass();
        zzgh.zze |= 2;
        zzgh.zzg = str;
    }

    static /* synthetic */ void zzj(zzgh zzgh, String str) {
        str.getClass();
        zzgh.zze |= 4;
        zzgh.zzh = str;
    }

    static /* synthetic */ void zzk(zzgh zzgh) {
        zzgh.zze &= -5;
        zzgh.zzh = zza.zzh;
    }

    static /* synthetic */ void zzm(zzgh zzgh, long j) {
        zzgh.zze |= 8;
        zzgh.zzi = j;
    }

    static /* synthetic */ void zzn(zzgh zzgh) {
        zzgh.zze &= -9;
        zzgh.zzi = 0;
    }

    static /* synthetic */ void zzo(zzgh zzgh, double d) {
        zzgh.zze |= 32;
        zzgh.zzk = d;
    }

    static /* synthetic */ void zzp(zzgh zzgh) {
        zzgh.zze &= -33;
        zzgh.zzk = 0.0d;
    }

    public final double zza() {
        return this.zzk;
    }

    public final long zzb() {
        return this.zzi;
    }

    public final long zzc() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zzg;
    }

    public final String zzg() {
        return this.zzh;
    }

    public final boolean zzq() {
        return (this.zze & 32) != 0;
    }

    public final boolean zzr() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzs() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 4) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zzgh();
            case 4:
                return new zzgg((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
