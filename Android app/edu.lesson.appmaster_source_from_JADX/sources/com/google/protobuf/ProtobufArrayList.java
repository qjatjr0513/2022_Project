package com.google.protobuf;

import java.util.Arrays;
import java.util.RandomAccess;

final class ProtobufArrayList<E> extends AbstractProtobufList<E> implements RandomAccess {
    private static final ProtobufArrayList<Object> EMPTY_LIST;
    private E[] array;
    private int size;

    static {
        ProtobufArrayList<Object> protobufArrayList = new ProtobufArrayList<>(new Object[0], 0);
        EMPTY_LIST = protobufArrayList;
        protobufArrayList.makeImmutable();
    }

    public static <E> ProtobufArrayList<E> emptyList() {
        return EMPTY_LIST;
    }

    ProtobufArrayList() {
        this(new Object[10], 0);
    }

    private ProtobufArrayList(E[] array2, int size2) {
        this.array = array2;
        this.size = size2;
    }

    public ProtobufArrayList<E> mutableCopyWithCapacity(int capacity) {
        if (capacity >= this.size) {
            return new ProtobufArrayList<>(Arrays.copyOf(this.array, capacity), this.size);
        }
        throw new IllegalArgumentException();
    }

    public boolean add(E element) {
        ensureIsMutable();
        int i = this.size;
        E[] eArr = this.array;
        if (i == eArr.length) {
            this.array = Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.array;
        int i2 = this.size;
        this.size = i2 + 1;
        eArr2[i2] = element;
        this.modCount++;
        return true;
    }

    public void add(int index, E element) {
        int i;
        ensureIsMutable();
        if (index < 0 || index > (i = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
        }
        E[] eArr = this.array;
        if (i < eArr.length) {
            System.arraycopy(eArr, index, eArr, index + 1, i - index);
        } else {
            E[] newArray = createArray(((i * 3) / 2) + 1);
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
            this.array = newArray;
        }
        this.array[index] = element;
        this.size++;
        this.modCount++;
    }

    public E get(int index) {
        ensureIndexInRange(index);
        return this.array[index];
    }

    public E remove(int index) {
        ensureIsMutable();
        ensureIndexInRange(index);
        E[] eArr = this.array;
        E value = eArr[index];
        int i = this.size;
        if (index < i - 1) {
            System.arraycopy(eArr, index + 1, eArr, index, (i - index) - 1);
        }
        this.size--;
        this.modCount++;
        return value;
    }

    public E set(int index, E element) {
        ensureIsMutable();
        ensureIndexInRange(index);
        E[] eArr = this.array;
        E toReturn = eArr[index];
        eArr[index] = element;
        this.modCount++;
        return toReturn;
    }

    public int size() {
        return this.size;
    }

    private static <E> E[] createArray(int capacity) {
        return (Object[]) new Object[capacity];
    }

    private void ensureIndexInRange(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int index) {
        return "Index:" + index + ", Size:" + this.size;
    }
}
