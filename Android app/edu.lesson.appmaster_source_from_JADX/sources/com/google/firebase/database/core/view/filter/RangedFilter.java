package com.google.firebase.database.core.view.filter;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.PriorityUtilities;
import java.util.Iterator;

public class RangedFilter implements NodeFilter {
    private final NamedNode endPost;
    private final Index index;
    private final IndexedFilter indexedFilter;
    private final NamedNode startPost;

    public RangedFilter(QueryParams params) {
        this.indexedFilter = new IndexedFilter(params.getIndex());
        this.index = params.getIndex();
        this.startPost = getStartPost(params);
        this.endPost = getEndPost(params);
    }

    public NamedNode getStartPost() {
        return this.startPost;
    }

    public NamedNode getEndPost() {
        return this.endPost;
    }

    private static NamedNode getStartPost(QueryParams params) {
        if (!params.hasStart()) {
            return params.getIndex().minPost();
        }
        return params.getIndex().makePost(params.getIndexStartName(), params.getIndexStartValue());
    }

    private static NamedNode getEndPost(QueryParams params) {
        if (!params.hasEnd()) {
            return params.getIndex().maxPost();
        }
        return params.getIndex().makePost(params.getIndexEndName(), params.getIndexEndValue());
    }

    public boolean matches(NamedNode node) {
        if (this.index.compare(getStartPost(), node) > 0 || this.index.compare(node, getEndPost()) > 0) {
            return false;
        }
        return true;
    }

    public IndexedNode updateChild(IndexedNode snap, ChildKey key, Node newChild, Path affectedPath, NodeFilter.CompleteChildSource source, ChildChangeAccumulator optChangeAccumulator) {
        if (!matches(new NamedNode(key, newChild))) {
            newChild = EmptyNode.Empty();
        }
        return this.indexedFilter.updateChild(snap, key, newChild, affectedPath, source, optChangeAccumulator);
    }

    public IndexedNode updateFullNode(IndexedNode oldSnap, IndexedNode newSnap, ChildChangeAccumulator optChangeAccumulator) {
        IndexedNode filtered;
        if (newSnap.getNode().isLeafNode()) {
            filtered = IndexedNode.from(EmptyNode.Empty(), this.index);
        } else {
            filtered = newSnap.updatePriority(PriorityUtilities.NullPriority());
            Iterator<NamedNode> it = newSnap.iterator();
            while (it.hasNext()) {
                NamedNode child = it.next();
                if (!matches(child)) {
                    filtered = filtered.updateChild(child.getName(), EmptyNode.Empty());
                }
            }
        }
        return this.indexedFilter.updateFullNode(oldSnap, filtered, optChangeAccumulator);
    }

    public IndexedNode updatePriority(IndexedNode oldSnap, Node newPriority) {
        return oldSnap;
    }

    public NodeFilter getIndexedFilter() {
        return this.indexedFilter;
    }

    public Index getIndex() {
        return this.index;
    }

    public boolean filtersNodes() {
        return true;
    }
}
