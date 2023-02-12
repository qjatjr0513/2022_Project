package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzej extends zzjx<zzej, zzei> implements zzld {
    /* access modifiers changed from: private */
    public static final zzej zza;
    private int zze;
    private int zzf;
    private String zzg = "";
    private zzke<zzel> zzh = zzbA();
    private boolean zzi;
    private zzeq zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzm;

    static {
        zzej zzej = new zzej();
        zza = zzej;
        zzjx.zzbG(zzej.class, zzej);
    }

    private zzej() {
    }

    public static zzei zzc() {
        return (zzei) zza.zzbu();
    }

    static /* synthetic */ void zzi(zzej zzej, String str) {
        zzej.zze |= 2;
        zzej.zzg = str;
    }

    static /* synthetic */ void zzj(zzej zzej, int i, zzel zzel) {
        zzel.getClass();
        zzke<zzel> zzke = zzej.zzh;
        if (!zzke.zzc()) {
            zzej.zzh = zzjx.zzbB(zzke);
        }
        zzej.zzh.set(i, zzel);
    }

    public final int zza() {
        return this.zzh.size();
    }

    public final int zzb() {
        return this.zzf;
    }

    public final zzel zze(int i) {
        return (zzel) this.zzh.get(i);
    }

    public final zzeq zzf() {
        zzeq zzeq = this.zzj;
        return zzeq == null ? zzeq.zzb() : zzeq;
    }

    public final String zzg() {
        return this.zzg;
    }

    public final List<zzel> zzh() {
        return this.zzh;
    }

    public final boolean zzk() {
        return this.zzk;
    }

    public final boolean zzm() {
        return this.zzl;
    }

    public final boolean zzn() {
        return this.zzm;
    }

    public final boolean zzo() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzp() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzq() {
        return (this.zze & 64) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", zzel.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
            case 3:
                return new zzej();
            case 4:
                return new zzei((zzef) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
