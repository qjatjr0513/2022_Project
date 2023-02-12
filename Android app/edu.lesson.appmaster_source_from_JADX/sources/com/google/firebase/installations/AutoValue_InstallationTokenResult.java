package com.google.firebase.installations;

import com.google.firebase.installations.InstallationTokenResult;

final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    private final String token;
    private final long tokenCreationTimestamp;
    private final long tokenExpirationTimestamp;

    private AutoValue_InstallationTokenResult(String token2, long tokenExpirationTimestamp2, long tokenCreationTimestamp2) {
        this.token = token2;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp2;
        this.tokenCreationTimestamp = tokenCreationTimestamp2;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    public String toString() {
        return "InstallationTokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", tokenCreationTimestamp=" + this.tokenCreationTimestamp + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstallationTokenResult)) {
            return false;
        }
        InstallationTokenResult that = (InstallationTokenResult) o;
        if (this.token.equals(that.getToken()) && this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp() && this.tokenCreationTimestamp == that.getTokenCreationTimestamp()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.tokenExpirationTimestamp;
        long j2 = this.tokenCreationTimestamp;
        return (((((1 * 1000003) ^ this.token.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    public InstallationTokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends InstallationTokenResult.Builder {
        private String token;
        private Long tokenCreationTimestamp;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(InstallationTokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.tokenCreationTimestamp = Long.valueOf(source.getTokenCreationTimestamp());
        }

        public InstallationTokenResult.Builder setToken(String token2) {
            if (token2 != null) {
                this.token = token2;
                return this;
            }
            throw new NullPointerException("Null token");
        }

        public InstallationTokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp2) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp2);
            return this;
        }

        public InstallationTokenResult.Builder setTokenCreationTimestamp(long tokenCreationTimestamp2) {
            this.tokenCreationTimestamp = Long.valueOf(tokenCreationTimestamp2);
            return this;
        }

        public InstallationTokenResult build() {
            String missing = "";
            if (this.token == null) {
                missing = missing + " token";
            }
            if (this.tokenExpirationTimestamp == null) {
                missing = missing + " tokenExpirationTimestamp";
            }
            if (this.tokenCreationTimestamp == null) {
                missing = missing + " tokenCreationTimestamp";
            }
            if (missing.isEmpty()) {
                return new AutoValue_InstallationTokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.tokenCreationTimestamp.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
