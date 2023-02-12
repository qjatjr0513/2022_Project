package com.google.firestore.p002v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* renamed from: com.google.firestore.v1.ListDocumentsResponseOrBuilder */
public interface ListDocumentsResponseOrBuilder extends MessageLiteOrBuilder {
    Document getDocuments(int i);

    int getDocumentsCount();

    List<Document> getDocumentsList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
