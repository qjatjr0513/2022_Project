package com.google.firestore.bundle;

import com.google.firestore.bundle.BundledQuery;
import com.google.firestore.p002v1.StructuredQuery;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BundledQueryOrBuilder extends MessageLiteOrBuilder {
    BundledQuery.LimitType getLimitType();

    int getLimitTypeValue();

    String getParent();

    ByteString getParentBytes();

    BundledQuery.QueryTypeCase getQueryTypeCase();

    StructuredQuery getStructuredQuery();

    boolean hasStructuredQuery();
}
