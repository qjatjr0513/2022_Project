package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzwh extends zzabs<zzwh, zzwc> implements zzada {
    /* access modifiers changed from: private */
    public static final zzwh zzb;
    private zzuq zzA;
    private zzyv zzB;
    private boolean zzC;
    private String zzD = "";
    private zzva zzE;
    private boolean zzF;
    private String zzG = "";
    private int zzH;
    private String zzI = "";
    private String zzJ = "";
    private int zzK;
    private String zzL = "";
    private byte zzM = 2;
    private int zze;
    private int zzf;
    private int zzg = 1;
    private zzjy zzh;
    private zzna zzi;
    private int zzj;
    private float zzk;
    private zzabz<zzyz> zzl = zzB();
    private zzxt zzm;
    private zzabz<zzvd> zzn = zzB();
    private zzvd zzo;
    private zzvk zzp;
    private zzxl zzq;
    private zzwv zzr;
    private zzxe zzs;
    private zzxa zzt;
    private zzxq zzu;
    private zzvw zzv;
    private zzwj zzw;
    private zzxg zzx;
    private zzvz zzy;
    private zzvn zzz;

    static {
        zzwh zzwh = new zzwh();
        zzb = zzwh;
        zzabs.zzG(zzwh.class, zzwh);
    }

    private zzwh() {
    }

    public static zzwc zza() {
        return (zzwc) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzwh zzwh, zzxt zzxt) {
        zzxt.getClass();
        zzwh.zzm = zzxt;
        zzwh.zze |= 64;
    }

    static /* synthetic */ void zze(zzwh zzwh, zzxl zzxl) {
        zzxl.getClass();
        zzwh.zzq = zzxl;
        zzwh.zze |= 512;
    }

    static /* synthetic */ void zzf(zzwh zzwh, zzwv zzwv) {
        zzwv.getClass();
        zzwh.zzr = zzwv;
        zzwh.zze |= 1024;
    }

    static /* synthetic */ void zzg(zzwh zzwh, zzjy zzjy) {
        zzjy.getClass();
        zzwh.zzh = zzjy;
        zzwh.zze |= 4;
    }

    static /* synthetic */ void zzh(zzwh zzwh, zzvw zzvw) {
        zzvw.getClass();
        zzwh.zzv = zzvw;
        zzwh.zze |= 16384;
    }

    static /* synthetic */ void zzi(zzwh zzwh, zzva zzva) {
        zzva.getClass();
        zzwh.zzE = zzva;
        zzwh.zze |= 8388608;
    }

    static /* synthetic */ void zzj(zzwh zzwh, boolean z) {
        zzwh.zze |= 16777216;
        zzwh.zzF = true;
    }

    static /* synthetic */ void zzk(zzwh zzwh, String str) {
        str.getClass();
        zzwh.zze |= 33554432;
        zzwh.zzG = str;
    }

    static /* synthetic */ void zzl(zzwh zzwh, String str) {
        zzwh.zze |= 134217728;
        zzwh.zzI = "2.5.0";
    }

    static /* synthetic */ void zzm(zzwh zzwh, String str) {
        str.getClass();
        zzwh.zze |= 1073741824;
        zzwh.zzL = str;
    }

    static /* synthetic */ void zzn(zzwh zzwh, int i) {
        zzwh.zzg = i;
        zzwh.zze |= 2;
    }

    static /* synthetic */ void zzo(zzwh zzwh, int i) {
        zzwh.zzK = i - 1;
        zzwh.zze |= 536870912;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        byte b = 1;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzM);
            case 2:
                return zzF(zzb, "\u0001!\u0000\u0001\u0001!!\u0000\u0002\u0003\u0001???\u0001\u0002???\u0002\u0003???\u0003\u0004\u001b\u0005???\u0006\u0006\u001b\u0007???\u0007\b???\b\t???\u0004\n???\u0005\u000b???\u0015\f???\t\r???\u0016\u000e???\n\u000f???\u000b\u0010???\f\u0011???\r\u0012???\u000e\u0013???\u000f\u0014???\u0010\u0015???\u0011\u0016???\u0012\u0017???\u0013\u0018???\u0017\u0019???\u0000\u001a???\u0014\u001b???\u0018\u001c???\u0019\u001d???\u001a\u001e???\u001b\u001f???\u001c ???\u001d!???\u001e", new Object[]{"zze", "zzg", zzwe.zza, "zzh", "zzi", "zzl", zzyz.class, "zzm", "zzn", zzvd.class, "zzo", "zzp", "zzj", zzwd.zza, "zzk", "zzC", "zzq", "zzD", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzE", "zzf", "zzB", "zzF", "zzG", "zzH", zzwf.zza, "zzI", "zzJ", "zzK", zzwg.zza, "zzL"});
            case 3:
                return new zzwh();
            case 4:
                return new zzwc((zztv) null);
            case 5:
                return zzb;
            default:
                if (obj == null) {
                    b = 0;
                }
                this.zzM = b;
                return null;
        }
    }
}
