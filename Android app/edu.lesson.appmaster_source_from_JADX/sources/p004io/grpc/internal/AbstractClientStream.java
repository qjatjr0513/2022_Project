package p004io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.p000io.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import p004io.grpc.CallOptions;
import p004io.grpc.Codec;
import p004io.grpc.Compressor;
import p004io.grpc.Deadline;
import p004io.grpc.Decompressor;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.Grpc;
import p004io.grpc.Metadata;
import p004io.grpc.Status;
import p004io.grpc.internal.AbstractStream;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.MessageFramer;

/* renamed from: io.grpc.internal.AbstractClientStream */
public abstract class AbstractClientStream extends AbstractStream implements ClientStream, MessageFramer.Sink {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(AbstractClientStream.class.getName());
    private volatile boolean cancelled;
    private final Framer framer;
    private Metadata headers;
    private boolean shouldBeCountedForInUse;
    private final TransportTracer transportTracer;
    private boolean useGet;

    /* renamed from: io.grpc.internal.AbstractClientStream$Sink */
    protected interface Sink {
        void cancel(Status status);

        void writeFrame(@Nullable WritableBuffer writableBuffer, boolean z, boolean z2, int i);

        void writeHeaders(Metadata metadata, @Nullable byte[] bArr);
    }

    /* access modifiers changed from: protected */
    public abstract Sink abstractClientStreamSink();

    /* access modifiers changed from: protected */
    public abstract TransportState transportState();

    protected AbstractClientStream(WritableBufferAllocator bufferAllocator, StatsTraceContext statsTraceCtx, TransportTracer transportTracer2, Metadata headers2, CallOptions callOptions, boolean useGet2) {
        Preconditions.checkNotNull(headers2, "headers");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
        this.shouldBeCountedForInUse = GrpcUtil.shouldBeCountedForInUse(callOptions);
        this.useGet = useGet2;
        if (!useGet2) {
            this.framer = new MessageFramer(this, bufferAllocator, statsTraceCtx);
            this.headers = headers2;
            return;
        }
        this.framer = new GetFramer(headers2, statsTraceCtx);
    }

    public void setDeadline(Deadline deadline) {
        this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
        this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
    }

    public void setMaxOutboundMessageSize(int maxSize) {
        this.framer.setMaxOutboundMessageSize(maxSize);
    }

    public void setMaxInboundMessageSize(int maxSize) {
        transportState().setMaxInboundMessageSize(maxSize);
    }

