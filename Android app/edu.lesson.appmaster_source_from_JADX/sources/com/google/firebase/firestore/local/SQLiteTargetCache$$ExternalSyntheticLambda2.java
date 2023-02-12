package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.util.SparseArray;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteTargetCache$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ SQLiteTargetCache f$0;
    public final /* synthetic */ SparseArray f$1;
    public final /* synthetic */ int[] f$2;

    public /* synthetic */ SQLiteTargetCache$$ExternalSyntheticLambda2(SQLiteTargetCache sQLiteTargetCache, SparseArray sparseArray, int[] iArr) {
        this.f$0 = sQLiteTargetCache;
        this.f$1 = sparseArray;
        this.f$2 = iArr;
    }

    public final void accept(Object obj) {
        this.f$0.mo9382x1041d572(this.f$1, this.f$2, (Cursor) obj);
    }
}
