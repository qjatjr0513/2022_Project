package com.google.firestore.p002v1;

import com.google.firestore.p002v1.BatchGetDocumentsRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder */
public interface BatchGetDocumentsRequestOrBuilder extends MessageLiteOrBuilder {
    BatchGetDocumentsRequest.ConsistencySelectorCase getConsistencySelectorCase();

    String getDatabase();

    ByteString getDatabaseBytes();

    String getDocuments(int i);

    ByteString getDocumentsBytes(int i);

    int getDocumentsCount();

    List<String> getDocumentsList();

    DocumentMask getMask();

    TransactionOptions getNewTransaction();

    Timestamp getReadTime();

    ByteString getTransaction();

    boolean hasMask();

    boolean hasNewTransaction();

    boolean hasReadTime();
}
