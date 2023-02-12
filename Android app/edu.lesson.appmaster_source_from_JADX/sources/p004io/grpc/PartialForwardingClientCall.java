package p004io.grpc;

import com.google.common.base.MoreObjects;
import javax.annotation.Nullable;

/* renamed from: io.grpc.PartialForwardingClientCall */
abstract class PartialForwardingClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    /* access modifiers changed from: protected */
    public abstract ClientCall<?, ?> delegate();

    PartialForwardingClientCall() {
    }

    public void request(int numMessages) {
        delegate().request(numMessages);
    }

    public void cancel(@Nullable String message, @Nullable Throwable cause) {
        delegate().cancel(message, cause);
    }

    public void halfClose() {
        delegate().halfClose();
    }

    public void setMessageCompression(boolean enabled) {
        delegate().setMessageCompression(enabled);
    }

    public boolean isReady() {
        return delegate().isReady();
    }

    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
