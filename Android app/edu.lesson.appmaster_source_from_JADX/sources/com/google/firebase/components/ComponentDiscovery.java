package com.google.firebase.components;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.inject.Provider;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ComponentDiscovery<T> {
    private static final String COMPONENT_KEY_PREFIX = "com.google.firebase.components:";
    private static final String COMPONENT_SENTINEL_VALUE = "com.google.firebase.components.ComponentRegistrar";
    static final String TAG = "ComponentDiscovery";
    private final T context;
    private final RegistrarNameRetriever<T> retriever;

    interface RegistrarNameRetriever<T> {
        List<String> retrieve(T t);
    }

    public static ComponentDiscovery<Context> forContext(Context context2, Class<? extends Service> discoveryService) {
        return new ComponentDiscovery<>(context2, new MetadataRegistrarNameRetriever(discoveryService));
    }

    ComponentDiscovery(T context2, RegistrarNameRetriever<T> retriever2) {
        this.context = context2;
        this.retriever = retriever2;
    }

    @Deprecated
    public List<ComponentRegistrar> discover() {
        List<ComponentRegistrar> result = new ArrayList<>();
        for (String registrarName : this.retriever.retrieve(this.context)) {
            try {
                ComponentRegistrar registrar = instantiate(registrarName);
                if (registrar != null) {
                    result.add(registrar);
                }
            } catch (InvalidRegistrarException ex) {
                Log.w(TAG, "Invalid component registrar.", ex);
            }
        }
        return result;
    }

    public List<Provider<ComponentRegistrar>> discoverLazy() {
        List<Provider<ComponentRegistrar>> result = new ArrayList<>();
        for (String registrarName : this.retriever.retrieve(this.context)) {
            result.add(new ComponentDiscovery$$ExternalSyntheticLambda0(registrarName));
        }
        return result;
    }

    /* access modifiers changed from: private */
    public static ComponentRegistrar instantiate(String registrarName) {
        try {
            Class<?> loadedClass = Class.forName(registrarName);
            if (ComponentRegistrar.class.isAssignableFrom(loadedClass)) {
                return (ComponentRegistrar) loadedClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            throw new InvalidRegistrarException(String.format("Class %s is not an instance of %s", new Object[]{registrarName, COMPONENT_SENTINEL_VALUE}));
        } catch (ClassNotFoundException e) {
            Log.w(TAG, String.format("Class %s is not an found.", new Object[]{registrarName}));
            return null;
        } catch (IllegalAccessException e2) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s.", new Object[]{registrarName}), e2);
        } catch (InstantiationException e3) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s.", new Object[]{registrarName}), e3);
        } catch (NoSuchMethodException e4) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s", new Object[]{registrarName}), e4);
        } catch (InvocationTargetException e5) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s", new Object[]{registrarName}), e5);
        }
    }

    private static class MetadataRegistrarNameRetriever implements RegistrarNameRetriever<Context> {
        private final Class<? extends Service> discoveryService;

        private MetadataRegistrarNameRetriever(Class<? extends Service> discoveryService2) {
            this.discoveryService = discoveryService2;
        }

        public List<String> retrieve(Context ctx) {
            Bundle metadata = getMetadata(ctx);
            if (metadata == null) {
                Log.w(ComponentDiscovery.TAG, "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            List<String> registrarNames = new ArrayList<>();
            for (String key : metadata.keySet()) {
                if (ComponentDiscovery.COMPONENT_SENTINEL_VALUE.equals(metadata.get(key)) && key.startsWith(ComponentDiscovery.COMPONENT_KEY_PREFIX)) {
                    registrarNames.add(key.substring(ComponentDiscovery.COMPONENT_KEY_PREFIX.length()));
                }
            }
            return registrarNames;
        }

        private Bundle getMetadata(Context context) {
            try {
                PackageManager manager = context.getPackageManager();
                if (manager == null) {
                    Log.w(ComponentDiscovery.TAG, "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo info = manager.getServiceInfo(new ComponentName(context, this.discoveryService), 128);
                if (info != null) {
                    return info.metaData;
                }
                Log.w(ComponentDiscovery.TAG, this.discoveryService + " has no service info.");
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(ComponentDiscovery.TAG, "Application info not found.");
                return null;
            }
        }
    }
}
