package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzkp extends zzkq {
    private zzkp() {
        super((zzkn) null);
    }

    /* synthetic */ zzkp(zzkn zzkn) {
        super((zzkn) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        ((zzke) zzml.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzb(Object obj, Object obj2, long j) {
        zzke zzke = (zzke) zzml.zzf(obj, j);
        zzke zzke2 = (zzke) zzml.zzf(obj2, j);
        int size = zzke.size();
        int size2 = zzke2.size();
        if (size > 0 && size2 > 0) {
            if (!zzke.zzc()) {
                zzke = zzke.zzd(size2 + size);
            }
            zzke.addAll(zzke2);
        }
        if (size > 0) {
            zzke2 = zzke;
        }
        zzml.zzs(obj, j, zzke2);
    }
}
