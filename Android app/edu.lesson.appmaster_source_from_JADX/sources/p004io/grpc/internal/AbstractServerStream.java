package p004io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.Decompressor;
import p004io.grpc.InternalStatus;
import p004io.grpc.Metadata;
import p004io.grpc.Status;
import p004io.grpc.internal.AbstractStream;
import p004io.grpc.internal.MessageFramer;

/* renamed from: io.grpc.internal.AbstractServerStream */
public abstract class AbstractServerStream extends AbstractStream implements ServerStream, MessageFramer.Sink {
    private final MessageFramer framer;
    private boolean headersSent;
    private boolean outboundClosed;
    private final StatsTraceContext statsTraceCtx;

    /* renamed from: io.grpc.internal.AbstractServerStream$Sink */
    protected interface Sink {
        void cancel(Status status);

        void writeFrame(@Nullable WritableBuffer writableBuffer, boolean z, int i);

        void writeHeaders(Metadata metadata);

        void writeTrailers(Metadata metadata, boolean z, Status status);
    }

    /* access modifiers changed from: protected */
    public abstract Sink abstractServerStreamSink();

    /* access modifiers changed from: protected */
    public abstract TransportState transportState();

    protected AbstractServerStream(WritableBufferAllocator bufferAllocator, StatsTraceContext statsTraceCtx2) {
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx2, "statsTraceCtx");
        this.framer = new MessageFramer(this, bufferAllocator, statsTraceCtx2);
    }

    /* access modifiers changed from: protected */
    public final MessageFramer framer() {
        return this.framer;
    }

    public final void writeHeaders(Metadata headers) {
        Preconditions.checkNotNull(headers, "headers");
        this.headersSent = true;
        abstractServerStreamSink().writeHeaders(headers);
    }

    public final void deliverFrame(WritableBuffer frame, boolean endOfStream, boolean flush, int numMessages) {
        abstractServerStreamSink().writeFrame(frame, endOfStream ? false : flush, numMessages);
    }

    public final void close(Status status, Metadata trailers) {
        Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
        Preconditions.checkNotNull(trailers, GrpcUtil.TE_TRAILERS);
        if (!this.outboundClosed) {
            this.outboundClosed = true;
            endOfMessages();
            addStatusToTrailers(trailers, status);
            transportState().setClosedStatus(status);
            abstractServerStreamSink().writeTrailers(trailers, this.headersSent, status);
        }
    }

    private void addStatusToTrailers(Metadata trailers, Status status) {
        trailers.discardAll(InternalStatus.CODE_KEY);
        trailers.discardAll(InternalStatus.MESSAGE_KEY);
        trailers.put(InternalStatus.CODE_KEY, status);
        if (status.getDescription() != null) {
            trailers.put(InternalStatus.MESSAGE_KEY, status.getDescription());
        }
    }

    public final void cancel(Status status) {
        abstractServerStreamSink().cancel(status);
    }

    public final boolean isReady() {
        return super.isReady();
    }

    public final void setDecompressor(Decompressor decompressor) {
        transportState().setDecompressor((Decompressor) Preconditions.checkNotNull(decompressor, "decompressor"));
    }

    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    public String getAuthority() {
        return null;
    }

    public final void setListener(ServerStreamListener serverStreamListener) {
        transportState().setListener(serverStreamListener);
    }

    public StatsTraceContext statsTraceContext() {
        return this.statsTraceCtx;
    }

    /* renamed from: io.grpc.internal.AbstractServerStream$TransportState */
    protected static abstract class TransportState extends AbstractStream.TransportState {
        @Nullable
        private Status closedStatus;
        private boolean deframerClosed = false;
        private Runnable deframerClosedTask;
        private boolean endOfStream = false;
        private boolean immediateCloseRequested = false;
        private ServerStreamListener listener;
        private boolean listenerClosed;
        private final StatsTraceContext statsTraceCtx;

        protected TransportState(int maxMessageSize, StatsTraceContext statsTraceCtx2, TransportTracer transportTracer) {
            super(maxMessageSize, statsTraceCtx2, (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer"));
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx2, "statsTraceCtx");
        }

        public final void setListener(ServerStreamListener listener2) {
            Preconditions.checkState(this.listener == null, "setListener should be called only once");
            this.listener = (ServerStreamListener) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        }

        public final void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportRemoteStreamStarted();
        }

        public void deframerClosed(boolean hasPartialMessage) {
            this.deframerClosed = true;
            if (this.endOfStream) {
                if (this.immediateCloseRequested || !hasPartialMessage) {
                    this.listener.halfClosed();
                } else {
                    deframeFailed(Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame").asRuntimeException());
                    this.deframerClosedTask = null;
                    return;
                }
            }
            Runnable runnable = this.deframerClosedTask;
            if (runnable != null) {
                runnable.run();
                this.deframerClosedTask = null;
            }
        }

        /* access modifiers changed from: protected */
        public ServerStreamListener listener() {
            return this.listener;
        }

        public void inboundDataReceived(ReadableBuffer frame, boolean endOfStream2) {
            Preconditions.checkState(!this.endOfStream, "Past end of stream");
            deframe(frame);
            if (endOfStream2) {
                this.endOfStream = true;
                closeDeframer(false);
            }
        }

        public final void transportReportStatus(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "status must not be OK");
            if (this.deframerClosed) {
                this.deframerClosedTask = null;
                closeListener(status);
                return;
            }
            this.deframerClosedTask = new Runnable() {
                public void run() {
                    TransportState.this.closeListener(status);
                }
            };
            this.immediateCloseRequested = true;
            closeDeframer(true);
        }

        public void complete() {
            if (this.deframerClosed) {
                this.deframerClosedTask = null;
                closeListener(Status.f313OK);
                return;
            }
            this.deframerClosedTask = new Runnable() {
                public void run() {
                    TransportState.this.closeListener(Status.f313OK);
                }
            };
            this.immediateCloseRequested = true;
            closeDeframer(true);
        }

        /* access modifiers changed from: private */
        public void closeListener(Status newStatus) {
            Preconditions.checkState(!newStatus.isOk() || this.closedStatus != null);
            if (!this.listenerClosed) {
                if (!newStatus.isOk()) {
                    this.statsTraceCtx.streamClosed(newStatus);
                    getTransportTracer().reportStreamClosed(false);
                } else {
                    this.statsTraceCtx.streamClosed(this.closedStatus);
                    getTransportTracer().reportStreamClosed(this.closedStatus.isOk());
                }
                this.listenerClosed = true;
                onStreamDeallocated();
                listener().closed(newStatus);
            }
        }

        /* access modifiers changed from: private */
        public void setClosedStatus(Status closeStatus) {
            Preconditions.checkState(this.closedStatus == null, "closedStatus can only be set once");
            this.closedStatus = closeStatus;
        }
    }
}
