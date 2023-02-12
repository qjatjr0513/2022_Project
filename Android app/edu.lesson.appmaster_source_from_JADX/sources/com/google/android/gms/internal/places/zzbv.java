package com.google.android.gms.internal.places;

final class zzbv extends zzbu {
    private zzbv() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzab();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzb(Object obj, Object obj2, long j) {
        zzbh zzc = zzc(obj, j);
        zzbh zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzaa()) {
                zzc = zzc.zzh(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzdy.zzb(obj, j, (Object) zzc2);
    }

    private static <E> zzbh<E> zzc(Object obj, long j) {
        return (zzbh) zzdy.zzp(obj, j);
    }
}
