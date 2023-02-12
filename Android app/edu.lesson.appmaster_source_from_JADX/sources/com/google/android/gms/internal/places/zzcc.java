package com.google.android.gms.internal.places;

import java.io.IOException;

public final class zzcc<K, V> {
    static <K, V> void zzb(zzaj zzaj, zzcb<K, V> zzcb, K k, V v) throws IOException {
        zzav.zzb(zzaj, zzcb.zzkj, 1, k);
        zzav.zzb(zzaj, zzcb.zzkl, 2, v);
    }

    static <K, V> int zzb(zzcb<K, V> zzcb, K k, V v) {
        return zzav.zzb(zzcb.zzkj, 1, k) + zzav.zzb(zzcb.zzkl, 2, v);
    }
}
