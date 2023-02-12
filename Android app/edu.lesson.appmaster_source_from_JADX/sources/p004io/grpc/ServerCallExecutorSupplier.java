package p004io.grpc;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* renamed from: io.grpc.ServerCallExecutorSupplier */
public interface ServerCallExecutorSupplier {
    @Nullable
    <ReqT, RespT> Executor getExecutor(ServerCall<ReqT, RespT> serverCall, Metadata metadata);
}
