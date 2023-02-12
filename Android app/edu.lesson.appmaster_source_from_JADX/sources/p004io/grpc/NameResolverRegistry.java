package p004io.grpc;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.NameResolver;
import p004io.grpc.ServiceProviders;

/* renamed from: io.grpc.NameResolverRegistry */
public final class NameResolverRegistry {
    private static final String UNKNOWN_SCHEME = "unknown";
    private static NameResolverRegistry instance;
    private static final Logger logger = Logger.getLogger(NameResolverRegistry.class.getName());
    private final LinkedHashSet<NameResolverProvider> allProviders = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public String defaultScheme = "unknown";
    private ImmutableMap<String, NameResolverProvider> effectiveProviders = ImmutableMap.m60of();
    private final NameResolver.Factory factory = new NameResolverFactory();

    public synchronized void register(NameResolverProvider provider) {
        addProvider(provider);
        refreshProviders();
    }

    private synchronized void addProvider(NameResolverProvider provider) {
        Preconditions.checkArgument(provider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(provider);
    }

    public synchronized void deregister(NameResolverProvider provider) {
        this.allProviders.remove(provider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        Map<String, NameResolverProvider> refreshedProviders = new HashMap<>();
        int maxPriority = Integer.MIN_VALUE;
        String refreshedDefaultScheme = "unknown";
        Iterator it = this.allProviders.iterator();
        while (it.hasNext()) {
            NameResolverProvider provider = (NameResolverProvider) it.next();
            String scheme = provider.getScheme();
            NameResolverProvider existing = refreshedProviders.get(scheme);
            if (existing == null || existing.priority() < provider.priority()) {
                refreshedProviders.put(scheme, provider);
            }
            if (maxPriority < provider.priority()) {
                maxPriority = provider.priority();
                refreshedDefaultScheme = provider.getScheme();
            }
        }
        this.effectiveProviders = ImmutableMap.copyOf(refreshedProviders);
        this.defaultScheme = refreshedDefaultScheme;
    }

    public static synchronized NameResolverRegistry getDefaultRegistry() {
        NameResolverRegistry nameResolverRegistry;
        synchronized (NameResolverRegistry.class) {
            if (instance == null) {
                List<NameResolverProvider> providerList = ServiceProviders.loadAll(NameResolverProvider.class, getHardCodedClasses(), NameResolverProvider.class.getClassLoader(), new NameResolverPriorityAccessor());
                if (providerList.isEmpty()) {
                    logger.warning("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
                }
                instance = new NameResolverRegistry();
                for (NameResolverProvider provider : providerList) {
                    logger.fine("Service loader found " + provider);
                    if (provider.isAvailable()) {
                        instance.addProvider(provider);
                    }
                }
                instance.refreshProviders();
            }
            nameResolverRegistry = instance;
        }
        return nameResolverRegistry;
    }

    /* access modifiers changed from: package-private */
    public synchronized Map<String, NameResolverProvider> providers() {
        return this.effectiveProviders;
    }

    public NameResolver.Factory asFactory() {
        return this.factory;
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList<Class<?>> list = new ArrayList<>();
        try {
            list.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find DNS NameResolver", e);
        }
        return Collections.unmodifiableList(list);
    }

    /* renamed from: io.grpc.NameResolverRegistry$NameResolverFactory */
    private final class NameResolverFactory extends NameResolver.Factory {
        private NameResolverFactory() {
        }

        @Nullable
        public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
            NameResolverProvider provider = NameResolverRegistry.this.providers().get(targetUri.getScheme());
            if (provider == null) {
                return null;
            }
            return provider.newNameResolver(targetUri, args);
        }

        public String getDefaultScheme() {
            String access$200;
            synchronized (NameResolverRegistry.this) {
                access$200 = NameResolverRegistry.this.defaultScheme;
            }
            return access$200;
        }
    }

    /* renamed from: io.grpc.NameResolverRegistry$NameResolverPriorityAccessor */
    private static final class NameResolverPriorityAccessor implements ServiceProviders.PriorityAccessor<NameResolverProvider> {
        private NameResolverPriorityAccessor() {
        }

        public boolean isAvailable(NameResolverProvider provider) {
            return provider.isAvailable();
        }

        public int getPriority(NameResolverProvider provider) {
            return provider.priority();
        }
    }
}
