package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

public final /* synthetic */ class Uploader$$ExternalSyntheticLambda1 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda1(Uploader uploader, TransportContext transportContext, int i) {
        this.f$0 = uploader;
        this.f$1 = transportContext;
        this.f$2 = i;
    }

    public final Object execute() {
        return this.f$0.mo30450x3eac4914(this.f$1, this.f$2);
    }
}
