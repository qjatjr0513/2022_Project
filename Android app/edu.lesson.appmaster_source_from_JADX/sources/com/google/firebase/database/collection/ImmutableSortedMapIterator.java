package com.google.firebase.database.collection;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class ImmutableSortedMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
    private final boolean isReverse;
    private final ArrayDeque<LLRBValueNode<K, V>> nodeStack = new ArrayDeque<>();

    ImmutableSortedMapIterator(LLRBNode<K, V> root, K startKey, Comparator<K> comparator, boolean isReverse2) {
        int cmp;
        this.isReverse = isReverse2;
        LLRBNode<K, V> node = root;
        while (!node.isEmpty()) {
            if (startKey == null) {
                cmp = 1;
            } else if (isReverse2) {
                cmp = comparator.compare(startKey, node.getKey());
            } else {
                cmp = comparator.compare(node.getKey(), startKey);
            }
            if (cmp < 0) {
                if (isReverse2) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
            } else if (cmp == 0) {
                this.nodeStack.push((LLRBValueNode) node);
                return;
            } else {
                this.nodeStack.push((LLRBValueNode) node);
                if (isReverse2) {
                    node = node.getRight();
                } else {
                    node = node.getLeft();
                }
            }
        }
    }

    public boolean hasNext() {
        return this.nodeStack.size() > 0;
    }

    public Map.Entry<K, V> next() {
        try {
            LLRBValueNode<K, V> node = this.nodeStack.pop();
            Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<>(node.getKey(), node.getValue());
            if (this.isReverse) {
                for (LLRBNode<K, V> next = node.getLeft(); !next.isEmpty(); next = next.getRight()) {
                    this.nodeStack.push((LLRBValueNode) next);
                }
            } else {
                for (LLRBNode<K, V> next2 = node.getRight(); !next2.isEmpty(); next2 = next2.getLeft()) {
                    this.nodeStack.push((LLRBValueNode) next2);
                }
            }
            return entry;
        } catch (EmptyStackException e) {
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("remove called on immutable collection");
    }
}
