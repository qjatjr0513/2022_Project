package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;

final class AutoValue_TransportContext extends TransportContext {
    private final String backendName;
    private final byte[] extras;
    private final Priority priority;

    private AutoValue_TransportContext(String backendName2, byte[] extras2, Priority priority2) {
        this.backendName = backendName2;
        this.extras = extras2;
        this.priority = priority2;
    }

    public String getBackendName() {
        return this.backendName;
    }

    public byte[] getExtras() {
        return this.extras;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TransportContext)) {
            return false;
        }
        TransportContext that = (TransportContext) o;
        if (this.backendName.equals(that.getBackendName())) {
            if (Arrays.equals(this.extras, that instanceof AutoValue_TransportContext ? ((AutoValue_TransportContext) that).extras : that.getExtras()) && this.priority.equals(that.getPriority())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.backendName.hashCode()) * 1000003) ^ Arrays.hashCode(this.extras)) * 1000003) ^ this.priority.hashCode();
    }

    static final class Builder extends TransportContext.Builder {
        private String backendName;
        private byte[] extras;
        private Priority priority;

        Builder() {
        }

        public TransportContext.Builder setBackendName(String backendName2) {
            if (backendName2 != null) {
                this.backendName = backendName2;
                return this;
            }
            throw new NullPointerException("Null backendName");
        }

        public TransportContext.Builder setExtras(byte[] extras2) {
            this.extras = extras2;
            return this;
        }

        public TransportContext.Builder setPriority(Priority priority2) {
            if (priority2 != null) {
                this.priority = priority2;
                return this;
            }
            throw new NullPointerException("Null priority");
        }

        public TransportContext build() {
            String missing = "";
            if (this.backendName == null) {
                missing = missing + " backendName";
            }
            if (this.priority == null) {
                missing = missing + " priority";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TransportContext(this.backendName, this.extras, this.priority);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
