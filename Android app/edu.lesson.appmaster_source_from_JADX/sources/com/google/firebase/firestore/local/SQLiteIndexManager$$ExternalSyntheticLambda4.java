package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

public final /* synthetic */ class SQLiteIndexManager$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda4(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add(DocumentKey.fromPath(ResourcePath.fromString(((Cursor) obj).getString(0))));
    }
}
