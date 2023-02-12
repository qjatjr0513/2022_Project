package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class TwitterAuthProvider {
    public static final String PROVIDER_ID = "twitter.com";
    public static final String TWITTER_SIGN_IN_METHOD = "twitter.com";

    private TwitterAuthProvider() {
    }

    public static AuthCredential getCredential(String token, String secret) {
        return new TwitterAuthCredential(token, secret);
    }
}
