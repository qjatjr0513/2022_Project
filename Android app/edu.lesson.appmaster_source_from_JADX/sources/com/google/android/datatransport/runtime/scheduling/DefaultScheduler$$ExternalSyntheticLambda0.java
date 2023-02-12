package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class DefaultScheduler$$ExternalSyntheticLambda0 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ DefaultScheduler f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ EventInternal f$2;

    public /* synthetic */ DefaultScheduler$$ExternalSyntheticLambda0(DefaultScheduler defaultScheduler, TransportContext transportContext, EventInternal eventInternal) {
        this.f$0 = defaultScheduler;
        this.f$1 = transportContext;
        this.f$2 = eventInternal;
    }

    public final Object execute() {
        return this.f$0.mo30410x8f06a4e(this.f$1, this.f$2);
    }
}
