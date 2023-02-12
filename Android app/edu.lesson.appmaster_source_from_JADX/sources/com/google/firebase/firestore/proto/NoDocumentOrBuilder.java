package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

public interface NoDocumentOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    Timestamp getReadTime();

    boolean hasReadTime();
}
