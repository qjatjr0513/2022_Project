package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {
    private final Provider<Clock> clockProvider;
    private final Provider<SchedulerConfig> configProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(Provider<Context> contextProvider2, Provider<EventStore> eventStoreProvider2, Provider<SchedulerConfig> configProvider2, Provider<Clock> clockProvider2) {
        this.contextProvider = contextProvider2;
        this.eventStoreProvider = eventStoreProvider2;
        this.configProvider = configProvider2;
        this.clockProvider = clockProvider2;
    }

    public WorkScheduler get() {
        return workScheduler(this.contextProvider.get(), this.eventStoreProvider.get(), this.configProvider.get(), this.clockProvider.get());
    }

    public static SchedulingModule_WorkSchedulerFactory create(Provider<Context> contextProvider2, Provider<EventStore> eventStoreProvider2, Provider<SchedulerConfig> configProvider2, Provider<Clock> clockProvider2) {
        return new SchedulingModule_WorkSchedulerFactory(contextProvider2, eventStoreProvider2, configProvider2, clockProvider2);
    }

    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig config, Clock clock) {
        return (WorkScheduler) Preconditions.checkNotNull(SchedulingModule.workScheduler(context, eventStore, config, clock), "Cannot return null from a non-@Nullable @Provides method");
    }
}
