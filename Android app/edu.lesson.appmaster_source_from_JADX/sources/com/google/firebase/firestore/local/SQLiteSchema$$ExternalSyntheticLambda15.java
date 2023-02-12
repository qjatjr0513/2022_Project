package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteSchema$$ExternalSyntheticLambda15 implements Consumer {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ SQLiteStatement f$1;

    public /* synthetic */ SQLiteSchema$$ExternalSyntheticLambda15(boolean[] zArr, SQLiteStatement sQLiteStatement) {
        this.f$0 = zArr;
        this.f$1 = sQLiteStatement;
    }

    public final void accept(Object obj) {
        SQLiteSchema.lambda$ensurePathLength$14(this.f$0, this.f$1, (Cursor) obj);
    }
}
