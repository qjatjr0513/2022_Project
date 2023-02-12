package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class SQLiteEventStore_Factory implements Factory<SQLiteEventStore> {
    private final Provider<Clock> clockProvider;
    private final Provider<EventStoreConfig> configProvider;
    private final Provider<SchemaManager> schemaManagerProvider;
    private final Provider<Clock> wallClockProvider;

    public SQLiteEventStore_Factory(Provider<Clock> wallClockProvider2, Provider<Clock> clockProvider2, Provider<EventStoreConfig> configProvider2, Provider<SchemaManager> schemaManagerProvider2) {
        this.wallClockProvider = wallClockProvider2;
        this.clockProvider = clockProvider2;
        this.configProvider = configProvider2;
        this.schemaManagerProvider = schemaManagerProvider2;
    }

    public SQLiteEventStore get() {
        return newInstance(this.wallClockProvider.get(), this.clockProvider.get(), this.configProvider.get(), this.schemaManagerProvider.get());
    }

    public static SQLiteEventStore_Factory create(Provider<Clock> wallClockProvider2, Provider<Clock> clockProvider2, Provider<EventStoreConfig> configProvider2, Provider<SchemaManager> schemaManagerProvider2) {
        return new SQLiteEventStore_Factory(wallClockProvider2, clockProvider2, configProvider2, schemaManagerProvider2);
    }

    public static SQLiteEventStore newInstance(Clock wallClock, Clock clock, Object config, Object schemaManager) {
        return new SQLiteEventStore(wallClock, clock, (EventStoreConfig) config, (SchemaManager) schemaManager);
    }
}
