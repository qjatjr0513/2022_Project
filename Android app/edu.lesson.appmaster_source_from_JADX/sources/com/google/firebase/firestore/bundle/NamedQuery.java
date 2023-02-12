package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

public class NamedQuery implements BundleElement {
    private final BundledQuery bundledQuery;
    private final String name;
    private final SnapshotVersion readTime;

    public NamedQuery(String name2, BundledQuery bundledQuery2, SnapshotVersion readTime2) {
        this.name = name2;
        this.bundledQuery = bundledQuery2;
        this.readTime = readTime2;
    }

    public String getName() {
        return this.name;
    }

    public BundledQuery getBundledQuery() {
        return this.bundledQuery;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NamedQuery that = (NamedQuery) o;
        if (this.name.equals(that.name) && this.bundledQuery.equals(that.bundledQuery)) {
            return this.readTime.equals(that.readTime);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.bundledQuery.hashCode()) * 31) + this.readTime.hashCode();
    }
}
