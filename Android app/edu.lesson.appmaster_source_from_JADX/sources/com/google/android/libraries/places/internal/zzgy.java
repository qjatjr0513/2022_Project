package com.google.android.libraries.places.internal;

import java.util.Iterator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzgy<F, T> implements Iterator<T> {
    final Iterator<? extends F> zzb;

    zzgy(Iterator<? extends F> it) {
        if (it != null) {
            this.zzb = it;
            return;
        }
        throw null;
    }

    public final boolean hasNext() {
        return this.zzb.hasNext();
    }

    public final T next() {
        return zza(this.zzb.next());
    }

    public final void remove() {
        this.zzb.remove();
    }

    /* access modifiers changed from: package-private */
    public abstract T zza(F f);
}
