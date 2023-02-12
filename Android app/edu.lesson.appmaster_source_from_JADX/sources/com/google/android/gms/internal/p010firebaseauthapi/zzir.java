package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzir */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzir extends zzzw<zzir, zzio> implements zzaba {
    /* access modifiers changed from: private */
    public static final zzir zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzaab<zziq> zzf = zzz();

    static {
        zzir zzir = new zzir();
        zzb = zzir;
        zzzw.zzF(zzir.class, zzir);
    }

    private zzir() {
    }

    public static zzio zzc() {
        return (zzio) zzb.zzt();
    }

    public static zzir zzf(byte[] bArr, zzzj zzzj) throws zzaae {
        return (zzir) zzzw.zzx(zzb, bArr, zzzj);
    }

    static /* synthetic */ void zzi(zzir zzir, zziq zziq) {
        zziq.getClass();
        zzaab<zziq> zzaab = zzir.zzf;
        if (!zzaab.zzc()) {
            zzir.zzf = zzzw.zzA(zzaab);
        }
        zzir.zzf.add(zziq);
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze;
    }

    public final zziq zzd(int i) {
        return (zziq) this.zzf.get(i);
    }

    public final List<zziq> zzg() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zziq.class});
            case 3:
                return new zzir();
            case 4:
                return new zzio((zzin) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
