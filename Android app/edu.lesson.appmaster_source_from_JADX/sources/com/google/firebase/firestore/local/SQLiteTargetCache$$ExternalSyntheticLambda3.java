package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteTargetCache$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ SQLiteTargetCache f$0;
    public final /* synthetic */ Target f$1;
    public final /* synthetic */ SQLiteTargetCache.TargetDataHolder f$2;

    public /* synthetic */ SQLiteTargetCache$$ExternalSyntheticLambda3(SQLiteTargetCache sQLiteTargetCache, Target target, SQLiteTargetCache.TargetDataHolder targetDataHolder) {
        this.f$0 = sQLiteTargetCache;
        this.f$1 = target;
        this.f$2 = targetDataHolder;
    }

    public final void accept(Object obj) {
        this.f$0.mo9381x4f05f442(this.f$1, this.f$2, (Cursor) obj);
    }
}
