package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class AbstractListMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 6588350623831699109L;

    /* access modifiers changed from: package-private */
    public abstract List<V> createCollection();

    protected AbstractListMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    /* access modifiers changed from: package-private */
    public List<V> createUnmodifiableEmptyCollection() {
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableList((List) collection);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> wrapCollection(K key, Collection<V> collection) {
        return wrapList(key, (List) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    public List<V> get(@NullableDecl K key) {
        return (List) super.get(key);
    }

    public List<V> removeAll(@NullableDecl Object key) {
        return (List) super.removeAll(key);
    }

    public List<V> replaceValues(@NullableDecl K key, Iterable<? extends V> values) {
        return (List) super.replaceValues(key, values);
    }

    public boolean put(@NullableDecl K key, @NullableDecl V value) {
        return super.put(key, value);
    }

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    public boolean equals(@NullableDecl Object object) {
        return super.equals(object);
    }
}
