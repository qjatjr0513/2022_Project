package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.inject.Inject;

public class Uploader {
    private static final String LOG_TAG = "Uploader";
    private final BackendRegistry backendRegistry;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler workScheduler;

    @Inject
    public Uploader(Context context2, BackendRegistry backendRegistry2, EventStore eventStore2, WorkScheduler workScheduler2, Executor executor2, SynchronizationGuard guard2, Clock clock2) {
        this.context = context2;
        this.backendRegistry = backendRegistry2;
        this.eventStore = eventStore2;
        this.workScheduler = workScheduler2;
        this.executor = executor2;
        this.guard = guard2;
        this.clock = clock2;
    }

    /* access modifiers changed from: package-private */
    public boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void upload(TransportContext transportContext, int attemptNumber, Runnable callback) {
        this.executor.execute(new Uploader$$ExternalSyntheticLambda4(this, transportContext, attemptNumber, callback));
    }

    /* renamed from: lambda$upload$1$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    public /* synthetic */ void mo30451x80c37673(TransportContext transportContext, int attemptNumber, Runnable callback) {
        try {
            SynchronizationGuard synchronizationGuard = this.guard;
            EventStore eventStore2 = this.eventStore;
            Objects.requireNonNull(eventStore2);
            synchronizationGuard.runCriticalSection(new Uploader$$ExternalSyntheticLambda3(eventStore2));
            if (!isNetworkAvailable()) {
                this.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda1(this, transportContext, attemptNumber));
            } else {
                logAndUpdateState(transportContext, attemptNumber);
            }
        } catch (SynchronizationException e) {
            this.workScheduler.schedule(transportContext, attemptNumber + 1);
        } catch (Throwable th) {
            callback.run();
            throw th;
        }
        callback.run();
    }

    /* renamed from: lambda$upload$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    public /* synthetic */ Object mo30450x3eac4914(TransportContext transportContext, int attemptNumber) {
        this.workScheduler.schedule(transportContext, attemptNumber + 1);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void logAndUpdateState(TransportContext transportContext, int attemptNumber) {
        BackendResponse response;
        TransportBackend backend = this.backendRegistry.get(transportContext.getBackendName());
        Iterable<PersistedEvent> persistedEvents = (Iterable) this.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda0(this, transportContext));
        if (persistedEvents.iterator().hasNext()) {
            if (backend == null) {
                Logging.m398d(LOG_TAG, "Unknown backend for %s, deleting event batch for it...", (Object) transportContext);
                response = BackendResponse.fatalError();
            } else {
                List<EventInternal> eventInternals = new ArrayList<>();
                for (PersistedEvent persistedEvent : persistedEvents) {
                    eventInternals.add(persistedEvent.getEvent());
                }
                response = backend.send(BackendRequest.builder().setEvents(eventInternals).setExtras(transportContext.getExtras()).build());
            }
            this.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda2(this, response, persistedEvents, transportContext, attemptNumber));
        }
    }

    /* renamed from: lambda$logAndUpdateState$2$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    public /* synthetic */ Iterable mo30448x65f78bd8(TransportContext transportContext) {
        return this.eventStore.loadBatch(transportContext);
    }

    /* renamed from: lambda$logAndUpdateState$3$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    public /* synthetic */ Object mo30449xa80eb937(BackendResponse response, Iterable persistedEvents, TransportContext transportContext, int attemptNumber) {
        if (response.getStatus() == BackendResponse.Status.TRANSIENT_ERROR) {
            this.eventStore.recordFailure(persistedEvents);
            this.workScheduler.schedule(transportContext, attemptNumber + 1);
            return null;
        }
        this.eventStore.recordSuccess(persistedEvents);
        if (response.getStatus() == BackendResponse.Status.OK) {
            this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + response.getNextRequestWaitMillis());
        }
        if (!this.eventStore.hasPendingEventsFor(transportContext)) {
            return null;
        }
        this.workScheduler.schedule(transportContext, 1, true);
        return null;
    }
}
