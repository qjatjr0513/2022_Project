package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.Path;

public final class PathIndex extends Index {
    private final Path indexPath;

    public PathIndex(Path indexPath2) {
        if (indexPath2.size() != 1 || !indexPath2.getFront().isPriorityChildName()) {
            this.indexPath = indexPath2;
            return;
        }
        throw new IllegalArgumentException("Can't create PathIndex with '.priority' as key. Please use PriorityIndex instead!");
    }

    public boolean isDefinedOn(Node snapshot) {
        return !snapshot.getChild(this.indexPath).isEmpty();
    }

    public int compare(NamedNode a, NamedNode b) {
        int indexCmp = a.getNode().getChild(this.indexPath).compareTo(b.getNode().getChild(this.indexPath));
        if (indexCmp == 0) {
            return a.getName().compareTo(b.getName());
        }
        return indexCmp;
    }

    public NamedNode makePost(ChildKey name, Node value) {
        return new NamedNode(name, EmptyNode.Empty().updateChild(this.indexPath, value));
    }

    public NamedNode maxPost() {
        return new NamedNode(ChildKey.getMaxName(), EmptyNode.Empty().updateChild(this.indexPath, Node.MAX_NODE));
    }

    public String getQueryDefinition() {
        return this.indexPath.wireFormat();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !this.indexPath.equals(((PathIndex) o).indexPath)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.indexPath.hashCode();
    }
}
