package com.google.firebase.firestore.model;

import com.google.firebase.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class FieldIndex {
    public static final int INITIAL_LARGEST_BATCH_ID = -1;
    public static final int INITIAL_SEQUENCE_NUMBER = 0;
    public static IndexState INITIAL_STATE = IndexState.create(0, IndexOffset.NONE);
    public static final Comparator<FieldIndex> SEMANTIC_COMPARATOR = FieldIndex$$ExternalSyntheticLambda0.INSTANCE;
    public static final int UNKNOWN_ID = -1;

    public abstract String getCollectionGroup();

    public abstract int getIndexId();

    public abstract IndexState getIndexState();

    public abstract List<Segment> getSegments();

    static /* synthetic */ int lambda$static$0(FieldIndex left, FieldIndex right) {
        int cmp = left.getCollectionGroup().compareTo(right.getCollectionGroup());
        if (cmp != 0) {
            return cmp;
        }
        Iterator<Segment> leftIt = left.getSegments().iterator();
        Iterator<Segment> rightIt = right.getSegments().iterator();
        while (leftIt.hasNext() && rightIt.hasNext()) {
            int cmp2 = leftIt.next().compareTo(rightIt.next());
            if (cmp2 != 0) {
                return cmp2;
            }
        }
        return Boolean.compare(leftIt.hasNext(), rightIt.hasNext());
    }

    public static abstract class Segment implements Comparable<Segment> {

        public enum Kind {
            ASCENDING,
            DESCENDING,
            CONTAINS
        }

        public abstract FieldPath getFieldPath();

        public abstract Kind getKind();

        public static Segment create(FieldPath fieldPath, Kind kind) {
            return new AutoValue_FieldIndex_Segment(fieldPath, kind);
        }

        public int compareTo(Segment other) {
            int cmp = getFieldPath().compareTo(other.getFieldPath());
            if (cmp != 0) {
                return cmp;
            }
            return getKind().compareTo(other.getKind());
        }
    }

    public static abstract class IndexState {
        public abstract IndexOffset getOffset();

        public abstract long getSequenceNumber();

        public static IndexState create(long sequenceNumber, IndexOffset offset) {
            return new AutoValue_FieldIndex_IndexState(sequenceNumber, offset);
        }

        public static IndexState create(long sequenceNumber, SnapshotVersion readTime, DocumentKey documentKey, int largestBatchId) {
            return create(sequenceNumber, IndexOffset.create(readTime, documentKey, largestBatchId));
        }
    }

    public static abstract class IndexOffset implements Comparable<IndexOffset> {
        public static final Comparator<MutableDocument> DOCUMENT_COMPARATOR = FieldIndex$IndexOffset$$ExternalSyntheticLambda0.INSTANCE;
        public static final IndexOffset NONE = create(SnapshotVersion.NONE, DocumentKey.empty(), -1);

        public abstract DocumentKey getDocumentKey();

        public abstract int getLargestBatchId();

        public abstract SnapshotVersion getReadTime();

        public static IndexOffset create(SnapshotVersion readTime, DocumentKey key, int largestBatchId) {
            return new AutoValue_FieldIndex_IndexOffset(readTime, key, largestBatchId);
        }

        public static IndexOffset createSuccessor(SnapshotVersion readTime, int largestBatchId) {
            Timestamp timestamp;
            long successorSeconds = readTime.getTimestamp().getSeconds();
            int successorNanos = readTime.getTimestamp().getNanoseconds() + 1;
            if (((double) successorNanos) == 1.0E9d) {
                timestamp = new Timestamp(1 + successorSeconds, 0);
            } else {
                timestamp = new Timestamp(successorSeconds, successorNanos);
            }
            return create(new SnapshotVersion(timestamp), DocumentKey.empty(), largestBatchId);
        }

        public static IndexOffset fromDocument(Document document) {
            return create(document.getReadTime(), document.getKey(), -1);
        }

        public int compareTo(IndexOffset other) {
            int cmp = getReadTime().compareTo(other.getReadTime());
            if (cmp != 0) {
                return cmp;
            }
            int cmp2 = getDocumentKey().compareTo(other.getDocumentKey());
            if (cmp2 != 0) {
                return cmp2;
            }
            return Integer.compare(getLargestBatchId(), other.getLargestBatchId());
        }
    }

    public static FieldIndex create(int indexId, String collectionGroup, List<Segment> segments, IndexState indexState) {
        return new AutoValue_FieldIndex(indexId, collectionGroup, segments, indexState);
    }

    public List<Segment> getDirectionalSegments() {
        List<Segment> filteredSegments = new ArrayList<>();
        for (Segment segment : getSegments()) {
            if (!segment.getKind().equals(Segment.Kind.CONTAINS)) {
                filteredSegments.add(segment);
            }
        }
        return filteredSegments;
    }

    public Segment getArraySegment() {
        for (Segment segment : getSegments()) {
            if (segment.getKind().equals(Segment.Kind.CONTAINS)) {
                return segment;
            }
        }
        return null;
    }
}
