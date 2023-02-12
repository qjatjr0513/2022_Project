package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;

public class LimboDocumentChange {
    private final DocumentKey key;
    private final Type type;

    public enum Type {
        ADDED,
        REMOVED
    }

    public LimboDocumentChange(Type type2, DocumentKey key2) {
        this.type = type2;
        this.key = key2;
    }

    public Type getType() {
        return this.type;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LimboDocumentChange)) {
            return false;
        }
        LimboDocumentChange other = (LimboDocumentChange) o;
        if (!this.type.equals(other.getType()) || !this.key.equals(other.getKey())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((67 * 31) + this.type.hashCode()) * 31) + this.key.hashCode();
    }
}
