package com.google.firebase.firestore.local;

import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.util.Consumer;

public final /* synthetic */ class LruGarbageCollector$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ LruGarbageCollector.RollingSequenceNumberBuffer f$0;

    public /* synthetic */ LruGarbageCollector$$ExternalSyntheticLambda1(LruGarbageCollector.RollingSequenceNumberBuffer rollingSequenceNumberBuffer) {
        this.f$0 = rollingSequenceNumberBuffer;
    }

    public final void accept(Object obj) {
        this.f$0.addElement((Long) obj);
    }
}
