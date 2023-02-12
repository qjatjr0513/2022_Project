package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import java.util.Arrays;

final class AutoValue_BackendRequest extends BackendRequest {
    private final Iterable<EventInternal> events;
    private final byte[] extras;

    private AutoValue_BackendRequest(Iterable<EventInternal> events2, byte[] extras2) {
        this.events = events2;
        this.extras = extras2;
    }

    public Iterable<EventInternal> getEvents() {
        return this.events;
    }

    public byte[] getExtras() {
        return this.extras;
    }

    public String toString() {
        return "BackendRequest{events=" + this.events + ", extras=" + Arrays.toString(this.extras) + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BackendRequest)) {
            return false;
        }
        BackendRequest that = (BackendRequest) o;
        if (this.events.equals(that.getEvents())) {
            if (Arrays.equals(this.extras, that instanceof AutoValue_BackendRequest ? ((AutoValue_BackendRequest) that).extras : that.getExtras())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.events.hashCode()) * 1000003) ^ Arrays.hashCode(this.extras);
    }

    static final class Builder extends BackendRequest.Builder {
        private Iterable<EventInternal> events;
        private byte[] extras;

        Builder() {
        }

        public BackendRequest.Builder setEvents(Iterable<EventInternal> events2) {
            if (events2 != null) {
                this.events = events2;
                return this;
            }
            throw new NullPointerException("Null events");
        }

        public BackendRequest.Builder setExtras(byte[] extras2) {
            this.extras = extras2;
            return this;
        }

        public BackendRequest build() {
            String missing = "";
            if (this.events == null) {
                missing = missing + " events";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BackendRequest(this.events, this.extras);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
