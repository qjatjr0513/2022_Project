package p004io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.Thread;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.CallCredentials;
import p004io.grpc.CallOptions;
import p004io.grpc.Channel;
import p004io.grpc.ChannelCredentials;
import p004io.grpc.ChannelLogger;
import p004io.grpc.ClientCall;
import p004io.grpc.ClientInterceptor;
import p004io.grpc.ClientInterceptors;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.CompressorRegistry;
import p004io.grpc.ConnectivityState;
import p004io.grpc.ConnectivityStateInfo;
import p004io.grpc.Context;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.ForwardingChannelBuilder;
import p004io.grpc.ForwardingClientCall;
import p004io.grpc.Grpc;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalConfigSelector;
import p004io.grpc.InternalInstrumented;
import p004io.grpc.InternalLogId;
import p004io.grpc.InternalWithLogId;
import p004io.grpc.LoadBalancer;
import p004io.grpc.ManagedChannel;
import p004io.grpc.ManagedChannelBuilder;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.NameResolver;
import p004io.grpc.NameResolverRegistry;
import p004io.grpc.ProxyDetector;
import p004io.grpc.Status;
import p004io.grpc.SynchronizationContext;
import p004io.grpc.internal.AutoConfiguredLoadBalancerFactory;
import p004io.grpc.internal.BackoffPolicy;
import p004io.grpc.internal.CallTracer;
import p004io.grpc.internal.ClientCallImpl;
import p004io.grpc.internal.ClientTransportFactory;
import p004io.grpc.internal.InternalSubchannel;
import p004io.grpc.internal.ManagedChannelImplBuilder;
import p004io.grpc.internal.ManagedChannelServiceConfig;
import p004io.grpc.internal.ManagedClientTransport;
import p004io.grpc.internal.RetriableStream;

