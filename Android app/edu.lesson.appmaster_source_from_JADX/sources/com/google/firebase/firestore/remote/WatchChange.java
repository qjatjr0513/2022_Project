package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import java.util.List;
import p004io.grpc.Status;

public abstract class WatchChange {

    public enum WatchTargetChangeType {
        NoChange,
        Added,
        Removed,
        Current,
        Reset
    }

    private WatchChange() {
    }

    public static final class DocumentChange extends WatchChange {
        private final DocumentKey documentKey;
        private final MutableDocument newDocument;
        private final List<Integer> removedTargetIds;
        private final List<Integer> updatedTargetIds;

        public DocumentChange(List<Integer> updatedTargetIds2, List<Integer> removedTargetIds2, DocumentKey key, MutableDocument document) {
            super();
            this.updatedTargetIds = updatedTargetIds2;
            this.removedTargetIds = removedTargetIds2;
            this.documentKey = key;
            this.newDocument = document;
        }

        public List<Integer> getUpdatedTargetIds() {
            return this.updatedTargetIds;
        }

        public List<Integer> getRemovedTargetIds() {
            return this.removedTargetIds;
        }

        public MutableDocument getNewDocument() {
            return this.newDocument;
        }

        public DocumentKey getDocumentKey() {
            return this.documentKey;
        }

        public String toString() {
            return "DocumentChange{updatedTargetIds=" + this.updatedTargetIds + ", removedTargetIds=" + this.removedTargetIds + ", key=" + this.documentKey + ", newDocument=" + this.newDocument + '}';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DocumentChange that = (DocumentChange) o;
            if (!this.updatedTargetIds.equals(that.updatedTargetIds) || !this.removedTargetIds.equals(that.removedTargetIds) || !this.documentKey.equals(that.documentKey)) {
                return false;
            }
            MutableDocument mutableDocument = this.newDocument;
            if (mutableDocument != null) {
                return mutableDocument.equals(that.newDocument);
            }
            if (that.newDocument == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int result = ((((this.updatedTargetIds.hashCode() * 31) + this.removedTargetIds.hashCode()) * 31) + this.documentKey.hashCode()) * 31;
            MutableDocument mutableDocument = this.newDocument;
            return result + (mutableDocument != null ? mutableDocument.hashCode() : 0);
        }
    }

    public static final class ExistenceFilterWatchChange extends WatchChange {
        private final ExistenceFilter existenceFilter;
        private final int targetId;

        public ExistenceFilterWatchChange(int targetId2, ExistenceFilter existenceFilter2) {
            super();
            this.targetId = targetId2;
            this.existenceFilter = existenceFilter2;
        }

        public int getTargetId() {
            return this.targetId;
        }

        public ExistenceFilter getExistenceFilter() {
            return this.existenceFilter;
        }

        public String toString() {
            return "ExistenceFilterWatchChange{targetId=" + this.targetId + ", existenceFilter=" + this.existenceFilter + '}';
        }
    }

    public static final class WatchTargetChange extends WatchChange {
        private final Status cause;
        private final WatchTargetChangeType changeType;
        private final ByteString resumeToken;
        private final List<Integer> targetIds;

        public WatchTargetChange(WatchTargetChangeType changeType2, List<Integer> targetIds2) {
            this(changeType2, targetIds2, WatchStream.EMPTY_RESUME_TOKEN, (Status) null);
        }

        public WatchTargetChange(WatchTargetChangeType changeType2, List<Integer> targetIds2, ByteString resumeToken2) {
            this(changeType2, targetIds2, resumeToken2, (Status) null);
        }

        public WatchTargetChange(WatchTargetChangeType changeType2, List<Integer> targetIds2, ByteString resumeToken2, Status cause2) {
            super();
            Assert.hardAssert(cause2 == null || changeType2 == WatchTargetChangeType.Removed, "Got cause for a target change that was not a removal", new Object[0]);
            this.changeType = changeType2;
            this.targetIds = targetIds2;
            this.resumeToken = resumeToken2;
            if (cause2 == null || cause2.isOk()) {
                this.cause = null;
            } else {
                this.cause = cause2;
            }
        }

        public WatchTargetChangeType getChangeType() {
            return this.changeType;
        }

        public List<Integer> getTargetIds() {
            return this.targetIds;
        }

        public ByteString getResumeToken() {
            return this.resumeToken;
        }

        public Status getCause() {
            return this.cause;
        }

        public String toString() {
            return "WatchTargetChange{changeType=" + this.changeType + ", targetIds=" + this.targetIds + '}';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            WatchTargetChange that = (WatchTargetChange) o;
            if (this.changeType != that.changeType || !this.targetIds.equals(that.targetIds) || !this.resumeToken.equals(that.resumeToken)) {
                return false;
            }
            Status status = this.cause;
            if (status != null) {
                if (that.cause == null || !status.getCode().equals(that.cause.getCode())) {
                    return false;
                }
                return true;
            } else if (that.cause == null) {
                return true;
            } else {
                return false;
            }
        }

        public int hashCode() {
            int result = ((((this.changeType.hashCode() * 31) + this.targetIds.hashCode()) * 31) + this.resumeToken.hashCode()) * 31;
            Status status = this.cause;
            return result + (status != null ? status.getCode().hashCode() : 0);
        }
    }
}
