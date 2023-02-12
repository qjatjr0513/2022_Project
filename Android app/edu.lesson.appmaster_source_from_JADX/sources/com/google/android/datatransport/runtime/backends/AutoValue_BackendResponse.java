package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendResponse;

final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final BackendResponse.Status status;

    AutoValue_BackendResponse(BackendResponse.Status status2, long nextRequestWaitMillis2) {
        if (status2 != null) {
            this.status = status2;
            this.nextRequestWaitMillis = nextRequestWaitMillis2;
            return;
        }
        throw new NullPointerException("Null status");
    }

    public BackendResponse.Status getStatus() {
        return this.status;
    }

    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public String toString() {
        return "BackendResponse{status=" + this.status + ", nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BackendResponse)) {
            return false;
        }
        BackendResponse that = (BackendResponse) o;
        if (!this.status.equals(that.getStatus()) || this.nextRequestWaitMillis != that.getNextRequestWaitMillis()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.nextRequestWaitMillis;
        return (((1 * 1000003) ^ this.status.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
