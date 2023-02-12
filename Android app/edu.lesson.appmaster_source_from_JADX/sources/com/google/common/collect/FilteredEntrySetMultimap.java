package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Set;

final class FilteredEntrySetMultimap<K, V> extends FilteredEntryMultimap<K, V> implements FilteredSetMultimap<K, V> {
    FilteredEntrySetMultimap(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
        super(unfiltered, predicate);
    }

    public SetMultimap<K, V> unfiltered() {
        return (SetMultimap) this.unfiltered;
    }

    public Set<V> get(K key) {
        return (Set) super.get(key);
    }

    public Set<V> removeAll(Object key) {
        return (Set) super.removeAll(key);
    }

    public Set<V> replaceValues(K key, Iterable<? extends V> values) {
        return (Set) super.replaceValues(key, values);
    }

    /* access modifiers changed from: package-private */
    public Set<Map.Entry<K, V>> createEntries() {
        return Sets.filter(unfiltered().entries(), entryPredicate());
    }

    public Set<Map.Entry<K, V>> entries() {
        return (Set) super.entries();
    }
}
