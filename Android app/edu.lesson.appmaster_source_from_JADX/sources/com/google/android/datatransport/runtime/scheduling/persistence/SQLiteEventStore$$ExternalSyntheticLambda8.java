package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda8 implements SQLiteEventStore.Producer {
    public final /* synthetic */ SQLiteDatabase f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda8(SQLiteDatabase sQLiteDatabase) {
        this.f$0 = sQLiteDatabase;
    }

    public final Object produce() {
        return this.f$0.beginTransaction();
    }
}