    public final void setFullStreamDecompression(boolean fullStreamDecompression) {
        transportState().setFullStreamDecompression(fullStreamDecompression);
    }

    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        transportState().setDecompressorRegistry(decompressorRegistry);
    }

    public final void start(ClientStreamListener listener) {
        transportState().setListener(listener);
        if (!this.useGet) {
            abstractClientStreamSink().writeHeaders(this.headers, (byte[]) null);
            this.headers = null;
        }
    }

    /* access modifiers changed from: protected */
    public final Framer framer() {
        return this.framer;
    }

    public final boolean shouldBeCountedForInUse() {
        return this.shouldBeCountedForInUse;
    }

    public final void deliverFrame(WritableBuffer frame, boolean endOfStream, boolean flush, int numMessages) {
        Preconditions.checkArgument(frame != null || endOfStream, "null frame before EOS");
        abstractClientStreamSink().writeFrame(frame, endOfStream, flush, numMessages);
    }

    public final void halfClose() {
        if (!transportState().isOutboundClosed()) {
            transportState().setOutboundClosed();
            endOfMessages();
        }
    }

    public final void cancel(Status reason) {
        Preconditions.checkArgument(!reason.isOk(), "Should not cancel with OK status");
        this.cancelled = true;
        abstractClientStreamSink().cancel(reason);
    }

    public final boolean isReady() {
        return super.isReady() && !this.cancelled;
    }

    public final void appendTimeoutInsight(InsightBuilder insight) {
        insight.appendKeyValue("remote_addr", getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR));
    }

    /* access modifiers changed from: protected */
    public TransportTracer getTransportTracer() {
        return this.transportTracer;
    }

    /* renamed from: io.grpc.internal.AbstractClientStream$TransportState */
    protected static abstract class TransportState extends AbstractStream.TransportState {
        private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
        private boolean deframerClosed = false;
        private Runnable deframerClosedTask;
        private boolean fullStreamDecompression;
        private ClientStreamListener listener;
        private boolean listenerClosed;
        private volatile boolean outboundClosed;
        private final StatsTraceContext statsTraceCtx;
        private boolean statusReported;
        private boolean statusReportedIsOk;

        protected TransportState(int maxMessageSize, StatsTraceContext statsTraceCtx2, TransportTracer transportTracer) {
            super(maxMessageSize, statsTraceCtx2, transportTracer);
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx2, "statsTraceCtx");
        }

        /* access modifiers changed from: private */
        public void setFullStreamDecompression(boolean fullStreamDecompression2) {
            this.fullStreamDecompression = fullStreamDecompression2;
        }

        /* access modifiers changed from: private */
        public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry2) {
            Preconditions.checkState(this.listener == null, "Already called start");
            this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(decompressorRegistry2, "decompressorRegistry");
        }

        public final void setListener(ClientStreamListener listener2) {
            Preconditions.checkState(this.listener == null, "Already called setListener");
            this.listener = (ClientStreamListener) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        }

        public void deframerClosed(boolean hasPartialMessage) {
            Preconditions.checkState(this.statusReported, "status should have been reported on deframer closed");
            this.deframerClosed = true;
            if (this.statusReportedIsOk && hasPartialMessage) {
                transportReportStatus(Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame"), true, new Metadata());
            }
            Runnable runnable = this.deframerClosedTask;
            if (runnable != null) {
                runnable.run();
                this.deframerClosedTask = null;
            }
        }

        /* access modifiers changed from: protected */
        public final ClientStreamListener listener() {
            return this.listener;
        }

        /* access modifiers changed from: private */
        public final void setOutboundClosed() {
            this.outboundClosed = true;
        }

        /* access modifiers changed from: protected */
        public final boolean isOutboundClosed() {
            return this.outboundClosed;
        }

        /* access modifiers changed from: protected */
        public void inboundHeadersReceived(Metadata headers) {
            Preconditions.checkState(!this.statusReported, "Received headers on closed stream");
            this.statsTraceCtx.clientInboundHeaders();
            boolean compressedStream = false;
            String streamEncoding = (String) headers.get(GrpcUtil.CONTENT_ENCODING_KEY);
            if (this.fullStreamDecompression && streamEncoding != null) {
                if (streamEncoding.equalsIgnoreCase("gzip")) {
                    setFullStreamDecompressor(new GzipInflatingBuffer());
                    compressedStream = true;
                } else if (!streamEncoding.equalsIgnoreCase("identity")) {
                    deframeFailed(Status.INTERNAL.withDescription(String.format("Can't find full stream decompressor for %s", new Object[]{streamEncoding})).asRuntimeException());
                    return;
                }
            }
            String messageEncoding = (String) headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
            if (messageEncoding != null) {
                Decompressor decompressor = this.decompressorRegistry.lookupDecompressor(messageEncoding);
                if (decompressor == null) {
                    deframeFailed(Status.INTERNAL.withDescription(String.format("Can't find decompressor for %s", new Object[]{messageEncoding})).asRuntimeException());
                    return;
                } else if (decompressor != Codec.Identity.NONE) {
                    if (compressedStream) {
                        deframeFailed(Status.INTERNAL.withDescription(String.format("Full stream and gRPC message encoding cannot both be set", new Object[0])).asRuntimeException());
                        return;
                    }
                    setDecompressor(decompressor);
                }
            }
            listener().headersRead(headers);
        }

        /* access modifiers changed from: protected */
        public void inboundDataReceived(ReadableBuffer frame) {
            Preconditions.checkNotNull(frame, TypedValues.AttributesType.S_FRAME);
            boolean needToCloseFrame = true;
            try {
                if (this.statusReported) {
                    AbstractClientStream.log.log(Level.INFO, "Received data on closed stream");
                    if (!needToCloseFrame) {
                        return;
                    }
                    return;
                }
                needToCloseFrame = false;
                deframe(frame);
                if (needToCloseFrame) {
                    frame.close();
                }
            } finally {
                if (needToCloseFrame) {
                    frame.close();
                }
            }
        }

        /* access modifiers changed from: protected */
        public void inboundTrailersReceived(Metadata trailers, Status status) {
            Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            Preconditions.checkNotNull(trailers, GrpcUtil.TE_TRAILERS);
            if (this.statusReported) {
                AbstractClientStream.log.log(Level.INFO, "Received trailers on closed stream:\n {1}\n {2}", new Object[]{status, trailers});
                return;
            }
            this.statsTraceCtx.clientInboundTrailers(trailers);
            transportReportStatus(status, false, trailers);
        }

        public final void transportReportStatus(Status status, boolean stopDelivery, Metadata trailers) {
            transportReportStatus(status, ClientStreamListener.RpcProgress.PROCESSED, stopDelivery, trailers);
        }

        public final void transportReportStatus(final Status status, final ClientStreamListener.RpcProgress rpcProgress, boolean stopDelivery, final Metadata trailers) {
            Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            Preconditions.checkNotNull(trailers, GrpcUtil.TE_TRAILERS);
            if (!this.statusReported || stopDelivery) {
                this.statusReported = true;
                this.statusReportedIsOk = status.isOk();
                onStreamDeallocated();
                if (this.deframerClosed) {
                    this.deframerClosedTask = null;
                    closeListener(status, rpcProgress, trailers);
                    return;
                }
                this.deframerClosedTask = new Runnable() {
                    public void run() {
                        TransportState.this.closeListener(status, rpcProgress, trailers);
                    }
                };
                closeDeframer(stopDelivery);
            }
        }

        /* access modifiers changed from: private */
        public void closeListener(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
            if (!this.listenerClosed) {
                this.listenerClosed = true;
                this.statsTraceCtx.streamClosed(status);
                listener().closed(status, rpcProgress, trailers);
                if (getTransportTracer() != null) {
                    getTransportTracer().reportStreamClosed(status.isOk());
                }
            }
        }
    }

    /* renamed from: io.grpc.internal.AbstractClientStream$GetFramer */
    private class GetFramer implements Framer {
        private boolean closed;
        private Metadata headers;
        private byte[] payload;
        private final StatsTraceContext statsTraceCtx;

        public GetFramer(Metadata headers2, StatsTraceContext statsTraceCtx2) {
            this.headers = (Metadata) Preconditions.checkNotNull(headers2, "headers");
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx2, "statsTraceCtx");
        }

        public void writePayload(InputStream message) {
            Preconditions.checkState(this.payload == null, "writePayload should not be called multiple times");
            try {
                this.payload = ByteStreams.toByteArray(message);
                this.statsTraceCtx.outboundMessage(0);
                StatsTraceContext statsTraceContext = this.statsTraceCtx;
                byte[] bArr = this.payload;
                statsTraceContext.outboundMessageSent(0, (long) bArr.length, (long) bArr.length);
                this.statsTraceCtx.outboundUncompressedSize((long) this.payload.length);
                this.statsTraceCtx.outboundWireSize((long) this.payload.length);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void flush() {
        }

        public boolean isClosed() {
            return this.closed;
        }

        public void close() {
            boolean z = true;
            this.closed = true;
            if (this.payload == null) {
                z = false;
            }
            Preconditions.checkState(z, "Lack of request message. GET request is only supported for unary requests");
            AbstractClientStream.this.abstractClientStreamSink().writeHeaders(this.headers, this.payload);
            this.payload = null;
            this.headers = null;
        }

        public void dispose() {
            this.closed = true;
            this.payload = null;
            this.headers = null;
        }

        public Framer setMessageCompression(boolean enable) {
            return this;
        }

        public Framer setCompressor(Compressor compressor) {
            return this;
        }

        public void setMaxOutboundMessageSize(int maxSize) {
        }
    }
}
