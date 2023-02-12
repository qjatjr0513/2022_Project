package com.google.firebase.database.core.utilities;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.StandardComparator;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ImmutableTree<T> implements Iterable<Map.Entry<Path, T>> {
    private static final ImmutableTree EMPTY;
    private static final ImmutableSortedMap EMPTY_CHILDREN;
    private final ImmutableSortedMap<ChildKey, ImmutableTree<T>> children;
    private final T value;

    public interface TreeVisitor<T, R> {
        R onNodeValue(Path path, T t, R r);
    }

    static {
        ImmutableSortedMap<K, V> emptyMap = ImmutableSortedMap.Builder.emptyMap(StandardComparator.getComparator(ChildKey.class));
        EMPTY_CHILDREN = emptyMap;
        EMPTY = new ImmutableTree((Object) null, emptyMap);
    }

    public static <V> ImmutableTree<V> emptyInstance() {
        return EMPTY;
    }

    public ImmutableTree(T value2, ImmutableSortedMap<ChildKey, ImmutableTree<T>> children2) {
        this.value = value2;
        this.children = children2;
    }

    public ImmutableTree(T value2) {
        this(value2, EMPTY_CHILDREN);
    }

    public T getValue() {
        return this.value;
    }

    public ImmutableSortedMap<ChildKey, ImmutableTree<T>> getChildren() {
        return this.children;
    }

    public boolean isEmpty() {
        return this.value == null && this.children.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r0 = r7.getFront();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.database.core.Path findRootMostMatchingPath(com.google.firebase.database.core.Path r7, com.google.firebase.database.core.utilities.Predicate<? super T> r8) {
        /*
            r6 = this;
            T r0 = r6.value
            if (r0 == 0) goto L_0x000f
            boolean r0 = r8.evaluate(r0)
            if (r0 == 0) goto L_0x000f
            com.google.firebase.database.core.Path r0 = com.google.firebase.database.core.Path.getEmptyPath()
            return r0
        L_0x000f:
            boolean r0 = r7.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            return r1
        L_0x0017:
            com.google.firebase.database.snapshot.ChildKey r0 = r7.getFront()
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.core.utilities.ImmutableTree<T>> r2 = r6.children
            java.lang.Object r2 = r2.get(r0)
            com.google.firebase.database.core.utilities.ImmutableTree r2 = (com.google.firebase.database.core.utilities.ImmutableTree) r2
            if (r2 == 0) goto L_0x0040
            com.google.firebase.database.core.Path r3 = r7.popFront()
            com.google.firebase.database.core.Path r3 = r2.findRootMostMatchingPath(r3, r8)
            if (r3 == 0) goto L_0x003f
            com.google.firebase.database.core.Path r1 = new com.google.firebase.database.core.Path
            r4 = 1
            com.google.firebase.database.snapshot.ChildKey[] r4 = new com.google.firebase.database.snapshot.ChildKey[r4]
            r5 = 0
            r4[r5] = r0
            r1.<init>((com.google.firebase.database.snapshot.ChildKey[]) r4)
            com.google.firebase.database.core.Path r1 = r1.child((com.google.firebase.database.core.Path) r3)
            return r1
        L_0x003f:
            return r1
        L_0x0040:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.utilities.ImmutableTree.findRootMostMatchingPath(com.google.firebase.database.core.Path, com.google.firebase.database.core.utilities.Predicate):com.google.firebase.database.core.Path");
    }

    public Path findRootMostPathWithValue(Path relativePath) {
        return findRootMostMatchingPath(relativePath, Predicate.TRUE);
    }

    public T rootMostValue(Path relativePath) {
        return rootMostValueMatching(relativePath, Predicate.TRUE);
    }

    public T rootMostValueMatching(Path relativePath, Predicate<? super T> predicate) {
        T t = this.value;
        if (t != null && predicate.evaluate(t)) {
            return this.value;
        }
        ImmutableTree immutableTree = this;
        Iterator<ChildKey> it = relativePath.iterator();
        while (it.hasNext() && (immutableTree = immutableTree.children.get(it.next())) != null) {
            T t2 = immutableTree.value;
            if (t2 != null && predicate.evaluate(t2)) {
                return immutableTree.value;
            }
        }
        return null;
    }

    public T leafMostValue(Path relativePath) {
        return leafMostValueMatching(relativePath, Predicate.TRUE);
    }

    public T leafMostValueMatching(Path path, Predicate<? super T> predicate) {
        T t = this.value;
        T currentValue = (t == null || !predicate.evaluate(t)) ? null : this.value;
        ImmutableTree immutableTree = this;
        Iterator<ChildKey> it = path.iterator();
        while (it.hasNext() && (immutableTree = immutableTree.children.get(it.next())) != null) {
            T t2 = immutableTree.value;
            if (t2 != null && predicate.evaluate(t2)) {
                currentValue = immutableTree.value;
            }
        }
        return currentValue;
    }

    public boolean containsMatchingValue(Predicate<? super T> predicate) {
        T t = this.value;
        if (t != null && predicate.evaluate(t)) {
            return true;
        }
        Iterator<Map.Entry<ChildKey, ImmutableTree<T>>> it = this.children.iterator();
        while (it.hasNext()) {
            if (it.next().getValue().containsMatchingValue(predicate)) {
                return true;
            }
        }
        return false;
    }

    public ImmutableTree<T> getChild(ChildKey child) {
        ImmutableTree<T> childTree = this.children.get(child);
        if (childTree != null) {
            return childTree;
        }
        return emptyInstance();
    }

    public ImmutableTree<T> subtree(Path relativePath) {
        if (relativePath.isEmpty()) {
            return this;
        }
        ImmutableTree<T> childTree = this.children.get(relativePath.getFront());
        if (childTree != null) {
            return childTree.subtree(relativePath.popFront());
        }
        return emptyInstance();
    }

    public ImmutableTree<T> set(Path relativePath, T value2) {
        if (relativePath.isEmpty()) {
            return new ImmutableTree<>(value2, this.children);
        }
        ChildKey front = relativePath.getFront();
        ImmutableTree immutableTree = this.children.get(front);
        if (immutableTree == null) {
            immutableTree = emptyInstance();
        }
        return new ImmutableTree<>(this.value, this.children.insert(front, immutableTree.set(relativePath.popFront(), value2)));
    }

    public ImmutableTree<T> remove(Path relativePath) {
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> newChildren;
        if (!relativePath.isEmpty()) {
            ChildKey front = relativePath.getFront();
            ImmutableTree immutableTree = this.children.get(front);
            if (immutableTree == null) {
                return this;
            }
            ImmutableTree remove = immutableTree.remove(relativePath.popFront());
            if (remove.isEmpty()) {
                newChildren = this.children.remove(front);
            } else {
                newChildren = this.children.insert(front, remove);
            }
            if (this.value != null || !newChildren.isEmpty()) {
                return new ImmutableTree<>(this.value, newChildren);
            }
            return emptyInstance();
        } else if (this.children.isEmpty()) {
            return emptyInstance();
        } else {
            return new ImmutableTree<>((Object) null, this.children);
        }
    }

    public T get(Path relativePath) {
        if (relativePath.isEmpty()) {
            return this.value;
        }
        ImmutableTree<T> child = this.children.get(relativePath.getFront());
        if (child != null) {
            return child.get(relativePath.popFront());
        }
        return null;
    }

    public ImmutableTree<T> setTree(Path relativePath, ImmutableTree<T> newTree) {
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> newChildren;
        if (relativePath.isEmpty()) {
            return newTree;
        }
        ChildKey front = relativePath.getFront();
        ImmutableTree<T> child = this.children.get(front);
        if (child == null) {
            child = emptyInstance();
        }
        ImmutableTree<T> newChild = child.setTree(relativePath.popFront(), newTree);
        if (newChild.isEmpty()) {
            newChildren = this.children.remove(front);
        } else {
            newChildren = this.children.insert(front, newChild);
        }
        return new ImmutableTree<>(this.value, newChildren);
    }

    public void foreach(TreeVisitor<T, Void> visitor) {
        fold(Path.getEmptyPath(), visitor, (Object) null);
    }

    public <R> R fold(R accum, TreeVisitor<? super T, R> visitor) {
        return fold(Path.getEmptyPath(), visitor, accum);
    }

    private <R> R fold(Path relativePath, TreeVisitor<? super T, R> visitor, R accum) {
        Iterator<Map.Entry<ChildKey, ImmutableTree<T>>> it = this.children.iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<T>> subtree = it.next();
            accum = subtree.getValue().fold(relativePath.child(subtree.getKey()), visitor, accum);
        }
        T t = this.value;
        if (t != null) {
            return visitor.onNodeValue(relativePath, t, accum);
        }
        return accum;
    }

    public Collection<T> values() {
        final ArrayList<T> list = new ArrayList<>();
        foreach(new TreeVisitor<T, Void>() {
            public Void onNodeValue(Path relativePath, T value, Void accum) {
                list.add(value);
                return null;
            }
        });
        return list;
    }

    public Iterator<Map.Entry<Path, T>> iterator() {
        final List<Map.Entry<Path, T>> list = new ArrayList<>();
        foreach(new TreeVisitor<T, Void>() {
            public Void onNodeValue(Path relativePath, T value, Void accum) {
                list.add(new AbstractMap.SimpleImmutableEntry(relativePath, value));
                return null;
            }
        });
        return list.iterator();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ImmutableTree { value=");
        builder.append(getValue());
        builder.append(", children={");
        Iterator<Map.Entry<ChildKey, ImmutableTree<T>>> it = this.children.iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<T>> child = it.next();
            builder.append(child.getKey().asString());
            builder.append("=");
            builder.append(child.getValue());
        }
        builder.append("} }");
        return builder.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImmutableTree that = (ImmutableTree) o;
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap = this.children;
        if (immutableSortedMap == null ? that.children != null : !immutableSortedMap.equals(that.children)) {
            return false;
        }
        T t = this.value;
        if (t == null ? that.value == null : t.equals(that.value)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        T t = this.value;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        ImmutableSortedMap<ChildKey, ImmutableTree<T>> immutableSortedMap = this.children;
        if (immutableSortedMap != null) {
            i = immutableSortedMap.hashCode();
        }
        return hashCode + i;
    }
}
