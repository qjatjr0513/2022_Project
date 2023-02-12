package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class FirebaseException extends Exception {
    @Deprecated
    protected FirebaseException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FirebaseException(String detailMessage) {
        super(detailMessage);
        Preconditions.checkNotEmpty(detailMessage, "Detail message must not be empty");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FirebaseException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
        Preconditions.checkNotEmpty(detailMessage, "Detail message must not be empty");
    }
}
