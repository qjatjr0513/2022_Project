package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class AutoValue_LogRequest extends LogRequest {
    private final ClientInfo clientInfo;
    private final List<LogEvent> logEvents;
    private final Integer logSource;
    private final String logSourceName;
    private final QosTier qosTier;
    private final long requestTimeMs;
    private final long requestUptimeMs;

    private AutoValue_LogRequest(long requestTimeMs2, long requestUptimeMs2, ClientInfo clientInfo2, Integer logSource2, String logSourceName2, List<LogEvent> logEvents2, QosTier qosTier2) {
        this.requestTimeMs = requestTimeMs2;
        this.requestUptimeMs = requestUptimeMs2;
        this.clientInfo = clientInfo2;
        this.logSource = logSource2;
        this.logSourceName = logSourceName2;
        this.logEvents = logEvents2;
        this.qosTier = qosTier2;
    }

    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    public Integer getLogSource() {
        return this.logSource;
    }

    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Encodable.Field(name = "logEvent")
    public List<LogEvent> getLogEvents() {
        return this.logEvents;
    }

    public QosTier getQosTier() {
        return this.qosTier;
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + "}";
    }

    public boolean equals(Object o) {
        ClientInfo clientInfo2;
        Integer num;
        String str;
        List<LogEvent> list;
        if (o == this) {
            return true;
        }
        if (!(o instanceof LogRequest)) {
            return false;
        }
        LogRequest that = (LogRequest) o;
        if (this.requestTimeMs == that.getRequestTimeMs() && this.requestUptimeMs == that.getRequestUptimeMs() && ((clientInfo2 = this.clientInfo) != null ? clientInfo2.equals(that.getClientInfo()) : that.getClientInfo() == null) && ((num = this.logSource) != null ? num.equals(that.getLogSource()) : that.getLogSource() == null) && ((str = this.logSourceName) != null ? str.equals(that.getLogSourceName()) : that.getLogSourceName() == null) && ((list = this.logEvents) != null ? list.equals(that.getLogEvents()) : that.getLogEvents() == null)) {
            QosTier qosTier2 = this.qosTier;
            if (qosTier2 == null) {
                if (that.getQosTier() == null) {
                    return true;
                }
            } else if (qosTier2.equals(that.getQosTier())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.requestTimeMs;
        long j2 = this.requestUptimeMs;
        int h$ = ((((1 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        ClientInfo clientInfo2 = this.clientInfo;
        int i = 0;
        int h$2 = (h$ ^ (clientInfo2 == null ? 0 : clientInfo2.hashCode())) * 1000003;
        Integer num = this.logSource;
        int h$3 = (h$2 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.logSourceName;
        int h$4 = (h$3 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<LogEvent> list = this.logEvents;
        int h$5 = (h$4 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        QosTier qosTier2 = this.qosTier;
        if (qosTier2 != null) {
            i = qosTier2.hashCode();
        }
        return h$5 ^ i;
    }

    static final class Builder extends LogRequest.Builder {
        private ClientInfo clientInfo;
        private List<LogEvent> logEvents;
        private Integer logSource;
        private String logSourceName;
        private QosTier qosTier;
        private Long requestTimeMs;
        private Long requestUptimeMs;

        Builder() {
        }

        public LogRequest.Builder setRequestTimeMs(long requestTimeMs2) {
            this.requestTimeMs = Long.valueOf(requestTimeMs2);
            return this;
        }

        public LogRequest.Builder setRequestUptimeMs(long requestUptimeMs2) {
            this.requestUptimeMs = Long.valueOf(requestUptimeMs2);
            return this;
        }

        public LogRequest.Builder setClientInfo(ClientInfo clientInfo2) {
            this.clientInfo = clientInfo2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogRequest.Builder setLogSource(Integer logSource2) {
            this.logSource = logSource2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogRequest.Builder setLogSourceName(String logSourceName2) {
            this.logSourceName = logSourceName2;
            return this;
        }

        public LogRequest.Builder setLogEvents(List<LogEvent> logEvents2) {
            this.logEvents = logEvents2;
            return this;
        }

        public LogRequest.Builder setQosTier(QosTier qosTier2) {
            this.qosTier = qosTier2;
            return this;
        }

        public LogRequest build() {
            String missing = "";
            if (this.requestTimeMs == null) {
                missing = missing + " requestTimeMs";
            }
            if (this.requestUptimeMs == null) {
                missing = missing + " requestUptimeMs";
            }
            if (missing.isEmpty()) {
                return new AutoValue_LogRequest(this.requestTimeMs.longValue(), this.requestUptimeMs.longValue(), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
