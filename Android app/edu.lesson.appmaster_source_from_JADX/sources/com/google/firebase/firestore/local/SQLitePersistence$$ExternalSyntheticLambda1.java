package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class SQLitePersistence$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ SQLitePersistence$$ExternalSyntheticLambda1 INSTANCE = new SQLitePersistence$$ExternalSyntheticLambda1();

    private /* synthetic */ SQLitePersistence$$ExternalSyntheticLambda1() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}
