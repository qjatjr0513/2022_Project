package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import java.util.Map;

public final /* synthetic */ class SQLiteDocumentOverlayCache$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ SQLiteDocumentOverlayCache f$0;
    public final /* synthetic */ BackgroundQueue f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ SQLiteDocumentOverlayCache$$ExternalSyntheticLambda2(SQLiteDocumentOverlayCache sQLiteDocumentOverlayCache, BackgroundQueue backgroundQueue, Map map) {
        this.f$0 = sQLiteDocumentOverlayCache;
        this.f$1 = backgroundQueue;
        this.f$2 = map;
    }

    public final void accept(Object obj) {
        this.f$0.mo9305x634144da(this.f$1, this.f$2, (Cursor) obj);
    }
}
