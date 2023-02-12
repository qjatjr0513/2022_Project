package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TransportRuntime implements TransportInternal {
    private static volatile TransportRuntimeComponent instance = null;
    private final Clock eventClock;
    private final Scheduler scheduler;
    private final Uploader uploader;
    private final Clock uptimeClock;

    @Inject
    TransportRuntime(Clock eventClock2, Clock uptimeClock2, Scheduler scheduler2, Uploader uploader2, WorkInitializer initializer) {
        this.eventClock = eventClock2;
        this.uptimeClock = uptimeClock2;
        this.scheduler = scheduler2;
        this.uploader = uploader2;
        initializer.ensureContextsScheduled();
    }

    public static void initialize(Context applicationContext) {
        if (instance == null) {
            synchronized (TransportRuntime.class) {
                if (instance == null) {
                    instance = DaggerTransportRuntimeComponent.builder().setApplicationContext(applicationContext).build();
                }
            }
        }
    }

    public static TransportRuntime getInstance() {
        TransportRuntimeComponent localRef = instance;
        if (localRef != null) {
            return localRef.getTransportRuntime();
        }
        throw new IllegalStateException("Not initialized!");
    }

    static void withInstance(TransportRuntimeComponent component, Callable<Void> callable) throws Throwable {
        TransportRuntimeComponent original;
        Class<TransportRuntime> cls = TransportRuntime.class;
        synchronized (cls) {
            original = instance;
            instance = component;
        }
        try {
            callable.call();
            synchronized (cls) {
                instance = original;
            }
        } catch (Throwable th) {
            synchronized (cls) {
                instance = original;
                throw th;
            }
        }
    }

    @Deprecated
    public TransportFactory newFactory(String backendName) {
        return new TransportFactoryImpl(getSupportedEncodings((Destination) null), TransportContext.builder().setBackendName(backendName).build(), this);
    }

    public TransportFactory newFactory(Destination destination) {
        return new TransportFactoryImpl(getSupportedEncodings(destination), TransportContext.builder().setBackendName(destination.getName()).setExtras(destination.getExtras()).build(), this);
    }

    private static Set<Encoding> getSupportedEncodings(Destination destination) {
        if (destination instanceof EncodedDestination) {
            return Collections.unmodifiableSet(((EncodedDestination) destination).getSupportedEncodings());
        }
        return Collections.singleton(Encoding.m395of("proto"));
    }

    public Uploader getUploader() {
        return this.uploader;
    }

    public void send(SendRequest request, TransportScheduleCallback callback) {
        this.scheduler.schedule(request.getTransportContext().withPriority(request.getEvent().getPriority()), convert(request), callback);
    }

    private EventInternal convert(SendRequest request) {
        return EventInternal.builder().setEventMillis(this.eventClock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName(request.getTransportName()).setEncodedPayload(new EncodedPayload(request.getEncoding(), request.getPayload())).setCode(request.getEvent().getCode()).build();
    }
}
