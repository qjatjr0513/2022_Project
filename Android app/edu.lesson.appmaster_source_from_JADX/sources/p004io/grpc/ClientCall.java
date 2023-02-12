package p004io.grpc;

import javax.annotation.Nullable;

/* renamed from: io.grpc.ClientCall */
public abstract class ClientCall<ReqT, RespT> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);

    public abstract void halfClose();

    public abstract void request(int i);

    public abstract void sendMessage(ReqT reqt);

    public abstract void start(Listener<RespT> listener, Metadata metadata);

    /* renamed from: io.grpc.ClientCall$Listener */
    public static abstract class Listener<T> {
        public void onHeaders(Metadata headers) {
        }

        public void onMessage(T t) {
        }

        public void onClose(Status status, Metadata trailers) {
        }

        public void onReady() {
        }
    }

    public boolean isReady() {
        return true;
    }

    public void setMessageCompression(boolean enabled) {
    }

    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }
}
