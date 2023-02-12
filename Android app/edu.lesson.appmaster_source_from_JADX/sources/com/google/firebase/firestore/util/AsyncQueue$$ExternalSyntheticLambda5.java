package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.util.Comparator;

public final /* synthetic */ class AsyncQueue$$ExternalSyntheticLambda5 implements Comparator {
    public static final /* synthetic */ AsyncQueue$$ExternalSyntheticLambda5 INSTANCE = new AsyncQueue$$ExternalSyntheticLambda5();

    private /* synthetic */ AsyncQueue$$ExternalSyntheticLambda5() {
    }

    public final int compare(Object obj, Object obj2) {
        return Long.compare(((AsyncQueue.DelayedTask) obj).targetTimeMs, ((AsyncQueue.DelayedTask) obj2).targetTimeMs);
    }
}
