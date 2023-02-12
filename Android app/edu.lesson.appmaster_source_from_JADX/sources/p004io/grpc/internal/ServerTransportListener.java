package p004io.grpc.internal;

import p004io.grpc.Attributes;
import p004io.grpc.Metadata;

/* renamed from: io.grpc.internal.ServerTransportListener */
public interface ServerTransportListener {
    void streamCreated(ServerStream serverStream, String str, Metadata metadata);

    Attributes transportReady(Attributes attributes);

    void transportTerminated();
}
