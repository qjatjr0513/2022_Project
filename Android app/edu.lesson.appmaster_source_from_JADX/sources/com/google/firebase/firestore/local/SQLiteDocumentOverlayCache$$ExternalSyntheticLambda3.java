package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import java.util.Map;

public final /* synthetic */ class SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ SQLiteDocumentOverlayCache f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ String[] f$3;
    public final /* synthetic */ BackgroundQueue f$4;
    public final /* synthetic */ Map f$5;

    public /* synthetic */ SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3(SQLiteDocumentOverlayCache sQLiteDocumentOverlayCache, int[] iArr, String[] strArr, String[] strArr2, BackgroundQueue backgroundQueue, Map map) {
        this.f$0 = sQLiteDocumentOverlayCache;
        this.f$1 = iArr;
        this.f$2 = strArr;
        this.f$3 = strArr2;
        this.f$4 = backgroundQueue;
        this.f$5 = map;
    }

    public final void accept(Object obj) {
        this.f$0.mo9302xa097d694(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (Cursor) obj);
    }
}