/* renamed from: io.grpc.internal.ManagedChannelImpl */
final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<InternalChannelz.ChannelStats> {
    /* access modifiers changed from: private */
    public static final ManagedChannelServiceConfig EMPTY_SERVICE_CONFIG = ManagedChannelServiceConfig.empty();
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1;
    /* access modifiers changed from: private */
    public static final InternalConfigSelector INITIAL_PENDING_SELECTOR = new InternalConfigSelector() {
        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs args) {
            throw new IllegalStateException("Resolution is pending");
        }
    };
    /* access modifiers changed from: private */
    public static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void start(ClientCall.Listener<Object> listener, Metadata headers) {
        }

        public void request(int numMessages) {
        }

        public void cancel(String message, Throwable cause) {
        }

        public void halfClose() {
        }

        public void sendMessage(Object message) {
        }

        public boolean isReady() {
            return false;
        }
    };
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5;
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    /* access modifiers changed from: private */
    @Nullable
    public final String authorityOverride;
    /* access modifiers changed from: private */
    public final BackoffPolicy.Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final ExecutorHolder balancerRpcExecutorHolder;
    /* access modifiers changed from: private */
    public final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    /* access modifiers changed from: private */
    public final CallTracer.Factory callTracerFactory;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final RetriableStream.ChannelBufferMeter channelBufferUsed = new RetriableStream.ChannelBufferMeter();
    /* access modifiers changed from: private */
    public final CallTracer channelCallTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public final ManagedChannelServiceConfig defaultServiceConfig;
    /* access modifiers changed from: private */
    public final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.Listener delayedTransportListener;
    /* access modifiers changed from: private */
    public final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public boolean fullStreamDecompression;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    /* access modifiers changed from: private */
    public ResolutionState lastResolutionState = ResolutionState.NO_RESOLUTION;
    /* access modifiers changed from: private */
    public ManagedChannelServiceConfig lastServiceConfig = EMPTY_SERVICE_CONFIG;
    /* access modifiers changed from: private */
    @Nullable
    public LbHelperImpl lbHelper;
    private final AutoConfiguredLoadBalancerFactory loadBalancerFactory;
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public final boolean lookUpServiceConfig;
    /* access modifiers changed from: private */
    public final int maxTraceEvents;
    private NameResolver nameResolver;
    /* access modifiers changed from: private */
    public final NameResolver.Args nameResolverArgs;
    /* access modifiers changed from: private */
    @Nullable
    public BackoffPolicy nameResolverBackoffPolicy;
    /* access modifiers changed from: private */
    public final NameResolver.Factory nameResolverFactory;
    /* access modifiers changed from: private */
    public final NameResolverRegistry nameResolverRegistry;
    /* access modifiers changed from: private */
    public boolean nameResolverStarted;
    /* access modifiers changed from: private */
    public final ExecutorHolder offloadExecutorHolder;
    /* access modifiers changed from: private */
    public final Set<OobChannel> oobChannels = new HashSet(1, 0.75f);
    /* access modifiers changed from: private */
    public final ClientTransportFactory oobTransportFactory;
    /* access modifiers changed from: private */
    @Nullable
    public final ChannelCredentials originalChannelCreds;
    /* access modifiers changed from: private */
    public final ClientTransportFactory originalTransportFactory;
    private boolean panicMode;
    /* access modifiers changed from: private */
    @Nullable
    public Collection<RealChannel.PendingCall<?, ?>> pendingCalls;
    /* access modifiers changed from: private */
    public final Object pendingCallsInUseObject = new Object();
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public final RealChannel realChannel;
    /* access modifiers changed from: private */
    public final boolean retryEnabled;
    /* access modifiers changed from: private */
    public final RestrictedScheduledExecutor scheduledExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle scheduledNameResolverRefresh;
    /* access modifiers changed from: private */
    public boolean serviceConfigUpdated = false;
    /* access modifiers changed from: private */
    public final AtomicBoolean shutdown = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean shutdownNowed;
    /* access modifiers changed from: private */
    public final Supplier<Stopwatch> stopwatchSupplier;
    /* access modifiers changed from: private */
    @Nullable
    public volatile LoadBalancer.SubchannelPicker subchannelPicker;
    /* access modifiers changed from: private */
    public final Set<InternalSubchannel> subchannels = new HashSet(16, 0.75f);
    final SynchronizationContext syncContext;
    /* access modifiers changed from: private */
    public final String target;
    /* access modifiers changed from: private */
    public volatile boolean terminated;
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public boolean terminating;
    /* access modifiers changed from: private */
    public final TimeProvider timeProvider;
    /* access modifiers changed from: private */
    public final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final ClientCallImpl.ClientStreamProvider transportProvider;
    /* access modifiers changed from: private */
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
    /* access modifiers changed from: private */
    @Nullable
    public final String userAgent;

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ResolutionState */
    enum ResolutionState {
        NO_RESOLUTION,
        SUCCESS,
        ERROR
    }

    /* access modifiers changed from: private */
    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel subchannel : this.subchannels) {
                subchannel.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel oobChannel : this.oobChannels) {
                oobChannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture<InternalChannelz.ChannelStats> ret = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                ManagedChannelImpl.this.channelCallTracer.updateBuilder(builder);
                ManagedChannelImpl.this.channelTracer.updateBuilder(builder);
                builder.setTarget(ManagedChannelImpl.this.target).setState(ManagedChannelImpl.this.channelStateManager.getState());
                List<InternalWithLogId> children = new ArrayList<>();
                children.addAll(ManagedChannelImpl.this.subchannels);
                children.addAll(ManagedChannelImpl.this.oobChannels);
                builder.setSubchannels(children);
                ret.set(builder.build());
            }
        });
        return ret;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeTimer */
    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        public void run() {
            if (ManagedChannelImpl.this.lbHelper != null) {
                ManagedChannelImpl.this.enterIdleMode();
            }
        }
    }

    /* access modifiers changed from: private */
    public void shutdownNameResolverAndLoadBalancer(boolean channelIsActive) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (channelIsActive) {
            Preconditions.checkState(this.nameResolverStarted, "nameResolver is not started");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolverStarted = false;
            if (channelIsActive) {
                this.nameResolver = getNameResolver(this.target, this.authorityOverride, this.nameResolverFactory, this.nameResolverArgs);
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.f315lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    /* access modifiers changed from: package-private */
    public void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (!this.shutdown.get() && !this.panicMode) {
            if (this.inUseStateAggregator.isInUse()) {
                cancelIdleTimer(false);
            } else {
                rescheduleIdleTimer();
            }
            if (this.lbHelper == null) {
                this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Exiting idle mode");
                LbHelperImpl lbHelper2 = new LbHelperImpl();
                lbHelper2.f315lb = this.loadBalancerFactory.newLoadBalancer(lbHelper2);
                this.lbHelper = lbHelper2;
                this.nameResolver.start((NameResolver.Listener2) new NameResolverListener(lbHelper2, this.nameResolver));
                this.nameResolverStarted = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess((LoadBalancer.SubchannelPicker) null);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.anyObjectInUse(this.pendingCallsInUseObject, this.delayedTransport)) {
            exitIdleMode();
        }
    }

    /* access modifiers changed from: private */
    public void cancelIdleTimer(boolean permanent) {
        this.idleTimer.cancel(permanent);
    }

    /* access modifiers changed from: private */
    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j != -1) {
            this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$DelayedNameResolverRefresh */
    class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        public void run() {
            SynchronizationContext.ScheduledHandle unused = ManagedChannelImpl.this.scheduledNameResolverRefresh = null;
            ManagedChannelImpl.this.refreshNameResolution();
        }
    }

    private void cancelNameResolverBackoff() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduledNameResolverRefresh;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.scheduledNameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    /* access modifiers changed from: private */
    public void refreshAndResetNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        cancelNameResolverBackoff();
        refreshNameResolution();
    }

    /* access modifiers changed from: private */
    public void refreshNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.nameResolverStarted) {
            this.nameResolver.refresh();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ChannelStreamProvider */
    private final class ChannelStreamProvider implements ClientCallImpl.ClientStreamProvider {
        private ChannelStreamProvider() {
        }

        /* access modifiers changed from: private */
        public ClientTransport getTransport(LoadBalancer.PickSubchannelArgs args) {
            LoadBalancer.SubchannelPicker pickerCopy = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (pickerCopy == null) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                });
                return ManagedChannelImpl.this.delayedTransport;
            }
            ClientTransport transport = GrpcUtil.getTransportFromPickResult(pickerCopy.pickSubchannel(args), args.getCallOptions().isWaitForReady());
            if (transport != null) {
                return transport;
            }
            return ManagedChannelImpl.this.delayedTransport;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> method, CallOptions callOptions, Metadata headers, Context context) {
            MethodDescriptor<?, ?> methodDescriptor = method;
            CallOptions callOptions2 = callOptions;
            Metadata metadata = headers;
            Context context2 = context;
            if (!ManagedChannelImpl.this.retryEnabled) {
                ClientTransport transport = getTransport(new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions2));
                Context origContext = context.attach();
                try {
                    ClientStream newStream = transport.newStream(methodDescriptor, metadata, callOptions2, GrpcUtil.getClientStreamTracers(callOptions2, metadata, 0, false));
                    context2.detach(origContext);
                    return newStream;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    context2.detach(origContext);
                    throw th2;
                }
            } else {
                RetriableStream.Throttle throttle = ManagedChannelImpl.this.lastServiceConfig.getRetryThrottling();
                ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) callOptions2.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
                HedgingPolicy hedgingPolicy = null;
                RetryPolicy retryPolicy = methodInfo == null ? null : methodInfo.retryPolicy;
                if (methodInfo != null) {
                    hedgingPolicy = methodInfo.hedgingPolicy;
                }
                return new RetriableStream<ReqT>(this, method, headers, callOptions, retryPolicy, hedgingPolicy, throttle, context) {
                    final /* synthetic */ ChannelStreamProvider this$1;
                    final /* synthetic */ CallOptions val$callOptions;
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ Metadata val$headers;
                    final /* synthetic */ HedgingPolicy val$hedgingPolicy;
                    final /* synthetic */ MethodDescriptor val$method;
                    final /* synthetic */ RetryPolicy val$retryPolicy;
                    final /* synthetic */ RetriableStream.Throttle val$throttle;

                    {
                        ChannelStreamProvider channelStreamProvider = this$1;
                        CallOptions callOptions = r19;
                        this.this$1 = channelStreamProvider;
                        MethodDescriptor methodDescriptor = r17;
                        this.val$method = methodDescriptor;
                        Metadata metadata = r18;
                        this.val$headers = metadata;
                        this.val$callOptions = callOptions;
                        RetryPolicy retryPolicy = r20;
                        this.val$retryPolicy = retryPolicy;
                        HedgingPolicy hedgingPolicy = r21;
                        this.val$hedgingPolicy = hedgingPolicy;
                        RetriableStream.Throttle throttle = r22;
                        this.val$throttle = throttle;
                        this.val$context = r23;
                    }

                    /* access modifiers changed from: package-private */
                    public Status prestart() {
                        return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                    }

                    /* access modifiers changed from: package-private */
                    public void postCommit() {
                        ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                    }

                    /* access modifiers changed from: package-private */
                    public ClientStream newSubstream(Metadata newHeaders, ClientStreamTracer.Factory factory, int previousAttempts, boolean isTransparentRetry) {
                        CallOptions newOptions = this.val$callOptions.withStreamTracerFactory(factory);
                        ClientStreamTracer[] tracers = GrpcUtil.getClientStreamTracers(newOptions, newHeaders, previousAttempts, isTransparentRetry);
                        ClientTransport transport = this.this$1.getTransport(new PickSubchannelArgsImpl(this.val$method, newHeaders, newOptions));
                        Context origContext = this.val$context.attach();
                        try {
                            return transport.newStream(this.val$method, newHeaders, newOptions, tracers);
                        } finally {
                            this.val$context.detach(origContext);
                        }
                    }
                };
            }
        }
    }

    ManagedChannelImpl(ManagedChannelImplBuilder builder, ClientTransportFactory clientTransportFactory, BackoffPolicy.Provider backoffPolicyProvider2, ObjectPool<? extends Executor> balancerRpcExecutorPool2, Supplier<Stopwatch> stopwatchSupplier2, List<ClientInterceptor> interceptors, TimeProvider timeProvider2) {
        C12661 r6;
        ManagedChannelImplBuilder managedChannelImplBuilder = builder;
        ClientTransportFactory clientTransportFactory2 = clientTransportFactory;
        ObjectPool<? extends Executor> objectPool = balancerRpcExecutorPool2;
        TimeProvider timeProvider3 = timeProvider2;
        SynchronizationContext synchronizationContext = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                ManagedChannelImpl.logger.log(Level.SEVERE, "[" + ManagedChannelImpl.this.getLogId() + "] Uncaught exception in the SynchronizationContext. Panic!", e);
                ManagedChannelImpl.this.panic(e);
            }
        });
        this.syncContext = synchronizationContext;
        DelayedTransportListener delayedTransportListener2 = new DelayedTransportListener();
        this.delayedTransportListener = delayedTransportListener2;
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelStreamProvider();
        String str = (String) Preconditions.checkNotNull(managedChannelImplBuilder.target, TypedValues.AttributesType.S_TARGET);
        this.target = str;
        InternalLogId allocate = InternalLogId.allocate("Channel", str);
        this.logId = allocate;
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider3, "timeProvider");
        ObjectPool<? extends Executor> objectPool2 = (ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder.executorPool, "executorPool");
        this.executorPool = objectPool2;
        Executor executor2 = (Executor) Preconditions.checkNotNull((Executor) objectPool2.getObject(), "executor");
        this.executor = executor2;
        this.originalChannelCreds = managedChannelImplBuilder.channelCredentials;
        this.originalTransportFactory = clientTransportFactory2;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory2, managedChannelImplBuilder.callCredentials, executor2);
        this.transportFactory = callCredentialsApplyingTransportFactory;
        this.oobTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory2, (CallCredentials) null, executor2);
        RestrictedScheduledExecutor restrictedScheduledExecutor = new RestrictedScheduledExecutor(callCredentialsApplyingTransportFactory.getScheduledExecutorService());
        this.scheduledExecutor = restrictedScheduledExecutor;
        this.maxTraceEvents = managedChannelImplBuilder.maxTraceEvents;
        ChannelTracer channelTracer2 = r12;
        RestrictedScheduledExecutor restrictedScheduledExecutor2 = restrictedScheduledExecutor;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory2 = callCredentialsApplyingTransportFactory;
        ChannelTracer channelTracer3 = new ChannelTracer(allocate, managedChannelImplBuilder.maxTraceEvents, timeProvider2.currentTimeNanos(), "Channel for '" + str + "'");
        this.channelTracer = channelTracer2;
        ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer2, timeProvider3);
        this.channelLogger = channelLoggerImpl;
        ProxyDetector proxyDetector = managedChannelImplBuilder.proxyDetector != null ? managedChannelImplBuilder.proxyDetector : GrpcUtil.DEFAULT_PROXY_DETECTOR;
        boolean z = managedChannelImplBuilder.retryEnabled;
        this.retryEnabled = z;
        AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = new AutoConfiguredLoadBalancerFactory(managedChannelImplBuilder.defaultLbPolicy);
        this.loadBalancerFactory = autoConfiguredLoadBalancerFactory;
        this.offloadExecutorHolder = new ExecutorHolder((ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder.offloadExecutorPool, "offloadExecutorPool"));
        this.nameResolverRegistry = managedChannelImplBuilder.nameResolverRegistry;
        ScParser serviceConfigParser = new ScParser(z, managedChannelImplBuilder.maxRetryAttempts, managedChannelImplBuilder.maxHedgedAttempts, autoConfiguredLoadBalancerFactory);
        NameResolver.Args build = NameResolver.Args.newBuilder().setDefaultPort(builder.getDefaultPort()).setProxyDetector(proxyDetector).setSynchronizationContext(synchronizationContext).setScheduledExecutorService(restrictedScheduledExecutor2).setServiceConfigParser(serviceConfigParser).setChannelLogger(channelLoggerImpl).setOffloadExecutor(new Executor() {
            public void execute(Runnable command) {
                ManagedChannelImpl.this.offloadExecutorHolder.getExecutor().execute(command);
            }
        }).build();
        this.nameResolverArgs = build;
        String str2 = managedChannelImplBuilder.authorityOverride;
        this.authorityOverride = str2;
        NameResolver.Factory factory = managedChannelImplBuilder.nameResolverFactory;
        this.nameResolverFactory = factory;
        this.nameResolver = getNameResolver(str, str2, factory, build);
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool);
        DelayedClientTransport delayedClientTransport = new DelayedClientTransport(executor2, synchronizationContext);
        this.delayedTransport = delayedClientTransport;
        delayedClientTransport.start(delayedTransportListener2);
        this.backoffPolicyProvider = backoffPolicyProvider2;
        if (managedChannelImplBuilder.defaultServiceConfig != null) {
            NameResolver.ConfigOrError parsedDefaultServiceConfig = serviceConfigParser.parseServiceConfig(managedChannelImplBuilder.defaultServiceConfig);
            Preconditions.checkState(parsedDefaultServiceConfig.getError() == null, "Default config is invalid: %s", (Object) parsedDefaultServiceConfig.getError());
            ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) parsedDefaultServiceConfig.getConfig();
            this.defaultServiceConfig = managedChannelServiceConfig;
            this.lastServiceConfig = managedChannelServiceConfig;
            r6 = null;
        } else {
            r6 = null;
            this.defaultServiceConfig = null;
        }
        boolean z2 = managedChannelImplBuilder.lookUpServiceConfig;
        this.lookUpServiceConfig = z2;
        this.realChannel = new RealChannel(this.nameResolver.getServiceAuthority());
        Channel channel = this.realChannel;
        this.interceptorChannel = ClientInterceptors.intercept(managedChannelImplBuilder.binlog != null ? managedChannelImplBuilder.binlog.wrapChannel(channel) : channel, (List<? extends ClientInterceptor>) interceptors);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(stopwatchSupplier2, "stopwatchSupplier");
        if (managedChannelImplBuilder.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        } else {
            Preconditions.checkArgument(managedChannelImplBuilder.idleTimeoutMillis >= ManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS, "invalid idleTimeoutMillis %s", managedChannelImplBuilder.idleTimeoutMillis);
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), synchronizationContext, callCredentialsApplyingTransportFactory2.getScheduledExecutorService(), stopwatchSupplier2.get());
        this.fullStreamDecompression = managedChannelImplBuilder.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder.compressorRegistry, "compressorRegistry");
        this.userAgent = managedChannelImplBuilder.userAgent;
        this.channelBufferLimit = managedChannelImplBuilder.retryBufferSize;
        this.perRpcBufferLimit = managedChannelImplBuilder.perRpcBufferLimit;
        final TimeProvider timeProvider4 = timeProvider2;
        AnonymousClass1ChannelCallTracerFactory r2 = new CallTracer.Factory() {
            public CallTracer create() {
                return new CallTracer(timeProvider4);
            }
        };
        this.callTracerFactory = r2;
        this.channelCallTracer = r2.create();
        InternalChannelz internalChannelz = (InternalChannelz) Preconditions.checkNotNull(managedChannelImplBuilder.channelz);
        this.channelz = internalChannelz;
        internalChannelz.addRootChannel(this);
        if (!z2) {
            if (this.defaultServiceConfig != null) {
                channelLoggerImpl.log(ChannelLogger.ChannelLogLevel.INFO, "Service config look-up disabled, using default service config");
            }
            this.serviceConfigUpdated = true;
        }
    }

    private static NameResolver getNameResolver(String target2, NameResolver.Factory nameResolverFactory2, NameResolver.Args nameResolverArgs2) {
        NameResolver resolver;
        URI targetUri = null;
        StringBuilder uriSyntaxErrors = new StringBuilder();
        try {
            targetUri = new URI(target2);
        } catch (URISyntaxException e) {
            uriSyntaxErrors.append(e.getMessage());
        }
        if (targetUri != null && (resolver = nameResolverFactory2.newNameResolver(targetUri, nameResolverArgs2)) != null) {
            return resolver;
        }
        String str = "";
        if (!URI_PATTERN.matcher(target2).matches()) {
            try {
                NameResolver resolver2 = nameResolverFactory2.newNameResolver(new URI(nameResolverFactory2.getDefaultScheme(), str, "/" + target2, (String) null), nameResolverArgs2);
                if (resolver2 != null) {
                    return resolver2;
                }
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = target2;
        if (uriSyntaxErrors.length() > 0) {
            str = " (" + uriSyntaxErrors + ")";
        }
        objArr[1] = str;
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
    }

    static NameResolver getNameResolver(String target2, @Nullable final String overrideAuthority, NameResolver.Factory nameResolverFactory2, NameResolver.Args nameResolverArgs2) {
        NameResolver resolver = getNameResolver(target2, nameResolverFactory2, nameResolverArgs2);
        if (overrideAuthority == null) {
            return resolver;
        }
        return new ForwardingNameResolver(resolver) {
            public String getServiceAuthority() {
                return overrideAuthority;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public InternalConfigSelector getConfigSelector() {
        return (InternalConfigSelector) this.realChannel.configSelector.get();
    }

    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.realChannel.shutdown();
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        });
        return this;
    }

    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.realChannel.shutdownNow();
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdownNowed) {
                    boolean unused = ManagedChannelImpl.this.shutdownNowed = true;
                    ManagedChannelImpl.this.maybeShutdownNowSubchannels();
                }
            }
        });
        return this;
    }

    /* access modifiers changed from: package-private */
    public void panic(Throwable t) {
        if (!this.panicMode) {
            this.panicMode = true;
            cancelIdleTimer(true);
            shutdownNameResolverAndLoadBalancer(false);
            updateSubchannelPicker(new LoadBalancer.SubchannelPicker(t) {
                private final LoadBalancer.PickResult panicPickResult;
                final /* synthetic */ Throwable val$t;

                {
                    this.val$t = r4;
                    this.panicPickResult = LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(r4));
                }

                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
                    return this.panicPickResult;
                }

                public String toString() {
                    return MoreObjects.toStringHelper((Class<?>) AnonymousClass1PanicSubchannelPicker.class).add("panicPickResult", (Object) this.panicPickResult).toString();
                }
            });
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
            this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInPanicMode() {
        return this.panicMode;
    }

    /* access modifiers changed from: private */
    public void updateSubchannelPicker(LoadBalancer.SubchannelPicker newPicker) {
        this.subchannelPicker = newPicker;
        this.delayedTransport.reprocess(newPicker);
    }

    public boolean isShutdown() {
        return this.shutdown.get();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.terminatedLatch.await(timeout, unit);
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions) {
        return this.interceptorChannel.newCall(method, callOptions);
    }

    public String authority() {
        return this.interceptorChannel.authority();
    }

    /* access modifiers changed from: private */
    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor2 = callOptions.getExecutor();
        if (executor2 == null) {
            return this.executor;
        }
        return executor2;
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$RealChannel */
    private class RealChannel extends Channel {
        /* access modifiers changed from: private */
        public final String authority;
        private final Channel clientCallImplChannel;
        /* access modifiers changed from: private */
        public final AtomicReference<InternalConfigSelector> configSelector;

        private RealChannel(String authority2) {
            this.configSelector = new AtomicReference<>(ManagedChannelImpl.INITIAL_PENDING_SELECTOR);
            this.clientCallImplChannel = new Channel() {
                public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> method, CallOptions callOptions) {
                    return new ClientCallImpl(method, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, (InternalConfigSelector) null).setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
                }

                public String authority() {
                    return RealChannel.this.authority;
                }
            };
            this.authority = (String) Preconditions.checkNotNull(authority2, "authority");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions) {
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(method, callOptions);
            }
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                }
            });
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(method, callOptions);
            }
            if (ManagedChannelImpl.this.shutdown.get()) {
                return new ClientCall<ReqT, RespT>() {
                    public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                        responseListener.onClose(ManagedChannelImpl.SHUTDOWN_STATUS, new Metadata());
                    }

                    public void request(int numMessages) {
                    }

                    public void cancel(@Nullable String message, @Nullable Throwable cause) {
                    }

                    public void halfClose() {
                    }

                    public void sendMessage(ReqT reqt) {
                    }
                };
            }
            final PendingCall<ReqT, RespT> pendingCall = new PendingCall<>(Context.current(), method, callOptions);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        if (ManagedChannelImpl.this.pendingCalls == null) {
                            Collection unused = ManagedChannelImpl.this.pendingCalls = new LinkedHashSet();
                            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.pendingCallsInUseObject, true);
                        }
                        ManagedChannelImpl.this.pendingCalls.add(pendingCall);
                        return;
                    }
                    pendingCall.reprocess();
                }
            });
            return pendingCall;
        }

        /* access modifiers changed from: package-private */
        public void updateConfigSelector(@Nullable InternalConfigSelector config) {
            InternalConfigSelector prevConfig = this.configSelector.get();
            this.configSelector.set(config);
            if (prevConfig == ManagedChannelImpl.INITIAL_PENDING_SELECTOR && ManagedChannelImpl.this.pendingCalls != null) {
                for (PendingCall<?, ?> pendingCall : ManagedChannelImpl.this.pendingCalls) {
                    pendingCall.reprocess();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onConfigError() {
            if (this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                updateConfigSelector((InternalConfigSelector) null);
            }
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.pendingCalls == null) {
                        if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                            RealChannel.this.configSelector.set((Object) null);
                        }
                        ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void shutdownNow() {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        RealChannel.this.configSelector.set((Object) null);
                    }
                    if (ManagedChannelImpl.this.pendingCalls != null) {
                        for (PendingCall<?, ?> pendingCall : ManagedChannelImpl.this.pendingCalls) {
                            pendingCall.cancel("Channel is forcefully shutdown", (Throwable) null);
                        }
                    }
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdownNow(ManagedChannelImpl.SHUTDOWN_NOW_STATUS);
                }
            });
        }

        public String authority() {
            return this.authority;
        }

        /* renamed from: io.grpc.internal.ManagedChannelImpl$RealChannel$PendingCall */
        private final class PendingCall<ReqT, RespT> extends DelayedClientCall<ReqT, RespT> {
            final CallOptions callOptions;
            final Context context;
            final MethodDescriptor<ReqT, RespT> method;

            PendingCall(Context context2, MethodDescriptor<ReqT, RespT> method2, CallOptions callOptions2) {
                super(ManagedChannelImpl.this.getCallExecutor(callOptions2), ManagedChannelImpl.this.scheduledExecutor, callOptions2.getDeadline());
                this.context = context2;
                this.method = method2;
                this.callOptions = callOptions2;
            }

            /* access modifiers changed from: package-private */
            public void reprocess() {
                ManagedChannelImpl.this.getCallExecutor(this.callOptions).execute(new Runnable() {
                    /* JADX INFO: finally extract failed */
                    public void run() {
                        Context previous = PendingCall.this.context.attach();
                        try {
                            ClientCall<ReqT, RespT> realCall = RealChannel.this.newClientCall(PendingCall.this.method, PendingCall.this.callOptions);
                            PendingCall.this.context.detach(previous);
                            PendingCall.this.setCall(realCall);
                            ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
                        } catch (Throwable th) {
                            PendingCall.this.context.detach(previous);
                            throw th;
                        }
                    }
                });
            }

            /* access modifiers changed from: protected */
            public void callCancelled() {
                super.callCancelled();
                ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
            }

            /* renamed from: io.grpc.internal.ManagedChannelImpl$RealChannel$PendingCall$PendingCallRemoval */
            final class PendingCallRemoval implements Runnable {
                PendingCallRemoval() {
                }

                public void run() {
                    if (ManagedChannelImpl.this.pendingCalls != null) {
                        ManagedChannelImpl.this.pendingCalls.remove(PendingCall.this);
                        if (ManagedChannelImpl.this.pendingCalls.isEmpty()) {
                            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.pendingCallsInUseObject, false);
                            Collection unused = ManagedChannelImpl.this.pendingCalls = null;
                            if (ManagedChannelImpl.this.shutdown.get()) {
                                ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                            }
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public <ReqT, RespT> ClientCall<ReqT, RespT> newClientCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions) {
            InternalConfigSelector selector = this.configSelector.get();
            if (selector == null) {
                return this.clientCallImplChannel.newCall(method, callOptions);
            }
            if (selector instanceof ManagedChannelServiceConfig.ServiceConfigConvertedSelector) {
                ManagedChannelServiceConfig.MethodInfo methodInfo = ((ManagedChannelServiceConfig.ServiceConfigConvertedSelector) selector).config.getMethodConfig(method);
                if (methodInfo != null) {
                    callOptions = callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodInfo);
                }
                return this.clientCallImplChannel.newCall(method, callOptions);
            }
            return new ConfigSelectingClientCall(selector, this.clientCallImplChannel, ManagedChannelImpl.this.executor, method, callOptions);
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ConfigSelectingClientCall */
    static final class ConfigSelectingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final Executor callExecutor;
        private CallOptions callOptions;
        private final Channel channel;
        private final InternalConfigSelector configSelector;
        /* access modifiers changed from: private */
        public final Context context;
        private ClientCall<ReqT, RespT> delegate;
        private final MethodDescriptor<ReqT, RespT> method;

        ConfigSelectingClientCall(InternalConfigSelector configSelector2, Channel channel2, Executor channelExecutor, MethodDescriptor<ReqT, RespT> method2, CallOptions callOptions2) {
            this.configSelector = configSelector2;
            this.channel = channel2;
            this.method = method2;
            Executor executor = callOptions2.getExecutor() == null ? channelExecutor : callOptions2.getExecutor();
            this.callExecutor = executor;
            this.callOptions = callOptions2.withExecutor(executor);
            this.context = Context.current();
        }

        /* access modifiers changed from: protected */
        public ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        public void start(ClientCall.Listener<RespT> observer, Metadata headers) {
            InternalConfigSelector.Result result = this.configSelector.selectConfig(new PickSubchannelArgsImpl(this.method, headers, this.callOptions));
            Status status = result.getStatus();
            if (!status.isOk()) {
                executeCloseObserverInContext(observer, status);
                this.delegate = ManagedChannelImpl.NOOP_CALL;
                return;
            }
            ClientInterceptor interceptor = result.getInterceptor();
            ManagedChannelServiceConfig.MethodInfo methodInfo = ((ManagedChannelServiceConfig) result.getConfig()).getMethodConfig(this.method);
            if (methodInfo != null) {
                this.callOptions = this.callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodInfo);
            }
            if (interceptor != null) {
                this.delegate = interceptor.interceptCall(this.method, this.callOptions, this.channel);
            } else {
                this.delegate = this.channel.newCall(this.method, this.callOptions);
            }
            this.delegate.start(observer, headers);
        }

        private void executeCloseObserverInContext(final ClientCall.Listener<RespT> observer, final Status status) {
            this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    observer.onClose(status, new Metadata());
                }
            });
        }

        public void cancel(@Nullable String message, @Nullable Throwable cause) {
            ClientCall<ReqT, RespT> clientCall = this.delegate;
            if (clientCall != null) {
                clientCall.cancel(message, cause);
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.offloadExecutorHolder.release();
            this.transportFactory.close();
            this.terminated = true;
            this.terminatedLatch.countDown();
        }
    }

    /* access modifiers changed from: private */
    public void handleInternalSubchannelState(ConnectivityStateInfo newState) {
        if (newState.getState() == ConnectivityState.TRANSIENT_FAILURE || newState.getState() == ConnectivityState.IDLE) {
            refreshAndResetNameResolution();
        }
    }

    public ConnectivityState getState(boolean requestConnection) {
        ConnectivityState savedChannelState = this.channelStateManager.getState();
        if (requestConnection && savedChannelState == ConnectivityState.IDLE) {
            this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                    if (ManagedChannelImpl.this.subchannelPicker != null) {
                        ManagedChannelImpl.this.subchannelPicker.requestConnection();
                    }
                    if (ManagedChannelImpl.this.lbHelper != null) {
                        ManagedChannelImpl.this.lbHelper.f315lb.requestConnection();
                    }
                }
            });
        }
        return savedChannelState;
    }

    public void notifyWhenStateChanged(final ConnectivityState source, final Runnable callback) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelStateManager.notifyWhenStateChanged(callback, ManagedChannelImpl.this.executor, source);
            }
        });
    }

    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get()) {
                    if (ManagedChannelImpl.this.scheduledNameResolverRefresh != null && ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                        Preconditions.checkState(ManagedChannelImpl.this.nameResolverStarted, "name resolver must be started");
                        ManagedChannelImpl.this.refreshAndResetNameResolution();
                    }
                    for (InternalSubchannel subchannel : ManagedChannelImpl.this.subchannels) {
                        subchannel.resetConnectBackoff();
                    }
                    for (OobChannel oobChannel : ManagedChannelImpl.this.oobChannels) {
                        oobChannel.resetConnectBackoff();
                    }
                }
            }
        });
    }

    public void enterIdle() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get() && ManagedChannelImpl.this.lbHelper != null) {
                    ManagedChannelImpl.this.cancelIdleTimer(false);
                    ManagedChannelImpl.this.enterIdleMode();
                }
            }
        });
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$UncommittedRetriableStreamsRegistry */
    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        Status shutdownStatus;
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
            if (r0 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
            p004io.grpc.internal.ManagedChannelImpl.access$1600(r3.this$0).shutdown(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onShutdown(p004io.grpc.Status r4) {
            /*
                r3 = this;
                r0 = 0
                java.lang.Object r1 = r3.lock
                monitor-enter(r1)
                io.grpc.Status r2 = r3.shutdownStatus     // Catch:{ all -> 0x0022 }
                if (r2 == 0) goto L_0x000a
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                return
            L_0x000a:
                r3.shutdownStatus = r4     // Catch:{ all -> 0x0022 }
                java.util.Collection<io.grpc.internal.ClientStream> r2 = r3.uncommittedRetriableStreams     // Catch:{ all -> 0x0022 }
                boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0022 }
                if (r2 == 0) goto L_0x0015
                r0 = 1
            L_0x0015:
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x0021
                io.grpc.internal.ManagedChannelImpl r1 = p004io.grpc.internal.ManagedChannelImpl.this
                io.grpc.internal.DelayedClientTransport r1 = r1.delayedTransport
                r1.shutdown(r4)
            L_0x0021:
                return
            L_0x0022:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.ManagedChannelImpl.UncommittedRetriableStreamsRegistry.onShutdown(io.grpc.Status):void");
        }

        /* access modifiers changed from: package-private */
        public void onShutdownNow(Status reason) {
            Collection<ClientStream> streams;
            onShutdown(reason);
            synchronized (this.lock) {
                streams = new ArrayList<>(this.uncommittedRetriableStreams);
            }
            for (ClientStream stream : streams) {
                stream.cancel(reason);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(reason);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                Status status = this.shutdownStatus;
                if (status != null) {
                    return status;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public void remove(RetriableStream<?> retriableStream) {
            Status shutdownStatusCopy = null;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    shutdownStatusCopy = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                }
            }
            if (shutdownStatusCopy != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(shutdownStatusCopy);
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$LbHelperImpl */
    private final class LbHelperImpl extends LoadBalancer.Helper {
        boolean ignoreRefreshNsCheck;

        /* renamed from: lb */
        AutoConfiguredLoadBalancerFactory.AutoConfiguredLoadBalancer f315lb;
        boolean nsRefreshedByLb;

        private LbHelperImpl() {
        }

        public AbstractSubchannel createSubchannel(LoadBalancer.CreateSubchannelArgs args) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            return new SubchannelImpl(args, this);
        }

        public void updateBalancingState(final ConnectivityState newState, final LoadBalancer.SubchannelPicker newPicker) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkNotNull(newState, "newState");
            Preconditions.checkNotNull(newPicker, "newPicker");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        ManagedChannelImpl.this.updateSubchannelPicker(newPicker);
                        if (newState != ConnectivityState.SHUTDOWN) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering {0} state with picker: {1}", newState, newPicker);
                            ManagedChannelImpl.this.channelStateManager.gotoState(newState);
                        }
                    }
                }
            });
        }

        public void refreshNameResolution() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            this.nsRefreshedByLb = true;
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.refreshAndResetNameResolution();
                }
            });
        }

        public void ignoreRefreshNameResolutionCheck() {
            this.ignoreRefreshNsCheck = true;
        }

        public ManagedChannel createOobChannel(EquivalentAddressGroup addressGroup, String authority) {
            return createOobChannel((List<EquivalentAddressGroup>) Collections.singletonList(addressGroup), authority);
        }

        public ManagedChannel createOobChannel(List<EquivalentAddressGroup> addressGroup, String authority) {
            List<EquivalentAddressGroup> list = addressGroup;
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            long oobChannelCreationTime = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId oobLogId = InternalLogId.allocate("OobChannel", (String) null);
            InternalLogId subchannelLogId = InternalLogId.allocate("Subchannel-OOB", authority);
            ChannelTracer oobChannelTracer = new ChannelTracer(oobLogId, ManagedChannelImpl.this.maxTraceEvents, oobChannelCreationTime, "OobChannel for " + list);
            final OobChannel oobChannel = new OobChannel(authority, ManagedChannelImpl.this.balancerRpcExecutorPool, ManagedChannelImpl.this.oobTransportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.syncContext, ManagedChannelImpl.this.callTracerFactory.create(), oobChannelTracer, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.timeProvider);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child OobChannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(oobChannelCreationTime).setChannelRef(oobChannel).build());
            ChannelTracer subchannelTracer = new ChannelTracer(subchannelLogId, ManagedChannelImpl.this.maxTraceEvents, oobChannelCreationTime, "Subchannel for " + list);
            ChannelLogger subchannelLogger = new ChannelLoggerImpl(subchannelTracer, ManagedChannelImpl.this.timeProvider);
            String access$6200 = ManagedChannelImpl.this.userAgent;
            BackoffPolicy.Provider access$6300 = ManagedChannelImpl.this.backoffPolicyProvider;
            ClientTransportFactory access$5700 = ManagedChannelImpl.this.oobTransportFactory;
            ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.oobTransportFactory.getScheduledExecutorService();
            Supplier access$6400 = ManagedChannelImpl.this.stopwatchSupplier;
            SynchronizationContext synchronizationContext = ManagedChannelImpl.this.syncContext;
            AnonymousClass1ManagedOobChannelCallback r2 = new InternalSubchannel.Callback() {
                /* access modifiers changed from: package-private */
                public void onTerminated(InternalSubchannel is) {
                    ManagedChannelImpl.this.oobChannels.remove(oobChannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(is);
                    oobChannel.handleSubchannelTerminated();
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: package-private */
                public void onStateChange(InternalSubchannel is, ConnectivityStateInfo newState) {
                    ManagedChannelImpl.this.handleInternalSubchannelState(newState);
                    oobChannel.handleSubchannelStateChange(newState);
                }
            };
            InternalChannelz access$5900 = ManagedChannelImpl.this.channelz;
            ChannelTracer oobChannelTracer2 = oobChannelTracer;
            SynchronizationContext synchronizationContext2 = synchronizationContext;
            InternalSubchannel internalSubchannel = new InternalSubchannel(addressGroup, authority, access$6200, access$6300, access$5700, scheduledExecutorService, access$6400, synchronizationContext2, r2, access$5900, ManagedChannelImpl.this.callTracerFactory.create(), subchannelTracer, subchannelLogId, subchannelLogger);
            oobChannelTracer2.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(oobChannelCreationTime).setSubchannelRef(internalSubchannel).build());
            ChannelTracer channelTracer = oobChannelTracer2;
            final OobChannel oobChannel2 = oobChannel;
            ManagedChannelImpl.this.channelz.addSubchannel(oobChannel2);
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            oobChannel2.setSubchannel(internalSubchannel);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        oobChannel2.shutdown();
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.oobChannels.add(oobChannel2);
                    }
                }
            });
            return oobChannel2;
        }

        @Deprecated
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String target) {
            return createResolvingOobChannelBuilder(target, new DefaultChannelCreds()).overrideAuthority(getAuthority());
        }

        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String target, ChannelCredentials channelCreds) {
            Preconditions.checkNotNull(channelCreds, "channelCreds");
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            return ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) new ForwardingChannelBuilder<AnonymousClass1ResolvingOobChannelBuilder>(channelCreds, target) {
                final ManagedChannelBuilder<?> delegate;
                final /* synthetic */ ChannelCredentials val$channelCreds;
                final /* synthetic */ String val$target;

                {
                    CallCredentials callCredentials;
                    final ClientTransportFactory transportFactory;
                    this.val$channelCreds = r11;
                    this.val$target = r12;
                    if (r11 instanceof DefaultChannelCreds) {
                        transportFactory = ManagedChannelImpl.this.originalTransportFactory;
                        callCredentials = null;
                    } else {
                        ClientTransportFactory.SwapChannelCredentialsResult swapResult = ManagedChannelImpl.this.originalTransportFactory.swapChannelCredentials(r11);
                        if (swapResult == null) {
                            this.delegate = Grpc.newChannelBuilder(r12, r11);
                            return;
                        }
                        ClientTransportFactory transportFactory2 = swapResult.transportFactory;
                        CallCredentials callCredentials2 = swapResult.callCredentials;
                        transportFactory = transportFactory2;
                        callCredentials = callCredentials2;
                    }
                    this.delegate = new ManagedChannelImplBuilder(r12, r11, callCredentials, new ManagedChannelImplBuilder.ClientTransportFactoryBuilder(LbHelperImpl.this) {
                        public ClientTransportFactory buildClientTransportFactory() {
                            return transportFactory;
                        }
                    }, new ManagedChannelImplBuilder.FixedPortProvider(ManagedChannelImpl.this.nameResolverArgs.getDefaultPort()));
                }

                /* access modifiers changed from: protected */
                public ManagedChannelBuilder<?> delegate() {
                    return this.delegate;
                }
            }.nameResolverFactory(ManagedChannelImpl.this.nameResolverFactory)).executor(ManagedChannelImpl.this.executor)).offloadExecutor(ManagedChannelImpl.this.offloadExecutorHolder.getExecutor())).maxTraceEvents(ManagedChannelImpl.this.maxTraceEvents)).proxyDetector(ManagedChannelImpl.this.nameResolverArgs.getProxyDetector())).userAgent(ManagedChannelImpl.this.userAgent);
        }

        public ChannelCredentials getUnsafeChannelCredentials() {
            if (ManagedChannelImpl.this.originalChannelCreds == null) {
                return new DefaultChannelCreds();
            }
            return ManagedChannelImpl.this.originalChannelCreds;
        }

        public void updateOobChannelAddresses(ManagedChannel channel, EquivalentAddressGroup eag) {
            updateOobChannelAddresses(channel, (List<EquivalentAddressGroup>) Collections.singletonList(eag));
        }

        public void updateOobChannelAddresses(ManagedChannel channel, List<EquivalentAddressGroup> eag) {
            Preconditions.checkArgument(channel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel) channel).updateAddresses(eag);
        }

        public String getAuthority() {
            return ManagedChannelImpl.this.authority();
        }

        public SynchronizationContext getSynchronizationContext() {
            return ManagedChannelImpl.this.syncContext;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return ManagedChannelImpl.this.scheduledExecutor;
        }

        public ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }

        public NameResolver.Args getNameResolverArgs() {
            return ManagedChannelImpl.this.nameResolverArgs;
        }

        public NameResolverRegistry getNameResolverRegistry() {
            return ManagedChannelImpl.this.nameResolverRegistry;
        }

        /* renamed from: io.grpc.internal.ManagedChannelImpl$LbHelperImpl$DefaultChannelCreds */
        final class DefaultChannelCreds extends ChannelCredentials {
            DefaultChannelCreds() {
            }

            public ChannelCredentials withoutBearerTokens() {
                return this;
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$NameResolverListener */
    private final class NameResolverListener extends NameResolver.Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        NameResolverListener(LbHelperImpl helperImpl, NameResolver resolver2) {
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(helperImpl, "helperImpl");
            this.resolver = (NameResolver) Preconditions.checkNotNull(resolver2, "resolver");
        }

        public void onResult(final NameResolver.ResolutionResult resolutionResult) {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelServiceConfig validServiceConfig;
                    ManagedChannelServiceConfig effectiveServiceConfig;
                    ManagedChannelServiceConfig effectiveServiceConfig2;
                    List<EquivalentAddressGroup> servers = resolutionResult.getAddresses();
                    ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", servers, resolutionResult.getAttributes());
                    if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.SUCCESS) {
                        ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Address resolved: {0}", servers);
                        ResolutionState unused = ManagedChannelImpl.this.lastResolutionState = ResolutionState.SUCCESS;
                    }
                    BackoffPolicy unused2 = ManagedChannelImpl.this.nameResolverBackoffPolicy = null;
                    NameResolver.ConfigOrError configOrError = resolutionResult.getServiceConfig();
                    InternalConfigSelector resolvedConfigSelector = (InternalConfigSelector) resolutionResult.getAttributes().get(InternalConfigSelector.KEY);
                    if (configOrError == null || configOrError.getConfig() == null) {
                        validServiceConfig = null;
                    } else {
                        validServiceConfig = (ManagedChannelServiceConfig) configOrError.getConfig();
                    }
                    Status serviceConfigError = configOrError != null ? configOrError.getError() : null;
                    if (!ManagedChannelImpl.this.lookUpServiceConfig) {
                        if (validServiceConfig != null) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Service config from name resolver discarded by channel settings");
                        }
                        effectiveServiceConfig = ManagedChannelImpl.this.defaultServiceConfig == null ? ManagedChannelImpl.EMPTY_SERVICE_CONFIG : ManagedChannelImpl.this.defaultServiceConfig;
                        if (resolvedConfigSelector != null) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Config selector from name resolver discarded by channel settings");
                        }
                        ManagedChannelImpl.this.realChannel.updateConfigSelector(effectiveServiceConfig.getDefaultConfigSelector());
                    } else {
                        if (validServiceConfig != null) {
                            effectiveServiceConfig2 = validServiceConfig;
                            if (resolvedConfigSelector != null) {
                                ManagedChannelImpl.this.realChannel.updateConfigSelector(resolvedConfigSelector);
                                if (effectiveServiceConfig2.getDefaultConfigSelector() != null) {
                                    ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Method configs in service config will be discarded due to presence ofconfig-selector");
                                }
                            } else {
                                ManagedChannelImpl.this.realChannel.updateConfigSelector(effectiveServiceConfig2.getDefaultConfigSelector());
                            }
                        } else if (ManagedChannelImpl.this.defaultServiceConfig != null) {
                            effectiveServiceConfig2 = ManagedChannelImpl.this.defaultServiceConfig;
                            ManagedChannelImpl.this.realChannel.updateConfigSelector(effectiveServiceConfig2.getDefaultConfigSelector());
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Received no service config, using default service config");
                        } else if (serviceConfigError == null) {
                            ManagedChannelServiceConfig effectiveServiceConfig3 = ManagedChannelImpl.EMPTY_SERVICE_CONFIG;
                            ManagedChannelImpl.this.realChannel.updateConfigSelector((InternalConfigSelector) null);
                            effectiveServiceConfig2 = effectiveServiceConfig3;
                        } else if (!ManagedChannelImpl.this.serviceConfigUpdated) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Fallback to error due to invalid first service config without default config");
                            NameResolverListener.this.onError(configOrError.getError());
                            return;
                        } else {
                            effectiveServiceConfig2 = ManagedChannelImpl.this.lastServiceConfig;
                        }
                        if (!effectiveServiceConfig.equals(ManagedChannelImpl.this.lastServiceConfig)) {
                            ChannelLogger access$3200 = ManagedChannelImpl.this.channelLogger;
                            ChannelLogger.ChannelLogLevel channelLogLevel = ChannelLogger.ChannelLogLevel.INFO;
                            Object[] objArr = new Object[1];
                            objArr[0] = effectiveServiceConfig == ManagedChannelImpl.EMPTY_SERVICE_CONFIG ? " to empty" : "";
                            access$3200.log(channelLogLevel, "Service config changed{0}", objArr);
                            ManagedChannelServiceConfig unused3 = ManagedChannelImpl.this.lastServiceConfig = effectiveServiceConfig;
                        }
                        try {
                            boolean unused4 = ManagedChannelImpl.this.serviceConfigUpdated = true;
                        } catch (RuntimeException re) {
                            ManagedChannelImpl.logger.log(Level.WARNING, "[" + ManagedChannelImpl.this.getLogId() + "] Unexpected exception from parsing service config", re);
                        }
                    }
                    Attributes effectiveAttrs = resolutionResult.getAttributes();
                    if (NameResolverListener.this.helper == ManagedChannelImpl.this.lbHelper) {
                        Attributes.Builder attrBuilder = effectiveAttrs.toBuilder().discard(InternalConfigSelector.KEY);
                        Map<String, ?> healthCheckingConfig = effectiveServiceConfig.getHealthCheckingConfig();
                        if (healthCheckingConfig != null) {
                            attrBuilder.set(LoadBalancer.ATTR_HEALTH_CHECKING_CONFIG, healthCheckingConfig).build();
                        }
                        Status handleResult = NameResolverListener.this.helper.f315lb.tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(servers).setAttributes(attrBuilder.build()).setLoadBalancingPolicyConfig(effectiveServiceConfig.getLoadBalancingConfig()).build());
                        if (!handleResult.isOk()) {
                            NameResolverListener.this.handleErrorInSyncContext(handleResult.augmentDescription(NameResolverListener.this.resolver + " was used"));
                        }
                    }
                }
            });
        }

        public void onError(final Status error) {
            Preconditions.checkArgument(!error.isOk(), "the error status must not be OK");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    NameResolverListener.this.handleErrorInSyncContext(error);
                }
            });
        }

        /* access modifiers changed from: private */
        public void handleErrorInSyncContext(Status error) {
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), error});
            ManagedChannelImpl.this.realChannel.onConfigError();
            if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.ERROR) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.WARNING, "Failed to resolve name: {0}", error);
                ResolutionState unused = ManagedChannelImpl.this.lastResolutionState = ResolutionState.ERROR;
            }
            if (this.helper == ManagedChannelImpl.this.lbHelper) {
                this.helper.f315lb.handleNameResolutionError(error);
                scheduleExponentialBackOffInSyncContext();
            }
        }

        private void scheduleExponentialBackOffInSyncContext() {
            if (ManagedChannelImpl.this.scheduledNameResolverRefresh == null || !ManagedChannelImpl.this.scheduledNameResolverRefresh.isPending()) {
                if (ManagedChannelImpl.this.nameResolverBackoffPolicy == null) {
                    ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                    BackoffPolicy unused = managedChannelImpl.nameResolverBackoffPolicy = managedChannelImpl.backoffPolicyProvider.get();
                }
                long delayNanos = ManagedChannelImpl.this.nameResolverBackoffPolicy.nextBackoffNanos();
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(delayNanos));
                ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                SynchronizationContext.ScheduledHandle unused2 = managedChannelImpl2.scheduledNameResolverRefresh = managedChannelImpl2.syncContext.schedule(new DelayedNameResolverRefresh(), delayNanos, TimeUnit.NANOSECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$SubchannelImpl */
    private final class SubchannelImpl extends AbstractSubchannel {
        List<EquivalentAddressGroup> addressGroups;
        final LoadBalancer.CreateSubchannelArgs args;
        SynchronizationContext.ScheduledHandle delayedShutdownTask;
        final LbHelperImpl helper;
        boolean shutdown;
        boolean started;
        InternalSubchannel subchannel;
        final InternalLogId subchannelLogId;
        final ChannelLoggerImpl subchannelLogger;
        final ChannelTracer subchannelTracer;

        SubchannelImpl(LoadBalancer.CreateSubchannelArgs args2, LbHelperImpl helper2) {
            this.addressGroups = args2.getAddresses();
            if (ManagedChannelImpl.this.authorityOverride != null) {
                args2 = args2.toBuilder().setAddresses(stripOverrideAuthorityAttributes(args2.getAddresses())).build();
            }
            this.args = (LoadBalancer.CreateSubchannelArgs) Preconditions.checkNotNull(args2, "args");
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(helper2, "helper");
            InternalLogId allocate = InternalLogId.allocate("Subchannel", ManagedChannelImpl.this.authority());
            this.subchannelLogId = allocate;
            ChannelTracer channelTracer = new ChannelTracer(allocate, ManagedChannelImpl.this.maxTraceEvents, ManagedChannelImpl.this.timeProvider.currentTimeNanos(), "Subchannel for " + args2.getAddresses());
            this.subchannelTracer = channelTracer;
            this.subchannelLogger = new ChannelLoggerImpl(channelTracer, ManagedChannelImpl.this.timeProvider);
        }

        public void start(LoadBalancer.SubchannelStateListener listener) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!this.started, "already started");
            Preconditions.checkState(!this.shutdown, "already shutdown");
            Preconditions.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            this.started = true;
            List<EquivalentAddressGroup> addresses = this.args.getAddresses();
            String authority = ManagedChannelImpl.this.authority();
            String access$6200 = ManagedChannelImpl.this.userAgent;
            BackoffPolicy.Provider access$6300 = ManagedChannelImpl.this.backoffPolicyProvider;
            ClientTransportFactory access$2300 = ManagedChannelImpl.this.transportFactory;
            ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
            Supplier access$6400 = ManagedChannelImpl.this.stopwatchSupplier;
            SynchronizationContext synchronizationContext = ManagedChannelImpl.this.syncContext;
            final LoadBalancer.SubchannelStateListener subchannelStateListener = listener;
            AnonymousClass1ManagedInternalSubchannelCallback r13 = new InternalSubchannel.Callback() {
                /* access modifiers changed from: package-private */
                public void onTerminated(InternalSubchannel is) {
                    ManagedChannelImpl.this.subchannels.remove(is);
                    ManagedChannelImpl.this.channelz.removeSubchannel(is);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: package-private */
                public void onStateChange(InternalSubchannel is, ConnectivityStateInfo newState) {
                    Preconditions.checkState(subchannelStateListener != null, "listener is null");
                    subchannelStateListener.onSubchannelState(newState);
                    if ((newState.getState() == ConnectivityState.TRANSIENT_FAILURE || newState.getState() == ConnectivityState.IDLE) && !SubchannelImpl.this.helper.ignoreRefreshNsCheck && !SubchannelImpl.this.helper.nsRefreshedByLb) {
                        ManagedChannelImpl.logger.log(Level.WARNING, "LoadBalancer should call Helper.refreshNameResolution() to refresh name resolution if subchannel state becomes TRANSIENT_FAILURE or IDLE. This will no longer happen automatically in the future releases");
                        ManagedChannelImpl.this.refreshAndResetNameResolution();
                        SubchannelImpl.this.helper.nsRefreshedByLb = true;
                    }
                }

                /* access modifiers changed from: package-private */
                public void onInUse(InternalSubchannel is) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(is, true);
                }

                /* access modifiers changed from: package-private */
                public void onNotInUse(InternalSubchannel is) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(is, false);
                }
            };
            InternalChannelz access$5900 = ManagedChannelImpl.this.channelz;
            CallTracer create = ManagedChannelImpl.this.callTracerFactory.create();
            ChannelTracer channelTracer = this.subchannelTracer;
            ChannelTracer channelTracer2 = channelTracer;
            InternalSubchannel internalSubchannel = new InternalSubchannel(addresses, authority, access$6200, access$6300, access$2300, scheduledExecutorService, access$6400, synchronizationContext, r13, access$5900, create, channelTracer2, this.subchannelLogId, this.subchannelLogger);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel started").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(ManagedChannelImpl.this.timeProvider.currentTimeNanos()).setSubchannelRef(internalSubchannel).build());
            this.subchannel = internalSubchannel;
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            ManagedChannelImpl.this.subchannels.add(internalSubchannel);
        }

        /* access modifiers changed from: package-private */
        public InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel() {
            Preconditions.checkState(this.started, "not started");
            return this.subchannel;
        }

        public void shutdown() {
            SynchronizationContext.ScheduledHandle scheduledHandle;
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            if (this.subchannel == null) {
                this.shutdown = true;
                return;
            }
            if (!this.shutdown) {
                this.shutdown = true;
            } else if (ManagedChannelImpl.this.terminating && (scheduledHandle = this.delayedShutdownTask) != null) {
                scheduledHandle.cancel();
                this.delayedShutdownTask = null;
            } else {
                return;
            }
            if (!ManagedChannelImpl.this.terminating) {
                this.delayedShutdownTask = ManagedChannelImpl.this.syncContext.schedule(new LogExceptionRunnable(new Runnable() {
                    public void run() {
                        SubchannelImpl.this.subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                    }
                }), 5, TimeUnit.SECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            } else {
                this.subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
            }
        }

        public void requestConnection() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            this.subchannel.obtainActiveTransport();
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            return this.addressGroups;
        }

        public Attributes getAttributes() {
            return this.args.getAttributes();
        }

        public String toString() {
            return this.subchannelLogId.toString();
        }

        public Channel asChannel() {
            Preconditions.checkState(this.started, "not started");
            return new SubchannelChannel(this.subchannel, ManagedChannelImpl.this.balancerRpcExecutorHolder.getExecutor(), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.callTracerFactory.create(), new AtomicReference((Object) null));
        }

        public Object getInternalSubchannel() {
            Preconditions.checkState(this.started, "Subchannel is not started");
            return this.subchannel;
        }

        public ChannelLogger getChannelLogger() {
            return this.subchannelLogger;
        }

        public void updateAddresses(List<EquivalentAddressGroup> addrs) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            this.addressGroups = addrs;
            if (ManagedChannelImpl.this.authorityOverride != null) {
                addrs = stripOverrideAuthorityAttributes(addrs);
            }
            this.subchannel.updateAddresses(addrs);
        }

        private List<EquivalentAddressGroup> stripOverrideAuthorityAttributes(List<EquivalentAddressGroup> eags) {
            List<EquivalentAddressGroup> eagsWithoutOverrideAttr = new ArrayList<>();
            for (EquivalentAddressGroup eag : eags) {
                eagsWithoutOverrideAttr.add(new EquivalentAddressGroup(eag.getAddresses(), eag.getAttributes().toBuilder().discard(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE).build()));
            }
            return Collections.unmodifiableList(eagsWithoutOverrideAttr);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add(TypedValues.AttributesType.S_TARGET, (Object) this.target).toString();
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$DelayedTransportListener */
    private final class DelayedTransportListener implements ManagedClientTransport.Listener {
        private DelayedTransportListener() {
        }

        public void transportShutdown(Status s) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        public void transportReady() {
        }

        public void transportInUse(boolean inUse) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, inUse);
        }

        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            boolean unused = ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$IdleModeStateAggregator */
    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        /* access modifiers changed from: protected */
        public void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            if (!ManagedChannelImpl.this.shutdown.get()) {
                ManagedChannelImpl.this.rescheduleIdleTimer();
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$ExecutorHolder */
    private static final class ExecutorHolder {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> executorPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(executorPool, "executorPool");
        }

        /* access modifiers changed from: package-private */
        public synchronized Executor getExecutor() {
            if (this.executor == null) {
                this.executor = (Executor) Preconditions.checkNotNull((Executor) this.pool.getObject(), "%s.getObject()", (Object) this.executor);
            }
            return this.executor;
        }

        /* access modifiers changed from: package-private */
        public synchronized void release() {
            Executor executor2 = this.executor;
            if (executor2 != null) {
                this.executor = (Executor) this.pool.returnObject(executor2);
            }
        }
    }

    /* renamed from: io.grpc.internal.ManagedChannelImpl$RestrictedScheduledExecutor */
    private static final class RestrictedScheduledExecutor implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private RestrictedScheduledExecutor(ScheduledExecutorService delegate2) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(delegate2, "delegate");
        }

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            return this.delegate.schedule(callable, delay, unit);
        }

        public ScheduledFuture<?> schedule(Runnable cmd, long delay, TimeUnit unit) {
            return this.delegate.schedule(cmd, delay, unit);
        }

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            return this.delegate.scheduleAtFixedRate(command, initialDelay, period, unit);
        }

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            return this.delegate.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        }

        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.awaitTermination(timeout, unit);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return this.delegate.invokeAll(tasks);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            return this.delegate.invokeAll(tasks, timeout, unit);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            return this.delegate.invokeAny(tasks);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.delegate.invokeAny(tasks, timeout, unit);
        }

        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        public <T> Future<T> submit(Callable<T> task) {
            return this.delegate.submit(task);
        }

        public Future<?> submit(Runnable task) {
            return this.delegate.submit(task);
        }

        public <T> Future<T> submit(Runnable task, T result) {
            return this.delegate.submit(task, result);
        }

        public void execute(Runnable command) {
            this.delegate.execute(command);
        }
    }
}
