package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class SQLitePersistence$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ SQLitePersistence$$ExternalSyntheticLambda0 INSTANCE = new SQLitePersistence$$ExternalSyntheticLambda0();

    private /* synthetic */ SQLitePersistence$$ExternalSyntheticLambda0() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}
