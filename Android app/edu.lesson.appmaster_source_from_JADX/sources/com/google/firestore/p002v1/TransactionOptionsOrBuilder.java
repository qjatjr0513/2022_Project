package com.google.firestore.p002v1;

import com.google.firestore.p002v1.TransactionOptions;
import com.google.protobuf.MessageLiteOrBuilder;

/* renamed from: com.google.firestore.v1.TransactionOptionsOrBuilder */
public interface TransactionOptionsOrBuilder extends MessageLiteOrBuilder {
    TransactionOptions.ModeCase getModeCase();

    TransactionOptions.ReadOnly getReadOnly();

    TransactionOptions.ReadWrite getReadWrite();

    boolean hasReadOnly();

    boolean hasReadWrite();
}
