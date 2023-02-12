package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.Consumer;
import java.util.SortedSet;

public final /* synthetic */ class SQLiteIndexManager$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ SortedSet f$0;
    public final /* synthetic */ FieldIndex f$1;
    public final /* synthetic */ DocumentKey f$2;

    public /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda6(SortedSet sortedSet, FieldIndex fieldIndex, DocumentKey documentKey) {
        this.f$0 = sortedSet;
        this.f$1 = fieldIndex;
        this.f$2 = documentKey;
    }

    public final void accept(Object obj) {
        this.f$0.add(IndexEntry.create(this.f$1.getIndexId(), this.f$2, ((Cursor) obj).getBlob(0), ((Cursor) obj).getBlob(1)));
    }
}
