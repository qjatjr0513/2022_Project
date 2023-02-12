package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzacl extends zzacn {
    private zzacl() {
        super((zzacm) null);
    }

    /* synthetic */ zzacl(zzack zzack) {
        super((zzacm) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        ((zzabz) zzael.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzb(Object obj, Object obj2, long j) {
        zzabz zzabz = (zzabz) zzael.zzf(obj, j);
        zzabz zzabz2 = (zzabz) zzael.zzf(obj2, j);
        int size = zzabz.size();
        int size2 = zzabz2.size();
        if (size > 0 && size2 > 0) {
            if (!zzabz.zzc()) {
                zzabz = zzabz.zzf(size2 + size);
            }
            zzabz.addAll(zzabz2);
        }
        if (size > 0) {
            zzabz2 = zzabz;
        }
        zzael.zzs(obj, j, zzabz2);
    }
}
