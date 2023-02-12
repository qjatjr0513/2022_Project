package p004io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import p004io.grpc.Attributes;
import p004io.grpc.Compressor;
import p004io.grpc.Deadline;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.Metadata;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.DelayedStream */
class DelayedStream implements ClientStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private DelayedStreamListener delayedListener;
    private Status error;
    private ClientStreamListener listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingCalls = new ArrayList();
    private List<Runnable> preStartPendingCalls = new ArrayList();
    /* access modifiers changed from: private */
    public ClientStream realStream;
    private long startTimeNanos;
    private long streamSetTimeNanos;

    DelayedStream() {
    }

    public void setMaxInboundMessageSize(final int maxSize) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setMaxInboundMessageSize(maxSize);
            }
        });
    }

    public void setMaxOutboundMessageSize(final int maxSize) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setMaxOutboundMessageSize(maxSize);
            }
        });
    }

    public void setDeadline(final Deadline deadline) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setDeadline(deadline);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void appendTimeoutInsight(p004io.grpc.internal.InsightBuilder r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            io.grpc.internal.ClientStreamListener r0 = r5.listener     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            return
        L_0x0007:
            io.grpc.internal.ClientStream r0 = r5.realStream     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x001f
            java.lang.String r0 = "buffered_nanos"
            long r1 = r5.streamSetTimeNanos     // Catch:{ all -> 0x0036 }
            long r3 = r5.startTimeNanos     // Catch:{ all -> 0x0036 }
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0036 }
            r6.appendKeyValue(r0, r1)     // Catch:{ all -> 0x0036 }
            io.grpc.internal.ClientStream r0 = r5.realStream     // Catch:{ all -> 0x0036 }
            r0.appendTimeoutInsight(r6)     // Catch:{ all -> 0x0036 }
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "buffered_nanos"
            long r1 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0036 }
            long r3 = r5.startTimeNanos     // Catch:{ all -> 0x0036 }
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0036 }
            r6.appendKeyValue(r0, r1)     // Catch:{ all -> 0x0036 }
            java.lang.String r0 = "waiting_for_connection"
            r6.append(r0)     // Catch:{ all -> 0x0036 }
        L_0x0034:
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            return
        L_0x0036:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedStream.appendTimeoutInsight(io.grpc.internal.InsightBuilder):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r0 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        internalStart(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        return new p004io.grpc.internal.DelayedStream.C12234(r3);
     */
    @javax.annotation.CheckReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Runnable setStream(p004io.grpc.internal.ClientStream r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            io.grpc.internal.ClientStream r0 = r3.realStream     // Catch:{ all -> 0x0038 }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r3)     // Catch:{ all -> 0x0038 }
            return r1
        L_0x0008:
            java.lang.String r0 = "stream"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r4, r0)     // Catch:{ all -> 0x0038 }
            io.grpc.internal.ClientStream r0 = (p004io.grpc.internal.ClientStream) r0     // Catch:{ all -> 0x0038 }
            r3.setRealStream(r0)     // Catch:{ all -> 0x0038 }
            io.grpc.internal.ClientStreamListener r0 = r3.listener     // Catch:{ all -> 0x0038 }
            if (r0 != 0) goto L_0x002b
            java.util.List<java.lang.Runnable> r2 = r3.pendingCalls     // Catch:{ all -> 0x0038 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0038 }
            if (r2 == 0) goto L_0x0025
            r3.pendingCalls = r1     // Catch:{ all -> 0x0038 }
            r2 = 1
            r3.passThrough = r2     // Catch:{ all -> 0x0038 }
            goto L_0x002b
        L_0x0025:
            java.lang.AssertionError r1 = new java.lang.AssertionError     // Catch:{ all -> 0x0038 }
            r1.<init>()     // Catch:{ all -> 0x0038 }
            throw r1     // Catch:{ all -> 0x0038 }
        L_0x002b:
            monitor-exit(r3)     // Catch:{ all -> 0x0038 }
            if (r0 != 0) goto L_0x002f
            return r1
        L_0x002f:
            r3.internalStart(r0)
            io.grpc.internal.DelayedStream$4 r1 = new io.grpc.internal.DelayedStream$4
            r1.<init>()
            return r1
        L_0x0038:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0038 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedStream.setStream(io.grpc.internal.ClientStream):java.lang.Runnable");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        r1.drainPendingCallbacks();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r2 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        if (r2.hasNext() == false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        r2.next().run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drainPendingCalls() {
        /*
            r4 = this;
            io.grpc.internal.ClientStream r0 = r4.realStream
            if (r0 == 0) goto L_0x004f
            boolean r0 = r4.passThrough
            if (r0 != 0) goto L_0x0049
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
        L_0x000e:
            monitor-enter(r4)
            java.util.List<java.lang.Runnable> r2 = r4.pendingCalls     // Catch:{ all -> 0x0046 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0046 }
            if (r2 == 0) goto L_0x0027
            r2 = 0
            r4.pendingCalls = r2     // Catch:{ all -> 0x0046 }
            r2 = 1
            r4.passThrough = r2     // Catch:{ all -> 0x0046 }
            io.grpc.internal.DelayedStream$DelayedStreamListener r2 = r4.delayedListener     // Catch:{ all -> 0x0046 }
            r1 = r2
            monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0026
            r1.drainPendingCallbacks()
        L_0x0026:
            return
        L_0x0027:
            r2 = r0
            java.util.List<java.lang.Runnable> r3 = r4.pendingCalls     // Catch:{ all -> 0x0046 }
            r0 = r3
            r4.pendingCalls = r2     // Catch:{ all -> 0x0046 }
            monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            java.util.Iterator r2 = r0.iterator()
        L_0x0032:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0042
            java.lang.Object r3 = r2.next()
            java.lang.Runnable r3 = (java.lang.Runnable) r3
            r3.run()
            goto L_0x0032
        L_0x0042:
            r0.clear()
            goto L_0x000e
        L_0x0046:
            r2 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            throw r2
        L_0x0049:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x004f:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedStream.drainPendingCalls():void");
    }

    private void delayOrExecute(Runnable runnable) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingCalls.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public void setAuthority(final String authority) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(authority, "authority");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setAuthority(authority);
            }
        });
    }

    public void start(ClientStreamListener listener2) {
        Status savedError;
        boolean savedPassThrough;
        Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            savedError = this.error;
            savedPassThrough = this.passThrough;
            if (!savedPassThrough) {
                DelayedStreamListener delayedStreamListener = new DelayedStreamListener(listener2);
                this.delayedListener = delayedStreamListener;
                listener2 = delayedStreamListener;
            }
            this.listener = listener2;
            this.startTimeNanos = System.nanoTime();
        }
        if (savedError != null) {
            listener2.closed(savedError, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
        } else if (savedPassThrough) {
            internalStart(listener2);
        }
    }

    private void internalStart(ClientStreamListener listener2) {
        for (Runnable runnable : this.preStartPendingCalls) {
            runnable.run();
        }
        this.preStartPendingCalls = null;
        this.realStream.start(listener2);
    }

    public Attributes getAttributes() {
        ClientStream savedRealStream;
        synchronized (this) {
            savedRealStream = this.realStream;
        }
        if (savedRealStream != null) {
            return savedRealStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public void writeMessage(final InputStream message) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        Preconditions.checkNotNull(message, "message");
        if (this.passThrough) {
            this.realStream.writeMessage(message);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.writeMessage(message);
                }
            });
        }
    }

    public void flush() {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.flush();
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.flush();
                }
            });
        }
    }

    public void cancel(final Status reason) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        Preconditions.checkNotNull(reason, "reason");
        boolean delegateToRealStream = true;
        synchronized (this) {
            if (this.realStream == null) {
                setRealStream(NoopClientStream.INSTANCE);
                delegateToRealStream = false;
                this.error = reason;
            }
        }
        if (delegateToRealStream) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.cancel(reason);
                }
            });
            return;
        }
        drainPendingCalls();
        onEarlyCancellation(reason);
        this.listener.closed(reason, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
    }

    /* access modifiers changed from: protected */
    public void onEarlyCancellation(Status reason) {
    }

    private void setRealStream(ClientStream realStream2) {
        ClientStream clientStream = this.realStream;
        Preconditions.checkState(clientStream == null, "realStream already set to %s", (Object) clientStream);
        this.realStream = realStream2;
        this.streamSetTimeNanos = System.nanoTime();
    }

    public void halfClose() {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        delayOrExecute(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.halfClose();
            }
        });
    }

    public void request(final int numMessages) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.request(numMessages);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.request(numMessages);
                }
            });
        }
    }

    public void optimizeForDirectExecutor() {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.optimizeForDirectExecutor();
            }
        });
    }

    public void setCompressor(final Compressor compressor) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(compressor, "compressor");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setCompressor(compressor);
            }
        });
    }

    public void setFullStreamDecompression(final boolean fullStreamDecompression) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setFullStreamDecompression(fullStreamDecompression);
            }
        });
    }

    public void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(decompressorRegistry, "decompressorRegistry");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    public boolean isReady() {
        if (this.passThrough) {
            return this.realStream.isReady();
        }
        return false;
    }

    public void setMessageCompression(final boolean enable) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.setMessageCompression(enable);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.setMessageCompression(enable);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public ClientStream getRealStream() {
        return this.realStream;
    }

    /* renamed from: io.grpc.internal.DelayedStream$DelayedStreamListener */
    private static class DelayedStreamListener implements ClientStreamListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        /* access modifiers changed from: private */
        public final ClientStreamListener realListener;

        static {
            Class<DelayedStream> cls = DelayedStream.class;
        }

        public DelayedStreamListener(ClientStreamListener listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            if (this.passThrough) {
                this.realListener.messagesAvailable(producer);
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedStreamListener.this.realListener.messagesAvailable(producer);
                    }
                });
            }
        }

        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedStreamListener.this.realListener.onReady();
                    }
                });
            }
        }

        public void headersRead(final Metadata headers) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStreamListener.this.realListener.headersRead(headers);
                }
            });
        }

        public void closed(final Status status, final ClientStreamListener.RpcProgress rpcProgress, final Metadata trailers) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStreamListener.this.realListener.closed(status, rpcProgress, trailers);
                }
            });
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
            r1 = r0.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            if (r1.hasNext() == false) goto L_0x0035;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
            r1.next().run();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainPendingCallbacks() {
            /*
                r3 = this;
                boolean r0 = r3.passThrough
                if (r0 != 0) goto L_0x003c
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
            L_0x0009:
                monitor-enter(r3)
                java.util.List<java.lang.Runnable> r1 = r3.pendingCallbacks     // Catch:{ all -> 0x0039 }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0039 }
                if (r1 == 0) goto L_0x001a
                r1 = 0
                r3.pendingCallbacks = r1     // Catch:{ all -> 0x0039 }
                r1 = 1
                r3.passThrough = r1     // Catch:{ all -> 0x0039 }
                monitor-exit(r3)     // Catch:{ all -> 0x0039 }
                return
            L_0x001a:
                r1 = r0
                java.util.List<java.lang.Runnable> r2 = r3.pendingCallbacks     // Catch:{ all -> 0x0039 }
                r0 = r2
                r3.pendingCallbacks = r1     // Catch:{ all -> 0x0039 }
                monitor-exit(r3)     // Catch:{ all -> 0x0039 }
                java.util.Iterator r1 = r0.iterator()
            L_0x0025:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x0035
                java.lang.Object r2 = r1.next()
                java.lang.Runnable r2 = (java.lang.Runnable) r2
                r2.run()
                goto L_0x0025
            L_0x0035:
                r0.clear()
                goto L_0x0009
            L_0x0039:
                r1 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0039 }
                throw r1
            L_0x003c:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.DelayedStream.DelayedStreamListener.drainPendingCallbacks():void");
        }
    }
}
