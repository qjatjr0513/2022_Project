package com.google.android.libraries.places.internal;

import java.util.Comparator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzaap implements Comparator<zzaax> {
    zzaap() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzaax zzaax = (zzaax) obj;
        zzaax zzaax2 = (zzaax) obj2;
        zzaan zzaan = new zzaan(zzaax);
        zzaan zzaan2 = new zzaan(zzaax2);
        while (zzaan.hasNext() && zzaan2.hasNext()) {
            int zza = zzaao.zza(zzaan.zza() & 255, zzaan2.zza() & 255);
            if (zza != 0) {
                return zza;
            }
        }
        return zzaao.zza(zzaax.zzd(), zzaax2.zzd());
    }
}
