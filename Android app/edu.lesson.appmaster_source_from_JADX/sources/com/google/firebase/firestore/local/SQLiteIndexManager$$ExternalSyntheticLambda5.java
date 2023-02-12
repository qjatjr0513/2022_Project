package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Consumer;
import java.util.Map;

public final /* synthetic */ class SQLiteIndexManager$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda5(Map map) {
        this.f$0 = map;
    }

    public final void accept(Object obj) {
        this.f$0.put(Integer.valueOf(((Cursor) obj).getInt(0)), FieldIndex.IndexState.create(((Cursor) obj).getLong(1), new SnapshotVersion(new Timestamp(((Cursor) obj).getLong(2), ((Cursor) obj).getInt(3))), DocumentKey.fromPath(EncodedPath.decodeResourcePath(((Cursor) obj).getString(4))), ((Cursor) obj).getInt(5)));
    }
}
