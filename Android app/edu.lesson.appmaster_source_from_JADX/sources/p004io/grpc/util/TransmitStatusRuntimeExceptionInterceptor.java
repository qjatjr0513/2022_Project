package p004io.grpc.util;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.ForwardingServerCall;
import p004io.grpc.ForwardingServerCallListener;
import p004io.grpc.Metadata;
import p004io.grpc.ServerCall;
import p004io.grpc.ServerCallHandler;
import p004io.grpc.ServerInterceptor;
import p004io.grpc.Status;
import p004io.grpc.StatusRuntimeException;
import p004io.grpc.internal.SerializingExecutor;

/* renamed from: io.grpc.util.TransmitStatusRuntimeExceptionInterceptor */
public final class TransmitStatusRuntimeExceptionInterceptor implements ServerInterceptor {
    private TransmitStatusRuntimeExceptionInterceptor() {
    }

    public static ServerInterceptor instance() {
        return new TransmitStatusRuntimeExceptionInterceptor();
    }

    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        final SerializingServerCall serializingServerCall = new SerializingServerCall(call);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(next.startCall(serializingServerCall, headers)) {
            public void onMessage(ReqT message) {
                try {
                    super.onMessage(message);
                } catch (StatusRuntimeException e) {
                    closeWithException(e);
                }
            }

            public void onHalfClose() {
                try {
                    super.onHalfClose();
                } catch (StatusRuntimeException e) {
                    closeWithException(e);
                }
            }

            public void onCancel() {
                try {
                    super.onCancel();
                } catch (StatusRuntimeException e) {
                    closeWithException(e);
                }
            }

            public void onComplete() {
                try {
                    super.onComplete();
                } catch (StatusRuntimeException e) {
                    closeWithException(e);
                }
            }

            public void onReady() {
                try {
                    super.onReady();
                } catch (StatusRuntimeException e) {
                    closeWithException(e);
                }
            }

            private void closeWithException(StatusRuntimeException t) {
                Metadata metadata = t.getTrailers();
                if (metadata == null) {
                    metadata = new Metadata();
                }
                serializingServerCall.close(t.getStatus(), metadata);
            }
        };
    }

    /* renamed from: io.grpc.util.TransmitStatusRuntimeExceptionInterceptor$SerializingServerCall */
    private static class SerializingServerCall<ReqT, RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT> {
        private static final String ERROR_MSG = "Encountered error during serialized access";
        /* access modifiers changed from: private */
        public boolean closeCalled = false;
        private final SerializingExecutor serializingExecutor = new SerializingExecutor(MoreExecutors.directExecutor());

        SerializingServerCall(ServerCall<ReqT, RespT> delegate) {
            super(delegate);
        }

        public void sendMessage(final RespT message) {
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    SerializingServerCall.super.sendMessage(message);
                }
            });
        }

        public void request(final int numMessages) {
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    SerializingServerCall.super.request(numMessages);
                }
            });
        }

        public void sendHeaders(final Metadata headers) {
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    SerializingServerCall.super.sendHeaders(headers);
                }
            });
        }

        public void close(final Status status, final Metadata trailers) {
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    if (!SerializingServerCall.this.closeCalled) {
                        boolean unused = SerializingServerCall.this.closeCalled = true;
                        SerializingServerCall.super.close(status, trailers);
                    }
                }
            });
        }

        public boolean isReady() {
            final SettableFuture<Boolean> retVal = SettableFuture.create();
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    retVal.set(Boolean.valueOf(SerializingServerCall.super.isReady()));
                }
            });
            try {
                return retVal.get().booleanValue();
            } catch (InterruptedException e) {
                throw new RuntimeException(ERROR_MSG, e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(ERROR_MSG, e2);
            }
        }

        public boolean isCancelled() {
            final SettableFuture<Boolean> retVal = SettableFuture.create();
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    retVal.set(Boolean.valueOf(SerializingServerCall.super.isCancelled()));
                }
            });
            try {
                return retVal.get().booleanValue();
            } catch (InterruptedException e) {
                throw new RuntimeException(ERROR_MSG, e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(ERROR_MSG, e2);
            }
        }

        public void setMessageCompression(final boolean enabled) {
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    SerializingServerCall.super.setMessageCompression(enabled);
                }
            });
        }

        public void setCompression(final String compressor) {
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    SerializingServerCall.super.setCompression(compressor);
                }
            });
        }

        public Attributes getAttributes() {
            final SettableFuture<Attributes> retVal = SettableFuture.create();
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    retVal.set(SerializingServerCall.super.getAttributes());
                }
            });
            try {
                return retVal.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(ERROR_MSG, e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(ERROR_MSG, e2);
            }
        }

        @Nullable
        public String getAuthority() {
            final SettableFuture<String> retVal = SettableFuture.create();
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    retVal.set(SerializingServerCall.super.getAuthority());
                }
            });
            try {
                return retVal.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(ERROR_MSG, e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(ERROR_MSG, e2);
            }
        }
    }
}
