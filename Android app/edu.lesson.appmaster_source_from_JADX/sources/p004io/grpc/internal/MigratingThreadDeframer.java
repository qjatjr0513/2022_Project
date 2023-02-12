package p004io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;
import p004io.grpc.Decompressor;
import p004io.grpc.internal.ApplicationThreadDeframerListener;
import p004io.grpc.internal.MessageDeframer;
import p004io.grpc.internal.StreamListener;
import p004io.perfmark.Link;
import p004io.perfmark.PerfMark;

/* renamed from: io.grpc.internal.MigratingThreadDeframer */
final class MigratingThreadDeframer implements ThreadOptimizedDeframer {
    /* access modifiers changed from: private */
    public final ApplicationThreadDeframerListener appListener;
    /* access modifiers changed from: private */
    public final MessageDeframer deframer;
    /* access modifiers changed from: private */
    public boolean deframerOnTransportThread;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public final DeframeMessageProducer messageProducer = new DeframeMessageProducer();
    /* access modifiers changed from: private */
    public boolean messageProducerEnqueued;
    /* access modifiers changed from: private */
    public final MigratingDeframerListener migratingListener;
    /* access modifiers changed from: private */
    public final Queue<C1284Op> opQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public final ApplicationThreadDeframerListener.TransportExecutor transportExecutor;
    /* access modifiers changed from: private */
    public final MessageDeframer.Listener transportListener;

    /* renamed from: io.grpc.internal.MigratingThreadDeframer$Op */
    private interface C1284Op {
        void run(boolean z);
    }

