package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfo extends zzjx<zzfo, zzfn> implements zzld {
    /* access modifiers changed from: private */
    public static final zzfo zza;
    private int zze;
    /* access modifiers changed from: private */
    public zzke<zzfs> zzf = zzbA();
    private String zzg = "";
    private long zzh;
    private long zzi;
    private int zzj;

    static {
        zzfo zzfo = new zzfo();
        zza = zzfo;
        zzjx.zzbG(zzfo.class, zzfo);
    }

    private zzfo() {
    }

    public static zzfn zze() {
        return (zzfn) zza.zzbu();
    }

    static /* synthetic */ void zzj(zzfo zzfo, int i, zzfs zzfs) {
        zzfs.getClass();
        zzfo.zzv();
        zzfo.zzf.set(i, zzfs);
    }

    static /* synthetic */ void zzk(zzfo zzfo, zzfs zzfs) {
        zzfs.getClass();
        zzfo.zzv();
        zzfo.zzf.add(zzfs);
    }

    static /* synthetic */ void zzm(zzfo zzfo, Iterable iterable) {
        zzfo.zzv();
        zzih.zzbq(iterable, zzfo.zzf);
    }

    static /* synthetic */ void zzo(zzfo zzfo, int i) {
        zzfo.zzv();
        zzfo.zzf.remove(i);
    }

    static /* synthetic */ void zzp(zzfo zzfo, String str) {
        str.getClass();
        zzfo.zze |= 1;
        zzfo.zzg = str;
    }

    static /* synthetic */ void zzq(zzfo zzfo, long j) {
        zzfo.zze |= 2;
        zzfo.zzh = j;
    }

    static /* synthetic */ void zzr(zzfo zzfo, long j) {
        zzfo.zze |= 4;
        zzfo.zzi = j;
    }

    private final void zzv() {
        zzke<zzfs> zzke = this.zzf;
        if (!zzke.zzc()) {
            this.zzf = zzjx.zzbB(zzke);
        }
    }

    public final int zza() {
        return this.zzj;
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final long zzc() {
        return this.zzi;
    }

    public final long zzd() {
        return this.zzh;
    }

    public final zzfs zzg(int i) {
        return (zzfs) this.zzf.get(i);
    }

    public final String zzh() {
        return this.zzg;
    }

    public final List<zzfs> zzi() {
        return this.zzf;
    }

    public final boolean zzs() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzu() {
        return (this.zze & 2) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzfs.class, "zzg", "zzh", "zzi", "zzj"});
            case 3:
                return new zzfo();
            case 4:
                return new zzfn((zzff) null);
            case 5:
                return zza;
            default:
                return null;
        }
    }
}
