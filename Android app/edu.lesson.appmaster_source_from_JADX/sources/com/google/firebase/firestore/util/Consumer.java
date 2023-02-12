package com.google.firebase.firestore.util;

public interface Consumer<T> {
    void accept(T t);
}
