package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class TransportRuntime_Factory implements Factory<TransportRuntime> {
    private final Provider<Clock> eventClockProvider;
    private final Provider<WorkInitializer> initializerProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<Uploader> uploaderProvider;
    private final Provider<Clock> uptimeClockProvider;

    public TransportRuntime_Factory(Provider<Clock> eventClockProvider2, Provider<Clock> uptimeClockProvider2, Provider<Scheduler> schedulerProvider2, Provider<Uploader> uploaderProvider2, Provider<WorkInitializer> initializerProvider2) {
        this.eventClockProvider = eventClockProvider2;
        this.uptimeClockProvider = uptimeClockProvider2;
        this.schedulerProvider = schedulerProvider2;
        this.uploaderProvider = uploaderProvider2;
        this.initializerProvider = initializerProvider2;
    }

    public TransportRuntime get() {
        return newInstance(this.eventClockProvider.get(), this.uptimeClockProvider.get(), this.schedulerProvider.get(), this.uploaderProvider.get(), this.initializerProvider.get());
    }

    public static TransportRuntime_Factory create(Provider<Clock> eventClockProvider2, Provider<Clock> uptimeClockProvider2, Provider<Scheduler> schedulerProvider2, Provider<Uploader> uploaderProvider2, Provider<WorkInitializer> initializerProvider2) {
        return new TransportRuntime_Factory(eventClockProvider2, uptimeClockProvider2, schedulerProvider2, uploaderProvider2, initializerProvider2);
    }

    public static TransportRuntime newInstance(Clock eventClock, Clock uptimeClock, Scheduler scheduler, Uploader uploader, WorkInitializer initializer) {
        return new TransportRuntime(eventClock, uptimeClock, scheduler, uploader, initializer);
    }
}
