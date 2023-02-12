package com.google.firebase.database.snapshot;

import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;

public class BooleanNode extends LeafNode<BooleanNode> {
    private final boolean value;

    public BooleanNode(Boolean value2, Node priority) {
        super(priority);
        this.value = value2.booleanValue();
    }

    public Object getValue() {
        return Boolean.valueOf(this.value);
    }

    public String getHashRepresentation(Node.HashVersion version) {
        return getPriorityHash(version) + "boolean:" + this.value;
    }

    public BooleanNode updatePriority(Node priority) {
        return new BooleanNode(Boolean.valueOf(this.value), priority);
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Boolean;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(BooleanNode other) {
        boolean z = this.value;
        if (z == other.value) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public boolean equals(Object other) {
        if (!(other instanceof BooleanNode)) {
            return false;
        }
        BooleanNode otherBooleanNode = (BooleanNode) other;
        if (this.value != otherBooleanNode.value || !this.priority.equals(otherBooleanNode.priority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.value;
        return (z ? 1 : 0) + this.priority.hashCode();
    }
}
