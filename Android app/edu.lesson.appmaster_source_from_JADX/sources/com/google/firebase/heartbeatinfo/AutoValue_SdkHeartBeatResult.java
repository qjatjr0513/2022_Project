package com.google.firebase.heartbeatinfo;

final class AutoValue_SdkHeartBeatResult extends SdkHeartBeatResult {
    private final long millis;
    private final String sdkName;

    AutoValue_SdkHeartBeatResult(String sdkName2, long millis2) {
        if (sdkName2 != null) {
            this.sdkName = sdkName2;
            this.millis = millis2;
            return;
        }
        throw new NullPointerException("Null sdkName");
    }

    public String getSdkName() {
        return this.sdkName;
    }

    public long getMillis() {
        return this.millis;
    }

    public String toString() {
        return "SdkHeartBeatResult{sdkName=" + this.sdkName + ", millis=" + this.millis + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SdkHeartBeatResult)) {
            return false;
        }
        SdkHeartBeatResult that = (SdkHeartBeatResult) o;
        if (!this.sdkName.equals(that.getSdkName()) || this.millis != that.getMillis()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.millis;
        return (((1 * 1000003) ^ this.sdkName.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
