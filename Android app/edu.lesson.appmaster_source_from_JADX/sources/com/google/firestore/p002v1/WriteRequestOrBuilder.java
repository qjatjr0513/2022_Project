package com.google.firestore.p002v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.firestore.v1.WriteRequestOrBuilder */
public interface WriteRequestOrBuilder extends MessageLiteOrBuilder {
    boolean containsLabels(String str);

    String getDatabase();

    ByteString getDatabaseBytes();

    @Deprecated
    Map<String, String> getLabels();

    int getLabelsCount();

    Map<String, String> getLabelsMap();

    String getLabelsOrDefault(String str, String str2);

    String getLabelsOrThrow(String str);

    String getStreamId();

    ByteString getStreamIdBytes();

    ByteString getStreamToken();

    Write getWrites(int i);

    int getWritesCount();

    List<Write> getWritesList();
}
