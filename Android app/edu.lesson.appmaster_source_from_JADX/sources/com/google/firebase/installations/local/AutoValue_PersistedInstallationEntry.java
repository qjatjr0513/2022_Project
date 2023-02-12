package com.google.firebase.installations.local;

import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;

final class AutoValue_PersistedInstallationEntry extends PersistedInstallationEntry {
    private final String authToken;
    private final long expiresInSecs;
    private final String firebaseInstallationId;
    private final String fisError;
    private final String refreshToken;
    private final PersistedInstallation.RegistrationStatus registrationStatus;
    private final long tokenCreationEpochInSecs;

    private AutoValue_PersistedInstallationEntry(String firebaseInstallationId2, PersistedInstallation.RegistrationStatus registrationStatus2, String authToken2, String refreshToken2, long expiresInSecs2, long tokenCreationEpochInSecs2, String fisError2) {
        this.firebaseInstallationId = firebaseInstallationId2;
        this.registrationStatus = registrationStatus2;
        this.authToken = authToken2;
        this.refreshToken = refreshToken2;
        this.expiresInSecs = expiresInSecs2;
        this.tokenCreationEpochInSecs = tokenCreationEpochInSecs2;
        this.fisError = fisError2;
    }

    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    public PersistedInstallation.RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public long getExpiresInSecs() {
        return this.expiresInSecs;
    }

    public long getTokenCreationEpochInSecs() {
        return this.tokenCreationEpochInSecs;
    }

    public String getFisError() {
        return this.fisError;
    }

    public String toString() {
        return "PersistedInstallationEntry{firebaseInstallationId=" + this.firebaseInstallationId + ", registrationStatus=" + this.registrationStatus + ", authToken=" + this.authToken + ", refreshToken=" + this.refreshToken + ", expiresInSecs=" + this.expiresInSecs + ", tokenCreationEpochInSecs=" + this.tokenCreationEpochInSecs + ", fisError=" + this.fisError + "}";
    }

    public boolean equals(Object o) {
        String str;
        String str2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PersistedInstallationEntry)) {
            return false;
        }
        PersistedInstallationEntry that = (PersistedInstallationEntry) o;
        String str3 = this.firebaseInstallationId;
        if (str3 != null ? str3.equals(that.getFirebaseInstallationId()) : that.getFirebaseInstallationId() == null) {
            if (this.registrationStatus.equals(that.getRegistrationStatus()) && ((str = this.authToken) != null ? str.equals(that.getAuthToken()) : that.getAuthToken() == null) && ((str2 = this.refreshToken) != null ? str2.equals(that.getRefreshToken()) : that.getRefreshToken() == null) && this.expiresInSecs == that.getExpiresInSecs() && this.tokenCreationEpochInSecs == that.getTokenCreationEpochInSecs()) {
                String str4 = this.fisError;
                if (str4 == null) {
                    if (that.getFisError() == null) {
                        return true;
                    }
                } else if (str4.equals(that.getFisError())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.firebaseInstallationId;
        int i = 0;
        int h$2 = (((h$ ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.registrationStatus.hashCode()) * 1000003;
        String str2 = this.authToken;
        int h$3 = (h$2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.refreshToken;
        int hashCode = str3 == null ? 0 : str3.hashCode();
        long j = this.expiresInSecs;
        long j2 = this.tokenCreationEpochInSecs;
        int h$4 = (((((h$3 ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        String str4 = this.fisError;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return h$4 ^ i;
    }

    public PersistedInstallationEntry.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends PersistedInstallationEntry.Builder {
        private String authToken;
        private Long expiresInSecs;
        private String firebaseInstallationId;
        private String fisError;
        private String refreshToken;
        private PersistedInstallation.RegistrationStatus registrationStatus;
        private Long tokenCreationEpochInSecs;

        Builder() {
        }

        private Builder(PersistedInstallationEntry source) {
            this.firebaseInstallationId = source.getFirebaseInstallationId();
            this.registrationStatus = source.getRegistrationStatus();
            this.authToken = source.getAuthToken();
            this.refreshToken = source.getRefreshToken();
            this.expiresInSecs = Long.valueOf(source.getExpiresInSecs());
            this.tokenCreationEpochInSecs = Long.valueOf(source.getTokenCreationEpochInSecs());
            this.fisError = source.getFisError();
        }

        public PersistedInstallationEntry.Builder setFirebaseInstallationId(String firebaseInstallationId2) {
            this.firebaseInstallationId = firebaseInstallationId2;
            return this;
        }

        public PersistedInstallationEntry.Builder setRegistrationStatus(PersistedInstallation.RegistrationStatus registrationStatus2) {
            if (registrationStatus2 != null) {
                this.registrationStatus = registrationStatus2;
                return this;
            }
            throw new NullPointerException("Null registrationStatus");
        }

        public PersistedInstallationEntry.Builder setAuthToken(String authToken2) {
            this.authToken = authToken2;
            return this;
        }

        public PersistedInstallationEntry.Builder setRefreshToken(String refreshToken2) {
            this.refreshToken = refreshToken2;
            return this;
        }

        public PersistedInstallationEntry.Builder setExpiresInSecs(long expiresInSecs2) {
            this.expiresInSecs = Long.valueOf(expiresInSecs2);
            return this;
        }

        public PersistedInstallationEntry.Builder setTokenCreationEpochInSecs(long tokenCreationEpochInSecs2) {
            this.tokenCreationEpochInSecs = Long.valueOf(tokenCreationEpochInSecs2);
            return this;
        }

        public PersistedInstallationEntry.Builder setFisError(String fisError2) {
            this.fisError = fisError2;
            return this;
        }

        public PersistedInstallationEntry build() {
            String missing = "";
            if (this.registrationStatus == null) {
                missing = missing + " registrationStatus";
            }
            if (this.expiresInSecs == null) {
                missing = missing + " expiresInSecs";
            }
            if (this.tokenCreationEpochInSecs == null) {
                missing = missing + " tokenCreationEpochInSecs";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PersistedInstallationEntry(this.firebaseInstallationId, this.registrationStatus, this.authToken, this.refreshToken, this.expiresInSecs.longValue(), this.tokenCreationEpochInSecs.longValue(), this.fisError);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
