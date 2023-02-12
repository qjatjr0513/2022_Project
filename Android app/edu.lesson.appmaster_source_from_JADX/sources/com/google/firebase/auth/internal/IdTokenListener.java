package com.google.firebase.auth.internal;

import com.google.firebase.internal.InternalTokenResult;

/* compiled from: com.google.firebase:firebase-auth-interop@@20.0.0 */
public interface IdTokenListener {
    void onIdTokenChanged(InternalTokenResult internalTokenResult);
}
