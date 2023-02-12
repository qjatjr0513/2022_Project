package p004io.grpc;

import com.google.common.base.Preconditions;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p004io.grpc.MethodDescriptor;
import p004io.grpc.ServerCall;
import p004io.grpc.ServerServiceDefinition;
import p004io.grpc.ServiceDescriptor;

/* renamed from: io.grpc.ServerInterceptors */
public final class ServerInterceptors {
    private ServerInterceptors() {
    }

    public static ServerServiceDefinition interceptForward(ServerServiceDefinition serviceDef, ServerInterceptor... interceptors) {
        return interceptForward(serviceDef, (List<? extends ServerInterceptor>) Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition interceptForward(BindableService bindableService, ServerInterceptor... interceptors) {
        return interceptForward(bindableService.bindService(), (List<? extends ServerInterceptor>) Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition interceptForward(ServerServiceDefinition serviceDef, List<? extends ServerInterceptor> interceptors) {
        List<? extends ServerInterceptor> copy = new ArrayList<>(interceptors);
        Collections.reverse(copy);
        return intercept(serviceDef, copy);
    }

    public static ServerServiceDefinition interceptForward(BindableService bindableService, List<? extends ServerInterceptor> interceptors) {
        return interceptForward(bindableService.bindService(), interceptors);
    }

    public static ServerServiceDefinition intercept(ServerServiceDefinition serviceDef, ServerInterceptor... interceptors) {
        return intercept(serviceDef, (List<? extends ServerInterceptor>) Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition intercept(BindableService bindableService, ServerInterceptor... interceptors) {
        Preconditions.checkNotNull(bindableService, "bindableService");
        return intercept(bindableService.bindService(), (List<? extends ServerInterceptor>) Arrays.asList(interceptors));
    }

    public static ServerServiceDefinition intercept(ServerServiceDefinition serviceDef, List<? extends ServerInterceptor> interceptors) {
        Preconditions.checkNotNull(serviceDef, "serviceDef");
        if (interceptors.isEmpty()) {
            return serviceDef;
        }
        ServerServiceDefinition.Builder serviceDefBuilder = ServerServiceDefinition.builder(serviceDef.getServiceDescriptor());
        for (ServerMethodDefinition<?, ?> method : serviceDef.getMethods()) {
            wrapAndAddMethod(serviceDefBuilder, method, interceptors);
        }
        return serviceDefBuilder.build();
    }

    public static ServerServiceDefinition intercept(BindableService bindableService, List<? extends ServerInterceptor> interceptors) {
        Preconditions.checkNotNull(bindableService, "bindableService");
        return intercept(bindableService.bindService(), interceptors);
    }

    public static ServerServiceDefinition useInputStreamMessages(ServerServiceDefinition serviceDef) {
        return useMarshalledMessages(serviceDef, new MethodDescriptor.Marshaller<InputStream>() {
            public InputStream stream(InputStream value) {
                return value;
            }

            public InputStream parse(InputStream stream) {
                if (stream.markSupported()) {
                    return stream;
                }
                if (stream instanceof KnownLength) {
                    return new KnownLengthBufferedInputStream(stream);
                }
                return new BufferedInputStream(stream);
            }
        });
    }

    /* renamed from: io.grpc.ServerInterceptors$KnownLengthBufferedInputStream */
    private static final class KnownLengthBufferedInputStream extends BufferedInputStream implements KnownLength {
        KnownLengthBufferedInputStream(InputStream in) {
            super(in);
        }
    }

    public static <T> ServerServiceDefinition useMarshalledMessages(ServerServiceDefinition serviceDef, MethodDescriptor.Marshaller<T> marshaller) {
        List<ServerMethodDefinition<?, ?>> wrappedMethods = new ArrayList<>();
        List<MethodDescriptor<?, ?>> wrappedDescriptors = new ArrayList<>();
        for (ServerMethodDefinition<?, ?> definition : serviceDef.getMethods()) {
            MethodDescriptor<NewReqT, NewRespT> build = definition.getMethodDescriptor().toBuilder(marshaller, marshaller).build();
            wrappedDescriptors.add(build);
            wrappedMethods.add(wrapMethod(definition, build));
        }
        ServiceDescriptor.Builder serviceDescriptorBuilder = ServiceDescriptor.newBuilder(serviceDef.getServiceDescriptor().getName()).setSchemaDescriptor(serviceDef.getServiceDescriptor().getSchemaDescriptor());
        for (MethodDescriptor<?, ?> wrappedDescriptor : wrappedDescriptors) {
            serviceDescriptorBuilder.addMethod(wrappedDescriptor);
        }
        ServerServiceDefinition.Builder serviceBuilder = ServerServiceDefinition.builder(serviceDescriptorBuilder.build());
        for (ServerMethodDefinition<?, ?> definition2 : wrappedMethods) {
            serviceBuilder.addMethod(definition2);
        }
        return serviceBuilder.build();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [io.grpc.ServerMethodDefinition<ReqT, RespT>, io.grpc.ServerMethodDefinition] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <ReqT, RespT> void wrapAndAddMethod(p004io.grpc.ServerServiceDefinition.Builder r3, p004io.grpc.ServerMethodDefinition<ReqT, RespT> r4, java.util.List<? extends p004io.grpc.ServerInterceptor> r5) {
        /*
            io.grpc.ServerCallHandler r0 = r4.getServerCallHandler()
            java.util.Iterator r1 = r5.iterator()
        L_0x0008:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0019
            java.lang.Object r2 = r1.next()
            io.grpc.ServerInterceptor r2 = (p004io.grpc.ServerInterceptor) r2
            io.grpc.ServerInterceptors$InterceptCallHandler r0 = p004io.grpc.ServerInterceptors.InterceptCallHandler.create(r2, r0)
            goto L_0x0008
        L_0x0019:
            io.grpc.ServerMethodDefinition r1 = r4.withServerCallHandler(r0)
            r3.addMethod(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.ServerInterceptors.wrapAndAddMethod(io.grpc.ServerServiceDefinition$Builder, io.grpc.ServerMethodDefinition, java.util.List):void");
    }

    /* renamed from: io.grpc.ServerInterceptors$InterceptCallHandler */
    static final class InterceptCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        private final ServerCallHandler<ReqT, RespT> callHandler;
        private final ServerInterceptor interceptor;

        public static <ReqT, RespT> InterceptCallHandler<ReqT, RespT> create(ServerInterceptor interceptor2, ServerCallHandler<ReqT, RespT> callHandler2) {
            return new InterceptCallHandler<>(interceptor2, callHandler2);
        }

        private InterceptCallHandler(ServerInterceptor interceptor2, ServerCallHandler<ReqT, RespT> callHandler2) {
            this.interceptor = (ServerInterceptor) Preconditions.checkNotNull(interceptor2, "interceptor");
            this.callHandler = callHandler2;
        }

        public ServerCall.Listener<ReqT> startCall(ServerCall<ReqT, RespT> call, Metadata headers) {
            return this.interceptor.interceptCall(call, headers, this.callHandler);
        }
    }

    static <OReqT, ORespT, WReqT, WRespT> ServerMethodDefinition<WReqT, WRespT> wrapMethod(ServerMethodDefinition<OReqT, ORespT> definition, MethodDescriptor<WReqT, WRespT> wrappedMethod) {
        return ServerMethodDefinition.create(wrappedMethod, wrapHandler(definition.getServerCallHandler(), definition.getMethodDescriptor(), wrappedMethod));
    }

    private static <OReqT, ORespT, WReqT, WRespT> ServerCallHandler<WReqT, WRespT> wrapHandler(final ServerCallHandler<OReqT, ORespT> originalHandler, final MethodDescriptor<OReqT, ORespT> originalMethod, final MethodDescriptor<WReqT, WRespT> wrappedMethod) {
        return new ServerCallHandler<WReqT, WRespT>() {
            public ServerCall.Listener<WReqT> startCall(final ServerCall<WReqT, WRespT> call, Metadata headers) {
                final ServerCall.Listener<OReqT> originalListener = originalHandler.startCall(new PartialForwardingServerCall<OReqT, ORespT>() {
                    /* access modifiers changed from: protected */
                    public ServerCall<WReqT, WRespT> delegate() {
                        return call;
                    }

                    public void sendMessage(ORespT message) {
                        delegate().sendMessage(wrappedMethod.parseResponse(MethodDescriptor.this.streamResponse(message)));
                    }

                    public MethodDescriptor<OReqT, ORespT> getMethodDescriptor() {
                        return MethodDescriptor.this;
                    }
                }, headers);
                return new PartialForwardingServerCallListener<WReqT>() {
                    /* access modifiers changed from: protected */
                    public ServerCall.Listener<OReqT> delegate() {
                        return originalListener;
                    }

                    public void onMessage(WReqT message) {
                        delegate().onMessage(MethodDescriptor.this.parseRequest(wrappedMethod.streamRequest(message)));
                    }
                };
            }
        };
    }
}
