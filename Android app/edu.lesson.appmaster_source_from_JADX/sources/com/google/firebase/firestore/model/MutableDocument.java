package com.google.firebase.firestore.model;

import com.google.firestore.p002v1.Value;

public final class MutableDocument implements Document {
    private DocumentState documentState;
    private DocumentType documentType;
    private final DocumentKey key;
    private SnapshotVersion readTime;
    private ObjectValue value;
    private SnapshotVersion version;

    private enum DocumentState {
        HAS_LOCAL_MUTATIONS,
        HAS_COMMITTED_MUTATIONS,
        SYNCED
    }

    private enum DocumentType {
        INVALID,
        FOUND_DOCUMENT,
        NO_DOCUMENT,
        UNKNOWN_DOCUMENT
    }

    private MutableDocument(DocumentKey key2) {
        this.key = key2;
        this.readTime = SnapshotVersion.NONE;
    }

    private MutableDocument(DocumentKey key2, DocumentType documentType2, SnapshotVersion version2, SnapshotVersion readTime2, ObjectValue value2, DocumentState documentState2) {
        this.key = key2;
        this.version = version2;
        this.readTime = readTime2;
        this.documentType = documentType2;
        this.documentState = documentState2;
        this.value = value2;
    }

    public static MutableDocument newInvalidDocument(DocumentKey documentKey) {
        return new MutableDocument(documentKey, DocumentType.INVALID, SnapshotVersion.NONE, SnapshotVersion.NONE, new ObjectValue(), DocumentState.SYNCED);
    }

    public static MutableDocument newFoundDocument(DocumentKey documentKey, SnapshotVersion version2, ObjectValue value2) {
        return new MutableDocument(documentKey).convertToFoundDocument(version2, value2);
    }

    public static MutableDocument newNoDocument(DocumentKey documentKey, SnapshotVersion version2) {
        return new MutableDocument(documentKey).convertToNoDocument(version2);
    }

    public static MutableDocument newUnknownDocument(DocumentKey documentKey, SnapshotVersion version2) {
        return new MutableDocument(documentKey).convertToUnknownDocument(version2);
    }

    public MutableDocument convertToFoundDocument(SnapshotVersion version2, ObjectValue value2) {
        this.version = version2;
        this.documentType = DocumentType.FOUND_DOCUMENT;
        this.value = value2;
        this.documentState = DocumentState.SYNCED;
        return this;
    }

    public MutableDocument convertToNoDocument(SnapshotVersion version2) {
        this.version = version2;
        this.documentType = DocumentType.NO_DOCUMENT;
        this.value = new ObjectValue();
        this.documentState = DocumentState.SYNCED;
        return this;
    }

    public MutableDocument convertToUnknownDocument(SnapshotVersion version2) {
        this.version = version2;
        this.documentType = DocumentType.UNKNOWN_DOCUMENT;
        this.value = new ObjectValue();
        this.documentState = DocumentState.HAS_COMMITTED_MUTATIONS;
        return this;
    }

    public MutableDocument setHasCommittedMutations() {
        this.documentState = DocumentState.HAS_COMMITTED_MUTATIONS;
        return this;
    }

    public MutableDocument setHasLocalMutations() {
        this.documentState = DocumentState.HAS_LOCAL_MUTATIONS;
        this.version = SnapshotVersion.NONE;
        return this;
    }

    public MutableDocument setReadTime(SnapshotVersion readTime2) {
        this.readTime = readTime2;
        return this;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public SnapshotVersion getVersion() {
        return this.version;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public boolean hasLocalMutations() {
        return this.documentState.equals(DocumentState.HAS_LOCAL_MUTATIONS);
    }

    public boolean hasCommittedMutations() {
        return this.documentState.equals(DocumentState.HAS_COMMITTED_MUTATIONS);
    }

    public boolean hasPendingWrites() {
        return hasLocalMutations() || hasCommittedMutations();
    }

    public ObjectValue getData() {
        return this.value;
    }

    public Value getField(FieldPath field) {
        return getData().get(field);
    }

    public boolean isValidDocument() {
        return !this.documentType.equals(DocumentType.INVALID);
    }

    public boolean isFoundDocument() {
        return this.documentType.equals(DocumentType.FOUND_DOCUMENT);
    }

    public boolean isNoDocument() {
        return this.documentType.equals(DocumentType.NO_DOCUMENT);
    }

    public boolean isUnknownDocument() {
        return this.documentType.equals(DocumentType.UNKNOWN_DOCUMENT);
    }

    public MutableDocument mutableCopy() {
        return new MutableDocument(this.key, this.documentType, this.version, this.readTime, this.value.clone(), this.documentState);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutableDocument document = (MutableDocument) o;
        if (this.key.equals(document.key) && this.version.equals(document.version) && this.documentType.equals(document.documentType) && this.documentState.equals(document.documentState)) {
            return this.value.equals(document.value);
        }
        return false;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        return "Document{key=" + this.key + ", version=" + this.version + ", readTime=" + this.readTime + ", type=" + this.documentType + ", documentState=" + this.documentState + ", value=" + this.value + '}';
    }
}
