package com.google.firestore.p002v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* renamed from: com.google.firestore.v1.WriteResponseOrBuilder */
public interface WriteResponseOrBuilder extends MessageLiteOrBuilder {
    Timestamp getCommitTime();

    String getStreamId();

    ByteString getStreamIdBytes();

    ByteString getStreamToken();

    WriteResult getWriteResults(int i);

    int getWriteResultsCount();

    List<WriteResult> getWriteResultsList();

    boolean hasCommitTime();
}
