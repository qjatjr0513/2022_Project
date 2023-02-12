package com.google.android.libraries.places.internal;

import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzgm {
    public static <F, T> List<T> zza(List<F> list, zzi zzi) {
        if (list instanceof RandomAccess) {
            return new zzgj(list, zzi, (zzi) null);
        }
        return new zzgl(list, zzi, (zzi) null);
    }
}
