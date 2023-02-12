package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.ArrayList;

public final /* synthetic */ class SQLiteIndexManager$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda3(ArrayList arrayList) {
        this.f$0 = arrayList;
    }

    public final void accept(Object obj) {
        this.f$0.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
    }
}
