package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import p004io.grpc.Metadata;
import p004io.grpc.Status;
import p004io.grpc.internal.ClientStreamListener;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.ForwardingClientStreamListener */
abstract class ForwardingClientStreamListener implements ClientStreamListener {
    /* access modifiers changed from: protected */
    public abstract ClientStreamListener delegate();

    ForwardingClientStreamListener() {
    }

    public void headersRead(Metadata headers) {
        delegate().headersRead(headers);
    }

    public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
        delegate().closed(status, rpcProgress, trailers);
    }

    public void messagesAvailable(StreamListener.MessageProducer producer) {
        delegate().messagesAvailable(producer);
    }

    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
