package com.google.firebase.database.snapshot;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.collection.ImmutableSortedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class IndexedNode implements Iterable<NamedNode> {
    private static final ImmutableSortedSet<NamedNode> FALLBACK_INDEX = new ImmutableSortedSet<>(Collections.emptyList(), (Comparator) null);
    private final Index index;
    private ImmutableSortedSet<NamedNode> indexed;
    private final Node node;

    private IndexedNode(Node node2, Index index2) {
        this.index = index2;
        this.node = node2;
        this.indexed = null;
    }

    private IndexedNode(Node node2, Index index2, ImmutableSortedSet<NamedNode> indexed2) {
        this.index = index2;
        this.node = node2;
        this.indexed = indexed2;
    }

    private void ensureIndexed() {
        if (this.indexed != null) {
            return;
        }
        if (this.index.equals(KeyIndex.getInstance())) {
            this.indexed = FALLBACK_INDEX;
            return;
        }
        List<NamedNode> children = new ArrayList<>();
        boolean sawIndexedValue = false;
        for (NamedNode entry : this.node) {
            sawIndexedValue = sawIndexedValue || this.index.isDefinedOn(entry.getNode());
            children.add(new NamedNode(entry.getName(), entry.getNode()));
        }
        if (sawIndexedValue) {
            this.indexed = new ImmutableSortedSet<>(children, this.index);
        } else {
            this.indexed = FALLBACK_INDEX;
        }
    }

    public static IndexedNode from(Node node2) {
        return new IndexedNode(node2, PriorityIndex.getInstance());
    }

    public static IndexedNode from(Node node2, Index index2) {
        return new IndexedNode(node2, index2);
    }

    public boolean hasIndex(Index index2) {
        return this.index == index2;
    }

    public Node getNode() {
        return this.node;
    }

    public Iterator<NamedNode> iterator() {
        ensureIndexed();
        if (Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.node.iterator();
        }
        return this.indexed.iterator();
    }

    public Iterator<NamedNode> reverseIterator() {
        ensureIndexed();
        if (Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.node.reverseIterator();
        }
        return this.indexed.reverseIterator();
    }

    public IndexedNode updateChild(ChildKey key, Node child) {
        Node newNode = this.node.updateImmediateChild(key, child);
        ImmutableSortedSet<NamedNode> immutableSortedSet = this.indexed;
        ImmutableSortedSet<NamedNode> immutableSortedSet2 = FALLBACK_INDEX;
        if (Objects.equal(immutableSortedSet, immutableSortedSet2) && !this.index.isDefinedOn(child)) {
            return new IndexedNode(newNode, this.index, immutableSortedSet2);
        }
        ImmutableSortedSet<NamedNode> immutableSortedSet3 = this.indexed;
        if (immutableSortedSet3 == null || Objects.equal(immutableSortedSet3, immutableSortedSet2)) {
            return new IndexedNode(newNode, this.index, (ImmutableSortedSet<NamedNode>) null);
        }
        ImmutableSortedSet<NamedNode> newIndexed = this.indexed.remove(new NamedNode(key, this.node.getImmediateChild(key)));
        if (!child.isEmpty()) {
            newIndexed = newIndexed.insert(new NamedNode(key, child));
        }
        return new IndexedNode(newNode, this.index, newIndexed);
    }

    public IndexedNode updatePriority(Node priority) {
        return new IndexedNode(this.node.updatePriority(priority), this.index, this.indexed);
    }

    public NamedNode getFirstChild() {
        if (!(this.node instanceof ChildrenNode)) {
            return null;
        }
        ensureIndexed();
        if (!Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.indexed.getMinEntry();
        }
        ChildKey firstKey = ((ChildrenNode) this.node).getFirstChildKey();
        return new NamedNode(firstKey, this.node.getImmediateChild(firstKey));
    }

    public NamedNode getLastChild() {
        if (!(this.node instanceof ChildrenNode)) {
            return null;
        }
        ensureIndexed();
        if (!Objects.equal(this.indexed, FALLBACK_INDEX)) {
            return this.indexed.getMaxEntry();
        }
        ChildKey lastKey = ((ChildrenNode) this.node).getLastChildKey();
        return new NamedNode(lastKey, this.node.getImmediateChild(lastKey));
    }

    public ChildKey getPredecessorChildName(ChildKey childKey, Node childNode, Index index2) {
        if (this.index.equals(KeyIndex.getInstance()) || this.index.equals(index2)) {
            ensureIndexed();
            if (Objects.equal(this.indexed, FALLBACK_INDEX)) {
                return this.node.getPredecessorChildKey(childKey);
            }
            NamedNode node2 = this.indexed.getPredecessorEntry(new NamedNode(childKey, childNode));
            if (node2 != null) {
                return node2.getName();
            }
            return null;
        }
        throw new IllegalArgumentException("Index not available in IndexedNode!");
    }
}
