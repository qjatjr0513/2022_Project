package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.List;

public class BundledDocumentMetadata implements BundleElement {
    private final boolean exists;
    private final DocumentKey key;
    private final List<String> queries;
    private final SnapshotVersion readTime;

    public BundledDocumentMetadata(DocumentKey key2, SnapshotVersion readTime2, boolean exists2, List<String> queries2) {
        this.key = key2;
        this.readTime = readTime2;
        this.exists = exists2;
        this.queries = queries2;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public boolean exists() {
        return this.exists;
    }

    public List<String> getQueries() {
        return this.queries;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BundledDocumentMetadata that = (BundledDocumentMetadata) o;
        if (this.exists == that.exists && this.key.equals(that.key) && this.readTime.equals(that.readTime)) {
            return this.queries.equals(that.queries);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.key.hashCode() * 31) + this.readTime.hashCode()) * 31) + (this.exists ? 1 : 0)) * 31) + this.queries.hashCode();
    }
}
