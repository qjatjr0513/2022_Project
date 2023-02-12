package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.util.Consumer;

public interface ConnectivityMonitor {

    public enum NetworkStatus {
        UNREACHABLE,
        REACHABLE
    }

    void addCallback(Consumer<NetworkStatus> consumer);

    void shutdown();
}
