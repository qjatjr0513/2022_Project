package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.Arrays;

final class AutoValue_IndexEntry extends IndexEntry {
    private final byte[] arrayValue;
    private final byte[] directionalValue;
    private final DocumentKey documentKey;
    private final int indexId;

    AutoValue_IndexEntry(int indexId2, DocumentKey documentKey2, byte[] arrayValue2, byte[] directionalValue2) {
        this.indexId = indexId2;
        if (documentKey2 != null) {
            this.documentKey = documentKey2;
            if (arrayValue2 != null) {
                this.arrayValue = arrayValue2;
                if (directionalValue2 != null) {
                    this.directionalValue = directionalValue2;
                    return;
                }
                throw new NullPointerException("Null directionalValue");
            }
            throw new NullPointerException("Null arrayValue");
        }
        throw new NullPointerException("Null documentKey");
    }

    public int getIndexId() {
        return this.indexId;
    }

    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    public byte[] getArrayValue() {
        return this.arrayValue;
    }

    public byte[] getDirectionalValue() {
        return this.directionalValue;
    }

    public String toString() {
        return "IndexEntry{indexId=" + this.indexId + ", documentKey=" + this.documentKey + ", arrayValue=" + Arrays.toString(this.arrayValue) + ", directionalValue=" + Arrays.toString(this.directionalValue) + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IndexEntry)) {
            return false;
        }
        IndexEntry that = (IndexEntry) o;
        if (this.indexId == that.getIndexId() && this.documentKey.equals(that.getDocumentKey())) {
            if (Arrays.equals(this.arrayValue, that instanceof AutoValue_IndexEntry ? ((AutoValue_IndexEntry) that).arrayValue : that.getArrayValue())) {
                if (Arrays.equals(this.directionalValue, that instanceof AutoValue_IndexEntry ? ((AutoValue_IndexEntry) that).directionalValue : that.getDirectionalValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((1 * 1000003) ^ this.indexId) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ Arrays.hashCode(this.arrayValue)) * 1000003) ^ Arrays.hashCode(this.directionalValue);
    }
}
