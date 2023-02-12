package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientCall;
import p004io.grpc.Codec;
import p004io.grpc.Compressor;
import p004io.grpc.CompressorRegistry;
import p004io.grpc.Context;
import p004io.grpc.Contexts;
import p004io.grpc.Deadline;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.InternalConfigSelector;
import p004io.grpc.InternalDecompressorRegistry;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.ManagedChannelServiceConfig;
import p004io.grpc.internal.StreamListener;
import p004io.perfmark.Link;
import p004io.perfmark.PerfMark;
import p004io.perfmark.Tag;

/* renamed from: io.grpc.internal.ClientCallImpl */
final class ClientCallImpl<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    private static final byte[] FULL_STREAM_DECOMPRESSION_ENCODINGS = "gzip".getBytes(StandardCharsets.US_ASCII);
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    private final boolean callExecutorIsDirect;
    private CallOptions callOptions;
    private boolean cancelCalled;
    /* access modifiers changed from: private */
    public volatile boolean cancelListenersShouldBeRemoved;
    private final ClientCallImpl<ReqT, RespT>.ContextCancellationListener cancellationListener = new ContextCancellationListener();
    /* access modifiers changed from: private */
    public final CallTracer channelCallsTracer;
    private final ClientStreamProvider clientStreamProvider;
    private CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();
    /* access modifiers changed from: private */
    public final Context context;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private volatile ScheduledFuture<?> deadlineCancellationFuture;
    private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
    private boolean fullStreamDecompression;
    private boolean halfCloseCalled;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    /* access modifiers changed from: private */
    public ClientStream stream;
    /* access modifiers changed from: private */
    public final Tag tag;
    private final boolean unaryRequest;

    /* renamed from: io.grpc.internal.ClientCallImpl$ClientStreamProvider */
    interface ClientStreamProvider {
        ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context);
    }

    ClientCallImpl(MethodDescriptor<ReqT, RespT> method2, Executor executor, CallOptions callOptions2, ClientStreamProvider clientStreamProvider2, ScheduledExecutorService deadlineCancellationExecutor2, CallTracer channelCallsTracer2, @Nullable InternalConfigSelector configSelector) {
        this.method = method2;
        Tag createTag = PerfMark.createTag(method2.getFullMethodName(), (long) System.identityHashCode(this));
        this.tag = createTag;
        boolean z = true;
        if (executor == MoreExecutors.directExecutor()) {
            this.callExecutor = new SerializeReentrantCallsDirectExecutor();
            this.callExecutorIsDirect = true;
        } else {
            this.callExecutor = new SerializingExecutor(executor);
            this.callExecutorIsDirect = false;
        }
        this.channelCallsTracer = channelCallsTracer2;
        this.context = Context.current();
        if (!(method2.getType() == MethodDescriptor.MethodType.UNARY || method2.getType() == MethodDescriptor.MethodType.SERVER_STREAMING)) {
            z = false;
        }
        this.unaryRequest = z;
        this.callOptions = callOptions2;
        this.clientStreamProvider = clientStreamProvider2;
        this.deadlineCancellationExecutor = deadlineCancellationExecutor2;
        PerfMark.event("ClientCall.<init>", createTag);
    }

    /* renamed from: io.grpc.internal.ClientCallImpl$ContextCancellationListener */
    private final class ContextCancellationListener implements Context.CancellationListener {
        private ContextCancellationListener() {
        }

        public void cancelled(Context context) {
            ClientCallImpl.this.stream.cancel(Contexts.statusFromCancelled(context));
        }
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setFullStreamDecompression(boolean fullStreamDecompression2) {
        this.fullStreamDecompression = fullStreamDecompression2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setDecompressorRegistry(DecompressorRegistry decompressorRegistry2) {
        this.decompressorRegistry = decompressorRegistry2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setCompressorRegistry(CompressorRegistry compressorRegistry2) {
        this.compressorRegistry = compressorRegistry2;
        return this;
    }

    static void prepareHeaders(Metadata headers, DecompressorRegistry decompressorRegistry2, Compressor compressor, boolean fullStreamDecompression2) {
        headers.discardAll(GrpcUtil.CONTENT_LENGTH_KEY);
        headers.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (compressor != Codec.Identity.NONE) {
            headers.put(GrpcUtil.MESSAGE_ENCODING_KEY, compressor.getMessageEncoding());
        }
        headers.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] advertisedEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(decompressorRegistry2);
        if (advertisedEncodings.length != 0) {
            headers.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, advertisedEncodings);
        }
        headers.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        headers.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (fullStreamDecompression2) {
            headers.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
    }

    public void start(ClientCall.Listener<RespT> observer, Metadata headers) {
        PerfMark.startTask("ClientCall.start", this.tag);
        try {
            startInternal(observer, headers);
        } finally {
            PerfMark.stopTask("ClientCall.start", this.tag);
        }
    }

    private void startInternal(ClientCall.Listener<RespT> observer, Metadata headers) {
        Compressor compressor;
        boolean deadlineExceeded = true;
        Preconditions.checkState(this.stream == null, "Already started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkNotNull(observer, "observer");
        Preconditions.checkNotNull(headers, "headers");
        if (this.context.isCancelled()) {
            this.stream = NoopClientStream.INSTANCE;
            final ClientCall.Listener<RespT> finalObserver = observer;
            this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    ClientCallImpl clientCallImpl = ClientCallImpl.this;
                    clientCallImpl.closeObserver(finalObserver, Contexts.statusFromCancelled(clientCallImpl.context), new Metadata());
                }
            });
            return;
        }
        applyMethodConfig();
        final String compressorName = this.callOptions.getCompressor();
        if (compressorName != null) {
            compressor = this.compressorRegistry.lookupCompressor(compressorName);
            if (compressor == null) {
                this.stream = NoopClientStream.INSTANCE;
                final ClientCall.Listener<RespT> finalObserver2 = observer;
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        ClientCallImpl.this.closeObserver(finalObserver2, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", new Object[]{compressorName})), new Metadata());
                    }
                });
                return;
            }
        } else {
            compressor = Codec.Identity.NONE;
        }
        prepareHeaders(headers, this.decompressorRegistry, compressor, this.fullStreamDecompression);
        Deadline effectiveDeadline = effectiveDeadline();
        if (effectiveDeadline == null || !effectiveDeadline.isExpired()) {
            deadlineExceeded = false;
        }
        if (!deadlineExceeded) {
            logIfContextNarrowedTimeout(effectiveDeadline, this.context.getDeadline(), this.callOptions.getDeadline());
            this.stream = this.clientStreamProvider.newStream(this.method, this.callOptions, headers, this.context);
        } else {
            this.stream = new FailingClientStream(Status.DEADLINE_EXCEEDED.withDescription("ClientCall started after deadline exceeded: " + effectiveDeadline), GrpcUtil.getClientStreamTracers(this.callOptions, headers, 0, false));
        }
        if (this.callExecutorIsDirect) {
            this.stream.optimizeForDirectExecutor();
        }
        if (this.callOptions.getAuthority() != null) {
            this.stream.setAuthority(this.callOptions.getAuthority());
        }
        if (this.callOptions.getMaxInboundMessageSize() != null) {
            this.stream.setMaxInboundMessageSize(this.callOptions.getMaxInboundMessageSize().intValue());
        }
        if (this.callOptions.getMaxOutboundMessageSize() != null) {
            this.stream.setMaxOutboundMessageSize(this.callOptions.getMaxOutboundMessageSize().intValue());
        }
        if (effectiveDeadline != null) {
            this.stream.setDeadline(effectiveDeadline);
        }
        this.stream.setCompressor(compressor);
        boolean z = this.fullStreamDecompression;
        if (z) {
            this.stream.setFullStreamDecompression(z);
        }
        this.stream.setDecompressorRegistry(this.decompressorRegistry);
        this.channelCallsTracer.reportCallStarted();
        this.stream.start(new ClientStreamListenerImpl(observer));
        this.context.addListener(this.cancellationListener, MoreExecutors.directExecutor());
        if (!(effectiveDeadline == null || effectiveDeadline.equals(this.context.getDeadline()) || this.deadlineCancellationExecutor == null)) {
            this.deadlineCancellationFuture = startDeadlineTimer(effectiveDeadline);
        }
        if (this.cancelListenersShouldBeRemoved) {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    private void applyMethodConfig() {
        ManagedChannelServiceConfig.MethodInfo info = (ManagedChannelServiceConfig.MethodInfo) this.callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
        if (info != null) {
            if (info.timeoutNanos != null) {
                Deadline newDeadline = Deadline.after(info.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
                Deadline existingDeadline = this.callOptions.getDeadline();
                if (existingDeadline == null || newDeadline.compareTo(existingDeadline) < 0) {
                    this.callOptions = this.callOptions.withDeadline(newDeadline);
                }
            }
            if (info.waitForReady != null) {
                this.callOptions = info.waitForReady.booleanValue() ? this.callOptions.withWaitForReady() : this.callOptions.withoutWaitForReady();
            }
            if (info.maxInboundMessageSize != null) {
                Integer existingLimit = this.callOptions.getMaxInboundMessageSize();
                if (existingLimit != null) {
                    this.callOptions = this.callOptions.withMaxInboundMessageSize(Math.min(existingLimit.intValue(), info.maxInboundMessageSize.intValue()));
                } else {
                    this.callOptions = this.callOptions.withMaxInboundMessageSize(info.maxInboundMessageSize.intValue());
                }
            }
            if (info.maxOutboundMessageSize != null) {
                Integer existingLimit2 = this.callOptions.getMaxOutboundMessageSize();
                if (existingLimit2 != null) {
                    this.callOptions = this.callOptions.withMaxOutboundMessageSize(Math.min(existingLimit2.intValue(), info.maxOutboundMessageSize.intValue()));
                } else {
                    this.callOptions = this.callOptions.withMaxOutboundMessageSize(info.maxOutboundMessageSize.intValue());
                }
            }
        }
    }

    private static void logIfContextNarrowedTimeout(Deadline effectiveDeadline, @Nullable Deadline outerCallDeadline, @Nullable Deadline callDeadline) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE) && effectiveDeadline != null && effectiveDeadline.equals(outerCallDeadline)) {
            StringBuilder builder = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", new Object[]{Long.valueOf(Math.max(0, effectiveDeadline.timeRemaining(TimeUnit.NANOSECONDS)))}));
            if (callDeadline == null) {
                builder.append(" Explicit call timeout was not set.");
            } else {
                builder.append(String.format(" Explicit call timeout was '%d' ns.", new Object[]{Long.valueOf(callDeadline.timeRemaining(TimeUnit.NANOSECONDS))}));
            }
            logger.fine(builder.toString());
        }
    }

    /* access modifiers changed from: private */
    public void removeContextListenerAndCancelDeadlineFuture() {
        this.context.removeListener(this.cancellationListener);
        ScheduledFuture<?> f = this.deadlineCancellationFuture;
        if (f != null) {
            f.cancel(false);
        }
    }

    /* renamed from: io.grpc.internal.ClientCallImpl$DeadlineTimer */
    private class DeadlineTimer implements Runnable {
        private final long remainingNanos;

        DeadlineTimer(long remainingNanos2) {
            this.remainingNanos = remainingNanos2;
        }

        public void run() {
            InsightBuilder insight = new InsightBuilder();
            ClientCallImpl.this.stream.appendTimeoutInsight(insight);
            long seconds = Math.abs(this.remainingNanos) / TimeUnit.SECONDS.toNanos(1);
            long nanos = Math.abs(this.remainingNanos) % TimeUnit.SECONDS.toNanos(1);
            StringBuilder buf = new StringBuilder();
            buf.append("deadline exceeded after ");
            if (this.remainingNanos < 0) {
                buf.append('-');
            }
            buf.append(seconds);
            buf.append(String.format(Locale.US, ".%09d", new Object[]{Long.valueOf(nanos)}));
            buf.append("s. ");
            buf.append(insight);
            ClientCallImpl.this.stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(buf.toString()));
        }
    }

    private ScheduledFuture<?> startDeadlineTimer(Deadline deadline) {
        long remainingNanos = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        return this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new DeadlineTimer(remainingNanos)), remainingNanos, TimeUnit.NANOSECONDS);
    }

    /* access modifiers changed from: private */
    @Nullable
    public Deadline effectiveDeadline() {
        return min(this.callOptions.getDeadline(), this.context.getDeadline());
    }

    @Nullable
    private static Deadline min(@Nullable Deadline deadline0, @Nullable Deadline deadline1) {
        if (deadline0 == null) {
            return deadline1;
        }
        if (deadline1 == null) {
            return deadline0;
        }
        return deadline0.minimum(deadline1);
    }

    public void request(int numMessages) {
        PerfMark.startTask("ClientCall.request", this.tag);
        try {
            boolean z = true;
            Preconditions.checkState(this.stream != null, "Not started");
            if (numMessages < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "Number requested must be non-negative");
            this.stream.request(numMessages);
        } finally {
            PerfMark.stopTask("ClientCall.request", this.tag);
        }
    }

    public void cancel(@Nullable String message, @Nullable Throwable cause) {
        PerfMark.startTask("ClientCall.cancel", this.tag);
        try {
            cancelInternal(message, cause);
        } finally {
            PerfMark.stopTask("ClientCall.cancel", this.tag);
        }
    }

    private void cancelInternal(@Nullable String message, @Nullable Throwable cause) {
        Status status;
        if (message == null && cause == null) {
            cause = new CancellationException("Cancelled without a message or cause");
            log.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", cause);
        }
        if (!this.cancelCalled) {
            this.cancelCalled = true;
            try {
                if (this.stream != null) {
                    Status status2 = Status.CANCELLED;
                    if (message != null) {
                        status = status2.withDescription(message);
                    } else {
                        status = status2.withDescription("Call cancelled without message");
                    }
                    if (cause != null) {
                        status = status.withCause(cause);
                    }
                    this.stream.cancel(status);
                }
            } finally {
                removeContextListenerAndCancelDeadlineFuture();
            }
        }
    }

    public void halfClose() {
        PerfMark.startTask("ClientCall.halfClose", this.tag);
        try {
            halfCloseInternal();
        } finally {
            PerfMark.stopTask("ClientCall.halfClose", this.tag);
        }
    }

    private void halfCloseInternal() {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    public void sendMessage(ReqT message) {
        PerfMark.startTask("ClientCall.sendMessage", this.tag);
        try {
            sendMessageInternal(message);
        } finally {
            PerfMark.stopTask("ClientCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(ReqT message) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            ClientStream clientStream = this.stream;
            if (clientStream instanceof RetriableStream) {
                ((RetriableStream) clientStream).sendMessage(message);
            } else {
                clientStream.writeMessage(this.method.streamRequest(message));
            }
            if (!this.unaryRequest) {
                this.stream.flush();
            }
        } catch (RuntimeException e) {
            this.stream.cancel(Status.CANCELLED.withCause(e).withDescription("Failed to stream message"));
        } catch (Error e2) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e2;
        }
    }

    public void setMessageCompression(boolean enabled) {
        Preconditions.checkState(this.stream != null, "Not started");
        this.stream.setMessageCompression(enabled);
    }

    public boolean isReady() {
        if (this.halfCloseCalled) {
            return false;
        }
        return this.stream.isReady();
    }

    public Attributes getAttributes() {
        ClientStream clientStream = this.stream;
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    /* access modifiers changed from: private */
    public void closeObserver(ClientCall.Listener<RespT> observer, Status status, Metadata trailers) {
        observer.onClose(status, trailers);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add(FirebaseAnalytics.Param.METHOD, (Object) this.method).toString();
    }

    /* renamed from: io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl */
    private class ClientStreamListenerImpl implements ClientStreamListener {
        /* access modifiers changed from: private */
        public Status exceptionStatus;
        /* access modifiers changed from: private */
        public final ClientCall.Listener<RespT> observer;

        public ClientStreamListenerImpl(ClientCall.Listener<RespT> observer2) {
            this.observer = (ClientCall.Listener) Preconditions.checkNotNull(observer2, "observer");
        }

        /* access modifiers changed from: private */
        public void exceptionThrown(Status status) {
            this.exceptionStatus = status;
            ClientCallImpl.this.stream.cancel(status);
        }

        public void headersRead(final Metadata headers) {
            PerfMark.startTask("ClientStreamListener.headersRead", ClientCallImpl.this.tag);
            final Link link = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.headersRead", ClientCallImpl.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.headersRead", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus == null) {
                            try {
                                ClientStreamListenerImpl.this.observer.onHeaders(headers);
                            } catch (Throwable t) {
                                ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(t).withDescription("Failed to read headers"));
                            }
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.headersRead", ClientCallImpl.this.tag);
            }
        }

        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            PerfMark.startTask("ClientStreamListener.messagesAvailable", ClientCallImpl.this.tag);
            final Link link = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.messagesAvailable", ClientCallImpl.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.messagesAvailable", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        InputStream message;
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            GrpcUtil.closeQuietly(producer);
                            return;
                        }
                        while (true) {
                            try {
                                InputStream next = producer.next();
                                message = next;
                                if (next != null) {
                                    ClientStreamListenerImpl.this.observer.onMessage(ClientCallImpl.this.method.parseResponse(message));
                                    message.close();
                                } else {
                                    return;
                                }
                            } catch (Throwable t) {
                                GrpcUtil.closeQuietly(producer);
                                ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(t).withDescription("Failed to read message."));
                                return;
                            }
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.messagesAvailable", ClientCallImpl.this.tag);
            }
        }

        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
            PerfMark.startTask("ClientStreamListener.closed", ClientCallImpl.this.tag);
            try {
                closedInternal(status, rpcProgress, trailers);
            } finally {
                PerfMark.stopTask("ClientStreamListener.closed", ClientCallImpl.this.tag);
            }
        }

        private void closedInternal(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
            Deadline deadline = ClientCallImpl.this.effectiveDeadline();
            if (status.getCode() == Status.Code.CANCELLED && deadline != null && deadline.isExpired()) {
                InsightBuilder insight = new InsightBuilder();
                ClientCallImpl.this.stream.appendTimeoutInsight(insight);
                status = Status.DEADLINE_EXCEEDED.augmentDescription("ClientCall was cancelled at or after deadline. " + insight);
                trailers = new Metadata();
            }
            final Status savedStatus = status;
            final Metadata savedTrailers = trailers;
            final Link link = PerfMark.linkOut();
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    PerfMark.startTask("ClientCall$Listener.onClose", ClientCallImpl.this.tag);
                    PerfMark.linkIn(link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ClientCall$Listener.onClose", ClientCallImpl.this.tag);
                    }
                }

                private void runInternal() {
                    Status status = savedStatus;
                    Metadata trailers = savedTrailers;
                    if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                        status = ClientStreamListenerImpl.this.exceptionStatus;
                        trailers = new Metadata();
                    }
                    boolean unused = ClientCallImpl.this.cancelListenersShouldBeRemoved = true;
                    try {
                        ClientCallImpl.this.closeObserver(ClientStreamListenerImpl.this.observer, status, trailers);
                    } finally {
                        ClientCallImpl.this.removeContextListenerAndCancelDeadlineFuture();
                        ClientCallImpl.this.channelCallsTracer.reportCallEnded(status.isOk());
                    }
                }
            });
        }

        public void onReady() {
            if (!ClientCallImpl.this.method.getType().clientSendsOneMessage()) {
                PerfMark.startTask("ClientStreamListener.onReady", ClientCallImpl.this.tag);
                final Link link = PerfMark.linkOut();
                try {
                    ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                        public void runInContext() {
                            PerfMark.startTask("ClientCall$Listener.onReady", ClientCallImpl.this.tag);
                            PerfMark.linkIn(link);
                            try {
                                runInternal();
                            } finally {
                                PerfMark.stopTask("ClientCall$Listener.onReady", ClientCallImpl.this.tag);
                            }
                        }

                        private void runInternal() {
                            if (ClientStreamListenerImpl.this.exceptionStatus == null) {
                                try {
                                    ClientStreamListenerImpl.this.observer.onReady();
                                } catch (Throwable t) {
                                    ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(t).withDescription("Failed to call onReady."));
                                }
                            }
                        }
                    });
                } finally {
                    PerfMark.stopTask("ClientStreamListener.onReady", ClientCallImpl.this.tag);
                }
            }
        }
    }
}
