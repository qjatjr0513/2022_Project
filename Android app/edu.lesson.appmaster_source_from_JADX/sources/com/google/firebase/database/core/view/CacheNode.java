package com.google.firebase.database.core.view;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;

public class CacheNode {
    private final boolean filtered;
    private final boolean fullyInitialized;
    private final IndexedNode indexedNode;

    public CacheNode(IndexedNode node, boolean fullyInitialized2, boolean filtered2) {
        this.indexedNode = node;
        this.fullyInitialized = fullyInitialized2;
        this.filtered = filtered2;
    }

    public boolean isFullyInitialized() {
        return this.fullyInitialized;
    }

    public boolean isFiltered() {
        return this.filtered;
    }

    public boolean isCompleteForPath(Path path) {
        if (path.isEmpty()) {
            return isFullyInitialized() && !this.filtered;
        }
        return isCompleteForChild(path.getFront());
    }

    public boolean isCompleteForChild(ChildKey key) {
        return (isFullyInitialized() && !this.filtered) || this.indexedNode.getNode().hasChild(key);
    }

    public Node getNode() {
        return this.indexedNode.getNode();
    }

    public IndexedNode getIndexedNode() {
        return this.indexedNode;
    }
}
