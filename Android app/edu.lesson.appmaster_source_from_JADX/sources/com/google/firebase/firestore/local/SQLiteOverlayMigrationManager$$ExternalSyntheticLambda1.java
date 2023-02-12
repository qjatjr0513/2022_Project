package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class SQLiteOverlayMigrationManager$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ Boolean[] f$0;

    public /* synthetic */ SQLiteOverlayMigrationManager$$ExternalSyntheticLambda1(Boolean[] boolArr) {
        this.f$0 = boolArr;
    }

    public final void accept(Object obj) {
        SQLiteOverlayMigrationManager.lambda$hasPendingOverlayMigration$2(this.f$0, (Cursor) obj);
    }
}
