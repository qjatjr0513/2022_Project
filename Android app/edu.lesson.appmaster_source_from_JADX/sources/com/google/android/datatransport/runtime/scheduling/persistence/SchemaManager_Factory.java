package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

public final class SchemaManager_Factory implements Factory<SchemaManager> {
    private final Provider<Context> contextProvider;
    private final Provider<String> dbNameProvider;
    private final Provider<Integer> schemaVersionProvider;

    public SchemaManager_Factory(Provider<Context> contextProvider2, Provider<String> dbNameProvider2, Provider<Integer> schemaVersionProvider2) {
        this.contextProvider = contextProvider2;
        this.dbNameProvider = dbNameProvider2;
        this.schemaVersionProvider = schemaVersionProvider2;
    }

    public SchemaManager get() {
        return newInstance(this.contextProvider.get(), this.dbNameProvider.get(), this.schemaVersionProvider.get().intValue());
    }

    public static SchemaManager_Factory create(Provider<Context> contextProvider2, Provider<String> dbNameProvider2, Provider<Integer> schemaVersionProvider2) {
        return new SchemaManager_Factory(contextProvider2, dbNameProvider2, schemaVersionProvider2);
    }

    public static SchemaManager newInstance(Context context, String dbName, int schemaVersion) {
        return new SchemaManager(context, dbName, schemaVersion);
    }
}
