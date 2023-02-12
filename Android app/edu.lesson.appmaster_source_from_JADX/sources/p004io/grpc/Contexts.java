package p004io.grpc;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeoutException;
import p004io.grpc.ForwardingServerCallListener;
import p004io.grpc.ServerCall;
import p004io.grpc.Status;

/* renamed from: io.grpc.Contexts */
public final class Contexts {
    private Contexts() {
    }

    public static <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(Context context, ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        Context previous = context.attach();
        try {
            return new ContextualizedServerCallListener(next.startCall(call, headers), context);
        } finally {
            context.detach(previous);
        }
    }

    /* renamed from: io.grpc.Contexts$ContextualizedServerCallListener */
    private static class ContextualizedServerCallListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
        private final Context context;

        public ContextualizedServerCallListener(ServerCall.Listener<ReqT> delegate, Context context2) {
            super(delegate);
            this.context = context2;
        }

        public void onMessage(ReqT message) {
            Context previous = this.context.attach();
            try {
                super.onMessage(message);
            } finally {
                this.context.detach(previous);
            }
        }

        public void onHalfClose() {
            Context previous = this.context.attach();
            try {
                super.onHalfClose();
            } finally {
                this.context.detach(previous);
            }
        }

        public void onCancel() {
            Context previous = this.context.attach();
            try {
                super.onCancel();
            } finally {
                this.context.detach(previous);
            }
        }

        public void onComplete() {
            Context previous = this.context.attach();
            try {
                super.onComplete();
            } finally {
                this.context.detach(previous);
            }
        }

        public void onReady() {
            Context previous = this.context.attach();
            try {
                super.onReady();
            } finally {
                this.context.detach(previous);
            }
        }
    }

    public static Status statusFromCancelled(Context context) {
        Preconditions.checkNotNull(context, "context must not be null");
        if (!context.isCancelled()) {
            return null;
        }
        Throwable cancellationCause = context.cancellationCause();
        if (cancellationCause == null) {
            return Status.CANCELLED.withDescription("io.grpc.Context was cancelled without error");
        }
        if (cancellationCause instanceof TimeoutException) {
            return Status.DEADLINE_EXCEEDED.withDescription(cancellationCause.getMessage()).withCause(cancellationCause);
        }
        Status status = Status.fromThrowable(cancellationCause);
        if (!Status.Code.UNKNOWN.equals(status.getCode()) || status.getCause() != cancellationCause) {
            return status.withCause(cancellationCause);
        }
        return Status.CANCELLED.withDescription("Context cancelled").withCause(cancellationCause);
    }
}
