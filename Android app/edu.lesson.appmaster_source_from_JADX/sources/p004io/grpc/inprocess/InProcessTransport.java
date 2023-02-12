package p004io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.InputStream;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Compressor;
import p004io.grpc.Deadline;
import p004io.grpc.Decompressor;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.Grpc;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalLogId;
import p004io.grpc.InternalMetadata;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.SecurityLevel;
import p004io.grpc.ServerStreamTracer;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientStream;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.ClientTransport;
import p004io.grpc.internal.ConnectionClientTransport;
import p004io.grpc.internal.GrpcAttributes;
import p004io.grpc.internal.GrpcUtil;
import p004io.grpc.internal.InUseStateAggregator;
import p004io.grpc.internal.InsightBuilder;
import p004io.grpc.internal.ManagedClientTransport;
import p004io.grpc.internal.NoopClientStream;
import p004io.grpc.internal.ObjectPool;
import p004io.grpc.internal.ServerListener;
import p004io.grpc.internal.ServerStream;
import p004io.grpc.internal.ServerStreamListener;
import p004io.grpc.internal.ServerTransport;
import p004io.grpc.internal.ServerTransportListener;
import p004io.grpc.internal.StatsTraceContext;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.inprocess.InProcessTransport */
final class InProcessTransport implements ServerTransport, ConnectionClientTransport {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(InProcessTransport.class.getName());
    /* access modifiers changed from: private */
    public final SocketAddress address;
    /* access modifiers changed from: private */
    public final Attributes attributes;
    private final String authority;
    /* access modifiers changed from: private */
    public final int clientMaxInboundMetadataSize;
    /* access modifiers changed from: private */
    public ManagedClientTransport.Listener clientTransportListener;
    /* access modifiers changed from: private */
    public final InUseStateAggregator<InProcessStream> inUseState;
    /* access modifiers changed from: private */
    public final boolean includeCauseWithStatus;
    private final InternalLogId logId;
    private final Optional<ServerListener> optionalServerListener;
    private int serverMaxInboundMetadataSize;
    private ScheduledExecutorService serverScheduler;
    private ObjectPool<ScheduledExecutorService> serverSchedulerPool;
    /* access modifiers changed from: private */
    public Attributes serverStreamAttributes;
    /* access modifiers changed from: private */
    public List<ServerStreamTracer.Factory> serverStreamTracerFactories;
    /* access modifiers changed from: private */
    public ServerTransportListener serverTransportListener;
    /* access modifiers changed from: private */
    public boolean shutdown;
    private Status shutdownStatus;
    /* access modifiers changed from: private */
    public final Set<InProcessStream> streams;
    private boolean terminated;
    private final String userAgent;

