package com.google.android.libraries.places.internal;

import java.util.Comparator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzgo<T> implements Comparator<T> {
    protected zzgo() {
    }

    public static <T> zzgo<T> zza(Comparator<T> comparator) {
        return new zzfu(comparator);
    }
}