    public MigratingThreadDeframer(MessageDeframer.Listener listener, ApplicationThreadDeframerListener.TransportExecutor transportExecutor2, MessageDeframer deframer2) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.transportListener = squelchLateMessagesAvailableDeframerListener;
        this.transportExecutor = (ApplicationThreadDeframerListener.TransportExecutor) Preconditions.checkNotNull(transportExecutor2, "transportExecutor");
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor2);
        this.appListener = applicationThreadDeframerListener;
        MigratingDeframerListener migratingDeframerListener = new MigratingDeframerListener(applicationThreadDeframerListener);
        this.migratingListener = migratingDeframerListener;
        deframer2.setListener(migratingDeframerListener);
        this.deframer = deframer2;
    }

    public void setMaxInboundMessageSize(int messageSize) {
        this.deframer.setMaxInboundMessageSize(messageSize);
    }

    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
        this.deframer.setFullStreamDecompressor(fullStreamDecompressor);
    }

    private boolean runWhereAppropriate(C1284Op op) {
        return runWhereAppropriate(op, true);
    }

    private boolean runWhereAppropriate(C1284Op op, boolean currentThreadIsTransportThread) {
        boolean deframerOnTransportThreadCopy;
        boolean alreadyEnqueued;
        String str;
        synchronized (this.lock) {
            deframerOnTransportThreadCopy = this.deframerOnTransportThread;
            alreadyEnqueued = this.messageProducerEnqueued;
            if (!deframerOnTransportThreadCopy) {
                this.opQueue.offer(op);
                this.messageProducerEnqueued = true;
            }
        }
        if (deframerOnTransportThreadCopy) {
            op.run(true);
            return true;
        } else if (alreadyEnqueued) {
            return false;
        } else {
            if (currentThreadIsTransportThread) {
                PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                try {
                    this.transportListener.messagesAvailable(this.messageProducer);
                    return false;
                } finally {
                    str = "MigratingThreadDeframer.messageAvailable";
                    PerfMark.stopTask(str);
                }
            } else {
                final Link link = PerfMark.linkOut();
                this.transportExecutor.runOnTransportThread(new Runnable() {
                    public void run() {
                        PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                        PerfMark.linkIn(link);
                        try {
                            MigratingThreadDeframer.this.transportListener.messagesAvailable(MigratingThreadDeframer.this.messageProducer);
                        } finally {
                            PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                        }
                    }
                });
                return false;
            }
        }
    }

    public void request(final int numMessages) {
        runWhereAppropriate(new C1284Op() {
            public void run(boolean isDeframerOnTransportThread) {
                if (isDeframerOnTransportThread) {
                    final Link link = PerfMark.linkOut();
                    MigratingThreadDeframer.this.transportExecutor.runOnTransportThread(new Runnable() {
                        public void run() {
                            PerfMark.startTask("MigratingThreadDeframer.request");
                            PerfMark.linkIn(link);
                            try {
                                MigratingThreadDeframer.this.requestFromTransportThread(numMessages);
                            } finally {
                                PerfMark.stopTask("MigratingThreadDeframer.request");
                            }
                        }
                    });
                    return;
                }
                PerfMark.startTask("MigratingThreadDeframer.request");
                try {
                    MigratingThreadDeframer.this.deframer.request(numMessages);
                } catch (Throwable th) {
                    PerfMark.stopTask("MigratingThreadDeframer.request");
                    throw th;
                }
                PerfMark.stopTask("MigratingThreadDeframer.request");
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void requestFromTransportThread(final int numMessages) {
        runWhereAppropriate(new C1284Op() {
            public void run(boolean isDeframerOnTransportThread) {
                if (!isDeframerOnTransportThread) {
                    MigratingThreadDeframer.this.request(numMessages);
                    return;
                }
                try {
                    MigratingThreadDeframer.this.deframer.request(numMessages);
                } catch (Throwable t) {
                    MigratingThreadDeframer.this.appListener.deframeFailed(t);
                    MigratingThreadDeframer.this.deframer.close();
                }
                if (!MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                    synchronized (MigratingThreadDeframer.this.lock) {
                        PerfMark.event("MigratingThreadDeframer.deframerOnApplicationThread");
                        MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.appListener);
                        boolean unused = MigratingThreadDeframer.this.deframerOnTransportThread = false;
                    }
                }
            }
        });
    }

    public void deframe(final ReadableBuffer data) {
        runWhereAppropriate(new Object() {
            /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
                p004io.grpc.internal.MigratingThreadDeframer.access$500(r3.this$0).deframeFailed(r1);
                p004io.grpc.internal.MigratingThreadDeframer.access$400(r3.this$0).close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
                p004io.perfmark.PerfMark.stopTask("MigratingThreadDeframer.deframe");
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
                throw r1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [B:2:0x0007, B:7:0x0018] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run(boolean r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "MigratingThreadDeframer.deframe"
                    p004io.perfmark.PerfMark.startTask(r0)
                    if (r4 == 0) goto L_0x0018
                    io.grpc.internal.MigratingThreadDeframer r1 = p004io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x0016 }
                    io.grpc.internal.MessageDeframer r1 = r1.deframer     // Catch:{ all -> 0x0016 }
                    io.grpc.internal.ReadableBuffer r2 = r2     // Catch:{ all -> 0x0016 }
                    r1.deframe(r2)     // Catch:{ all -> 0x0016 }
                    p004io.perfmark.PerfMark.stopTask(r0)
                    return
                L_0x0016:
                    r1 = move-exception
                    goto L_0x003c
                L_0x0018:
                    io.grpc.internal.MigratingThreadDeframer r1 = p004io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x0024 }
                    io.grpc.internal.MessageDeframer r1 = r1.deframer     // Catch:{ all -> 0x0024 }
                    io.grpc.internal.ReadableBuffer r2 = r2     // Catch:{ all -> 0x0024 }
                    r1.deframe(r2)     // Catch:{ all -> 0x0024 }
                    goto L_0x0037
                L_0x0024:
                    r1 = move-exception
                    io.grpc.internal.MigratingThreadDeframer r2 = p004io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x0016 }
                    io.grpc.internal.ApplicationThreadDeframerListener r2 = r2.appListener     // Catch:{ all -> 0x0016 }
                    r2.deframeFailed(r1)     // Catch:{ all -> 0x0016 }
                    io.grpc.internal.MigratingThreadDeframer r2 = p004io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x0016 }
                    io.grpc.internal.MessageDeframer r2 = r2.deframer     // Catch:{ all -> 0x0016 }
                    r2.close()     // Catch:{ all -> 0x0016 }
                L_0x0037:
                    p004io.perfmark.PerfMark.stopTask(r0)
                    return
                L_0x003c:
                    p004io.perfmark.PerfMark.stopTask(r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.MigratingThreadDeframer.AnonymousClass1DeframeOp.run(boolean):void");
            }

            public void close() {
                data.close();
            }
        });
    }

    public void closeWhenComplete() {
        runWhereAppropriate(new C1284Op() {
            public void run(boolean isDeframerOnTransportThread) {
                MigratingThreadDeframer.this.deframer.closeWhenComplete();
            }
        });
    }

    public void close() {
        if (!runWhereAppropriate(new C1284Op() {
            public void run(boolean isDeframerOnTransportThread) {
                MigratingThreadDeframer.this.deframer.close();
            }
        })) {
            this.deframer.stopDelivery();
        }
    }

    /* renamed from: io.grpc.internal.MigratingThreadDeframer$DeframeMessageProducer */
    class DeframeMessageProducer implements StreamListener.MessageProducer, Closeable {
        DeframeMessageProducer() {
        }

        public InputStream next() {
            C1284Op op;
            while (true) {
                InputStream is = MigratingThreadDeframer.this.appListener.messageReadQueuePoll();
                if (is != null) {
                    return is;
                }
                synchronized (MigratingThreadDeframer.this.lock) {
                    op = (C1284Op) MigratingThreadDeframer.this.opQueue.poll();
                    if (op == null) {
                        if (MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                            PerfMark.event("MigratingThreadDeframer.deframerOnTransportThread");
                            MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.transportListener);
                            boolean unused = MigratingThreadDeframer.this.deframerOnTransportThread = true;
                        }
                        boolean unused2 = MigratingThreadDeframer.this.messageProducerEnqueued = false;
                        return null;
                    }
                }
                op.run(false);
            }
            while (true) {
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x001b A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() {
            /*
                r4 = this;
            L_0x0000:
                io.grpc.internal.MigratingThreadDeframer r0 = p004io.grpc.internal.MigratingThreadDeframer.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
            L_0x0007:
                io.grpc.internal.MigratingThreadDeframer r1 = p004io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x002b }
                java.util.Queue r1 = r1.opQueue     // Catch:{ all -> 0x002b }
                java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x002b }
                io.grpc.internal.MigratingThreadDeframer$Op r1 = (p004io.grpc.internal.MigratingThreadDeframer.C1284Op) r1     // Catch:{ all -> 0x002b }
                if (r1 == 0) goto L_0x0019
                boolean r2 = r1 instanceof java.io.Closeable     // Catch:{ all -> 0x002b }
                if (r2 == 0) goto L_0x0007
            L_0x0019:
                if (r1 != 0) goto L_0x0023
                io.grpc.internal.MigratingThreadDeframer r2 = p004io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x002b }
                r3 = 0
                boolean unused = r2.messageProducerEnqueued = r3     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                r0 = r1
                java.io.Closeable r0 = (java.io.Closeable) r0
                p004io.grpc.internal.GrpcUtil.closeQuietly((java.io.Closeable) r0)
                goto L_0x0000
            L_0x002b:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.MigratingThreadDeframer.DeframeMessageProducer.close():void");
        }
    }

    /* renamed from: io.grpc.internal.MigratingThreadDeframer$MigratingDeframerListener */
    static class MigratingDeframerListener extends ForwardingDeframerListener {
        private MessageDeframer.Listener delegate;

        public MigratingDeframerListener(MessageDeframer.Listener delegate2) {
            setDelegate(delegate2);
        }

        /* access modifiers changed from: protected */
        public MessageDeframer.Listener delegate() {
            return this.delegate;
        }

        public void setDelegate(MessageDeframer.Listener delegate2) {
            this.delegate = (MessageDeframer.Listener) Preconditions.checkNotNull(delegate2, "delegate");
        }
    }
}
