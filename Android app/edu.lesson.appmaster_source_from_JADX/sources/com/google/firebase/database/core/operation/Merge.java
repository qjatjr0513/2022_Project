package com.google.firebase.database.core.operation;

import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.snapshot.ChildKey;

public class Merge extends Operation {
    private final CompoundWrite children;

    public Merge(OperationSource source, Path path, CompoundWrite children2) {
        super(Operation.OperationType.Merge, source, path);
        this.children = children2;
    }

    public CompoundWrite getChildren() {
        return this.children;
    }

    public Operation operationForChild(ChildKey childKey) {
        if (this.path.isEmpty()) {
            CompoundWrite childTree = this.children.childCompoundWrite(new Path(childKey));
            if (childTree.isEmpty()) {
                return null;
            }
            if (childTree.rootWrite() != null) {
                return new Overwrite(this.source, Path.getEmptyPath(), childTree.rootWrite());
            }
            return new Merge(this.source, Path.getEmptyPath(), childTree);
        } else if (this.path.getFront().equals(childKey)) {
            return new Merge(this.source, this.path.popFront(), this.children);
        } else {
            return null;
        }
    }

    public String toString() {
        return String.format("Merge { path=%s, source=%s, children=%s }", new Object[]{getPath(), getSource(), this.children});
    }
}
