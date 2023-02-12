package com.google.firestore.p002v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* renamed from: com.google.firestore.v1.DocumentMaskOrBuilder */
public interface DocumentMaskOrBuilder extends MessageLiteOrBuilder {
    String getFieldPaths(int i);

    ByteString getFieldPathsBytes(int i);

    int getFieldPathsCount();

    List<String> getFieldPathsList();
}
