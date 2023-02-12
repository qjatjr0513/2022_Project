package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;

/* compiled from: com.google.firebase:firebase-auth-interop@@20.0.0 */
public class FirebaseAuthException extends FirebaseException {
    private final String zza;

    public FirebaseAuthException(String errorCode, String detailMessage) {
        super(detailMessage);
        this.zza = Preconditions.checkNotEmpty(errorCode);
    }

    public String getErrorCode() {
        return this.zza;
    }
}
