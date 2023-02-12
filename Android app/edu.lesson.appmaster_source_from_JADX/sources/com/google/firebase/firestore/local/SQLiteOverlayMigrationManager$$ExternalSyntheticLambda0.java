package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.Set;

public final /* synthetic */ class SQLiteOverlayMigrationManager$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Set f$0;

    public /* synthetic */ SQLiteOverlayMigrationManager$$ExternalSyntheticLambda0(Set set) {
        this.f$0 = set;
    }

    public final void accept(Object obj) {
        this.f$0.add(((Cursor) obj).getString(0));
    }
}
