package com.google.android.datatransport.runtime.backends;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class MetadataBackendRegistry implements BackendRegistry {
    private static final String BACKEND_KEY_PREFIX = "backend:";
    private static final String TAG = "BackendRegistry";
    private final BackendFactoryProvider backendFactoryProvider;
    private final Map<String, TransportBackend> backends;
    private final CreationContextFactory creationContextFactory;

    @Inject
    MetadataBackendRegistry(Context applicationContext, CreationContextFactory creationContextFactory2) {
        this(new BackendFactoryProvider(applicationContext), creationContextFactory2);
    }

    MetadataBackendRegistry(BackendFactoryProvider backendFactoryProvider2, CreationContextFactory creationContextFactory2) {
        this.backends = new HashMap();
        this.backendFactoryProvider = backendFactoryProvider2;
        this.creationContextFactory = creationContextFactory2;
    }

    public synchronized TransportBackend get(String name) {
        if (this.backends.containsKey(name)) {
            return this.backends.get(name);
        }
        BackendFactory factory = this.backendFactoryProvider.get(name);
        if (factory == null) {
            return null;
        }
        TransportBackend backend = factory.create(this.creationContextFactory.create(name));
        this.backends.put(name, backend);
        return backend;
    }

    static class BackendFactoryProvider {
        private final Context applicationContext;
        private Map<String, String> backendProviders = null;

        BackendFactoryProvider(Context applicationContext2) {
            this.applicationContext = applicationContext2;
        }

        /* access modifiers changed from: package-private */
        public BackendFactory get(String name) {
            String backendProviderName = getBackendProviders().get(name);
            if (backendProviderName == null) {
                return null;
            }
            try {
                return (BackendFactory) Class.forName(backendProviderName).asSubclass(BackendFactory.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e) {
                Log.w(MetadataBackendRegistry.TAG, String.format("Class %s is not found.", new Object[]{backendProviderName}), e);
                return null;
            } catch (IllegalAccessException e2) {
                Log.w(MetadataBackendRegistry.TAG, String.format("Could not instantiate %s.", new Object[]{backendProviderName}), e2);
                return null;
            } catch (InstantiationException e3) {
                Log.w(MetadataBackendRegistry.TAG, String.format("Could not instantiate %s.", new Object[]{backendProviderName}), e3);
                return null;
            } catch (NoSuchMethodException e4) {
                Log.w(MetadataBackendRegistry.TAG, String.format("Could not instantiate %s", new Object[]{backendProviderName}), e4);
                return null;
            } catch (InvocationTargetException e5) {
                Log.w(MetadataBackendRegistry.TAG, String.format("Could not instantiate %s", new Object[]{backendProviderName}), e5);
                return null;
            }
        }

        private Map<String, String> getBackendProviders() {
            if (this.backendProviders == null) {
                this.backendProviders = discover(this.applicationContext);
            }
            return this.backendProviders;
        }

        private Map<String, String> discover(Context ctx) {
            Bundle metadata = getMetadata(ctx);
            if (metadata == null) {
                Log.w(MetadataBackendRegistry.TAG, "Could not retrieve metadata, returning empty list of transport backends.");
                return Collections.emptyMap();
            }
            Map<String, String> backendNames = new HashMap<>();
            for (String key : metadata.keySet()) {
                Object rawValue = metadata.get(key);
                if ((rawValue instanceof String) && key.startsWith(MetadataBackendRegistry.BACKEND_KEY_PREFIX)) {
                    for (String name : ((String) rawValue).split(",", -1)) {
                        String name2 = name.trim();
                        if (!name2.isEmpty()) {
                            backendNames.put(name2, key.substring(MetadataBackendRegistry.BACKEND_KEY_PREFIX.length()));
                        }
                    }
                }
            }
            return backendNames;
        }

        private static Bundle getMetadata(Context context) {
            try {
                PackageManager manager = context.getPackageManager();
                if (manager == null) {
                    Log.w(MetadataBackendRegistry.TAG, "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo info = manager.getServiceInfo(new ComponentName(context, TransportBackendDiscovery.class), 128);
                if (info != null) {
                    return info.metaData;
                }
                Log.w(MetadataBackendRegistry.TAG, "TransportBackendDiscovery has no service info.");
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(MetadataBackendRegistry.TAG, "Application info not found.");
                return null;
            }
        }
    }
}
