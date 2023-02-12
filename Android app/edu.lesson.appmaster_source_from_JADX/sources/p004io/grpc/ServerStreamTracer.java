package p004io.grpc;

import javax.annotation.Nullable;

/* renamed from: io.grpc.ServerStreamTracer */
public abstract class ServerStreamTracer extends StreamTracer {

    /* renamed from: io.grpc.ServerStreamTracer$Factory */
    public static abstract class Factory {
        public abstract ServerStreamTracer newServerStreamTracer(String str, Metadata metadata);
    }

    /* renamed from: io.grpc.ServerStreamTracer$ServerCallInfo */
    public static abstract class ServerCallInfo<ReqT, RespT> {
        public abstract Attributes getAttributes();

        @Nullable
        public abstract String getAuthority();

        public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();
    }

    public Context filterContext(Context context) {
        return context;
    }

    public void serverCallStarted(ServerCallInfo<?, ?> callInfo) {
        serverCallStarted((ServerCall<?, ?>) ReadOnlyServerCall.create(callInfo));
    }

    @Deprecated
    public void serverCallStarted(ServerCall<?, ?> serverCall) {
    }

    @Deprecated
    /* renamed from: io.grpc.ServerStreamTracer$ReadOnlyServerCall */
    private static final class ReadOnlyServerCall<ReqT, RespT> extends ForwardingServerCall<ReqT, RespT> {
        private final ServerCallInfo<ReqT, RespT> callInfo;

        /* access modifiers changed from: private */
        public static <ReqT, RespT> ReadOnlyServerCall<ReqT, RespT> create(ServerCallInfo<ReqT, RespT> callInfo2) {
            return new ReadOnlyServerCall<>(callInfo2);
        }

        private ReadOnlyServerCall(ServerCallInfo<ReqT, RespT> callInfo2) {
            this.callInfo = callInfo2;
        }

        public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
            return this.callInfo.getMethodDescriptor();
        }

        public Attributes getAttributes() {
            return this.callInfo.getAttributes();
        }

        public boolean isReady() {
            return false;
        }

        public boolean isCancelled() {
            return false;
        }

        public String getAuthority() {
            return this.callInfo.getAuthority();
        }

        /* access modifiers changed from: protected */
        public ServerCall<ReqT, RespT> delegate() {
            throw new UnsupportedOperationException();
        }
    }
}
