package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzhg extends zzzw<zzhg, zzhf> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzhg zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzha zzf;
    /* access modifiers changed from: private */
    public zzyu zzg = zzyu.zzb;
    /* access modifiers changed from: private */
    public zzyu zzh = zzyu.zzb;

    static {
        zzhg zzhg = new zzhg();
        zzb = zzhg;
        zzzw.zzF(zzhg.class, zzhg);
    }

    private zzhg() {
    }

    public static zzhf zzc() {
        return (zzhf) zzb.zzt();
    }

    public static zzhg zze() {
        return zzb;
    }

    public static zzhg zzf(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzhg) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzk(zzhg zzhg, zzha zzha) {
        zzha.getClass();
        zzhg.zzf = zzha;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzha zzb() {
        zzha zzha = this.zzf;
        return zzha == null ? zzha.zze() : zzha;
    }

    public final zzyu zzg() {
        return this.zzg;
    }

    public final zzyu zzh() {
        return this.zzh;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zzhg();
            case 4:
                return new zzhf((zzhe) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
