package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import p004io.grpc.Attributes;
import p004io.grpc.CallCredentials;
import p004io.grpc.CallOptions;
import p004io.grpc.ChannelCredentials;
import p004io.grpc.ChannelLogger;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.CompositeCallCredentials;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.SecurityLevel;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientTransportFactory;
import p004io.grpc.internal.MetadataApplierImpl;

/* renamed from: io.grpc.internal.CallCredentialsApplyingTransportFactory */
final class CallCredentialsApplyingTransportFactory implements ClientTransportFactory {
    /* access modifiers changed from: private */
    public final Executor appExecutor;
    /* access modifiers changed from: private */
    public final CallCredentials channelCallCredentials;
    private final ClientTransportFactory delegate;

    CallCredentialsApplyingTransportFactory(ClientTransportFactory delegate2, CallCredentials channelCallCredentials2, Executor appExecutor2) {
        this.delegate = (ClientTransportFactory) Preconditions.checkNotNull(delegate2, "delegate");
        this.channelCallCredentials = channelCallCredentials2;
        this.appExecutor = (Executor) Preconditions.checkNotNull(appExecutor2, "appExecutor");
    }

    public ConnectionClientTransport newClientTransport(SocketAddress serverAddress, ClientTransportFactory.ClientTransportOptions options, ChannelLogger channelLogger) {
        return new CallCredentialsApplyingTransport(this.delegate.newClientTransport(serverAddress, options, channelLogger), options.getAuthority());
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return this.delegate.getScheduledExecutorService();
    }

    public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCreds) {
        throw new UnsupportedOperationException();
    }

    public void close() {
        this.delegate.close();
    }

    /* renamed from: io.grpc.internal.CallCredentialsApplyingTransportFactory$CallCredentialsApplyingTransport */
    private class CallCredentialsApplyingTransport extends ForwardingConnectionClientTransport {
        private final MetadataApplierImpl.MetadataApplierListener applierListener = new MetadataApplierImpl.MetadataApplierListener() {
            public void onComplete() {
                if (CallCredentialsApplyingTransport.this.pendingApplier.decrementAndGet() == 0) {
                    CallCredentialsApplyingTransport.this.maybeShutdown();
                }
            }
        };
        /* access modifiers changed from: private */
        public final String authority;
        /* access modifiers changed from: private */
        public final ConnectionClientTransport delegate;
        /* access modifiers changed from: private */
        public final AtomicInteger pendingApplier = new AtomicInteger(-2147483647);
        private Status savedShutdownNowStatus;
        private Status savedShutdownStatus;
        private volatile Status shutdownStatus;

        CallCredentialsApplyingTransport(ConnectionClientTransport delegate2, String authority2) {
            this.delegate = (ConnectionClientTransport) Preconditions.checkNotNull(delegate2, "delegate");
            this.authority = (String) Preconditions.checkNotNull(authority2, "authority");
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(final MethodDescriptor<?, ?> method, Metadata headers, final CallOptions callOptions, ClientStreamTracer[] tracers) {
            CallCredentials creds = callOptions.getCredentials();
            if (creds == null) {
                creds = CallCredentialsApplyingTransportFactory.this.channelCallCredentials;
            } else if (CallCredentialsApplyingTransportFactory.this.channelCallCredentials != null) {
                creds = new CompositeCallCredentials(CallCredentialsApplyingTransportFactory.this.channelCallCredentials, creds);
            }
            if (creds != null) {
                MetadataApplierImpl applier = new MetadataApplierImpl(this.delegate, method, headers, callOptions, this.applierListener, tracers);
                if (this.pendingApplier.incrementAndGet() > 0) {
                    this.applierListener.onComplete();
                    return new FailingClientStream(this.shutdownStatus, tracers);
                }
                try {
                    creds.applyRequestMetadata(new CallCredentials.RequestInfo() {
                        public MethodDescriptor<?, ?> getMethodDescriptor() {
                            return method;
                        }

                        public SecurityLevel getSecurityLevel() {
                            return (SecurityLevel) MoreObjects.firstNonNull((SecurityLevel) CallCredentialsApplyingTransport.this.delegate.getAttributes().get(GrpcAttributes.ATTR_SECURITY_LEVEL), SecurityLevel.NONE);
                        }

                        public String getAuthority() {
                            return (String) MoreObjects.firstNonNull(callOptions.getAuthority(), CallCredentialsApplyingTransport.this.authority);
                        }

                        public Attributes getTransportAttrs() {
                            return CallCredentialsApplyingTransport.this.delegate.getAttributes();
                        }
                    }, (Executor) MoreObjects.firstNonNull(callOptions.getExecutor(), CallCredentialsApplyingTransportFactory.this.appExecutor), applier);
                } catch (Throwable t) {
                    applier.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(t));
                }
                return applier.returnStream();
            } else if (this.pendingApplier.get() >= 0) {
                return new FailingClientStream(this.shutdownStatus, tracers);
            } else {
                return this.delegate.newStream(method, headers, callOptions, tracers);
            }
        }

        public void shutdown(Status status) {
            Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            synchronized (this) {
                if (this.pendingApplier.get() < 0) {
                    this.shutdownStatus = status;
                    this.pendingApplier.addAndGet(Integer.MAX_VALUE);
                    if (this.pendingApplier.get() != 0) {
                        this.savedShutdownStatus = status;
                    } else {
                        super.shutdown(status);
                    }
                }
            }
        }

        public void shutdownNow(Status status) {
            Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            synchronized (this) {
                if (this.pendingApplier.get() < 0) {
                    this.shutdownStatus = status;
                    this.pendingApplier.addAndGet(Integer.MAX_VALUE);
                } else if (this.savedShutdownNowStatus != null) {
                    return;
                }
                if (this.pendingApplier.get() != 0) {
                    this.savedShutdownNowStatus = status;
                } else {
                    super.shutdownNow(status);
                }
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
            if (r1 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
            super.shutdownNow(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r0 == null) goto L_0x001a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
            super.shutdown(r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void maybeShutdown() {
            /*
                r3 = this;
                monitor-enter(r3)
                java.util.concurrent.atomic.AtomicInteger r0 = r3.pendingApplier     // Catch:{ all -> 0x0020 }
                int r0 = r0.get()     // Catch:{ all -> 0x0020 }
                if (r0 == 0) goto L_0x000b
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                return
            L_0x000b:
                io.grpc.Status r0 = r3.savedShutdownStatus     // Catch:{ all -> 0x0020 }
                io.grpc.Status r1 = r3.savedShutdownNowStatus     // Catch:{ all -> 0x0020 }
                r2 = 0
                r3.savedShutdownStatus = r2     // Catch:{ all -> 0x0020 }
                r3.savedShutdownNowStatus = r2     // Catch:{ all -> 0x0020 }
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                if (r0 == 0) goto L_0x001a
                super.shutdown(r0)
            L_0x001a:
                if (r1 == 0) goto L_0x001f
                super.shutdownNow(r1)
            L_0x001f:
                return
            L_0x0020:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.CallCredentialsApplyingTransportFactory.CallCredentialsApplyingTransport.maybeShutdown():void");
        }
    }
}
