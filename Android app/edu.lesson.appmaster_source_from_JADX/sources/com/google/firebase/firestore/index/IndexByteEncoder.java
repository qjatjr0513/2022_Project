package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.FieldIndex;
import com.google.protobuf.ByteString;

public class IndexByteEncoder {
    private final AscendingIndexByteEncoder ascending = new AscendingIndexByteEncoder();
    private final DescendingIndexByteEncoder descending = new DescendingIndexByteEncoder();
    /* access modifiers changed from: private */
    public final OrderedCodeWriter orderedCode = new OrderedCodeWriter();

    class AscendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        AscendingIndexByteEncoder() {
        }

        public void writeBytes(ByteString val) {
            IndexByteEncoder.this.orderedCode.writeBytesAscending(val);
        }

        public void writeString(String val) {
            IndexByteEncoder.this.orderedCode.writeUtf8Ascending(val);
        }

        public void writeLong(long val) {
            IndexByteEncoder.this.orderedCode.writeSignedLongAscending(val);
        }

        public void writeDouble(double val) {
            IndexByteEncoder.this.orderedCode.writeDoubleAscending(val);
        }

        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityAscending();
        }
    }

    class DescendingIndexByteEncoder extends DirectionalIndexByteEncoder {
        DescendingIndexByteEncoder() {
        }

        public void writeBytes(ByteString val) {
            IndexByteEncoder.this.orderedCode.writeBytesDescending(val);
        }

        public void writeString(String val) {
            IndexByteEncoder.this.orderedCode.writeUtf8Descending(val);
        }

        public void writeLong(long val) {
            IndexByteEncoder.this.orderedCode.writeSignedLongDescending(val);
        }

        public void writeDouble(double val) {
            IndexByteEncoder.this.orderedCode.writeDoubleDescending(val);
        }

        public void writeInfinity() {
            IndexByteEncoder.this.orderedCode.writeInfinityDescending();
        }
    }

    public void seed(byte[] encodedBytes) {
        this.orderedCode.seed(encodedBytes);
    }

    public DirectionalIndexByteEncoder forKind(FieldIndex.Segment.Kind kind) {
        if (kind.equals(FieldIndex.Segment.Kind.DESCENDING)) {
            return this.descending;
        }
        return this.ascending;
    }

    public byte[] getEncodedBytes() {
        return this.orderedCode.encodedBytes();
    }

    public void reset() {
        this.orderedCode.reset();
    }
}
