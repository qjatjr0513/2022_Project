package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p004io.grpc.Attributes;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Context;
import p004io.grpc.Metadata;
import p004io.grpc.ServerStreamTracer;
import p004io.grpc.Status;
import p004io.grpc.StreamTracer;

/* renamed from: io.grpc.internal.StatsTraceContext */
public final class StatsTraceContext {
    public static final StatsTraceContext NOOP = new StatsTraceContext(new StreamTracer[0]);
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final StreamTracer[] tracers;

    public static StatsTraceContext newClientContext(ClientStreamTracer[] tracers2, Attributes transportAtts, Metadata headers) {
        StatsTraceContext ctx = new StatsTraceContext(tracers2);
        for (ClientStreamTracer tracer : tracers2) {
            tracer.streamCreated(transportAtts, headers);
        }
        return ctx;
    }

    public static StatsTraceContext newServerContext(List<? extends ServerStreamTracer.Factory> factories, String fullMethodName, Metadata headers) {
        if (factories.isEmpty()) {
            return NOOP;
        }
        StreamTracer[] tracers2 = new StreamTracer[factories.size()];
        for (int i = 0; i < tracers2.length; i++) {
            tracers2[i] = ((ServerStreamTracer.Factory) factories.get(i)).newServerStreamTracer(fullMethodName, headers);
        }
        return new StatsTraceContext(tracers2);
    }

    StatsTraceContext(StreamTracer[] tracers2) {
        this.tracers = tracers2;
    }

    public List<StreamTracer> getTracersForTest() {
        return new ArrayList(Arrays.asList(this.tracers));
    }

    public void clientOutboundHeaders() {
        for (StreamTracer tracer : this.tracers) {
            ((ClientStreamTracer) tracer).outboundHeaders();
        }
    }

    public void clientInboundHeaders() {
        for (StreamTracer tracer : this.tracers) {
            ((ClientStreamTracer) tracer).inboundHeaders();
        }
    }

    public void clientInboundTrailers(Metadata trailers) {
        for (StreamTracer tracer : this.tracers) {
            ((ClientStreamTracer) tracer).inboundTrailers(trailers);
        }
    }

    public <ReqT, RespT> Context serverFilterContext(Context context) {
        Context ctx = (Context) Preconditions.checkNotNull(context, "context");
        for (StreamTracer tracer : this.tracers) {
            ctx = ((ServerStreamTracer) tracer).filterContext(ctx);
            Preconditions.checkNotNull(ctx, "%s returns null context", (Object) tracer);
        }
        return ctx;
    }

    public void serverCallStarted(ServerStreamTracer.ServerCallInfo<?, ?> callInfo) {
        for (StreamTracer tracer : this.tracers) {
            ((ServerStreamTracer) tracer).serverCallStarted(callInfo);
        }
    }

    public void streamClosed(Status status) {
        if (this.closed.compareAndSet(false, true)) {
            for (StreamTracer tracer : this.tracers) {
                tracer.streamClosed(status);
            }
        }
    }

    public void outboundMessage(int seqNo) {
        for (StreamTracer tracer : this.tracers) {
            tracer.outboundMessage(seqNo);
        }
    }

    public void inboundMessage(int seqNo) {
        for (StreamTracer tracer : this.tracers) {
            tracer.inboundMessage(seqNo);
        }
    }

    public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        for (StreamTracer tracer : this.tracers) {
            tracer.outboundMessageSent(seqNo, optionalWireSize, optionalUncompressedSize);
        }
    }

    public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        for (StreamTracer tracer : this.tracers) {
            tracer.inboundMessageRead(seqNo, optionalWireSize, optionalUncompressedSize);
        }
    }

    public void outboundUncompressedSize(long bytes) {
        for (StreamTracer tracer : this.tracers) {
            tracer.outboundUncompressedSize(bytes);
        }
    }

    public void outboundWireSize(long bytes) {
        for (StreamTracer tracer : this.tracers) {
            tracer.outboundWireSize(bytes);
        }
    }

    public void inboundUncompressedSize(long bytes) {
        for (StreamTracer tracer : this.tracers) {
            tracer.inboundUncompressedSize(bytes);
        }
    }

    public void inboundWireSize(long bytes) {
        for (StreamTracer tracer : this.tracers) {
            tracer.inboundWireSize(bytes);
        }
    }
}
