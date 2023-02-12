package com.google.firestore.p002v1;

import com.google.firestore.p002v1.ListenResponse;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.ListenResponseOrBuilder */
public interface ListenResponseOrBuilder extends MessageLiteOrBuilder {
    DocumentChange getDocumentChange();

    DocumentDelete getDocumentDelete();

    DocumentRemove getDocumentRemove();

    ExistenceFilter getFilter();

    ListenResponse.ResponseTypeCase getResponseTypeCase();

    TargetChange getTargetChange();

    boolean hasDocumentChange();

    boolean hasDocumentDelete();

    boolean hasDocumentRemove();

    boolean hasFilter();

    boolean hasTargetChange();
}
