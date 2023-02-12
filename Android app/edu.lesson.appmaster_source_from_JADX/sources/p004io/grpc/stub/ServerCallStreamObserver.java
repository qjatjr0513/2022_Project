package p004io.grpc.stub;

/* renamed from: io.grpc.stub.ServerCallStreamObserver */
public abstract class ServerCallStreamObserver<RespT> extends CallStreamObserver<RespT> {
    public abstract boolean isCancelled();

    public abstract boolean isReady();

    public abstract void request(int i);

    public abstract void setCompression(String str);

    public abstract void setMessageCompression(boolean z);

    public abstract void setOnCancelHandler(Runnable runnable);

    public abstract void setOnReadyHandler(Runnable runnable);

    public void disableAutoRequest() {
        throw new UnsupportedOperationException();
    }

    public void setOnCloseHandler(Runnable onCloseHandler) {
        throw new UnsupportedOperationException();
    }
}
