package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

public final /* synthetic */ class DefaultScheduler$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DefaultScheduler f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ TransportScheduleCallback f$2;
    public final /* synthetic */ EventInternal f$3;

    public /* synthetic */ DefaultScheduler$$ExternalSyntheticLambda1(DefaultScheduler defaultScheduler, TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        this.f$0 = defaultScheduler;
        this.f$1 = transportContext;
        this.f$2 = transportScheduleCallback;
        this.f$3 = eventInternal;
    }

    public final void run() {
        this.f$0.mo30411x41d0caed(this.f$1, this.f$2, this.f$3);
    }
}
