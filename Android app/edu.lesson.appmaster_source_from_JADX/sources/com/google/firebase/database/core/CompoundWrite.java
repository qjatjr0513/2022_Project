package com.google.firebase.database.core;

import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class CompoundWrite implements Iterable<Map.Entry<Path, Node>> {
    private static final CompoundWrite EMPTY = new CompoundWrite(new ImmutableTree(null));
    private final ImmutableTree<Node> writeTree;

    private CompoundWrite(ImmutableTree<Node> writeTree2) {
        this.writeTree = writeTree2;
    }

    public static CompoundWrite emptyWrite() {
        return EMPTY;
    }

    public static CompoundWrite fromValue(Map<String, Object> merge) {
        ImmutableTree<Node> writeTree2 = ImmutableTree.emptyInstance();
        for (Map.Entry<String, Object> entry : merge.entrySet()) {
            writeTree2 = writeTree2.setTree(new Path(entry.getKey()), new ImmutableTree<>(NodeUtilities.NodeFromJSON(entry.getValue())));
        }
        return new CompoundWrite(writeTree2);
    }

    public static CompoundWrite fromChildMerge(Map<ChildKey, Node> merge) {
        ImmutableTree<Node> writeTree2 = ImmutableTree.emptyInstance();
        for (Map.Entry<ChildKey, Node> entry : merge.entrySet()) {
            writeTree2 = writeTree2.setTree(new Path(entry.getKey()), new ImmutableTree<>(entry.getValue()));
        }
        return new CompoundWrite(writeTree2);
    }

    public static CompoundWrite fromPathMerge(Map<Path, Node> merge) {
        ImmutableTree<Node> writeTree2 = ImmutableTree.emptyInstance();
        for (Map.Entry<Path, Node> entry : merge.entrySet()) {
            writeTree2 = writeTree2.setTree(entry.getKey(), new ImmutableTree<>(entry.getValue()));
        }
        return new CompoundWrite(writeTree2);
    }

    public CompoundWrite addWrite(Path path, Node node) {
        if (path.isEmpty()) {
            return new CompoundWrite(new ImmutableTree(node));
        }
        Path rootMostPath = this.writeTree.findRootMostPathWithValue(path);
        if (rootMostPath != null) {
            Path relativePath = Path.getRelative(rootMostPath, path);
            Node value = this.writeTree.get(rootMostPath);
            ChildKey back = relativePath.getBack();
            if (back != null && back.isPriorityChildName() && value.getChild(relativePath.getParent()).isEmpty()) {
                return this;
            }
            return new CompoundWrite(this.writeTree.set(rootMostPath, value.updateChild(relativePath, node)));
        }
        return new CompoundWrite(this.writeTree.setTree(path, new ImmutableTree<>(node)));
    }

    public CompoundWrite addWrite(ChildKey key, Node node) {
        return addWrite(new Path(key), node);
    }

    public CompoundWrite addWrites(final Path path, CompoundWrite updates) {
        return (CompoundWrite) updates.writeTree.fold(this, new ImmutableTree.TreeVisitor<Node, CompoundWrite>() {
            public CompoundWrite onNodeValue(Path relativePath, Node value, CompoundWrite accum) {
                return accum.addWrite(path.child(relativePath), value);
            }
        });
    }

    public CompoundWrite removeWrite(Path path) {
        if (path.isEmpty()) {
            return EMPTY;
        }
        return new CompoundWrite(this.writeTree.setTree(path, ImmutableTree.emptyInstance()));
    }

    public boolean hasCompleteWrite(Path path) {
        return getCompleteNode(path) != null;
    }

    public Node rootWrite() {
        return this.writeTree.getValue();
    }

    public Node getCompleteNode(Path path) {
        Path rootMost = this.writeTree.findRootMostPathWithValue(path);
        if (rootMost != null) {
            return this.writeTree.get(rootMost).getChild(Path.getRelative(rootMost, path));
        }
        return null;
    }

    public List<NamedNode> getCompleteChildren() {
        List<NamedNode> children = new ArrayList<>();
        if (this.writeTree.getValue() != null) {
            for (NamedNode entry : this.writeTree.getValue()) {
                children.add(new NamedNode(entry.getName(), entry.getNode()));
            }
        } else {
            Iterator<Map.Entry<ChildKey, ImmutableTree<Node>>> it = this.writeTree.getChildren().iterator();
            while (it.hasNext()) {
                Map.Entry<ChildKey, ImmutableTree<Node>> entry2 = it.next();
                ImmutableTree<Node> childTree = entry2.getValue();
                if (childTree.getValue() != null) {
                    children.add(new NamedNode(entry2.getKey(), childTree.getValue()));
                }
            }
        }
        return children;
    }

    public CompoundWrite childCompoundWrite(Path path) {
        if (path.isEmpty()) {
            return this;
        }
        Node shadowingNode = getCompleteNode(path);
        if (shadowingNode != null) {
            return new CompoundWrite(new ImmutableTree(shadowingNode));
        }
        return new CompoundWrite(this.writeTree.subtree(path));
    }

    public Map<ChildKey, CompoundWrite> childCompoundWrites() {
        Map<ChildKey, CompoundWrite> children = new HashMap<>();
        Iterator<Map.Entry<ChildKey, ImmutableTree<Node>>> it = this.writeTree.getChildren().iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Node>> entries = it.next();
            children.put(entries.getKey(), new CompoundWrite(entries.getValue()));
        }
        return children;
    }

    public boolean isEmpty() {
        return this.writeTree.isEmpty();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.firebase.database.snapshot.Node} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.firebase.database.snapshot.Node applySubtreeWrite(com.google.firebase.database.core.Path r8, com.google.firebase.database.core.utilities.ImmutableTree<com.google.firebase.database.snapshot.Node> r9, com.google.firebase.database.snapshot.Node r10) {
        /*
            r7 = this;
            java.lang.Object r0 = r9.getValue()
            if (r0 == 0) goto L_0x0011
            java.lang.Object r0 = r9.getValue()
            com.google.firebase.database.snapshot.Node r0 = (com.google.firebase.database.snapshot.Node) r0
            com.google.firebase.database.snapshot.Node r0 = r10.updateChild(r8, r0)
            return r0
        L_0x0011:
            r0 = 0
            com.google.firebase.database.collection.ImmutableSortedMap r1 = r9.getChildren()
            java.util.Iterator r1 = r1.iterator()
        L_0x001a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            com.google.firebase.database.core.utilities.ImmutableTree r3 = (com.google.firebase.database.core.utilities.ImmutableTree) r3
            java.lang.Object r4 = r2.getKey()
            com.google.firebase.database.snapshot.ChildKey r4 = (com.google.firebase.database.snapshot.ChildKey) r4
            boolean r5 = r4.isPriorityChildName()
            if (r5 == 0) goto L_0x004e
            java.lang.Object r5 = r3.getValue()
            if (r5 == 0) goto L_0x0040
            r5 = 1
            goto L_0x0041
        L_0x0040:
            r5 = 0
        L_0x0041:
            java.lang.String r6 = "Priority writes must always be leaf nodes"
            com.google.firebase.database.core.utilities.Utilities.hardAssert(r5, r6)
            java.lang.Object r5 = r3.getValue()
            r0 = r5
            com.google.firebase.database.snapshot.Node r0 = (com.google.firebase.database.snapshot.Node) r0
            goto L_0x0056
        L_0x004e:
            com.google.firebase.database.core.Path r5 = r8.child((com.google.firebase.database.snapshot.ChildKey) r4)
            com.google.firebase.database.snapshot.Node r10 = r7.applySubtreeWrite(r5, r3, r10)
        L_0x0056:
            goto L_0x001a
        L_0x0057:
            com.google.firebase.database.snapshot.Node r1 = r10.getChild(r8)
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x006f
            if (r0 == 0) goto L_0x006f
            com.google.firebase.database.snapshot.ChildKey r1 = com.google.firebase.database.snapshot.ChildKey.getPriorityKey()
            com.google.firebase.database.core.Path r1 = r8.child((com.google.firebase.database.snapshot.ChildKey) r1)
            com.google.firebase.database.snapshot.Node r10 = r10.updateChild(r1, r0)
        L_0x006f:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.CompoundWrite.applySubtreeWrite(com.google.firebase.database.core.Path, com.google.firebase.database.core.utilities.ImmutableTree, com.google.firebase.database.snapshot.Node):com.google.firebase.database.snapshot.Node");
    }

    public Node apply(Node node) {
        return applySubtreeWrite(Path.getEmptyPath(), this.writeTree, node);
    }

    public Map<String, Object> getValue(final boolean exportFormat) {
        final Map<String, Object> writes = new HashMap<>();
        this.writeTree.foreach(new ImmutableTree.TreeVisitor<Node, Void>() {
            public Void onNodeValue(Path relativePath, Node value, Void accum) {
                writes.put(relativePath.wireFormat(), value.getValue(exportFormat));
                return null;
            }
        });
        return writes;
    }

    public Iterator<Map.Entry<Path, Node>> iterator() {
        return this.writeTree.iterator();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        return ((CompoundWrite) o).getValue(true).equals(getValue(true));
    }

    public int hashCode() {
        return getValue(true).hashCode();
    }

    public String toString() {
        return "CompoundWrite{" + getValue(true).toString() + "}";
    }
}
