package com.google.firebase.firestore.local;

import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.util.Preconditions;
import com.google.protobuf.ByteString;

public final class TargetData {
    private final SnapshotVersion lastLimboFreeSnapshotVersion;
    private final QueryPurpose purpose;
    private final ByteString resumeToken;
    private final long sequenceNumber;
    private final SnapshotVersion snapshotVersion;
    private final Target target;
    private final int targetId;

    TargetData(Target target2, int targetId2, long sequenceNumber2, QueryPurpose purpose2, SnapshotVersion snapshotVersion2, SnapshotVersion lastLimboFreeSnapshotVersion2, ByteString resumeToken2) {
        this.target = (Target) Preconditions.checkNotNull(target2);
        this.targetId = targetId2;
        this.sequenceNumber = sequenceNumber2;
        this.lastLimboFreeSnapshotVersion = lastLimboFreeSnapshotVersion2;
        this.purpose = purpose2;
        this.snapshotVersion = (SnapshotVersion) Preconditions.checkNotNull(snapshotVersion2);
        this.resumeToken = (ByteString) Preconditions.checkNotNull(resumeToken2);
    }

    public TargetData(Target target2, int targetId2, long sequenceNumber2, QueryPurpose purpose2) {
        this(target2, targetId2, sequenceNumber2, purpose2, SnapshotVersion.NONE, SnapshotVersion.NONE, WatchStream.EMPTY_RESUME_TOKEN);
    }

    public TargetData withSequenceNumber(long sequenceNumber2) {
        return new TargetData(this.target, this.targetId, sequenceNumber2, this.purpose, this.snapshotVersion, this.lastLimboFreeSnapshotVersion, this.resumeToken);
    }

    public TargetData withResumeToken(ByteString resumeToken2, SnapshotVersion snapshotVersion2) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, snapshotVersion2, this.lastLimboFreeSnapshotVersion, resumeToken2);
    }

    public TargetData withLastLimboFreeSnapshotVersion(SnapshotVersion lastLimboFreeSnapshotVersion2) {
        return new TargetData(this.target, this.targetId, this.sequenceNumber, this.purpose, this.snapshotVersion, lastLimboFreeSnapshotVersion2, this.resumeToken);
    }

    public Target getTarget() {
        return this.target;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public QueryPurpose getPurpose() {
        return this.purpose;
    }

    public SnapshotVersion getSnapshotVersion() {
        return this.snapshotVersion;
    }

    public ByteString getResumeToken() {
        return this.resumeToken;
    }

    public SnapshotVersion getLastLimboFreeSnapshotVersion() {
        return this.lastLimboFreeSnapshotVersion;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TargetData targetData = (TargetData) o;
        if (!this.target.equals(targetData.target) || this.targetId != targetData.targetId || this.sequenceNumber != targetData.sequenceNumber || !this.purpose.equals(targetData.purpose) || !this.snapshotVersion.equals(targetData.snapshotVersion) || !this.lastLimboFreeSnapshotVersion.equals(targetData.lastLimboFreeSnapshotVersion) || !this.resumeToken.equals(targetData.resumeToken)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((this.target.hashCode() * 31) + this.targetId) * 31) + ((int) this.sequenceNumber)) * 31) + this.purpose.hashCode()) * 31) + this.snapshotVersion.hashCode()) * 31) + this.lastLimboFreeSnapshotVersion.hashCode()) * 31) + this.resumeToken.hashCode();
    }

    public String toString() {
        return "TargetData{target=" + this.target + ", targetId=" + this.targetId + ", sequenceNumber=" + this.sequenceNumber + ", purpose=" + this.purpose + ", snapshotVersion=" + this.snapshotVersion + ", lastLimboFreeSnapshotVersion=" + this.lastLimboFreeSnapshotVersion + ", resumeToken=" + this.resumeToken + '}';
    }
}
