package com.google.firebase.database.core;

import com.google.firebase.database.snapshot.Node;

public final class UserWriteRecord {
    private final CompoundWrite merge;
    private final Node overwrite;
    private final Path path;
    private final boolean visible;
    private final long writeId;

    public UserWriteRecord(long writeId2, Path path2, Node overwrite2, boolean visible2) {
        this.writeId = writeId2;
        this.path = path2;
        this.overwrite = overwrite2;
        this.merge = null;
        this.visible = visible2;
    }

    public UserWriteRecord(long writeId2, Path path2, CompoundWrite merge2) {
        this.writeId = writeId2;
        this.path = path2;
        this.overwrite = null;
        this.merge = merge2;
        this.visible = true;
    }

    public long getWriteId() {
        return this.writeId;
    }

    public Path getPath() {
        return this.path;
    }

    public Node getOverwrite() {
        Node node = this.overwrite;
        if (node != null) {
            return node;
        }
        throw new IllegalArgumentException("Can't access overwrite when write is a merge!");
    }

    public CompoundWrite getMerge() {
        CompoundWrite compoundWrite = this.merge;
        if (compoundWrite != null) {
            return compoundWrite;
        }
        throw new IllegalArgumentException("Can't access merge when write is an overwrite!");
    }

    public boolean isMerge() {
        return this.merge != null;
    }

    public boolean isOverwrite() {
        return this.overwrite != null;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserWriteRecord record = (UserWriteRecord) o;
        if (this.writeId != record.writeId || !this.path.equals(record.path) || this.visible != record.visible) {
            return false;
        }
        Node node = this.overwrite;
        if (node == null ? record.overwrite != null : !node.equals(record.overwrite)) {
            return false;
        }
        CompoundWrite compoundWrite = this.merge;
        if (compoundWrite == null ? record.merge == null : compoundWrite.equals(record.merge)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = ((((Long.valueOf(this.writeId).hashCode() * 31) + Boolean.valueOf(this.visible).hashCode()) * 31) + this.path.hashCode()) * 31;
        Node node = this.overwrite;
        int i = 0;
        int result2 = (result + (node != null ? node.hashCode() : 0)) * 31;
        CompoundWrite compoundWrite = this.merge;
        if (compoundWrite != null) {
            i = compoundWrite.hashCode();
        }
        return result2 + i;
    }

    public String toString() {
        return "UserWriteRecord{id=" + this.writeId + " path=" + this.path + " visible=" + this.visible + " overwrite=" + this.overwrite + " merge=" + this.merge + "}";
    }
}
