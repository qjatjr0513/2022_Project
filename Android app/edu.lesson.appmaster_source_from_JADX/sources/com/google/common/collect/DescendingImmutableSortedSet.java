package com.google.common.collect;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class DescendingImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    private final ImmutableSortedSet<E> forward;

    DescendingImmutableSortedSet(ImmutableSortedSet<E> forward2) {
        super(Ordering.from(forward2.comparator()).reverse());
        this.forward = forward2;
    }

    public boolean contains(@NullableDecl Object object) {
        return this.forward.contains(object);
    }

    public int size() {
        return this.forward.size();
    }

    public UnmodifiableIterator<E> iterator() {
        return this.forward.descendingIterator();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> headSetImpl(E toElement, boolean inclusive) {
        return this.forward.tailSet(toElement, inclusive).descendingSet();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> subSetImpl(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return this.forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> tailSetImpl(E fromElement, boolean inclusive) {
        return this.forward.headSet(fromElement, inclusive).descendingSet();
    }

    public ImmutableSortedSet<E> descendingSet() {
        return this.forward;
    }

    public UnmodifiableIterator<E> descendingIterator() {
        return this.forward.iterator();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> createDescendingSet() {
        throw new AssertionError("should never be called");
    }

    public E lower(E element) {
        return this.forward.higher(element);
    }

    public E floor(E element) {
        return this.forward.ceiling(element);
    }

    public E ceiling(E element) {
        return this.forward.floor(element);
    }

    public E higher(E element) {
        return this.forward.lower(element);
    }

    /* access modifiers changed from: package-private */
    public int indexOf(@NullableDecl Object target) {
        int index = this.forward.indexOf(target);
        if (index == -1) {
            return index;
        }
        return (size() - 1) - index;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return this.forward.isPartialView();
    }
}
