package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda16 implements SQLiteEventStore.Function {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda16(Map map) {
        this.f$0 = map;
    }

    public final Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadMetadata$14(this.f$0, (Cursor) obj);
    }
}
