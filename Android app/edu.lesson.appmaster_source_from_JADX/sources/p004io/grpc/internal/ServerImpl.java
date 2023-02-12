package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import p004io.grpc.Attributes;
import p004io.grpc.BinaryLog;
import p004io.grpc.CompressorRegistry;
import p004io.grpc.Context;
import p004io.grpc.Contexts;
import p004io.grpc.Deadline;
import p004io.grpc.Decompressor;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.HandlerRegistry;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalInstrumented;
import p004io.grpc.InternalLogId;
import p004io.grpc.InternalServer;
import p004io.grpc.InternalServerInterceptors;
import p004io.grpc.Metadata;
import p004io.grpc.Server;
import p004io.grpc.ServerCall;
import p004io.grpc.ServerCallExecutorSupplier;
import p004io.grpc.ServerCallHandler;
import p004io.grpc.ServerInterceptor;
import p004io.grpc.ServerMethodDefinition;
import p004io.grpc.ServerServiceDefinition;
import p004io.grpc.ServerTransportFilter;
import p004io.grpc.Status;
import p004io.grpc.internal.StreamListener;
import p004io.perfmark.Link;
import p004io.perfmark.PerfMark;
import p004io.perfmark.Tag;

/* renamed from: io.grpc.internal.ServerImpl */
public final class ServerImpl extends Server implements InternalInstrumented<InternalChannelz.ServerStats> {
    /* access modifiers changed from: private */
    public static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    /* access modifiers changed from: private */
    public final BinaryLog binlog;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    public Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public final ServerCallExecutorSupplier executorSupplier;
    /* access modifiers changed from: private */
    public final HandlerRegistry fallbackRegistry;
    /* access modifiers changed from: private */
    public final long handshakeTimeoutMillis;
    /* access modifiers changed from: private */
    public final ServerInterceptor[] interceptors;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public final HandlerRegistry registry;
    /* access modifiers changed from: private */
    public final Context rootContext;
    /* access modifiers changed from: private */
    public final CallTracer serverCallTracer;
    /* access modifiers changed from: private */
    public boolean serverShutdownCallbackInvoked;
    private boolean shutdown;
    /* access modifiers changed from: private */
    public Status shutdownNowStatus;
    private boolean started;
    private boolean terminated;
    /* access modifiers changed from: private */
    public final Deadline.Ticker ticker;
    /* access modifiers changed from: private */
    public final List<ServerTransportFilter> transportFilters;
    private final InternalServer transportServer;
    /* access modifiers changed from: private */
    public boolean transportServersTerminated;
    /* access modifiers changed from: private */
    public final Set<ServerTransport> transports = new HashSet();

    ServerImpl(ServerImplBuilder builder, InternalServer transportServer2, Context rootContext2) {
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(builder.executorPool, "executorPool");
        this.registry = (HandlerRegistry) Preconditions.checkNotNull(builder.registryBuilder.build(), "registryBuilder");
        this.fallbackRegistry = (HandlerRegistry) Preconditions.checkNotNull(builder.fallbackRegistry, "fallbackRegistry");
        this.transportServer = (InternalServer) Preconditions.checkNotNull(transportServer2, "transportServer");
        this.logId = InternalLogId.allocate(HttpHeaders.SERVER, String.valueOf(getListenSocketsIgnoringLifecycle()));
        this.rootContext = ((Context) Preconditions.checkNotNull(rootContext2, "rootContext")).fork();
        this.decompressorRegistry = builder.decompressorRegistry;
        this.compressorRegistry = builder.compressorRegistry;
        this.transportFilters = Collections.unmodifiableList(new ArrayList(builder.transportFilters));
        this.interceptors = (ServerInterceptor[]) builder.interceptors.toArray(new ServerInterceptor[builder.interceptors.size()]);
        this.handshakeTimeoutMillis = builder.handshakeTimeoutMillis;
        this.binlog = builder.binlog;
        InternalChannelz internalChannelz = builder.channelz;
        this.channelz = internalChannelz;
        this.serverCallTracer = builder.callTracerFactory.create();
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(builder.ticker, "ticker");
        internalChannelz.addServer(this);
        this.executorSupplier = builder.executorSupplier;
    }

