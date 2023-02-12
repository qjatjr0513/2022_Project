package com.google.firebase.firestore.util;

import java.util.Comparator;
import java.util.Map;

public final /* synthetic */ class Util$$ExternalSyntheticLambda3 implements Comparator {
    public final /* synthetic */ Comparator f$0;

    public /* synthetic */ Util$$ExternalSyntheticLambda3(Comparator comparator) {
        this.f$0 = comparator;
    }

    public final int compare(Object obj, Object obj2) {
        return this.f$0.compare(((Map.Entry) obj).getValue(), ((Map.Entry) obj2).getValue());
    }
}
