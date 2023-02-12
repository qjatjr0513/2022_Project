package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import p004io.grpc.CallOptions;
import p004io.grpc.Channel;
import p004io.grpc.ClientCall;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.Context;
import p004io.grpc.InternalConfigSelector;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientCallImpl;
import p004io.grpc.internal.ClientStreamListener;

/* renamed from: io.grpc.internal.SubchannelChannel */
final class SubchannelChannel extends Channel {
    static final Status NOT_READY_ERROR;
    static final Status WAIT_FOR_READY_ERROR = Status.UNAVAILABLE.withDescription("wait-for-ready RPC is not supported on Subchannel.asChannel()");
    /* access modifiers changed from: private */
    public static final FailingClientTransport notReadyTransport;
    private final CallTracer callsTracer;
    private final AtomicReference<InternalConfigSelector> configSelector;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private final Executor executor;
    /* access modifiers changed from: private */
    public final InternalSubchannel subchannel;
    private final ClientCallImpl.ClientStreamProvider transportProvider = new ClientCallImpl.ClientStreamProvider() {
        public ClientStream newStream(MethodDescriptor<?, ?> method, CallOptions callOptions, Metadata headers, Context context) {
            ClientTransport transport = SubchannelChannel.this.subchannel.getTransport();
            if (transport == null) {
                transport = SubchannelChannel.notReadyTransport;
            }
            ClientStreamTracer[] tracers = GrpcUtil.getClientStreamTracers(callOptions, headers, 0, false);
            Context origContext = context.attach();
            try {
                return transport.newStream(method, headers, callOptions, tracers);
            } finally {
                context.detach(origContext);
            }
        }
    };

    static {
        Status withDescription = Status.UNAVAILABLE.withDescription("Subchannel is NOT READY");
        NOT_READY_ERROR = withDescription;
        notReadyTransport = new FailingClientTransport(withDescription, ClientStreamListener.RpcProgress.REFUSED);
    }

    SubchannelChannel(InternalSubchannel subchannel2, Executor executor2, ScheduledExecutorService deadlineCancellationExecutor2, CallTracer callsTracer2, AtomicReference<InternalConfigSelector> configSelector2) {
        this.subchannel = (InternalSubchannel) Preconditions.checkNotNull(subchannel2, "subchannel");
        this.executor = (Executor) Preconditions.checkNotNull(executor2, "executor");
        this.deadlineCancellationExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(deadlineCancellationExecutor2, "deadlineCancellationExecutor");
        this.callsTracer = (CallTracer) Preconditions.checkNotNull(callsTracer2, "callsTracer");
        this.configSelector = (AtomicReference) Preconditions.checkNotNull(configSelector2, "configSelector");
    }

    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        final Executor effectiveExecutor = callOptions.getExecutor() == null ? this.executor : callOptions.getExecutor();
        if (callOptions.isWaitForReady()) {
            return new ClientCall<RequestT, ResponseT>() {
                public void start(final ClientCall.Listener<ResponseT> listener, Metadata headers) {
                    effectiveExecutor.execute(new Runnable() {
                        public void run() {
                            listener.onClose(SubchannelChannel.WAIT_FOR_READY_ERROR, new Metadata());
                        }
                    });
                }

                public void request(int numMessages) {
                }

                public void cancel(String message, Throwable cause) {
                }

                public void halfClose() {
                }

                public void sendMessage(RequestT requestt) {
                }
            };
        }
        return new ClientCallImpl(methodDescriptor, effectiveExecutor, callOptions.withOption(GrpcUtil.CALL_OPTIONS_RPC_OWNED_BY_BALANCER, Boolean.TRUE), this.transportProvider, this.deadlineCancellationExecutor, this.callsTracer, this.configSelector.get());
    }

    public String authority() {
        return this.subchannel.getAuthority();
    }
}
