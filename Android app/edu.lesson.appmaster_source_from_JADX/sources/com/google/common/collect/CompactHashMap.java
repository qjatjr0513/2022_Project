package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    /* access modifiers changed from: private */
    public static final Object NOT_FOUND = new Object();
    @NullableDecl
    transient int[] entries;
    @NullableDecl
    private transient Set<Map.Entry<K, V>> entrySetView;
    @NullableDecl
    private transient Set<K> keySetView;
    @NullableDecl
    transient Object[] keys;
    /* access modifiers changed from: private */
    public transient int metadata;
    private transient int size;
    /* access modifiers changed from: private */
    @NullableDecl
    public transient Object table;
    @NullableDecl
    transient Object[] values;
    @NullableDecl
    private transient Collection<V> valuesView;

    static /* synthetic */ int access$710(CompactHashMap x0) {
        int i = x0.size;
        x0.size = i - 1;
        return i;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactHashMap<>(expectedSize);
    }

    CompactHashMap() {
        init(3);
    }

    CompactHashMap(int expectedSize) {
        init(expectedSize);
    }

    /* access modifiers changed from: package-private */
    public void init(int expectedSize) {
        Preconditions.checkArgument(expectedSize >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(expectedSize, 1, 1073741823);
    }

    /* access modifiers changed from: package-private */
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    /* access modifiers changed from: package-private */
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int expectedSize = this.metadata;
        int buckets = CompactHashing.tableSize(expectedSize);
        this.table = CompactHashing.createTable(buckets);
        setHashTableMask(buckets - 1);
        this.entries = new int[expectedSize];
        this.keys = new Object[expectedSize];
        this.values = new Object[expectedSize];
        return expectedSize;
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public Map<K, V> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Map<K, V> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashMap(tableSize, 1.0f);
    }

    /* access modifiers changed from: package-private */
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> newDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int i = firstEntryIndex();
        while (i >= 0) {
            newDelegate.put(this.keys[i], this.values[i]);
            i = getSuccessor(i);
        }
        this.table = newDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return newDelegate;
    }

    private void setHashTableMask(int mask) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(mask), 31);
    }

    /* access modifiers changed from: private */
    public int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* access modifiers changed from: package-private */
    public void incrementModCount() {
        this.metadata += 32;
    }

    /* access modifiers changed from: package-private */
    public void accessEntry(int index) {
    }

    @NullableDecl
    public V put(@NullableDecl K key, @NullableDecl V value) {
        int hashPrefix;
        K k = key;
        V v = value;
        if (needsAllocArrays()) {
            allocArrays();
        }
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.put(k, v);
        }
        int[] entries2 = this.entries;
        Object[] keys2 = this.keys;
        V[] values2 = this.values;
        int newEntryIndex = this.size;
        int newSize = newEntryIndex + 1;
        int hash = Hashing.smearedHash(key);
        int mask = hashTableMask();
        int next = hash & mask;
        int entry = CompactHashing.tableGet(this.table, next);
        if (entry != 0) {
            int hashPrefix2 = CompactHashing.getHashPrefix(hash, mask);
            int bucketLength = 0;
            while (true) {
                int entryIndex = entry - 1;
                int i = entry;
                int entry2 = entries2[entryIndex];
                int tableIndex = next;
                if (CompactHashing.getHashPrefix(entry2, mask) != hashPrefix2 || !Objects.equal(k, keys2[entryIndex])) {
                    int next2 = CompactHashing.getNext(entry2, mask);
                    bucketLength++;
                    if (next2 == 0) {
                        int i2 = hashPrefix2;
                        if (bucketLength >= 9) {
                            return convertToHashFloodingResistantImplementation().put(k, v);
                        }
                        if (newSize > mask) {
                            hashPrefix = resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
                            int i3 = next2;
                        } else {
                            entries2[entryIndex] = CompactHashing.maskCombine(entry2, newEntryIndex + 1, mask);
                            hashPrefix = mask;
                            int i4 = next2;
                        }
                    } else {
                        int mask2 = hashPrefix2;
                        entry = next2;
                        next = tableIndex;
                    }
                } else {
                    V oldValue = values2[entryIndex];
                    values2[entryIndex] = v;
                    accessEntry(entryIndex);
                    return oldValue;
                }
            }
        } else if (newSize > mask) {
            hashPrefix = resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            int i5 = entry;
            int i6 = next;
        } else {
            CompactHashing.tableSet(this.table, next, newEntryIndex + 1);
            hashPrefix = mask;
            int i7 = entry;
            int i8 = next;
        }
        resizeMeMaybe(newSize);
        insertEntry(newEntryIndex, key, value, hash, hashPrefix);
        this.size = newSize;
        incrementModCount();
        return null;
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int entryIndex, @NullableDecl K key, @NullableDecl V value, int hash, int mask) {
        this.entries[entryIndex] = CompactHashing.maskCombine(hash, 0, mask);
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
    }

    private void resizeMeMaybe(int newSize) {
        int newCapacity;
        int entriesSize = this.entries.length;
        if (newSize > entriesSize && (newCapacity = Math.min(1073741823, (Math.max(1, entriesSize >>> 1) + entriesSize) | 1)) != entriesSize) {
            resizeEntries(newCapacity);
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeEntries(int newCapacity) {
        this.entries = Arrays.copyOf(this.entries, newCapacity);
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
    }

    private int resizeTable(int mask, int newCapacity, int targetHash, int targetEntryIndex) {
        int i = mask;
        Object newTable = CompactHashing.createTable(newCapacity);
        int newMask = newCapacity - 1;
        if (targetEntryIndex != 0) {
            CompactHashing.tableSet(newTable, targetHash & newMask, targetEntryIndex + 1);
        }
        Object table2 = this.table;
        int[] entries2 = this.entries;
        for (int tableIndex = 0; tableIndex <= i; tableIndex++) {
            int next = CompactHashing.tableGet(table2, tableIndex);
            while (next != 0) {
                int entryIndex = next - 1;
                int entry = entries2[entryIndex];
                int hash = CompactHashing.getHashPrefix(entry, mask) | tableIndex;
                int newTableIndex = hash & newMask;
                int newNext = CompactHashing.tableGet(newTable, newTableIndex);
                CompactHashing.tableSet(newTable, newTableIndex, next);
                entries2[entryIndex] = CompactHashing.maskCombine(hash, newNext, newMask);
                next = CompactHashing.getNext(entry, mask);
            }
        }
        this.table = newTable;
        setHashTableMask(newMask);
        return newMask;
    }

    /* access modifiers changed from: private */
    public int indexOf(@NullableDecl Object key) {
        if (needsAllocArrays()) {
            return -1;
        }
        int hash = Hashing.smearedHash(key);
        int mask = hashTableMask();
        int next = CompactHashing.tableGet(this.table, hash & mask);
        if (next == 0) {
            return -1;
        }
        int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
        do {
            int entryIndex = next - 1;
            int entry = this.entries[entryIndex];
            if (CompactHashing.getHashPrefix(entry, mask) == hashPrefix && Objects.equal(key, this.keys[entryIndex])) {
                return entryIndex;
            }
            next = CompactHashing.getNext(entry, mask);
        } while (next != 0);
        return -1;
    }

    public boolean containsKey(@NullableDecl Object key) {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.containsKey(key);
        }
        return indexOf(key) != -1;
    }

    public V get(@NullableDecl Object key) {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.get(key);
        }
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }
        accessEntry(index);
        return this.values[index];
    }

    @NullableDecl
    public V remove(@NullableDecl Object key) {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.remove(key);
        }
        Object oldValue = removeHelper(key);
        if (oldValue == NOT_FOUND) {
            return null;
        }
        return oldValue;
    }

    /* access modifiers changed from: private */
    @NullableDecl
    public Object removeHelper(@NullableDecl Object key) {
        if (needsAllocArrays()) {
            return NOT_FOUND;
        }
        int mask = hashTableMask();
        int index = CompactHashing.remove(key, (Object) null, mask, this.table, this.entries, this.keys, (Object[]) null);
        if (index == -1) {
            return NOT_FOUND;
        }
        Object oldValue = this.values[index];
        moveLastEntry(index, mask);
        this.size--;
        incrementModCount();
        return oldValue;
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int dstIndex, int mask) {
        int entryIndex;
        int entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            Object[] objArr = this.keys;
            Object key = objArr[srcIndex];
            objArr[dstIndex] = key;
            Object[] objArr2 = this.values;
            objArr2[dstIndex] = objArr2[srcIndex];
            objArr[srcIndex] = null;
            objArr2[srcIndex] = null;
            int[] iArr = this.entries;
            iArr[dstIndex] = iArr[srcIndex];
            iArr[srcIndex] = 0;
            int tableIndex = Hashing.smearedHash(key) & mask;
            int next = CompactHashing.tableGet(this.table, tableIndex);
            int srcNext = srcIndex + 1;
            if (next == srcNext) {
                CompactHashing.tableSet(this.table, tableIndex, dstIndex + 1);
                return;
            }
            do {
                entryIndex = next - 1;
                entry = this.entries[entryIndex];
                next = CompactHashing.getNext(entry, mask);
            } while (next != srcNext);
            this.entries[entryIndex] = CompactHashing.maskCombine(entry, dstIndex + 1, mask);
            return;
        }
        this.keys[dstIndex] = null;
        this.values[dstIndex] = null;
        this.entries[dstIndex] = 0;
    }

    /* access modifiers changed from: package-private */
    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int getSuccessor(int entryIndex) {
        if (entryIndex + 1 < this.size) {
            return entryIndex + 1;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove - 1;
    }

    private abstract class Itr<T> implements Iterator<T> {
        int currentIndex;
        int expectedMetadata;
        int indexToRemove;

        /* access modifiers changed from: package-private */
        public abstract T getOutput(int i);

        private Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        public T next() {
            checkForConcurrentModification();
            if (hasNext()) {
                int i = this.currentIndex;
                this.indexToRemove = i;
                T result = getOutput(i);
                this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
                return result;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            incrementExpectedModCount();
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.keys[this.indexToRemove]);
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        /* access modifiers changed from: package-private */
        public void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.expectedMetadata) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
        }
        Set<K> createKeySet = createKeySet();
        this.keySetView = createKeySet;
        return createKeySet;
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return new KeySetView();
    }

    class KeySetView extends AbstractSet<K> {
        KeySetView() {
        }

        public int size() {
            return CompactHashMap.this.size();
        }

        public boolean contains(Object o) {
            return CompactHashMap.this.containsKey(o);
        }

        public boolean remove(@NullableDecl Object o) {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.keySet().remove(o);
            }
            return CompactHashMap.this.removeHelper(o) != CompactHashMap.NOT_FOUND;
        }

        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> keySetIterator() {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.keySet().iterator();
        }
        return new CompactHashMap<K, V>.Itr<K>() {
            /* access modifiers changed from: package-private */
            public K getOutput(int entry) {
                return CompactHashMap.this.keys[entry];
            }
        };
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySetView = createEntrySet;
        return createEntrySet;
    }

    /* access modifiers changed from: package-private */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        EntrySetView() {
        }

        public int size() {
            return CompactHashMap.this.size();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        public boolean contains(@NullableDecl Object o) {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.entrySet().contains(o);
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            int index = CompactHashMap.this.indexOf(entry.getKey());
            if (index == -1 || !Objects.equal(CompactHashMap.this.values[index], entry.getValue())) {
                return false;
            }
            return true;
        }

        public boolean remove(@NullableDecl Object o) {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.entrySet().remove(o);
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            if (CompactHashMap.this.needsAllocArrays()) {
                return false;
            }
            int mask = CompactHashMap.this.hashTableMask();
            int index = CompactHashing.remove(entry.getKey(), entry.getValue(), mask, CompactHashMap.this.table, CompactHashMap.this.entries, CompactHashMap.this.keys, CompactHashMap.this.values);
            if (index == -1) {
                return false;
            }
            CompactHashMap.this.moveLastEntry(index, mask);
            CompactHashMap.access$710(CompactHashMap.this);
            CompactHashMap.this.incrementModCount();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.entrySet().iterator();
        }
        return new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() {
            /* access modifiers changed from: package-private */
            public Map.Entry<K, V> getOutput(int entry) {
                return new MapEntry(entry);
            }
        };
    }

    final class MapEntry extends AbstractMapEntry<K, V> {
        @NullableDecl
        private final K key;
        private int lastKnownIndex;

        MapEntry(int index) {
            this.key = CompactHashMap.this.keys[index];
            this.lastKnownIndex = index;
        }

        @NullableDecl
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i == -1 || i >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @NullableDecl
        public V getValue() {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.get(this.key);
            }
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return null;
            }
            return CompactHashMap.this.values[this.lastKnownIndex];
        }

        public V setValue(V value) {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            if (delegate != null) {
                return delegate.put(this.key, value);
            }
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                CompactHashMap.this.put(this.key, value);
                return null;
            }
            V old = CompactHashMap.this.values[this.lastKnownIndex];
            CompactHashMap.this.values[this.lastKnownIndex] = value;
            return old;
        }
    }

    public int size() {
        Map<K, V> delegate = delegateOrNull();
        return delegate != null ? delegate.size() : this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsValue(@NullableDecl Object value) {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.containsValue(value);
        }
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(value, this.values[i])) {
                return true;
            }
        }
        return false;
    }

    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.valuesView = createValues;
        return createValues;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new ValuesView();
    }

    class ValuesView extends AbstractCollection<V> {
        ValuesView() {
        }

        public int size() {
            return CompactHashMap.this.size();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valuesIterator() {
        Map<K, V> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.values().iterator();
        }
        return new CompactHashMap<K, V>.Itr<V>() {
            /* access modifiers changed from: package-private */
            public V getOutput(int entry) {
                return CompactHashMap.this.values[entry];
            }
        };
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            Map<K, V> delegate = delegateOrNull();
            if (delegate != null) {
                Map<K, V> newDelegate = createHashFloodingResistantDelegate(size());
                newDelegate.putAll(delegate);
                this.table = newDelegate;
                return;
            }
            int size2 = this.size;
            if (size2 < this.entries.length) {
                resizeEntries(size2);
            }
            int minimumTableSize = CompactHashing.tableSize(size2);
            int mask = hashTableMask();
            if (minimumTableSize < mask) {
                resizeTable(mask, minimumTableSize, 0, 0);
            }
        }
    }

    public void clear() {
        if (!needsAllocArrays()) {
            incrementModCount();
            Map<K, V> delegate = delegateOrNull();
            if (delegate != null) {
                this.metadata = Ints.constrainToRange(size(), 3, 1073741823);
                delegate.clear();
                this.table = null;
                this.size = 0;
                return;
            }
            Arrays.fill(this.keys, 0, this.size, (Object) null);
            Arrays.fill(this.values, 0, this.size, (Object) null);
            CompactHashing.tableClear(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size());
        Iterator<Map.Entry<K, V>> entryIterator = entrySetIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<K, V> e = entryIterator.next();
            stream.writeObject(e.getKey());
            stream.writeObject(e.getValue());
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int elementCount = stream.readInt();
        if (elementCount >= 0) {
            init(elementCount);
            for (int i = 0; i < elementCount; i++) {
                put(stream.readObject(), stream.readObject());
            }
            return;
        }
        throw new InvalidObjectException(new StringBuilder(25).append("Invalid size: ").append(elementCount).toString());
    }
}
