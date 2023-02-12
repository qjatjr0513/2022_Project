package p004io.grpc;

/* renamed from: io.grpc.StreamTracer */
public abstract class StreamTracer {
    public void streamClosed(Status status) {
    }

    public void outboundMessage(int seqNo) {
    }

    public void inboundMessage(int seqNo) {
    }

    public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
    }

    public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
    }

    public void outboundWireSize(long bytes) {
    }

    public void outboundUncompressedSize(long bytes) {
    }

    public void inboundWireSize(long bytes) {
    }

    public void inboundUncompressedSize(long bytes) {
    }
}
