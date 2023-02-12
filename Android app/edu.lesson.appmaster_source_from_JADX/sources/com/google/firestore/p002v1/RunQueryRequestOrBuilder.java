package com.google.firestore.p002v1;

import com.google.firestore.p002v1.RunQueryRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* renamed from: com.google.firestore.v1.RunQueryRequestOrBuilder */
public interface RunQueryRequestOrBuilder extends MessageLiteOrBuilder {
    RunQueryRequest.ConsistencySelectorCase getConsistencySelectorCase();

    TransactionOptions getNewTransaction();

    String getParent();

    ByteString getParentBytes();

    RunQueryRequest.QueryTypeCase getQueryTypeCase();

    Timestamp getReadTime();

    StructuredQuery getStructuredQuery();

    ByteString getTransaction();

    boolean hasNewTransaction();

    boolean hasReadTime();

    boolean hasStructuredQuery();
}
