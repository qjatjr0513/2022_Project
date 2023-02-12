package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.Comparator;

public final /* synthetic */ class SQLiteIndexManager$$ExternalSyntheticLambda7 implements Comparator {
    public static final /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda7 INSTANCE = new SQLiteIndexManager$$ExternalSyntheticLambda7();

    private /* synthetic */ SQLiteIndexManager$$ExternalSyntheticLambda7() {
    }

    public final int compare(Object obj, Object obj2) {
        return Integer.compare(((FieldIndex) obj).getSegments().size(), ((FieldIndex) obj2).getSegments().size());
    }
}
