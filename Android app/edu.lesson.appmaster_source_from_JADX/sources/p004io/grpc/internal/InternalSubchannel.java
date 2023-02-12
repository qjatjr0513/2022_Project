package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.CallOptions;
import p004io.grpc.ChannelLogger;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.ConnectivityState;
import p004io.grpc.ConnectivityStateInfo;
import p004io.grpc.EquivalentAddressGroup;
import p004io.grpc.HttpConnectProxiedSocketAddress;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalInstrumented;
import p004io.grpc.InternalLogId;
import p004io.grpc.InternalWithLogId;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.SynchronizationContext;
import p004io.grpc.internal.BackoffPolicy;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.ClientTransportFactory;
import p004io.grpc.internal.ManagedClientTransport;

/* renamed from: io.grpc.internal.InternalSubchannel */
final class InternalSubchannel implements InternalInstrumented<InternalChannelz.ChannelStats>, TransportProvider {
    /* access modifiers changed from: private */
    @Nullable
    public volatile ManagedClientTransport activeTransport;
    /* access modifiers changed from: private */
    public volatile List<EquivalentAddressGroup> addressGroups;
    /* access modifiers changed from: private */
    public final Index addressIndex;
    private final String authority;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final Callback callback;
    /* access modifiers changed from: private */
    public final CallTracer callsTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    private final Stopwatch connectingTimer;
    /* access modifiers changed from: private */
    public final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>() {
        /* access modifiers changed from: protected */
        public void handleInUse() {
            InternalSubchannel.this.callback.onInUse(InternalSubchannel.this);
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            InternalSubchannel.this.callback.onNotInUse(InternalSubchannel.this);
        }
    };
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    @Nullable
    public ConnectionClientTransport pendingTransport;
    /* access modifiers changed from: private */
    public BackoffPolicy reconnectPolicy;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle reconnectTask;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle shutdownDueToUpdateTask;
    /* access modifiers changed from: private */
    @Nullable
    public ManagedClientTransport shutdownDueToUpdateTransport;
    /* access modifiers changed from: private */
    public Status shutdownReason;
    /* access modifiers changed from: private */
    public volatile ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final Collection<ConnectionClientTransport> transports = new ArrayList();
    private final String userAgent;

