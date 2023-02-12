package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;

public abstract class IndexEntry implements Comparable<IndexEntry> {
    public abstract byte[] getArrayValue();

    public abstract byte[] getDirectionalValue();

    public abstract DocumentKey getDocumentKey();

    public abstract int getIndexId();

    public static IndexEntry create(int indexId, DocumentKey documentKey, byte[] arrayValue, byte[] directionalValue) {
        return new AutoValue_IndexEntry(indexId, documentKey, arrayValue, directionalValue);
    }

    public int compareTo(IndexEntry other) {
        int cmp = Integer.compare(getIndexId(), other.getIndexId());
        if (cmp != 0) {
            return cmp;
        }
        int cmp2 = getDocumentKey().compareTo(other.getDocumentKey());
        if (cmp2 != 0) {
            return cmp2;
        }
        int cmp3 = Util.compareByteArrays(getArrayValue(), other.getArrayValue());
        if (cmp3 != 0) {
            return cmp3;
        }
        return Util.compareByteArrays(getDirectionalValue(), other.getDirectionalValue());
    }
}
