package p004io.grpc;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p004io.grpc.ClientCall;
import p004io.grpc.MethodDescriptor;

/* renamed from: io.grpc.ClientInterceptors */
public class ClientInterceptors {
    /* access modifiers changed from: private */
    public static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void start(ClientCall.Listener<Object> listener, Metadata headers) {
        }

        public void request(int numMessages) {
        }

        public void cancel(String message, Throwable cause) {
        }

        public void halfClose() {
        }

        public void sendMessage(Object message) {
        }

        public boolean isReady() {
            return false;
        }
    };

    private ClientInterceptors() {
    }

    public static Channel interceptForward(Channel channel, ClientInterceptor... interceptors) {
        return interceptForward(channel, (List<? extends ClientInterceptor>) Arrays.asList(interceptors));
    }

    public static Channel interceptForward(Channel channel, List<? extends ClientInterceptor> interceptors) {
        List<? extends ClientInterceptor> copy = new ArrayList<>(interceptors);
        Collections.reverse(copy);
        return intercept(channel, copy);
    }

    public static Channel intercept(Channel channel, ClientInterceptor... interceptors) {
        return intercept(channel, (List<? extends ClientInterceptor>) Arrays.asList(interceptors));
    }

    public static Channel intercept(Channel channel, List<? extends ClientInterceptor> interceptors) {
        Preconditions.checkNotNull(channel, "channel");
        for (ClientInterceptor interceptor : interceptors) {
            channel = new InterceptorChannel(channel, interceptor);
        }
        return channel;
    }

    static <WReqT, WRespT> ClientInterceptor wrapClientInterceptor(final ClientInterceptor interceptor, final MethodDescriptor.Marshaller<WReqT> reqMarshaller, final MethodDescriptor.Marshaller<WRespT> respMarshaller) {
        return new ClientInterceptor() {
            public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
                final ClientCall<WReqT, WRespT> wrappedCall = interceptor.interceptCall(method.toBuilder(MethodDescriptor.Marshaller.this, respMarshaller).build(), callOptions, next);
                return new PartialForwardingClientCall<ReqT, RespT>() {
                    public void start(final ClientCall.Listener<RespT> responseListener, Metadata headers) {
                        wrappedCall.start(new PartialForwardingClientCallListener<WRespT>() {
                            public void onMessage(WRespT wMessage) {
                                responseListener.onMessage(method.getResponseMarshaller().parse(respMarshaller.stream(wMessage)));
                            }

                            /* access modifiers changed from: protected */
                            public ClientCall.Listener<?> delegate() {
                                return responseListener;
                            }
                        }, headers);
                    }

                    public void sendMessage(ReqT message) {
                        wrappedCall.sendMessage(MethodDescriptor.Marshaller.this.parse(method.getRequestMarshaller().stream(message)));
                    }

                    /* access modifiers changed from: protected */
                    public ClientCall<?, ?> delegate() {
                        return wrappedCall;
                    }
                };
            }
        };
    }

    /* renamed from: io.grpc.ClientInterceptors$InterceptorChannel */
    private static class InterceptorChannel extends Channel {
        private final Channel channel;
        private final ClientInterceptor interceptor;

        private InterceptorChannel(Channel channel2, ClientInterceptor interceptor2) {
            this.channel = channel2;
            this.interceptor = (ClientInterceptor) Preconditions.checkNotNull(interceptor2, "interceptor");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions) {
            return this.interceptor.interceptCall(method, callOptions, this.channel);
        }

        public String authority() {
            return this.channel.authority();
        }
    }

    /* renamed from: io.grpc.ClientInterceptors$CheckedForwardingClientCall */
    public static abstract class CheckedForwardingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private ClientCall<ReqT, RespT> delegate;

        /* access modifiers changed from: protected */
        public abstract void checkedStart(ClientCall.Listener<RespT> listener, Metadata metadata) throws Exception;

        protected CheckedForwardingClientCall(ClientCall<ReqT, RespT> delegate2) {
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        public final ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        public final void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
            try {
                checkedStart(responseListener, headers);
            } catch (Exception e) {
                this.delegate = ClientInterceptors.NOOP_CALL;
                responseListener.onClose(Status.fromThrowable(e), new Metadata());
            }
        }
    }
}
