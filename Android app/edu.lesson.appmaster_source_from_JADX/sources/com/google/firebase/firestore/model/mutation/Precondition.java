package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;

public final class Precondition {
    public static final Precondition NONE = new Precondition((SnapshotVersion) null, (Boolean) null);
    private final Boolean exists;
    private final SnapshotVersion updateTime;

    private Precondition(SnapshotVersion updateTime2, Boolean exists2) {
        Assert.hardAssert(updateTime2 == null || exists2 == null, "Precondition can specify \"exists\" or \"updateTime\" but not both", new Object[0]);
        this.updateTime = updateTime2;
        this.exists = exists2;
    }

    public static Precondition exists(boolean exists2) {
        return new Precondition((SnapshotVersion) null, Boolean.valueOf(exists2));
    }

    public static Precondition updateTime(SnapshotVersion version) {
        return new Precondition(version, (Boolean) null);
    }

    public boolean isNone() {
        return this.updateTime == null && this.exists == null;
    }

    public SnapshotVersion getUpdateTime() {
        return this.updateTime;
    }

    public Boolean getExists() {
        return this.exists;
    }

    public boolean isValidFor(MutableDocument doc) {
        if (this.updateTime == null) {
            Boolean bool = this.exists;
            if (bool == null) {
                Assert.hardAssert(isNone(), "Precondition should be empty", new Object[0]);
                return true;
            } else if (bool.booleanValue() == doc.isFoundDocument()) {
                return true;
            } else {
                return false;
            }
        } else if (!doc.isFoundDocument() || !doc.getVersion().equals(this.updateTime)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Precondition that = (Precondition) o;
        SnapshotVersion snapshotVersion = this.updateTime;
        if (snapshotVersion == null ? that.updateTime != null : !snapshotVersion.equals(that.updateTime)) {
            return false;
        }
        Boolean bool = this.exists;
        if (bool != null) {
            return bool.equals(that.exists);
        }
        if (that.exists == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        SnapshotVersion snapshotVersion = this.updateTime;
        int i = 0;
        int hashCode = (snapshotVersion != null ? snapshotVersion.hashCode() : 0) * 31;
        Boolean bool = this.exists;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        if (isNone()) {
            return "Precondition{<none>}";
        }
        if (this.updateTime != null) {
            return "Precondition{updateTime=" + this.updateTime + "}";
        }
        if (this.exists != null) {
            return "Precondition{exists=" + this.exists + "}";
        }
        throw Assert.fail("Invalid Precondition", new Object[0]);
    }
}
