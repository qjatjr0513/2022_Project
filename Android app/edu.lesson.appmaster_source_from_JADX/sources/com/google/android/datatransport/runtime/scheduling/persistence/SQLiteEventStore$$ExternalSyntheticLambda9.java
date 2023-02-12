package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda9 implements SQLiteEventStore.Producer {
    public final /* synthetic */ SchemaManager f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda9(SchemaManager schemaManager) {
        this.f$0 = schemaManager;
    }

    public final Object produce() {
        return this.f$0.getWritableDatabase();
    }
}
