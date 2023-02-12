package p004io.grpc;

import javax.annotation.Nullable;

/* renamed from: io.grpc.StatusException */
public class StatusException extends Exception {
    private static final long serialVersionUID = -660954903976144640L;
    private final boolean fillInStackTrace;
    private final Status status;
    private final Metadata trailers;

    public StatusException(Status status2) {
        this(status2, (Metadata) null);
    }

    public StatusException(Status status2, @Nullable Metadata trailers2) {
        this(status2, trailers2, true);
    }

    StatusException(Status status2, @Nullable Metadata trailers2, boolean fillInStackTrace2) {
        super(Status.formatThrowableMessage(status2), status2.getCause());
        this.status = status2;
        this.trailers = trailers2;
        this.fillInStackTrace = fillInStackTrace2;
        fillInStackTrace();
    }

    public synchronized Throwable fillInStackTrace() {
        return this.fillInStackTrace ? super.fillInStackTrace() : this;
    }

    public final Status getStatus() {
        return this.status;
    }

    public final Metadata getTrailers() {
        return this.trailers;
    }
}
