package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class OAuthCredential extends AuthCredential {
    public abstract String getAccessToken();

    public abstract String getIdToken();

    public abstract String getSecret();
}
