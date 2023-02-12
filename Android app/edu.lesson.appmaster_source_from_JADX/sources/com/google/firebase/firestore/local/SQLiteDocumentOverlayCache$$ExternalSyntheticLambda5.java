package com.google.firebase.firestore.local;

import java.util.Map;

public final /* synthetic */ class SQLiteDocumentOverlayCache$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ SQLiteDocumentOverlayCache f$0;
    public final /* synthetic */ byte[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Map f$3;

    public /* synthetic */ SQLiteDocumentOverlayCache$$ExternalSyntheticLambda5(SQLiteDocumentOverlayCache sQLiteDocumentOverlayCache, byte[] bArr, int i, Map map) {
        this.f$0 = sQLiteDocumentOverlayCache;
        this.f$1 = bArr;
        this.f$2 = i;
        this.f$3 = map;
    }

    public final void run() {
        this.f$0.mo9304x1d10fe66(this.f$1, this.f$2, this.f$3);
    }
}
