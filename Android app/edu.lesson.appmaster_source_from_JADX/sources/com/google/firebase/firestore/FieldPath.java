package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Preconditions;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class FieldPath {
    private static final FieldPath DOCUMENT_ID_INSTANCE = new FieldPath(com.google.firebase.firestore.model.FieldPath.KEY_PATH);
    private static final Pattern RESERVED = Pattern.compile("[~*/\\[\\]]");
    private final com.google.firebase.firestore.model.FieldPath internalPath;

    private FieldPath(List<String> segments) {
        this.internalPath = com.google.firebase.firestore.model.FieldPath.fromSegments(segments);
    }

    private FieldPath(com.google.firebase.firestore.model.FieldPath internalPath2) {
        this.internalPath = internalPath2;
    }

    /* access modifiers changed from: package-private */
    public com.google.firebase.firestore.model.FieldPath getInternalPath() {
        return this.internalPath;
    }

    /* renamed from: of */
    public static FieldPath m197of(String... fieldNames) {
        Preconditions.checkArgument(fieldNames.length > 0, "Invalid field path. Provided path must not be empty.", new Object[0]);
        for (int i = 0; i < fieldNames.length; i++) {
            Preconditions.checkArgument(fieldNames[i] != null && !fieldNames[i].isEmpty(), "Invalid field name at argument " + (i + 1) + ". Field names must not be null or empty.", new Object[0]);
        }
        return new FieldPath((List<String>) Arrays.asList(fieldNames));
    }

    public static FieldPath documentId() {
        return DOCUMENT_ID_INSTANCE;
    }

    static FieldPath fromDotSeparatedPath(String path) {
        Preconditions.checkNotNull(path, "Provided field path must not be null.");
        Preconditions.checkArgument(!RESERVED.matcher(path).find(), "Use FieldPath.of() for field names containing '~*/[]'.", new Object[0]);
        try {
            return m197of(path.split("\\.", -1));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid field path (" + path + "). Paths must not be empty, begin with '.', end with '.', or contain '..'");
        }
    }

    public String toString() {
        return this.internalPath.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.internalPath.equals(((FieldPath) o).internalPath);
    }

    public int hashCode() {
        return this.internalPath.hashCode();
    }
}
