package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzjy extends zzabs<zzjy, zzjt> implements zzada {
    /* access modifiers changed from: private */
    public static final zzjy zzb;
    private int zze;
    private String zzf = "";
    private int zzg;
    private int zzh;
    private String zzi = "";
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;

    static {
        zzjy zzjy = new zzjy();
        zzb = zzjy;
        zzabs.zzG(zzjy.class, zzjy);
    }

    private zzjy() {
    }

    public static zzjt zza() {
        return (zzjt) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzjy zzjy, String str) {
        zzjy.zze |= 1;
        zzjy.zzf = str;
    }

    static /* synthetic */ void zze(zzjy zzjy, int i) {
        zzjy.zze |= 2;
        zzjy.zzg = i;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဌ\u0007\tဌ\b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzju.zza, "zzl", zzjw.zza, "zzm", zzjv.zza, "zzn", zzjx.zza});
            case 3:
                return new zzjy();
            case 4:
                return new zzjt((zzjs) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
