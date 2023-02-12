package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzvk extends zzabs<zzvk, zzvg> implements zzada {
    /* access modifiers changed from: private */
    public static final zzvk zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private zzvf zzh;
    private zznc zzi;
    private int zzj = 1;
    private String zzk = "";
    private int zzl;
    private int zzm;
    private String zzn = "";
    private int zzo;
    private byte zzp = 2;

    static {
        zzvk zzvk = new zzvk();
        zzb = zzvk;
        zzabs.zzG(zzvk.class, zzvk);
    }

    private zzvk() {
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzp);
            case 2:
                return zzF(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003\u0005ဌ\u0004\u0006ဈ\u0005\u0007ဌ\u0006\bင\u0007\tဈ\b\nဌ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzvh.zza, "zzk", "zzl", zzvj.zza, "zzm", "zzn", "zzo", zzvi.zza});
            case 3:
                return new zzvk();
            case 4:
                return new zzvg((zztv) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzp = b;
                return null;
        }
    }
}
