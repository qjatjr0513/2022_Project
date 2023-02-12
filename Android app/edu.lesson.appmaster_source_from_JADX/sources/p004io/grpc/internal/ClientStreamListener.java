package p004io.grpc.internal;

import p004io.grpc.Metadata;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.ClientStreamListener */
public interface ClientStreamListener extends StreamListener {

    /* renamed from: io.grpc.internal.ClientStreamListener$RpcProgress */
    public enum RpcProgress {
        PROCESSED,
        REFUSED,
        DROPPED
    }

    void closed(Status status, RpcProgress rpcProgress, Metadata metadata);

    void headersRead(Metadata metadata);
}
