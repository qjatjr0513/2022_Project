package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.inject.Inject;

public class DefaultScheduler implements Scheduler {
    private static final Logger LOGGER = Logger.getLogger(TransportRuntime.class.getName());
    private final BackendRegistry backendRegistry;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler workScheduler;

    @Inject
    public DefaultScheduler(Executor executor2, BackendRegistry backendRegistry2, WorkScheduler workScheduler2, EventStore eventStore2, SynchronizationGuard guard2) {
        this.executor = executor2;
        this.backendRegistry = backendRegistry2;
        this.workScheduler = workScheduler2;
        this.eventStore = eventStore2;
        this.guard = guard2;
    }

    public void schedule(TransportContext transportContext, EventInternal event, TransportScheduleCallback callback) {
        this.executor.execute(new DefaultScheduler$$ExternalSyntheticLambda1(this, transportContext, callback, event));
    }

    /* renamed from: lambda$schedule$1$com-google-android-datatransport-runtime-scheduling-DefaultScheduler */
    public /* synthetic */ void mo30411x41d0caed(TransportContext transportContext, TransportScheduleCallback callback, EventInternal event) {
        try {
            TransportBackend transportBackend = this.backendRegistry.get(transportContext.getBackendName());
            if (transportBackend == null) {
                String errorMsg = String.format("Transport backend '%s' is not registered", new Object[]{transportContext.getBackendName()});
                LOGGER.warning(errorMsg);
                callback.onSchedule(new IllegalArgumentException(errorMsg));
                return;
            }
            this.guard.runCriticalSection(new DefaultScheduler$$ExternalSyntheticLambda0(this, transportContext, transportBackend.decorate(event)));
            callback.onSchedule((Exception) null);
        } catch (Exception e) {
            LOGGER.warning("Error scheduling event " + e.getMessage());
            callback.onSchedule(e);
        }
    }

    /* renamed from: lambda$schedule$0$com-google-android-datatransport-runtime-scheduling-DefaultScheduler */
    public /* synthetic */ Object mo30410x8f06a4e(TransportContext transportContext, EventInternal decoratedEvent) {
        this.eventStore.persist(transportContext, decoratedEvent);
        this.workScheduler.schedule(transportContext, 1);
        return null;
    }
}
