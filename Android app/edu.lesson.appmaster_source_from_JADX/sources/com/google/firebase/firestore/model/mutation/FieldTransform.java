package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.FieldPath;

public final class FieldTransform {
    private final FieldPath fieldPath;
    private final TransformOperation operation;

    public FieldTransform(FieldPath fieldPath2, TransformOperation operation2) {
        this.fieldPath = fieldPath2;
        this.operation = operation2;
    }

    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    public TransformOperation getOperation() {
        return this.operation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTransform that = (FieldTransform) o;
        if (!this.fieldPath.equals(that.fieldPath)) {
            return false;
        }
        return this.operation.equals(that.operation);
    }

    public int hashCode() {
        return (this.fieldPath.hashCode() * 31) + this.operation.hashCode();
    }
}
