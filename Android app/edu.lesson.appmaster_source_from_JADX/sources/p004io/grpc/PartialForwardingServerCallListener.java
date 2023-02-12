package p004io.grpc;

import com.google.common.base.MoreObjects;
import p004io.grpc.ServerCall;

/* renamed from: io.grpc.PartialForwardingServerCallListener */
abstract class PartialForwardingServerCallListener<ReqT> extends ServerCall.Listener<ReqT> {
    /* access modifiers changed from: protected */
    public abstract ServerCall.Listener<?> delegate();

    PartialForwardingServerCallListener() {
    }

    public void onHalfClose() {
        delegate().onHalfClose();
    }

    public void onCancel() {
        delegate().onCancel();
    }

    public void onComplete() {
        delegate().onComplete();
    }

    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
