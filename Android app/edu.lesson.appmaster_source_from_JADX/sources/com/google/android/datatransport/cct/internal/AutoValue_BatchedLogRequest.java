package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class AutoValue_BatchedLogRequest extends BatchedLogRequest {
    private final List<LogRequest> logRequests;

    AutoValue_BatchedLogRequest(List<LogRequest> logRequests2) {
        if (logRequests2 != null) {
            this.logRequests = logRequests2;
            return;
        }
        throw new NullPointerException("Null logRequests");
    }

    @Encodable.Field(name = "logRequest")
    public List<LogRequest> getLogRequests() {
        return this.logRequests;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.logRequests + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BatchedLogRequest) {
            return this.logRequests.equals(((BatchedLogRequest) o).getLogRequests());
        }
        return false;
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.logRequests.hashCode();
    }
}
