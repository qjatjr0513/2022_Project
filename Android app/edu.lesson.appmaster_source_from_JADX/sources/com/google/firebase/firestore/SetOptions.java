package com.google.firebase.firestore;

import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SetOptions {
    private static final SetOptions MERGE_ALL_FIELDS = new SetOptions(true, (FieldMask) null);
    static final SetOptions OVERWRITE = new SetOptions(false, (FieldMask) null);
    private final FieldMask fieldMask;
    private final boolean merge;

    private SetOptions(boolean merge2, FieldMask fieldMask2) {
        Preconditions.checkArgument(fieldMask2 == null || merge2, "Cannot specify a fieldMask for non-merge sets()", new Object[0]);
        this.merge = merge2;
        this.fieldMask = fieldMask2;
    }

    /* access modifiers changed from: package-private */
    public boolean isMerge() {
        return this.merge;
    }

    public FieldMask getFieldMask() {
        return this.fieldMask;
    }

    public static SetOptions merge() {
        return MERGE_ALL_FIELDS;
    }

    public static SetOptions mergeFields(List<String> fields) {
        Set<FieldPath> fieldPaths = new HashSet<>();
        for (String field : fields) {
            fieldPaths.add(FieldPath.fromDotSeparatedPath(field).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(fieldPaths));
    }

    public static SetOptions mergeFields(String... fields) {
        Set<FieldPath> fieldPaths = new HashSet<>();
        for (String field : fields) {
            fieldPaths.add(FieldPath.fromDotSeparatedPath(field).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(fieldPaths));
    }

    public static SetOptions mergeFieldPaths(List<FieldPath> fields) {
        Set<FieldPath> fieldPaths = new HashSet<>();
        for (FieldPath field : fields) {
            fieldPaths.add(field.getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(fieldPaths));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetOptions that = (SetOptions) o;
        if (this.merge != that.merge) {
            return false;
        }
        FieldMask fieldMask2 = this.fieldMask;
        if (fieldMask2 != null) {
            return fieldMask2.equals(that.fieldMask);
        }
        if (that.fieldMask == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = ((int) this.merge) * true;
        FieldMask fieldMask2 = this.fieldMask;
        return i + (fieldMask2 != null ? fieldMask2.hashCode() : 0);
    }
}
