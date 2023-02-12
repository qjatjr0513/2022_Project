package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import p004io.grpc.CallCredentials;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Context;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.MetadataApplierImpl */
final class MetadataApplierImpl extends CallCredentials.MetadataApplier {
    private final CallOptions callOptions;
    private final Context ctx;
    DelayedStream delayedStream;
    boolean finalized;
    private final MetadataApplierListener listener;
    private final Object lock = new Object();
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;
    @Nullable
    private ClientStream returnedStream;
    private final ClientStreamTracer[] tracers;
    private final ClientTransport transport;

    /* renamed from: io.grpc.internal.MetadataApplierImpl$MetadataApplierListener */
    public interface MetadataApplierListener {
        void onComplete();
    }

    MetadataApplierImpl(ClientTransport transport2, MethodDescriptor<?, ?> method2, Metadata origHeaders2, CallOptions callOptions2, MetadataApplierListener listener2, ClientStreamTracer[] tracers2) {
        this.transport = transport2;
        this.method = method2;
        this.origHeaders = origHeaders2;
        this.callOptions = callOptions2;
        this.ctx = Context.current();
        this.listener = listener2;
        this.tracers = tracers2;
    }

    /* JADX INFO: finally extract failed */
    public void apply(Metadata headers) {
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(headers, "headers");
        this.origHeaders.merge(headers);
        Context origCtx = this.ctx.attach();
        try {
            ClientStream realStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions, this.tracers);
            this.ctx.detach(origCtx);
            finalizeWith(realStream);
        } catch (Throwable th) {
            this.ctx.detach(origCtx);
            throw th;
        }
    }

    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(status, this.tracers));
    }

    private void finalizeWith(ClientStream stream) {
        boolean z = true;
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        boolean directStream = false;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = stream;
                directStream = true;
            }
        }
        if (directStream) {
            this.listener.onComplete();
            return;
        }
        if (this.delayedStream == null) {
            z = false;
        }
        Preconditions.checkState(z, "delayedStream is null");
        Runnable slow = this.delayedStream.setStream(stream);
        if (slow != null) {
            slow.run();
        }
        this.listener.onComplete();
    }

    /* access modifiers changed from: package-private */
    public ClientStream returnStream() {
        synchronized (this.lock) {
            ClientStream clientStream = this.returnedStream;
            if (clientStream != null) {
                return clientStream;
            }
            DelayedStream delayedStream2 = new DelayedStream();
            this.delayedStream = delayedStream2;
            this.returnedStream = delayedStream2;
            return delayedStream2;
        }
    }
}
