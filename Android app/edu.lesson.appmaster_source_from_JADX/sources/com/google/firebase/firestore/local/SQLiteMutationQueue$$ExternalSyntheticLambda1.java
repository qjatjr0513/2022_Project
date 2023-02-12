package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class SQLiteMutationQueue$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ SQLiteMutationQueue$$ExternalSyntheticLambda1 INSTANCE = new SQLiteMutationQueue$$ExternalSyntheticLambda1();

    private /* synthetic */ SQLiteMutationQueue$$ExternalSyntheticLambda1() {
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((Cursor) obj).getInt(0));
    }
}
