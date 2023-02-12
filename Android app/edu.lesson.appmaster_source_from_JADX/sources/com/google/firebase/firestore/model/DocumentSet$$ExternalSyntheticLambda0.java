package com.google.firebase.firestore.model;

import java.util.Comparator;

public final /* synthetic */ class DocumentSet$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ Comparator f$0;

    public /* synthetic */ DocumentSet$$ExternalSyntheticLambda0(Comparator comparator) {
        this.f$0 = comparator;
    }

    public final int compare(Object obj, Object obj2) {
        return DocumentSet.lambda$emptySet$0(this.f$0, (Document) obj, (Document) obj2);
    }
}
