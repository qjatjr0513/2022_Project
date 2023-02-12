package com.google.firebase.firestore.util;

public interface Listener<T> {
    void onValue(T t);
}
