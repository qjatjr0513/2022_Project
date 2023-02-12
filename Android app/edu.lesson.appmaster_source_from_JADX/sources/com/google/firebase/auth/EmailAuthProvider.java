package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class EmailAuthProvider {
    public static final String EMAIL_LINK_SIGN_IN_METHOD = "emailLink";
    public static final String EMAIL_PASSWORD_SIGN_IN_METHOD = "password";
    public static final String PROVIDER_ID = "password";

    private EmailAuthProvider() {
    }

    public static AuthCredential getCredential(String email, String password) {
        Preconditions.checkNotEmpty(email);
        Preconditions.checkNotEmpty(password);
        return new EmailAuthCredential(email, password, (String) null, (String) null, false);
    }

    public static AuthCredential getCredentialWithLink(String email, String emailLink) {
        if (EmailAuthCredential.zzi(emailLink)) {
            return new EmailAuthCredential(email, (String) null, emailLink, (String) null, false);
        }
        throw new IllegalArgumentException("Given link is not a valid email link. Please use FirebaseAuth#isSignInWithEmailLink(String) to determine this before calling this function");
    }
}
