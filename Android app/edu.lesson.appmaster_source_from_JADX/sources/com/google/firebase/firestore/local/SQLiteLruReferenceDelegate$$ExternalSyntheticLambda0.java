package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

public final /* synthetic */ class SQLiteLruReferenceDelegate$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SQLiteLruReferenceDelegate f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ List f$2;

    public /* synthetic */ SQLiteLruReferenceDelegate$$ExternalSyntheticLambda0(SQLiteLruReferenceDelegate sQLiteLruReferenceDelegate, int[] iArr, List list) {
        this.f$0 = sQLiteLruReferenceDelegate;
        this.f$1 = iArr;
        this.f$2 = list;
    }

    public final void accept(Object obj) {
        this.f$0.mo9312x5fd3c655(this.f$1, this.f$2, (Cursor) obj);
    }
}
