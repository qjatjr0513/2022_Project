package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzel extends zzjx<zzel, zzek> implements zzld {
    /* access modifiers changed from: private */
    public static final zzel zza;
    private int zze;
    private zzex zzf;
    private zzeq zzg;
    private boolean zzh;
    private String zzi = "";

    static {
        zzel zzel = new zzel();
        zza = zzel;
        zzjx.zzbG(zzel.class, zzel);
    }

    private zzel() {
    }

    public static zzel zzb() {
        return zza;
    }

    static /* synthetic */ void zzf(zzel zzel, String str) {
        zzel.zze |= 8;
        zzel.zzi = str;
    }

    public final zzeq zzc() {
        zzeq zzeq = this.zzg;
        return zzeq == null ? zzeq.zzb() : zzeq;
    }

    public final zzex zzd() {
        zzex zzex = this.zzf;
        return zzex == null ? zzex.zzc() : zzex;
    }

    public final String zze() {
        return this.zzi;
    }

    public final boolean zzg() {
        return this.zzh;
    }

    public final boolean zzh() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzj() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzk() {
        return (this.zze & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 3:
                return new zzel();
            case 4:
                return new zzek((zzef) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
