package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteTargetCache$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ SQLiteTargetCache f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ SQLiteTargetCache$$ExternalSyntheticLambda4(SQLiteTargetCache sQLiteTargetCache, Consumer consumer) {
        this.f$0 = sQLiteTargetCache;
        this.f$1 = consumer;
    }

    public final void accept(Object obj) {
        this.f$0.mo9380x1515438a(this.f$1, (Cursor) obj);
    }
}
