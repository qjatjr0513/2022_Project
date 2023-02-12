package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class SQLiteBundleCache$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ String f$0;

    public /* synthetic */ SQLiteBundleCache$$ExternalSyntheticLambda1(String str) {
        this.f$0 = str;
    }

    public final Object apply(Object obj) {
        return SQLiteBundleCache.lambda$getBundleMetadata$0(this.f$0, (Cursor) obj);
    }
}
