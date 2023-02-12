package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.List;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda14 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ TransportContext f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda14(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext) {
        this.f$0 = sQLiteEventStore;
        this.f$1 = list;
        this.f$2 = transportContext;
    }

    public final Object apply(Object obj) {
        return this.f$0.mo30498xbdd0a62c(this.f$1, this.f$2, (Cursor) obj);
    }
}
