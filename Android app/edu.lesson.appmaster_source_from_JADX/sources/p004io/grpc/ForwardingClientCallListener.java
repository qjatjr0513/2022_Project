package p004io.grpc;

import p004io.grpc.ClientCall;

/* renamed from: io.grpc.ForwardingClientCallListener */
public abstract class ForwardingClientCallListener<RespT> extends PartialForwardingClientCallListener<RespT> {
    /* access modifiers changed from: protected */
    public abstract ClientCall.Listener<RespT> delegate();

    public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
        super.onClose(status, metadata);
    }

    public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
        super.onHeaders(metadata);
    }

    public /* bridge */ /* synthetic */ void onReady() {
        super.onReady();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public void onMessage(RespT message) {
        delegate().onMessage(message);
    }

    /* renamed from: io.grpc.ForwardingClientCallListener$SimpleForwardingClientCallListener */
    public static abstract class SimpleForwardingClientCallListener<RespT> extends ForwardingClientCallListener<RespT> {
        private final ClientCall.Listener<RespT> delegate;

        public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
            ForwardingClientCallListener.super.onClose(status, metadata);
        }

        public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
            ForwardingClientCallListener.super.onHeaders(metadata);
        }

        public /* bridge */ /* synthetic */ void onReady() {
            ForwardingClientCallListener.super.onReady();
        }

        public /* bridge */ /* synthetic */ String toString() {
            return ForwardingClientCallListener.super.toString();
        }

        protected SimpleForwardingClientCallListener(ClientCall.Listener<RespT> delegate2) {
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        public ClientCall.Listener<RespT> delegate() {
            return this.delegate;
        }
    }
}
