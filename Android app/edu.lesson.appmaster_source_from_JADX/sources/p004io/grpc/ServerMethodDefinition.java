package p004io.grpc;

/* renamed from: io.grpc.ServerMethodDefinition */
public final class ServerMethodDefinition<ReqT, RespT> {
    private final ServerCallHandler<ReqT, RespT> handler;
    private final MethodDescriptor<ReqT, RespT> method;

    private ServerMethodDefinition(MethodDescriptor<ReqT, RespT> method2, ServerCallHandler<ReqT, RespT> handler2) {
        this.method = method2;
        this.handler = handler2;
    }

    public static <ReqT, RespT> ServerMethodDefinition<ReqT, RespT> create(MethodDescriptor<ReqT, RespT> method2, ServerCallHandler<ReqT, RespT> handler2) {
        return new ServerMethodDefinition<>(method2, handler2);
    }

    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    public ServerCallHandler<ReqT, RespT> getServerCallHandler() {
        return this.handler;
    }

    public ServerMethodDefinition<ReqT, RespT> withServerCallHandler(ServerCallHandler<ReqT, RespT> handler2) {
        return new ServerMethodDefinition<>(this.method, handler2);
    }
}
