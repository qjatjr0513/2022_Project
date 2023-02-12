package com.google.firebase.database.snapshot;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChildrenNode implements Node {
    public static Comparator<ChildKey> NAME_ONLY_COMPARATOR = new Comparator<ChildKey>() {
        public int compare(ChildKey o1, ChildKey o2) {
            return o1.compareTo(o2);
        }
    };
    private final ImmutableSortedMap<ChildKey, Node> children;
    private String lazyHash;
    private final Node priority;

    private static class NamedNodeIterator implements Iterator<NamedNode> {
        private final Iterator<Map.Entry<ChildKey, Node>> iterator;

        public NamedNodeIterator(Iterator<Map.Entry<ChildKey, Node>> iterator2) {
            this.iterator = iterator2;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public NamedNode next() {
            Map.Entry<ChildKey, Node> entry = this.iterator.next();
            return new NamedNode(entry.getKey(), entry.getValue());
        }

        public void remove() {
            this.iterator.remove();
        }
    }

    public static abstract class ChildVisitor extends LLRBNode.NodeVisitor<ChildKey, Node> {
        public abstract void visitChild(ChildKey childKey, Node node);

        public void visitEntry(ChildKey key, Node value) {
            visitChild(key, value);
        }
    }

    protected ChildrenNode() {
        this.lazyHash = null;
        this.children = ImmutableSortedMap.Builder.emptyMap(NAME_ONLY_COMPARATOR);
        this.priority = PriorityUtilities.NullPriority();
    }

    protected ChildrenNode(ImmutableSortedMap<ChildKey, Node> children2, Node priority2) {
        this.lazyHash = null;
        if (!children2.isEmpty() || priority2.isEmpty()) {
            this.priority = priority2;
            this.children = children2;
            return;
        }
        throw new IllegalArgumentException("Can't create empty ChildrenNode with priority!");
    }

    public boolean hasChild(ChildKey name) {
        return !getImmediateChild(name).isEmpty();
    }

    public boolean isEmpty() {
        return this.children.isEmpty();
    }

    public int getChildCount() {
        return this.children.size();
    }

    public Object getValue() {
        return getValue(false);
    }

    public Object getValue(boolean useExportFormat) {
        if (isEmpty()) {
            return null;
        }
        int numKeys = 0;
        int maxKey = 0;
        boolean allIntegerKeys = true;
        Map<String, Object> result = new HashMap<>();
        Iterator<Map.Entry<ChildKey, Node>> it = this.children.iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, Node> entry = it.next();
            String key = entry.getKey().asString();
            result.put(key, entry.getValue().getValue(useExportFormat));
            numKeys++;
            if (allIntegerKeys) {
                if (key.length() <= 1 || key.charAt(0) != '0') {
                    Integer keyAsInt = Utilities.tryParseInt(key);
                    if (keyAsInt == null || keyAsInt.intValue() < 0) {
                        allIntegerKeys = false;
                    } else if (keyAsInt.intValue() > maxKey) {
                        maxKey = keyAsInt.intValue();
                    }
                } else {
                    allIntegerKeys = false;
                }
            }
        }
        if (useExportFormat || !allIntegerKeys || maxKey >= numKeys * 2) {
            if (useExportFormat && !this.priority.isEmpty()) {
                result.put(".priority", this.priority.getValue());
            }
            return result;
        }
        List<Object> arrayResult = new ArrayList<>(maxKey + 1);
        for (int i = 0; i <= maxKey; i++) {
            arrayResult.add(result.get("" + i));
        }
        return arrayResult;
    }

    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        return this.children.getPredecessorKey(childKey);
    }

    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        return this.children.getSuccessorKey(childKey);
    }

    public String getHashRepresentation(Node.HashVersion version) {
        if (version == Node.HashVersion.V1) {
            StringBuilder toHash = new StringBuilder();
            if (!this.priority.isEmpty()) {
                toHash.append("priority:");
                toHash.append(this.priority.getHashRepresentation(Node.HashVersion.V1));
                toHash.append(":");
            }
            List<NamedNode> nodes = new ArrayList<>();
            boolean sawPriority = false;
            Iterator<NamedNode> it = iterator();
            while (it.hasNext()) {
                NamedNode node = it.next();
                nodes.add(node);
                sawPriority = sawPriority || !node.getNode().getPriority().isEmpty();
            }
            if (sawPriority) {
                Collections.sort(nodes, PriorityIndex.getInstance());
            }
            for (NamedNode node2 : nodes) {
                String hashString = node2.getNode().getHash();
                if (!hashString.equals("")) {
                    toHash.append(":");
                    toHash.append(node2.getName().asString());
                    toHash.append(":");
                    toHash.append(hashString);
                }
            }
            return toHash.toString();
        }
        throw new IllegalArgumentException("Hashes on children nodes only supported for V1");
    }

    public String getHash() {
        if (this.lazyHash == null) {
            String hashString = getHashRepresentation(Node.HashVersion.V1);
            this.lazyHash = hashString.isEmpty() ? "" : Utilities.sha1HexDigest(hashString);
        }
        return this.lazyHash;
    }

    public boolean isLeafNode() {
        return false;
    }

    public Node getPriority() {
        return this.priority;
    }

    public Node updatePriority(Node priority2) {
        if (this.children.isEmpty()) {
            return EmptyNode.Empty();
        }
        return new ChildrenNode(this.children, priority2);
    }

    public Node getImmediateChild(ChildKey name) {
        if (name.isPriorityChildName() && !this.priority.isEmpty()) {
            return this.priority;
        }
        if (this.children.containsKey(name)) {
            return this.children.get(name);
        }
        return EmptyNode.Empty();
    }

    public Node getChild(Path path) {
        ChildKey front = path.getFront();
        if (front == null) {
            return this;
        }
        return getImmediateChild(front).getChild(path.popFront());
    }

    public void forEachChild(ChildVisitor visitor) {
        forEachChild(visitor, false);
    }

    public void forEachChild(final ChildVisitor visitor, boolean includePriority) {
        if (!includePriority || getPriority().isEmpty()) {
            this.children.inOrderTraversal(visitor);
        } else {
            this.children.inOrderTraversal(new LLRBNode.NodeVisitor<ChildKey, Node>() {
                boolean passedPriorityKey = false;

                public void visitEntry(ChildKey key, Node value) {
                    if (!this.passedPriorityKey && key.compareTo(ChildKey.getPriorityKey()) > 0) {
                        this.passedPriorityKey = true;
                        visitor.visitChild(ChildKey.getPriorityKey(), ChildrenNode.this.getPriority());
                    }
                    visitor.visitChild(key, value);
                }
            });
        }
    }

    public ChildKey getFirstChildKey() {
        return this.children.getMinKey();
    }

    public ChildKey getLastChildKey() {
        return this.children.getMaxKey();
    }

    public Node updateChild(Path path, Node newChildNode) {
        ChildKey front = path.getFront();
        if (front == null) {
            return newChildNode;
        }
        if (!front.isPriorityChildName()) {
            return updateImmediateChild(front, getImmediateChild(front).updateChild(path.popFront(), newChildNode));
        }
        Utilities.hardAssert(PriorityUtilities.isValidPriority(newChildNode));
        return updatePriority(newChildNode);
    }

    public Iterator<NamedNode> iterator() {
        return new NamedNodeIterator(this.children.iterator());
    }

    public Iterator<NamedNode> reverseIterator() {
        return new NamedNodeIterator(this.children.reverseIterator());
    }

    public Node updateImmediateChild(ChildKey key, Node newChildNode) {
        if (key.isPriorityChildName()) {
            return updatePriority(newChildNode);
        }
        ImmutableSortedMap<ChildKey, Node> newChildren = this.children;
        if (newChildren.containsKey(key)) {
            newChildren = newChildren.remove(key);
        }
        if (!newChildNode.isEmpty()) {
            newChildren = newChildren.insert(key, newChildNode);
        }
        if (newChildren.isEmpty()) {
            return EmptyNode.Empty();
        }
        return new ChildrenNode(newChildren, this.priority);
    }

    public int compareTo(Node o) {
        if (isEmpty()) {
            return o.isEmpty() ? 0 : -1;
        }
        if (!o.isLeafNode() && !o.isEmpty()) {
            return o == Node.MAX_NODE ? -1 : 0;
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            if (r10 != r9) goto L_0x0008
            return r1
        L_0x0008:
            boolean r2 = r10 instanceof com.google.firebase.database.snapshot.ChildrenNode
            if (r2 != 0) goto L_0x000d
            return r0
        L_0x000d:
            r2 = r10
            com.google.firebase.database.snapshot.ChildrenNode r2 = (com.google.firebase.database.snapshot.ChildrenNode) r2
            com.google.firebase.database.snapshot.Node r3 = r9.getPriority()
            com.google.firebase.database.snapshot.Node r4 = r2.getPriority()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x001f
            return r0
        L_0x001f:
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r3 = r9.children
            int r3 = r3.size()
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r4 = r2.children
            int r4 = r4.size()
            if (r3 == r4) goto L_0x002e
            return r0
        L_0x002e:
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r3 = r9.children
            java.util.Iterator r3 = r3.iterator()
            com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.database.snapshot.ChildKey, com.google.firebase.database.snapshot.Node> r4 = r2.children
            java.util.Iterator r4 = r4.iterator()
        L_0x003a:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0075
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0075
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r6 = r4.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r5.getKey()
            com.google.firebase.database.snapshot.ChildKey r7 = (com.google.firebase.database.snapshot.ChildKey) r7
            java.lang.Object r8 = r6.getKey()
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0074
            java.lang.Object r7 = r5.getValue()
            com.google.firebase.database.snapshot.Node r7 = (com.google.firebase.database.snapshot.Node) r7
            java.lang.Object r8 = r6.getValue()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x0073
            goto L_0x0074
        L_0x0073:
            goto L_0x003a
        L_0x0074:
            return r0
        L_0x0075:
            boolean r0 = r3.hasNext()
            if (r0 != 0) goto L_0x0082
            boolean r0 = r4.hasNext()
            if (r0 != 0) goto L_0x0082
            return r1
        L_0x0082:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Something went wrong internally."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.snapshot.ChildrenNode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = 0;
        Iterator<NamedNode> it = iterator();
        while (it.hasNext()) {
            NamedNode entry = it.next();
            hashCode = (((hashCode * 31) + entry.getName().hashCode()) * 17) + entry.getNode().hashCode();
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(builder, 0);
        return builder.toString();
    }

    private static void addIndentation(StringBuilder builder, int indentation) {
        for (int i = 0; i < indentation; i++) {
            builder.append(" ");
        }
    }

    private void toString(StringBuilder builder, int indentation) {
        if (!this.children.isEmpty() || !this.priority.isEmpty()) {
            builder.append("{\n");
            Iterator<Map.Entry<ChildKey, Node>> it = this.children.iterator();
            while (it.hasNext()) {
                Map.Entry<ChildKey, Node> childEntry = it.next();
                addIndentation(builder, indentation + 2);
                builder.append(childEntry.getKey().asString());
                builder.append("=");
                if (childEntry.getValue() instanceof ChildrenNode) {
                    ((ChildrenNode) childEntry.getValue()).toString(builder, indentation + 2);
                } else {
                    builder.append(childEntry.getValue().toString());
                }
                builder.append("\n");
            }
            if (!this.priority.isEmpty()) {
                addIndentation(builder, indentation + 2);
                builder.append(".priority=");
                builder.append(this.priority.toString());
                builder.append("\n");
            }
            addIndentation(builder, indentation);
            builder.append("}");
            return;
        }
        builder.append("{ }");
    }
}