    private InProcessTransport(SocketAddress address2, int maxInboundMetadataSize, String authority2, String userAgent2, Attributes eagAttrs, Optional<ServerListener> optionalServerListener2, boolean includeCauseWithStatus2) {
        this.streams = Collections.newSetFromMap(new IdentityHashMap());
        this.inUseState = new InUseStateAggregator<InProcessStream>() {
            /* access modifiers changed from: protected */
            public void handleInUse() {
                InProcessTransport.this.clientTransportListener.transportInUse(true);
            }

            /* access modifiers changed from: protected */
            public void handleNotInUse() {
                InProcessTransport.this.clientTransportListener.transportInUse(false);
            }
        };
        this.address = address2;
        this.clientMaxInboundMetadataSize = maxInboundMetadataSize;
        this.authority = authority2;
        this.userAgent = GrpcUtil.getGrpcUserAgent("inprocess", userAgent2);
        Preconditions.checkNotNull(eagAttrs, "eagAttrs");
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, eagAttrs).set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, address2).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, address2).build();
        this.optionalServerListener = optionalServerListener2;
        this.logId = InternalLogId.allocate(getClass(), address2.toString());
        this.includeCauseWithStatus = includeCauseWithStatus2;
    }

    public InProcessTransport(SocketAddress address2, int maxInboundMetadataSize, String authority2, String userAgent2, Attributes eagAttrs, boolean includeCauseWithStatus2) {
        this(address2, maxInboundMetadataSize, authority2, userAgent2, eagAttrs, Optional.absent(), includeCauseWithStatus2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    InProcessTransport(String name, int maxInboundMetadataSize, String authority2, String userAgent2, Attributes eagAttrs, ObjectPool<ScheduledExecutorService> serverSchedulerPool2, List<ServerStreamTracer.Factory> serverStreamTracerFactories2, ServerListener serverListener) {
        this(new InProcessSocketAddress(name), maxInboundMetadataSize, authority2, userAgent2, eagAttrs, Optional.m12of(serverListener), false);
        String str = name;
        this.serverMaxInboundMetadataSize = maxInboundMetadataSize;
        this.serverSchedulerPool = serverSchedulerPool2;
        this.serverStreamTracerFactories = serverStreamTracerFactories2;
    }

    @CheckReturnValue
    public synchronized Runnable start(ManagedClientTransport.Listener listener) {
        this.clientTransportListener = listener;
        if (this.optionalServerListener.isPresent()) {
            this.serverScheduler = this.serverSchedulerPool.getObject();
            this.serverTransportListener = this.optionalServerListener.get().transportCreated(this);
        } else {
            InProcessServer server = InProcessServer.findServer(this.address);
            if (server != null) {
                this.serverMaxInboundMetadataSize = server.getMaxInboundMetadataSize();
                ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool = server.getScheduledExecutorServicePool();
                this.serverSchedulerPool = scheduledExecutorServicePool;
                this.serverScheduler = scheduledExecutorServicePool.getObject();
                this.serverStreamTracerFactories = server.getStreamTracerFactories();
                this.serverTransportListener = server.register(this);
            }
        }
        if (this.serverTransportListener == null) {
            final Status localShutdownStatus = Status.UNAVAILABLE.withDescription("Could not find server: " + this.address);
            this.shutdownStatus = localShutdownStatus;
            return new Runnable() {
                public void run() {
                    synchronized (InProcessTransport.this) {
                        InProcessTransport.this.notifyShutdown(localShutdownStatus);
                        InProcessTransport.this.notifyTerminated();
                    }
                }
            };
        }
        return new Runnable() {
            public void run() {
                synchronized (InProcessTransport.this) {
                    Attributes serverTransportAttrs = Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, InProcessTransport.this.address).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, InProcessTransport.this.address).build();
                    InProcessTransport inProcessTransport = InProcessTransport.this;
                    Attributes unused = inProcessTransport.serverStreamAttributes = inProcessTransport.serverTransportListener.transportReady(serverTransportAttrs);
                    InProcessTransport.this.clientTransportListener.transportReady();
                }
            }
        };
    }

    public synchronized ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) {
        int metadataSize;
        StatsTraceContext statsTraceContext = StatsTraceContext.newClientContext(tracers, getAttributes(), headers);
        Status status = this.shutdownStatus;
        if (status != null) {
            return failedClientStream(statsTraceContext, status);
        }
        headers.put(GrpcUtil.USER_AGENT_KEY, this.userAgent);
        if (this.serverMaxInboundMetadataSize == Integer.MAX_VALUE || (metadataSize = metadataSize(headers)) <= this.serverMaxInboundMetadataSize) {
            return new InProcessStream(method, headers, callOptions, this.authority, statsTraceContext).clientStream;
        }
        return failedClientStream(statsTraceContext, Status.RESOURCE_EXHAUSTED.withDescription(String.format("Request metadata larger than %d: %d", new Object[]{Integer.valueOf(this.serverMaxInboundMetadataSize), Integer.valueOf(metadataSize)})));
    }

    private ClientStream failedClientStream(final StatsTraceContext statsTraceCtx, final Status status) {
        return new NoopClientStream() {
            public void start(ClientStreamListener listener) {
                statsTraceCtx.clientOutboundHeaders();
                statsTraceCtx.streamClosed(status);
                listener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            }
        };
    }

    public synchronized void ping(final ClientTransport.PingCallback callback, Executor executor) {
        if (this.terminated) {
            final Status shutdownStatus2 = this.shutdownStatus;
            executor.execute(new Runnable() {
                public void run() {
                    callback.onFailure(shutdownStatus2.asRuntimeException());
                }
            });
        } else {
            executor.execute(new Runnable() {
                public void run() {
                    callback.onSuccess(0);
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void shutdown(p004io.grpc.Status r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.shutdown     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            r1.shutdownStatus = r2     // Catch:{ all -> 0x0019 }
            r1.notifyShutdown(r2)     // Catch:{ all -> 0x0019 }
            java.util.Set<io.grpc.inprocess.InProcessTransport$InProcessStream> r0 = r1.streams     // Catch:{ all -> 0x0019 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0017
            r1.notifyTerminated()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.shutdown(io.grpc.Status):void");
    }

    public synchronized void shutdown() {
        shutdown(Status.UNAVAILABLE.withDescription("InProcessTransport shutdown by the server-side"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r1.hasNext() == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        p004io.grpc.inprocess.InProcessTransport.InProcessStream.access$700(r1.next()).cancel(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r1 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void shutdownNow(p004io.grpc.Status r5) {
        /*
            r4 = this;
            java.lang.String r0 = "reason"
            com.google.common.base.Preconditions.checkNotNull(r5, r0)
            monitor-enter(r4)
            r4.shutdown(r5)     // Catch:{ all -> 0x0030 }
            boolean r0 = r4.terminated     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x000f
            monitor-exit(r4)     // Catch:{ all -> 0x0030 }
            return
        L_0x000f:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0030 }
            java.util.Set<io.grpc.inprocess.InProcessTransport$InProcessStream> r1 = r4.streams     // Catch:{ all -> 0x0030 }
            r0.<init>(r1)     // Catch:{ all -> 0x0030 }
            monitor-exit(r4)     // Catch:{ all -> 0x0030 }
            java.util.Iterator r1 = r0.iterator()
        L_0x001b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x002f
            java.lang.Object r2 = r1.next()
            io.grpc.inprocess.InProcessTransport$InProcessStream r2 = (p004io.grpc.inprocess.InProcessTransport.InProcessStream) r2
            io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r3 = r2.clientStream
            r3.cancel(r5)
            goto L_0x001b
        L_0x002f:
            return
        L_0x0030:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0030 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.shutdownNow(io.grpc.Status):void");
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("address", (Object) this.address).toString();
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return this.serverScheduler;
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture<InternalChannelz.SocketStats> ret = SettableFuture.create();
        ret.set(null);
        return ret;
    }

    /* access modifiers changed from: private */
    public synchronized void notifyShutdown(Status s) {
        if (!this.shutdown) {
            this.shutdown = true;
            this.clientTransportListener.transportShutdown(s);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void notifyTerminated() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.terminated     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            r2.terminated = r0     // Catch:{ all -> 0x0026 }
            java.util.concurrent.ScheduledExecutorService r0 = r2.serverScheduler     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0018
            io.grpc.internal.ObjectPool<java.util.concurrent.ScheduledExecutorService> r1 = r2.serverSchedulerPool     // Catch:{ all -> 0x0026 }
            java.lang.Object r0 = r1.returnObject(r0)     // Catch:{ all -> 0x0026 }
            java.util.concurrent.ScheduledExecutorService r0 = (java.util.concurrent.ScheduledExecutorService) r0     // Catch:{ all -> 0x0026 }
            r2.serverScheduler = r0     // Catch:{ all -> 0x0026 }
        L_0x0018:
            io.grpc.internal.ManagedClientTransport$Listener r0 = r2.clientTransportListener     // Catch:{ all -> 0x0026 }
            r0.transportTerminated()     // Catch:{ all -> 0x0026 }
            io.grpc.internal.ServerTransportListener r0 = r2.serverTransportListener     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0024
            r0.transportTerminated()     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r2)
            return
        L_0x0026:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.notifyTerminated():void");
    }

    /* access modifiers changed from: private */
    public static int metadataSize(Metadata metadata) {
        byte[][] serialized = InternalMetadata.serialize(metadata);
        if (serialized == null) {
            return 0;
        }
        long size = 0;
        for (int i = 0; i < serialized.length; i += 2) {
            size += (long) (serialized[i].length + 32 + serialized[i + 1].length);
        }
        return (int) Math.min(size, 2147483647L);
    }

    /* renamed from: io.grpc.inprocess.InProcessTransport$InProcessStream */
    private class InProcessStream {
        /* access modifiers changed from: private */
        public volatile String authority;
        private final CallOptions callOptions;
        /* access modifiers changed from: private */
        public final InProcessClientStream clientStream;
        /* access modifiers changed from: private */
        public final Metadata headers;
        /* access modifiers changed from: private */
        public final MethodDescriptor<?, ?> method;
        /* access modifiers changed from: private */
        public final InProcessServerStream serverStream;

        private InProcessStream(MethodDescriptor<?, ?> method2, Metadata headers2, CallOptions callOptions2, String authority2, StatsTraceContext statsTraceContext) {
            this.method = (MethodDescriptor) Preconditions.checkNotNull(method2, FirebaseAnalytics.Param.METHOD);
            this.headers = (Metadata) Preconditions.checkNotNull(headers2, "headers");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
            this.authority = authority2;
            this.clientStream = new InProcessClientStream(callOptions2, statsTraceContext);
            this.serverStream = new InProcessServerStream(method2, headers2);
        }

        /* access modifiers changed from: private */
        public void streamClosed() {
            synchronized (InProcessTransport.this) {
                boolean justRemovedAnElement = InProcessTransport.this.streams.remove(this);
                if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                    InProcessTransport.this.inUseState.updateObjectInUse(this, false);
                }
                if (InProcessTransport.this.streams.isEmpty() && justRemovedAnElement && InProcessTransport.this.shutdown) {
                    InProcessTransport.this.notifyTerminated();
                }
            }
        }

        /* renamed from: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream */
        private class InProcessServerStream implements ServerStream {
            private Status clientNotifyStatus;
            private Metadata clientNotifyTrailers;
            private ArrayDeque<StreamListener.MessageProducer> clientReceiveQueue = new ArrayDeque<>();
            private int clientRequested;
            private ClientStreamListener clientStreamListener;
            private boolean closed;
            private int outboundSeqNo;
            final StatsTraceContext statsTraceCtx;

            InProcessServerStream(MethodDescriptor<?, ?> method, Metadata headers) {
                this.statsTraceCtx = StatsTraceContext.newServerContext(InProcessTransport.this.serverStreamTracerFactories, method.getFullMethodName(), headers);
            }

            /* access modifiers changed from: private */
            public synchronized void setListener(ClientStreamListener listener) {
                this.clientStreamListener = listener;
            }

            public void setListener(ServerStreamListener serverStreamListener) {
                InProcessStream.this.clientStream.setListener(serverStreamListener);
            }

            public void request(int numMessages) {
                if (InProcessStream.this.clientStream.serverRequested(numMessages)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.clientStreamListener.onReady();
                        }
                    }
                }
            }

            /* access modifiers changed from: private */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
                return r1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized boolean clientRequested(int r8) {
                /*
                    r7 = this;
                    monitor-enter(r7)
                    boolean r0 = r7.closed     // Catch:{ all -> 0x0079 }
                    r1 = 0
                    if (r0 == 0) goto L_0x0008
                    monitor-exit(r7)
                    return r1
                L_0x0008:
                    int r0 = r7.clientRequested     // Catch:{ all -> 0x0079 }
                    r2 = 1
                    if (r0 <= 0) goto L_0x000f
                    r3 = r2
                    goto L_0x0010
                L_0x000f:
                    r3 = r1
                L_0x0010:
                    int r0 = r0 + r8
                    r7.clientRequested = r0     // Catch:{ all -> 0x0079 }
                L_0x0013:
                    int r0 = r7.clientRequested     // Catch:{ all -> 0x0079 }
                    if (r0 <= 0) goto L_0x0032
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r0 = r7.clientReceiveQueue     // Catch:{ all -> 0x0079 }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0079 }
                    if (r0 != 0) goto L_0x0032
                    int r0 = r7.clientRequested     // Catch:{ all -> 0x0079 }
                    int r0 = r0 - r2
                    r7.clientRequested = r0     // Catch:{ all -> 0x0079 }
                    io.grpc.internal.ClientStreamListener r0 = r7.clientStreamListener     // Catch:{ all -> 0x0079 }
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r4 = r7.clientReceiveQueue     // Catch:{ all -> 0x0079 }
                    java.lang.Object r4 = r4.poll()     // Catch:{ all -> 0x0079 }
                    io.grpc.internal.StreamListener$MessageProducer r4 = (p004io.grpc.internal.StreamListener.MessageProducer) r4     // Catch:{ all -> 0x0079 }
                    r0.messagesAvailable(r4)     // Catch:{ all -> 0x0079 }
                    goto L_0x0013
                L_0x0032:
                    boolean r0 = r7.closed     // Catch:{ all -> 0x0079 }
                    if (r0 == 0) goto L_0x0038
                    monitor-exit(r7)
                    return r1
                L_0x0038:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r0 = r7.clientReceiveQueue     // Catch:{ all -> 0x0079 }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0079 }
                    if (r0 == 0) goto L_0x006b
                    io.grpc.Status r0 = r7.clientNotifyStatus     // Catch:{ all -> 0x0079 }
                    if (r0 == 0) goto L_0x006b
                    r7.closed = r2     // Catch:{ all -> 0x0079 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0079 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r0 = r0.clientStream     // Catch:{ all -> 0x0079 }
                    io.grpc.internal.StatsTraceContext r0 = r0.statsTraceCtx     // Catch:{ all -> 0x0079 }
                    io.grpc.Metadata r4 = r7.clientNotifyTrailers     // Catch:{ all -> 0x0079 }
                    r0.clientInboundTrailers(r4)     // Catch:{ all -> 0x0079 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0079 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r0 = r0.clientStream     // Catch:{ all -> 0x0079 }
                    io.grpc.internal.StatsTraceContext r0 = r0.statsTraceCtx     // Catch:{ all -> 0x0079 }
                    io.grpc.Status r4 = r7.clientNotifyStatus     // Catch:{ all -> 0x0079 }
                    r0.streamClosed(r4)     // Catch:{ all -> 0x0079 }
                    io.grpc.internal.ClientStreamListener r0 = r7.clientStreamListener     // Catch:{ all -> 0x0079 }
                    io.grpc.Status r4 = r7.clientNotifyStatus     // Catch:{ all -> 0x0079 }
                    io.grpc.internal.ClientStreamListener$RpcProgress r5 = p004io.grpc.internal.ClientStreamListener.RpcProgress.PROCESSED     // Catch:{ all -> 0x0079 }
                    io.grpc.Metadata r6 = r7.clientNotifyTrailers     // Catch:{ all -> 0x0079 }
                    r0.closed(r4, r5, r6)     // Catch:{ all -> 0x0079 }
                L_0x006b:
                    int r0 = r7.clientRequested     // Catch:{ all -> 0x0079 }
                    if (r0 <= 0) goto L_0x0071
                    r0 = r2
                    goto L_0x0072
                L_0x0071:
                    r0 = r1
                L_0x0072:
                    if (r3 != 0) goto L_0x0077
                    if (r0 == 0) goto L_0x0077
                    r1 = r2
                L_0x0077:
                    monitor-exit(r7)
                    return r1
                L_0x0079:
                    r8 = move-exception
                    monitor-exit(r7)
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessServerStream.clientRequested(int):boolean");
            }

            /* access modifiers changed from: private */
            public void clientCancelled(Status status) {
                internalCancel(status);
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0057, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized void writeMessage(java.io.InputStream r9) {
                /*
                    r8 = this;
                    monitor-enter(r8)
                    boolean r0 = r8.closed     // Catch:{ all -> 0x0058 }
                    if (r0 == 0) goto L_0x0007
                    monitor-exit(r8)
                    return
                L_0x0007:
                    io.grpc.internal.StatsTraceContext r0 = r8.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r0.outboundMessage(r1)     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.StatsTraceContext r2 = r8.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r3 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r4 = -1
                    r6 = -1
                    r2.outboundMessageSent(r3, r4, r6)     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r0 = r0.clientStream     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.StatsTraceContext r0 = r0.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r0.inboundMessage(r1)     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r0 = r0.clientStream     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.StatsTraceContext r1 = r0.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r2 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r3 = -1
                    r5 = -1
                    r1.inboundMessageRead(r2, r3, r5)     // Catch:{ all -> 0x0058 }
                    int r0 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    int r0 = r0 + 1
                    r8.outboundSeqNo = r0     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$SingleMessageProducer r0 = new io.grpc.inprocess.InProcessTransport$SingleMessageProducer     // Catch:{ all -> 0x0058 }
                    r1 = 0
                    r0.<init>(r9)     // Catch:{ all -> 0x0058 }
                    int r1 = r8.clientRequested     // Catch:{ all -> 0x0058 }
                    if (r1 <= 0) goto L_0x0051
                    int r1 = r1 + -1
                    r8.clientRequested = r1     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.ClientStreamListener r1 = r8.clientStreamListener     // Catch:{ all -> 0x0058 }
                    r1.messagesAvailable(r0)     // Catch:{ all -> 0x0058 }
                    goto L_0x0056
                L_0x0051:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r1 = r8.clientReceiveQueue     // Catch:{ all -> 0x0058 }
                    r1.add(r0)     // Catch:{ all -> 0x0058 }
                L_0x0056:
                    monitor-exit(r8)
                    return
                L_0x0058:
                    r9 = move-exception
                    monitor-exit(r8)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessServerStream.writeMessage(java.io.InputStream):void");
            }

            public void flush() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x000e, code lost:
                return r1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized boolean isReady() {
                /*
                    r2 = this;
                    monitor-enter(r2)
                    boolean r0 = r2.closed     // Catch:{ all -> 0x000f }
                    r1 = 0
                    if (r0 == 0) goto L_0x0008
                    monitor-exit(r2)
                    return r1
                L_0x0008:
                    int r0 = r2.clientRequested     // Catch:{ all -> 0x000f }
                    if (r0 <= 0) goto L_0x000d
                    r1 = 1
                L_0x000d:
                    monitor-exit(r2)
                    return r1
                L_0x000f:
                    r0 = move-exception
                    monitor-exit(r2)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessServerStream.isReady():boolean");
            }

            public void writeHeaders(Metadata headers) {
                int metadataSize;
                if (InProcessTransport.this.clientMaxInboundMetadataSize == Integer.MAX_VALUE || (metadataSize = InProcessTransport.metadataSize(headers)) <= InProcessTransport.this.clientMaxInboundMetadataSize) {
                    synchronized (this) {
                        if (!this.closed) {
                            InProcessStream.this.clientStream.statsTraceCtx.clientInboundHeaders();
                            this.clientStreamListener.headersRead(headers);
                            return;
                        }
                        return;
                    }
                }
                Status serverStatus = Status.CANCELLED.withDescription("Client cancelled the RPC");
                InProcessStream.this.clientStream.serverClosed(serverStatus, serverStatus);
                notifyClientClose(Status.RESOURCE_EXHAUSTED.withDescription(String.format("Response header metadata larger than %d: %d", new Object[]{Integer.valueOf(InProcessTransport.this.clientMaxInboundMetadataSize), Integer.valueOf(metadataSize)})), new Metadata());
            }

            public void close(Status status, Metadata trailers) {
                InProcessStream.this.clientStream.serverClosed(Status.f313OK, status);
                if (InProcessTransport.this.clientMaxInboundMetadataSize != Integer.MAX_VALUE) {
                    int metadataSize = InProcessTransport.metadataSize(trailers) + (status.getDescription() == null ? 0 : status.getDescription().length());
                    if (metadataSize > InProcessTransport.this.clientMaxInboundMetadataSize) {
                        status = Status.RESOURCE_EXHAUSTED.withDescription(String.format("Response header metadata larger than %d: %d", new Object[]{Integer.valueOf(InProcessTransport.this.clientMaxInboundMetadataSize), Integer.valueOf(metadataSize)}));
                        trailers = new Metadata();
                    }
                }
                notifyClientClose(status, trailers);
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
                p004io.grpc.inprocess.InProcessTransport.InProcessStream.access$2000(r3.this$1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private void notifyClientClose(p004io.grpc.Status r4, p004io.grpc.Metadata r5) {
                /*
                    r3 = this;
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this
                    io.grpc.inprocess.InProcessTransport r0 = p004io.grpc.inprocess.InProcessTransport.this
                    boolean r0 = r0.includeCauseWithStatus
                    io.grpc.Status r0 = p004io.grpc.inprocess.InProcessTransport.cleanStatus(r4, r0)
                    monitor-enter(r3)
                    boolean r1 = r3.closed     // Catch:{ all -> 0x0047 }
                    if (r1 == 0) goto L_0x0013
                    monitor-exit(r3)     // Catch:{ all -> 0x0047 }
                    return
                L_0x0013:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r1 = r3.clientReceiveQueue     // Catch:{ all -> 0x0047 }
                    boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0047 }
                    if (r1 == 0) goto L_0x003c
                    r1 = 1
                    r3.closed = r1     // Catch:{ all -> 0x0047 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r1 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0047 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r1 = r1.clientStream     // Catch:{ all -> 0x0047 }
                    io.grpc.internal.StatsTraceContext r1 = r1.statsTraceCtx     // Catch:{ all -> 0x0047 }
                    r1.clientInboundTrailers(r5)     // Catch:{ all -> 0x0047 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r1 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0047 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r1 = r1.clientStream     // Catch:{ all -> 0x0047 }
                    io.grpc.internal.StatsTraceContext r1 = r1.statsTraceCtx     // Catch:{ all -> 0x0047 }
                    r1.streamClosed(r0)     // Catch:{ all -> 0x0047 }
                    io.grpc.internal.ClientStreamListener r1 = r3.clientStreamListener     // Catch:{ all -> 0x0047 }
                    io.grpc.internal.ClientStreamListener$RpcProgress r2 = p004io.grpc.internal.ClientStreamListener.RpcProgress.PROCESSED     // Catch:{ all -> 0x0047 }
                    r1.closed(r0, r2, r5)     // Catch:{ all -> 0x0047 }
                    goto L_0x0040
                L_0x003c:
                    r3.clientNotifyStatus = r0     // Catch:{ all -> 0x0047 }
                    r3.clientNotifyTrailers = r5     // Catch:{ all -> 0x0047 }
                L_0x0040:
                    monitor-exit(r3)     // Catch:{ all -> 0x0047 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r1 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this
                    r1.streamClosed()
                    return
                L_0x0047:
                    r1 = move-exception
                    monitor-exit(r3)     // Catch:{ all -> 0x0047 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessServerStream.notifyClientClose(io.grpc.Status, io.grpc.Metadata):void");
            }

            public void cancel(Status status) {
                if (internalCancel(Status.CANCELLED.withDescription("server cancelled stream"))) {
                    InProcessStream.this.clientStream.serverClosed(status, status);
                    InProcessStream.this.streamClosed();
                }
            }

            private synchronized boolean internalCancel(Status clientStatus) {
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while (true) {
                    StreamListener.MessageProducer poll = this.clientReceiveQueue.poll();
                    StreamListener.MessageProducer producer = poll;
                    if (poll != null) {
                        while (true) {
                            InputStream next = producer.next();
                            InputStream message = next;
                            if (next == null) {
                                break;
                            }
                            try {
                                message.close();
                            } catch (Throwable t) {
                                InProcessTransport.log.log(Level.WARNING, "Exception closing stream", t);
                            }
                        }
                    } else {
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(clientStatus);
                        this.clientStreamListener.closed(clientStatus, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
                        return true;
                    }
                }
            }

            public void setMessageCompression(boolean enable) {
            }

            public void optimizeForDirectExecutor() {
            }

            public void setCompressor(Compressor compressor) {
            }

            public void setDecompressor(Decompressor decompressor) {
            }

            public Attributes getAttributes() {
                return InProcessTransport.this.serverStreamAttributes;
            }

            public String getAuthority() {
                return InProcessStream.this.authority;
            }

            public StatsTraceContext statsTraceContext() {
                return this.statsTraceCtx;
            }

            public int streamId() {
                return -1;
            }
        }

        /* renamed from: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream */
        private class InProcessClientStream implements ClientStream {
            final CallOptions callOptions;
            private boolean closed;
            private int outboundSeqNo;
            private boolean serverNotifyHalfClose;
            private ArrayDeque<StreamListener.MessageProducer> serverReceiveQueue = new ArrayDeque<>();
            private int serverRequested;
            private ServerStreamListener serverStreamListener;
            final StatsTraceContext statsTraceCtx;

            InProcessClientStream(CallOptions callOptions2, StatsTraceContext statsTraceContext) {
                this.callOptions = callOptions2;
                this.statsTraceCtx = statsTraceContext;
            }

            /* access modifiers changed from: private */
            public synchronized void setListener(ServerStreamListener listener) {
                this.serverStreamListener = listener;
            }

            public void request(int numMessages) {
                if (InProcessStream.this.serverStream.clientRequested(numMessages)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.serverStreamListener.onReady();
                        }
                    }
                }
            }

            /* access modifiers changed from: private */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x0052, code lost:
                return r1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized boolean serverRequested(int r6) {
                /*
                    r5 = this;
                    monitor-enter(r5)
                    boolean r0 = r5.closed     // Catch:{ all -> 0x0053 }
                    r1 = 0
                    if (r0 == 0) goto L_0x0008
                    monitor-exit(r5)
                    return r1
                L_0x0008:
                    int r0 = r5.serverRequested     // Catch:{ all -> 0x0053 }
                    r2 = 1
                    if (r0 <= 0) goto L_0x000f
                    r3 = r2
                    goto L_0x0010
                L_0x000f:
                    r3 = r1
                L_0x0010:
                    int r0 = r0 + r6
                    r5.serverRequested = r0     // Catch:{ all -> 0x0053 }
                L_0x0013:
                    int r0 = r5.serverRequested     // Catch:{ all -> 0x0053 }
                    if (r0 <= 0) goto L_0x0032
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r0 = r5.serverReceiveQueue     // Catch:{ all -> 0x0053 }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0053 }
                    if (r0 != 0) goto L_0x0032
                    int r0 = r5.serverRequested     // Catch:{ all -> 0x0053 }
                    int r0 = r0 - r2
                    r5.serverRequested = r0     // Catch:{ all -> 0x0053 }
                    io.grpc.internal.ServerStreamListener r0 = r5.serverStreamListener     // Catch:{ all -> 0x0053 }
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r4 = r5.serverReceiveQueue     // Catch:{ all -> 0x0053 }
                    java.lang.Object r4 = r4.poll()     // Catch:{ all -> 0x0053 }
                    io.grpc.internal.StreamListener$MessageProducer r4 = (p004io.grpc.internal.StreamListener.MessageProducer) r4     // Catch:{ all -> 0x0053 }
                    r0.messagesAvailable(r4)     // Catch:{ all -> 0x0053 }
                    goto L_0x0013
                L_0x0032:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r0 = r5.serverReceiveQueue     // Catch:{ all -> 0x0053 }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0053 }
                    if (r0 == 0) goto L_0x0045
                    boolean r0 = r5.serverNotifyHalfClose     // Catch:{ all -> 0x0053 }
                    if (r0 == 0) goto L_0x0045
                    r5.serverNotifyHalfClose = r1     // Catch:{ all -> 0x0053 }
                    io.grpc.internal.ServerStreamListener r0 = r5.serverStreamListener     // Catch:{ all -> 0x0053 }
                    r0.halfClosed()     // Catch:{ all -> 0x0053 }
                L_0x0045:
                    int r0 = r5.serverRequested     // Catch:{ all -> 0x0053 }
                    if (r0 <= 0) goto L_0x004b
                    r0 = r2
                    goto L_0x004c
                L_0x004b:
                    r0 = r1
                L_0x004c:
                    if (r3 != 0) goto L_0x0051
                    if (r0 == 0) goto L_0x0051
                    r1 = r2
                L_0x0051:
                    monitor-exit(r5)
                    return r1
                L_0x0053:
                    r6 = move-exception
                    monitor-exit(r5)
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.serverRequested(int):boolean");
            }

            /* access modifiers changed from: private */
            public void serverClosed(Status serverListenerStatus, Status serverTracerStatus) {
                internalCancel(serverListenerStatus, serverTracerStatus);
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0057, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized void writeMessage(java.io.InputStream r9) {
                /*
                    r8 = this;
                    monitor-enter(r8)
                    boolean r0 = r8.closed     // Catch:{ all -> 0x0058 }
                    if (r0 == 0) goto L_0x0007
                    monitor-exit(r8)
                    return
                L_0x0007:
                    io.grpc.internal.StatsTraceContext r0 = r8.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r0.outboundMessage(r1)     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.StatsTraceContext r2 = r8.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r3 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r4 = -1
                    r6 = -1
                    r2.outboundMessageSent(r3, r4, r6)     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream r0 = r0.serverStream     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.StatsTraceContext r0 = r0.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r0.inboundMessage(r1)     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = p004io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream r0 = r0.serverStream     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.StatsTraceContext r1 = r0.statsTraceCtx     // Catch:{ all -> 0x0058 }
                    int r2 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    r3 = -1
                    r5 = -1
                    r1.inboundMessageRead(r2, r3, r5)     // Catch:{ all -> 0x0058 }
                    int r0 = r8.outboundSeqNo     // Catch:{ all -> 0x0058 }
                    int r0 = r0 + 1
                    r8.outboundSeqNo = r0     // Catch:{ all -> 0x0058 }
                    io.grpc.inprocess.InProcessTransport$SingleMessageProducer r0 = new io.grpc.inprocess.InProcessTransport$SingleMessageProducer     // Catch:{ all -> 0x0058 }
                    r1 = 0
                    r0.<init>(r9)     // Catch:{ all -> 0x0058 }
                    int r1 = r8.serverRequested     // Catch:{ all -> 0x0058 }
                    if (r1 <= 0) goto L_0x0051
                    int r1 = r1 + -1
                    r8.serverRequested = r1     // Catch:{ all -> 0x0058 }
                    io.grpc.internal.ServerStreamListener r1 = r8.serverStreamListener     // Catch:{ all -> 0x0058 }
                    r1.messagesAvailable(r0)     // Catch:{ all -> 0x0058 }
                    goto L_0x0056
                L_0x0051:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r1 = r8.serverReceiveQueue     // Catch:{ all -> 0x0058 }
                    r1.add(r0)     // Catch:{ all -> 0x0058 }
                L_0x0056:
                    monitor-exit(r8)
                    return
                L_0x0058:
                    r9 = move-exception
                    monitor-exit(r8)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.writeMessage(java.io.InputStream):void");
            }

            public void flush() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x000e, code lost:
                return r1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized boolean isReady() {
                /*
                    r2 = this;
                    monitor-enter(r2)
                    boolean r0 = r2.closed     // Catch:{ all -> 0x000f }
                    r1 = 0
                    if (r0 == 0) goto L_0x0008
                    monitor-exit(r2)
                    return r1
                L_0x0008:
                    int r0 = r2.serverRequested     // Catch:{ all -> 0x000f }
                    if (r0 <= 0) goto L_0x000d
                    r1 = 1
                L_0x000d:
                    monitor-exit(r2)
                    return r1
                L_0x000f:
                    r0 = move-exception
                    monitor-exit(r2)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.isReady():boolean");
            }

            public void cancel(Status reason) {
                Status serverStatus = InProcessTransport.cleanStatus(reason, InProcessTransport.this.includeCauseWithStatus);
                if (internalCancel(serverStatus, serverStatus)) {
                    InProcessStream.this.serverStream.clientCancelled(reason);
                    InProcessStream.this.streamClosed();
                }
            }

            private synchronized boolean internalCancel(Status serverListenerStatus, Status serverTracerStatus) {
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while (true) {
                    StreamListener.MessageProducer poll = this.serverReceiveQueue.poll();
                    StreamListener.MessageProducer producer = poll;
                    if (poll != null) {
                        while (true) {
                            InputStream next = producer.next();
                            InputStream message = next;
                            if (next == null) {
                                break;
                            }
                            try {
                                message.close();
                            } catch (Throwable t) {
                                InProcessTransport.log.log(Level.WARNING, "Exception closing stream", t);
                            }
                        }
                    } else {
                        InProcessStream.this.serverStream.statsTraceCtx.streamClosed(serverTracerStatus);
                        this.serverStreamListener.closed(serverListenerStatus);
                        return true;
                    }
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized void halfClose() {
                /*
                    r1 = this;
                    monitor-enter(r1)
                    boolean r0 = r1.closed     // Catch:{ all -> 0x001a }
                    if (r0 == 0) goto L_0x0007
                    monitor-exit(r1)
                    return
                L_0x0007:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r0 = r1.serverReceiveQueue     // Catch:{ all -> 0x001a }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x001a }
                    if (r0 == 0) goto L_0x0015
                    io.grpc.internal.ServerStreamListener r0 = r1.serverStreamListener     // Catch:{ all -> 0x001a }
                    r0.halfClosed()     // Catch:{ all -> 0x001a }
                    goto L_0x0018
                L_0x0015:
                    r0 = 1
                    r1.serverNotifyHalfClose = r0     // Catch:{ all -> 0x001a }
                L_0x0018:
                    monitor-exit(r1)
                    return
                L_0x001a:
                    r0 = move-exception
                    monitor-exit(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.halfClose():void");
            }

            public void setMessageCompression(boolean enable) {
            }

            public void setAuthority(String string) {
                String unused = InProcessStream.this.authority = string;
            }

            public void start(ClientStreamListener listener) {
                InProcessStream.this.serverStream.setListener(listener);
                synchronized (InProcessTransport.this) {
                    this.statsTraceCtx.clientOutboundHeaders();
                    InProcessTransport.this.streams.add(InProcessStream.this);
                    if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                        InProcessTransport.this.inUseState.updateObjectInUse(InProcessStream.this, true);
                    }
                    InProcessTransport.this.serverTransportListener.streamCreated(InProcessStream.this.serverStream, InProcessStream.this.method.getFullMethodName(), InProcessStream.this.headers);
                }
            }

            public Attributes getAttributes() {
                return InProcessTransport.this.attributes;
            }

            public void optimizeForDirectExecutor() {
            }

            public void setCompressor(Compressor compressor) {
            }

            public void setFullStreamDecompression(boolean fullStreamDecompression) {
            }

            public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            }

            public void setMaxInboundMessageSize(int maxSize) {
            }

            public void setMaxOutboundMessageSize(int maxSize) {
            }

            public void setDeadline(Deadline deadline) {
                InProcessStream.this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
                InProcessStream.this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
            }

            public void appendTimeoutInsight(InsightBuilder insight) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static Status cleanStatus(Status status, boolean includeCauseWithStatus2) {
        if (status == null) {
            return null;
        }
        Status clientStatus = Status.fromCodeValue(status.getCode().value()).withDescription(status.getDescription());
        if (includeCauseWithStatus2) {
            return clientStatus.withCause(status.getCause());
        }
        return clientStatus;
    }

    /* renamed from: io.grpc.inprocess.InProcessTransport$SingleMessageProducer */
    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream message2) {
            this.message = message2;
        }

        @Nullable
        public InputStream next() {
            InputStream messageToReturn = this.message;
            this.message = null;
            return messageToReturn;
        }
    }
}
