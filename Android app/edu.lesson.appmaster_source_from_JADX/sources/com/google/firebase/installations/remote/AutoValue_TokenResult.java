package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.TokenResult;

final class AutoValue_TokenResult extends TokenResult {
    private final TokenResult.ResponseCode responseCode;
    private final String token;
    private final long tokenExpirationTimestamp;

    private AutoValue_TokenResult(String token2, long tokenExpirationTimestamp2, TokenResult.ResponseCode responseCode2) {
        this.token = token2;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp2;
        this.responseCode = responseCode2;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public TokenResult.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        return "TokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", responseCode=" + this.responseCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TokenResult)) {
            return false;
        }
        TokenResult that = (TokenResult) o;
        String str = this.token;
        if (str != null ? str.equals(that.getToken()) : that.getToken() == null) {
            if (this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp()) {
                TokenResult.ResponseCode responseCode2 = this.responseCode;
                if (responseCode2 == null) {
                    if (that.getResponseCode() == null) {
                        return true;
                    }
                } else if (responseCode2.equals(that.getResponseCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.token;
        int i = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.tokenExpirationTimestamp;
        int h$2 = (((h$ ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        TokenResult.ResponseCode responseCode2 = this.responseCode;
        if (responseCode2 != null) {
            i = responseCode2.hashCode();
        }
        return h$2 ^ i;
    }

    public TokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends TokenResult.Builder {
        private TokenResult.ResponseCode responseCode;
        private String token;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(TokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.responseCode = source.getResponseCode();
        }

        public TokenResult.Builder setToken(String token2) {
            this.token = token2;
            return this;
        }

        public TokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp2) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp2);
            return this;
        }

        public TokenResult.Builder setResponseCode(TokenResult.ResponseCode responseCode2) {
            this.responseCode = responseCode2;
            return this;
        }

        public TokenResult build() {
            String missing = "";
            if (this.tokenExpirationTimestamp == null) {
                missing = missing + " tokenExpirationTimestamp";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.responseCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
