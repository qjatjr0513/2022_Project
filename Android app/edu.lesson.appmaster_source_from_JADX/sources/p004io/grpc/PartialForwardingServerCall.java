package p004io.grpc;

import com.google.common.base.MoreObjects;

/* renamed from: io.grpc.PartialForwardingServerCall */
abstract class PartialForwardingServerCall<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    /* access modifiers changed from: protected */
    public abstract ServerCall<?, ?> delegate();

    PartialForwardingServerCall() {
    }

    public void request(int numMessages) {
        delegate().request(numMessages);
    }

    public void sendHeaders(Metadata headers) {
        delegate().sendHeaders(headers);
    }

    public boolean isReady() {
        return delegate().isReady();
    }

    public void close(Status status, Metadata trailers) {
        delegate().close(status, trailers);
    }

    public boolean isCancelled() {
        return delegate().isCancelled();
    }

    public void setMessageCompression(boolean enabled) {
        delegate().setMessageCompression(enabled);
    }

    public void setCompression(String compressor) {
        delegate().setCompression(compressor);
    }

    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String getAuthority() {
        return delegate().getAuthority();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
