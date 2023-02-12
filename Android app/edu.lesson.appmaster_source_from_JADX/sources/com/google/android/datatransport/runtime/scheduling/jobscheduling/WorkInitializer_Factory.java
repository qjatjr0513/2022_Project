package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

public final class WorkInitializer_Factory implements Factory<WorkInitializer> {
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> schedulerProvider;
    private final Provider<EventStore> storeProvider;

    public WorkInitializer_Factory(Provider<Executor> executorProvider2, Provider<EventStore> storeProvider2, Provider<WorkScheduler> schedulerProvider2, Provider<SynchronizationGuard> guardProvider2) {
        this.executorProvider = executorProvider2;
        this.storeProvider = storeProvider2;
        this.schedulerProvider = schedulerProvider2;
        this.guardProvider = guardProvider2;
    }

    public WorkInitializer get() {
        return newInstance(this.executorProvider.get(), this.storeProvider.get(), this.schedulerProvider.get(), this.guardProvider.get());
    }

    public static WorkInitializer_Factory create(Provider<Executor> executorProvider2, Provider<EventStore> storeProvider2, Provider<WorkScheduler> schedulerProvider2, Provider<SynchronizationGuard> guardProvider2) {
        return new WorkInitializer_Factory(executorProvider2, storeProvider2, schedulerProvider2, guardProvider2);
    }

    public static WorkInitializer newInstance(Executor executor, EventStore store, WorkScheduler scheduler, SynchronizationGuard guard) {
        return new WorkInitializer(executor, store, scheduler, guard);
    }
}
