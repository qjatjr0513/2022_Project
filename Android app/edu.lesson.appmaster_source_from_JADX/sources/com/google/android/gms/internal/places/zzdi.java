package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzdi implements Iterator<Object> {
    zzdi() {
    }

    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
