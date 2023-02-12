package p004io.grpc;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.ServiceProviders;

/* renamed from: io.grpc.LoadBalancerRegistry */
public final class LoadBalancerRegistry {
    private static final Iterable<Class<?>> HARDCODED_CLASSES = getHardCodedClasses();
    private static LoadBalancerRegistry instance;
    private static final Logger logger = Logger.getLogger(LoadBalancerRegistry.class.getName());
    private final LinkedHashSet<LoadBalancerProvider> allProviders = new LinkedHashSet<>();
    private final LinkedHashMap<String, LoadBalancerProvider> effectiveProviders = new LinkedHashMap<>();

    public synchronized void register(LoadBalancerProvider provider) {
        addProvider(provider);
        refreshProviderMap();
    }

    private synchronized void addProvider(LoadBalancerProvider provider) {
        Preconditions.checkArgument(provider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(provider);
    }

    public synchronized void deregister(LoadBalancerProvider provider) {
        this.allProviders.remove(provider);
        refreshProviderMap();
    }

    private synchronized void refreshProviderMap() {
        this.effectiveProviders.clear();
        Iterator it = this.allProviders.iterator();
        while (it.hasNext()) {
            LoadBalancerProvider provider = (LoadBalancerProvider) it.next();
            String policy = provider.getPolicyName();
            LoadBalancerProvider existing = this.effectiveProviders.get(policy);
            if (existing == null || existing.getPriority() < provider.getPriority()) {
                this.effectiveProviders.put(policy, provider);
            }
        }
    }

    public static synchronized LoadBalancerRegistry getDefaultRegistry() {
        LoadBalancerRegistry loadBalancerRegistry;
        synchronized (LoadBalancerRegistry.class) {
            if (instance == null) {
                List<LoadBalancerProvider> providerList = ServiceProviders.loadAll(LoadBalancerProvider.class, HARDCODED_CLASSES, LoadBalancerProvider.class.getClassLoader(), new LoadBalancerPriorityAccessor());
                instance = new LoadBalancerRegistry();
                for (LoadBalancerProvider provider : providerList) {
                    logger.fine("Service loader found " + provider);
                    if (provider.isAvailable()) {
                        instance.addProvider(provider);
                    }
                }
                instance.refreshProviderMap();
            }
            loadBalancerRegistry = instance;
        }
        return loadBalancerRegistry;
    }

    @Nullable
    public synchronized LoadBalancerProvider getProvider(String policy) {
        return this.effectiveProviders.get(Preconditions.checkNotNull(policy, "policy"));
    }

    /* access modifiers changed from: package-private */
    public synchronized Map<String, LoadBalancerProvider> providers() {
        return new LinkedHashMap(this.effectiveProviders);
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList<Class<?>> list = new ArrayList<>();
        try {
            list.add(Class.forName("io.grpc.internal.PickFirstLoadBalancerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Unable to find pick-first LoadBalancer", e);
        }
        try {
            list.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Unable to find round-robin LoadBalancer", e2);
        }
        return Collections.unmodifiableList(list);
    }

    /* renamed from: io.grpc.LoadBalancerRegistry$LoadBalancerPriorityAccessor */
    private static final class LoadBalancerPriorityAccessor implements ServiceProviders.PriorityAccessor<LoadBalancerProvider> {
        LoadBalancerPriorityAccessor() {
        }

        public boolean isAvailable(LoadBalancerProvider provider) {
            return provider.isAvailable();
        }

        public int getPriority(LoadBalancerProvider provider) {
            return provider.getPriority();
        }
    }
}
