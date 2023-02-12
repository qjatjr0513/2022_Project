package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;

final class AutoValue_LogEvent extends LogEvent {
    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    private AutoValue_LogEvent(long eventTimeMs2, Integer eventCode2, long eventUptimeMs2, byte[] sourceExtension2, String sourceExtensionJsonProto32, long timezoneOffsetSeconds2, NetworkConnectionInfo networkConnectionInfo2) {
        this.eventTimeMs = eventTimeMs2;
        this.eventCode = eventCode2;
        this.eventUptimeMs = eventUptimeMs2;
        this.sourceExtension = sourceExtension2;
        this.sourceExtensionJsonProto3 = sourceExtensionJsonProto32;
        this.timezoneOffsetSeconds = timezoneOffsetSeconds2;
        this.networkConnectionInfo = networkConnectionInfo2;
    }

    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    public Integer getEventCode() {
        return this.eventCode;
    }

    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + "}";
    }

    public boolean equals(Object o) {
        Integer num;
        String str;
        if (o == this) {
            return true;
        }
        if (!(o instanceof LogEvent)) {
            return false;
        }
        LogEvent that = (LogEvent) o;
        if (this.eventTimeMs == that.getEventTimeMs() && ((num = this.eventCode) != null ? num.equals(that.getEventCode()) : that.getEventCode() == null) && this.eventUptimeMs == that.getEventUptimeMs()) {
            if (Arrays.equals(this.sourceExtension, that instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) that).sourceExtension : that.getSourceExtension()) && ((str = this.sourceExtensionJsonProto3) != null ? str.equals(that.getSourceExtensionJsonProto3()) : that.getSourceExtensionJsonProto3() == null) && this.timezoneOffsetSeconds == that.getTimezoneOffsetSeconds()) {
                NetworkConnectionInfo networkConnectionInfo2 = this.networkConnectionInfo;
                if (networkConnectionInfo2 == null) {
                    if (that.getNetworkConnectionInfo() == null) {
                        return true;
                    }
                } else if (networkConnectionInfo2.equals(that.getNetworkConnectionInfo())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.eventTimeMs;
        int h$ = ((1 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        Integer num = this.eventCode;
        int i = 0;
        int hashCode = num == null ? 0 : num.hashCode();
        long j2 = this.eventUptimeMs;
        int h$2 = (((((h$ ^ hashCode) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003;
        String str = this.sourceExtensionJsonProto3;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j3 = this.timezoneOffsetSeconds;
        int h$3 = (((h$2 ^ hashCode2) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo2 = this.networkConnectionInfo;
        if (networkConnectionInfo2 != null) {
            i = networkConnectionInfo2.hashCode();
        }
        return h$3 ^ i;
    }

    static final class Builder extends LogEvent.Builder {
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        Builder() {
        }

        public LogEvent.Builder setEventTimeMs(long eventTimeMs2) {
            this.eventTimeMs = Long.valueOf(eventTimeMs2);
            return this;
        }

        public LogEvent.Builder setEventCode(Integer eventCode2) {
            this.eventCode = eventCode2;
            return this;
        }

        public LogEvent.Builder setEventUptimeMs(long eventUptimeMs2) {
            this.eventUptimeMs = Long.valueOf(eventUptimeMs2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogEvent.Builder setSourceExtension(byte[] sourceExtension2) {
            this.sourceExtension = sourceExtension2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogEvent.Builder setSourceExtensionJsonProto3(String sourceExtensionJsonProto32) {
            this.sourceExtensionJsonProto3 = sourceExtensionJsonProto32;
            return this;
        }

        public LogEvent.Builder setTimezoneOffsetSeconds(long timezoneOffsetSeconds2) {
            this.timezoneOffsetSeconds = Long.valueOf(timezoneOffsetSeconds2);
            return this;
        }

        public LogEvent.Builder setNetworkConnectionInfo(NetworkConnectionInfo networkConnectionInfo2) {
            this.networkConnectionInfo = networkConnectionInfo2;
            return this;
        }

        public LogEvent build() {
            String missing = "";
            if (this.eventTimeMs == null) {
                missing = missing + " eventTimeMs";
            }
            if (this.eventUptimeMs == null) {
                missing = missing + " eventUptimeMs";
            }
            if (this.timezoneOffsetSeconds == null) {
                missing = missing + " timezoneOffsetSeconds";
            }
            if (missing.isEmpty()) {
                return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
