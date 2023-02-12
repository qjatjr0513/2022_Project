package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzxt extends zzabs<zzxt, zzxr> implements zzada {
    /* access modifiers changed from: private */
    public static final zzxt zzb;
    private int zze;
    private int zzf;
    private String zzg = "";
    private String zzh = "";
    private zzvf zzi;
    private zzyc zzj;
    private zzwq zzk;
    private zzvp zzl;
    private zzwo zzm;
    private zzvr zzn;
    private zzwm zzo;
    private zzye zzp;
    private zzye zzq;
    private zzws zzr;
    private zzwb zzs;
    private zzxv zzt;
    private zzxx zzu;
    private zzxi zzv;
    private zzwy zzw;
    private zzxz zzx;
    private byte zzy = 2;

    static {
        zzxt zzxt = new zzxt();
        zzb = zzxt;
        zzabs.zzG(zzxt.class, zzxt);
    }

    private zzxt() {
    }

    public static zzxr zza() {
        return (zzxr) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzxt zzxt, String str) {
        str.getClass();
        zzxt.zze |= 2;
        zzxt.zzg = str;
    }

    static /* synthetic */ void zze(zzxt zzxt, String str) {
        str.getClass();
        zzxt.zze |= 4;
        zzxt.zzh = str;
    }

    static /* synthetic */ void zzf(zzxt zzxt, zzwo zzwo) {
        zzwo.getClass();
        zzxt.zzm = zzwo;
        zzxt.zze |= 128;
    }

    static /* synthetic */ void zzg(zzxt zzxt, zzvr zzvr) {
        zzvr.getClass();
        zzxt.zzn = zzvr;
        zzxt.zze |= 256;
    }

    static /* synthetic */ void zzh(zzxt zzxt, int i) {
        zzxt.zzf = i - 1;
        zzxt.zze |= 1;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzy);
            case 2:
                return zzF(zzb, "\u0001\u0013\u0000\u0001\u0001\u0013\u0013\u0000\u0000\u0004\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003\u0005ᐉ\u0004\u0006ᐉ\u0005\u0007ᐉ\u0006\bဉ\u0007\tᐉ\b\nဉ\t\u000bဉ\u000b\fဉ\n\rဉ\f\u000eဉ\r\u000fဉ\u000e\u0010ဉ\u000f\u0011ဉ\u0010\u0012ဉ\u0011\u0013ဉ\u0012", new Object[]{"zze", "zzf", zzxs.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzq", "zzp", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx"});
            case 3:
                return new zzxt();
            case 4:
                return new zzxr((zztv) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzy = b;
                return null;
        }
    }
}
