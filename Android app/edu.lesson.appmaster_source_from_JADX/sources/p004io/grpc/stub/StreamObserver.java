package p004io.grpc.stub;

/* renamed from: io.grpc.stub.StreamObserver */
public interface StreamObserver<V> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(V v);
}
