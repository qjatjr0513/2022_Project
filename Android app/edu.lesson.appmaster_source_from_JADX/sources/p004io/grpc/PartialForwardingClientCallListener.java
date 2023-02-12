package p004io.grpc;

import com.google.common.base.MoreObjects;
import p004io.grpc.ClientCall;

/* renamed from: io.grpc.PartialForwardingClientCallListener */
abstract class PartialForwardingClientCallListener<RespT> extends ClientCall.Listener<RespT> {
    /* access modifiers changed from: protected */
    public abstract ClientCall.Listener<?> delegate();

    PartialForwardingClientCallListener() {
    }

    public void onHeaders(Metadata headers) {
        delegate().onHeaders(headers);
    }

    public void onClose(Status status, Metadata trailers) {
        delegate().onClose(status, trailers);
    }

    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
