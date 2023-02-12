package p004io.grpc.internal;

import java.util.concurrent.Executor;
import p004io.grpc.CallOptions;
import p004io.grpc.ClientStreamTracer;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalInstrumented;
import p004io.grpc.Metadata;
import p004io.grpc.MethodDescriptor;

/* renamed from: io.grpc.internal.ClientTransport */
public interface ClientTransport extends InternalInstrumented<InternalChannelz.SocketStats> {

    /* renamed from: io.grpc.internal.ClientTransport$PingCallback */
    public interface PingCallback {
        void onFailure(Throwable th);

        void onSuccess(long j);
    }

    ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr);

    void ping(PingCallback pingCallback, Executor executor);
}
