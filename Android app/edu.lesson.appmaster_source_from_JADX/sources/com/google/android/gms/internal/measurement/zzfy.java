package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfy extends zzjx<zzfy, zzfx> implements zzld {
    public static final /* synthetic */ int zza = 0;
    /* access modifiers changed from: private */
    public static final zzfy zze;
    private boolean zzA;
    private String zzB = "";
    private long zzC;
    private int zzD;
    private String zzE = "";
    private String zzF = "";
    private boolean zzG;
    /* access modifiers changed from: private */
    public zzke<zzfk> zzH = zzbA();
    private String zzI = "";
    private int zzJ;
    private int zzK;
    private int zzL;
    private String zzM = "";
    private long zzN;
    private long zzO;
    private String zzP = "";
    private String zzQ = "";
    private int zzR;
    private String zzS = "";
    private zzgb zzT;
    private zzkc zzU = zzbx();
    private long zzV;
    private long zzW;
    private String zzX = "";
    private String zzY = "";
    private int zzZ;
    private boolean zzaa;
    private String zzab = "";
    private boolean zzac;
    private zzfu zzad;
    private int zzf;
    private int zzg;
    private int zzh;
    /* access modifiers changed from: private */
    public zzke<zzfo> zzi = zzbA();
    private zzke<zzgh> zzj = zzbA();
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private String zzp = "";
    private String zzq = "";
    private String zzr = "";
    private String zzs = "";
    private int zzt;
    private String zzu = "";
    private String zzv = "";
    private String zzw = "";
    private long zzx;
    private long zzy;
    private String zzz = "";

    static {
        zzfy zzfy = new zzfy();
        zze = zzfy;
        zzjx.zzbG(zzfy.class, zzfy);
    }

    private zzfy() {
    }

    static /* synthetic */ void zzQ(zzfy zzfy, long j) {
        zzfy.zzf |= 1073741824;
        zzfy.zzO = j;
    }

    static /* synthetic */ void zzR(zzfy zzfy) {
        zzfy.zzf &= Integer.MAX_VALUE;
        zzfy.zzP = zze.zzP;
    }

    static /* synthetic */ void zzS(zzfy zzfy, int i) {
        zzfy.zzg |= 2;
        zzfy.zzR = i;
    }

    static /* synthetic */ void zzT(zzfy zzfy, int i, zzfo zzfo) {
        zzfo.getClass();
        zzfy.zzbI();
        zzfy.zzi.set(i, zzfo);
    }

    static /* synthetic */ void zzU(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzg |= 4;
        zzfy.zzS = str;
    }

    static /* synthetic */ void zzV(zzfy zzfy, zzgb zzgb) {
        zzgb.getClass();
        zzfy.zzT = zzgb;
        zzfy.zzg |= 8;
    }

    static /* synthetic */ void zzW(zzfy zzfy, Iterable iterable) {
        int i;
        zzkc zzkc = zzfy.zzU;
        if (!zzkc.zzc()) {
            int size = zzkc.size();
            if (size == 0) {
                i = 10;
            } else {
                i = size + size;
            }
            zzfy.zzU = zzkc.zzg(i);
        }
        zzih.zzbq(iterable, zzfy.zzU);
    }

    static /* synthetic */ void zzX(zzfy zzfy, zzfo zzfo) {
        zzfo.getClass();
        zzfy.zzbI();
        zzfy.zzi.add(zzfo);
    }

    static /* synthetic */ void zzY(zzfy zzfy, long j) {
        zzfy.zzg |= 16;
        zzfy.zzV = j;
    }

    static /* synthetic */ void zzZ(zzfy zzfy, long j) {
        zzfy.zzg |= 32;
        zzfy.zzW = j;
    }

    static /* synthetic */ void zzaA(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 65536;
        zzfy.zzz = str;
    }

    static /* synthetic */ void zzaB(zzfy zzfy) {
        zzfy.zzf &= -65537;
        zzfy.zzz = zze.zzz;
    }

    static /* synthetic */ void zzaC(zzfy zzfy, boolean z) {
        zzfy.zzf |= 131072;
        zzfy.zzA = z;
    }

    static /* synthetic */ void zzaD(zzfy zzfy) {
        zzfy.zzf &= -131073;
        zzfy.zzA = false;
    }

    static /* synthetic */ void zzaE(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 262144;
        zzfy.zzB = str;
    }

    static /* synthetic */ void zzaF(zzfy zzfy) {
        zzfy.zzf &= -262145;
        zzfy.zzB = zze.zzB;
    }

    static /* synthetic */ void zzaG(zzfy zzfy, long j) {
        zzfy.zzf |= 524288;
        zzfy.zzC = j;
    }

    static /* synthetic */ void zzaH(zzfy zzfy, int i) {
        zzfy.zzf |= 1048576;
        zzfy.zzD = i;
    }

    static /* synthetic */ void zzaI(zzfy zzfy, String str) {
        zzfy.zzf |= 2097152;
        zzfy.zzE = str;
    }

    static /* synthetic */ void zzaJ(zzfy zzfy) {
        zzfy.zzf &= -2097153;
        zzfy.zzE = zze.zzE;
    }

    static /* synthetic */ void zzaK(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 4194304;
        zzfy.zzF = str;
    }

    static /* synthetic */ void zzaL(zzfy zzfy, boolean z) {
        zzfy.zzf |= 8388608;
        zzfy.zzG = z;
    }

    static /* synthetic */ void zzaM(zzfy zzfy, Iterable iterable) {
        zzke<zzfk> zzke = zzfy.zzH;
        if (!zzke.zzc()) {
            zzfy.zzH = zzjx.zzbB(zzke);
        }
        zzih.zzbq(iterable, zzfy.zzH);
    }

    static /* synthetic */ void zzaO(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 16777216;
        zzfy.zzI = str;
    }

    static /* synthetic */ void zzaP(zzfy zzfy, int i) {
        zzfy.zzf |= 33554432;
        zzfy.zzJ = i;
    }

    static /* synthetic */ void zzaQ(zzfy zzfy, int i) {
        zzfy.zzf |= 1;
        zzfy.zzh = 1;
    }

    static /* synthetic */ void zzaR(zzfy zzfy) {
        zzfy.zzf &= -268435457;
        zzfy.zzM = zze.zzM;
    }

    static /* synthetic */ void zzaS(zzfy zzfy, long j) {
        zzfy.zzf |= 536870912;
        zzfy.zzN = j;
    }

    static /* synthetic */ void zzaa(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzg |= 64;
        zzfy.zzX = str;
    }

    static /* synthetic */ void zzab(zzfy zzfy, String str) {
        zzfy.zzg |= 128;
        zzfy.zzY = str;
    }

    static /* synthetic */ void zzac(zzfy zzfy, Iterable iterable) {
        zzfy.zzbI();
        zzih.zzbq(iterable, zzfy.zzi);
    }

    static /* synthetic */ void zzae(zzfy zzfy, int i) {
        zzfy.zzbI();
        zzfy.zzi.remove(i);
    }

    static /* synthetic */ void zzaf(zzfy zzfy, int i, zzgh zzgh) {
        zzgh.getClass();
        zzfy.zzbJ();
        zzfy.zzj.set(i, zzgh);
    }

    static /* synthetic */ void zzag(zzfy zzfy, zzgh zzgh) {
        zzgh.getClass();
        zzfy.zzbJ();
        zzfy.zzj.add(zzgh);
    }

    static /* synthetic */ void zzah(zzfy zzfy, Iterable iterable) {
        zzfy.zzbJ();
        zzih.zzbq(iterable, zzfy.zzj);
    }

    static /* synthetic */ void zzai(zzfy zzfy, int i) {
        zzfy.zzbJ();
        zzfy.zzj.remove(i);
    }

    static /* synthetic */ void zzaj(zzfy zzfy, long j) {
        zzfy.zzf |= 2;
        zzfy.zzk = j;
    }

    static /* synthetic */ void zzak(zzfy zzfy, long j) {
        zzfy.zzf |= 4;
        zzfy.zzl = j;
    }

    static /* synthetic */ void zzal(zzfy zzfy, long j) {
        zzfy.zzf |= 8;
        zzfy.zzm = j;
    }

    static /* synthetic */ void zzam(zzfy zzfy, long j) {
        zzfy.zzf |= 16;
        zzfy.zzn = j;
    }

    static /* synthetic */ void zzan(zzfy zzfy) {
        zzfy.zzf &= -17;
        zzfy.zzn = 0;
    }

    static /* synthetic */ void zzao(zzfy zzfy, long j) {
        zzfy.zzf |= 32;
        zzfy.zzo = j;
    }

    static /* synthetic */ void zzap(zzfy zzfy) {
        zzfy.zzf &= -33;
        zzfy.zzo = 0;
    }

    static /* synthetic */ void zzaq(zzfy zzfy, String str) {
        zzfy.zzf |= 64;
        zzfy.zzp = "android";
    }

    static /* synthetic */ void zzar(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 128;
        zzfy.zzq = str;
    }

    static /* synthetic */ void zzas(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 256;
        zzfy.zzr = str;
    }

    static /* synthetic */ void zzat(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 512;
        zzfy.zzs = str;
    }

    static /* synthetic */ void zzau(zzfy zzfy, int i) {
        zzfy.zzf |= 1024;
        zzfy.zzt = i;
    }

    static /* synthetic */ void zzav(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 2048;
        zzfy.zzu = str;
    }

    static /* synthetic */ void zzaw(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 4096;
        zzfy.zzv = str;
    }

    static /* synthetic */ void zzax(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 8192;
        zzfy.zzw = str;
    }

    static /* synthetic */ void zzay(zzfy zzfy, long j) {
        zzfy.zzf |= 16384;
        zzfy.zzx = j;
    }

    static /* synthetic */ void zzaz(zzfy zzfy, long j) {
        zzfy.zzf |= 32768;
        zzfy.zzy = 46000;
    }

    private final void zzbI() {
        zzke<zzfo> zzke = this.zzi;
        if (!zzke.zzc()) {
            this.zzi = zzjx.zzbB(zzke);
        }
    }

    private final void zzbJ() {
        zzke<zzgh> zzke = this.zzj;
        if (!zzke.zzc()) {
            this.zzj = zzjx.zzbB(zzke);
        }
    }

    public static zzfx zzu() {
        return (zzfx) zze.zzbu();
    }

    public final String zzA() {
        return this.zzu;
    }

    public final String zzB() {
        return this.zzw;
    }

    public final String zzC() {
        return this.zzY;
    }

    public final String zzD() {
        return this.zzr;
    }

    public final String zzE() {
        return this.zzP;
    }

    public final String zzF() {
        return this.zzI;
    }

    public final String zzG() {
        return this.zzX;
    }

    public final String zzH() {
        return this.zzF;
    }

    public final String zzI() {
        return this.zzE;
    }

    public final String zzJ() {
        return this.zzq;
    }

    public final String zzK() {
        return this.zzp;
    }

    public final String zzL() {
        return this.zzz;
    }

    public final String zzM() {
        return this.zzs;
    }

    public final List<zzfk> zzN() {
        return this.zzH;
    }

    public final List<zzfo> zzO() {
        return this.zzi;
    }

    public final List<zzgh> zzP() {
        return this.zzj;
    }

    public final int zza() {
        return this.zzJ;
    }

    public final boolean zzaT() {
        return this.zzA;
    }

    public final boolean zzaU() {
        return this.zzG;
    }

    public final boolean zzaV() {
        return (this.zzf & 1073741824) != 0;
    }

    public final boolean zzaW() {
        return (this.zzf & 33554432) != 0;
    }

    public final boolean zzaX() {
        return (this.zzf & 1048576) != 0;
    }

    public final boolean zzaY() {
        return (this.zzf & 536870912) != 0;
    }

    public final boolean zzaZ() {
        return (this.zzg & 128) != 0;
    }

    public final int zzb() {
        return this.zzD;
    }

    public final boolean zzba() {
        return (this.zzf & 524288) != 0;
    }

    public final boolean zzbb() {
        return (this.zzg & 16) != 0;
    }

    public final boolean zzbc() {
        return (this.zzf & 8) != 0;
    }

    public final boolean zzbd() {
        return (this.zzf & 16384) != 0;
    }

    public final boolean zzbe() {
        return (this.zzf & 131072) != 0;
    }

    public final boolean zzbf() {
        return (this.zzf & 32) != 0;
    }

    public final boolean zzbg() {
        return (this.zzf & 16) != 0;
    }

    public final boolean zzbh() {
        return (this.zzf & 1) != 0;
    }

    public final boolean zzbi() {
        return (this.zzg & 2) != 0;
    }

    public final boolean zzbj() {
        return (this.zzf & 8388608) != 0;
    }

    public final boolean zzbk() {
        return (this.zzf & 4) != 0;
    }

    public final boolean zzbl() {
        return (this.zzf & 1024) != 0;
    }

    public final boolean zzbm() {
        return (this.zzf & 2) != 0;
    }

    public final boolean zzbn() {
        return (this.zzf & 32768) != 0;
    }

    public final int zzc() {
        return this.zzi.size();
    }

    public final int zzd() {
        return this.zzh;
    }

    public final int zze() {
        return this.zzR;
    }

    public final int zzf() {
        return this.zzt;
    }

    public final int zzg() {
        return this.zzj.size();
    }

    public final long zzh() {
        return this.zzO;
    }

    public final long zzi() {
        return this.zzN;
    }

    public final long zzj() {
        return this.zzC;
    }

    public final long zzk() {
        return this.zzV;
    }

    public final long zzm() {
        return this.zzm;
    }

    public final long zzn() {
        return this.zzx;
    }

    public final long zzo() {
        return this.zzo;
    }

    public final long zzp() {
        return this.zzn;
    }

    public final long zzq() {
        return this.zzl;
    }

    public final long zzr() {
        return this.zzk;
    }

    public final long zzs() {
        return this.zzy;
    }

    public final zzfo zzt(int i) {
        return (zzfo) this.zzi.get(i);
    }

    public final zzgh zzw(int i) {
        return (zzgh) this.zzj.get(i);
    }

    public final String zzx() {
        return this.zzS;
    }

    public final String zzy() {
        return this.zzv;
    }

    public final String zzz() {
        return this.zzB;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzbF(zze, "\u00011\u0000\u0002\u0001;1\u0000\u0004\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5ဌ(7ဇ)9ဈ*:ဇ+;ဉ,", new Object[]{"zzf", "zzg", "zzh", "zzi", zzfo.class, "zzj", zzgh.class, "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzE", "zzF", "zzn", "zzG", "zzH", zzfk.class, "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", "zzY", "zzZ", zzfg.zza, "zzaa", "zzab", "zzac", "zzad"});
            case 3:
                return new zzfy();
            case 4:
                return new zzfx((zzff) null);
            case 5:
                return zze;
            default:
                return null;
        }
    }
}
