package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<CreationContextFactory> creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(Provider<Context> applicationContextProvider2, Provider<CreationContextFactory> creationContextFactoryProvider2) {
        this.applicationContextProvider = applicationContextProvider2;
        this.creationContextFactoryProvider = creationContextFactoryProvider2;
    }

    public MetadataBackendRegistry get() {
        return newInstance(this.applicationContextProvider.get(), this.creationContextFactoryProvider.get());
    }

    public static MetadataBackendRegistry_Factory create(Provider<Context> applicationContextProvider2, Provider<CreationContextFactory> creationContextFactoryProvider2) {
        return new MetadataBackendRegistry_Factory(applicationContextProvider2, creationContextFactoryProvider2);
    }

    public static MetadataBackendRegistry newInstance(Context applicationContext, Object creationContextFactory) {
        return new MetadataBackendRegistry(applicationContext, (CreationContextFactory) creationContextFactory);
    }
}
