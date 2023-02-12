package p004io.grpc;

/* renamed from: io.grpc.ServerTransportFilter */
public abstract class ServerTransportFilter {
    public Attributes transportReady(Attributes transportAttrs) {
        return transportAttrs;
    }

    public void transportTerminated(Attributes transportAttrs) {
    }
}
