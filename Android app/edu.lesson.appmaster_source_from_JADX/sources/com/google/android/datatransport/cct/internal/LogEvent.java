package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_LogEvent;

public abstract class LogEvent {

    public static abstract class Builder {
        public abstract LogEvent build();

        public abstract Builder setEventCode(Integer num);

        public abstract Builder setEventTimeMs(long j);

        public abstract Builder setEventUptimeMs(long j);

        public abstract Builder setNetworkConnectionInfo(NetworkConnectionInfo networkConnectionInfo);

        /* access modifiers changed from: package-private */
        public abstract Builder setSourceExtension(byte[] bArr);

        /* access modifiers changed from: package-private */
        public abstract Builder setSourceExtensionJsonProto3(String str);

        public abstract Builder setTimezoneOffsetSeconds(long j);
    }

    public abstract Integer getEventCode();

    public abstract long getEventTimeMs();

    public abstract long getEventUptimeMs();

    public abstract NetworkConnectionInfo getNetworkConnectionInfo();

    public abstract byte[] getSourceExtension();

    public abstract String getSourceExtensionJsonProto3();

    public abstract long getTimezoneOffsetSeconds();

    public static Builder protoBuilder(byte[] sourceExtension) {
        return builder().setSourceExtension(sourceExtension);
    }

    public static Builder jsonBuilder(String sourceJsonExtension) {
        return builder().setSourceExtensionJsonProto3(sourceJsonExtension);
    }

    private static Builder builder() {
        return new AutoValue_LogEvent.Builder();
    }
}
