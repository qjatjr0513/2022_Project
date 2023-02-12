package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

public final /* synthetic */ class SQLiteMutationQueue$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ SQLiteMutationQueue$$ExternalSyntheticLambda8(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add(((Cursor) obj).getString(0));
    }
}
