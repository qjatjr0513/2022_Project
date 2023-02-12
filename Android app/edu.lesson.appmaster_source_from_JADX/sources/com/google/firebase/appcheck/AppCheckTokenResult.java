package com.google.firebase.appcheck;

import com.google.firebase.FirebaseException;

public abstract class AppCheckTokenResult {
    public abstract FirebaseException getError();

    public abstract String getToken();
}
