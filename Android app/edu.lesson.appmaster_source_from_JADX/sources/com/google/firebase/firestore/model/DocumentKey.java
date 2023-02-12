package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class DocumentKey implements Comparable<DocumentKey> {
    private static final Comparator<DocumentKey> COMPARATOR;
    private static final ImmutableSortedSet<DocumentKey> EMPTY_KEY_SET;
    public static final String KEY_FIELD_NAME = "__name__";
    private final ResourcePath path;

    static {
        DocumentKey$$ExternalSyntheticLambda0 documentKey$$ExternalSyntheticLambda0 = DocumentKey$$ExternalSyntheticLambda0.INSTANCE;
        COMPARATOR = documentKey$$ExternalSyntheticLambda0;
        EMPTY_KEY_SET = new ImmutableSortedSet<>(Collections.emptyList(), documentKey$$ExternalSyntheticLambda0);
    }

    public static Comparator<DocumentKey> comparator() {
        return COMPARATOR;
    }

    public static ImmutableSortedSet<DocumentKey> emptyKeySet() {
        return EMPTY_KEY_SET;
    }

    public static DocumentKey empty() {
        return fromSegments(Collections.emptyList());
    }

    public static DocumentKey fromName(String name) {
        ResourcePath resourceName = ResourcePath.fromString(name);
        Assert.hardAssert(resourceName.length() > 4 && resourceName.getSegment(0).equals("projects") && resourceName.getSegment(2).equals("databases") && resourceName.getSegment(4).equals("documents"), "Tried to parse an invalid key: %s", resourceName);
        return fromPath((ResourcePath) resourceName.popFirst(5));
    }

    public static DocumentKey fromPath(ResourcePath path2) {
        return new DocumentKey(path2);
    }

    public static DocumentKey fromSegments(List<String> segments) {
        return new DocumentKey(ResourcePath.fromSegments(segments));
    }

    public static DocumentKey fromPathString(String path2) {
        return new DocumentKey(ResourcePath.fromString(path2));
    }

    public static boolean isDocumentKey(ResourcePath path2) {
        return path2.length() % 2 == 0;
    }

    private DocumentKey(ResourcePath path2) {
        Assert.hardAssert(isDocumentKey(path2), "Not a document key path: %s", path2);
        this.path = path2;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        ResourcePath resourcePath = this.path;
        return resourcePath.getSegment(resourcePath.length() - 2);
    }

    public ResourcePath getCollectionPath() {
        return (ResourcePath) this.path.popLast();
    }

    public String getDocumentId() {
        return this.path.getLastSegment();
    }

    public boolean hasCollectionId(String collectionId) {
        return this.path.length() >= 2 && ((String) this.path.segments.get(this.path.length() - 2)).equals(collectionId);
    }

    public int compareTo(DocumentKey another) {
        return this.path.compareTo(another.path);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.path.equals(((DocumentKey) o).path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return this.path.toString();
    }
}
