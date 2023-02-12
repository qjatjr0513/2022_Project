package com.google.firestore.p002v1;

import com.google.firestore.p002v1.ListDocumentsRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* renamed from: com.google.firestore.v1.ListDocumentsRequestOrBuilder */
public interface ListDocumentsRequestOrBuilder extends MessageLiteOrBuilder {
    String getCollectionId();

    ByteString getCollectionIdBytes();

    ListDocumentsRequest.ConsistencySelectorCase getConsistencySelectorCase();

    DocumentMask getMask();

    String getOrderBy();

    ByteString getOrderByBytes();

    int getPageSize();

    String getPageToken();

    ByteString getPageTokenBytes();

    String getParent();

    ByteString getParentBytes();

    Timestamp getReadTime();

    boolean getShowMissing();

    ByteString getTransaction();

    boolean hasMask();

    boolean hasReadTime();
}
