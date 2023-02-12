package p004io.grpc.internal;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Context;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalLogId;
import p004io.grpc.LoadBalancer;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.SynchronizationContext;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.ClientTransport;
import p004io.grpc.internal.ManagedClientTransport;

/* renamed from: io.grpc.internal.DelayedClientTransport */
final class DelayedClientTransport implements ManagedClientTransport {
    private final Executor defaultAppExecutor;
    @Nullable
    private LoadBalancer.SubchannelPicker lastPicker;
    private long lastPickerVersion;
    /* access modifiers changed from: private */
    public ManagedClientTransport.Listener listener;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId = InternalLogId.allocate((Class<?>) DelayedClientTransport.class, (String) null);
    /* access modifiers changed from: private */
    @Nonnull
    public Collection<PendingStream> pendingStreams = new LinkedHashSet();
    private Runnable reportTransportInUse;
    /* access modifiers changed from: private */
    public Runnable reportTransportNotInUse;
    /* access modifiers changed from: private */
    public Runnable reportTransportTerminated;
    /* access modifiers changed from: private */
    public Status shutdownStatus;
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;

    DelayedClientTransport(Executor defaultAppExecutor2, SynchronizationContext syncContext2) {
        this.defaultAppExecutor = defaultAppExecutor2;
        this.syncContext = syncContext2;
    }

    public final Runnable start(final ManagedClientTransport.Listener listener2) {
        this.listener = listener2;
        this.reportTransportInUse = new Runnable() {
            public void run() {
                listener2.transportInUse(true);
            }
        };
        this.reportTransportNotInUse = new Runnable() {
            public void run() {
                listener2.transportInUse(false);
            }
        };
        this.reportTransportTerminated = new Runnable() {
            public void run() {
                listener2.transportTerminated();
            }
        };
        return null;
    }

