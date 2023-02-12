package p004io.grpc.internal;

/* renamed from: io.grpc.internal.BackoffPolicy */
public interface BackoffPolicy {

    /* renamed from: io.grpc.internal.BackoffPolicy$Provider */
    public interface Provider {
        BackoffPolicy get();
    }

    long nextBackoffNanos();
}
