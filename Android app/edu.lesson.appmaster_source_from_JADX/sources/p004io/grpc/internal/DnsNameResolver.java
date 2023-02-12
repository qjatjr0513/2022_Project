package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.NameResolver;
import p004io.grpc.ProxiedSocketAddress;
import p004io.grpc.ProxyDetector;
import p004io.grpc.Status;
import p004io.grpc.SynchronizationContext;
import p004io.grpc.internal.SharedResourceHolder;

/* renamed from: io.grpc.internal.DnsNameResolver */
public class DnsNameResolver extends NameResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long DEFAULT_NETWORK_CACHE_TTL_SECONDS = 30;
    private static final String JNDI_LOCALHOST_PROPERTY;
    private static final String JNDI_PROPERTY;
    private static final String JNDI_TXT_PROPERTY;
    static final String NETWORKADDRESS_CACHE_TTL_PROPERTY = "networkaddress.cache.ttl";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY = "clientHostname";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY = "clientLanguage";
    private static final Set<String> SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY})));
    private static final String SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY = "percentage";
    private static final String SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY = "serviceConfig";
    private static final String SERVICE_CONFIG_NAME_PREFIX = "_grpc_config.";
    static final String SERVICE_CONFIG_PREFIX = "grpc_config=";
    static boolean enableJndi;
    static boolean enableJndiLocalhost;
    protected static boolean enableTxt;
    private static String localHostname;
    /* access modifiers changed from: private */
    public static final Logger logger;
    private static final ResourceResolverFactory resourceResolverFactory;
    protected volatile AddressResolver addressResolver = JdkAddressResolver.INSTANCE;
    private final String authority;
    /* access modifiers changed from: private */
    public final long cacheTtlNanos;
    private Executor executor;
    private final SharedResourceHolder.Resource<Executor> executorResource;
    /* access modifiers changed from: private */
    public final String host;
    private NameResolver.Listener2 listener;
    private final int port;
    final ProxyDetector proxyDetector;
    private final Random random = new Random();
    protected boolean resolved;
    /* access modifiers changed from: private */
    public boolean resolving;
    private final AtomicReference<ResourceResolver> resourceResolver = new AtomicReference<>();
    private final NameResolver.ServiceConfigParser serviceConfigParser;
    private boolean shutdown;
    /* access modifiers changed from: private */
    public final Stopwatch stopwatch;
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;
    private final boolean usingExecutorResource;

    /* renamed from: io.grpc.internal.DnsNameResolver$AddressResolver */
    public interface AddressResolver {
        List<InetAddress> resolveAddress(String str) throws Exception;
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$ResourceResolver */
    public interface ResourceResolver {
        List<SrvRecord> resolveSrv(String str) throws Exception;

        List<String> resolveTxt(String str) throws Exception;
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$ResourceResolverFactory */
    interface ResourceResolverFactory {
        @Nullable
        ResourceResolver newResourceResolver();

        @Nullable
        Throwable unavailabilityCause();
    }

    static {
        Class<DnsNameResolver> cls = DnsNameResolver.class;
        logger = Logger.getLogger(cls.getName());
        String property = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");
        JNDI_PROPERTY = property;
        String property2 = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", "false");
        JNDI_LOCALHOST_PROPERTY = property2;
        String property3 = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", "false");
        JNDI_TXT_PROPERTY = property3;
        enableJndi = Boolean.parseBoolean(property);
        enableJndiLocalhost = Boolean.parseBoolean(property2);
        enableTxt = Boolean.parseBoolean(property3);
        resourceResolverFactory = getResourceResolverFactory(cls.getClassLoader());
    }

    protected DnsNameResolver(@Nullable String nsAuthority, String name, NameResolver.Args args, SharedResourceHolder.Resource<Executor> executorResource2, Stopwatch stopwatch2, boolean isAndroid) {
        Preconditions.checkNotNull(args, "args");
        this.executorResource = executorResource2;
        URI nameUri = URI.create("//" + ((String) Preconditions.checkNotNull(name, AppMeasurementSdk.ConditionalUserProperty.NAME)));
        boolean z = true;
        Preconditions.checkArgument(nameUri.getHost() != null, "Invalid DNS name: %s", (Object) name);
        this.authority = (String) Preconditions.checkNotNull(nameUri.getAuthority(), "nameUri (%s) doesn't have an authority", (Object) nameUri);
        this.host = nameUri.getHost();
        if (nameUri.getPort() == -1) {
            this.port = args.getDefaultPort();
        } else {
            this.port = nameUri.getPort();
        }
        this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(args.getProxyDetector(), "proxyDetector");
        this.cacheTtlNanos = getNetworkAddressCacheTtlNanos(isAndroid);
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch2, NotificationCompat.CATEGORY_STOPWATCH);
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(args.getSynchronizationContext(), "syncContext");
        Executor offloadExecutor = args.getOffloadExecutor();
        this.executor = offloadExecutor;
        this.usingExecutorResource = offloadExecutor != null ? false : z;
        this.serviceConfigParser = (NameResolver.ServiceConfigParser) Preconditions.checkNotNull(args.getServiceConfigParser(), "serviceConfigParser");
    }

    public String getServiceAuthority() {
        return this.authority;
    }

    /* access modifiers changed from: protected */
    public String getHost() {
        return this.host;
    }

    public void start(NameResolver.Listener2 listener2) {
        Preconditions.checkState(this.listener == null, "already started");
        if (this.usingExecutorResource) {
            this.executor = (Executor) SharedResourceHolder.get(this.executorResource);
        }
        this.listener = (NameResolver.Listener2) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        resolve();
    }

    public void refresh() {
        Preconditions.checkState(this.listener != null, "not started");
        resolve();
    }

    private List<EquivalentAddressGroup> resolveAddresses() {
        Exception addressesException;
        try {
            List<InetAddress> resolveAddress = this.addressResolver.resolveAddress(this.host);
            if (0 != 0) {
                logger.log(Level.FINE, "Address resolution failure", (Throwable) null);
            }
            List<EquivalentAddressGroup> servers = new ArrayList<>(resolveAddress.size());
            for (InetAddress inetAddr : resolveAddress) {
                servers.add(new EquivalentAddressGroup((SocketAddress) new InetSocketAddress(inetAddr, this.port)));
            }
            return Collections.unmodifiableList(servers);
        } catch (Exception e) {
            addressesException = e;
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        } catch (Throwable th) {
            if (addressesException != null) {
                logger.log(Level.FINE, "Address resolution failure", addressesException);
            }
            throw th;
        }
    }

    @Nullable
    private NameResolver.ConfigOrError resolveServiceConfig() {
        List<String> txtRecords = Collections.emptyList();
        ResourceResolver resourceResolver2 = getResourceResolver();
        if (resourceResolver2 != null) {
            try {
                txtRecords = resourceResolver2.resolveTxt(SERVICE_CONFIG_NAME_PREFIX + this.host);
            } catch (Exception e) {
                logger.log(Level.FINE, "ServiceConfig resolution failure", e);
            }
        }
        if (!txtRecords.isEmpty()) {
            NameResolver.ConfigOrError rawServiceConfig = parseServiceConfig(txtRecords, this.random, getLocalHostname());
            if (rawServiceConfig == null) {
                return null;
            }
            if (rawServiceConfig.getError() != null) {
                return NameResolver.ConfigOrError.fromError(rawServiceConfig.getError());
            }
            return this.serviceConfigParser.parseServiceConfig((Map) rawServiceConfig.getConfig());
        }
        logger.log(Level.FINE, "No TXT records found for {0}", new Object[]{this.host});
        return null;
    }

    /* access modifiers changed from: private */
    @Nullable
    public EquivalentAddressGroup detectProxy() throws IOException {
        ProxiedSocketAddress proxiedAddr = this.proxyDetector.proxyFor(InetSocketAddress.createUnresolved(this.host, this.port));
        if (proxiedAddr != null) {
            return new EquivalentAddressGroup((SocketAddress) proxiedAddr);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public InternalResolutionResult doResolve(boolean forceTxt) {
        InternalResolutionResult result = new InternalResolutionResult();
        try {
            List unused = result.addresses = resolveAddresses();
        } catch (Exception e) {
            if (!forceTxt) {
                Status unused2 = result.error = Status.UNAVAILABLE.withDescription("Unable to resolve host " + this.host).withCause(e);
                return result;
            }
        }
        if (enableTxt) {
            NameResolver.ConfigOrError unused3 = result.config = resolveServiceConfig();
        }
        return result;
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$Resolve */
    private final class Resolve implements Runnable {
        private final NameResolver.Listener2 savedListener;

        Resolve(NameResolver.Listener2 savedListener2) {
            this.savedListener = (NameResolver.Listener2) Preconditions.checkNotNull(savedListener2, "savedListener");
        }

        public void run() {
            SynchronizationContext access$1000;
            C12341 r3;
            if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                DnsNameResolver.logger.finer("Attempting DNS resolution of " + DnsNameResolver.this.host);
            }
            InternalResolutionResult result = null;
            final boolean succeed = true;
            try {
                EquivalentAddressGroup proxiedAddr = DnsNameResolver.this.detectProxy();
                NameResolver.ResolutionResult.Builder resolutionResultBuilder = NameResolver.ResolutionResult.newBuilder();
                if (proxiedAddr != null) {
                    if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                        DnsNameResolver.logger.finer("Using proxy address " + proxiedAddr);
                    }
                    resolutionResultBuilder.setAddresses(Collections.singletonList(proxiedAddr));
                } else {
                    result = DnsNameResolver.this.doResolve(false);
                    if (result.error != null) {
                        this.savedListener.onError(result.error);
                        if (result == null || result.error != null) {
                            succeed = false;
                        }
                        DnsNameResolver.this.syncContext.execute(new Runnable() {
                            public void run() {
                                if (succeed) {
                                    DnsNameResolver.this.resolved = true;
                                    if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                        DnsNameResolver.this.stopwatch.reset().start();
                                    }
                                }
                                boolean unused = DnsNameResolver.this.resolving = false;
                            }
                        });
                        return;
                    }
                    if (result.addresses != null) {
                        resolutionResultBuilder.setAddresses(result.addresses);
                    }
                    if (result.config != null) {
                        resolutionResultBuilder.setServiceConfig(result.config);
                    }
                    if (result.attributes != null) {
                        resolutionResultBuilder.setAttributes(result.attributes);
                    }
                }
                this.savedListener.onResult(resolutionResultBuilder.build());
                if (result == null || result.error != null) {
                    succeed = false;
                }
                access$1000 = DnsNameResolver.this.syncContext;
                r3 = new Runnable() {
                    public void run() {
                        if (succeed) {
                            DnsNameResolver.this.resolved = true;
                            if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                DnsNameResolver.this.stopwatch.reset().start();
                            }
                        }
                        boolean unused = DnsNameResolver.this.resolving = false;
                    }
                };
            } catch (IOException e) {
                this.savedListener.onError(Status.UNAVAILABLE.withDescription("Unable to resolve host " + DnsNameResolver.this.host).withCause(e));
                if (result == null || result.error != null) {
                    succeed = false;
                }
                access$1000 = DnsNameResolver.this.syncContext;
                r3 = new Runnable() {
                    public void run() {
                        if (succeed) {
                            DnsNameResolver.this.resolved = true;
                            if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                DnsNameResolver.this.stopwatch.reset().start();
                            }
                        }
                        boolean unused = DnsNameResolver.this.resolving = false;
                    }
                };
            } catch (Throwable th) {
                if (result == null || result.error != null) {
                    succeed = false;
                }
                DnsNameResolver.this.syncContext.execute(new Runnable() {
                    public void run() {
                        if (succeed) {
                            DnsNameResolver.this.resolved = true;
                            if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                DnsNameResolver.this.stopwatch.reset().start();
                            }
                        }
                        boolean unused = DnsNameResolver.this.resolving = false;
                    }
                });
                throw th;
            }
            access$1000.execute(r3);
        }
    }

    @Nullable
    static NameResolver.ConfigOrError parseServiceConfig(List<String> rawTxtRecords, Random random2, String localHostname2) {
        try {
            Map<String, ?> possibleServiceConfig = null;
            for (Map<String, ?> possibleServiceConfigChoice : parseTxtResults(rawTxtRecords)) {
                try {
                    possibleServiceConfig = maybeChooseServiceConfig(possibleServiceConfigChoice, random2, localHostname2);
                    if (possibleServiceConfig != null) {
                        break;
                    }
                } catch (RuntimeException e) {
                    return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to pick service config choice").withCause(e));
                }
            }
            if (possibleServiceConfig == null) {
                return null;
            }
            return NameResolver.ConfigOrError.fromConfig(possibleServiceConfig);
        } catch (IOException | RuntimeException e2) {
            return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse TXT records").withCause(e2));
        }
    }

    private void resolve() {
        if (!this.resolving && !this.shutdown && cacheRefreshRequired()) {
            this.resolving = true;
            this.executor.execute(new Resolve(this.listener));
        }
    }

    private boolean cacheRefreshRequired() {
        if (this.resolved) {
            long j = this.cacheTtlNanos;
            return j == 0 || (j > 0 && this.stopwatch.elapsed(TimeUnit.NANOSECONDS) > this.cacheTtlNanos);
        }
    }

    public void shutdown() {
        if (!this.shutdown) {
            this.shutdown = true;
            Executor executor2 = this.executor;
            if (executor2 != null && this.usingExecutorResource) {
                this.executor = (Executor) SharedResourceHolder.release(this.executorResource, executor2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final int getPort() {
        return this.port;
    }

    static List<Map<String, ?>> parseTxtResults(List<String> txtRecords) throws IOException {
        List<Map<String, ?>> possibleServiceConfigChoices = new ArrayList<>();
        for (String txtRecord : txtRecords) {
            if (!txtRecord.startsWith(SERVICE_CONFIG_PREFIX)) {
                logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{txtRecord});
            } else {
                Object rawChoices = JsonParser.parse(txtRecord.substring(SERVICE_CONFIG_PREFIX.length()));
                if (rawChoices instanceof List) {
                    possibleServiceConfigChoices.addAll(JsonUtil.checkObjectList((List) rawChoices));
                } else {
                    throw new ClassCastException("wrong type " + rawChoices);
                }
            }
        }
        return possibleServiceConfigChoices;
    }

    @Nullable
    private static final Double getPercentageFromChoice(Map<String, ?> serviceConfigChoice) {
        return JsonUtil.getNumberAsDouble(serviceConfigChoice, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY);
    }

    @Nullable
    private static final List<String> getClientLanguagesFromChoice(Map<String, ?> serviceConfigChoice) {
        return JsonUtil.getListOfStrings(serviceConfigChoice, SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY);
    }

    @Nullable
    private static final List<String> getHostnamesFromChoice(Map<String, ?> serviceConfigChoice) {
        return JsonUtil.getListOfStrings(serviceConfigChoice, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY);
    }

    private static long getNetworkAddressCacheTtlNanos(boolean isAndroid) {
        if (isAndroid) {
            return 0;
        }
        String cacheTtlPropertyValue = System.getProperty(NETWORKADDRESS_CACHE_TTL_PROPERTY);
        long cacheTtl = DEFAULT_NETWORK_CACHE_TTL_SECONDS;
        if (cacheTtlPropertyValue != null) {
            try {
                cacheTtl = Long.parseLong(cacheTtlPropertyValue);
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{NETWORKADDRESS_CACHE_TTL_PROPERTY, cacheTtlPropertyValue, Long.valueOf(DEFAULT_NETWORK_CACHE_TTL_SECONDS)});
            }
        }
        return cacheTtl > 0 ? TimeUnit.SECONDS.toNanos(cacheTtl) : cacheTtl;
    }

    @Nullable
    static Map<String, ?> maybeChooseServiceConfig(Map<String, ?> choice, Random random2, String hostname) {
        for (Map.Entry<String, ?> entry : choice.entrySet()) {
            Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", (Object) entry);
        }
        List<String> clientLanguages = getClientLanguagesFromChoice(choice);
        if (clientLanguages != null && !clientLanguages.isEmpty()) {
            boolean javaPresent = false;
            Iterator<String> it = clientLanguages.iterator();
            while (true) {
                if (it.hasNext()) {
                    if ("java".equalsIgnoreCase(it.next())) {
                        javaPresent = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!javaPresent) {
                return null;
            }
        }
        Double percentage = getPercentageFromChoice(choice);
        if (percentage != null) {
            int pct = percentage.intValue();
            Verify.verify(pct >= 0 && pct <= 100, "Bad percentage: %s", (Object) percentage);
            if (random2.nextInt(100) >= pct) {
                return null;
            }
        }
        List<String> clientHostnames = getHostnamesFromChoice(choice);
        if (clientHostnames != null && !clientHostnames.isEmpty()) {
            boolean hostnamePresent = false;
            Iterator<String> it2 = clientHostnames.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().equals(hostname)) {
                        hostnamePresent = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!hostnamePresent) {
                return null;
            }
        }
        Map<String, ?> sc = JsonUtil.getObject(choice, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY);
        if (sc != null) {
            return sc;
        }
        throw new VerifyException(String.format("key '%s' missing in '%s'", new Object[]{choice, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY}));
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$InternalResolutionResult */
    protected static final class InternalResolutionResult {
        /* access modifiers changed from: private */
        public List<EquivalentAddressGroup> addresses;
        public Attributes attributes;
        /* access modifiers changed from: private */
        public NameResolver.ConfigOrError config;
        /* access modifiers changed from: private */
        public Status error;

        private InternalResolutionResult() {
        }
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$SrvRecord */
    public static final class SrvRecord {
        public final String host;
        public final int port;

        public SrvRecord(String host2, int port2) {
            this.host = host2;
            this.port = port2;
        }

        public int hashCode() {
            return Objects.hashCode(this.host, Integer.valueOf(this.port));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SrvRecord that = (SrvRecord) obj;
            if (this.port != that.port || !this.host.equals(that.host)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("host", (Object) this.host).add("port", this.port).toString();
        }
    }

    /* access modifiers changed from: protected */
    public void setAddressResolver(AddressResolver addressResolver2) {
        this.addressResolver = addressResolver2;
    }

    /* access modifiers changed from: protected */
    public void setResourceResolver(ResourceResolver resourceResolver2) {
        this.resourceResolver.set(resourceResolver2);
    }

    /* renamed from: io.grpc.internal.DnsNameResolver$JdkAddressResolver */
    private enum JdkAddressResolver implements AddressResolver {
        INSTANCE;

        public List<InetAddress> resolveAddress(String host) throws UnknownHostException {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(host)));
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ResourceResolver getResourceResolver() {
        ResourceResolverFactory resourceResolverFactory2;
        if (!shouldUseJndi(enableJndi, enableJndiLocalhost, this.host)) {
            return null;
        }
        ResourceResolver resourceResolver2 = this.resourceResolver.get();
        ResourceResolver rr = resourceResolver2;
        if (resourceResolver2 != null || (resourceResolverFactory2 = resourceResolverFactory) == null) {
            return rr;
        }
        if (resourceResolverFactory2.unavailabilityCause() == null) {
            return resourceResolverFactory2.newResourceResolver();
        }
        throw new AssertionError();
    }

    @Nullable
    static ResourceResolverFactory getResourceResolverFactory(ClassLoader loader) {
        try {
            try {
                try {
                    ResourceResolverFactory rrf = (ResourceResolverFactory) Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, loader).asSubclass(ResourceResolverFactory.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (rrf.unavailabilityCause() == null) {
                        return rrf;
                    }
                    logger.log(Level.FINE, "JndiResourceResolverFactory not available, skipping.", rrf.unavailabilityCause());
                    return null;
                } catch (Exception e) {
                    logger.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", e);
                    return null;
                }
            } catch (Exception e2) {
                logger.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            logger.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", e3);
            return null;
        } catch (ClassCastException e4) {
            logger.log(Level.FINE, "Unable to cast JndiResourceResolverFactory, skipping.", e4);
            return null;
        }
    }

    private static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    protected static boolean shouldUseJndi(boolean jndiEnabled, boolean jndiLocalhostEnabled, String target) {
        if (!jndiEnabled) {
            return false;
        }
        if ("localhost".equalsIgnoreCase(target)) {
            return jndiLocalhostEnabled;
        }
        if (target.contains(":")) {
            return false;
        }
        boolean alldigits = true;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (c != '.') {
                alldigits &= c >= '0' && c <= '9';
            }
        }
        return !alldigits;
    }
}
