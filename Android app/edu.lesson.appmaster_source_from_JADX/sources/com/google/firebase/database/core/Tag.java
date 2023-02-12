package com.google.firebase.database.core;

public final class Tag {
    private final long tagNumber;

    public Tag(long tagNumber2) {
        this.tagNumber = tagNumber2;
    }

    public long getTagNumber() {
        return this.tagNumber;
    }

    public String toString() {
        return "Tag{tagNumber=" + this.tagNumber + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && getClass() == o.getClass() && this.tagNumber == ((Tag) o).tagNumber) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.tagNumber;
        return (int) (j ^ (j >>> 32));
    }
}
