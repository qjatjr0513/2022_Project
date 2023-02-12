package com.google.android.libraries.places.internal;

import java.util.ListIterator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzgz<F, T> extends zzgy<F, T> implements ListIterator<T> {
    zzgz(ListIterator<? extends F> listIterator) {
        super(listIterator);
    }

    public final void add(T t) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    public final boolean hasPrevious() {
        return this.zzb.hasPrevious();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    public final int nextIndex() {
        return this.zzb.nextIndex();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    public final T previous() {
        return zza(this.zzb.previous());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Iterator, java.util.ListIterator] */
    public final int previousIndex() {
        return this.zzb.previousIndex();
    }

    public final void set(T t) {
        throw new UnsupportedOperationException();
    }
}
