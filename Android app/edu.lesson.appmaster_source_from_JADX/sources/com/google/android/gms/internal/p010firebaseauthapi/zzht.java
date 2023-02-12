package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzht */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzht extends zzzw<zzht, zzhs> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzht zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzhz zzf;
    /* access modifiers changed from: private */
    public zzyu zzg = zzyu.zzb;

    static {
        zzht zzht = new zzht();
        zzb = zzht;
        zzzw.zzF(zzht.class, zzht);
    }

    private zzht() {
    }

    public static zzhs zzb() {
        return (zzhs) zzb.zzt();
    }

    public static zzht zzd() {
        return zzb;
    }

    public static zzht zze(zzyu zzyu, zzzj zzzj) throws zzaae {
        return (zzht) zzzw.zzw(zzb, zzyu, zzzj);
    }

    static /* synthetic */ void zzi(zzht zzht, zzhz zzhz) {
        zzhz.getClass();
        zzht.zzf = zzhz;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzhz zzf() {
        zzhz zzhz = this.zzf;
        return zzhz == null ? zzhz.zze() : zzhz;
    }

    public final zzyu zzg() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzht();
            case 4:
                return new zzhs((zzhr) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
