package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Utilities;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RangeMerge {
    private final Path optExclusiveStart;
    private final Path optInclusiveEnd;
    private final Node snap;

    public RangeMerge(Path optExclusiveStart2, Path optInclusiveEnd2, Node snap2) {
        this.optExclusiveStart = optExclusiveStart2;
        this.optInclusiveEnd = optInclusiveEnd2;
        this.snap = snap2;
    }

    public RangeMerge(com.google.firebase.database.connection.RangeMerge rangeMerge) {
        List<String> optStartPathList = rangeMerge.getOptExclusiveStart();
        Path path = null;
        this.optExclusiveStart = optStartPathList != null ? new Path(optStartPathList) : null;
        List<String> optEndPathList = rangeMerge.getOptInclusiveEnd();
        this.optInclusiveEnd = optEndPathList != null ? new Path(optEndPathList) : path;
        this.snap = NodeUtilities.NodeFromJSON(rangeMerge.getSnap());
    }

    public Node applyTo(Node node) {
        return updateRangeInNode(Path.getEmptyPath(), node, this.snap);
    }

    /* access modifiers changed from: package-private */
    public Path getStart() {
        return this.optExclusiveStart;
    }

    /* access modifiers changed from: package-private */
    public Path getEnd() {
        return this.optInclusiveEnd;
    }

    private Node updateRangeInNode(Path currentPath, Node node, Node updateNode) {
        Path path = currentPath;
        Node node2 = node;
        Node node3 = updateNode;
        Path path2 = this.optExclusiveStart;
        boolean z = true;
        int startComparison = path2 == null ? 1 : path.compareTo(path2);
        Path path3 = this.optInclusiveEnd;
        int endComparison = path3 == null ? -1 : path.compareTo(path3);
        Path path4 = this.optExclusiveStart;
        boolean startInNode = path4 != null && path.contains(path4);
        Path path5 = this.optInclusiveEnd;
        boolean endInNode = path5 != null && path.contains(path5);
        if (startComparison > 0 && endComparison < 0 && !endInNode) {
            return node3;
        }
        if (startComparison > 0 && endInNode && updateNode.isLeafNode()) {
            return node3;
        }
        if (startComparison > 0 && endComparison == 0) {
            Utilities.hardAssert(endInNode);
            Utilities.hardAssert(true ^ updateNode.isLeafNode());
            if (node.isLeafNode()) {
                return EmptyNode.Empty();
            }
            return node2;
        } else if (startInNode || endInNode) {
            Set<ChildKey> allChildren = new HashSet<>();
            Iterator it = node.iterator();
            while (it.hasNext()) {
                allChildren.add(((NamedNode) it.next()).getName());
            }
            Iterator it2 = updateNode.iterator();
            while (it2.hasNext()) {
                allChildren.add(((NamedNode) it2.next()).getName());
            }
            List<ChildKey> inOrder = new ArrayList<>(allChildren.size() + 1);
            inOrder.addAll(allChildren);
            if (!updateNode.getPriority().isEmpty() || !node.getPriority().isEmpty()) {
                inOrder.add(ChildKey.getPriorityKey());
            }
            Node newNode = node;
            for (ChildKey key : inOrder) {
                Node currentChild = node2.getImmediateChild(key);
                Node updatedChild = updateRangeInNode(path.child(key), node2.getImmediateChild(key), node3.getImmediateChild(key));
                if (updatedChild != currentChild) {
                    newNode = newNode.updateImmediateChild(key, updatedChild);
                }
                path = currentPath;
            }
            return newNode;
        } else {
            if (endComparison <= 0 && startComparison > 0) {
                z = false;
            }
            Utilities.hardAssert(z);
            return node2;
        }
    }

    public String toString() {
        return "RangeMerge{optExclusiveStart=" + this.optExclusiveStart + ", optInclusiveEnd=" + this.optInclusiveEnd + ", snap=" + this.snap + '}';
    }
}
