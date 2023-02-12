package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;

public class LongNode extends LeafNode<LongNode> {
    private final long value;

    public LongNode(Long value2, Node priority) {
        super(priority);
        this.value = value2.longValue();
    }

    public Object getValue() {
        return Long.valueOf(this.value);
    }

    public String getHashRepresentation(Node.HashVersion version) {
        return (getPriorityHash(version) + "number:") + Utilities.doubleToHashString((double) this.value);
    }

    public LongNode updatePriority(Node priority) {
        return new LongNode(Long.valueOf(this.value), priority);
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Number;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(LongNode other) {
        return Utilities.compareLongs(this.value, other.value);
    }

    public boolean equals(Object other) {
        if (!(other instanceof LongNode)) {
            return false;
        }
        LongNode otherLongNode = (LongNode) other;
        if (this.value != otherLongNode.value || !this.priority.equals(otherLongNode.priority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.value;
        return ((int) (j ^ (j >>> 32))) + this.priority.hashCode();
    }
}
