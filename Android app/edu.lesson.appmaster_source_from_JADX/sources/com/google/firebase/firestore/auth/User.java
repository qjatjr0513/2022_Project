package com.google.firebase.firestore.auth;

public final class User {
    public static final User UNAUTHENTICATED = new User((String) null);
    private final String uid;

    public User(String uid2) {
        this.uid = uid2;
    }

    public String getUid() {
        return this.uid;
    }

    public boolean isAuthenticated() {
        return this.uid != null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        String str = this.uid;
        if (str != null) {
            return str.equals(user.uid);
        }
        if (user.uid == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.uid;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "User(uid:" + this.uid + ")";
    }
}
