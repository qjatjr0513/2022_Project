package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzxn extends zzabs<zzxn, zzxm> implements zzada {
    /* access modifiers changed from: private */
    public static final zzxn zzb;
    private zzabz<String> zze = zzabs.zzB();

    static {
        zzxn zzxn = new zzxn();
        zzb = zzxn;
        zzabs.zzG(zzxn.class, zzxn);
    }

    private zzxn() {
    }

    public static zzxm zza() {
        return (zzxm) zzb.zzx();
    }

    static /* synthetic */ void zzc(zzxn zzxn, Iterable iterable) {
        zzabz<String> zzabz = zzxn.zze;
        if (!zzabz.zzc()) {
            zzxn.zze = zzabs.zzC(zzabz);
        }
        zzaaj.zzt(iterable, zzxn.zze);
    }

    /* access modifiers changed from: protected */
    public final Object zzd(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zze"});
            case 3:
                return new zzxn();
            case 4:
                return new zzxm((zztv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
