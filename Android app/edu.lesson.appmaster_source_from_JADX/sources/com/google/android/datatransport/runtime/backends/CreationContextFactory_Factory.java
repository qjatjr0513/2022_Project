package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class CreationContextFactory_Factory implements Factory<CreationContextFactory> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<Clock> monotonicClockProvider;
    private final Provider<Clock> wallClockProvider;

    public CreationContextFactory_Factory(Provider<Context> applicationContextProvider2, Provider<Clock> wallClockProvider2, Provider<Clock> monotonicClockProvider2) {
        this.applicationContextProvider = applicationContextProvider2;
        this.wallClockProvider = wallClockProvider2;
        this.monotonicClockProvider = monotonicClockProvider2;
    }

    public CreationContextFactory get() {
        return newInstance(this.applicationContextProvider.get(), this.wallClockProvider.get(), this.monotonicClockProvider.get());
    }

    public static CreationContextFactory_Factory create(Provider<Context> applicationContextProvider2, Provider<Clock> wallClockProvider2, Provider<Clock> monotonicClockProvider2) {
        return new CreationContextFactory_Factory(applicationContextProvider2, wallClockProvider2, monotonicClockProvider2);
    }

    public static CreationContextFactory newInstance(Context applicationContext, Clock wallClock, Clock monotonicClock) {
        return new CreationContextFactory(applicationContext, wallClock, monotonicClock);
    }
}
