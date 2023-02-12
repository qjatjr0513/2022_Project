package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;

public class DoubleNode extends LeafNode<DoubleNode> {
    private final Double value;

    public DoubleNode(Double value2, Node priority) {
        super(priority);
        this.value = value2;
    }

    public Object getValue() {
        return this.value;
    }

    public String getHashRepresentation(Node.HashVersion version) {
        return (getPriorityHash(version) + "number:") + Utilities.doubleToHashString(this.value.doubleValue());
    }

    public DoubleNode updatePriority(Node priority) {
        Utilities.hardAssert(PriorityUtilities.isValidPriority(priority));
        return new DoubleNode(this.value, priority);
    }

    /* access modifiers changed from: protected */
    public LeafNode.LeafType getLeafType() {
        return LeafNode.LeafType.Number;
    }

    /* access modifiers changed from: protected */
    public int compareLeafValues(DoubleNode other) {
        return this.value.compareTo(other.value);
    }

    public boolean equals(Object other) {
        if (!(other instanceof DoubleNode)) {
            return false;
        }
        DoubleNode otherDoubleNode = (DoubleNode) other;
        if (!this.value.equals(otherDoubleNode.value) || !this.priority.equals(otherDoubleNode.priority)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value.hashCode() + this.priority.hashCode();
    }
}
