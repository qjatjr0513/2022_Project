package com.google.firebase.database.collection;

import com.google.firebase.database.collection.ImmutableSortedMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImmutableSortedSet<T> implements Iterable<T> {
    private final ImmutableSortedMap<T, Void> map;

    private static class WrappedEntryIterator<T> implements Iterator<T> {
        final Iterator<Map.Entry<T, Void>> iterator;

        public WrappedEntryIterator(Iterator<Map.Entry<T, Void>> iterator2) {
            this.iterator = iterator2;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public T next() {
            return this.iterator.next().getKey();
        }

        public void remove() {
            this.iterator.remove();
        }
    }

    public ImmutableSortedSet(List<T> elems, Comparator<T> comparator) {
        this.map = ImmutableSortedMap.Builder.buildFrom(elems, Collections.emptyMap(), ImmutableSortedMap.Builder.identityTranslator(), comparator);
    }

    private ImmutableSortedSet(ImmutableSortedMap<T, Void> map2) {
        this.map = map2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImmutableSortedSet)) {
            return false;
        }
        return this.map.equals(((ImmutableSortedSet) other).map);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public boolean contains(T entry) {
        return this.map.containsKey(entry);
    }

    public ImmutableSortedSet<T> remove(T entry) {
        ImmutableSortedMap<T, Void> newMap = this.map.remove(entry);
        return newMap == this.map ? this : new ImmutableSortedSet<>(newMap);
    }

    public ImmutableSortedSet<T> insert(T entry) {
        return new ImmutableSortedSet<>(this.map.insert(entry, null));
    }

    public ImmutableSortedSet<T> unionWith(ImmutableSortedSet<T> other) {
        ImmutableSortedSet<T> result = this;
        int size = result.size();
        int size2 = other.size();
        ImmutableSortedSet<T> other2 = other;
        if (size < size2) {
            result = other;
            other2 = this;
        }
        Iterator it = other2.iterator();
        while (it.hasNext()) {
            result = result.insert(it.next());
        }
        return result;
    }

    public T getMinEntry() {
        return this.map.getMinKey();
    }

    public T getMaxEntry() {
        return this.map.getMaxKey();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Iterator<T> iterator() {
        return new WrappedEntryIterator(this.map.iterator());
    }

    public Iterator<T> iteratorFrom(T entry) {
        return new WrappedEntryIterator(this.map.iteratorFrom(entry));
    }

    public Iterator<T> reverseIteratorFrom(T entry) {
        return new WrappedEntryIterator(this.map.reverseIteratorFrom(entry));
    }

    public Iterator<T> reverseIterator() {
        return new WrappedEntryIterator(this.map.reverseIterator());
    }

    public T getPredecessorEntry(T entry) {
        return this.map.getPredecessorKey(entry);
    }

    public int indexOf(T entry) {
        return this.map.indexOf(entry);
    }
}