    InternalSubchannel(List<EquivalentAddressGroup> addressGroups2, String authority2, String userAgent2, BackoffPolicy.Provider backoffPolicyProvider2, ClientTransportFactory transportFactory2, ScheduledExecutorService scheduledExecutor2, Supplier<Stopwatch> stopwatchSupplier, SynchronizationContext syncContext2, Callback callback2, InternalChannelz channelz2, CallTracer callsTracer2, ChannelTracer channelTracer2, InternalLogId logId2, ChannelLogger channelLogger2) {
        List<EquivalentAddressGroup> list = addressGroups2;
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!addressGroups2.isEmpty(), "addressGroups is empty");
        checkListHasNoNulls(list, "addressGroups contains null entry");
        List<EquivalentAddressGroup> unmodifiableAddressGroups = Collections.unmodifiableList(new ArrayList(list));
        this.addressGroups = unmodifiableAddressGroups;
        this.addressIndex = new Index(unmodifiableAddressGroups);
        this.authority = authority2;
        this.userAgent = userAgent2;
        this.backoffPolicyProvider = backoffPolicyProvider2;
        this.transportFactory = transportFactory2;
        this.scheduledExecutor = scheduledExecutor2;
        this.connectingTimer = stopwatchSupplier.get();
        this.syncContext = syncContext2;
        this.callback = callback2;
        this.channelz = channelz2;
        this.callsTracer = callsTracer2;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer2, "channelTracer");
        this.logId = (InternalLogId) Preconditions.checkNotNull(logId2, "logId");
        this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger2, "channelLogger");
    }

    /* access modifiers changed from: package-private */
    public ChannelLogger getChannelLogger() {
        return this.channelLogger;
    }

    public ClientTransport obtainActiveTransport() {
        ClientTransport savedTransport = this.activeTransport;
        if (savedTransport != null) {
            return savedTransport;
        }
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.IDLE) {
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING as requested");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ClientTransport getTransport() {
        return this.activeTransport;
    }

    /* access modifiers changed from: package-private */
    public String getAuthority() {
        return this.authority;
    }

    /* access modifiers changed from: private */
    public void startNewTransport() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex.isAtBeginning()) {
            this.connectingTimer.reset().start();
        }
        SocketAddress address = this.addressIndex.getCurrentAddress();
        HttpConnectProxiedSocketAddress proxiedAddr = null;
        boolean z = address instanceof HttpConnectProxiedSocketAddress;
        SocketAddress address2 = address;
        if (z) {
            proxiedAddr = (HttpConnectProxiedSocketAddress) address;
            address2 = proxiedAddr.getTargetAddress();
        }
        Attributes currentEagAttributes = this.addressIndex.getCurrentEagAttributes();
        String eagChannelAuthority = (String) currentEagAttributes.get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE);
        ClientTransportFactory.ClientTransportOptions options = new ClientTransportFactory.ClientTransportOptions().setAuthority(eagChannelAuthority != null ? eagChannelAuthority : this.authority).setEagAttributes(currentEagAttributes).setUserAgent(this.userAgent).setHttpConnectProxiedSocketAddress(proxiedAddr);
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = getLogId();
        ConnectionClientTransport transport = new CallTracingTransport(this.transportFactory.newClientTransport(address2, options, transportLogger), this.callsTracer);
        transportLogger.logId = transport.getLogId();
        this.channelz.addClientSocket(transport);
        this.pendingTransport = transport;
        this.transports.add(transport);
        Runnable runnable = transport.start(new TransportListener(transport, address2));
        if (runnable != null) {
            this.syncContext.executeLater(runnable);
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Started transport {0}", transportLogger.logId);
    }

    /* access modifiers changed from: private */
    public void scheduleBackoff(Status status) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long delayNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        boolean z = false;
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", printShortStatus(status), Long.valueOf(delayNanos));
        if (this.reconnectTask == null) {
            z = true;
        }
        Preconditions.checkState(z, "previous reconnectTask is not done");
        this.reconnectTask = this.syncContext.schedule(new Runnable() {
            public void run() {
                SynchronizationContext.ScheduledHandle unused = InternalSubchannel.this.reconnectTask = null;
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING after backoff");
                InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                InternalSubchannel.this.startNewTransport();
            }
        }, delayNanos, TimeUnit.NANOSECONDS, this.scheduledExecutor);
    }

    /* access modifiers changed from: package-private */
    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.TRANSIENT_FAILURE) {
                    InternalSubchannel.this.cancelReconnectTask();
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void gotoNonErrorState(ConnectivityState newState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forNonError(newState));
    }

    private void gotoState(ConnectivityStateInfo newState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.state.getState() != newState.getState()) {
            Preconditions.checkState(this.state.getState() != ConnectivityState.SHUTDOWN, "Cannot transition out of SHUTDOWN to " + newState);
            this.state = newState;
            this.callback.onStateChange(this, newState);
        }
    }

    public void updateAddresses(List<EquivalentAddressGroup> newAddressGroups) {
        Preconditions.checkNotNull(newAddressGroups, "newAddressGroups");
        checkListHasNoNulls(newAddressGroups, "newAddressGroups contains null entry");
        Preconditions.checkArgument(!newAddressGroups.isEmpty(), "newAddressGroups is empty");
        final List<EquivalentAddressGroup> newImmutableAddressGroups = Collections.unmodifiableList(new ArrayList(newAddressGroups));
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedClientTransport savedTransport = null;
                SocketAddress previousAddress = InternalSubchannel.this.addressIndex.getCurrentAddress();
                InternalSubchannel.this.addressIndex.updateGroups(newImmutableAddressGroups);
                List unused = InternalSubchannel.this.addressGroups = newImmutableAddressGroups;
                if ((InternalSubchannel.this.state.getState() == ConnectivityState.READY || InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING) && !InternalSubchannel.this.addressIndex.seekTo(previousAddress)) {
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.READY) {
                        savedTransport = InternalSubchannel.this.activeTransport;
                        ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = null;
                        InternalSubchannel.this.addressIndex.reset();
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                    } else {
                        InternalSubchannel.this.pendingTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed pending transport due to address change"));
                        ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                        InternalSubchannel.this.addressIndex.reset();
                        InternalSubchannel.this.startNewTransport();
                    }
                }
                if (savedTransport != null) {
                    if (InternalSubchannel.this.shutdownDueToUpdateTask != null) {
                        InternalSubchannel.this.shutdownDueToUpdateTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport early due to address change"));
                        InternalSubchannel.this.shutdownDueToUpdateTask.cancel();
                        SynchronizationContext.ScheduledHandle unused4 = InternalSubchannel.this.shutdownDueToUpdateTask = null;
                        ManagedClientTransport unused5 = InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                    }
                    ManagedClientTransport unused6 = InternalSubchannel.this.shutdownDueToUpdateTransport = savedTransport;
                    InternalSubchannel internalSubchannel = InternalSubchannel.this;
                    SynchronizationContext.ScheduledHandle unused7 = internalSubchannel.shutdownDueToUpdateTask = internalSubchannel.syncContext.schedule(new Runnable() {
                        public void run() {
                            ManagedClientTransport transport = InternalSubchannel.this.shutdownDueToUpdateTransport;
                            SynchronizationContext.ScheduledHandle unused = InternalSubchannel.this.shutdownDueToUpdateTask = null;
                            ManagedClientTransport unused2 = InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                            transport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
                        }
                    }, 5, TimeUnit.SECONDS, InternalSubchannel.this.scheduledExecutor);
                }
            }
        });
    }

    public void shutdown(final Status reason) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) {
                    Status unused = InternalSubchannel.this.shutdownReason = reason;
                    ManagedClientTransport savedActiveTransport = InternalSubchannel.this.activeTransport;
                    ConnectionClientTransport savedPendingTransport = InternalSubchannel.this.pendingTransport;
                    ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = null;
                    ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.SHUTDOWN);
                    InternalSubchannel.this.addressIndex.reset();
                    if (InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                    InternalSubchannel.this.cancelReconnectTask();
                    if (InternalSubchannel.this.shutdownDueToUpdateTask != null) {
                        InternalSubchannel.this.shutdownDueToUpdateTask.cancel();
                        InternalSubchannel.this.shutdownDueToUpdateTransport.shutdown(reason);
                        SynchronizationContext.ScheduledHandle unused4 = InternalSubchannel.this.shutdownDueToUpdateTask = null;
                        ManagedClientTransport unused5 = InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                    }
                    if (savedActiveTransport != null) {
                        savedActiveTransport.shutdown(reason);
                    }
                    if (savedPendingTransport != null) {
                        savedPendingTransport.shutdown(reason);
                    }
                }
            }
        });
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("addressGroups", (Object) this.addressGroups).toString();
    }

    /* access modifiers changed from: private */
    public void handleTermination() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
                InternalSubchannel.this.callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTransportInUseState(final ConnectionClientTransport transport, final boolean inUse) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.inUseStateAggregator.updateObjectInUse(transport, inUse);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void shutdownNow(final Status reason) {
        shutdown(reason);
        this.syncContext.execute(new Runnable() {
            public void run() {
                for (ManagedClientTransport transport : new ArrayList<>(InternalSubchannel.this.transports)) {
                    transport.shutdownNow(reason);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public List<EquivalentAddressGroup> getAddressGroups() {
        return this.addressGroups;
    }

    /* access modifiers changed from: private */
    public void cancelReconnectTask() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.reconnectTask;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture<InternalChannelz.ChannelStats> channelStatsFuture = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                List<EquivalentAddressGroup> addressGroupsSnapshot = InternalSubchannel.this.addressIndex.getGroups();
                List<InternalWithLogId> transportsSnapshot = new ArrayList<>(InternalSubchannel.this.transports);
                builder.setTarget(addressGroupsSnapshot.toString()).setState(InternalSubchannel.this.getState());
                builder.setSockets(transportsSnapshot);
                InternalSubchannel.this.callsTracer.updateBuilder(builder);
                InternalSubchannel.this.channelTracer.updateBuilder(builder);
                channelStatsFuture.set(builder.build());
            }
        });
        return channelStatsFuture;
    }

    /* access modifiers changed from: package-private */
    public ConnectivityState getState() {
        return this.state.getState();
    }

    private static void checkListHasNoNulls(List<?> list, String msg) {
        for (Object item : list) {
            Preconditions.checkNotNull(item, msg);
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$TransportListener */
    private class TransportListener implements ManagedClientTransport.Listener {
        final SocketAddress address;
        boolean shutdownInitiated = false;
        final ConnectionClientTransport transport;

        TransportListener(ConnectionClientTransport transport2, SocketAddress address2) {
            this.transport = transport2;
            this.address = address2;
        }

        public void transportReady() {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "READY");
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    BackoffPolicy unused = InternalSubchannel.this.reconnectPolicy = null;
                    if (InternalSubchannel.this.shutdownReason != null) {
                        Preconditions.checkState(InternalSubchannel.this.activeTransport == null, "Unexpected non-null activeTransport");
                        TransportListener.this.transport.shutdown(InternalSubchannel.this.shutdownReason);
                    } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                        ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = TransportListener.this.transport;
                        ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                    }
                }
            });
        }

        public void transportInUse(boolean inUse) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, inUse);
        }

        public void transportShutdown(final Status s) {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} SHUTDOWN with {1}", this.transport.getLogId(), InternalSubchannel.this.printShortStatus(s));
            this.shutdownInitiated = true;
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) {
                        if (InternalSubchannel.this.activeTransport == TransportListener.this.transport) {
                            ManagedClientTransport unused = InternalSubchannel.this.activeTransport = null;
                            InternalSubchannel.this.addressIndex.reset();
                            InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                        } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                            Preconditions.checkState(InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING, "Expected state is CONNECTING, actual state is %s", (Object) InternalSubchannel.this.state.getState());
                            InternalSubchannel.this.addressIndex.increment();
                            if (!InternalSubchannel.this.addressIndex.isValid()) {
                                ConnectionClientTransport unused2 = InternalSubchannel.this.pendingTransport = null;
                                InternalSubchannel.this.addressIndex.reset();
                                InternalSubchannel.this.scheduleBackoff(s);
                                return;
                            }
                            InternalSubchannel.this.startNewTransport();
                        }
                    }
                }
            });
        }

        public void transportTerminated() {
            Preconditions.checkState(this.shutdownInitiated, "transportShutdown() must be called before transportTerminated().");
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} Terminated", this.transport.getLogId());
            InternalSubchannel.this.channelz.removeClientSocket(this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    InternalSubchannel.this.transports.remove(TransportListener.this.transport);
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                }
            });
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$Callback */
    static abstract class Callback {
        Callback() {
        }

        /* access modifiers changed from: package-private */
        public void onTerminated(InternalSubchannel is) {
        }

        /* access modifiers changed from: package-private */
        public void onStateChange(InternalSubchannel is, ConnectivityStateInfo newState) {
        }

        /* access modifiers changed from: package-private */
        public void onInUse(InternalSubchannel is) {
        }

        /* access modifiers changed from: package-private */
        public void onNotInUse(InternalSubchannel is) {
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$CallTracingTransport */
    static final class CallTracingTransport extends ForwardingConnectionClientTransport {
        /* access modifiers changed from: private */
        public final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        private CallTracingTransport(ConnectionClientTransport delegate2, CallTracer callTracer2) {
            this.delegate = delegate2;
            this.callTracer = callTracer2;
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) {
            final ClientStream streamDelegate = super.newStream(method, headers, callOptions, tracers);
            return new ForwardingClientStream() {
                /* access modifiers changed from: protected */
                public ClientStream delegate() {
                    return streamDelegate;
                }

                public void start(final ClientStreamListener listener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener() {
                        /* access modifiers changed from: protected */
                        public ClientStreamListener delegate() {
                            return listener;
                        }

                        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, trailers);
                        }
                    });
                }
            };
        }
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$Index */
    static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List<EquivalentAddressGroup> groups) {
            this.addressGroups = groups;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public void increment() {
            int i = this.addressIndex + 1;
            this.addressIndex = i;
            if (i >= this.addressGroups.get(this.groupIndex).getAddresses().size()) {
                this.groupIndex++;
                this.addressIndex = 0;
            }
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public SocketAddress getCurrentAddress() {
            return this.addressGroups.get(this.groupIndex).getAddresses().get(this.addressIndex);
        }

        public Attributes getCurrentEagAttributes() {
            return this.addressGroups.get(this.groupIndex).getAttributes();
        }

        public List<EquivalentAddressGroup> getGroups() {
            return this.addressGroups;
        }

        public void updateGroups(List<EquivalentAddressGroup> newGroups) {
            this.addressGroups = newGroups;
            reset();
        }

        public boolean seekTo(SocketAddress needle) {
            int i = 0;
            while (i < this.addressGroups.size()) {
                int j = this.addressGroups.get(i).getAddresses().indexOf(needle);
                if (j == -1) {
                    i++;
                } else {
                    this.groupIndex = i;
                    this.addressIndex = j;
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public String printShortStatus(Status status) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(status.getCode());
        if (status.getDescription() != null) {
            buffer.append("(").append(status.getDescription()).append(")");
        }
        return buffer.toString();
    }

    /* renamed from: io.grpc.internal.InternalSubchannel$TransportLogger */
    static final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        TransportLogger() {
        }

        public void log(ChannelLogger.ChannelLogLevel level, String message) {
            ChannelLoggerImpl.logOnly(this.logId, level, message);
        }

        public void log(ChannelLogger.ChannelLogLevel level, String messageFormat, Object... args) {
            ChannelLoggerImpl.logOnly(this.logId, level, messageFormat, args);
        }
    }
}
