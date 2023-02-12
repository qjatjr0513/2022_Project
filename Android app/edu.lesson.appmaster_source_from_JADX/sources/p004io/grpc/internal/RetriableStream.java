package p004io.grpc.internal;

import androidx.core.app.NotificationManagerCompat;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Compressor;
import p004io.grpc.Deadline;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.SynchronizationContext;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.RetriableStream */
abstract class RetriableStream<ReqT> implements ClientStream {
    /* access modifiers changed from: private */
    public static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.m347of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.m347of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    /* access modifiers changed from: private */
    public static Random random = new Random();
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    private Status cancellationStatus;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final ChannelBufferMeter channelBufferUsed;
    /* access modifiers changed from: private */
    public final InsightBuilder closedSubstreamsInsight = new InsightBuilder();
    private final Metadata headers;
    /* access modifiers changed from: private */
    @Nullable
    public final HedgingPolicy hedgingPolicy;
    /* access modifiers changed from: private */
    public boolean isClosed;
    /* access modifiers changed from: private */
    public final boolean isHedging;
    /* access modifiers changed from: private */
    public final Executor listenerSerializeExecutor = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread t, Throwable e) {
            throw Status.fromThrowable(e).withDescription("Uncaught exception in the SynchronizationContext. Re-thrown.").asRuntimeException();
        }
    });
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public ClientStreamListener masterListener;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, ?> method;
    /* access modifiers changed from: private */
    public long nextBackoffIntervalNanos;
    /* access modifiers changed from: private */
    public final AtomicBoolean noMoreTransparentRetry = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public long perRpcBufferUsed;
    /* access modifiers changed from: private */
    @Nullable
    public final RetryPolicy retryPolicy;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutorService;
    /* access modifiers changed from: private */
    public FutureCanceller scheduledHedging;
    /* access modifiers changed from: private */
    public FutureCanceller scheduledRetry;
    /* access modifiers changed from: private */
    public volatile State state = new State(new ArrayList(8), Collections.emptyList(), (Collection<Substream>) null, (Substream) null, false, false, false, 0);
    /* access modifiers changed from: private */
    @Nullable
    public final Throttle throttle;

    /* renamed from: io.grpc.internal.RetriableStream$BufferEntry */
    private interface BufferEntry {
        void runWith(Substream substream);
    }

    /* access modifiers changed from: package-private */
    public abstract ClientStream newSubstream(Metadata metadata, ClientStreamTracer.Factory factory, int i, boolean z);

    /* access modifiers changed from: package-private */
    public abstract void postCommit();

    /* access modifiers changed from: package-private */
    @CheckReturnValue
    @Nullable
    public abstract Status prestart();

    RetriableStream(MethodDescriptor<ReqT, ?> method2, Metadata headers2, ChannelBufferMeter channelBufferUsed2, long perRpcBufferLimit2, long channelBufferLimit2, Executor callExecutor2, ScheduledExecutorService scheduledExecutorService2, @Nullable RetryPolicy retryPolicy2, @Nullable HedgingPolicy hedgingPolicy2, @Nullable Throttle throttle2) {
        RetryPolicy retryPolicy3 = retryPolicy2;
        HedgingPolicy hedgingPolicy3 = hedgingPolicy2;
        this.method = method2;
        this.channelBufferUsed = channelBufferUsed2;
        this.perRpcBufferLimit = perRpcBufferLimit2;
        this.channelBufferLimit = channelBufferLimit2;
        this.callExecutor = callExecutor2;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.headers = headers2;
        this.retryPolicy = retryPolicy3;
        if (retryPolicy3 != null) {
            this.nextBackoffIntervalNanos = retryPolicy3.initialBackoffNanos;
        }
        this.hedgingPolicy = hedgingPolicy3;
        boolean z = false;
        Preconditions.checkArgument(retryPolicy3 == null || hedgingPolicy3 == null, "Should not provide both retryPolicy and hedgingPolicy");
        this.isHedging = hedgingPolicy3 != null ? true : z;
        this.throttle = throttle2;
    }

    /* access modifiers changed from: private */
    @CheckReturnValue
    @Nullable
    public Runnable commit(Substream winningSubstream) {
        Future<?> retryFuture;
        Future<?> hedgingFuture;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> savedDrainedSubstreams = this.state.drainedSubstreams;
            this.state = this.state.committed(winningSubstream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                retryFuture = futureCanceller.markCancelled();
                this.scheduledRetry = null;
            } else {
                retryFuture = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                Future<?> hedgingFuture2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
                hedgingFuture = hedgingFuture2;
            } else {
                hedgingFuture = null;
            }
            final Substream substream = winningSubstream;
            final Future<?> future = retryFuture;
            final Future<?> future2 = hedgingFuture;
            AnonymousClass1CommitTask r3 = new Runnable() {
                public void run() {
                    for (Substream substream : savedDrainedSubstreams) {
                        if (substream != substream) {
                            substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    Future future = future;
                    if (future != null) {
                        future.cancel(false);
                    }
                    Future future2 = future2;
                    if (future2 != null) {
                        future2.cancel(false);
                    }
                    RetriableStream.this.postCommit();
                }
            };
            return r3;
        }
    }

    /* access modifiers changed from: private */
    public void commitAndRun(Substream winningSubstream) {
        Runnable postCommitTask = commit(winningSubstream);
        if (postCommitTask != null) {
            postCommitTask.run();
        }
    }

    /* access modifiers changed from: private */
    public Substream createSubstream(int previousAttemptCount, boolean isTransparentRetry) {
        Substream sub = new Substream(previousAttemptCount);
        final ClientStreamTracer bufferSizeTracer = new BufferSizeTracer(sub);
        sub.stream = newSubstream(updateHeaders(this.headers, previousAttemptCount), new ClientStreamTracer.Factory() {
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo info, Metadata headers) {
                return bufferSizeTracer;
            }
        }, previousAttemptCount, isTransparentRetry);
        return sub;
    }

    /* access modifiers changed from: package-private */
    public final Metadata updateHeaders(Metadata originalHeaders, int previousAttemptCount) {
        Metadata newHeaders = new Metadata();
        newHeaders.merge(originalHeaders);
        if (previousAttemptCount > 0) {
            newHeaders.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(previousAttemptCount));
        }
        return newHeaders;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0082, code lost:
        r5 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008a, code lost:
        if (r5.hasNext() == false) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008c, code lost:
        r7 = r5.next();
        r7.runWith(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
        if ((r7 instanceof p004io.grpc.internal.RetriableStream.StartEntry) == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0099, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009a, code lost:
        if (r3 == false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009c, code lost:
        r6 = r10.state;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a0, code lost:
        if (r6.winningSubstream == null) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a4, code lost:
        if (r6.winningSubstream == r11) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a9, code lost:
        if (r6.cancelled == false) goto L_0x0086;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain(p004io.grpc.internal.RetriableStream.Substream r11) {
        /*
            r10 = this;
            r0 = 0
            r1 = 128(0x80, float:1.794E-43)
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            java.lang.Object r5 = r10.lock
            monitor-enter(r5)
            io.grpc.internal.RetriableStream$State r6 = r10.state     // Catch:{ all -> 0x00af }
            if (r3 == 0) goto L_0x001d
            io.grpc.internal.RetriableStream$Substream r7 = r6.winningSubstream     // Catch:{ all -> 0x00af }
            if (r7 == 0) goto L_0x0017
            io.grpc.internal.RetriableStream$Substream r7 = r6.winningSubstream     // Catch:{ all -> 0x00af }
            if (r7 == r11) goto L_0x0017
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
            goto L_0x003a
        L_0x0017:
            boolean r7 = r6.cancelled     // Catch:{ all -> 0x00af }
            if (r7 == 0) goto L_0x001d
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
            goto L_0x003a
        L_0x001d:
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r7 = r6.buffer     // Catch:{ all -> 0x00af }
            int r7 = r7.size()     // Catch:{ all -> 0x00af }
            if (r0 != r7) goto L_0x0053
            io.grpc.internal.RetriableStream$State r7 = r6.substreamDrained(r11)     // Catch:{ all -> 0x00af }
            r10.state = r7     // Catch:{ all -> 0x00af }
            boolean r7 = r10.isReady()     // Catch:{ all -> 0x00af }
            if (r7 != 0) goto L_0x0033
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
            return
        L_0x0033:
            io.grpc.internal.RetriableStream$3 r7 = new io.grpc.internal.RetriableStream$3     // Catch:{ all -> 0x00af }
            r7.<init>()     // Catch:{ all -> 0x00af }
            r4 = r7
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
        L_0x003a:
            if (r4 == 0) goto L_0x0042
            java.util.concurrent.Executor r5 = r10.listenerSerializeExecutor
            r5.execute(r4)
            return
        L_0x0042:
            io.grpc.internal.ClientStream r5 = r11.stream
            io.grpc.internal.RetriableStream$State r6 = r10.state
            io.grpc.internal.RetriableStream$Substream r6 = r6.winningSubstream
            if (r6 != r11) goto L_0x004d
            io.grpc.Status r6 = r10.cancellationStatus
            goto L_0x004f
        L_0x004d:
            io.grpc.Status r6 = CANCELLED_BECAUSE_COMMITTED
        L_0x004f:
            r5.cancel(r6)
            return
        L_0x0053:
            boolean r7 = r11.closed     // Catch:{ all -> 0x00af }
            if (r7 == 0) goto L_0x0059
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
            return
        L_0x0059:
            int r7 = r0 + r1
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r8 = r6.buffer     // Catch:{ all -> 0x00af }
            int r8 = r8.size()     // Catch:{ all -> 0x00af }
            int r7 = java.lang.Math.min(r7, r8)     // Catch:{ all -> 0x00af }
            if (r2 != 0) goto L_0x0074
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x00af }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r9 = r6.buffer     // Catch:{ all -> 0x00af }
            java.util.List r9 = r9.subList(r0, r7)     // Catch:{ all -> 0x00af }
            r8.<init>(r9)     // Catch:{ all -> 0x00af }
            r2 = r8
            goto L_0x0080
        L_0x0074:
            r2.clear()     // Catch:{ all -> 0x00af }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r8 = r6.buffer     // Catch:{ all -> 0x00af }
            java.util.List r8 = r8.subList(r0, r7)     // Catch:{ all -> 0x00af }
            r2.addAll(r8)     // Catch:{ all -> 0x00af }
        L_0x0080:
            r0 = r7
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
            java.util.Iterator r5 = r2.iterator()
        L_0x0086:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x00ad
            java.lang.Object r7 = r5.next()
            io.grpc.internal.RetriableStream$BufferEntry r7 = (p004io.grpc.internal.RetriableStream.BufferEntry) r7
            r7.runWith(r11)
            boolean r8 = r7 instanceof p004io.grpc.internal.RetriableStream.StartEntry
            if (r8 == 0) goto L_0x009a
            r3 = 1
        L_0x009a:
            if (r3 == 0) goto L_0x00ac
            io.grpc.internal.RetriableStream$State r6 = r10.state
            io.grpc.internal.RetriableStream$Substream r8 = r6.winningSubstream
            if (r8 == 0) goto L_0x00a7
            io.grpc.internal.RetriableStream$Substream r8 = r6.winningSubstream
            if (r8 == r11) goto L_0x00a7
            goto L_0x00ad
        L_0x00a7:
            boolean r8 = r6.cancelled
            if (r8 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            goto L_0x0086
        L_0x00ad:
            goto L_0x0006
        L_0x00af:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00af }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.RetriableStream.drain(io.grpc.internal.RetriableStream$Substream):void");
    }

    /* renamed from: io.grpc.internal.RetriableStream$StartEntry */
    class StartEntry implements BufferEntry {
        StartEntry() {
        }

        public void runWith(Substream substream) {
            substream.stream.start(new Sublistener(substream));
        }
    }

    public final void start(ClientStreamListener listener) {
        Throttle throttle2;
        this.masterListener = listener;
        Status shutdownStatus = prestart();
        if (shutdownStatus != null) {
            cancel(shutdownStatus);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new StartEntry());
        }
        Substream substream = createSubstream(0, false);
        if (this.isHedging) {
            FutureCanceller scheduledHedgingRef = null;
            synchronized (this.lock) {
                this.state = this.state.addActiveHedge(substream);
                if (hasPotentialHedging(this.state) && ((throttle2 = this.throttle) == null || throttle2.isAboveThreshold())) {
                    FutureCanceller futureCanceller = new FutureCanceller(this.lock);
                    scheduledHedgingRef = futureCanceller;
                    this.scheduledHedging = futureCanceller;
                }
            }
            if (scheduledHedgingRef != null) {
                scheduledHedgingRef.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(scheduledHedgingRef), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
            }
        }
        drain(substream);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        if (r1 == null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        r1.cancel(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        r3.setFuture(r7.scheduledExecutorService.schedule(new p004io.grpc.internal.RetriableStream.HedgingRunnable(r7, r3), (long) r8.intValue(), java.util.concurrent.TimeUnit.MILLISECONDS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void pushbackHedging(@javax.annotation.Nullable java.lang.Integer r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r8.intValue()
            if (r0 >= 0) goto L_0x000d
            r7.freezeHedging()
            return
        L_0x000d:
            java.lang.Object r0 = r7.lock
            monitor-enter(r0)
            io.grpc.internal.RetriableStream$FutureCanceller r1 = r7.scheduledHedging     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0016:
            java.util.concurrent.Future r1 = r1.markCancelled()     // Catch:{ all -> 0x0041 }
            io.grpc.internal.RetriableStream$FutureCanceller r2 = new io.grpc.internal.RetriableStream$FutureCanceller     // Catch:{ all -> 0x0041 }
            java.lang.Object r3 = r7.lock     // Catch:{ all -> 0x0041 }
            r2.<init>(r3)     // Catch:{ all -> 0x0041 }
            r3 = r2
            r7.scheduledHedging = r2     // Catch:{ all -> 0x0041 }
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x002b
            r0 = 0
            r1.cancel(r0)
        L_0x002b:
            java.util.concurrent.ScheduledExecutorService r0 = r7.scheduledExecutorService
            io.grpc.internal.RetriableStream$HedgingRunnable r2 = new io.grpc.internal.RetriableStream$HedgingRunnable
            r2.<init>(r3)
            int r4 = r8.intValue()
            long r4 = (long) r4
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.ScheduledFuture r0 = r0.schedule(r2, r4, r6)
            r3.setFuture(r0)
            return
        L_0x0041:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.RetriableStream.pushbackHedging(java.lang.Integer):void");
    }

    /* renamed from: io.grpc.internal.RetriableStream$HedgingRunnable */
    private final class HedgingRunnable implements Runnable {
        final FutureCanceller scheduledHedgingRef;

        HedgingRunnable(FutureCanceller scheduledHedging) {
            this.scheduledHedgingRef = scheduledHedging;
        }

        public void run() {
            RetriableStream.this.callExecutor.execute(new Runnable() {
                public void run() {
                    Substream newSubstream = RetriableStream.this.createSubstream(RetriableStream.this.state.hedgingAttemptCount, false);
                    boolean cancelled = false;
                    FutureCanceller future = null;
                    synchronized (RetriableStream.this.lock) {
                        if (HedgingRunnable.this.scheduledHedgingRef.isCancelled()) {
                            cancelled = true;
                        } else {
                            State unused = RetriableStream.this.state = RetriableStream.this.state.addActiveHedge(newSubstream);
                            if (!RetriableStream.this.hasPotentialHedging(RetriableStream.this.state) || (RetriableStream.this.throttle != null && !RetriableStream.this.throttle.isAboveThreshold())) {
                                State unused2 = RetriableStream.this.state = RetriableStream.this.state.freezeHedging();
                                FutureCanceller unused3 = RetriableStream.this.scheduledHedging = null;
                            } else {
                                RetriableStream retriableStream = RetriableStream.this;
                                FutureCanceller futureCanceller = new FutureCanceller(RetriableStream.this.lock);
                                future = futureCanceller;
                                FutureCanceller unused4 = retriableStream.scheduledHedging = futureCanceller;
                            }
                        }
                    }
                    if (cancelled) {
                        newSubstream.stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                        return;
                    }
                    if (future != null) {
                        future.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new HedgingRunnable(future), RetriableStream.this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                    }
                    RetriableStream.this.drain(newSubstream);
                }
            });
        }
    }

    public final void cancel(final Status reason) {
        Substream noopSubstream = new Substream(0);
        noopSubstream.stream = new NoopClientStream();
        Runnable runnable = commit(noopSubstream);
        if (runnable != null) {
            runnable.run();
            this.listenerSerializeExecutor.execute(new Runnable() {
                public void run() {
                    boolean unused = RetriableStream.this.isClosed = true;
                    RetriableStream.this.masterListener.closed(reason, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
                }
            });
            return;
        }
        Substream winningSubstreamToCancel = null;
        synchronized (this.lock) {
            if (this.state.drainedSubstreams.contains(this.state.winningSubstream)) {
                winningSubstreamToCancel = this.state.winningSubstream;
            } else {
                this.cancellationStatus = reason;
            }
            this.state = this.state.cancelled();
        }
        if (winningSubstreamToCancel != null) {
            winningSubstreamToCancel.stream.cancel(reason);
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> savedDrainedSubstreams;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            savedDrainedSubstreams = this.state.drainedSubstreams;
        }
        for (Substream substream : savedDrainedSubstreams) {
            bufferEntry.runWith(substream);
        }
    }

    public final void writeMessage(InputStream message) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    /* access modifiers changed from: package-private */
    public final void sendMessage(final ReqT message) {
        State savedState = this.state;
        if (savedState.passThrough) {
            savedState.winningSubstream.stream.writeMessage(this.method.streamRequest(message));
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(RetriableStream.this.method.streamRequest(message));
                }
            });
        }
    }

    public final void request(final int numMessages) {
        State savedState = this.state;
        if (savedState.passThrough) {
            savedState.winningSubstream.stream.request(numMessages);
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.request(numMessages);
                }
            });
        }
    }

    public final void flush() {
        State savedState = this.state;
        if (savedState.passThrough) {
            savedState.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    public final boolean isReady() {
        for (Substream substream : this.state.drainedSubstreams) {
            if (substream.stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    public void optimizeForDirectExecutor() {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.optimizeForDirectExecutor();
            }
        });
    }

    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    public final void setFullStreamDecompression(final boolean fullStreamDecompression) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(fullStreamDecompression);
            }
        });
    }

    public final void setMessageCompression(final boolean enable) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(enable);
            }
        });
    }

    public final void halfClose() {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    public final void setAuthority(final String authority) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setAuthority(authority);
            }
        });
    }

    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    public final void setMaxInboundMessageSize(final int maxSize) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(maxSize);
            }
        });
    }

    public final void setMaxOutboundMessageSize(final int maxSize) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(maxSize);
            }
        });
    }

    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }

    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public void appendTimeoutInsight(InsightBuilder insight) {
        State currentState;
        synchronized (this.lock) {
            insight.appendKeyValue("closed", this.closedSubstreamsInsight);
            currentState = this.state;
        }
        if (currentState.winningSubstream != null) {
            InsightBuilder substreamInsight = new InsightBuilder();
            currentState.winningSubstream.stream.appendTimeoutInsight(substreamInsight);
            insight.appendKeyValue("committed", substreamInsight);
            return;
        }
        InsightBuilder openSubstreamsInsight = new InsightBuilder();
        for (Substream sub : currentState.drainedSubstreams) {
            InsightBuilder substreamInsight2 = new InsightBuilder();
            sub.stream.appendTimeoutInsight(substreamInsight2);
            openSubstreamsInsight.append(substreamInsight2);
        }
        insight.appendKeyValue("open", openSubstreamsInsight);
    }

    static void setRandom(Random random2) {
        random = random2;
    }

    /* access modifiers changed from: private */
    public boolean hasPotentialHedging(State state2) {
        return state2.winningSubstream == null && state2.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state2.hedgingFrozen;
    }

    /* access modifiers changed from: private */
    public void freezeHedging() {
        Future<?> futureToBeCancelled = null;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            if (futureCanceller != null) {
                futureToBeCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
            }
            this.state = this.state.freezeHedging();
        }
        if (futureToBeCancelled != null) {
            futureToBeCancelled.cancel(false);
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$Sublistener */
    private final class Sublistener implements ClientStreamListener {
        final Substream substream;

        Sublistener(Substream substream2) {
            this.substream = substream2;
        }

        public void headersRead(final Metadata headers) {
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() {
                    public void run() {
                        RetriableStream.this.masterListener.headersRead(headers);
                    }
                });
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0147, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void closed(final p004io.grpc.Status r8, final p004io.grpc.internal.ClientStreamListener.RpcProgress r9, final p004io.grpc.Metadata r10) {
            /*
                r7 = this;
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01aa }
                io.grpc.internal.RetriableStream$State r2 = r1.state     // Catch:{ all -> 0x01aa }
                io.grpc.internal.RetriableStream$Substream r3 = r7.substream     // Catch:{ all -> 0x01aa }
                io.grpc.internal.RetriableStream$State r2 = r2.substreamClosed(r3)     // Catch:{ all -> 0x01aa }
                p004io.grpc.internal.RetriableStream.State unused = r1.state = r2     // Catch:{ all -> 0x01aa }
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01aa }
                io.grpc.internal.InsightBuilder r1 = r1.closedSubstreamsInsight     // Catch:{ all -> 0x01aa }
                io.grpc.Status$Code r2 = r8.getCode()     // Catch:{ all -> 0x01aa }
                r1.append(r2)     // Catch:{ all -> 0x01aa }
                monitor-exit(r0)     // Catch:{ all -> 0x01aa }
                io.grpc.internal.RetriableStream$Substream r0 = r7.substream
                boolean r0 = r0.bufferLimitExceeded
                if (r0 == 0) goto L_0x004c
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r1 = r7.substream
                r0.commitAndRun(r1)
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                io.grpc.internal.RetriableStream$Substream r1 = r7.substream
                if (r0 != r1) goto L_0x004b
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                java.util.concurrent.Executor r0 = r0.listenerSerializeExecutor
                io.grpc.internal.RetriableStream$Sublistener$2 r1 = new io.grpc.internal.RetriableStream$Sublistener$2
                r1.<init>(r8, r9, r10)
                r0.execute(r1)
            L_0x004b:
                return
            L_0x004c:
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 != 0) goto L_0x0188
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = p004io.grpc.internal.ClientStreamListener.RpcProgress.REFUSED
                r1 = 1
                if (r9 != r0) goto L_0x00df
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.noMoreTransparentRetry
                r2 = 0
                boolean r0 = r0.compareAndSet(r2, r1)
                if (r0 == 0) goto L_0x00df
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r2 = r7.substream
                int r2 = r2.previousAttemptCount
                io.grpc.internal.RetriableStream$Substream r0 = r0.createSubstream(r2, r1)
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this
                boolean r2 = r2.isHedging
                if (r2 == 0) goto L_0x00b9
                r2 = 0
                io.grpc.internal.RetriableStream r3 = p004io.grpc.internal.RetriableStream.this
                java.lang.Object r3 = r3.lock
                monitor-enter(r3)
                io.grpc.internal.RetriableStream r4 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x00b6 }
                io.grpc.internal.RetriableStream$State r5 = r4.state     // Catch:{ all -> 0x00b6 }
                io.grpc.internal.RetriableStream$Substream r6 = r7.substream     // Catch:{ all -> 0x00b6 }
                io.grpc.internal.RetriableStream$State r5 = r5.replaceActiveHedge(r6, r0)     // Catch:{ all -> 0x00b6 }
                p004io.grpc.internal.RetriableStream.State unused = r4.state = r5     // Catch:{ all -> 0x00b6 }
                io.grpc.internal.RetriableStream r4 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x00b6 }
                io.grpc.internal.RetriableStream$State r5 = r4.state     // Catch:{ all -> 0x00b6 }
                boolean r4 = r4.hasPotentialHedging(r5)     // Catch:{ all -> 0x00b6 }
                if (r4 != 0) goto L_0x00ad
                io.grpc.internal.RetriableStream r4 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x00b6 }
                io.grpc.internal.RetriableStream$State r4 = r4.state     // Catch:{ all -> 0x00b6 }
                java.util.Collection<io.grpc.internal.RetriableStream$Substream> r4 = r4.activeHedges     // Catch:{ all -> 0x00b6 }
                int r4 = r4.size()     // Catch:{ all -> 0x00b6 }
                if (r4 != r1) goto L_0x00ad
                r1 = 1
                r2 = r1
            L_0x00ad:
                monitor-exit(r3)     // Catch:{ all -> 0x00b6 }
                if (r2 == 0) goto L_0x00b5
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                r1.commitAndRun(r0)
            L_0x00b5:
                goto L_0x00d0
            L_0x00b6:
                r1 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x00b6 }
                throw r1
            L_0x00b9:
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r2 = r2.retryPolicy
                if (r2 == 0) goto L_0x00cb
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetryPolicy r2 = r2.retryPolicy
                int r2 = r2.maxAttempts
                if (r2 != r1) goto L_0x00d0
            L_0x00cb:
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                r1.commitAndRun(r0)
            L_0x00d0:
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                java.util.concurrent.Executor r1 = r1.callExecutor
                io.grpc.internal.RetriableStream$Sublistener$3 r2 = new io.grpc.internal.RetriableStream$Sublistener$3
                r2.<init>(r0)
                r1.execute(r2)
                return
            L_0x00df:
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = p004io.grpc.internal.ClientStreamListener.RpcProgress.DROPPED
                if (r9 != r0) goto L_0x00f2
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                boolean r0 = r0.isHedging
                if (r0 == 0) goto L_0x0188
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                r0.freezeHedging()
                goto L_0x0188
            L_0x00f2:
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.noMoreTransparentRetry
                r0.set(r1)
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                boolean r0 = r0.isHedging
                if (r0 == 0) goto L_0x014d
                io.grpc.internal.RetriableStream$HedgingPlan r0 = r7.makeHedgingDecision(r8, r10)
                boolean r1 = r0.isHedgeable
                if (r1 == 0) goto L_0x0112
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                java.lang.Integer r2 = r0.hedgingPushbackMillis
                r1.pushbackHedging(r2)
            L_0x0112:
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x014a }
                io.grpc.internal.RetriableStream$State r3 = r2.state     // Catch:{ all -> 0x014a }
                io.grpc.internal.RetriableStream$Substream r4 = r7.substream     // Catch:{ all -> 0x014a }
                io.grpc.internal.RetriableStream$State r3 = r3.removeActiveHedge(r4)     // Catch:{ all -> 0x014a }
                p004io.grpc.internal.RetriableStream.State unused = r2.state = r3     // Catch:{ all -> 0x014a }
                boolean r2 = r0.isHedgeable     // Catch:{ all -> 0x014a }
                if (r2 == 0) goto L_0x0148
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x014a }
                io.grpc.internal.RetriableStream$State r3 = r2.state     // Catch:{ all -> 0x014a }
                boolean r2 = r2.hasPotentialHedging(r3)     // Catch:{ all -> 0x014a }
                if (r2 != 0) goto L_0x0146
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x014a }
                io.grpc.internal.RetriableStream$State r2 = r2.state     // Catch:{ all -> 0x014a }
                java.util.Collection<io.grpc.internal.RetriableStream$Substream> r2 = r2.activeHedges     // Catch:{ all -> 0x014a }
                boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x014a }
                if (r2 != 0) goto L_0x0148
            L_0x0146:
                monitor-exit(r1)     // Catch:{ all -> 0x014a }
                return
            L_0x0148:
                monitor-exit(r1)     // Catch:{ all -> 0x014a }
                goto L_0x0188
            L_0x014a:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x014a }
                throw r2
            L_0x014d:
                io.grpc.internal.RetriableStream$RetryPlan r0 = r7.makeRetryDecision(r8, r10)
                boolean r1 = r0.shouldRetry
                if (r1 == 0) goto L_0x0188
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0185 }
                io.grpc.internal.RetriableStream$FutureCanceller r3 = new io.grpc.internal.RetriableStream$FutureCanceller     // Catch:{ all -> 0x0185 }
                io.grpc.internal.RetriableStream r4 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0185 }
                java.lang.Object r4 = r4.lock     // Catch:{ all -> 0x0185 }
                r3.<init>(r4)     // Catch:{ all -> 0x0185 }
                r4 = r3
                p004io.grpc.internal.RetriableStream.FutureCanceller unused = r2.scheduledRetry = r3     // Catch:{ all -> 0x0185 }
                monitor-exit(r1)     // Catch:{ all -> 0x0185 }
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                java.util.concurrent.ScheduledExecutorService r1 = r1.scheduledExecutorService
                io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable r2 = new io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable
                r2.<init>()
                long r5 = r0.backoffNanos
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.NANOSECONDS
                java.util.concurrent.ScheduledFuture r1 = r1.schedule(r2, r5, r3)
                r4.setFuture(r1)
                return
            L_0x0185:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0185 }
                throw r2
            L_0x0188:
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r1 = r7.substream
                r0.commitAndRun(r1)
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                io.grpc.internal.RetriableStream$Substream r1 = r7.substream
                if (r0 != r1) goto L_0x01a9
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                java.util.concurrent.Executor r0 = r0.listenerSerializeExecutor
                io.grpc.internal.RetriableStream$Sublistener$4 r1 = new io.grpc.internal.RetriableStream$Sublistener$4
                r1.<init>(r8, r9, r10)
                r0.execute(r1)
            L_0x01a9:
                return
            L_0x01aa:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x01aa }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.RetriableStream.Sublistener.closed(io.grpc.Status, io.grpc.internal.ClientStreamListener$RpcProgress, io.grpc.Metadata):void");
        }

        private RetryPlan makeRetryDecision(Status status, Metadata trailer) {
            if (RetriableStream.this.retryPolicy == null) {
                return new RetryPlan(false, 0);
            }
            boolean shouldRetry = false;
            long backoffNanos = 0;
            boolean isRetryableStatusCode = RetriableStream.this.retryPolicy.retryableStatusCodes.contains(status.getCode());
            Integer pushbackMillis = getPushbackMills(trailer);
            boolean isThrottled = false;
            if (RetriableStream.this.throttle != null && (isRetryableStatusCode || (pushbackMillis != null && pushbackMillis.intValue() < 0))) {
                isThrottled = !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
            }
            if (RetriableStream.this.retryPolicy.maxAttempts > this.substream.previousAttemptCount + 1 && !isThrottled) {
                if (pushbackMillis == null) {
                    if (isRetryableStatusCode) {
                        shouldRetry = true;
                        backoffNanos = (long) (((double) RetriableStream.this.nextBackoffIntervalNanos) * RetriableStream.random.nextDouble());
                        RetriableStream retriableStream = RetriableStream.this;
                        long unused = retriableStream.nextBackoffIntervalNanos = Math.min((long) (((double) retriableStream.nextBackoffIntervalNanos) * RetriableStream.this.retryPolicy.backoffMultiplier), RetriableStream.this.retryPolicy.maxBackoffNanos);
                    }
                } else if (pushbackMillis.intValue() >= 0) {
                    shouldRetry = true;
                    backoffNanos = TimeUnit.MILLISECONDS.toNanos((long) pushbackMillis.intValue());
                    RetriableStream retriableStream2 = RetriableStream.this;
                    long unused2 = retriableStream2.nextBackoffIntervalNanos = retriableStream2.retryPolicy.initialBackoffNanos;
                }
            }
            return new RetryPlan(shouldRetry, backoffNanos);
        }

        private HedgingPlan makeHedgingDecision(Status status, Metadata trailer) {
            Integer pushbackMillis = getPushbackMills(trailer);
            boolean z = true;
            boolean isFatal = !RetriableStream.this.hedgingPolicy.nonFatalStatusCodes.contains(status.getCode());
            boolean isThrottled = false;
            if (RetriableStream.this.throttle != null && (!isFatal || (pushbackMillis != null && pushbackMillis.intValue() < 0))) {
                isThrottled = !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
            }
            if (isFatal || isThrottled) {
                z = false;
            }
            return new HedgingPlan(z, pushbackMillis);
        }

        @Nullable
        private Integer getPushbackMills(Metadata trailer) {
            String pushbackStr = (String) trailer.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (pushbackStr == null) {
                return null;
            }
            try {
                return Integer.valueOf(pushbackStr);
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            State savedState = RetriableStream.this.state;
            Preconditions.checkState(savedState.winningSubstream != null, "Headers should be received prior to messages.");
            if (savedState.winningSubstream == this.substream) {
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() {
                    public void run() {
                        RetriableStream.this.masterListener.messagesAvailable(producer);
                    }
                });
            }
        }

        public void onReady() {
            if (RetriableStream.this.isReady()) {
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() {
                    public void run() {
                        if (!RetriableStream.this.isClosed) {
                            RetriableStream.this.masterListener.onReady();
                        }
                    }
                });
            }
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$State */
    private static final class State {
        final Collection<Substream> activeHedges;
        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;
        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> buffer2, Collection<Substream> drainedSubstreams2, Collection<Substream> activeHedges2, @Nullable Substream winningSubstream2, boolean cancelled2, boolean passThrough2, boolean hedgingFrozen2, int hedgingAttemptCount2) {
            this.buffer = buffer2;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(drainedSubstreams2, "drainedSubstreams");
            this.winningSubstream = winningSubstream2;
            this.activeHedges = activeHedges2;
            this.cancelled = cancelled2;
            this.passThrough = passThrough2;
            this.hedgingFrozen = hedgingFrozen2;
            this.hedgingAttemptCount = hedgingAttemptCount2;
            boolean z = false;
            Preconditions.checkState(!passThrough2 || buffer2 == null, "passThrough should imply buffer is null");
            Preconditions.checkState(!passThrough2 || winningSubstream2 != null, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!passThrough2 || (drainedSubstreams2.size() == 1 && drainedSubstreams2.contains(winningSubstream2)) || (drainedSubstreams2.size() == 0 && winningSubstream2.closed), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((!cancelled2 || winningSubstream2 != null) ? true : z, "cancelled should imply committed");
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, true, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State substreamDrained(Substream substream) {
            Collection<Substream> drainedSubstreams2;
            List<BufferEntry> buffer2;
            boolean z = true;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                drainedSubstreams2 = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                drainedSubstreams2 = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                drainedSubstreams2 = Collections.unmodifiableCollection(arrayList);
            }
            Substream substream2 = this.winningSubstream;
            boolean passThrough2 = substream2 != null;
            List<BufferEntry> buffer3 = this.buffer;
            if (passThrough2) {
                if (substream2 != substream) {
                    z = false;
                }
                Preconditions.checkState(z, "Another RPC attempt has already committed");
                buffer2 = null;
            } else {
                buffer2 = buffer3;
            }
            return new State(buffer2, drainedSubstreams2, this.activeHedges, this.winningSubstream, this.cancelled, passThrough2, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State substreamClosed(Substream substream) {
            substream.closed = true;
            if (!this.drainedSubstreams.contains(substream)) {
                return this;
            }
            Collection<Substream> drainedSubstreams2 = new ArrayList<>(this.drainedSubstreams);
            drainedSubstreams2.remove(substream);
            return new State(this.buffer, Collections.unmodifiableCollection(drainedSubstreams2), this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State committed(Substream winningSubstream2) {
            Collection<Substream> drainedSubstreams2;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            boolean passThrough2 = false;
            List<BufferEntry> buffer2 = this.buffer;
            if (this.drainedSubstreams.contains(winningSubstream2)) {
                passThrough2 = true;
                buffer2 = null;
                drainedSubstreams2 = Collections.singleton(winningSubstream2);
            } else {
                drainedSubstreams2 = Collections.emptyList();
            }
            return new State(buffer2, drainedSubstreams2, this.activeHedges, winningSubstream2, this.cancelled, passThrough2, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State freezeHedging() {
            if (this.hedgingFrozen) {
                return this;
            }
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State addActiveHedge(Substream substream) {
            Collection<Substream> activeHedges2;
            Preconditions.checkState(!this.hedgingFrozen, "hedging frozen");
            Preconditions.checkState(this.winningSubstream == null, "already committed");
            if (this.activeHedges == null) {
                activeHedges2 = Collections.singleton(substream);
            } else {
                Collection<Substream> activeHedges3 = new ArrayList<>(this.activeHedges);
                activeHedges3.add(substream);
                activeHedges2 = Collections.unmodifiableCollection(activeHedges3);
            }
            return new State(this.buffer, this.drainedSubstreams, activeHedges2, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, 1 + this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State removeActiveHedge(Substream substream) {
            Collection<Substream> activeHedges2 = new ArrayList<>(this.activeHedges);
            activeHedges2.remove(substream);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(activeHedges2), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State replaceActiveHedge(Substream oldOne, Substream newOne) {
            Collection<Substream> activeHedges2 = new ArrayList<>(this.activeHedges);
            activeHedges2.remove(oldOne);
            activeHedges2.add(newOne);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(activeHedges2), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$Substream */
    private static final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttemptCount;
        ClientStream stream;

        Substream(int previousAttemptCount2) {
            this.previousAttemptCount = previousAttemptCount2;
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$BufferSizeTracer */
    class BufferSizeTracer extends ClientStreamTracer {
        long bufferNeeded;
        private final Substream substream;

        BufferSizeTracer(Substream substream2) {
            this.substream = substream2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x007f, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0081, code lost:
            r0.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void outboundWireSize(long r9) {
            /*
                r8 = this;
                io.grpc.internal.RetriableStream r0 = p004io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                r0 = 0
                io.grpc.internal.RetriableStream r1 = p004io.grpc.internal.RetriableStream.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$State r2 = r2.state     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$Substream r2 = r2.winningSubstream     // Catch:{ all -> 0x0087 }
                if (r2 != 0) goto L_0x0085
                io.grpc.internal.RetriableStream$Substream r2 = r8.substream     // Catch:{ all -> 0x0087 }
                boolean r2 = r2.closed     // Catch:{ all -> 0x0087 }
                if (r2 == 0) goto L_0x0024
                goto L_0x0085
            L_0x0024:
                long r2 = r8.bufferNeeded     // Catch:{ all -> 0x0087 }
                long r2 = r2 + r9
                r8.bufferNeeded = r2     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r4 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r4 = r4.perRpcBufferUsed     // Catch:{ all -> 0x0087 }
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 > 0) goto L_0x0035
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                return
            L_0x0035:
                long r2 = r8.bufferNeeded     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r4 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r4 = r4.perRpcBufferLimit     // Catch:{ all -> 0x0087 }
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                r3 = 1
                if (r2 <= 0) goto L_0x0047
                io.grpc.internal.RetriableStream$Substream r2 = r8.substream     // Catch:{ all -> 0x0087 }
                r2.bufferLimitExceeded = r3     // Catch:{ all -> 0x0087 }
                goto L_0x006f
            L_0x0047:
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$ChannelBufferMeter r2 = r2.channelBufferUsed     // Catch:{ all -> 0x0087 }
                long r4 = r8.bufferNeeded     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r6 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r6 = r6.perRpcBufferUsed     // Catch:{ all -> 0x0087 }
                long r4 = r4 - r6
                long r4 = r2.addAndGet(r4)     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r6 = r8.bufferNeeded     // Catch:{ all -> 0x0087 }
                long unused = r2.perRpcBufferUsed = r6     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r6 = r2.channelBufferLimit     // Catch:{ all -> 0x0087 }
                int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r2 <= 0) goto L_0x006f
                io.grpc.internal.RetriableStream$Substream r2 = r8.substream     // Catch:{ all -> 0x0087 }
                r2.bufferLimitExceeded = r3     // Catch:{ all -> 0x0087 }
            L_0x006f:
                io.grpc.internal.RetriableStream$Substream r2 = r8.substream     // Catch:{ all -> 0x0087 }
                boolean r2 = r2.bufferLimitExceeded     // Catch:{ all -> 0x0087 }
                if (r2 == 0) goto L_0x007e
                io.grpc.internal.RetriableStream r2 = p004io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$Substream r3 = r8.substream     // Catch:{ all -> 0x0087 }
                java.lang.Runnable r2 = r2.commit(r3)     // Catch:{ all -> 0x0087 }
                r0 = r2
            L_0x007e:
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                if (r0 == 0) goto L_0x0084
                r0.run()
            L_0x0084:
                return
            L_0x0085:
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                return
            L_0x0087:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0087 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.RetriableStream.BufferSizeTracer.outboundWireSize(long):void");
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$ChannelBufferMeter */
    static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter() {
        }

        /* access modifiers changed from: package-private */
        public long addAndGet(long newBytesUsed) {
            return this.bufferUsed.addAndGet(newBytesUsed);
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$Throttle */
    static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        Throttle(float maxTokens2, float tokenRatio2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (tokenRatio2 * 1000.0f);
            int i = (int) (1000.0f * maxTokens2);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        /* access modifiers changed from: package-private */
        public boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        /* access modifiers changed from: package-private */
        public boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int currentCount;
            int decremented;
            do {
                currentCount = this.tokenCount.get();
                if (currentCount == 0) {
                    return false;
                }
                decremented = currentCount + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
            } while (!this.tokenCount.compareAndSet(currentCount, Math.max(decremented, 0)));
            if (decremented > this.threshold) {
                return true;
            }
            return false;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        void onSuccess() {
            /*
                r4 = this;
            L_0x0000:
                java.util.concurrent.atomic.AtomicInteger r0 = r4.tokenCount
                int r0 = r0.get()
                int r1 = r4.maxTokens
                if (r0 != r1) goto L_0x000b
                goto L_0x001b
            L_0x000b:
                int r2 = r4.tokenRatio
                int r2 = r2 + r0
                java.util.concurrent.atomic.AtomicInteger r3 = r4.tokenCount
                int r1 = java.lang.Math.min(r2, r1)
                boolean r1 = r3.compareAndSet(r0, r1)
                if (r1 == 0) goto L_0x001c
            L_0x001b:
                return
            L_0x001c:
                goto L_0x0000
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.RetriableStream.Throttle.onSuccess():void");
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Throttle)) {
                return false;
            }
            Throttle that = (Throttle) o;
            if (this.maxTokens == that.maxTokens && this.tokenRatio == that.tokenRatio) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$RetryPlan */
    private static final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        RetryPlan(boolean shouldRetry2, long backoffNanos2) {
            this.shouldRetry = shouldRetry2;
            this.backoffNanos = backoffNanos2;
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$HedgingPlan */
    private static final class HedgingPlan {
        @Nullable
        final Integer hedgingPushbackMillis;
        final boolean isHedgeable;

        public HedgingPlan(boolean isHedgeable2, @Nullable Integer hedgingPushbackMillis2) {
            this.isHedgeable = isHedgeable2;
            this.hedgingPushbackMillis = hedgingPushbackMillis2;
        }
    }

    /* renamed from: io.grpc.internal.RetriableStream$FutureCanceller */
    private static final class FutureCanceller {
        boolean cancelled;
        Future<?> future;
        final Object lock;

        FutureCanceller(Object lock2) {
            this.lock = lock2;
        }

        /* access modifiers changed from: package-private */
        public void setFuture(Future<?> future2) {
            synchronized (this.lock) {
                if (!this.cancelled) {
                    this.future = future2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public Future<?> markCancelled() {
            this.cancelled = true;
            return this.future;
        }

        /* access modifiers changed from: package-private */
        public boolean isCancelled() {
            return this.cancelled;
        }
    }
}
