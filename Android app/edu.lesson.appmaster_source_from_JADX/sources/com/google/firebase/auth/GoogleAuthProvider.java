package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class GoogleAuthProvider {
    public static final String GOOGLE_SIGN_IN_METHOD = "google.com";
    public static final String PROVIDER_ID = "google.com";

    private GoogleAuthProvider() {
    }

    public static AuthCredential getCredential(String idToken, String accessToken) {
        return new GoogleAuthCredential(idToken, accessToken);
    }
}
