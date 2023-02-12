package com.google.firebase.firestore.auth;

public final class Token {
    private final User user;
    private final String value;

    public Token(String value2, User user2) {
        this.value = value2;
        this.user = user2;
    }

    public String getValue() {
        return this.value;
    }

    public User getUser() {
        return this.user;
    }
}