    public final ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) {
        ClientTransport transport;
        try {
            LoadBalancer.PickSubchannelArgs args = new PickSubchannelArgsImpl(method, headers, callOptions);
            LoadBalancer.SubchannelPicker picker = null;
            long pickerVersion = -1;
            do {
                synchronized (this.lock) {
                    if (this.shutdownStatus != null) {
                        FailingClientStream failingClientStream = new FailingClientStream(this.shutdownStatus, tracers);
                        this.syncContext.drain();
                        return failingClientStream;
                    }
                    LoadBalancer.SubchannelPicker subchannelPicker = this.lastPicker;
                    if (subchannelPicker == null) {
                        PendingStream createPendingStream = createPendingStream(args, tracers);
                        this.syncContext.drain();
                        return createPendingStream;
                    }
                    if (picker != null) {
                        if (pickerVersion == this.lastPickerVersion) {
                            PendingStream createPendingStream2 = createPendingStream(args, tracers);
                            this.syncContext.drain();
                            return createPendingStream2;
                        }
                    }
                    picker = subchannelPicker;
                    pickerVersion = this.lastPickerVersion;
                    transport = GrpcUtil.getTransportFromPickResult(picker.pickSubchannel(args), callOptions.isWaitForReady());
                }
            } while (transport == null);
            ClientStream newStream = transport.newStream(args.getMethodDescriptor(), args.getHeaders(), args.getCallOptions(), tracers);
            this.syncContext.drain();
            return newStream;
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    private PendingStream createPendingStream(LoadBalancer.PickSubchannelArgs args, ClientStreamTracer[] tracers) {
        PendingStream pendingStream = new PendingStream(args, tracers);
        this.pendingStreams.add(pendingStream);
        if (getPendingStreamsCount() == 1) {
            this.syncContext.executeLater(this.reportTransportInUse);
        }
        return pendingStream;
    }

    public final void ping(ClientTransport.PingCallback callback, Executor executor) {
        throw new UnsupportedOperationException("This method is not expected to be called");
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture<InternalChannelz.SocketStats> ret = SettableFuture.create();
        ret.set(null);
        return ret;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r3.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void shutdown(final p004io.grpc.Status r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            io.grpc.Status r1 = r3.shutdownStatus     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0009:
            r3.shutdownStatus = r4     // Catch:{ all -> 0x002e }
            io.grpc.SynchronizationContext r1 = r3.syncContext     // Catch:{ all -> 0x002e }
            io.grpc.internal.DelayedClientTransport$4 r2 = new io.grpc.internal.DelayedClientTransport$4     // Catch:{ all -> 0x002e }
            r2.<init>(r4)     // Catch:{ all -> 0x002e }
            r1.executeLater(r2)     // Catch:{ all -> 0x002e }
            boolean r1 = r3.hasPendingStreams()     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x0027
            java.lang.Runnable r1 = r3.reportTransportTerminated     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0027
            io.grpc.SynchronizationContext r2 = r3.syncContext     // Catch:{ all -> 0x002e }
            r2.executeLater(r1)     // Catch:{ all -> 0x002e }
            r1 = 0
            r3.reportTransportTerminated = r1     // Catch:{ all -> 0x002e }
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            io.grpc.SynchronizationContext r0 = r3.syncContext
            r0.drain()
            return
        L_0x002e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedClientTransport.shutdown(io.grpc.Status):void");
    }

    public final void shutdownNow(Status status) {
        Collection<PendingStream> savedPendingStreams;
        Runnable savedReportTransportTerminated;
        shutdown(status);
        synchronized (this.lock) {
            Collection<PendingStream> collection = this.pendingStreams;
            savedPendingStreams = collection;
            savedReportTransportTerminated = this.reportTransportTerminated;
            this.reportTransportTerminated = null;
            if (!collection.isEmpty()) {
                this.pendingStreams = Collections.emptyList();
            }
        }
        if (savedReportTransportTerminated != null) {
            for (PendingStream stream : savedPendingStreams) {
                Runnable runnable = stream.setStream(new FailingClientStream(status, ClientStreamListener.RpcProgress.REFUSED, stream.tracers));
                if (runnable != null) {
                    runnable.run();
                }
            }
            this.syncContext.execute(savedReportTransportTerminated);
        }
    }

    public final boolean hasPendingStreams() {
        boolean z;
        synchronized (this.lock) {
            z = !this.pendingStreams.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final int getPendingStreamsCount() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r2 = new java.util.ArrayList();
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (r0.hasNext() == false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        r3 = r0.next();
        r4 = r10.pickSubchannel(p004io.grpc.internal.DelayedClientTransport.PendingStream.access$300(r3));
        r5 = p004io.grpc.internal.DelayedClientTransport.PendingStream.access$300(r3).getCallOptions();
        r6 = p004io.grpc.internal.GrpcUtil.getTransportFromPickResult(r4, r5.isWaitForReady());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        if (r6 == null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        r7 = r9.defaultAppExecutor;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        if (r5.getExecutor() == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0057, code lost:
        r7 = r5.getExecutor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005b, code lost:
        r8 = p004io.grpc.internal.DelayedClientTransport.PendingStream.access$400(r3, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        if (r8 == null) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        r7.execute(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        r2.add(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        r3 = r9.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006f, code lost:
        if (hasPendingStreams() != false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0071, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0072, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        r9.pendingStreams.removeAll(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        if (r9.pendingStreams.isEmpty() == false) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0080, code lost:
        r9.pendingStreams = new java.util.LinkedHashSet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
        if (hasPendingStreams() != false) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        r9.syncContext.executeLater(r9.reportTransportNotInUse);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0096, code lost:
        if (r9.shutdownStatus == null) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0098, code lost:
        r0 = r9.reportTransportTerminated;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009a, code lost:
        if (r0 == null) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
        r9.syncContext.executeLater(r0);
        r9.reportTransportTerminated = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a4, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a5, code lost:
        r9.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00aa, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void reprocess(@javax.annotation.Nullable p004io.grpc.LoadBalancer.SubchannelPicker r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.lock
            monitor-enter(r0)
            r9.lastPicker = r10     // Catch:{ all -> 0x00b0 }
            long r1 = r9.lastPickerVersion     // Catch:{ all -> 0x00b0 }
            r3 = 1
            long r1 = r1 + r3
            r9.lastPickerVersion = r1     // Catch:{ all -> 0x00b0 }
            if (r10 == 0) goto L_0x00ae
            boolean r1 = r9.hasPendingStreams()     // Catch:{ all -> 0x00b0 }
            if (r1 != 0) goto L_0x0016
            goto L_0x00ae
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00b0 }
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r2 = r9.pendingStreams     // Catch:{ all -> 0x00b0 }
            r1.<init>(r2)     // Catch:{ all -> 0x00b0 }
            monitor-exit(r0)     // Catch:{ all -> 0x00b0 }
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = r0
            java.util.Iterator r0 = r1.iterator()
        L_0x0028:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0068
            java.lang.Object r3 = r0.next()
            io.grpc.internal.DelayedClientTransport$PendingStream r3 = (p004io.grpc.internal.DelayedClientTransport.PendingStream) r3
            io.grpc.LoadBalancer$PickSubchannelArgs r4 = r3.args
            io.grpc.LoadBalancer$PickResult r4 = r10.pickSubchannel(r4)
            io.grpc.LoadBalancer$PickSubchannelArgs r5 = r3.args
            io.grpc.CallOptions r5 = r5.getCallOptions()
            boolean r6 = r5.isWaitForReady()
            io.grpc.internal.ClientTransport r6 = p004io.grpc.internal.GrpcUtil.getTransportFromPickResult(r4, r6)
            if (r6 == 0) goto L_0x0067
            java.util.concurrent.Executor r7 = r9.defaultAppExecutor
            java.util.concurrent.Executor r8 = r5.getExecutor()
            if (r8 == 0) goto L_0x005b
            java.util.concurrent.Executor r7 = r5.getExecutor()
        L_0x005b:
            java.lang.Runnable r8 = r3.createRealStream(r6)
            if (r8 == 0) goto L_0x0064
            r7.execute(r8)
        L_0x0064:
            r2.add(r3)
        L_0x0067:
            goto L_0x0028
        L_0x0068:
            java.lang.Object r3 = r9.lock
            monitor-enter(r3)
            boolean r0 = r9.hasPendingStreams()     // Catch:{ all -> 0x00ab }
            if (r0 != 0) goto L_0x0073
            monitor-exit(r3)     // Catch:{ all -> 0x00ab }
            return
        L_0x0073:
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r0 = r9.pendingStreams     // Catch:{ all -> 0x00ab }
            r0.removeAll(r2)     // Catch:{ all -> 0x00ab }
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r0 = r9.pendingStreams     // Catch:{ all -> 0x00ab }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00ab }
            if (r0 == 0) goto L_0x0087
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet     // Catch:{ all -> 0x00ab }
            r0.<init>()     // Catch:{ all -> 0x00ab }
            r9.pendingStreams = r0     // Catch:{ all -> 0x00ab }
        L_0x0087:
            boolean r0 = r9.hasPendingStreams()     // Catch:{ all -> 0x00ab }
            if (r0 != 0) goto L_0x00a4
            io.grpc.SynchronizationContext r0 = r9.syncContext     // Catch:{ all -> 0x00ab }
            java.lang.Runnable r4 = r9.reportTransportNotInUse     // Catch:{ all -> 0x00ab }
            r0.executeLater(r4)     // Catch:{ all -> 0x00ab }
            io.grpc.Status r0 = r9.shutdownStatus     // Catch:{ all -> 0x00ab }
            if (r0 == 0) goto L_0x00a4
            java.lang.Runnable r0 = r9.reportTransportTerminated     // Catch:{ all -> 0x00ab }
            if (r0 == 0) goto L_0x00a4
            io.grpc.SynchronizationContext r4 = r9.syncContext     // Catch:{ all -> 0x00ab }
            r4.executeLater(r0)     // Catch:{ all -> 0x00ab }
            r0 = 0
            r9.reportTransportTerminated = r0     // Catch:{ all -> 0x00ab }
        L_0x00a4:
            monitor-exit(r3)     // Catch:{ all -> 0x00ab }
            io.grpc.SynchronizationContext r0 = r9.syncContext
            r0.drain()
            return
        L_0x00ab:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00ab }
            throw r0
        L_0x00ae:
            monitor-exit(r0)     // Catch:{ all -> 0x00b0 }
            return
        L_0x00b0:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00b0 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedClientTransport.reprocess(io.grpc.LoadBalancer$SubchannelPicker):void");
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* renamed from: io.grpc.internal.DelayedClientTransport$PendingStream */
    private class PendingStream extends DelayedStream {
        /* access modifiers changed from: private */
        public final LoadBalancer.PickSubchannelArgs args;
        private final Context context;
        /* access modifiers changed from: private */
        public final ClientStreamTracer[] tracers;

        private PendingStream(LoadBalancer.PickSubchannelArgs args2, ClientStreamTracer[] tracers2) {
            this.context = Context.current();
            this.args = args2;
            this.tracers = tracers2;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: private */
        public Runnable createRealStream(ClientTransport transport) {
            Context origContext = this.context.attach();
            try {
                ClientStream realStream = transport.newStream(this.args.getMethodDescriptor(), this.args.getHeaders(), this.args.getCallOptions(), this.tracers);
                this.context.detach(origContext);
                return setStream(realStream);
            } catch (Throwable th) {
                this.context.detach(origContext);
                throw th;
            }
        }

        public void cancel(Status reason) {
            super.cancel(reason);
            synchronized (DelayedClientTransport.this.lock) {
                if (DelayedClientTransport.this.reportTransportTerminated != null) {
                    boolean justRemovedAnElement = DelayedClientTransport.this.pendingStreams.remove(this);
                    if (!DelayedClientTransport.this.hasPendingStreams() && justRemovedAnElement) {
                        DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportNotInUse);
                        if (DelayedClientTransport.this.shutdownStatus != null) {
                            DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportTerminated);
                            Runnable unused = DelayedClientTransport.this.reportTransportTerminated = null;
                        }
                    }
                }
            }
            DelayedClientTransport.this.syncContext.drain();
        }

        /* access modifiers changed from: protected */
        public void onEarlyCancellation(Status reason) {
            for (ClientStreamTracer tracer : this.tracers) {
                tracer.streamClosed(reason);
            }
        }

        public void appendTimeoutInsight(InsightBuilder insight) {
            if (this.args.getCallOptions().isWaitForReady()) {
                insight.append("wait_for_ready");
            }
            super.appendTimeoutInsight(insight);
        }
    }
}
