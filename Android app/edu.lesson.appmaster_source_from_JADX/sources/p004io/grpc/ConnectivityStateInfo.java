package p004io.grpc;

import com.google.common.base.Preconditions;

/* renamed from: io.grpc.ConnectivityStateInfo */
public final class ConnectivityStateInfo {
    private final ConnectivityState state;
    private final Status status;

    public static ConnectivityStateInfo forNonError(ConnectivityState state2) {
        Preconditions.checkArgument(state2 != ConnectivityState.TRANSIENT_FAILURE, "state is TRANSIENT_ERROR. Use forError() instead");
        return new ConnectivityStateInfo(state2, Status.f313OK);
    }

    public static ConnectivityStateInfo forTransientFailure(Status error) {
        Preconditions.checkArgument(!error.isOk(), "The error status must not be OK");
        return new ConnectivityStateInfo(ConnectivityState.TRANSIENT_FAILURE, error);
    }

    public ConnectivityState getState() {
        return this.state;
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean equals(Object other) {
        if (!(other instanceof ConnectivityStateInfo)) {
            return false;
        }
        ConnectivityStateInfo o = (ConnectivityStateInfo) other;
        if (!this.state.equals(o.state) || !this.status.equals(o.status)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.state.hashCode() ^ this.status.hashCode();
    }

    public String toString() {
        if (this.status.isOk()) {
            return this.state.toString();
        }
        return this.state + "(" + this.status + ")";
    }

    private ConnectivityStateInfo(ConnectivityState state2, Status status2) {
        this.state = (ConnectivityState) Preconditions.checkNotNull(state2, "state is null");
        this.status = (Status) Preconditions.checkNotNull(status2, "status is null");
    }
}
