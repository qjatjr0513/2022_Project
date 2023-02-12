package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

public final /* synthetic */ class SQLitePersistence$Query$$ExternalSyntheticLambda0 implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ Object[] f$0;

    public /* synthetic */ SQLitePersistence$Query$$ExternalSyntheticLambda0(Object[] objArr) {
        this.f$0 = objArr;
    }

    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        return SQLitePersistence.bind(sQLiteQuery, this.f$0);
    }
}
