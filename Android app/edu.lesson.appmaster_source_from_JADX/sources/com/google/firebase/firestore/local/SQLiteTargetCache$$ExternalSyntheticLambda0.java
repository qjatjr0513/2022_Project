package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteTargetCache$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SQLiteTargetCache.DocumentKeysHolder f$0;

    public /* synthetic */ SQLiteTargetCache$$ExternalSyntheticLambda0(SQLiteTargetCache.DocumentKeysHolder documentKeysHolder) {
        this.f$0 = documentKeysHolder;
    }

    public final void accept(Object obj) {
        this.f$0.keys = this.f$0.keys.insert(DocumentKey.fromPath(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0))));
    }
}
