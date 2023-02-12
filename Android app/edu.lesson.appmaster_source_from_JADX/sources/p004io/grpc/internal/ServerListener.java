package p004io.grpc.internal;

/* renamed from: io.grpc.internal.ServerListener */
public interface ServerListener {
    void serverShutdown();

    ServerTransportListener transportCreated(ServerTransport serverTransport);
}
