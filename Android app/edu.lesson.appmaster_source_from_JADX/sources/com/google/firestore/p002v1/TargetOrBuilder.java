package com.google.firestore.p002v1;

import com.google.firestore.p002v1.Target;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* renamed from: com.google.firestore.v1.TargetOrBuilder */
public interface TargetOrBuilder extends MessageLiteOrBuilder {
    Target.DocumentsTarget getDocuments();

    boolean getOnce();

    Target.QueryTarget getQuery();

    Timestamp getReadTime();

    ByteString getResumeToken();

    Target.ResumeTypeCase getResumeTypeCase();

    int getTargetId();

    Target.TargetTypeCase getTargetTypeCase();

    boolean hasDocuments();

    boolean hasQuery();

    boolean hasReadTime();
}
