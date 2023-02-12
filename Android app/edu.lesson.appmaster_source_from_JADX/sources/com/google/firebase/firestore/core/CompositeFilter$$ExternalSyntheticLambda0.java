package com.google.firebase.firestore.core;

import com.google.firebase.firestore.util.Function;

public final /* synthetic */ class CompositeFilter$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ CompositeFilter$$ExternalSyntheticLambda0 INSTANCE = new CompositeFilter$$ExternalSyntheticLambda0();

    private /* synthetic */ CompositeFilter$$ExternalSyntheticLambda0() {
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((FieldFilter) obj).isInequality());
    }
}
