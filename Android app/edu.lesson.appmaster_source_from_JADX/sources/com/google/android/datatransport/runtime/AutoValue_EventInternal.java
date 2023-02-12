package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.EventInternal;
import java.util.Map;

final class AutoValue_EventInternal extends EventInternal {
    private final Map<String, String> autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final String transportName;
    private final long uptimeMillis;

    private AutoValue_EventInternal(String transportName2, Integer code2, EncodedPayload encodedPayload2, long eventMillis2, long uptimeMillis2, Map<String, String> autoMetadata2) {
        this.transportName = transportName2;
        this.code = code2;
        this.encodedPayload = encodedPayload2;
        this.eventMillis = eventMillis2;
        this.uptimeMillis = uptimeMillis2;
        this.autoMetadata = autoMetadata2;
    }

    public String getTransportName() {
        return this.transportName;
    }

    public Integer getCode() {
        return this.code;
    }

    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    public long getEventMillis() {
        return this.eventMillis;
    }

    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getAutoMetadata() {
        return this.autoMetadata;
    }

    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + "}";
    }

    public boolean equals(Object o) {
        Integer num;
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventInternal)) {
            return false;
        }
        EventInternal that = (EventInternal) o;
        if (!this.transportName.equals(that.getTransportName()) || ((num = this.code) != null ? !num.equals(that.getCode()) : that.getCode() != null) || !this.encodedPayload.equals(that.getEncodedPayload()) || this.eventMillis != that.getEventMillis() || this.uptimeMillis != that.getUptimeMillis() || !this.autoMetadata.equals(that.getAutoMetadata())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int h$ = ((1 * 1000003) ^ this.transportName.hashCode()) * 1000003;
        Integer num = this.code;
        int hashCode = num == null ? 0 : num.hashCode();
        long j = this.eventMillis;
        long j2 = this.uptimeMillis;
        return ((((((((h$ ^ hashCode) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode();
    }

    static final class Builder extends EventInternal.Builder {
        private Map<String, String> autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private String transportName;
        private Long uptimeMillis;

        Builder() {
        }

        public EventInternal.Builder setTransportName(String transportName2) {
            if (transportName2 != null) {
                this.transportName = transportName2;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        public EventInternal.Builder setCode(Integer code2) {
            this.code = code2;
            return this;
        }

        public EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload2) {
            if (encodedPayload2 != null) {
                this.encodedPayload = encodedPayload2;
                return this;
            }
            throw new NullPointerException("Null encodedPayload");
        }

        public EventInternal.Builder setEventMillis(long eventMillis2) {
            this.eventMillis = Long.valueOf(eventMillis2);
            return this;
        }

        public EventInternal.Builder setUptimeMillis(long uptimeMillis2) {
            this.uptimeMillis = Long.valueOf(uptimeMillis2);
            return this;
        }

        /* access modifiers changed from: protected */
        public EventInternal.Builder setAutoMetadata(Map<String, String> autoMetadata2) {
            if (autoMetadata2 != null) {
                this.autoMetadata = autoMetadata2;
                return this;
            }
            throw new NullPointerException("Null autoMetadata");
        }

        /* access modifiers changed from: protected */
        public Map<String, String> getAutoMetadata() {
            Map<String, String> map = this.autoMetadata;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        public EventInternal build() {
            String missing = "";
            if (this.transportName == null) {
                missing = missing + " transportName";
            }
            if (this.encodedPayload == null) {
                missing = missing + " encodedPayload";
            }
            if (this.eventMillis == null) {
                missing = missing + " eventMillis";
            }
            if (this.uptimeMillis == null) {
                missing = missing + " uptimeMillis";
            }
            if (this.autoMetadata == null) {
                missing = missing + " autoMetadata";
            }
            if (missing.isEmpty()) {
                return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
