package com.google.firestore.bundle;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

public interface BundledDocumentMetadataOrBuilder extends MessageLiteOrBuilder {
    boolean getExists();

    String getName();

    ByteString getNameBytes();

    String getQueries(int i);

    ByteString getQueriesBytes(int i);

    int getQueriesCount();

    List<String> getQueriesList();

    Timestamp getReadTime();

    boolean hasReadTime();
}
