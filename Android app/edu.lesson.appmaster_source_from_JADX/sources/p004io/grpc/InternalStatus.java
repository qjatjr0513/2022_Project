package p004io.grpc;

import p004io.grpc.Metadata;

/* renamed from: io.grpc.InternalStatus */
public final class InternalStatus {
    public static final Metadata.Key<Status> CODE_KEY = Status.CODE_KEY;
    public static final Metadata.Key<String> MESSAGE_KEY = Status.MESSAGE_KEY;

    private InternalStatus() {
    }
}
