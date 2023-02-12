package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;

public class BundleDocument implements BundleElement {
    private MutableDocument document;

    public BundleDocument(MutableDocument document2) {
        this.document = document2;
    }

    public DocumentKey getKey() {
        return this.document.getKey();
    }

    public MutableDocument getDocument() {
        return this.document;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.document.equals(((BundleDocument) o).document);
    }

    public int hashCode() {
        return this.document.hashCode();
    }
}
