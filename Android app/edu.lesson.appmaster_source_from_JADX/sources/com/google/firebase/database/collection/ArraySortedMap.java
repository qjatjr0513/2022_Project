package com.google.firebase.database.collection;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.LLRBNode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArraySortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final Comparator<K> comparator;
    /* access modifiers changed from: private */
    public final K[] keys;
    /* access modifiers changed from: private */
    public final V[] values;

    public static <A, B, C> ArraySortedMap<A, C> buildFrom(List<A> keys2, Map<B, C> values2, ImmutableSortedMap.Builder.KeyTranslator<A, B> translator, Comparator<A> comparator2) {
        Collections.sort(keys2, comparator2);
        int size = keys2.size();
        A[] keyArray = new Object[size];
        C[] valueArray = new Object[size];
        int pos = 0;
        for (A k : keys2) {
            keyArray[pos] = k;
            valueArray[pos] = values2.get(translator.translate(k));
            pos++;
        }
        return new ArraySortedMap<>(comparator2, keyArray, valueArray);
    }

    public static <K, V> ArraySortedMap<K, V> fromMap(Map<K, V> map, Comparator<K> comparator2) {
        return buildFrom(new ArrayList(map.keySet()), map, ImmutableSortedMap.Builder.identityTranslator(), comparator2);
    }

    public ArraySortedMap(Comparator<K> comparator2) {
        this.keys = new Object[0];
        this.values = new Object[0];
        this.comparator = comparator2;
    }

    private ArraySortedMap(Comparator<K> comparator2, K[] keys2, V[] values2) {
        this.keys = keys2;
        this.values = values2;
        this.comparator = comparator2;
    }

    public boolean containsKey(K key) {
        return findKey(key) != -1;
    }

    public V get(K key) {
        int pos = findKey(key);
        if (pos != -1) {
            return this.values[pos];
        }
        return null;
    }

    public ImmutableSortedMap<K, V> remove(K key) {
        int pos = findKey(key);
        if (pos == -1) {
            return this;
        }
        return new ArraySortedMap(this.comparator, removeFromArray(this.keys, pos), removeFromArray(this.values, pos));
    }

    public ImmutableSortedMap<K, V> insert(K key, V value) {
        int pos = findKey(key);
        if (pos != -1) {
            K[] kArr = this.keys;
            if (kArr[pos] == key && this.values[pos] == value) {
                return this;
            }
            return new ArraySortedMap(this.comparator, replaceInArray(kArr, pos, key), replaceInArray(this.values, pos, value));
        } else if (this.keys.length > 25) {
            Map<K, V> map = new HashMap<>(this.keys.length + 1);
            int i = 0;
            while (true) {
                K[] kArr2 = this.keys;
                if (i < kArr2.length) {
                    map.put(kArr2[i], this.values[i]);
                    i++;
                } else {
                    map.put(key, value);
                    return RBTreeSortedMap.fromMap(map, this.comparator);
                }
            }
        } else {
            int newPos = findKeyOrInsertPosition(key);
            return new ArraySortedMap(this.comparator, addToArray(this.keys, newPos, key), addToArray(this.values, newPos, value));
        }
    }

    public K getMinKey() {
        K[] kArr = this.keys;
        if (kArr.length > 0) {
            return kArr[0];
        }
        return null;
    }

    public K getMaxKey() {
        K[] kArr = this.keys;
        if (kArr.length > 0) {
            return kArr[kArr.length - 1];
        }
        return null;
    }

    public int size() {
        return this.keys.length;
    }

    public boolean isEmpty() {
        return this.keys.length == 0;
    }

    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> visitor) {
        int i = 0;
        while (true) {
            K[] kArr = this.keys;
            if (i < kArr.length) {
                visitor.visitEntry(kArr[i], this.values[i]);
                i++;
            } else {
                return;
            }
        }
    }

    private Iterator<Map.Entry<K, V>> iterator(int pos, boolean reverse) {
        return new Iterator<Map.Entry<K, V>>(pos, reverse) {
            int currentPos;
            final /* synthetic */ int val$pos;
            final /* synthetic */ boolean val$reverse;

            {
                this.val$pos = r2;
                this.val$reverse = r3;
                this.currentPos = r2;
            }

            public boolean hasNext() {
                if (this.val$reverse) {
                    if (this.currentPos >= 0) {
                        return true;
                    }
                } else if (this.currentPos < ArraySortedMap.this.keys.length) {
                    return true;
                }
                return false;
            }

            public Map.Entry<K, V> next() {
                K key = ArraySortedMap.this.keys[this.currentPos];
                V[] access$100 = ArraySortedMap.this.values;
                int i = this.currentPos;
                V value = access$100[i];
                this.currentPos = this.val$reverse ? i - 1 : i + 1;
                return new AbstractMap.SimpleImmutableEntry(key, value);
            }

            public void remove() {
                throw new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
            }
        };
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return iterator(0, false);
    }

    public Iterator<Map.Entry<K, V>> iteratorFrom(K key) {
        return iterator(findKeyOrInsertPosition(key), false);
    }

    public Iterator<Map.Entry<K, V>> reverseIteratorFrom(K key) {
        int pos = findKeyOrInsertPosition(key);
        K[] kArr = this.keys;
        if (pos >= kArr.length || this.comparator.compare(kArr[pos], key) != 0) {
            return iterator(pos - 1, true);
        }
        return iterator(pos, true);
    }

    public Iterator<Map.Entry<K, V>> reverseIterator() {
        return iterator(this.keys.length - 1, true);
    }

    public K getPredecessorKey(K key) {
        int pos = findKey(key);
        if (pos == -1) {
            throw new IllegalArgumentException("Can't find predecessor of nonexistent key");
        } else if (pos > 0) {
            return this.keys[pos - 1];
        } else {
            return null;
        }
    }

    public K getSuccessorKey(K key) {
        int pos = findKey(key);
        if (pos != -1) {
            K[] kArr = this.keys;
            if (pos < kArr.length - 1) {
                return kArr[pos + 1];
            }
            return null;
        }
        throw new IllegalArgumentException("Can't find successor of nonexistent key");
    }

    public int indexOf(K key) {
        return findKey(key);
    }

    public Comparator<K> getComparator() {
        return this.comparator;
    }

    private static <T> T[] removeFromArray(T[] arr, int pos) {
        int newSize = arr.length - 1;
        T[] newArray = new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, pos);
        System.arraycopy(arr, pos + 1, newArray, pos, newSize - pos);
        return newArray;
    }

    private static <T> T[] addToArray(T[] arr, int pos, T value) {
        int newSize = arr.length + 1;
        T[] newArray = new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, pos);
        newArray[pos] = value;
        System.arraycopy(arr, pos, newArray, pos + 1, (newSize - pos) - 1);
        return newArray;
    }

    private static <T> T[] replaceInArray(T[] arr, int pos, T value) {
        int size = arr.length;
        T[] newArray = new Object[size];
        System.arraycopy(arr, 0, newArray, 0, size);
        newArray[pos] = value;
        return newArray;
    }

    private int findKeyOrInsertPosition(K key) {
        int newPos = 0;
        while (true) {
            K[] kArr = this.keys;
            if (newPos >= kArr.length || this.comparator.compare(kArr[newPos], key) >= 0) {
                return newPos;
            }
            newPos++;
        }
        return newPos;
    }

    private int findKey(K key) {
        int i = 0;
        for (K otherKey : this.keys) {
            if (this.comparator.compare(key, otherKey) == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
