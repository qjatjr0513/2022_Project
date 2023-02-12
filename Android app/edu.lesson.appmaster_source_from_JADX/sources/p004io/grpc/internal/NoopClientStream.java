package p004io.grpc.internal;

import java.io.InputStream;
import javax.annotation.Nonnull;
import p004io.grpc.Attributes;
import p004io.grpc.Compressor;
import p004io.grpc.Deadline;
import p004io.grpc.DecompressorRegistry;
import p004io.grpc.Status;

/* renamed from: io.grpc.internal.NoopClientStream */
public class NoopClientStream implements ClientStream {
    public static final NoopClientStream INSTANCE = new NoopClientStream();

    public void setAuthority(String authority) {
    }

    public void start(ClientStreamListener listener) {
    }

    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    public void request(int numMessages) {
    }

    public void writeMessage(InputStream message) {
    }

    public void flush() {
    }

    public boolean isReady() {
        return false;
    }

    public void cancel(Status status) {
    }

    public void halfClose() {
    }

    public void setMessageCompression(boolean enable) {
    }

    public void optimizeForDirectExecutor() {
    }

    public void setCompressor(Compressor compressor) {
    }

    public void setFullStreamDecompression(boolean fullStreamDecompression) {
    }

    public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
    }

    public void setMaxInboundMessageSize(int maxSize) {
    }

    public void setMaxOutboundMessageSize(int maxSize) {
    }

    public void setDeadline(@Nonnull Deadline deadline) {
    }

    public void appendTimeoutInsight(InsightBuilder insight) {
        insight.append("noop");
    }
}
