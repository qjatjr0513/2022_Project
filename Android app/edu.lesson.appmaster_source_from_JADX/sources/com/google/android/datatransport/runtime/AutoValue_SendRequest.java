package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;

final class AutoValue_SendRequest extends SendRequest {
    private final Encoding encoding;
    private final Event<?> event;
    private final Transformer<?, byte[]> transformer;
    private final TransportContext transportContext;
    private final String transportName;

    private AutoValue_SendRequest(TransportContext transportContext2, String transportName2, Event<?> event2, Transformer<?, byte[]> transformer2, Encoding encoding2) {
        this.transportContext = transportContext2;
        this.transportName = transportName2;
        this.event = event2;
        this.transformer = transformer2;
        this.encoding = encoding2;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public String getTransportName() {
        return this.transportName;
    }

    /* access modifiers changed from: package-private */
    public Event<?> getEvent() {
        return this.event;
    }

    /* access modifiers changed from: package-private */
    public Transformer<?, byte[]> getTransformer() {
        return this.transformer;
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.transportContext + ", transportName=" + this.transportName + ", event=" + this.event + ", transformer=" + this.transformer + ", encoding=" + this.encoding + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SendRequest)) {
            return false;
        }
        SendRequest that = (SendRequest) o;
        if (!this.transportContext.equals(that.getTransportContext()) || !this.transportName.equals(that.getTransportName()) || !this.event.equals(that.getEvent()) || !this.transformer.equals(that.getTransformer()) || !this.encoding.equals(that.getEncoding())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((1 * 1000003) ^ this.transportContext.hashCode()) * 1000003) ^ this.transportName.hashCode()) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.transformer.hashCode()) * 1000003) ^ this.encoding.hashCode();
    }

    static final class Builder extends SendRequest.Builder {
        private Encoding encoding;
        private Event<?> event;
        private Transformer<?, byte[]> transformer;
        private TransportContext transportContext;
        private String transportName;

        Builder() {
        }

        public SendRequest.Builder setTransportContext(TransportContext transportContext2) {
            if (transportContext2 != null) {
                this.transportContext = transportContext2;
                return this;
            }
            throw new NullPointerException("Null transportContext");
        }

        public SendRequest.Builder setTransportName(String transportName2) {
            if (transportName2 != null) {
                this.transportName = transportName2;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        /* access modifiers changed from: package-private */
        public SendRequest.Builder setEvent(Event<?> event2) {
            if (event2 != null) {
                this.event = event2;
                return this;
            }
            throw new NullPointerException("Null event");
        }

        /* access modifiers changed from: package-private */
        public SendRequest.Builder setTransformer(Transformer<?, byte[]> transformer2) {
            if (transformer2 != null) {
                this.transformer = transformer2;
                return this;
            }
            throw new NullPointerException("Null transformer");
        }

        /* access modifiers changed from: package-private */
        public SendRequest.Builder setEncoding(Encoding encoding2) {
            if (encoding2 != null) {
                this.encoding = encoding2;
                return this;
            }
            throw new NullPointerException("Null encoding");
        }

        public SendRequest build() {
            String missing = "";
            if (this.transportContext == null) {
                missing = missing + " transportContext";
            }
            if (this.transportName == null) {
                missing = missing + " transportName";
            }
            if (this.event == null) {
                missing = missing + " event";
            }
            if (this.transformer == null) {
                missing = missing + " transformer";
            }
            if (this.encoding == null) {
                missing = missing + " encoding";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
