package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzuo extends zzabs<zzuo, zzun> implements zzada {
    /* access modifiers changed from: private */
    public static final zzuo zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzuo zzuo = new zzuo();
        zzb = zzuo;
        zzabs.zzG(zzuo.class, zzuo);
    }

    private zzuo() {
    }

    public static zzun zza() {
        return (zzun) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzuo zzuo, int i) {
        zzuo.zze |= 1;
        zzuo.zzf = 1;
    }

    static /* synthetic */ void zze(zzuo zzuo, int i) {
        zzuo.zze |= 2;
        zzuo.zzg = i;
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzuo();
            case 4:
                return new zzun((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
