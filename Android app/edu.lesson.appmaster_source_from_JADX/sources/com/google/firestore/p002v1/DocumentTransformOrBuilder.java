package com.google.firestore.p002v1;

import com.google.firestore.p002v1.DocumentTransform;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* renamed from: com.google.firestore.v1.DocumentTransformOrBuilder */
public interface DocumentTransformOrBuilder extends MessageLiteOrBuilder {
    String getDocument();

    ByteString getDocumentBytes();

    DocumentTransform.FieldTransform getFieldTransforms(int i);

    int getFieldTransformsCount();

    List<DocumentTransform.FieldTransform> getFieldTransformsList();
}
