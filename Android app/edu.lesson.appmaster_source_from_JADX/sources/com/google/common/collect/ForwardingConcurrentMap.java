package com.google.common.collect;

import java.util.concurrent.ConcurrentMap;

public abstract class ForwardingConcurrentMap<K, V> extends ForwardingMap<K, V> implements ConcurrentMap<K, V> {
    /* access modifiers changed from: protected */
    public abstract ConcurrentMap<K, V> delegate();

    protected ForwardingConcurrentMap() {
    }

    public V putIfAbsent(K key, V value) {
        return delegate().putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return delegate().remove(key, value);
    }

    public V replace(K key, V value) {
        return delegate().replace(key, value);
    }

    public boolean replace(K key, V oldValue, V newValue) {
        return delegate().replace(key, oldValue, newValue);
    }
}
