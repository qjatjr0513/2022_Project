package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda3 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ EventStore f$0;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda3(EventStore eventStore) {
        this.f$0 = eventStore;
    }

    public final Object execute() {
        return Integer.valueOf(this.f$0.cleanUp());
    }
}
