package p004io.grpc;

import javax.annotation.Nullable;

/* renamed from: io.grpc.ServerCall */
public abstract class ServerCall<ReqT, RespT> {
    public abstract void close(Status status, Metadata metadata);

    public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();

    public abstract boolean isCancelled();

    public abstract void request(int i);

    public abstract void sendHeaders(Metadata metadata);

    public abstract void sendMessage(RespT respt);

    /* renamed from: io.grpc.ServerCall$Listener */
    public static abstract class Listener<ReqT> {
        public void onMessage(ReqT reqt) {
        }

        public void onHalfClose() {
        }

        public void onCancel() {
        }

        public void onComplete() {
        }

        public void onReady() {
        }
    }

    public boolean isReady() {
        return true;
    }

    public void setMessageCompression(boolean enabled) {
    }

    public void setCompression(String compressor) {
    }

    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    @Nullable
    public String getAuthority() {
        return null;
    }
}
