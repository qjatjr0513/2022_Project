package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    @NullableDecl
    transient Object[] elements;
    @NullableDecl
    private transient int[] entries;
    /* access modifiers changed from: private */
    public transient int metadata;
    private transient int size;
    @NullableDecl
    private transient Object table;

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> set = createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E... elements2) {
        CompactHashSet<E> set = createWithExpectedSize(elements2.length);
        Collections.addAll(set, elements2);
        return set;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactHashSet<>(expectedSize);
    }

    CompactHashSet() {
        init(3);
    }

    CompactHashSet(int expectedSize) {
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
        this.elements = new Object[expectedSize];
        return expectedSize;
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public Set<E> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    private Set<E> createHashFloodingResistantDelegate(int tableSize) {
        return new LinkedHashSet(tableSize, 1.0f);
    }

    /* access modifiers changed from: package-private */
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> newDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int i = firstEntryIndex();
        while (i >= 0) {
            newDelegate.add(this.elements[i]);
            i = getSuccessor(i);
        }
        this.table = newDelegate;
        this.entries = null;
        this.elements = null;
        incrementModCount();
        return newDelegate;
    }

    /* access modifiers changed from: package-private */
    public boolean isUsingHashFloodingResistance() {
        return delegateOrNull() != null;
    }

    private void setHashTableMask(int mask) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(mask), 31);
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* access modifiers changed from: package-private */
    public void incrementModCount() {
        this.metadata += 32;
    }

    public boolean add(@NullableDecl E object) {
        int entryIndex;
        int entry;
        if (needsAllocArrays()) {
            allocArrays();
        }
        Set<E> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.add(object);
        }
        int[] entries2 = this.entries;
        Object[] elements2 = this.elements;
        int newEntryIndex = this.size;
        int newSize = newEntryIndex + 1;
        int hash = Hashing.smearedHash(object);
        int mask = hashTableMask();
        int tableIndex = hash & mask;
        int next = CompactHashing.tableGet(this.table, tableIndex);
        if (next != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
            int bucketLength = 0;
            do {
                entryIndex = next - 1;
                entry = entries2[entryIndex];
                if (CompactHashing.getHashPrefix(entry, mask) == hashPrefix && Objects.equal(object, elements2[entryIndex])) {
                    return false;
                }
                next = CompactHashing.getNext(entry, mask);
                bucketLength++;
            } while (next != 0);
            if (bucketLength >= 9) {
                return convertToHashFloodingResistantImplementation().add(object);
            }
            if (newSize > mask) {
                mask = resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            } else {
                entries2[entryIndex] = CompactHashing.maskCombine(entry, newEntryIndex + 1, mask);
            }
        } else if (newSize > mask) {
            mask = resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
        } else {
            CompactHashing.tableSet(this.table, tableIndex, newEntryIndex + 1);
        }
        resizeMeMaybe(newSize);
        insertEntry(newEntryIndex, object, hash, mask);
        this.size = newSize;
        incrementModCount();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int entryIndex, @NullableDecl E object, int hash, int mask) {
        this.entries[entryIndex] = CompactHashing.maskCombine(hash, 0, mask);
        this.elements[entryIndex] = object;
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
        this.elements = Arrays.copyOf(this.elements, newCapacity);
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

    public boolean contains(@NullableDecl Object object) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.contains(object);
        }
        int hash = Hashing.smearedHash(object);
        int mask = hashTableMask();
        int next = CompactHashing.tableGet(this.table, hash & mask);
        if (next == 0) {
            return false;
        }
        int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
        do {
            int entryIndex = next - 1;
            int entry = this.entries[entryIndex];
            if (CompactHashing.getHashPrefix(entry, mask) == hashPrefix && Objects.equal(object, this.elements[entryIndex])) {
                return true;
            }
            next = CompactHashing.getNext(entry, mask);
        } while (next != 0);
        return false;
    }

    public boolean remove(@NullableDecl Object object) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.remove(object);
        }
        int mask = hashTableMask();
        int index = CompactHashing.remove(object, (Object) null, mask, this.table, this.entries, this.elements, (Object[]) null);
        if (index == -1) {
            return false;
        }
        moveLastEntry(index, mask);
        this.size--;
        incrementModCount();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int dstIndex, int mask) {
        int entryIndex;
        int entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            Object[] objArr = this.elements;
            Object object = objArr[srcIndex];
            objArr[dstIndex] = object;
            objArr[srcIndex] = null;
            int[] iArr = this.entries;
            iArr[dstIndex] = iArr[srcIndex];
            iArr[srcIndex] = 0;
            int tableIndex = Hashing.smearedHash(object) & mask;
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
        this.elements[dstIndex] = null;
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

    public Iterator<E> iterator() {
        Set<E> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.iterator();
        }
        return new Iterator<E>() {
            int currentIndex;
            int expectedMetadata;
            int indexToRemove = -1;

            {
                this.expectedMetadata = CompactHashSet.this.metadata;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            public E next() {
                checkForConcurrentModification();
                if (hasNext()) {
                    this.indexToRemove = this.currentIndex;
                    E[] eArr = CompactHashSet.this.elements;
                    int i = this.currentIndex;
                    E result = eArr[i];
                    this.currentIndex = CompactHashSet.this.getSuccessor(i);
                    return result;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                incrementExpectedModCount();
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.elements[this.indexToRemove]);
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }

            /* access modifiers changed from: package-private */
            public void incrementExpectedModCount() {
                this.expectedMetadata += 32;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.expectedMetadata) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    public int size() {
        Set<E> delegate = delegateOrNull();
        return delegate != null ? delegate.size() : this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        Set<E> delegate = delegateOrNull();
        return delegate != null ? delegate.toArray() : Arrays.copyOf(this.elements, this.size);
    }

    public <T> T[] toArray(T[] a) {
        if (needsAllocArrays()) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }
        Set<E> delegate = delegateOrNull();
        if (delegate != null) {
            return delegate.toArray(a);
        }
        return ObjectArrays.toArrayImpl(this.elements, 0, this.size, a);
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            Set<E> delegate = delegateOrNull();
            if (delegate != null) {
                Set<E> newDelegate = createHashFloodingResistantDelegate(size());
                newDelegate.addAll(delegate);
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
            Set<E> delegate = delegateOrNull();
            if (delegate != null) {
                this.metadata = Ints.constrainToRange(size(), 3, 1073741823);
                delegate.clear();
                this.table = null;
                this.size = 0;
                return;
            }
            Arrays.fill(this.elements, 0, this.size, (Object) null);
            CompactHashing.tableClear(this.table);
            Arrays.fill(this.entries, 0, this.size, 0);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            stream.writeObject(it.next());
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int elementCount = stream.readInt();
        if (elementCount >= 0) {
            init(elementCount);
            for (int i = 0; i < elementCount; i++) {
                add(stream.readObject());
            }
            return;
        }
        throw new InvalidObjectException(new StringBuilder(25).append("Invalid size: ").append(elementCount).toString());
    }
}
