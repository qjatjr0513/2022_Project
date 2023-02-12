package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

public final /* synthetic */ class SQLiteMutationQueue$$ExternalSyntheticLambda9 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ SQLiteMutationQueue$$ExternalSyntheticLambda9(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
    }
}
