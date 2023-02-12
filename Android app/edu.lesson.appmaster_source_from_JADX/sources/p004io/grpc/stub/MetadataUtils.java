package p004io.grpc.stub;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
import p004io.grpc.CallOptions;
import p004io.grpc.Channel;
import p004io.grpc.ClientCall;
import p004io.grpc.ClientInterceptor;
import p004io.grpc.ForwardingClientCall;
import p004io.grpc.ForwardingClientCallListener;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.Status;

/* renamed from: io.grpc.stub.MetadataUtils */
public final class MetadataUtils {
    private MetadataUtils() {
    }

    @Deprecated
    public static <T extends AbstractStub<T>> T attachHeaders(T stub, Metadata extraHeaders) {
        return stub.withInterceptors(newAttachHeadersInterceptor(extraHeaders));
    }

    public static ClientInterceptor newAttachHeadersInterceptor(Metadata extraHeaders) {
        return new HeaderAttachingClientInterceptor(extraHeaders);
    }

    /* renamed from: io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor */
    private static final class HeaderAttachingClientInterceptor implements ClientInterceptor {
        /* access modifiers changed from: private */
        public final Metadata extraHeaders;

        HeaderAttachingClientInterceptor(Metadata extraHeaders2) {
            this.extraHeaders = (Metadata) Preconditions.checkNotNull(extraHeaders2, "extraHeaders");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
            return new HeaderAttachingClientCall(next.newCall(method, callOptions));
        }

        /* renamed from: io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor$HeaderAttachingClientCall */
        private final class HeaderAttachingClientCall<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
            HeaderAttachingClientCall(ClientCall<ReqT, RespT> call) {
                super(call);
            }

            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                headers.merge(HeaderAttachingClientInterceptor.this.extraHeaders);
                super.start(responseListener, headers);
            }
        }
    }

    @Deprecated
    public static <T extends AbstractStub<T>> T captureMetadata(T stub, AtomicReference<Metadata> headersCapture, AtomicReference<Metadata> trailersCapture) {
        return stub.withInterceptors(newCaptureMetadataInterceptor(headersCapture, trailersCapture));
    }

    public static ClientInterceptor newCaptureMetadataInterceptor(AtomicReference<Metadata> headersCapture, AtomicReference<Metadata> trailersCapture) {
        return new MetadataCapturingClientInterceptor(headersCapture, trailersCapture);
    }

    /* renamed from: io.grpc.stub.MetadataUtils$MetadataCapturingClientInterceptor */
    private static final class MetadataCapturingClientInterceptor implements ClientInterceptor {
        final AtomicReference<Metadata> headersCapture;
        final AtomicReference<Metadata> trailersCapture;

        MetadataCapturingClientInterceptor(AtomicReference<Metadata> headersCapture2, AtomicReference<Metadata> trailersCapture2) {
            this.headersCapture = (AtomicReference) Preconditions.checkNotNull(headersCapture2, "headersCapture");
            this.trailersCapture = (AtomicReference) Preconditions.checkNotNull(trailersCapture2, "trailersCapture");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
            return new MetadataCapturingClientCall(next.newCall(method, callOptions));
        }

        /* renamed from: io.grpc.stub.MetadataUtils$MetadataCapturingClientInterceptor$MetadataCapturingClientCall */
        private final class MetadataCapturingClientCall<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
            MetadataCapturingClientCall(ClientCall<ReqT, RespT> call) {
                super(call);
            }

            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                MetadataCapturingClientInterceptor.this.headersCapture.set((Object) null);
                MetadataCapturingClientInterceptor.this.trailersCapture.set((Object) null);
                super.start(new MetadataCapturingClientCallListener(responseListener), headers);
            }

            /* renamed from: io.grpc.stub.MetadataUtils$MetadataCapturingClientInterceptor$MetadataCapturingClientCall$MetadataCapturingClientCallListener */
            private final class MetadataCapturingClientCallListener extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
                MetadataCapturingClientCallListener(ClientCall.Listener<RespT> responseListener) {
                    super(responseListener);
                }

                public void onHeaders(Metadata headers) {
                    MetadataCapturingClientInterceptor.this.headersCapture.set(headers);
                    super.onHeaders(headers);
                }

                public void onClose(Status status, Metadata trailers) {
                    MetadataCapturingClientInterceptor.this.trailersCapture.set(trailers);
                    super.onClose(status, trailers);
                }
            }
        }
    }
}
