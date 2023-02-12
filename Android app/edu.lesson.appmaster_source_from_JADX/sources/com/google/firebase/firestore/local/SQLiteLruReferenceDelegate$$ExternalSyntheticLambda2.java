package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2 implements Function {
    public static final /* synthetic */ SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2 INSTANCE = new SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2();

    private /* synthetic */ SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2() {
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((Cursor) obj).getLong(0));
    }
}
