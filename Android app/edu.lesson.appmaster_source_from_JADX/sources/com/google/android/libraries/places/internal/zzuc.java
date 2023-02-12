package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzuc extends zzabs<zzuc, zzub> implements zzada {
    /* access modifiers changed from: private */
    public static final zzuc zzb;
    private int zze;
    private int zzf;

    static {
        zzuc zzuc = new zzuc();
        zzb = zzuc;
        zzabs.zzG(zzuc.class, zzuc);
    }

    private zzuc() {
    }

    public static zzub zza() {
        return (zzub) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzuc zzuc, int i) {
        zzuc.zze |= 1;
        zzuc.zzf = i;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
            case 3:
                return new zzuc();
            case 4:
                return new zzub((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
