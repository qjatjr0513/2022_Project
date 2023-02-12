package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class SQLiteSchema$$ExternalSyntheticLambda17 implements Function {
    public static final /* synthetic */ SQLiteSchema$$ExternalSyntheticLambda17 INSTANCE = new SQLiteSchema$$ExternalSyntheticLambda17();

    private /* synthetic */ SQLiteSchema$$ExternalSyntheticLambda17() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}
