package com.google.android.datatransport.cct.internal;

final class AutoValue_LogResponse extends LogResponse {
    private final long nextRequestWaitMillis;

    AutoValue_LogResponse(long nextRequestWaitMillis2) {
        this.nextRequestWaitMillis = nextRequestWaitMillis2;
    }

    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LogResponse)) {
            return false;
        }
        if (this.nextRequestWaitMillis == ((LogResponse) o).getNextRequestWaitMillis()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.nextRequestWaitMillis;
        return (1 * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
