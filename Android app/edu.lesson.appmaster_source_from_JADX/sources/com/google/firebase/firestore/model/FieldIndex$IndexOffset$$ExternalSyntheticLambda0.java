package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.Comparator;

public final /* synthetic */ class FieldIndex$IndexOffset$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ FieldIndex$IndexOffset$$ExternalSyntheticLambda0 INSTANCE = new FieldIndex$IndexOffset$$ExternalSyntheticLambda0();

    private /* synthetic */ FieldIndex$IndexOffset$$ExternalSyntheticLambda0() {
    }

    public final int compare(Object obj, Object obj2) {
        return FieldIndex.IndexOffset.fromDocument((MutableDocument) obj).compareTo(FieldIndex.IndexOffset.fromDocument((MutableDocument) obj2));
    }
}
