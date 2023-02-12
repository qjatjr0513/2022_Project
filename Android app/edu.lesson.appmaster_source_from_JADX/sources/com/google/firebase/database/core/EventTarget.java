package com.google.firebase.database.core;

public interface EventTarget {
    void postEvent(Runnable runnable);

    void restart();

    void shutdown();
}
