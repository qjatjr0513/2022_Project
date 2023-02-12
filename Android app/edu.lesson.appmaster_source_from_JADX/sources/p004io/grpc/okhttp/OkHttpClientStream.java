package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.common.p000io.BaseEncoding;
import java.util.List;
import okio.Buffer;
import p004io.grpc.Attributes;
import p004io.grpc.CallOptions;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.internal.AbstractClientStream;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.Http2ClientStreamTransportState;
import p004io.grpc.internal.StatsTraceContext;
import p004io.grpc.internal.TransportTracer;
import p004io.grpc.internal.WritableBuffer;
import p004io.grpc.okhttp.internal.framed.ErrorCode;
import p004io.grpc.okhttp.internal.framed.Header;
import p004io.perfmark.PerfMark;
import p004io.perfmark.Tag;

/* renamed from: io.grpc.okhttp.OkHttpClientStream */
class OkHttpClientStream extends AbstractClientStream {
    public static final int ABSENT_ID = -1;
    /* access modifiers changed from: private */
    public static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    /* access modifiers changed from: private */
    public String authority;
    /* access modifiers changed from: private */

    /* renamed from: id */
    public volatile int f316id;
    /* access modifiers changed from: private */
    public final MethodDescriptor<?, ?> method;
    private Object outboundFlowState;
    private final Sink sink;
    /* access modifiers changed from: private */
    public final TransportState state;
    /* access modifiers changed from: private */
    public final StatsTraceContext statsTraceCtx;
    /* access modifiers changed from: private */
    public boolean useGet;
    /* access modifiers changed from: private */
    public final String userAgent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkHttpClientStream(MethodDescriptor<?, ?> method2, Metadata headers, ExceptionHandlingFrameWriter frameWriter, OkHttpClientTransport transport, OutboundFlowController outboundFlow, Object lock, int maxMessageSize, int initialWindowSize, String authority2, String userAgent2, StatsTraceContext statsTraceCtx2, TransportTracer transportTracer, CallOptions callOptions, boolean useGetForSafeMethods) {
        super(new OkHttpWritableBufferAllocator(), statsTraceCtx2, transportTracer, headers, callOptions, useGetForSafeMethods && method2.isSafe());
        this.f316id = -1;
        this.sink = new Sink();
        this.useGet = false;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx2, "statsTraceCtx");
        this.method = method2;
        this.authority = authority2;
        this.userAgent = userAgent2;
        this.attributes = transport.getAttributes();
        this.state = new TransportState(maxMessageSize, statsTraceCtx2, lock, frameWriter, outboundFlow, transport, initialWindowSize, method2.getFullMethodName());
    }

    /* access modifiers changed from: protected */
    public TransportState transportState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    public MethodDescriptor.MethodType getType() {
        return this.method.getType();
    }

    /* renamed from: id */
    public int mo17338id() {
        return this.f316id;
    }

    /* access modifiers changed from: package-private */
    public boolean useGet() {
        return this.useGet;
    }

    public void setAuthority(String authority2) {
        this.authority = (String) Preconditions.checkNotNull(authority2, "authority");
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    /* renamed from: io.grpc.okhttp.OkHttpClientStream$Sink */
    class Sink implements AbstractClientStream.Sink {
        Sink() {
        }

        public void writeHeaders(Metadata metadata, byte[] payload) {
            PerfMark.startTask("OkHttpClientStream$Sink.writeHeaders");
            String defaultPath = "/" + OkHttpClientStream.this.method.getFullMethodName();
            if (payload != null) {
                boolean unused = OkHttpClientStream.this.useGet = true;
                defaultPath = defaultPath + "?" + BaseEncoding.base64().encode(payload);
            }
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.streamReady(metadata, defaultPath);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.writeHeaders");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.writeHeaders");
                throw th;
            }
        }

        public void writeFrame(WritableBuffer frame, boolean endOfStream, boolean flush, int numMessages) {
            Buffer buffer;
            PerfMark.startTask("OkHttpClientStream$Sink.writeFrame");
            if (frame == null) {
                buffer = OkHttpClientStream.EMPTY_BUFFER;
            } else {
                buffer = ((OkHttpWritableBuffer) frame).buffer();
                int size = (int) buffer.size();
                if (size > 0) {
                    OkHttpClientStream.this.onSendingBytes(size);
                }
            }
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.sendBuffer(buffer, endOfStream, flush);
                    OkHttpClientStream.this.getTransportTracer().reportMessageSent(numMessages);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.writeFrame");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.writeFrame");
                throw th;
            }
        }

        public void cancel(Status reason) {
            PerfMark.startTask("OkHttpClientStream$Sink.cancel");
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.cancel(reason, true, (Metadata) null);
                }
                PerfMark.stopTask("OkHttpClientStream$Sink.cancel");
            } catch (Throwable th) {
                PerfMark.stopTask("OkHttpClientStream$Sink.cancel");
                throw th;
            }
        }
    }

    /* renamed from: io.grpc.okhttp.OkHttpClientStream$TransportState */
    class TransportState extends Http2ClientStreamTransportState {
        private boolean canStart = true;
        private boolean cancelSent = false;
        private boolean flushPendingData = false;
        private final ExceptionHandlingFrameWriter frameWriter;
        private final int initialWindowSize;
        /* access modifiers changed from: private */
        public final Object lock;
        private final OutboundFlowController outboundFlow;
        private Buffer pendingData = new Buffer();
        private boolean pendingDataHasEndOfStream = false;
        private int processedWindow;
        private List<Header> requestHeaders;
        private final Tag tag;
        private final OkHttpClientTransport transport;
        private int window;

        public TransportState(int maxMessageSize, StatsTraceContext statsTraceCtx, Object lock2, ExceptionHandlingFrameWriter frameWriter2, OutboundFlowController outboundFlow2, OkHttpClientTransport transport2, int initialWindowSize2, String methodName) {
            super(maxMessageSize, statsTraceCtx, OkHttpClientStream.this.getTransportTracer());
            this.lock = Preconditions.checkNotNull(lock2, "lock");
            this.frameWriter = frameWriter2;
            this.outboundFlow = outboundFlow2;
            this.transport = transport2;
            this.window = initialWindowSize2;
            this.processedWindow = initialWindowSize2;
            this.initialWindowSize = initialWindowSize2;
            this.tag = PerfMark.createTag(methodName);
        }

        public void start(int streamId) {
            Preconditions.checkState(OkHttpClientStream.this.f316id == -1, "the stream has been started with id %s", streamId);
            int unused = OkHttpClientStream.this.f316id = streamId;
            OkHttpClientStream.this.state.onStreamAllocated();
            if (this.canStart) {
                this.frameWriter.synStream(OkHttpClientStream.this.useGet, false, OkHttpClientStream.this.f316id, 0, this.requestHeaders);
                OkHttpClientStream.this.statsTraceCtx.clientOutboundHeaders();
                this.requestHeaders = null;
                if (this.pendingData.size() > 0) {
                    this.outboundFlow.data(this.pendingDataHasEndOfStream, OkHttpClientStream.this.f316id, this.pendingData, this.flushPendingData);
                }
                this.canStart = false;
            }
        }

        /* access modifiers changed from: protected */
        public void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        /* access modifiers changed from: protected */
        public void http2ProcessingFailed(Status status, boolean stopDelivery, Metadata trailers) {
            cancel(status, stopDelivery, trailers);
        }

        public void deframeFailed(Throwable cause) {
            http2ProcessingFailed(Status.fromThrowable(cause), true, new Metadata());
        }

        public void bytesRead(int processedBytes) {
            int i = this.processedWindow - processedBytes;
            this.processedWindow = i;
            int i2 = this.initialWindowSize;
            if (((float) i) <= ((float) i2) * 0.5f) {
                int delta = i2 - i;
                this.window += delta;
                this.processedWindow = i + delta;
                this.frameWriter.windowUpdate(OkHttpClientStream.this.mo17338id(), (long) delta);
            }
        }

        public void deframerClosed(boolean hasPartialMessage) {
            onEndOfStream();
            super.deframerClosed(hasPartialMessage);
        }

        public void runOnTransportThread(Runnable r) {
            synchronized (this.lock) {
                r.run();
            }
        }

        public void transportHeadersReceived(List<Header> headers, boolean endOfStream) {
            if (endOfStream) {
                transportTrailersReceived(Utils.convertTrailers(headers));
            } else {
                transportHeadersReceived(Utils.convertHeaders(headers));
            }
        }

        public void transportDataReceived(Buffer frame, boolean endOfStream) {
            int size = this.window - ((int) frame.size());
            this.window = size;
            if (size < 0) {
                this.frameWriter.rstStream(OkHttpClientStream.this.mo17338id(), ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(OkHttpClientStream.this.mo17338id(), Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), ClientStreamListener.RpcProgress.PROCESSED, false, (ErrorCode) null, (Metadata) null);
                return;
            }
            super.transportDataReceived(new OkHttpReadableBuffer(frame), endOfStream);
        }

        private void onEndOfStream() {
            if (!isOutboundClosed()) {
                this.transport.finishStream(OkHttpClientStream.this.mo17338id(), (Status) null, ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.CANCEL, (Metadata) null);
            } else {
                this.transport.finishStream(OkHttpClientStream.this.mo17338id(), (Status) null, ClientStreamListener.RpcProgress.PROCESSED, false, (ErrorCode) null, (Metadata) null);
            }
        }

        /* access modifiers changed from: private */
        public void cancel(Status reason, boolean stopDelivery, Metadata trailers) {
            if (!this.cancelSent) {
                this.cancelSent = true;
                if (this.canStart) {
                    this.transport.removePendingStream(OkHttpClientStream.this);
                    this.requestHeaders = null;
                    this.pendingData.clear();
                    this.canStart = false;
                    transportReportStatus(reason, true, trailers != null ? trailers : new Metadata());
                    return;
                }
                this.transport.finishStream(OkHttpClientStream.this.mo17338id(), reason, ClientStreamListener.RpcProgress.PROCESSED, stopDelivery, ErrorCode.CANCEL, trailers);
            }
        }

        /* access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean endOfStream, boolean flush) {
            if (!this.cancelSent) {
                if (this.canStart) {
                    this.pendingData.write(buffer, (long) ((int) buffer.size()));
                    this.pendingDataHasEndOfStream |= endOfStream;
                    this.flushPendingData |= flush;
                    return;
                }
                Preconditions.checkState(OkHttpClientStream.this.mo17338id() != -1, "streamId should be set");
                this.outboundFlow.data(endOfStream, OkHttpClientStream.this.mo17338id(), buffer, flush);
            }
        }

        /* access modifiers changed from: private */
        public void streamReady(Metadata metadata, String path) {
            this.requestHeaders = Headers.createRequestHeaders(metadata, path, OkHttpClientStream.this.authority, OkHttpClientStream.this.userAgent, OkHttpClientStream.this.useGet, this.transport.isUsingPlaintext());
            this.transport.streamReadyToStart(OkHttpClientStream.this);
        }

        /* access modifiers changed from: package-private */
        public Tag tag() {
            return this.tag;
        }
    }

    /* access modifiers changed from: package-private */
    public void setOutboundFlowState(Object outboundFlowState2) {
        this.outboundFlowState = outboundFlowState2;
    }

    /* access modifiers changed from: package-private */
    public Object getOutboundFlowState() {
        return this.outboundFlowState;
    }
}
