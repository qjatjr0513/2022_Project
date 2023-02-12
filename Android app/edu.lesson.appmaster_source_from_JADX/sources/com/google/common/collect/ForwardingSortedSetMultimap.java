package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class ForwardingSortedSetMultimap<K, V> extends ForwardingSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    /* access modifiers changed from: protected */
    public abstract SortedSetMultimap<K, V> delegate();

    protected ForwardingSortedSetMultimap() {
    }

    public SortedSet<V> get(@NullableDecl K key) {
        return delegate().get(key);
    }

    public SortedSet<V> removeAll(@NullableDecl Object key) {
        return delegate().removeAll(key);
    }

    public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
        return delegate().replaceValues(key, values);
    }

    public Comparator<? super V> valueComparator() {
        return delegate().valueComparator();
    }
}
