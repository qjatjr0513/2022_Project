package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzvw extends zzabs<zzvw, zzvt> implements zzada {
    /* access modifiers changed from: private */
    public static final zzvw zzb;
    private int zze;
    private int zzf;
    private int zzg = 1;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;
    private int zzs;
    private int zzt;
    private int zzu;

    static {
        zzvw zzvw = new zzvw();
        zzb = zzvw;
        zzabs.zzG(zzvw.class, zzvw);
    }

    private zzvw() {
    }

    public static zzvt zza() {
        return (zzvt) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzvw zzvw, boolean z) {
        zzvw.zze |= 4;
        zzvw.zzh = z;
    }

    static /* synthetic */ void zze(zzvw zzvw, boolean z) {
        zzvw.zze |= 8;
        zzvw.zzi = z;
    }

    static /* synthetic */ void zzf(zzvw zzvw, boolean z) {
        zzvw.zze |= 16;
        zzvw.zzj = z;
    }

    static /* synthetic */ void zzg(zzvw zzvw, int i) {
        zzvw.zze |= 32;
        zzvw.zzk = i;
    }

    static /* synthetic */ void zzh(zzvw zzvw, int i) {
        zzvw.zze |= 64;
        zzvw.zzl = i;
    }

    static /* synthetic */ void zzi(zzvw zzvw, int i) {
        zzvw.zze |= 128;
        zzvw.zzm = i;
    }

    static /* synthetic */ void zzj(zzvw zzvw, int i) {
        zzvw.zze |= 256;
        zzvw.zzn = i;
    }

    static /* synthetic */ void zzk(zzvw zzvw, int i) {
        zzvw.zze |= 512;
        zzvw.zzo = i;
    }

    static /* synthetic */ void zzl(zzvw zzvw, int i) {
        zzvw.zze |= 1024;
        zzvw.zzp = i;
    }

    static /* synthetic */ void zzm(zzvw zzvw, int i) {
        zzvw.zze |= 2048;
        zzvw.zzq = i;
    }

    static /* synthetic */ void zzn(zzvw zzvw, boolean z) {
        zzvw.zze |= 4096;
        zzvw.zzr = z;
    }

    static /* synthetic */ void zzo(zzvw zzvw, int i) {
        zzvw.zze |= 8192;
        zzvw.zzs = i;
    }

    static /* synthetic */ void zzp(zzvw zzvw, int i) {
        zzvw.zzf = i - 1;
        zzvw.zze |= 1;
    }

    static /* synthetic */ void zzq(zzvw zzvw, int i) {
        zzvw.zzg = i;
        zzvw.zze |= 2;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0010\u0000\u0001\u0001\u0011\u0010\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဋ\u0005\u0007ဋ\u0006\bဋ\u0007\nဋ\t\u000bဋ\n\fဋ\u000b\rဇ\f\u000eဋ\r\u000fဋ\b\u0010ဋ\u000e\u0011ဌ\u000f", new Object[]{"zze", "zzf", zzvv.zza, "zzg", zzvs.zza, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzn", "zzt", "zzu", zzvu.zza});
            case 3:
                return new zzvw();
            case 4:
                return new zzvt((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
