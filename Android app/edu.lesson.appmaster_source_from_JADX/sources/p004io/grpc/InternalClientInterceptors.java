package p004io.grpc;

import p004io.grpc.MethodDescriptor;

/* renamed from: io.grpc.InternalClientInterceptors */
public final class InternalClientInterceptors {
    public static <ReqT, RespT> ClientInterceptor wrapClientInterceptor(ClientInterceptor interceptor, MethodDescriptor.Marshaller<ReqT> reqMarshaller, MethodDescriptor.Marshaller<RespT> respMarshaller) {
        return ClientInterceptors.wrapClientInterceptor(interceptor, reqMarshaller, respMarshaller);
    }
}
