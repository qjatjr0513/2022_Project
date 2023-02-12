package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DatabaseId;

public final class DatabaseInfo {
    private final DatabaseId databaseId;
    private final String host;
    private final String persistenceKey;
    private final boolean sslEnabled;

    public DatabaseInfo(DatabaseId databaseId2, String persistenceKey2, String host2, boolean sslEnabled2) {
        this.databaseId = databaseId2;
        this.persistenceKey = persistenceKey2;
        this.host = host2;
        this.sslEnabled = sslEnabled2;
    }

    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    public String getPersistenceKey() {
        return this.persistenceKey;
    }

    public String getHost() {
        return this.host;
    }

    public boolean isSslEnabled() {
        return this.sslEnabled;
    }

    public String toString() {
        return "DatabaseInfo(databaseId:" + this.databaseId + " host:" + this.host + ")";
    }
}
