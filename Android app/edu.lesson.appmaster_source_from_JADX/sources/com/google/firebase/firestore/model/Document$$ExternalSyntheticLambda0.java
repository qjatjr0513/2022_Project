package com.google.firebase.firestore.model;

import java.util.Comparator;

public final /* synthetic */ class Document$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ Document$$ExternalSyntheticLambda0 INSTANCE = new Document$$ExternalSyntheticLambda0();

    private /* synthetic */ Document$$ExternalSyntheticLambda0() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((Document) obj).getKey().compareTo(((Document) obj2).getKey());
    }
}
