package com.google.firebase.firestore;

public interface EventListener<T> {
    void onEvent(T t, FirebaseFirestoreException firebaseFirestoreException);
}
