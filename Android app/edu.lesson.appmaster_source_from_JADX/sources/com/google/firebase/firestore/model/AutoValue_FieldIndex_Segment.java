package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

final class AutoValue_FieldIndex_Segment extends FieldIndex.Segment {
    private final FieldPath fieldPath;
    private final FieldIndex.Segment.Kind kind;

    AutoValue_FieldIndex_Segment(FieldPath fieldPath2, FieldIndex.Segment.Kind kind2) {
        if (fieldPath2 != null) {
            this.fieldPath = fieldPath2;
            if (kind2 != null) {
                this.kind = kind2;
                return;
            }
            throw new NullPointerException("Null kind");
        }
        throw new NullPointerException("Null fieldPath");
    }

    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    public FieldIndex.Segment.Kind getKind() {
        return this.kind;
    }

    public String toString() {
        return "Segment{fieldPath=" + this.fieldPath + ", kind=" + this.kind + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIndex.Segment)) {
            return false;
        }
        FieldIndex.Segment that = (FieldIndex.Segment) o;
        if (!this.fieldPath.equals(that.getFieldPath()) || !this.kind.equals(that.getKind())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.fieldPath.hashCode()) * 1000003) ^ this.kind.hashCode();
    }
}
