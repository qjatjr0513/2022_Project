package com.google.firestore.bundle;

import com.google.firestore.bundle.BundleElement;
import com.google.firestore.p002v1.Document;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BundleElementOrBuilder extends MessageLiteOrBuilder {
    Document getDocument();

    BundledDocumentMetadata getDocumentMetadata();

    BundleElement.ElementTypeCase getElementTypeCase();

    BundleMetadata getMetadata();

    NamedQuery getNamedQuery();

    boolean hasDocument();

    boolean hasDocumentMetadata();

    boolean hasMetadata();

    boolean hasNamedQuery();
}