    public ServerImpl start() throws IOException {
        synchronized (this.lock) {
            boolean z = false;
            Preconditions.checkState(!this.started, "Already started");
            if (!this.shutdown) {
                z = true;
            }
            Preconditions.checkState(z, "Shutting down");
            this.transportServer.start(new ServerListenerImpl());
            this.executor = (Executor) Preconditions.checkNotNull((Executor) this.executorPool.getObject(), "executor");
            this.started = true;
        }
        return this;
    }

    public int getPort() {
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            for (SocketAddress addr : this.transportServer.getListenSocketAddresses()) {
                if (addr instanceof InetSocketAddress) {
                    int port = ((InetSocketAddress) addr).getPort();
                    return port;
                }
            }
            return -1;
        }
    }

    public List<SocketAddress> getListenSockets() {
        List<SocketAddress> listenSocketsIgnoringLifecycle;
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            listenSocketsIgnoringLifecycle = getListenSocketsIgnoringLifecycle();
        }
        return listenSocketsIgnoringLifecycle;
    }

    private List<SocketAddress> getListenSocketsIgnoringLifecycle() {
        List<SocketAddress> unmodifiableList;
        synchronized (this.lock) {
            unmodifiableList = Collections.unmodifiableList(this.transportServer.getListenSocketAddresses());
        }
        return unmodifiableList;
    }

    public List<ServerServiceDefinition> getServices() {
        List<ServerServiceDefinition> fallbackServices = this.fallbackRegistry.getServices();
        if (fallbackServices.isEmpty()) {
            return this.registry.getServices();
        }
        List<ServerServiceDefinition> registryServices = this.registry.getServices();
        List<ServerServiceDefinition> services = new ArrayList<>(registryServices.size() + fallbackServices.size());
        services.addAll(registryServices);
        services.addAll(fallbackServices);
        return Collections.unmodifiableList(services);
    }

    public List<ServerServiceDefinition> getImmutableServices() {
        return this.registry.getServices();
    }

    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.unmodifiableList(this.fallbackRegistry.getServices());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r2 == false) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r3.transportServer.shutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p004io.grpc.internal.ServerImpl shutdown() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            boolean r1 = r3.shutdown     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return r3
        L_0x0009:
            r1 = 1
            r3.shutdown = r1     // Catch:{ all -> 0x001e }
            boolean r2 = r3.started     // Catch:{ all -> 0x001e }
            if (r2 != 0) goto L_0x0015
            r3.transportServersTerminated = r1     // Catch:{ all -> 0x001e }
            r3.checkForTermination()     // Catch:{ all -> 0x001e }
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x001d
            io.grpc.internal.InternalServer r0 = r3.transportServer
            r0.shutdown()
        L_0x001d:
            return r3
        L_0x001e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.ServerImpl.shutdown():io.grpc.internal.ServerImpl");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        r1 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r1.hasNext() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r1.next().shutdownNow(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r3 == false) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p004io.grpc.internal.ServerImpl shutdownNow() {
        /*
            r5 = this;
            r5.shutdown()
            io.grpc.Status r0 = p004io.grpc.Status.UNAVAILABLE
            java.lang.String r1 = "Server shutdownNow invoked"
            io.grpc.Status r0 = r0.withDescription(r1)
            java.lang.Object r1 = r5.lock
            monitor-enter(r1)
            io.grpc.Status r2 = r5.shutdownNowStatus     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            return r5
        L_0x0014:
            r5.shutdownNowStatus = r0     // Catch:{ all -> 0x0037 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0037 }
            java.util.Set<io.grpc.internal.ServerTransport> r3 = r5.transports     // Catch:{ all -> 0x0037 }
            r2.<init>(r3)     // Catch:{ all -> 0x0037 }
            boolean r3 = r5.serverShutdownCallbackInvoked     // Catch:{ all -> 0x0037 }
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0036
            java.util.Iterator r1 = r2.iterator()
        L_0x0026:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0036
            java.lang.Object r4 = r1.next()
            io.grpc.internal.ServerTransport r4 = (p004io.grpc.internal.ServerTransport) r4
            r4.shutdownNow(r0)
            goto L_0x0026
        L_0x0036:
            return r5
        L_0x0037:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.ServerImpl.shutdownNow():io.grpc.internal.ServerImpl");
    }

    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        boolean z;
        synchronized (this.lock) {
            long endTimeNanos = System.nanoTime() + unit.toNanos(timeout);
            while (!this.terminated) {
                long nanoTime = endTimeNanos - System.nanoTime();
                long timeoutNanos = nanoTime;
                if (nanoTime <= 0) {
                    break;
                }
                TimeUnit.NANOSECONDS.timedWait(this.lock, timeoutNanos);
            }
            z = this.terminated;
        }
        return z;
    }

    public void awaitTermination() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.terminated) {
                this.lock.wait();
            }
        }
    }

    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            z = this.terminated;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void transportClosed(ServerTransport transport) {
        synchronized (this.lock) {
            if (this.transports.remove(transport)) {
                this.channelz.removeServerSocket(this, transport);
                checkForTermination();
            } else {
                throw new AssertionError("Transport already removed");
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkForTermination() {
        synchronized (this.lock) {
            if (this.shutdown && this.transports.isEmpty() && this.transportServersTerminated) {
                if (!this.terminated) {
                    this.terminated = true;
                    this.channelz.removeServer(this);
                    Executor executor2 = this.executor;
                    if (executor2 != null) {
                        this.executor = (Executor) this.executorPool.returnObject(executor2);
                    }
                    this.lock.notifyAll();
                } else {
                    throw new AssertionError("Server already terminated");
                }
            }
        }
    }

    /* renamed from: io.grpc.internal.ServerImpl$ServerListenerImpl */
    private final class ServerListenerImpl implements ServerListener {
        private ServerListenerImpl() {
        }

        public ServerTransportListener transportCreated(ServerTransport transport) {
            synchronized (ServerImpl.this.lock) {
                ServerImpl.this.transports.add(transport);
            }
            ServerTransportListenerImpl stli = new ServerTransportListenerImpl(transport);
            stli.init();
            return stli;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
            if (r0.hasNext() == false) goto L_0x0043;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
            r3 = r0.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
            if (r2 != null) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
            r3.shutdown();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
            r3.shutdownNow(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
            r3 = p004io.grpc.internal.ServerImpl.access$200(r5.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
            monitor-enter(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            p004io.grpc.internal.ServerImpl.access$602(r5.this$0, true);
            p004io.grpc.internal.ServerImpl.access$700(r5.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
            monitor-exit(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
            r0 = r1.iterator();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serverShutdown() {
            /*
                r5 = this;
                io.grpc.internal.ServerImpl r0 = p004io.grpc.internal.ServerImpl.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.internal.ServerImpl r1 = p004io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                boolean r1 = r1.serverShutdownCallbackInvoked     // Catch:{ all -> 0x0059 }
                if (r1 == 0) goto L_0x0011
                monitor-exit(r0)     // Catch:{ all -> 0x0059 }
                return
            L_0x0011:
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0059 }
                io.grpc.internal.ServerImpl r2 = p004io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                java.util.Set r2 = r2.transports     // Catch:{ all -> 0x0059 }
                r1.<init>(r2)     // Catch:{ all -> 0x0059 }
                io.grpc.internal.ServerImpl r2 = p004io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                io.grpc.Status r2 = r2.shutdownNowStatus     // Catch:{ all -> 0x0059 }
                io.grpc.internal.ServerImpl r3 = p004io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                r4 = 1
                boolean unused = r3.serverShutdownCallbackInvoked = r4     // Catch:{ all -> 0x0059 }
                monitor-exit(r0)     // Catch:{ all -> 0x0059 }
                java.util.Iterator r0 = r1.iterator()
            L_0x002d:
                boolean r3 = r0.hasNext()
                if (r3 == 0) goto L_0x0043
                java.lang.Object r3 = r0.next()
                io.grpc.internal.ServerTransport r3 = (p004io.grpc.internal.ServerTransport) r3
                if (r2 != 0) goto L_0x003f
                r3.shutdown()
                goto L_0x0042
            L_0x003f:
                r3.shutdownNow(r2)
            L_0x0042:
                goto L_0x002d
            L_0x0043:
                io.grpc.internal.ServerImpl r0 = p004io.grpc.internal.ServerImpl.this
                java.lang.Object r3 = r0.lock
                monitor-enter(r3)
                io.grpc.internal.ServerImpl r0 = p004io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0056 }
                boolean unused = r0.transportServersTerminated = r4     // Catch:{ all -> 0x0056 }
                io.grpc.internal.ServerImpl r0 = p004io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0056 }
                r0.checkForTermination()     // Catch:{ all -> 0x0056 }
                monitor-exit(r3)     // Catch:{ all -> 0x0056 }
                return
            L_0x0056:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0056 }
                throw r0
            L_0x0059:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0059 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.ServerImpl.ServerListenerImpl.serverShutdown():void");
        }
    }

    /* renamed from: io.grpc.internal.ServerImpl$ServerTransportListenerImpl */
    private final class ServerTransportListenerImpl implements ServerTransportListener {
        private Attributes attributes;
        private Future<?> handshakeTimeoutFuture;
        /* access modifiers changed from: private */
        public final ServerTransport transport;

        ServerTransportListenerImpl(ServerTransport transport2) {
            this.transport = transport2;
        }

        public void init() {
            if (ServerImpl.this.handshakeTimeoutMillis != Long.MAX_VALUE) {
                this.handshakeTimeoutFuture = this.transport.getScheduledExecutorService().schedule(new Runnable() {
                    public void run() {
                        ServerTransportListenerImpl.this.transport.shutdownNow(Status.CANCELLED.withDescription("Handshake timeout exceeded"));
                    }
                }, ServerImpl.this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
            } else {
                this.handshakeTimeoutFuture = new FutureTask(new Runnable() {
                    public void run() {
                    }
                }, (Object) null);
            }
            ServerImpl.this.channelz.addServerSocket(ServerImpl.this, this.transport);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: io.grpc.Attributes} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public p004io.grpc.Attributes transportReady(p004io.grpc.Attributes r5) {
            /*
                r4 = this;
                java.util.concurrent.Future<?> r0 = r4.handshakeTimeoutFuture
                r1 = 0
                r0.cancel(r1)
                r0 = 0
                r4.handshakeTimeoutFuture = r0
                io.grpc.internal.ServerImpl r0 = p004io.grpc.internal.ServerImpl.this
                java.util.List r0 = r0.transportFilters
                java.util.Iterator r0 = r0.iterator()
            L_0x0013:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x002d
                java.lang.Object r1 = r0.next()
                io.grpc.ServerTransportFilter r1 = (p004io.grpc.ServerTransportFilter) r1
                io.grpc.Attributes r2 = r1.transportReady(r5)
                java.lang.String r3 = "Filter %s returned null"
                java.lang.Object r2 = com.google.common.base.Preconditions.checkNotNull(r2, (java.lang.String) r3, (java.lang.Object) r1)
                r5 = r2
                io.grpc.Attributes r5 = (p004io.grpc.Attributes) r5
                goto L_0x0013
            L_0x002d:
                r4.attributes = r5
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.ServerImpl.ServerTransportListenerImpl.transportReady(io.grpc.Attributes):io.grpc.Attributes");
        }

        public void transportTerminated() {
            Future<?> future = this.handshakeTimeoutFuture;
            if (future != null) {
                future.cancel(false);
                this.handshakeTimeoutFuture = null;
            }
            for (ServerTransportFilter filter : ServerImpl.this.transportFilters) {
                filter.transportTerminated(this.attributes);
            }
            ServerImpl.this.transportClosed(this.transport);
        }

        public void streamCreated(ServerStream stream, String methodName, Metadata headers) {
            Tag tag = PerfMark.createTag(methodName, (long) stream.streamId());
            PerfMark.startTask("ServerTransportListener.streamCreated", tag);
            try {
                streamCreatedInternal(stream, methodName, headers, tag);
            } finally {
                PerfMark.stopTask("ServerTransportListener.streamCreated", tag);
            }
        }

        private void streamCreatedInternal(ServerStream stream, String methodName, Metadata headers, Tag tag) {
            Executor wrappedExecutor;
            ServerStream serverStream = stream;
            Metadata metadata = headers;
            if (ServerImpl.this.executorSupplier == null && ServerImpl.this.executor == MoreExecutors.directExecutor()) {
                Executor serializeReentrantCallsDirectExecutor = new SerializeReentrantCallsDirectExecutor();
                stream.optimizeForDirectExecutor();
                wrappedExecutor = serializeReentrantCallsDirectExecutor;
            } else {
                wrappedExecutor = new SerializingExecutor(ServerImpl.this.executor);
            }
            if (metadata.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
                String encoding = (String) metadata.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                Decompressor decompressor = ServerImpl.this.decompressorRegistry.lookupDecompressor(encoding);
                if (decompressor == null) {
                    serverStream.setListener(ServerImpl.NOOP_LISTENER);
                    serverStream.close(Status.UNIMPLEMENTED.withDescription(String.format("Can't find decompressor for %s", new Object[]{encoding})), new Metadata());
                    return;
                }
                serverStream.setDecompressor(decompressor);
            }
            StatsTraceContext statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(stream.statsTraceContext(), "statsTraceCtx not present from stream");
            Context.CancellableContext context = createContext(metadata, statsTraceCtx);
            Link link = PerfMark.linkOut();
            JumpToApplicationThreadServerStreamListener jumpListener = new JumpToApplicationThreadServerStreamListener(wrappedExecutor, ServerImpl.this.executor, stream, context, tag);
            serverStream.setListener(jumpListener);
            SettableFuture<ServerCallParameters<?, ?>> future = SettableFuture.create();
            Context.CancellableContext cancellableContext = context;
            Tag tag2 = tag;
            Link link2 = link;
            AnonymousClass1MethodLookup r12 = r0;
            StatsTraceContext statsTraceContext = statsTraceCtx;
            AnonymousClass1MethodLookup r0 = new ContextRunnable(cancellableContext, tag2, link2, methodName, stream, jumpListener, future, statsTraceCtx, headers, wrappedExecutor) {
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ SettableFuture val$future;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ StatsTraceContext val$statsTraceCtx;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;
                final /* synthetic */ Executor val$wrappedExecutor;

                {
                    this.val$context = r2;
                    this.val$tag = r3;
                    this.val$link = r4;
                    this.val$methodName = r5;
                    this.val$stream = r6;
                    this.val$jumpListener = r7;
                    this.val$future = r8;
                    this.val$statsTraceCtx = r9;
                    this.val$headers = r10;
                    this.val$wrappedExecutor = r11;
                }

                public void runInContext() {
                    PerfMark.startTask("ServerTransportListener$MethodLookup.startCall", this.val$tag);
                    PerfMark.linkIn(this.val$link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ServerTransportListener$MethodLookup.startCall", this.val$tag);
                    }
                }

                private void runInternal() {
                    try {
                        ServerMethodDefinition<?, ?> method = ServerImpl.this.registry.lookupMethod(this.val$methodName);
                        if (method == null) {
                            method = ServerImpl.this.fallbackRegistry.lookupMethod(this.val$methodName, this.val$stream.getAuthority());
                        }
                        if (method == null) {
                            Status status = Status.UNIMPLEMENTED.withDescription("Method not found: " + this.val$methodName);
                            this.val$jumpListener.setListener(ServerImpl.NOOP_LISTENER);
                            this.val$stream.close(status, new Metadata());
                            this.val$context.cancel((Throwable) null);
                            this.val$future.cancel(false);
                            return;
                        }
                        this.val$future.set(maySwitchExecutor(ServerTransportListenerImpl.this.wrapMethod(this.val$stream, method, this.val$statsTraceCtx), this.val$stream, this.val$headers, this.val$context, this.val$tag));
                    } catch (Throwable t) {
                        this.val$jumpListener.setListener(ServerImpl.NOOP_LISTENER);
                        this.val$stream.close(Status.fromThrowable(t), new Metadata());
                        this.val$context.cancel((Throwable) null);
                        this.val$future.cancel(false);
                        throw t;
                    }
                }

                private <ReqT, RespT> ServerCallParameters<ReqT, RespT> maySwitchExecutor(ServerMethodDefinition<ReqT, RespT> methodDef, ServerStream stream, Metadata headers, Context.CancellableContext context, Tag tag) {
                    Executor switchingExecutor;
                    ServerCallImpl<ReqT, RespT> call = new ServerCallImpl<>(stream, methodDef.getMethodDescriptor(), headers, context, ServerImpl.this.decompressorRegistry, ServerImpl.this.compressorRegistry, ServerImpl.this.serverCallTracer, tag);
                    if (!(ServerImpl.this.executorSupplier == null || (switchingExecutor = ServerImpl.this.executorSupplier.getExecutor(call, headers)) == null)) {
                        ((SerializingExecutor) this.val$wrappedExecutor).setExecutor(switchingExecutor);
                    }
                    return new ServerCallParameters<>(call, methodDef.getServerCallHandler());
                }
            };
            wrappedExecutor.execute(r12);
            wrappedExecutor.execute(new ContextRunnable(cancellableContext, tag2, link2, future, methodName, headers, stream, jumpListener) {
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ SettableFuture val$future;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;

                {
                    this.val$context = r2;
                    this.val$tag = r3;
                    this.val$link = r4;
                    this.val$future = r5;
                    this.val$methodName = r6;
                    this.val$headers = r7;
                    this.val$stream = r8;
                    this.val$jumpListener = r9;
                }

                public void runInContext() {
                    PerfMark.startTask("ServerTransportListener$HandleServerCall.startCall", this.val$tag);
                    PerfMark.linkIn(this.val$link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ServerTransportListener$HandleServerCall.startCall", this.val$tag);
                    }
                }

                private void runInternal() {
                    ServerStreamListener listener = ServerImpl.NOOP_LISTENER;
                    if (!this.val$future.isCancelled()) {
                        try {
                            this.val$jumpListener.setListener(ServerTransportListenerImpl.this.startWrappedCall(this.val$methodName, (ServerCallParameters) Futures.getDone(this.val$future), this.val$headers));
                            this.val$context.addListener(new Context.CancellationListener() {
                                public void cancelled(Context context) {
                                    Status status = Contexts.statusFromCancelled(context);
                                    if (Status.DEADLINE_EXCEEDED.getCode().equals(status.getCode())) {
                                        AnonymousClass1HandleServerCall.this.val$stream.cancel(status);
                                    }
                                }
                            }, MoreExecutors.directExecutor());
                        } catch (Throwable ex) {
                            this.val$jumpListener.setListener(listener);
                            throw ex;
                        }
                    }
                }
            });
        }

        private Context.CancellableContext createContext(Metadata headers, StatsTraceContext statsTraceCtx) {
            Long timeoutNanos = (Long) headers.get(GrpcUtil.TIMEOUT_KEY);
            Context baseContext = statsTraceCtx.serverFilterContext(ServerImpl.this.rootContext).withValue(InternalServer.SERVER_CONTEXT_KEY, ServerImpl.this);
            if (timeoutNanos == null) {
                return baseContext.withCancellation();
            }
            return baseContext.withDeadline(Deadline.after(timeoutNanos.longValue(), TimeUnit.NANOSECONDS, ServerImpl.this.ticker), this.transport.getScheduledExecutorService());
        }

        /* access modifiers changed from: private */
        public <ReqT, RespT> ServerMethodDefinition<?, ?> wrapMethod(ServerStream stream, ServerMethodDefinition<ReqT, RespT> methodDef, StatsTraceContext statsTraceCtx) {
            statsTraceCtx.serverCallStarted(new ServerCallInfoImpl(methodDef.getMethodDescriptor(), stream.getAttributes(), stream.getAuthority()));
            ServerCallHandler<ReqT, RespT> handler = methodDef.getServerCallHandler();
            for (ServerInterceptor interceptor : ServerImpl.this.interceptors) {
                handler = InternalServerInterceptors.interceptCallHandlerCreate(interceptor, handler);
            }
            ServerMethodDefinition<ReqT, RespT> interceptedDef = methodDef.withServerCallHandler(handler);
            return ServerImpl.this.binlog == null ? interceptedDef : ServerImpl.this.binlog.wrapMethodDefinition(interceptedDef);
        }

        /* renamed from: io.grpc.internal.ServerImpl$ServerTransportListenerImpl$ServerCallParameters */
        private final class ServerCallParameters<ReqT, RespT> {
            ServerCallImpl<ReqT, RespT> call;
            ServerCallHandler<ReqT, RespT> callHandler;

            public ServerCallParameters(ServerCallImpl<ReqT, RespT> call2, ServerCallHandler<ReqT, RespT> callHandler2) {
                this.call = call2;
                this.callHandler = callHandler2;
            }
        }

        /* access modifiers changed from: private */
        public <WReqT, WRespT> ServerStreamListener startWrappedCall(String fullMethodName, ServerCallParameters<WReqT, WRespT> params, Metadata headers) {
            ServerCall.Listener<ReqT> startCall = params.callHandler.startCall(params.call, headers);
            if (startCall != null) {
                return params.call.newServerStreamListener(startCall);
            }
            throw new NullPointerException("startCall() returned a null listener for method " + fullMethodName);
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public ListenableFuture<InternalChannelz.ServerStats> getStats() {
        InternalChannelz.ServerStats.Builder builder = new InternalChannelz.ServerStats.Builder();
        List<InternalInstrumented<InternalChannelz.SocketStats>> stats = this.transportServer.getListenSocketStatsList();
        if (stats != null) {
            builder.addListenSockets(stats);
        }
        this.serverCallTracer.updateBuilder(builder);
        SettableFuture<InternalChannelz.ServerStats> ret = SettableFuture.create();
        ret.set(builder.build());
        return ret;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("transportServer", (Object) this.transportServer).toString();
    }

    /* renamed from: io.grpc.internal.ServerImpl$NoopListener */
    private static final class NoopListener implements ServerStreamListener {
        private NoopListener() {
        }

        public void messagesAvailable(StreamListener.MessageProducer producer) {
            while (true) {
                InputStream next = producer.next();
                InputStream message = next;
                if (next != null) {
                    try {
                        message.close();
                    } catch (IOException e) {
                        while (true) {
                            InputStream next2 = producer.next();
                            InputStream message2 = next2;
                            if (next2 != null) {
                                try {
                                    message2.close();
                                } catch (IOException ioException) {
                                    ServerImpl.log.log(Level.WARNING, "Exception closing stream", ioException);
                                }
                            } else {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }

        public void halfClosed() {
        }

        public void closed(Status status) {
        }

        public void onReady() {
        }
    }

    /* renamed from: io.grpc.internal.ServerImpl$JumpToApplicationThreadServerStreamListener */
    static final class JumpToApplicationThreadServerStreamListener implements ServerStreamListener {
        private final Executor callExecutor;
        private final Executor cancelExecutor;
        /* access modifiers changed from: private */
        public final Context.CancellableContext context;
        private ServerStreamListener listener;
        private final ServerStream stream;
        /* access modifiers changed from: private */
        public final Tag tag;

        public JumpToApplicationThreadServerStreamListener(Executor executor, Executor cancelExecutor2, ServerStream stream2, Context.CancellableContext context2, Tag tag2) {
            this.callExecutor = executor;
            this.cancelExecutor = cancelExecutor2;
            this.stream = stream2;
            this.context = context2;
            this.tag = tag2;
        }

        /* access modifiers changed from: private */
        public ServerStreamListener getListener() {
            ServerStreamListener serverStreamListener = this.listener;
            if (serverStreamListener != null) {
                return serverStreamListener;
            }
            throw new IllegalStateException("listener unset");
        }

        /* access modifiers changed from: package-private */
        public void setListener(ServerStreamListener listener2) {
            Preconditions.checkNotNull(listener2, "listener must not be null");
            Preconditions.checkState(this.listener == null, "Listener already set");
            this.listener = listener2;
        }

        /* access modifiers changed from: private */
        public void internalClose(Throwable t) {
            this.stream.close(Status.UNKNOWN.withCause(t), new Metadata());
        }

        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", this.tag);
            final Link link = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).messagesAvailable", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().messagesAvailable(producer);
                            PerfMark.stopTask("ServerCallListener(app).messagesAvailable", JumpToApplicationThreadServerStreamListener.this.tag);
                        } catch (Throwable t) {
                            PerfMark.stopTask("ServerCallListener(app).messagesAvailable", JumpToApplicationThreadServerStreamListener.this.tag);
                            throw t;
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", this.tag);
            }
        }

        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", this.tag);
            final Link link = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).halfClosed", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().halfClosed();
                            PerfMark.stopTask("ServerCallListener(app).halfClosed", JumpToApplicationThreadServerStreamListener.this.tag);
                        } catch (Throwable t) {
                            PerfMark.stopTask("ServerCallListener(app).halfClosed", JumpToApplicationThreadServerStreamListener.this.tag);
                            throw t;
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", this.tag);
            }
        }

        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", this.tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", this.tag);
            }
        }

        private void closedInternal(final Status status) {
            if (!status.isOk()) {
                this.cancelExecutor.execute(new ContextCloser(this.context, status.getCause()));
            }
            final Link link = PerfMark.linkOut();
            this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    PerfMark.startTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    PerfMark.linkIn(link);
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().closed(status);
                    } finally {
                        PerfMark.stopTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    }
                }
            });
        }

        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", this.tag);
            final Link link = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).onReady", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().onReady();
                            PerfMark.stopTask("ServerCallListener(app).onReady", JumpToApplicationThreadServerStreamListener.this.tag);
                        } catch (Throwable t) {
                            PerfMark.stopTask("ServerCallListener(app).onReady", JumpToApplicationThreadServerStreamListener.this.tag);
                            throw t;
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.onReady", this.tag);
            }
        }
    }

    /* renamed from: io.grpc.internal.ServerImpl$ContextCloser */
    static final class ContextCloser implements Runnable {
        private final Throwable cause;
        private final Context.CancellableContext context;

        ContextCloser(Context.CancellableContext context2, Throwable cause2) {
            this.context = context2;
            this.cause = cause2;
        }

        public void run() {
            this.context.cancel(this.cause);
        }
    }
}
