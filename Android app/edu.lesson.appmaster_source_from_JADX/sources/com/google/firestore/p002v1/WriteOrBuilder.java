package com.google.firestore.p002v1;

import com.google.firestore.p002v1.DocumentTransform;
import com.google.firestore.p002v1.Write;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* renamed from: com.google.firestore.v1.WriteOrBuilder */
public interface WriteOrBuilder extends MessageLiteOrBuilder {
    Precondition getCurrentDocument();

    String getDelete();

    ByteString getDeleteBytes();

    Write.OperationCase getOperationCase();

    DocumentTransform getTransform();

    Document getUpdate();

    DocumentMask getUpdateMask();

    DocumentTransform.FieldTransform getUpdateTransforms(int i);

    int getUpdateTransformsCount();

    List<DocumentTransform.FieldTransform> getUpdateTransformsList();

    String getVerify();

    ByteString getVerifyBytes();

    boolean hasCurrentDocument();

    boolean hasTransform();

    boolean hasUpdate();

    boolean hasUpdateMask();
}
