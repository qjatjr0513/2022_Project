package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

public final /* synthetic */ class SQLiteMutationQueue$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ SQLiteMutationQueue f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ SQLiteMutationQueue$$ExternalSyntheticLambda5(SQLiteMutationQueue sQLiteMutationQueue, List list) {
        this.f$0 = sQLiteMutationQueue;
        this.f$1 = list;
    }

    public final void accept(Object obj) {
        this.f$0.mo9315xb3096ab7(this.f$1, (Cursor) obj);
    }
}
