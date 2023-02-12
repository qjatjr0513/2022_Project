package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda13 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ EventInternal f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda13(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, EventInternal eventInternal) {
        this.f$0 = sQLiteEventStore;
        this.f$1 = transportContext;
        this.f$2 = eventInternal;
    }

    public final Object apply(Object obj) {
        return this.f$0.mo30499x42ac2bf1(this.f$1, this.f$2, (SQLiteDatabase) obj);
    }
}
