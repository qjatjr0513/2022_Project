package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgd extends zzjx<zzgd, zzgc> implements zzld {
    /* access modifiers changed from: private */
    public static final zzgd zza;
    /* access modifiers changed from: private */
    public zzkd zze = zzby();
    /* access modifiers changed from: private */
    public zzkd zzf = zzby();
    private zzke<zzfm> zzg = zzbA();
    private zzke<zzgf> zzh = zzbA();

    static {
        zzgd zzgd = new zzgd();
        zza = zzgd;
        zzjx.zzbG(zzgd.class, zzgd);
    }

    private zzgd() {
    }

    public static zzgc zzf() {
        return (zzgc) zza.zzbu();
    }

    public static zzgd zzh() {
        return zza;
    }

    static /* synthetic */ void zzo(zzgd zzgd, Iterable iterable) {
        zzkd zzkd = zzgd.zze;
        if (!zzkd.zzc()) {
            zzgd.zze = zzjx.zzbz(zzkd);
        }
        zzih.zzbq(iterable, zzgd.zze);
    }

    static /* synthetic */ void zzq(zzgd zzgd, Iterable iterable) {
        zzkd zzkd = zzgd.zzf;
        if (!zzkd.zzc()) {
            zzgd.zzf = zzjx.zzbz(zzkd);
        }
        zzih.zzbq(iterable, zzgd.zzf);
    }

    static /* synthetic */ void zzs(zzgd zzgd, Iterable iterable) {
        zzgd.zzw();
        zzih.zzbq(iterable, zzgd.zzg);
    }

    static /* synthetic */ void zzt(zzgd zzgd, int i) {
        zzgd.zzw();
        zzgd.zzg.remove(i);
    }

    static /* synthetic */ void zzu(zzgd zzgd, Iterable iterable) {
        zzgd.zzx();
        zzih.zzbq(iterable, zzgd.zzh);
    }

    static /* synthetic */ void zzv(zzgd zzgd, int i) {
        zzgd.zzx();
        zzgd.zzh.remove(i);
    }

    private final void zzw() {
        zzke<zzfm> zzke = this.zzg;
        if (!zzke.zzc()) {
            this.zzg = zzjx.zzbB(zzke);
        }
    }

    private final void zzx() {
        zzke<zzgf> zzke = this.zzh;
        if (!zzke.zzc()) {
            this.zzh = zzjx.zzbB(zzke);
        }
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final int zzc() {
        return this.zzh.size();
    }

    public final int zzd() {
        return this.zze.size();
    }

    public final zzfm zze(int i) {
        return (zzfm) this.zzg.get(i);
    }

    public final zzgf zzi(int i) {
        return (zzgf) this.zzh.get(i);
    }

    public final List<zzfm> zzj() {
        return this.zzg;
    }

    public final List<Long> zzk() {
        return this.zzf;
    }

    public final List<zzgf> zzm() {
        return this.zzh;
    }

    public final List<Long> zzn() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zzfm.class, "zzh", zzgf.class});
            case 3:
                return new zzgd();
            case 4:
                return new zzgc((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
