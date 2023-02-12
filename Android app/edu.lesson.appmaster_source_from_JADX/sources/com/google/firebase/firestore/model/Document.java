package com.google.firebase.firestore.model;

import com.google.firestore.p002v1.Value;
import java.util.Comparator;

public interface Document {
    public static final Comparator<Document> KEY_COMPARATOR = Document$$ExternalSyntheticLambda0.INSTANCE;

    ObjectValue getData();

    Value getField(FieldPath fieldPath);

    DocumentKey getKey();

    SnapshotVersion getReadTime();

    SnapshotVersion getVersion();

    boolean hasCommittedMutations();

    boolean hasLocalMutations();

    boolean hasPendingWrites();

    boolean isFoundDocument();

    boolean isNoDocument();

    boolean isUnknownDocument();

    boolean isValidDocument();

    MutableDocument mutableCopy();
}
