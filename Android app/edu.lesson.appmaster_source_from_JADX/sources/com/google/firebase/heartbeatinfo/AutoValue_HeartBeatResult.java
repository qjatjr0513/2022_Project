package com.google.firebase.heartbeatinfo;

import java.util.List;

final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final List<String> usedDates;
    private final String userAgent;

    AutoValue_HeartBeatResult(String userAgent2, List<String> usedDates2) {
        if (userAgent2 != null) {
            this.userAgent = userAgent2;
            if (usedDates2 != null) {
                this.usedDates = usedDates2;
                return;
            }
            throw new NullPointerException("Null usedDates");
        }
        throw new NullPointerException("Null userAgent");
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public List<String> getUsedDates() {
        return this.usedDates;
    }

    public String toString() {
        return "HeartBeatResult{userAgent=" + this.userAgent + ", usedDates=" + this.usedDates + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HeartBeatResult)) {
            return false;
        }
        HeartBeatResult that = (HeartBeatResult) o;
        if (!this.userAgent.equals(that.getUserAgent()) || !this.usedDates.equals(that.getUsedDates())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.userAgent.hashCode()) * 1000003) ^ this.usedDates.hashCode();
    }
}
