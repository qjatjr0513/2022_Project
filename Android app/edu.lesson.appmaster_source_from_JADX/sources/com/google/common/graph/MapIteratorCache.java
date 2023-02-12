package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class MapIteratorCache<K, V> {
    /* access modifiers changed from: private */
    public final Map<K, V> backingMap;
    /* access modifiers changed from: private */
    @NullableDecl
    public volatile transient Map.Entry<K, V> cacheEntry;

    MapIteratorCache(Map<K, V> backingMap2) {
        this.backingMap = (Map) Preconditions.checkNotNull(backingMap2);
    }

    public final V put(@NullableDecl K key, @NullableDecl V value) {
        clearCache();
        return this.backingMap.put(key, value);
    }

    public final V remove(@NullableDecl Object key) {
        clearCache();
        return this.backingMap.remove(key);
    }

    public final void clear() {
        clearCache();
        this.backingMap.clear();
    }

    public V get(@NullableDecl Object key) {
        V value = getIfCached(key);
        return value != null ? value : getWithoutCaching(key);
    }

    public final V getWithoutCaching(@NullableDecl Object key) {
        return this.backingMap.get(key);
    }

    public final boolean containsKey(@NullableDecl Object key) {
        return getIfCached(key) != null || this.backingMap.containsKey(key);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>() {
            public UnmodifiableIterator<K> iterator() {
                final Iterator<Map.Entry<K, V>> entryIterator = MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>() {
                    public boolean hasNext() {
                        return entryIterator.hasNext();
                    }

                    public K next() {
                        Map.Entry<K, V> entry = (Map.Entry) entryIterator.next();
                        Map.Entry unused = MapIteratorCache.this.cacheEntry = entry;
                        return entry.getKey();
                    }
                };
            }

            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }

            public boolean contains(@NullableDecl Object key) {
                return MapIteratorCache.this.containsKey(key);
            }
        };
    }

    /* access modifiers changed from: protected */
    public V getIfCached(@NullableDecl Object key) {
        Map.Entry<K, V> entry = this.cacheEntry;
        if (entry == null || entry.getKey() != key) {
            return null;
        }
        return entry.getValue();
    }

    /* access modifiers changed from: protected */
    public void clearCache() {
        this.cacheEntry = null;
    }
}
