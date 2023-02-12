package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteSchema$$ExternalSyntheticLambda16 implements Consumer {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ SQLiteStatement f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SQLiteSchema$$ExternalSyntheticLambda16(boolean[] zArr, SQLiteStatement sQLiteStatement, long j) {
        this.f$0 = zArr;
        this.f$1 = sQLiteStatement;
        this.f$2 = j;
    }

    public final void accept(Object obj) {
        SQLiteSchema.lambda$ensureSequenceNumbers$8(this.f$0, this.f$1, this.f$2, (Cursor) obj);
    }
}
